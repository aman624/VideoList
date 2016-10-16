package com.aman.videolist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aman.videolist.R;
import com.aman.videolist.model.Video;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom adapter to display a list of video items
 * Each video item has an image, title and studio name
 */
public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.VideoViewHolder> {
    private Context mContext;
    private List<Video> mVideoList;

    /**
     * Constructor of the adapter
     * @param context Caller context
     */
    public VideoListAdapter(Context context) {
        mContext = context;
        mVideoList = new ArrayList<>();
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a View from the list item XML
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_video, parent, false);
        return new VideoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {
        // Video data at specified position
        Video video = mVideoList.get(position);

        // Set title and studio text
        holder.mTitleView.setText(video.getTitle());
        holder.mStudioView.setText(video.getStudio());

        // Image loading is a well known requirement in many Android apps
        // Need to consider asynchronous loading, recycling bitmaps, caching, cancellation etc
        // Hence, a well tested third party library (like Glide, Picasso, Volley, Fresco etc) should be used
        // Using Glide library here
        Glide.with(mContext)
                .load(video.getThumb())
                .placeholder(android.R.color.darker_gray)
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mVideoList.size();
    }

    /**
     * Set the list of items to be displayed
     * @param list The list of item associated with the adapter
     */
    public void setVideoList(List<Video> list) {
        mVideoList = list;
    }

    /**
     * Custom ViewHolder for holding Video items
     * This pattern makes sure that expensive findViewById calls are limited
     * This is mandatory for using a RecyclerView
     */
    public static class VideoViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTitleView;
        public TextView mStudioView;

        /**
         * Constructor of the ViewHolder
         * @param itemView View associated with the ViewHolder
         */
        public VideoViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.thumb_image_view);
            mTitleView = (TextView) itemView.findViewById(R.id.title_view);
            mStudioView = (TextView) itemView.findViewById(R.id.studio_view);
        }
    }
 }
