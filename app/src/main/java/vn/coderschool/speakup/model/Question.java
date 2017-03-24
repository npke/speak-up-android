package vn.coderschool.speakup.model;

import com.google.firebase.database.PropertyName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by udcun on 3/23/2017.
 */

public class Question {
    @PropertyName("question_id")
    public int id;

    @PropertyName("question")
    public String question;

    @PropertyName("answer_id")
    public int answerId;

    //@PropertyName("anwers")
    public List<Answer> answers;

    // Required default constructor for Firebase object mapping
    public Question() {}

}
