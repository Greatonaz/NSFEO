package com.avalon.backend.beans;

import com.google.appengine.api.datastore.Entity;

import org.joda.time.DateTime;

import java.util.Date;
import java.util.List;

/**
 * Created by Tonaz on 4/8/2015.
 */
public class GameUser {

    private String name;
    private String password;
    private DateTime DOB;
    private Location location;
    private List<InterestGroupAffinity> interestGroupAffinityList;
    private List<GameSession> gameSessionList;
    private List<GameUser> friendList;
    private String userProfilePictureURL;

    public GameUser(Entity entity){

        //this.setId(result.getKey().getId());
        this.setName((String)entity.getProperty("Name"));
        this.setDOB(DateTime.parse((String) entity.getProperty("DOB")));
        this.setLocation((Location) entity.getProperty("Location"));
       // taskBeans.add(taskBean);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DateTime getDOB() {
        return DOB;
    }

    public void setDOB(DateTime DOB) {
        this.DOB = DOB;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<InterestGroupAffinity> getInterestGroupAffinityList() {
        return interestGroupAffinityList;
    }

    public void setInterestGroupAffinityList(List<InterestGroupAffinity> interestGroupAffinityList) {
        this.interestGroupAffinityList = interestGroupAffinityList;
    }

    public List<GameSession> getGameSessionList() {
        return gameSessionList;
    }

    public void setGameSessionList(List<GameSession> gameSessionList) {
        this.gameSessionList = gameSessionList;
    }

    public List<GameUser> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<GameUser> friendList) {
        this.friendList = friendList;
    }

    public String getUserProfilePictureURL() {
        return userProfilePictureURL;
    }

    public void setUserProfilePictureURL(String userProfilePictureURL) {
        this.userProfilePictureURL = userProfilePictureURL;
    }
}
