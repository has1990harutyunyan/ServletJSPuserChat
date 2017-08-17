package manager;

import db.DBConnectionProvider;
import model.Message;
import sun.text.resources.no.CollationData_no;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hasmik on 01.07.2017.
 */
public class MessageManager {
    private DBConnectionProvider provider;
    private Connection connection;

    public MessageManager() {
        provider = DBConnectionProvider.getInstance();
        connection = provider.getConnection();
    }

    public void sendMessage(Message message) {
        String query = "INSERT INTO messages (`from_id`, `to_id`, `message`) VALUES (?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, message.getFromId());
            preparedStatement.setLong(2, message.getToId());
            preparedStatement.setString(3, message.getMessage());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                message.setId(resultSet.getLong(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to send a message." + e);
        }


    }

    public ArrayList<Message> getAllMessages(long userId) {
        String query = "SELECT * FROM messages WHERE `to_id` = ?";
        UserManager userManager = new UserManager();
        ArrayList<Message>allMessages = new ArrayList<Message>();
        Message message;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                message = new Message();
                message.setId(resultSet.getLong(1));
                message.setFromId(resultSet.getLong(2));
                message.setToId(resultSet.getLong(3));
                message.setMessage(resultSet.getString(4));
                message.setTimestamp(resultSet.getString(5));

                message.setFromUser(userManager.getUserById(message.getFromId()));
                message.setToUser(userManager.getUserById(message.getToId()));


                allMessages.add(message);

            }
            return allMessages;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get  messages." + e);
        }

    }

}
