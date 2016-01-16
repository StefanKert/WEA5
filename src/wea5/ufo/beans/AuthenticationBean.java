package wea5.ufo.beans;

import java.io.Serializable;
import java.util.Objects;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
 
@ManagedBean(name = "authenticationBean")
@SessionScoped
public class AuthenticationBean implements Serializable {
	private static final long serialVersionUID = 5115452756983982147L;
	private static Logger logger = Logger.getLogger(AuthenticationBean.class.getName());
	
	private String password;
    private String user;
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }

 
    public String getUser() {
        return user;
    }
 
    public void setUser(String user) {
        this.user = user;
    }
 
    //validate login
    public void login() {
        logger.info("try to login with credentials user: " + user + " and pwd: " +  password);
        
    	if(Objects.equals(user, "test") && Objects.equals(password, "test")){
            HttpSession session = SessionBean.getSession();
            session.setAttribute("username", user);
 
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Passoword",
                            "Please enter correct username and Password"));
        }
    }
      
    public void logout() {
        HttpSession session = SessionBean.getSession();
        session.invalidate();
    }
}