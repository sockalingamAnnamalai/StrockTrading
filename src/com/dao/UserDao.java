
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.beans.StockApiBean;
import com.beans.StockApiBean1;
import com.beans.User;
import java.util.List;

/**
 *
 * @author harshinijayakumar
 */
public interface UserDao {

    
    
    public void registerUser(User user);
    public boolean checkUserExists(User user);

    public boolean validateLogin(User user);


    public void loginUser(User user);
public String getUserInfo(User user);
    public String getNameFromId(String username);
   public String getRoleFromId(String username);
   public String getFirstnameFromId(String username);

    public String getLasttnameFromId(String username);
    public String getEmailFromId(String username);
    

    public int getIdFromUname(String username);

    public void UpdateUserInfo(User user);

    public List<User> showmanager();

    

    public void Approvemanager(User user);

    public List<User> approvemanagerlist();

    public List<StockApiBean1> displaywatchlist();

    public List<User> selectedmanager();

    public String giverequest(String username);

    public int getMidFromUid(int uid);

    public List<User> viewrequest();

    public List<StockApiBean> showpurchase();
    
    }
    

   

    

   
