package wea5.ufo.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import wea5.ufo.datalayer.LoginServiceProxy;
 
@ManagedBean(name = "authenticationBean")
@SessionScoped
public class AuthenticationBean implements Serializable {
	private static final long serialVersionUID = 5115452756983982147L;

	private String password;
    private String user;
    private boolean isAuthenticated;
 
	@ManagedProperty("#{loginServiceProxy}")
	private LoginServiceProxy serviceProxy;

	protected LoginServiceProxy getServiceProxy() {
		return serviceProxy;
	}
	
	public void setServiceProxy(LoginServiceProxy serviceProxy) {
		this.serviceProxy = serviceProxy;
	}
	
	public boolean getIsAuthenticated(){
		return isAuthenticated;
	}
    
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);
    }

    public String getUser() {
        return user;
    }
 
    public void setUser(String user) {
        this.user = user;
    }
 
    public void login() {
    	if(serviceProxy.isUserValid(user, password)){
    		isAuthenticated = true;
        } else {
        	user = null;
        	password = null;
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Passoword",
                            "Please enter correct username and Password"));
        }
    }
      
    public void logout() {
    	isAuthenticated = false;
    	user = null;
    	password = null;
    }
}