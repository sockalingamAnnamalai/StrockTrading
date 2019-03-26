/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.beans.StockApiBean1;
import com.beans.User;
import java.util.List;

/**
 *
 * @author harshinijayakumar
 */
public interface UserService {

    public void registerUser(User user);
    public boolean checkUserExists(User user);
         public boolean validateLogin(User user);
public void loginUser(User user);
public void UpdateUserInfo(User user);
public List<User> showmanager();
public List<User> selectedmanager();
public String giverequest(String username);
    public int getIdFromUname(String username);
    public void Approvemanager(User user);
    public int getMidFromUid(int uid);
    public List<User> viewrequest();
    
}
