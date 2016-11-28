/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.katenos.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author kate_
 */
public class Database {

    Connection connection = null;

    public boolean checkDB() {
        if (!this.loadDriver() && !this.getConnection()) {
            return true;
        } else {
            return false;
        }
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
            connection = DriverManager.getConnection(connectionString, login, password);
        } catch (SQLException e) {
            System.out.println("Соединение не создано");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void createTable() {
        try {
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE testTable (id IDENTITY , user VARCHAR(20), password VARCHAR(20))";
            statement.executeUpdate(sql);
        } catch (SQLException e) {

        }
    }

    public void fillTable() {
        Statement statement;
        try {
            statement = connection.createStatement();
            String sql = "INSERT INTO testTable (user, password) VALUES ('katenos','123456')";
            statement.executeUpdate(sql);
            sql = "INSERT INTO testTable (user, password) VALUES ('gosha','Privet2008')";
            statement.executeUpdate(sql);
            sql = "INSERT INTO testTable (user, password) VALUES ('maruska','klop123')";
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeTable() {
        Statement statement;
        try {
            statement = connection.createStatement();
            String sql = "DROP TABLE testTable";
            statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getData() {
        Statement statement;
        try {
            statement = connection.createStatement();
            String sql = "SELECT id, user, password FROM testTable";
            ResultSet resultSet = statement.executeQuery(sql);
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void printTable() {
        Statement statement;
        try {
            statement = connection.createStatement();
            String sql = "SELECT id, user, password FROM testTable";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                System.out.println(resultSet.getString(2) + " "
                        + resultSet.getString(3));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {

        Statement statement;
        try {
            statement = connection.createStatement();
            String sql = "SHUTDOWN";
            statement.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
