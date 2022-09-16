package pl.coderslab.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private static final String CREATE_USER_QUERY = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
    private static final String FIND_QUERY = "SELECT *  FROM ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM ? WHERE id=?";


    public void create (User user) {
        try (Connection connection = (Connection) DbUtil.connect ("workshop2"))
        {
            PreparedStatement preparedStatement = connection.prepareStatement (CREATE_USER_QUERY);
            preparedStatement.setString (1, user.getUserName ());
            preparedStatement.setString (2, user.getEmail ());
            preparedStatement.setString (3, user.getPassword ());
            preparedStatement.executeUpdate ();

        } catch (SQLException e)
        {
            e.printStackTrace ();
        }

    }
        public User read ( long id){
            User user = new User ();
            try (Connection connection = (Connection) DbUtil.connect ()) {
                PreparedStatement preparedStatement = connection.prepareStatement (FIND_QUERY);
                preparedStatement.setString (1, "users");
                preparedStatement.setLong (2, id);
                ResultSet resultSet = preparedStatement.executeQuery ();
                while (resultSet.next ()) {
                    long id1 = resultSet.getInt ("id");
                    user.setId ((int) id1);
                    user.setUserName (resultSet.getString ("userName"));
                    user.setEmail (resultSet.getString ("email"));
                }
            } catch (SQLException e) {
                e.printStackTrace ();
            }
            return user;
        }
    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }


}
