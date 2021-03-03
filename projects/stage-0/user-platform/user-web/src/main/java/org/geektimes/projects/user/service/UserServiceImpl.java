package org.geektimes.projects.user.service;

import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.repository.DatabaseUserRepository;
import org.geektimes.projects.user.sql.DBConnectionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserServiceImpl implements UserService{

    private DatabaseUserRepository dur;

    public UserServiceImpl() {
        String databaseURL = "jdbc:derby:user-platform;create=true";
        try {
            Connection connection = DriverManager.getConnection(databaseURL);
            DBConnectionManager dm = new DBConnectionManager();
            dm.setConnection(connection);
            dur = new DatabaseUserRepository(dm);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public boolean register(User user) {
        dur.save(user);
        return true;
    }

    @Override
    public boolean deregister(User user) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public User queryUserById(Long id) {
        return null;
    }

    @Override
    public User queryUserByNameAndPassword(String name, String password) {
        return null;
    }
}
