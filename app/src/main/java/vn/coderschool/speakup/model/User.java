package vn.coderschool.speakup.model;

import com.google.firebase.database.PropertyName;

/**
 * Created by udcun on 3/22/2017.
 */

public class User {
    public String firstName;
    public String lastName;
    public String fullName;
    public String id;
    public String level;

    @PropertyName("profilePhotoUrl")
    public String profilePhotoUrl;

    public String status;

    public User() {}
}
