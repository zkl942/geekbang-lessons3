package org.geektimes.projects.user.hashing;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;

public class ConsistentHashingWithVirtualNode {

    //List of servers to be added to the Hash ring
    private static String[] servers = {
            "192.168.0.0:111", "192.168.0.1:111", "192.168.0.2:111",
            "192.168.0.3:111", "192.168.0.4:111"};

    //For the real node list, considering the online and offline scenarios of the server, that is, the scenarios of adding and deleting will be more frequent. It will be better to use LinkedList here
    private static List<String> realNodes = new LinkedList<String>();

    //The key is the hash value of the virtual node, and the value is the name of the virtual node
    private static SortedMap<Integer, String> virtualNodes = new TreeMap<Integer, String>();

    //The number of virtual nodes is written here. For demonstration, a real node corresponds to five virtual nodes
    private static final int VIRTUAL_NODES = 5;

    static{
        //First add the original server to the real node list
        for(int i=0; i<servers.length; i++)
            realNodes.add(servers[i]);

        //Adding virtual nodes, traversing LinkedList, using foreach loop will be more efficient
        for (String str : realNodes){
            for(int i=0; i<VIRTUAL_NODES; i++){
                String virtualNodeName = str + "&&VN" + String.valueOf(i);
                int hash = getHash(virtualNodeName);
                System.out.println("Virtual node [" + virtualNodeName + "] added, and its hash value is " + hash);
                virtualNodes.put(hash, virtualNodeName);
            }
        }
        System.out.println();
    }

    //Using FNV1_32_HASH algorithm to calculate the hash value of the server, here does not use the method of rewriting hashCode, the final effect is no difference
    private static int getHash(String str){
        final int p = 16777619;
        int hash = (int)2166136261L;
        for (int i = 0; i < str.length(); i++)
            hash = (hash ^ str.charAt(i)) * p;
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        // If the calculated value is negative, take its absolute value
        if (hash < 0)
            hash = Math.abs(hash);
        return hash;
    }

    //Get the node that should be routed to
    private static String getServer(String key){
        //Get the hash value of the key
        int hash = getHash(key);
        // Get all maps larger than the Hash value
        SortedMap<Integer, String> subMap = virtualNodes.tailMap(hash);
        String virtualNode;
        if(subMap.isEmpty()){
            //If there is no one larger than the hash value of the key, start with the first node
            Integer i = virtualNodes.firstKey();
            //Return to the corresponding server
            virtualNode = virtualNodes.get(i);
        }else{
            //The first Key is the node closest to the node clockwise
            Integer i = subMap.firstKey();
            //Return to the corresponding server
            virtualNode = subMap.get(i);
        }
        //The name of virtual node should be intercepted
        if(StringUtils.isNotBlank(virtualNode)){
            return virtualNode.substring(0, virtualNode.indexOf("&&"));
        }
        return null;
    }

    public static void main(String[] args){
        String[] keys = {
                "sunlight", "moon", "stars"};
        for(int i=0; i<keys.length; i++)
            System.out.println("[" + keys[i] + "] its hash value is " +
                    getHash(keys[i]) + ", and it's routed to the node [" + getServer(keys[i]) + "]");
    }
}