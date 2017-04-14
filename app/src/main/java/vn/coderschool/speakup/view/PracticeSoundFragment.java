package vn.coderschool.speakup.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.coderschool.speakup.R;
import vn.coderschool.speakup.model.Sound;

/**
 * Created by kenp on 06/04/2017.
 */

public class PracticeSoundFragment extends Fragment {

    private List<Sound> mSounds;

    @BindView(R.id.recycler_sounds)
    RecyclerView recyclerView;

    public static PracticeSoundFragment getInstance(List<Sound> sounds) {
        PracticeSoundFragment fragment = new PracticeSoundFragment();
        fragment.mSounds = sounds;

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_practice_sound, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        SoundAdapter adapter = new SoundAdapter(mSounds, getSoundClickListener());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
    }

    public SoundAdapter.SoundClickListener getSoundClickListener() {
        return new SoundAdapter.SoundClickListener() {
            @Override
            public void onSoundClick(Sound sound) {
                Intent intent = new Intent(getActivity(), PracticeSoundActivity.class);
                intent.putExtra("sound", Parcels.wrap(sound));
                startActivity(intent);
            }
        };
    }
}
