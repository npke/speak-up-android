package vn.coderschool.speakup.presenter;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import vn.coderschool.speakup.view.LevelTestView;

/**
 * Created by udcun on 3/23/2017.
 */

public class LevelTestPresenter implements Presenter<LevelTestView>  {

    public static final String TAG = "LevelTestPresenter";

    private LevelTestView levelTestView;

    private FirebaseDatabase database;

    public LevelTestPresenter() {
        this.database = FirebaseDatabase.getInstance();
    }

    @Override
    public void attachView(LevelTestView view) {
        this.levelTestView = view;
    }

    @Override
    public void detachView() {
        this.levelTestView = null;
    }
}
