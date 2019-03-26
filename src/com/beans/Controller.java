package com.beans;

import java.io.IOException;
import java.sql.ResultSet;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.dao.Datainsert;

@ManagedBean(name="Controller")
@SessionScoped
public class Controller {
	
	private String firstname="";
	private String lastname="";
	private String address="";
	private String phonenumber="";
	private String email="";
	private String username="";
	private String password="";
	private String approved="";
	

	
	/*public void nameValidator(FacesContext context, UIComponent comp, Object value) {
		String regex = "[A-Za-z]*";
		String name = (String) value;
		String nameTrim = name.replaceAll("\\s","");
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher((CharSequence) nameTrim);
		if(!matcher.matches() || nameTrim.length() == 0 || nameTrim.length()>15) {
			((UIInput) comp).setValid(false);
			
			FacesMessage message = new FacesMessage("Invalid Name");
			context.addMessage(comp.getClientId(context), message);
		}
		
	}
	
	public void emailValidator(FacesContext context, UIComponent comp, Object value) {
		String regex = "^(.+)@(.+)$";
		
		String emailAddress = (String) value;
		String emailAddressTrim = emailAddress.replaceAll("\\s","");
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher((CharSequence) emailAddressTrim);
		if(!matcher.matches() || emailAddressTrim.length() == 0) {
			((UIInput) comp).setValid(false);
			
			FacesMessage message = new FacesMessage("Invalid Email Address");
			context.addMessage(comp.getClientId(context), message);
		}
	}*/
	
	
	
	/*public void UIDValidator(FacesContext context, UIComponent comp, Object value) {
		String regex = "[A-Za-z0-9]*";
		String userid = (String) value;
		String useridTrim = userid.replaceAll("\\s","");
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher((CharSequence) useridTrim);
		if(useridTrim.length()>12 || !matcher.matches() || useridTrim.length()==0) {
			((UIInput) comp).setValid(false);
			
			FacesMessage message = new FacesMessage("Invalid userID. Please enter userId less than 12 characters");
			context.addMessage(comp.getClientId(context), message);
		}
	}
	
	public void passwordValidator(FacesContext context, UIComponent comp, Object value) {
		String regex = "[A-Za-z0-9@#$%^&+=]*";
		String password = (String) value;
		String passwordTrim = password.replaceAll("\\s", "");
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher((CharSequence) passwordTrim);
		if(passwordTrim.length()>12 || !matcher.matches() || passwordTrim.length()==0) {
			((UIInput) comp).setValid(false);
			
			FacesMessage message = new FacesMessage("Maximum length of userID is 12 characters");
			context.addMessage(comp.getClientId(context), message);
		}
	}*/
	
	

	
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
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
	

	public String getApproved() {
		return approved;
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}

	public String RegisterController() {
		
		boolean userInserted = Datainsert.insertUser(this.firstname, this.lastname, this.address, this.phonenumber, this.email, this.username, this.password);
		if(userInserted) {
			//FacesContext.getCurrentInstance().addMessage("signInForm:loginButton", new FacesMessage(FacesMessage.SEVERITY_INFO,"User Successfuly Registered", null));
			//FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			//this.name = this.emailAddress = this.userID = this.password = "";
		  return ("successfully_registered.xhtml");
		}
		else
		  return ("unsuccess.xhtml");
	}

   /* public String validateUser() {
        boolean valid = LoginDAO.validate(username, password);
        if (valid == true) {

            return "userhome?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN, "Incorrect Username and Passowrd. Please enter correct username and Password",""));
            return "login";
        }
    }
    public String validatemyuser() {
        boolean valid = LoginDAO.validatemyuser(username, password);
        if (valid == true) {

            return "userhome?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN, "Incorrect Username and Passowrd. Please enter correct username and Password",""));
            return "login";
        }
    }

    // logout event, invalidate session
    public void logout() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
    }
    public String ProfileinfodisplayController() {
    	String inforead = Datainsert.readUserinfo(this.username);
    	if(inforead != null) {
    	  return ("Profile_info.xhtml");
    	}
    	else
    	  return ("unsuccess.xhtml");
    }
    
    public String EditController() {
		boolean userUpdated = Datainsert.updateUser(this.firstname, this.username);
		if(userUpdated) {
		  return ("edit.xhtml");
		}
		else
		  return ("unsuccess.xhtml");
}
	public String ManagerRegisterController() {
		
		boolean userInserted = Datainsert.insertManager(this.firstname, this.lastname, this.address, this.phonenumber, this.email, this.username,this.approved, this.password);
		if(userInserted) {
			//FacesContext.getCurrentInstance().addMessage("signInForm:loginButton", new FacesMessage(FacesMessage.SEVERITY_INFO,"User Successfuly Registered", null));
			//FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			//this.name = this.emailAddress = this.userID = this.password = "";
		  return ("successfully_registered.xhtml");
		}
		else
		  return ("unsuccess.xhtml");
	}
	public String validatemymanager() {
        boolean valid = LoginDAO.validatemymanager(username, password);
        if (valid == true) {

            return "Adminhome?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN, "Incorrect Username and Passowrd. Please enter correct username and Password",""));
            return "unsuccess";
        }
    }
	public String ManagerrequestController() {
        String valid = Datainsert.viewrequest();
        if (valid != null) {
System.out.println(valid);
            return "managerreq?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN, "Incorrect Username and Passowrd. Please enter correct username and Password",""));
            return "unsuccess";
        }
    }*/
}
