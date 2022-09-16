package pl.coderslab.entity;

import java.sql.*;
import java.util.Arrays;

public class UserDao {
    private static final String CREATE_USER_QUERY = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
    private static final String UPDATE_USER_QUERY = "UPDATE users SET username=?, email=?, password=? WHERE id=?";
    private static final String FIND_QUERY = "SELECT *  FROM users WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM users WHERE id=?";

    private static final String FIND_ALL = "SELECT *  FROM users WHERE 1";


    public static User create (User user) {
        try (Connection conn = DbUtil.connect ()) {
            PreparedStatement statement =
                    conn.prepareStatement (CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString (1, user.getUserName ());
            statement.setString (2, user.getEmail ());
            statement.setString (3, hashPassword (user.getPassword ()));
            statement.executeUpdate ();
            //Pobieramy wstawiony do bazy identyfikator, a następnie ustawiamy id obiektu user.
            ResultSet resultSet = statement.getGeneratedKeys ();
            if (resultSet.next ()) {
                user.setId (resultSet.getInt (1));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace ();
            return null;
        }
    }

    public static User read (long id) {
        User user = new User ();
        try (Connection connection = (Connection) DbUtil.connect ()) {
            PreparedStatement statement = connection.prepareStatement (FIND_QUERY);
            statement.setString (1, String.valueOf (id));
            statement.executeQuery ();
            ResultSet resultSet = statement.executeQuery ();
            while (resultSet.next ()) {
                long id1 = resultSet.getInt ("id");
                user.setId ((int) id1);
                user.setUserName (resultSet.getString ("userName"));
                user.setEmail (resultSet.getString ("email"));
                user.setPassword (resultSet.getString ("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }
        return user;
    }

    public static void delete (int userId) {
        try (Connection conn = DbUtil.connect ()) {
            PreparedStatement statement =
                    conn.prepareStatement (DELETE_QUERY);
            statement.setInt (1, userId);
            statement.executeUpdate ();
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }
    public static void update (User user) {
        try (Connection conn = DbUtil.connect ()) {
            PreparedStatement statement =
                    conn.prepareStatement (UPDATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString (1, user.getUserName ());
            statement.setString (2, user.getEmail ());
            statement.setString (3, hashPassword (user.getPassword ()));
            statement.setInt (4, user.getId ());
            statement.executeUpdate ();
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    public static String hashPassword (String password) {
        return org.mindrot.jbcrypt.BCrypt.hashpw (password, org.mindrot.jbcrypt.BCrypt.gensalt ());
    }

    private static User[] addToArray (User u, User[] users) {
        User[] tmpUsers = Arrays.copyOf (users, users.length + 1);
        // Tworzymy kopię tablicy powiększoną o 1.
        tmpUsers[users.length] = u;
        // Dodajemy obiekt na ostatniej pozycji.
        return tmpUsers; //
        // Zwracamy nową tablicę.
    }

    public static User[] findAll () {
        User[] usersArray = new User[0];
        try (Connection conn = DbUtil.connect ()) {
            PreparedStatement statement = conn.prepareStatement (FIND_ALL);
            ResultSet resultSet = statement.executeQuery ();
            while (resultSet.next ()) {
                User user = new User ();
                long id1 = resultSet.getInt ("id");
                user.setId ((int) id1);
                user.setUserName (resultSet.getString ("userName"));
                user.setEmail (resultSet.getString ("email"));
                user.setPassword (resultSet.getString ("password"));
                usersArray = addToArray (user, usersArray);
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }
        return usersArray;
    }

    public static void generateUser (String surnamePart) {
        User user = new User ();
        user.setUserName (surnamePart + "ski");
        user.setEmail (surnamePart.toLowerCase () + "ski@nothing.com");
        user.setPassword (randomString (8));
        UserDao.create (user);

    }

    public static String randomString (int len) {
        char[] str = new char[100];
        for (int i = 0; i < len; i++) {
            str[i] = (char) (((int) (Math.random () * 26)) + (int) 'A');
        }
        return (new String (str, 0, len));
    }


}
