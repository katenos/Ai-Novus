/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.katenos.db;

import com.katenos.entity.User;

/**
 *
 * @author admin
 */

public interface DAO {

    boolean userExist(String username) throws DAOException;

    void addUser(User user) throws DAOException;

    User getUser (String username, String password)throws DAOException;
}
