/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.awt.Image;
import java.sql.Date;
import java.io.File;
import java.io.InputStream;
/**
 *
 * @author EslamWaheed
 */
public class UserDTO {

    private Integer id;
    private String name;
    private Date bd;
    private Integer password;
    private String job;
    private String email;
    private String address;
    private Integer credit_limit;
    private InputStream image;
    private Integer privalege;
    
    public UserDTO() {
    }

    public UserDTO(Integer id, String name, Date bd, Integer password, String job, String email, String address, Integer credit_limit, InputStream image) {
        this.id = id;
        this.name = name;
        this.bd = bd;
        this.password = password;
        this.job = job;
        this.email = email;
        this.address = address;
        this.credit_limit = credit_limit;
        this.image = image;
    }

    public UserDTO(String name, Date bd, Integer password, String job, String email, String address, Integer credit_limit, InputStream image) {
        this.name = name;
        this.bd = bd;
        this.password = password;
        this.job = job;
        this.email = email;
        this.address = address;
        this.credit_limit = credit_limit;
        this.image = image;
    }

    public UserDTO(Integer id, String name, Date bd, Integer password, String job, String email, String address, Integer credit_limit) {
        this.id = id;
        this.name = name;
        this.bd = bd;
        this.password = password;
        this.job = job;
        this.email = email;
        this.address = address;
        this.credit_limit = credit_limit;
    }

    public UserDTO(String name, Date bd, Integer password, String job, String email, String address, Integer credit_limit) {
        this.name = name;
        this.bd = bd;
        this.password = password;
        this.job = job;
        this.email = email;
        this.address = address;
        this.credit_limit = credit_limit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBd() {
        return bd;
    }

    public void setBd(Date bd) {
        this.bd = bd;
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
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

    public Integer getCredit_limit() {
        return credit_limit;
    }

    public void setCredit_limit(Integer credit_limit) {
        this.credit_limit = credit_limit;
    }

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }

    public Integer getPrivalege() {
        return privalege;
    }

    public void setPrivalege(Integer privalege) {
        this.privalege = privalege;
    }
}
