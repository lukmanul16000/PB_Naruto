package com.example.pb_naruto.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pb_naruto.R;
import com.example.pb_naruto.model.AnimeList;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder> {
    private Context mContext;
    private ArrayList<AnimeList> mExampleList;

//    menambah intent
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

//    akhirtambah

    public AnimeAdapter(Context context, ArrayList<AnimeList> exampleList) {
        mContext = context;
        mExampleList = exampleList;
    }




    @Override
    public AnimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.anime_list, parent, false);
        return new AnimeViewHolder(v);
    }


    @Override
    public void onBindViewHolder(AnimeViewHolder holder, int position) {
        AnimeList currentItem = mExampleList.get(position);
        String imageUrl = currentItem.getMimage_url();
        String tilte = currentItem.getMtitle();
        int episode = currentItem.getMepisodes();

//        baru
        String type = currentItem.getMtype();
        String score = currentItem.getMscore();
        String start = currentItem.getMstart_date();
        String end = currentItem.getMend_date();

//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            Date start1 = format.parse(start);
//            String start_date = format.format(start1);
//
//            Date end1 = format.parse(start);
//            String end_date = format.format(end1);
//
//
////            start
//            holder.mstart.setText(start_date);
////            end
//            holder.mend.setText(end_date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }


        holder.mtype.setText(type);
        holder.mscore.setText(String.valueOf(score));
        holder.mstart.setText(start);
        holder.mend.setText(end);
        holder.mtitle.setText(tilte);
        holder.mepisode.setText("Episode: " + episode);
        Picasso.get().load(imageUrl).fit().centerInside().into(holder.mImageView);
    }


    @Override
    public int getItemCount() {
        return mExampleList.size();
    }


    public class AnimeViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mtitle;
        public TextView mepisode;
        public TextView mtype;
        public TextView mscore;
        public TextView mstart;
        public TextView mend;
        public AnimeViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
            mtitle = itemView.findViewById(R.id.text_view_title);
            mepisode = itemView.findViewById(R.id.text_view_episode);
            mtype = itemView.findViewById(R.id.lanime_type);
            mscore = itemView.findViewById(R.id.anime_score);
            mstart = itemView.findViewById(R.id.lstart_date);
            mend = itemView.findViewById(R.id.lend_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
        }
    }