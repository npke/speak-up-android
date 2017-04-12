package vn.coderschool.speakup.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import vn.coderschool.speakup.R;
import vn.coderschool.speakup.model.Question;
import vn.coderschool.speakup.presenter.LevelTestPresenter;

/**
 * Created by udcun on 3/23/2017.
 */

public class LevelTestActivity extends AppCompatActivity implements LevelTestView {

    public static final String TAG = "LevelTestActivity";

    private LevelTestPresenter presenter;

    @BindView(R.id.activity_level_test_final_layout) View finalLayout;
    @BindView(R.id.toolbar_main) Toolbar toolbar;
    @BindView(R.id.activity_level_test_tv_question_number) TextView tvQuestionNumber;
    @BindView(R.id.activity_level_test_tv_question_content) TextView tvQuestionContent;
    @BindView(R.id.activity_level_test_rg_answers) RadioGroup rgAnswers;
    @BindView(R.id.activity_level_test_btn_next) Button btnNext;
    @BindView(R.id.activity_level_test_btn_previous) Button btnPrevious;
    @BindView(R.id.activity_level_test_btn_submit) Button btnSubmit;

    private ProgressDialog pdViewLoading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_test);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        presenter = new LevelTestPresenter();
        presenter.attachView(this);

        pdViewLoading = new ProgressDialog(this);
        pdViewLoading.setMessage("Loading.....");

        presenter.loadQuestions();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loadNextQuestion();
            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loadPreviousQuestion();
            }
        });

        rgAnswers.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId != -1) {
                    RadioButton selectedAnswer = (RadioButton) findViewById(checkedId);
                    presenter.setScoreForAnswer((Integer) selectedAnswer.getTag());
                } else {
                    presenter.setScoreForAnswer(0);
                }
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.submitTest();
            }
        });
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        final MenuItem menuItemUser = menu.findItem(R.id.menu_item_user);
        FrameLayout rootView = (FrameLayout) menuItemUser.getActionView();

        presenter.loadUserAvatar();

        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItemUser);
            }
        });
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_user:
                Toast.makeText(LevelTestActivity.this, "click", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showProgressIndicator() {
        pdViewLoading.show();
    }

    @Override
    public void hideProgressIndicator() {
        pdViewLoading.dismiss();
        finalLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showQuestion(Question question, String numberOfQuestion, int selectedAnswerId) {
        rgAnswers.clearCheck();
        tvQuestionNumber.setText("Question " + numberOfQuestion);
        tvQuestionContent.setText(question.content.toString());
        for (int i = 0; i < rgAnswers.getChildCount(); i++) {
            String answerContent = question.answers.get(i).content.toString();
            int answerId = question.answers.get(i).id;
            RadioButton radioButtonAnswer = (RadioButton) rgAnswers.getChildAt(i);
            radioButtonAnswer.setText(answerContent);
            radioButtonAnswer.setTag(answerId);
            if ( selectedAnswerId == answerId ) {
                radioButtonAnswer.setChecked(true);
            }
        }
    }

    @Override
    public void showUserLevel(String level) {
        Toast.makeText(LevelTestActivity.this, level, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void showUserAvatar(String url) {
        ImageView imageViewUser = (ImageView) findViewById(R.id.image_view_user);

        Glide.with(getContext()).load(url)
                .centerCrop()
                .bitmapTransform(new CropCircleTransformation(getContext()))
                .placeholder(R.drawable.ic_user_64x64)
                .into(imageViewUser);
    }
}
