package vn.coderschool.speakup.view;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.coderschool.speakup.R;
import vn.coderschool.speakup.model.MatchingResult;
import vn.coderschool.speakup.presenter.FindPartnerPresenter;

public class FindPartnerFragment extends Fragment implements FindPartnerView {

    interface FindPartnerListener {
        void onPartnerFound(MatchingResult matchingResult);

        void onPartnerNotFound();
    }

    private FindPartnerPresenter presenter;

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

        presenter = new FindPartnerPresenter();
        presenter.attachView(this);

        presenter.sendMatchingRequest();
    }

    @Override
    public void showFindingPartner() {
        layoutFindingPartner.setVisibility(View.VISIBLE);
    }

    @Override
    public void showPartnerAndConversationDetail(final MatchingResult matchingResult) {
        layoutFindingPartner.setVisibility(View.GONE);
        layoutConversationDetail.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.onPartnerFound(matchingResult);
            }
        }, 3000);
    }

    @Override
    public void showFindingError(Throwable t) {
        listener.onPartnerNotFound();
    }
}
