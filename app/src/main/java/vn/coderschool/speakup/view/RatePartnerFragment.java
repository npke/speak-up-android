package vn.coderschool.speakup.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.coderschool.speakup.R;

/**
 * Created by kenp on 23/03/2017.
 */

public class RatePartnerFragment extends Fragment {

    public interface RatePartnerListener {
        void onRateSubmit(float rating);
    }

    private RatePartnerListener listener;

    public static RatePartnerFragment getInstance(RatePartnerListener rateListener) {
        RatePartnerFragment fragment = new RatePartnerFragment();
        fragment.listener = rateListener;

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rate_partner, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @OnClick(R.id.button_submit_rating)
    public void submitRating() {
        listener.onRateSubmit(0.5f);
    }
}
