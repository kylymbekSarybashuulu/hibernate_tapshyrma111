package org.example.dao;

import org.example.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.util.Util.connect;

public class UserDaoJdbcImpl implements UserDao {
    public UserDaoJdbcImpl() {

    }

    public void createUsersTable()  {
        String SQL = "CREATE TABLE users(" +
                "id SERIAL PRIMARY KEY," +
                "name VARCHAR (50)," +
                "lastName VARCHAR (60)," +
                "age SMALLINT NOT NULL)";
        try (Connection connection = connect()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(SQL);
            System.out.println("таблица успешно тузулду");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void dropUsersTable(){
        String DROP_SQL = "DROP TABLE users";
        try (Connection connect = connect();
             Statement statement = connect.createStatement()) {
            statement.executeUpdate(DROP_SQL);
            System.out.println("удаления таблиц ");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age){
        String SQL = "INSERT INTO users(name,lastName,age) VALUES(?,?,?)";
        try(Connection connect = connect();
            PreparedStatement statement = connect.prepareStatement(SQL)){
            statement.setString(1,name);
            statement.setString(2,lastName);
            statement.setByte(3,age);
            statement.executeUpdate();
            System.out.println("маалымат кошулду");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String REMOVE_ID = "DELETE FROM users WHERE id = ?";
        try(Connection connect = connect();
            PreparedStatement statement = connect.prepareStatement(REMOVE_ID)){
            statement.setInt(1, (int) id);
            statement.executeUpdate();
            System.out.println("удаление с айдишкой");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        try(Connection connect = connect();
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){
            while (resultSet.next()){
                User user1 = new User();
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastname");
                int age = resultSet.getInt("age");
                user1.setId((long) id);
                users.add(user1);
                user1.setName(name);
                user1.setLastName(lastName);
                user1.setAge((byte)age);
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return users;
    }

    public void cleanUsersTable() {
        String CLEAN_SQL ="TRUNCATE TABLE users";
        try(Connection connect = connect();
            Statement statement = connect.createStatement()){
            // statement.executeUpdate(CLEAN_SQL);
            System.out.println("чистка таблиц");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

