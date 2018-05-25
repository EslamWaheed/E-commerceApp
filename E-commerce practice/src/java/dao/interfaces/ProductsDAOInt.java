/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import dto.CategoryDTO;
import dto.ProductsDTO;
import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;

/**
 *
 * @author EslamWaheed
 */
public interface ProductsDAOInt {

    ProductsDTO getProducts(int id);
    
    Set<ProductsDTO> getAllProducts();
    
    boolean insertProducts(ProductsDTO productsDTO);

    boolean updateProducts(ProductsDTO productsDTO);

    boolean deleteProducts(int id);
    
    int getNumberOfProducts();
    
    HashMap<CategoryDTO, ArrayList<ProductsDTO>> getAllOfCatagoryProducts();
}
