package com.fanchen.imovie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fanchen.imovie.R;
import com.fanchen.imovie.activity.BangumiListActivity;
import com.fanchen.imovie.base.BaseAdapter;
import com.fanchen.imovie.entity.face.IVideo;
import com.fanchen.imovie.entity.face.IViewType;
import com.fanchen.imovie.picasso.PicassoWrap;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by fanchen on 2017/9/19.
 */
public class BangumiListAdapter extends BaseAdapter {

    private PicassoWrap picasso;

    public BangumiListAdapter(Context context, List<IViewType> mList, Picasso pic) {
        super(context, mList);
        picasso = new PicassoWrap(pic);
    }

    public BangumiListAdapter(Context context, Picasso pic) {
        super(context);
        picasso = new PicassoWrap(pic);
    }

    @Override
    public boolean hasFooterView() {
        return true;
    }

    @Override
    public RecyclerView.ViewHolder createViewHolder(View v, int viewType) {
        return viewType == IViewType.TYPE_NORMAL ? new VideoViewHolder(v) : new FooterViewHolder(v);
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder holder, List<IViewType> datas, int viewType, int position) {
        if (viewType != IViewType.TYPE_NORMAL) return;
        IVideo videoItem = (IVideo) datas.get(position);
        VideoViewHolder videoViewHolder = (VideoViewHolder) holder;
        videoViewHolder.titletTextView.setText(videoItem.getTitle());
        videoViewHolder.playTextView.setText(videoItem.getExtras());
        videoViewHolder.upTextView.setText(videoItem.getLast());
        if (videoItem.getDrawable() > 0) {
            videoViewHolder.rankImageView.setVisibility(View.VISIBLE);
            picasso.load(videoItem.getDrawable(), BangumiListActivity.class, videoViewHolder.rankImageView);
        }else{
            videoViewHolder.rankImageView.setVisibility(View.GONE);
        }
        videoViewHolder.danmakuTextView.setVisibility(View.VISIBLE);
        videoViewHolder.danmakuTextView.setText(videoItem.getDanmaku());
        picasso.loadHorizontal(videoItem.getCover(), BangumiListActivity.class, videoViewHolder.imageView);
    }

    @Override
    public int getLayout(int viewType) {
        return viewType == IViewType.TYPE_NORMAL ? R.layout.item_video_horizontal : R.layout.item_load_footer;
    }

    private static class VideoViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public ImageView rankImageView;
        public TextView titletTextView;
        public TextView playTextView;
        public TextView danmakuTextView;
        public TextView upTextView;

        public VideoViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_video_image);
            titletTextView = (TextView) itemView.findViewById(R.id.tv_video_text);
            playTextView = (TextView) itemView.findViewById(R.id.tv_video_play);
            danmakuTextView = (TextView) itemView.findViewById(R.id.tv_video_danmaku);
            upTextView = (TextView) itemView.findViewById(R.id.tv_video_up);
            rankImageView = (ImageView) itemView.findViewById(R.id.iv_video_rank);
        }

    }
}
