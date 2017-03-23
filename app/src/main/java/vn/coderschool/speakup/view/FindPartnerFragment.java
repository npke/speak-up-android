package vn.coderschool.speakup.view;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.coderschool.speakup.R;
import vn.coderschool.speakup.model.MatchingResult;
import vn.coderschool.speakup.util.SpeakUpApi;

public class FindPartnerFragment extends Fragment {

    interface FindPartnerListener {
        void onStartConversation(MatchingResult matchingResult);
    }

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private FindPartnerListener listener;

    @BindView(R.id.layout_finding_partner)
    LinearLayout layoutFindingPartner;

    @BindView(R.id.layout_conversation_detail)
    LinearLayout layoutConversationDetail;

    public static FindPartnerFragment getInstance(FindPartnerListener listener) {
        FindPartnerFragment fragment = new FindPartnerFragment();
        fragment.listener = listener;

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_find_partner, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        layoutFindingPartner.setVisibility(View.VISIBLE);

        mAuth.getCurrentUser().getUid();

        SpeakUpApi.Creator.getService().findPartner(mAuth.getCurrentUser().getUid()).enqueue(new Callback<MatchingResult>() {
            @Override
            public void onResponse(Call<MatchingResult> call, Response<MatchingResult> response) {
                showConversationDetail(response.body());
            }

            @Override
            public void onFailure(Call<MatchingResult> call, Throwable t) {
                Toast.makeText(getActivity(), "Partner not found! :'(", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showConversationDetail(final MatchingResult matchingResult) {
        layoutFindingPartner.setVisibility(View.GONE);
        layoutConversationDetail.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.onStartConversation(matchingResult);
            }
        }, 3000);
    }

}
