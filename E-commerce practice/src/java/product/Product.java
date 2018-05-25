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
public class Product {
    String prodName;
    String desc;
    int price;
    int numberItem;
    int id;

    public Product(String prodName, String desc, int price, int numberItem, int id) {
        this.prodName = prodName;
        this.desc = desc;
        this.price = price;
        this.numberItem = numberItem;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Product{" + "prodName=" + prodName + ", desc=" + desc + ", price=" + price + ", numberItem=" + numberItem + ", id=" + id + '}';
    }

    public Product(String prodName, String desc, int price, int numberItem) {
        this.prodName = prodName;
        this.desc = desc;
        this.price = price;
        this.numberItem = numberItem;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdName() {
        return prodName;
    }

    public String getDesc() {
        return desc;
    }

    public int getPrice() {
        return price;
    }

    public int getNumberItem() {
        return numberItem;
    }

    public int getId() {
        return id;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setNumberItem(int numberItem) {
        this.numberItem = numberItem;
    }
    
}
