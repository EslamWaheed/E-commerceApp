/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 
package util;

import java.io.File;
import java.io.Serializable;
/**
 *
 * @author Hazem
 */
public class User implements Serializable {
    private int id;
    private String name;
    private String bd;
    private String password;
    private String job;
    private String email;
    private String address;
    private int creditlimit;
    private File image; 
    
    public User(String name,String job,String email,String address){
        this.name = name;
        this.job = job;
        this.email = email;
        this.address = address;
    }
    public User(int id,String name,String bd,String password,String job,String email,String address,int creditlimit,File image){
        this.id = id;
        this.name = name;
        this.bd = bd;
        this.password = password;
        this.job = job;
        this.email = email;
        this.address = address;
        this.creditlimit = creditlimit;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBd() {
        return bd;
    }

    public void setBd(String bd) {
        this.bd = bd;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCreditlimit() {
        return creditlimit;
    }

    public void setCreditlimit(int creditlimit) {
        this.creditlimit = creditlimit;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }
}
