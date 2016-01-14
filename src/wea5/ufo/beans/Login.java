package wea5.ufo.beans;

import java.io.Serializable;
import java.util.Objects;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
 
@ManagedBean
@SessionScoped
public class Login implements Serializable {
	private static final long serialVersionUID = 5115452756983982147L;
	private static Logger logger = Logger.getLogger(Login.class.getName());
	
	private String pwd;
    private String msg;
    private String user;
 
    public String getPwd() {
        return pwd;
    }
 
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
 
    public String getMsg() {
        return msg;
    }
 
    public void setMsg(String msg) {
        this.msg = msg;
    }
 
    public String getUser() {
        return user;
    }
 
    public void setUser(String user) {
        this.user = user;
    }
 
    //validate login
    public String validateUsernamePassword() {
        logger.info("try to login with credentials user: " + user + " and pwd: " +  pwd);
    	if(Objects.equals(user, "test") && Objects.equals(pwd, "test")){
            HttpSession session = SessionBean.getSession();
            session.setAttribute("username", user);
            logger.info("Login succeeded");
            return "admin";
        } else {
        	logger.info("wrong credentials");
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Passoword",
                            "Please enter correct username and Password"));
            return "login";
        }
    }
 
    //logout event, invalidate session
    public String logout() {
        HttpSession session = SessionBean.getSession();
        session.invalidate();
        return "login";
    }
}