/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import dto.UserInterestsDTO;

/**
 *
 * @author EslamWaheed
 */
public interface UserInterestsDAOInt {

    UserInterestsDTO getUserInterests(int userID);

    boolean insertUserInterests(UserInterestsDTO userInterestsDTO);

    boolean updateUserInterests(UserInterestsDTO userInterestsDTO);

    boolean deleteUserInterests(int userID);
}
