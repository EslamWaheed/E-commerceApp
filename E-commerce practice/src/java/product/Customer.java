/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product;

/**
 *
 * @author Aya
 */
public class Customer {
    private int id;
    private String name;
    private String bd;
    private String password;
    private String job;
    private String email;
    private String address;
    private int creditlimit;
    
    public Customer(int id,String name,String bd,String password,String job,String email,String address,int creditlimit){
        this.id = id;
        this.name = name;
        this.bd = bd;
        this.password = password;
        this.job = job;
        this.email = email;
        this.address = address;
        this.creditlimit = creditlimit;
    }

    public Customer(String name, String bd, String password, String job, String email, String address, int creditlimit) {
        this.name = name;
        this.bd = bd;
        this.password = password;
        this.job = job;
        this.email = email;
        this.address = address;
        this.creditlimit = creditlimit;
    }
    

    public int getId() {
        return id;
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

}

    

