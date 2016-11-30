package com.katenos.db;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.katenos.entity.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author kate_
 */
public class Database implements DAO {

    private Statement statement = null;    

    public void createConnection() {
        checkDB();
        try {
            //если таблицы нет, то создать
            statement.executeQuery("SELECT id, user, password FROM testTable");
        } catch (Exception e) {
            createTable();
            fillTable();
        }
    }

    public void checkDB() {
        this.loadDriver();
        this.getConnection();
    }

    public boolean loadDriver() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Драйвер не найден");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean getConnection() {
        try {
            String path = "mypath/";
            String dbname = "testDB";
            String connectionString = "jdbc:hsqldb:file:" + path + dbname;
            String login = "katenos";
            String password = "Qwerty1";
            Connection connection = DriverManager.getConnection(connectionString, login, password);
            statement = connection.createStatement();            
        } catch (SQLException ex) {
            System.out.println("Соединение не создано");
            ex.printStackTrace();            
            return false;
        }
        return true;
    }

    public void createTable() {
        try {
            String sql = "CREATE TABLE testTable (id IDENTITY , username VARCHAR(20), password VARCHAR(20))";
            statement.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Таблица не создана");
            e.printStackTrace();            
        }
    }

    public void fillTable() {
        try {
            String sql = "INSERT INTO testTable (username, password) VALUES ('gosha','Privet2008')";
            statement.executeUpdate(sql);
            sql = "INSERT INTO testTable (username, password) VALUES ('katenos','123456')";
            statement.executeUpdate(sql);
            sql = "INSERT INTO testTable (username, password) VALUES ('maruska','klop123')";
            statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();            
        }
    }

    public void closeConnection() {
        try {
            String sql = "SHUTDOWN";
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();            
        }
    }

    @Override
    public boolean userExist(String username) throws DAOException {
        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    private List<User> getAllUsers() throws DAOException {
        List<User> users = new ArrayList<>();
        try {
            //если не создавать и не закрывать connection после повторного запуска перестает работать с ожибкой lock бд
            this.createConnection();
            String sql = "SELECT id, username, password FROM testTable";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                users.add(new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
            }
            this.closeConnection();
            return users;
        } catch (SQLException e) {
            this.closeConnection();            
            throw new DAOException(e.getMessage());
        }
    }

    @Override
    public void addUser(User user) throws DAOException {
        try {
            this.createConnection();
            String sql = "INSERT INTO testTable (username, password) VALUES ('" + user.getUsername() + "','" + user.getPassword() + "')";
            statement.executeUpdate(sql);
            this.closeConnection();
        } catch (SQLException e) {
            this.closeConnection();            
            throw new DAOException(e.getMessage());
        }
    }

    @Override
    public User getUser(String username, String password) throws DAOException {
        User user = null;
        try {
            this.createConnection();
            String sql = "SELECT id, username, password FROM testTable";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                if (resultSet.getString(2).equals(username) && resultSet.getString(3).equals(password)) {
                    user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
                }
            }
            this.closeConnection();
            return user;
        } catch (SQLException e) {
            this.closeConnection();            
            throw new DAOException(e.getMessage());
        }
    }

}
