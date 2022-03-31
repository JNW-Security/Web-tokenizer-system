package edu.eci.cvds.managedbeans;

import edu.eci.cvds.entities.User;
import edu.eci.cvds.services.AnonimizacionServices;
import edu.eci.cvds.services.ServicesException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;
import java.util.logging.Level;

@SessionScoped
@ManagedBean(name="userBean")
public class UserBean extends BasePageBean implements Serializable {
    @Inject
    private AnonimizacionServices anonimizacionServices;
    private static final Logger logger = LoggerFactory.getLogger(UserBean.class);
    private String username;
    private String fullname;
    private String userpassword;
    private String mail;
    private String redirectURL="/faces/signin.xhtml";
    private Subject currentUser;
    private boolean rememberMe=false;
    private String selectedVersion;
    private User user = new User();

    public void signin(){
        UsernamePasswordToken token = new UsernamePasswordToken(getUsername(),new Sha256Hash(getUserpassword()).toHex());
        currentUser = SecurityUtils.getSubject();

        try {
            currentUser.login(token);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            Session session = currentUser.getSession();
            session.setAttribute("username",username);
            session.setAttribute("currentUser",currentUser);
            if(currentUser.hasRole("Administrator") ||currentUser.hasRole("Usuario") ){
                facesContext.getExternalContext().redirect("/faces/homeA.xhtml");
            } else if(currentUser.hasRole("Student") || currentUser.hasRole("Teacher") || currentUser.hasRole("Graduate") || currentUser.hasRole("Administrative")  ){
                facesContext.getExternalContext().redirect("/faces/homeB.xhtml");
            }

        } catch ( UnknownAccountException e ) {
            //username wasn't in the system, show them an error message?
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Sign in Error", "Incorrect Credentials"));
            logger.error(e.getMessage(),e);
        } catch ( IncorrectCredentialsException e ) {
            //password didn't match, try again?
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Sign in Error", "Incorrect Credentials"));
            logger.error(e.getMessage(),e);
        } catch ( LockedAccountException e ) {
            //account for that username is locked - can't login.  Show them a message?
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Sign in Error", "Sign in Error"));
            logger.error(e.getMessage(),e);
        } catch ( AuthenticationException e ) {
            //unexpected condition - error?
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Sign in Error", "Sign in Error"));
            logger.error(e.getMessage(),e);
        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Sign in Error", "Sign in Error"));
        }
    }

    public void signup(){
        try {
            this.user.setStatus(true);
            this.user.setUsertype(2);
            switch (selectedVersion){
                case "Version 1":
                    //ningun metodo de anonimizacion
                    anonimizacionServices.registerUser(user);
                    break;
                case "Version 2":
                    //uso de SHA-256
                    implementSHA();
                    anonimizacionServices.registerUserV2(user);
                    break;
                case "Version 3":
                    //SHA-256 + SALT
                    implementSHASALT();
                    anonimizacionServices.registerUserV3(user);
                    break;
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"User Added", username));
        } catch (ServicesException ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Add Error",ex.getMessage()));
        }
    }

    public void signOut() {
        SecurityUtils.getSubject().logout();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(redirectURL);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void implementSHA() {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
            String clave = toHexString(md.digest(user.getUserpassword().getBytes(StandardCharsets.UTF_8)));
            user.setUserpassword(clave);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    public void implementSHASALT() {
        byte[] salt = getSalt();
        String generatedPassword = user.getUserpassword();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] bytes = md.digest(user.getUserpassword().getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        user.setUserpassword(generatedPassword);
    }

    public static String toHexString(byte[] hash)
    {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);
        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));
        // Pad with leading zeros
        while (hexString.length() < 32)
        {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }

    private static byte[] getSalt()  {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getRedirectURL() {
        return redirectURL;
    }

    public void setRedirectURL(String redirectURL) {
        this.redirectURL = redirectURL;
    }

    public Subject getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Subject currentUser) {
        this.currentUser = currentUser;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getSelectedVersion() {
        return selectedVersion;
    }

    public void setSelectedVersion(String selectedVersion) {
        this.selectedVersion = selectedVersion;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

