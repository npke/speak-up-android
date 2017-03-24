package vn.coderschool.speakup.model;

import com.google.firebase.database.PropertyName;

/**
 * Created by udcun on 3/23/2017.
 */
public class Answer {
    @PropertyName("answer_id")
    public int id;

    @PropertyName("content")
    public String content;

    // Required default constructor for Firebase object mapping
    public Answer() {}
}
