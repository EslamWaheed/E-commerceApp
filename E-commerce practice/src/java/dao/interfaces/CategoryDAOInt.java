/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import dto.CategoryDTO;
import java.util.Set;

/**
 *
 * @author EslamWaheed
 */
public interface CategoryDAOInt {
    
    CategoryDTO getCategory(int id);

    boolean insertCategory(CategoryDTO categoryDTO);

    boolean updateCategory(CategoryDTO categoryDTO);

    boolean deleteCategory(int id);
    
    int getNumberOfCategory();
    
    Set<CategoryDTO> getAllCategory();
}
