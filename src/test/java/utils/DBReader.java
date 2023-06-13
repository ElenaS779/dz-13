package utils;
import models.Person;
import java.sql.DriverManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DBReader {
    private final static String URL = "jdbc:postgresql://localhost:4567/postgres";
    private final static String USER_NAME = "postgres";
    private final static String USER_PASSWORD = "YMo892uUVKbU4qg86F0c";
    private final static String QUERY_SELECT_ALL = "select * from persons";
    private final static String QUERY_INSERT = "insert into persons values(?,?,?,?)";
    private final static String QUERY_UPDATE = "update persons set age=? where id=?";
    private final static String QUERY_DELETE = "delete from persons where id=?";
    private final static String QUERY_SELECT_ID = "select * from persons where id=?";

    public static List<Person> getAllPersonsFromDB(){
        List<Person> persons = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL,USER_NAME,USER_PASSWORD)){
            Statement sqlStatement =  connection.createStatement();
            ResultSet resultSet = sqlStatement.executeQuery(QUERY_SELECT_ALL);
            while (resultSet.next()){
                Person person = new Person(resultSet.getString("name"), resultSet.getString("lastname"),resultSet.getInt("age"));
                persons.add(person);
            }
        } catch (SQLException exception) {
            throw new RuntimeException(String.format("Please check connection string" +
                    ". URL [%s], name [%s], pass [%s]", URL, USER_NAME, USER_PASSWORD));
        }
           return persons;
    }
    public static void insertToDB(int id, String name, String lastname, int age) {
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_INSERT);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, lastname);
            preparedStatement.setInt(4, age);
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            throw new RuntimeException(String.format("Please check connection string" +
                    ". URL [%s], name [%s], pass [%s]", URL, USER_NAME, USER_PASSWORD));
        }
    }

    public static void updateDB(int id, int age) {
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_UPDATE);
            preparedStatement.setInt(1, age);
            preparedStatement.setInt(2, id);
        } catch (SQLException exception) {
            throw new RuntimeException(String.format("Please check connection string" +
                    ". URL [%s], name [%s], pass [%s]", URL, USER_NAME, USER_PASSWORD));
        }

    }

    public static void deleteById(int id) {
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            throw new RuntimeException(String.format("Please check connection string" +
                    ". URL [%s], name [%s], pass [%s]", URL, USER_NAME, USER_PASSWORD));
        }
    }
    public static Person selectById(int id) {
        Person person = null;
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SELECT_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                person = new Person(resultSet.getString("name"), resultSet.getString("lastname"),resultSet.getInt("age"));
                break;
            }

        } catch (SQLException exception) {
            throw new RuntimeException(String.format("Please check connection string" +
                    ". URL [%s], name [%s], pass [%s]", URL, USER_NAME, USER_PASSWORD));
        }
        return person;
    }
}
