/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.beans.DBConn;
import com.service.UserServiceImpl;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import com.beans.StockApiBean;
//import static com.beans.StockApiBeans.API_KEY;
import java.io.IOException;
import java.net.MalformedURLException;

@ManagedBean (name="User")
@RequestScoped
public class User {
    String firstname,lastname,username, password, email,role;
    private double price;
   
    String mname;
    UserServiceImpl userService;
    public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
        public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
        public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
        public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}
	/**
     * @return the price
     */
    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

	public String registerUser() {
               User user = new User();
               user.email=this.email;
               user.firstname=this.firstname;
               user.lastname=this.lastname;
               user.password=this.password;
               user.username=this.username;
               user.role=this.role;
               System.out.print(user.username);
               System.out.print(user.role);
               if("manager".equals(user.role))
               {
                   userService=new UserServiceImpl();
                   userService = new UserServiceImpl();
               if(userService.checkUserExists(user)==false){
                    userService.Approvemanager(user);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("wait for approval"));
               
               }else{
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("UserId Already used"));
                   return "index";
               }
               }
               
               userService = new UserServiceImpl();
               if(userService.checkUserExists(user)==false){
                    userService.registerUser(user);
                    return "signin";
               }else{
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("UserId Already used"));
                   return "index";
               }
               
        }
         
        public String loginUser(){
            User user = new User();
            user.username = this.username;
            user.password = this.password;
            userService = new UserServiceImpl();
            if(userService.validateLogin(user)){
               //userService.loginUser(user);
               int uid = userService.getIdFromUname(this.username);
               String uname=userService.getNameFromId(user.getUsername());
               String role = userService.getRoleFromId(user.getUsername());
               String fname=userService.getFirstnameFromId(user.getUsername());
               String lname=userService.getLastnameFromId(user.getUsername());
               String em=userService.getEmailFromId(user.getUsername());
               System.out.print(uid);
                System.out.print(uname);
                System.out.print(role);
                System.out.print(fname);
              FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username",uname);
               FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("uid", uid);
             FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("role", role);
             FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("firstname", fname);
              FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("lastname", lname);
              FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("email", em);
              if("user".equals(role))
              {
              return "user";
              } else if("manager".equals(role)) {
                {
                    return "manager";
                }
              } else if("admin".equals(role))
               {
                   return "Admin";
               }
            }
        
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User name and password do not match"));
                return "signin";
        
        }
        
        public String invalidateSession(){
           FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
           return "signin";
        }
public String UpdateUserInfo(){
    System.out.print("i amuser  updateUserInfo");
    User user = new User();
               user.email=this.email;
               user.firstname=this.firstname;
               user.lastname=this.lastname;
               user.password=this.password;
               user.username=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username").toString();
               
              
               
               System.out.print(user.username);
               userService = new UserServiceImpl();
                    userService.UpdateUserInfo(user);
        return "user";
                   
             
}
public String showmanager(){
    System.out.print("user.showmanager");
    
    //user.username=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username").toString();         
              
               
              // System.out.print(user.username);
               userService = new UserServiceImpl();
       List<User> managers = userService.showmanager();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("mlist", managers);
 
        return "selectmanager";
}
public String selectedmanager(){
    System.out.print("user.showmanager");
    
    //user.username=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username").toString();         
              
               
              // System.out.print(user.username);
               userService = new UserServiceImpl();
       List<User> selectedlist = userService.selectedmanager();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("rlist", selectedlist);
 
        return "selectmanager";
}
public String displaywatchlist()
{
  System.out.print("user... displaywatchlist");
userService=new UserServiceImpl();
List<StockApiBean1> watchlist=userService.displaywatchlist();
FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("dlist",watchlist);
 
        return "watchlist";
}        

public String approvemanagerlist(){
    System.out.print("approve manager lsit");
               userService = new UserServiceImpl();
       List<User> approvemanager = userService.approvemanagerlist();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("alist", approvemanager);
 
        return "Admin";
}
        
public String createDbRec(String username)
{
    Connection conn = DBConn.getConnection();
        try {
            userService = new UserServiceImpl();
            int mid =userService.getIdFromUname(username);
            Integer uid = Integer.parseInt(FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getSessionMap().get("uid").toString());
            
            int oldMid = userService.getMidFromUid(uid);
            
            System.out.print("manager is" +mid);
            Statement statement = conn.createStatement();
            
            System.out.println(uid);
            System.out.println("username:" + username);
            
         statement.executeUpdate("INSERT INTO `manager` (`uid`, `mid`) "
                    + "VALUES ('" + uid + "','" + mid + "');");
            
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully selected ",""));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "user";
}
public String Requestpurchase(double price)
{
    Connection conn = DBConn.getConnection();
        try {
            userService = new UserServiceImpl();
            int mid =userService.getIdFromUname(username);
            Integer uid = Integer.parseInt(FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getSessionMap().get("uid").toString());
            
            
            System.out.println(uid);
            System.out.println("price:" + getPrice());
            Statement statement = conn.createStatement();
         statement.executeUpdate("INSERT INTO `request_stock` (`uid`, `price`) "
                    + "VALUES ('" + uid + "','" +getPrice()+ "');");
            
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Request is sent ",""));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "user";
}

public String registermanager(String firstname,String lastname,String email,String username,String password,String role)
{
    Connection conn = DBConn.getConnection();
        try {
            Statement statement = conn.createStatement();
//            Integer uid = Integer.parseInt(FacesContext.getCurrentInstance()
//                    .getExternalContext()
//                    .getSessionMap().get("uid").toString());
//            
//            System.out.println(uid);
            System.out.println("username:" + username);
            System.out.println("password:" + password);
            
            statement.executeUpdate("INSERT INTO `user` (`uid`, `firstname`,`lastname`,`email`,`username`,`password`,`role`) "
                    + "VALUES (null,'" + firstname + "','" + lastname + "','" + email + "','" + username + "','" + password + "','" + role + "');");
            
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully selected manager ",""));
       return "login";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "register";
        
}
           
        
public String viewrequest(){
    System.out.print("user view request");
    userService =new UserServiceImpl();
    List<User> requests = userService.viewrequest();
    System.out.print(".....username" +requests.get(0).getUsername());
    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("requests",requests);

        return "viewrequest";
    
}
    public String showpurchase()
    {
        System.out.print("user showpurchase");
        userService=new UserServiceImpl();
        List<StockApiBean> purchase = userService.showpurchase();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("plist", purchase);
        return "purchased";
    }
   public String deleterequest(String username)
{
    Connection conn = DBConn.getConnection();
        try {
            userService = new UserServiceImpl();
            int uid =userService.getIdFromUname(username);
            Integer mid = Integer.parseInt(FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getSessionMap().get("uid").toString());
            
            
            System.out.println(uid);
           
            Statement statement = conn.createStatement();
         statement.executeUpdate("DELETE FROM `request_stock` where uid='"+uid+"';");
                    
            
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Request is sent ",""));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "viewrequest";
} 
 public String declinemanager(String username){
 Connection conn = DBConn.getConnection();
        try {
            userService = new UserServiceImpl();
            int uid =userService.getIdFromUname(username);
         System.out.println("decline uid " +uid);
           
            Statement statement = conn.createStatement();
         statement.executeUpdate("DELETE FROM `approve` where username='"+username+"';");
                    
            
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Request is sent ",""));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Admin";
}
}