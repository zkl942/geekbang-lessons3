package org.geektimes.projects.user.hashing;

import java.util.SortedMap;
import java.util.TreeMap;

/** * Key points: 1. How to build a hash ring, 2. How to map server nodes on Hash ring, 3. How to find corresponding nodes */
public class ConsistentHashingWithoutVirtualNode {

    //List of servers to be added to the Hash ring
    private static String[] servers = { "192.168.0.0:111", "192.168.0.1:111",
            "192.168.0.2:111", "192.168.0.3:111", "192.168.0.4:111" };

    //The key represents the hash value of the server, and the value represents the server
    private static SortedMap<Integer, String> sortedMap = new TreeMap<Integer, String>();

    //Program initialization, put all servers into sortedMap
    static {
        for (int i=0; i<servers.length; i++) {
            int hash = getHash(servers[i]);
            System.out.println("[" + servers[i] + "] joins the collection, and its hash value is " + hash);
            sortedMap.put(hash, servers[i]);
        }
        System.out.println();
    }

    //Get the node that should be routed to
    private static String getServer(String key) {
        //Get the hash value of the key
        int hash = getHash(key);
        //Get all maps larger than the Hash value
        SortedMap<Integer, String> subMap = sortedMap.tailMap(hash);
        if(subMap.isEmpty()){
            //If there is no one larger than the hash value of the key, start with the first node
            Integer i = sortedMap.firstKey();
            //Return to the corresponding server
            return sortedMap.get(i);
        }else{
            //The first Key is the node closest to the node clockwise
            Integer i = subMap.firstKey();
            //Return to the corresponding server
            return subMap.get(i);
        }
    }

    //Using FNV1_32_HASH algorithm to calculate the hash value of the server, here does not use the method of rewriting hashCode, the final effect is no difference
    private static int getHash(String str) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
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

    public static void main(String[] args) {
        String[] keys = {
                "sunlight", "moon", "stars"};
        for(int i=0; i<keys.length; i++)
            System.out.println("[" + keys[i] + "] has hash value of " + getHash(keys[i])
                    + ", and is routed to the node [" + getServer(keys[i]) + "]");
    }
}