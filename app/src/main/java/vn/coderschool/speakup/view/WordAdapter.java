package vn.coderschool.speakup.view;

import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.coderschool.speakup.R;
import vn.coderschool.speakup.model.Word;

/**
 * Created by kenp on 28/03/2017.
 */

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.ViewHolder> {

    private List<Word> mWords;

    public WordAdapter(List<Word> words) {
        this.mWords = words;
    }

    @Override
    public WordAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_word, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WordAdapter.ViewHolder holder, int position) {
        final Word word = mWords.get(position);

        holder.tvWord.setText(word.getText());
        holder.tvIpa.setText(word.getIpa());
        holder.btPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try {
                    mediaPlayer.setDataSource(word.getAudioUrl());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mWords.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_word)
        TextView tvWord;

        @BindView(R.id.text_ipa)
        TextView tvIpa;

        @BindView(R.id.image_play)
        ImageView btPlay;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
