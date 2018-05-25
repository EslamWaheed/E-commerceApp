/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import dto.UserDTO;
import java.util.Set;

/**
 *
 * @author EslamWaheed
 */
public interface UserDAOInt {

    UserDTO getUser(int id);
    
    Set<UserDTO> getAllUser();

    boolean insertUser(UserDTO userDTO);

    boolean updateUser(UserDTO userDTO);

    boolean deleteUser(int id);
    
    int getNumberOfUser();
    
    int getMaxID();
    
    UserDTO getUserByEmailAndPassword(String email, int password);
    boolean updatePrivUser(UserDTO userdto);
    
}
