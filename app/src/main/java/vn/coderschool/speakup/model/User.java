package vn.coderschool.speakup.model;

import java.util.List;

/**
 * Created by udcun on 3/22/2017.
 */

public class User {
    private String id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String fullName;
    private String profilePhotoUrl;
    private List<String> interestedTopics;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public List<String> getInterestedTopics() {
        return interestedTopics;
    }

    public void setInterestedTopics(List<String> interestedTopics) {
        this.interestedTopics = interestedTopics;
    }
}
