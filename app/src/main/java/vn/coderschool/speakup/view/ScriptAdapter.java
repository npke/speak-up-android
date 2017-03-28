package vn.coderschool.speakup.view;

import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.coderschool.speakup.R;
import vn.coderschool.speakup.model.Script;

/**
 * Created by kenp on 29/03/2017.
 */

public class ScriptAdapter extends RecyclerView.Adapter<ScriptAdapter.ViewHolder> {


    private static final int SCRIPT_LEFT = 0;
    private static final int SCRIPT_RIGHT = 1;

    private List<Script> mScripts;

    public ScriptAdapter(List<Script> scripts) {
        this.mScripts = scripts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        if (viewType == SCRIPT_LEFT)
            view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_script, parent, false);
        else view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_script, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Script script = mScripts.get(position);

        if (getItemViewType(position) == SCRIPT_RIGHT)
            holder.setGravity(Gravity.RIGHT);

        holder.tvOrigin.setText(script.getOrigin());
        holder.tvTranslated.setText(script.getTranslated());
    }

    @Override
    public int getItemViewType(int position) {
        return mScripts.get(position).getRole();
    }

    @Override
    public int getItemCount() {
        return mScripts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_origin)
        public TextView tvOrigin;

        @BindView(R.id.text_translated)
        public TextView tvTranslated;

        @BindView(R.id.layout_script)
        public LinearLayout layoutScript;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        public void setGravity(int gravity) {
            layoutScript.setGravity(gravity);
            tvOrigin.setGravity(gravity);
            tvTranslated.setGravity(gravity);
        }
    }
}
