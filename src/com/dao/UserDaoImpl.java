/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;
import com.beans.DBConn;

import com.beans.StockApiBean;
import com.beans.StockApiBean1;
import com.beans.User;

import static com.mysql.jdbc.Messages.getString;
import com.service.UserServiceImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Null;

/**
 *
 * @author harshinijayakumar
 */
public class UserDaoImpl implements UserDao{

    Statement statement;
    private Connection conn;
    private String username;
    UserServiceImpl userService;
   // private String username;
    public UserDaoImpl() {
        
       conn = DBConn.getConnection();
    }


    @Override
    public void registerUser(User user) {
        try {
            statement = conn.createStatement();
            System.out.println("........"+ user.getRole());
            statement.execute("INSERT INTO `user` VALUES (uid,'"+user.getFirstname()+"','"+user.getLastname()+"','"+user.getEmail()+"','"+user.getUsername()+"','"+user.getPassword()+"','"+user.getRole()+"',balance);");
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    

    @Override
    public boolean checkUserExists(User user) {
        try {
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM `user` WHERE `username`='"+user.getUsername()+"';");
            if(rs.next()){
                
                
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;

    }

    @Override
    public boolean validateLogin(User user) {
        
        try {
            statement = conn.createStatement();
            ResultSet rs1 = statement.executeQuery("SELECT * FROM `user` WHERE username='"+user.getUsername()+"' AND password='"+user.getPassword()+"';");
            if(rs1.next())
            {
                return true;
            }
            else {
                return false;
                   }
        } 
        catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
        
    }

    @Override
    public void loginUser(User user) {
       
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNameFromId(String username) {
        try {
            statement = conn.createStatement();
            
            ResultSet rs = statement.executeQuery("SELECT * FROM `user` WHERE username='"+username+"';");
            if(rs.next()){
                return rs.getString("username");
            }
            else{
                return "";
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    @Override
    public String getRoleFromId(String username) {
        try {
            statement = conn.createStatement();
            
            ResultSet rs = statement.executeQuery("SELECT * FROM `user` WHERE username='"+username+"';");
            if(rs.next()){
                return rs.getString("role");
            }
            else{
                return "";
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    
    }
public String getFirstnameFromId(String username){
        try {
            statement = conn.createStatement();
            
            ResultSet rs = statement.executeQuery("SELECT * FROM `user` WHERE username='"+username+"';");
            if(rs.next()){
                return rs.getString("firstname");
            }
            else{
                return "";
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    
    }

    @Override
    public String getUserInfo(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getLasttnameFromId(String username) {
       try {
            statement = conn.createStatement();
            
            ResultSet rs = statement.executeQuery("SELECT * FROM `user` WHERE username='"+username+"';");
            if(rs.next()){
                return rs.getString("lastname");
            }
            else{
                return "";
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";

    }

    @Override
    public String getEmailFromId(String username) {
       
        try {
            statement = conn.createStatement();
            
            ResultSet rs = statement.executeQuery("SELECT * FROM `user` WHERE username='"+username+"';");
            if(rs.next()){
                return rs.getString("email");
            }
            else{
                return "";
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";

    }

    

    @Override
    public int getIdFromUname(String username) {
            try {
            statement = conn.createStatement();
            
            ResultSet rs = statement.executeQuery("SELECT * FROM `user` WHERE username='"+username+"';");
            if(rs.next()){
                return rs.getInt("uid");
            }
           else{
                return -1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public void UpdateUserInfo(User user) {
        System.out.print("userdaoimpl");
        try {
            statement=conn.createStatement();
           System.out.println("........"+ user.getFirstname());
           System.out.println("........"+ user.getLastname());
           System.out.println("........"+ user.getUsername());
           System.out.println("........"+ user.getEmail());
           statement.execute("UPDATE `user` set firstname='"+user.getFirstname()+"',lastname='"+user.getLastname()+"',email='"+user.getEmail()+"' where username='"+user.getUsername()+"';");
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public List<User> showmanager() {
        List<User> selectmanager = new ArrayList<User>();
        System.out.print("i am at userdaoimpls selectmanager");
        try {
            statement=conn.createStatement();
            ResultSet rs= statement.executeQuery("SELECT * FROM `user` WHERE role='manager';");
           User user;
           while(rs.next()){
            user = new User();
            user.setFirstname(rs.getString("firstname"));
           user.setLastname(rs.getString("lastname"));
           user.setUsername(rs.getString("username"));
           user.setEmail(rs.getString("email"));
           selectmanager.add(user);
           }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return selectmanager;
    }

    

    @Override
    public void Approvemanager(User user) {
        {
            try {
            statement = conn.createStatement();
            System.out.println("........"+ user.getRole());
            statement.execute("INSERT INTO `approve` VALUES (uid,'"+user.getFirstname()+"','"+user.getLastname()+"','"+user.getEmail()+"','"+user.getUsername()+"','"+user.getPassword()+"','"+user.getRole()+"');");
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

        
}

    @Override
    public List<User> approvemanagerlist() {
        List<User> approvemanager = new ArrayList<User>();
        System.out.print("i am at userdaoimpls approvemanagerlist");
        try {
            statement=conn.createStatement();
            ResultSet rs= statement.executeQuery("SELECT * FROM `approve` WHERE role='manager';");
           User user;
           while(rs.next()){
            user = new User();
            user.setFirstname(rs.getString("firstname"));
           user.setLastname(rs.getString("lastname"));
          user.setEmail(rs.getString("email"));
user.setUsername(rs.getString("username"));
          user.setPassword(rs.getString("password"));
          user.setRole(rs.getString("role"));
           approvemanager.add(user);
           }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return approvemanager;
    }

    @Override
    public List<StockApiBean1> displaywatchlist() {
    List<StockApiBean1> watchlist=new ArrayList<StockApiBean1>();
    System.out.print("userdao impl watchlist ");
        try {
            Integer uid = Integer.parseInt(FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getSessionMap().get("uid").toString());
            
            System.out.println(uid);
            statement=conn.createStatement();
            ResultSet rs=statement.executeQuery("SELECT * FROM `watchlist` WHERE uid='"+uid+"';");
            StockApiBean1 stockapibean1;
            while(rs.next())
            {
                stockapibean1 =new StockApiBean1();
               // stockapibean1.setPurchaseSymbol(rs.getString("symbol"));
               stockapibean1.setSymbol(rs.getString("symbol"));
               stockapibean1.setPrice(rs.getDouble("price"));
        watchlist.add(stockapibean1);
            }
        }catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return watchlist;
    }

    @Override
    public List<User> selectedmanager() {
        List<User> selectedlist = new ArrayList<User>();
        System.out.print(" userdaoimpls selectedmanager");
        try {
            Integer uid = Integer.parseInt(FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getSessionMap().get("uid").toString());
          
            System.out.println(uid);
            statement=conn.createStatement();
            ResultSet rs= statement.executeQuery("SELECT * FROM `manager` WHERE uid='"+uid+"';");
           User user;
           while(rs.next()){
            user = new User();
//            user.setFirstname(rs.getString("firstname"));
//           user.setLastname(rs.getString("lastname"));
           user.setUsername(rs.getString("managername"));
//           user.setEmail(rs.getString("email"));
           selectedlist.add(user);
           
           }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return selectedlist;
    }

    @Override
    public String giverequest(String username) {
           
        System.out.print("mname is ..." +username);
    userService = new UserServiceImpl();
//         int mid = userService.getIdFromUname(this.username); 
//         System.out.print(mid);
        return "";
        
        
    }

    @Override
    public int getMidFromUid(int uid) {
   try {
            statement = conn.createStatement();
            
            ResultSet rs = statement.executeQuery("SELECT mid FROM `manager` WHERE uid="+uid+"';");
            if(rs.next()){
                
                return rs.getInt("mid");
                
            }
           else{
                return -1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public  List<User> viewrequest() {
                List<User> users=new ArrayList<User>();

    try {
        User user;
        int i =0;
        Integer uid = Integer.parseInt(FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getSessionMap().get("uid").toString());
          
            System.out.println(uid);
            statement = conn.createStatement();
            
            ResultSet rs = statement.executeQuery("select username, price from user u1 join request_stock r1 join manager m1 where u1.uid=r1.uid=m1.uid and m1.uid IN(select uid from manager where  mid="+uid+");");
            while(rs.next()){
                ;
                user = new User();
                user.setPrice(rs.getDouble("price"));
                user.setUsername(rs.getString("username"));
                users.add(user);
            i++;
            }
            if(i==0){
                    user = new User();
                   user.setUsername("No Requests");
                   users.add(user);
            }
            return users;
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    
    }

    @Override
    public List<StockApiBean> showpurchase() {
                List<StockApiBean> purchase = new ArrayList<StockApiBean>();
        System.out.print("i am at userdaoimpls showpurchase");
        try {
            Integer uid = Integer.parseInt(FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getSessionMap().get("uid").toString());
            statement=conn.createStatement();
            ResultSet rs= statement.executeQuery("SELECT * FROM `purchase` WHERE uid='"+uid+"';");
           StockApiBean stockapibean;
           while(rs.next()){
            stockapibean = new StockApiBean();
            stockapibean.setSymbol(rs.getString("stock_symbol"));
           stockapibean.setQty(rs.getInt("qty"));
          stockapibean.setPrice(rs.getDouble("price"));
stockapibean.setAmt(rs.getDouble("amt"));
          
           purchase.add(stockapibean);
           }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return purchase;
    }

    
    
    }



    

    
