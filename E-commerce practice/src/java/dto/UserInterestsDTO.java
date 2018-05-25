/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author EslamWaheed
 */
public class UserInterestsDTO {

    private Integer userID;
    private String interests;

    public UserInterestsDTO() {
    }

    public UserInterestsDTO(Integer userID, String interests) {
        this.userID = userID;
        this.interests = interests;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

}
