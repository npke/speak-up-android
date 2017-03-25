package vn.coderschool.speakup.presenter;

import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.coderschool.speakup.model.MatchingResult;
import vn.coderschool.speakup.util.SpeakUpApi;
import vn.coderschool.speakup.view.FindPartnerView;

/**
 * Created by kenp on 25/03/2017.
 */

public class FindPartnerPresenter implements Presenter<FindPartnerView> {

    private FindPartnerView view;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    public void attachView(FindPartnerView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    public void sendMatchingRequest() {
        String userId = mAuth.getCurrentUser().getUid();

        SpeakUpApi.Creator.getService().findPartner(userId).enqueue(new Callback<MatchingResult>() {
            @Override
            public void onResponse(Call<MatchingResult> call, Response<MatchingResult> response) {
                view.showPartnerAndConversationDetail(response.body());
            }

            @Override
            public void onFailure(Call<MatchingResult> call, Throwable t) {
                view.showFindingError(t);
            }
        });
    }
}
