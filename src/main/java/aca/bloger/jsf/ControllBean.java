package aca.bloger.jsf;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PersistenceException;

import org.primefaces.PrimeFaces;

import aca.bloger.domen.User;
import aca.bloger.service.AppException;
import aca.bloger.service.ErrorMessages;
import aca.bloger.service.UserService;

@Named
@SessionScoped
public class ControllBean implements Serializable{

	//default serial Id
	private static final long serialVersionUID = 1L;

	@Inject
	UserService userService;
	
	private User user = new User();
	
	public ControllBean() {
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	//kreira user-a
	public void createUser() {
		
		try {
			userService.createUser(user);
			
			user = new User();
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacija uspela", "Uspesno kreiran user");
	         
	        PrimeFaces.current().dialog().showMessageDynamic(message);	  
	        
	    }catch (PersistenceException pe) {
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error code: " + 
			                                         ErrorMessages.db_problem.getCode(), ErrorMessages.db_problem.getMessage());
	        PrimeFaces.current().dialog().showMessageDynamic(message);
			
		}catch (AppException appEx) {
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error code: " + appEx.getError().getCode(), appEx.getError().getMessage());
	         
	        PrimeFaces.current().dialog().showMessageDynamic(message);
			    
		}
	}
	
	public String backToHomePage() {
		
		return "homePage?faces-redirect=true";
	}
	
	//dovlaci sve user-e
	public List<User> listOfUsers(){
		
		return userService.getAllUsers();
	}
	
}
