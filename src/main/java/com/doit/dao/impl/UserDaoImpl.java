package com.doit.dao.impl;

import com.doit.data.User;
import com.doit.dataSource.DataSource;
import com.doit.dao.UserDao;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private Statement statement;

    public UserDaoImpl() {
        try {
            Connection connection = DataSource.getInstance().getConnection();
            statement = connection.createStatement();
        } catch (SQLException | IOException | PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User get(int id) {
        List<String> userProperties = new ArrayList<>();
        User user = new User();
        try {
            ResultSet set = statement.executeQuery("SELECT USER_ID, USER_NAME, USER_ROLE  from USERS WHERE USERS.USER_ID = "
                    + String.valueOf(id));
            set.beforeFirst();
            set.next();
            user.setId(set.getInt("USER_ID"));
            user.setName(set.getString("USER_NAME"));
            user.setRole(set.getInt("USER_ROLE"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try {
            ResultSet set = statement.executeQuery("SELECT USER_ID, USER_NAME, USER_ROLE from USERS");
            set.beforeFirst();
            while (set.next()) {
                User newUser = new User();
                newUser.setId(set.getInt("USER_ID"));
                newUser.setName(set.getString("USER_NAME"));
                newUser.setRole(set.getInt("USER_ROLE"));

                users.add(newUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean put(int id, User user) {
        boolean flag = false;
        User newUser = new User();
        try {
            statement.execute(String.format("UPDATE USERS SET USER_NAME='%s',USER_ROLE=%d WHERE USER_ID=%d",
                    user.getName(), user.getRole(), id));
            if (statement.getUpdateCount() != 0) flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean post(User user) {
        boolean flag = false;
        try {
            statement.execute(String.format("INSERT INTO USERS(USER_NAME, USER_ROLE) VALUES ('%s',%d)", user.getName(), user.getRole()));
            if (statement.getUpdateCount() != 0) flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean delete(int id) {
        boolean flag = false;
        try {
            if (get(id).getRole() == 1) {
                System.out.println(countAdmin());
                if (countAdmin() <= 1) flag = false;
                else {
                    statement.execute(String.format("DELETE FROM USERS WHERE USER_ID = %s", String.valueOf(id)));
                    if (statement.getUpdateCount() != 0) flag = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<User> getByRole(int role) {
        List<User> users = new ArrayList<>();
        try {
            ResultSet set = statement.executeQuery("SELECT USER_ID, USER_NAME, USER_ROLE from USERS WHERE USER_ROLE = "
                    + String.valueOf(role));
            set.beforeFirst();
            while (set.next()) {
                User newUser = new User();
                newUser.setId(set.getInt("USER_ID"));
                newUser.setName(set.getString("USER_NAME"));
                newUser.setRole(set.getInt("USER_ROLE"));
                users.add(newUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    public int countAdmin() {
        int count = 0;
        try {
            ResultSet set = statement.executeQuery("Select count(*) from Users where user_role = 1");
            set.beforeFirst();
            set.next();
            count = set.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

//    public static void main(String[] args) {
//        UserDaoImpl userDao = new UserDaoImpl();
//        userDao.countAdmin();
//    }
}