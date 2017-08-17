package manager;

import db.DBConnectionProvider;
import model.User;

import java.sql.*;
import java.util.ArrayList;

public class UserManager {
    private DBConnectionProvider provider;
    private Connection connection;

    public UserManager() {
        provider = DBConnectionProvider.getInstance();
        connection = provider.getConnection();
    }

    public void addUser(User user) {
        String query = "INSERT INTO user (`name`, `surname`,`age`,`email`,`password`,`profile_pic`) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getProfilePic());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getLong(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to add user to DB." + e);
        }


    }

    public boolean isEmailExist(String email) {
        String query = "SELECT `email` FROM `user` WHERE `email` = ?";
        String emailExist = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                emailExist = resultSet.getString(1);
            }
            if (email.equals(emailExist)) {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


    public User getUserByEmailAndPassword(String email, String password) {
        String query = "SELECT * FROM user WHERE `email` = ? AND `password`= ?";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setSurname(resultSet.getString(3));
                user.setAge(resultSet.getInt(4));
                user.setEmail(resultSet.getString(5));
                user.setPassword(resultSet.getString(6));
                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to verify user" + e);
        }
    }

    public ArrayList<User> getAllUsers(long id) {
        String query = "SELECT * FROM user WHERE `id` NOT LIKE ?";
        ArrayList<User> allUsers = new ArrayList<User>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            User user;
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setSurname(resultSet.getString(3));
                user.setAge(resultSet.getInt(4));
                user.setEmail(resultSet.getString(5));
                user.setPassword(resultSet.getString(6));
                allUsers.add(user);
            }
            return allUsers;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve all users from DB." + e);
        }

    }

    public void addFriend(long id1, long id2) {

        String query = "INSERT INTO friend_list (`user_id`,`friend_id`) VALUES (?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id1);
            preparedStatement.setLong(2, id2);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to add friend to DB." + e);
        }


    }

    public User getUserById(long friend_id) {
        String query = "SELECT * FROM user WHERE `id` = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, friend_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            User user = null;
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setSurname(resultSet.getString(3));
                user.setAge(resultSet.getInt(4));
                user.setEmail(resultSet.getString(5));
                user.setPassword(resultSet.getString(6));

            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve friend list from DB." + e);
        }
    }

    public ArrayList<Long> getFriendId(long userId) {
        String query = "SELECT `friend_id` FROM `friend_list` WHERE `user_id` = ?";
        ArrayList<Long> list = new ArrayList<Long>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getLong(1));

            }
            return list;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve friend list from DB." + e);
        }

    }

    public void deleteFromFriendList(long id) {
        String query = "DELETE FROM `friend_list` where `friend_id` = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete friend." + e);
        }
    }

    public String getProfilePic(long id) {
        String query = "SELECT `profile_pic` FROM user WHERE `id` = ?";
        String profilePic = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                profilePic = resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profilePic;
    }

}
