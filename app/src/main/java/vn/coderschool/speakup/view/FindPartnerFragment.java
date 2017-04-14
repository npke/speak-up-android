package vn.coderschool.speakup.view;

import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.coderschool.speakup.R;
import vn.coderschool.speakup.databinding.FragmentFindPartnerBinding;
import vn.coderschool.speakup.model.MatchingResult;
import vn.coderschool.speakup.presenter.FindPartnerPresenter;

public class FindPartnerFragment extends Fragment implements FindPartnerView {

    interface FindPartnerListener {
        void onPartnerFound(MatchingResult matchingResult);

        void onPartnerNotFound();
    }

    private FindPartnerPresenter presenter;

    private FindPartnerListener listener;

    FragmentFindPartnerBinding binding;

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
        //binding = DataBindingUtil.inflate(inflater, R.layout.fragment_find_partner, container, false)
        return inflater.inflate(R.layout.fragment_find_partner, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        binding = DataBindingUtil.bind(view);

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
        binding.setPartner(matchingResult.getPartner());
        binding.setSelectedTopic(matchingResult.getSelectedTopic());

        layoutFindingPartner.setVisibility(View.GONE);
        layoutConversationDetail.setVisibility(View.VISIBLE);

        new AsyncTask<Integer, Integer, Void>() {

            @Override
            protected Void doInBackground(Integer... integers) {
                for (int i = integers[0]; i > 0; i--) {
                    publishProgress(i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                binding.textRemainPrepareTime.setText(String.valueOf(values[0]));

            }
        }.execute(matchingResult.getSelectedTopic().getPrepareTime());


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.onPartnerFound(matchingResult);
            }
        }, matchingResult.getSelectedTopic().getPrepareTime() * 100);
    }

    @Override
    public void showFindingError(Throwable t) {
        listener.onPartnerNotFound();
    }
}
