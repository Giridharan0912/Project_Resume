package com.alphaverse.projectresume.activities.createresumes.createresumefragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alphaverse.projectresume.R;
import com.alphaverse.projectresume.model.Preview;

import java.util.List;

public class PreviewRVAdapter extends RecyclerView.Adapter<PreviewRVAdapter.ResumesViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private OnPreviewListener onPreviewListener;
    private List<Preview> previewList;

    public PreviewRVAdapter(Context context, List<Preview> previewList,OnPreviewListener listener) {
        this.context = context;
        this.previewList = previewList;
        this.layoutInflater=LayoutInflater.from(context);
        this.onPreviewListener=listener;
    }

    @NonNull
    @Override
    public ResumesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.resume_item,null);
        return new ResumesViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull ResumesViewHolder holder, int position) {
        Preview current=previewList.get(position);
        holder.setResume(current);

    }

    @Override
    public int getItemCount() {
        return previewList.size();
    }
    public interface OnPreviewListener{
        void onPreviewClicked(String preview);
    }


    public class ResumesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView resumeImageView;
        private TextView resumeTextView;
        private Context context;
        private String resumeClicked;
        public ResumesViewHolder(@NonNull View itemView,Context ctx) {
            super(itemView);
            resumeImageView=itemView.findViewById(R.id.resume_iv);
            resumeTextView=itemView.findViewById(R.id.resume_tv);
            this.context=ctx;
            resumeImageView.setOnClickListener(this);

        }
        private void setResume(Preview preview){
            this.resumeClicked=preview.getPreviewName();
            resumeTextView.setText(preview.getPreviewName());
            resumeImageView.setImageResource(preview.getPreViewImage(context));
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.resume_iv:
                    onPreviewListener.onPreviewClicked(resumeClicked);
            }
        }
    }
}
