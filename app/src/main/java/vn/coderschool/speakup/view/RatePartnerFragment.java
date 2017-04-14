package vn.coderschool.speakup.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import vn.coderschool.speakup.R;
import vn.coderschool.speakup.databinding.FragmentRatePartnerBinding;
import vn.coderschool.speakup.model.User;

/**
 * Created by kenp on 23/03/2017.
 */

public class RatePartnerFragment extends Fragment {

    public interface RatePartnerListener {
        void onRateSubmit(float rating);
    }

    private RatePartnerListener listener;
    private User partner;

    private FragmentRatePartnerBinding binding;

    @BindView(R.id.image_partner)
    ImageView ivPartnerProfilePhoto;

    @BindView(R.id.text_fullname)
    TextView tvFullName;

    public static RatePartnerFragment getInstance(RatePartnerListener rateListener, User partner) {
        RatePartnerFragment fragment = new RatePartnerFragment();
        fragment.listener = rateListener;
        fragment.partner = partner;

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

        binding = DataBindingUtil.bind(view);
        binding.setPartner(partner);
    }

    @OnClick(R.id.button_submit_rating)
    public void submitRating() {
        listener.onRateSubmit(0.5f);
    }
}
