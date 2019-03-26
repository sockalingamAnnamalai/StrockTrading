/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.beans.StockApiBean;
import com.beans.StockApiBean1;
import com.beans.User;
import com.dao.UserDao;
import com.dao.UserDaoImpl;
import java.util.List;

/**
 *
 * @author harshinijayakumar
 */
public class UserServiceImpl implements UserService {
    
    UserDao userDao;
    public UserServiceImpl (){
        userDao = new UserDaoImpl() ;
    }
    @Override
    public void registerUser(User user) {
        System.out.print("I am at userService");
       userDao.registerUser(user);
    }

    @Override
    public boolean checkUserExists(User user) {
        return userDao.checkUserExists(user);

    }

    
@Override
    public void loginUser(User user) {
     userDao.loginUser(user);    
    }

    @Override
    public boolean validateLogin(User user) {
    return userDao.validateLogin(user);    
    }

    public String getNameFromId(String username) {
        return  userDao.getNameFromId(username);

    }
public String getRoleFromId(String role)
{
    return userDao.getRoleFromId(role);
}    
public String getFirstnameFromId(String firstname)
{
    return userDao.getFirstnameFromId(firstname);
}  

    public String getLastnameFromId(String lastname) {
        return userDao.getLasttnameFromId(lastname);
   }

    public String getEmailFromId(String email) {
        return userDao.getEmailFromId(email);
   }

    
    
    @Override
    public int getIdFromUname(String username) {
            return userDao.getIdFromUname(username);
    }

   

    public void UpdateUserInfo(User user) {
        userDao.UpdateUserInfo(user);
    }

    @Override
    public List<User> showmanager() {
        return userDao.showmanager();
        
    

    }
 public void Approvemanager(User user) {
        System.out.print("I am at userService");
       userDao.Approvemanager(user);
    
    }

    public List<User> approvemanagerlist() {
          return userDao.approvemanagerlist();
    }

    public List<StockApiBean1> displaywatchlist() {
     return userDao.displaywatchlist();
    }

    public List<User> selectedmanager() {
        return userDao.selectedmanager();
        
    }

    public String giverequest(String username) {
        return userDao.giverequest(username);
    }
    @Override
    public int getMidFromUid(int uid) {
        return userDao.getMidFromUid(uid);
    }

    @Override
    public List<User> viewrequest() {
        return userDao.viewrequest();
        
                }

    public List<StockApiBean> showpurchase() {
        return userDao.showpurchase();
                }
    
    
    
}



    

    

   
    
  

    

    

    





    

    
