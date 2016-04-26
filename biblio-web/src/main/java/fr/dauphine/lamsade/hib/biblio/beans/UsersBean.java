/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.dauphine.lamsade.hib.biblio.beans;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import fr.dauphine.lamsade.hib.biblio.modele.Utilisateur;
import fr.dauphine.lamsade.hib.biblio.service.inter.UtilisateurServiceRemote;

/**
 *
 * @author Mohamed Youssef Errayhani
 */
@ManagedBean(name = "usersBean")
@SessionScoped
public class UsersBean {

    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String passwordV;
    private String telephone;
    private String adresse;
    private boolean isAdmin;
    private String imagePath;
    
    private Part imageFile = null;
    
    @EJB
    private UtilisateurServiceRemote userService;
    
    public static final String USER_SESSION_KEY = "user";
    /**
     * Creates a new instance of UsersBean
     */
    public UsersBean() {
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the passwordV
     */
    public String getPasswordV() {
        return passwordV;
    }

    /**
     * @param passwordV the passwordV to set
     */
    public void setPasswordV(String passwordV) {
        this.passwordV = passwordV;
    }

    /**
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone the telephone to set
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * @return the adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * @param adresse the adresse to set
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
    
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Part getImageFile() {
		return imageFile;
	}

	public void setImageFile(Part imageFile) {
		this.imageFile = imageFile;
	}

	public String validateUser() {   
        FacesContext context = FacesContext.getCurrentInstance();
        Utilisateur user = userService.getUserByUserName(email);
        if (user != null) {
            if (!user.getMotDePasse().equals(password)) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                           "Login Failed!",
                                           "The password specified is not correct.");
                context.addMessage(null, message);
                return null;
            }
            
            context.getExternalContext().getSessionMap().put(USER_SESSION_KEY, user);
            return "app-main";
        } else {           
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Login Failed!",
                    "Username '"
                    + email
                    +
                    "' does not exist.");
            context.addMessage(null, message);
            return null;
        }
    }
   
      public String createUser() {
        FacesContext context = FacesContext.getCurrentInstance();
          Utilisateur wuser = userService.getUserByUserName(email);
        if (wuser == null) {
            if (!password.equals(passwordV)) {
                FacesMessage message = new FacesMessage("The specified passwords do not match.  Please try again");
                context.addMessage(null, message);
                return null;
            }
            if (imageFile != null) {

                try {
                    saveImage();
                } catch (IOException ex) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error uploqding image!",
                            "Unexpected error when uploqding the image.  Please contact the system Administrator");
                    context.addMessage(null, message);
                    Logger.getAnonymousLogger().log(Level.SEVERE,
                            "Unable to upload the image",
                            ex);
                }
            }

            wuser = new Utilisateur();
            		wuser.setAdresse(adresse);
            		wuser.setMail(email);
            		wuser.setMotDePasse(password);
            		wuser.setNom(nom);
            		wuser.setPrenom(prenom);
            		wuser.setTelephone(telephone);
            		wuser.setPhotoPath(imagePath);
            try {
              userService.add(wuser);
                return "login";
            } catch (Exception e) {               
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                                        "Error creating user!",
                                                        "Unexpected error when creating your account.  Please contact the system Administrator");
                context.addMessage(null, message);
                Logger.getAnonymousLogger().log(Level.SEVERE,
                                                "Unable to create new user",
                                                e);
                return null;
            }
        } else {           
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                                    "Username '"
                                                      + email 
                                                      + "' already exists!  ",
                                                    "Please choose a different username.");
            context.addMessage(null, message);
            return null;
        }        
    }
     
      public String updateUser(){
    	  return null;
      }
  
    
     public String logout() {
        HttpSession session = (HttpSession)
             FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "login";      
    }
     public void validateFile(FacesContext ctx,
             UIComponent comp,
             Object value) {
         List<FacesMessage> msgs = new ArrayList<FacesMessage>();
         Part file = (Part) value;
         if (file.getSize() > (long) 1048576) {
             msgs.add(new FacesMessage("file too big"));
         }
         if (!"image/jpeg".equals(file.getContentType())) {
             msgs.add(new FacesMessage("not a  jpeg image file"));
         }
         if (!msgs.isEmpty()) {
             throw new ValidatorException(msgs);
         }
     }

     
     private static String getFilename(Part part) {
         for (String cd : part.getHeader("content-disposition").split(";")) {
             if (cd.trim().startsWith("filename")) {
                 String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                 return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.  
             }
         }
         return null;
     }

     private void saveImage() throws IOException {
         String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
         UUID id = UUID.randomUUID();
         imagePath = "XX" + id + "XX" + getFilename(imageFile);
         String filePath = path.concat("resources\\users\\");
         Image image = ImageIO.read(imageFile.getInputStream());
         BufferedImage imgBuffredThumb = new BufferedImage(150, 150, BufferedImage.TYPE_INT_RGB);
         BufferedImage imgBuffredReal = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
         imgBuffredReal.createGraphics().drawImage(image, 0, 0, null);
         imgBuffredThumb.createGraphics().drawImage(image.getScaledInstance(150, 150, Image.SCALE_SMOOTH), 0, 0, null);
         ImageIO.write(imgBuffredThumb, "jpg", new File(filePath + "\\thumbnail\\" + imagePath));
         ImageIO.write(imgBuffredReal, "jpg", new File(filePath + imagePath));
     }


}
