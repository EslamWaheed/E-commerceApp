/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import dto.ShoppingCartDTO;

/**
 *
 * @author EslamWaheed
 */
public interface ShoppingCartDAOInt {

    ShoppingCartDTO getShoppingCart(int id);

    boolean insertShoppingCart(ShoppingCartDTO shoppingCartDTO);

    boolean updateShoppingCart(ShoppingCartDTO shoppingCartDTO);

    boolean deleteShoppingCart(ShoppingCartDTO shoppingCartDTO);
}
