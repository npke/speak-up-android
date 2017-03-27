package vn.coderschool.speakup.presenter;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import vn.coderschool.speakup.model.Question;
import vn.coderschool.speakup.model.User;
import vn.coderschool.speakup.view.LevelTestView;

/**
 * Created by udcun on 3/23/2017.
 */

public class LevelTestPresenter implements Presenter<LevelTestView>  {

    public static final String TAG = "LevelTestPresenter";

    private LevelTestView levelTestView;

    private DatabaseReference mDatabase;

    private List<Question> questions;
    private int currentQuestion;
    private int[] testResults;

    private SharedPreferences sharedPreferences;

    public LevelTestPresenter() {
        this.mDatabase = FirebaseDatabase.getInstance().getReference();
        this.questions = new ArrayList<>();
        this.currentQuestion = 0;
    }

    @Override
    public void attachView(LevelTestView view) {
        this.levelTestView = view;
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());
    }

    @Override
    public void detachView() {
        this.levelTestView = null;
    }

    public void loadQuestions() {
        levelTestView.showProgressIndicator();
        DatabaseReference reference = mDatabase.child("questions");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot questionSnapshot : dataSnapshot.getChildren()) {
                    Question question = questionSnapshot.getValue(Question.class);
                    Log.d(TAG, question.content.toString());
                    questions.add(question);
                }
                testResults = new int[questions.size()];
                levelTestView.hideProgressIndicator();
                levelTestView.showQuestion(questions.get(currentQuestion));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                levelTestView.hideProgressIndicator();
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    public void loadNextQuestion(int answerId) {
        setScore(answerId);
        if (currentQuestion == questions.size() - 1) {
            currentQuestion = 0;
        } else {
            currentQuestion++;
        }
        levelTestView.showQuestion(questions.get(currentQuestion));
    }

    public void loadPreviousQuestion(int answerId) {
        setScore(answerId);
        if (currentQuestion == 0) {
            currentQuestion = questions.size() - 1;
        } else {
            currentQuestion--;
        }
        levelTestView.showQuestion(questions.get(currentQuestion));
    }

    public void setScore(int answerId) {
        if (questions.get(currentQuestion).answerId == answerId) {
            testResults[currentQuestion] = 1;
        } else {
            testResults[currentQuestion] = 0;
        }
    }

    public String rateLevelForUser() {
        String userLevel;
        int score = 0;
        int numberOfQuestion = testResults.length;
        for (int i = 0; i < numberOfQuestion; i++) {
            if (testResults[i] == 1) {
                score++;
            }
        }
        // Set user level by score
        // score <= 1/3 number of question : beginner
        // 1/3 < score <= 2/3 number of question : intermediate
        // score > 2/3 number of question : advanced
        if ( score <= numberOfQuestion / 3) {
            userLevel = "beginner";
        } else if ( score <= numberOfQuestion / 3 * 2) {
            userLevel = "intermediate";
        } else {
            userLevel = "advanced";
        }

        return userLevel;
    }

    public void submitTest() {
        levelTestView.showUserLevel(rateLevelForUser());

        mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            //System.out.println(user.getUid());
            mDatabase.child("users").child(user.getUid()).child("level").setValue(rateLevelForUser());
        }
        // Reset array result to 0
        Arrays.fill(testResults, 0);
    }

    public void loadUserAvatar() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user!= null) {
            DatabaseReference reference = mDatabase.child("users").child(user.getUid());
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    User currentUser = (User) dataSnapshot.getValue(User.class);
                    if (currentUser.profilePhotoUrl != null) {
                        levelTestView.showUserAvatar(currentUser.profilePhotoUrl.toString());
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    public void loadUserInfor() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user!= null) {
            DatabaseReference reference = mDatabase.child("users").child(user.getUid());
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    User currentUser = (User) dataSnapshot.getValue(User.class);
                    System.out.println(currentUser.profilePhotoUrl.toString());
                    levelTestView.showUserAvatar(currentUser.profilePhotoUrl.toString());
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }
}
