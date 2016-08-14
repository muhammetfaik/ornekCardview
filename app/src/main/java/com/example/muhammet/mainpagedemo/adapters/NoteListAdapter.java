package com.example.muhammet.mainpagedemo.adapters;

/**
 * Created by MUHAMMET on 12.07.2016.
 */

import android.content.Context;
//import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.muhammet.mainpagedemo.helpers.HttpGetAsyncTask;
import com.example.muhammet.mainpagedemo.types.DataListener;
import com.example.muhammet.mainpagedemo.types.GeneralResponse;
import com.example.muhammet.mainpagedemo.types.Methods;
import com.example.muhammet.mainpagedemo.types.Note;
import com.example.muhammet.mainpagedemo.R;
import com.example.muhammet.mainpagedemo.types.NoteData;
import com.example.muhammet.mainpagedemo.types.OnTaskCompleted;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
//import com.squareup.picasso.Picasso;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.ViewHolder>  {

    Context mContext;
    OnItemClickListener mItemClickListener;


    public NoteListAdapter(Context context) {
        this.mContext = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_places, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
       try {
           final Note note = new NoteData().fooNote().get(position);
           holder.placeName.setText(note.id);
       }
       catch (Exception e) {

           Log.e("hata:  " + e.toString(), e.toString());
       }
        //Picasso.with(mContext).load(place.getImageResourceId(mContext)).into(holder.placeImage);

        //Bitmap photo = BitmapFactory.decodeResource(mContext.getResources(), place.getImageResourceId(mContext));

      /* Palette.generateAsync(photo, new Palette.PaletteAsyncListener() {
            public void onGenerated(Palette palette) {
                int mutedLight = palette.getMutedColor(mContext.getResources().getColor(android.R.color.black));
                holder.placeNameHolder.setBackgroundColor(mutedLight);
            }
        })*/;
    }
    @Override
    public int getItemCount() {

            final int[] y = new int[1];
            int x;

            NoteData.httpclient(new DataListener() {
                @Override
                public void onDataFetched(ArrayList<Note> noteList) {
                    y[0] = noteList.size();

                }
            });

            x = y[0];
            return x;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public LinearLayout placeHolder;
        public LinearLayout placeNameHolder;
        public TextView placeName;
        public ImageView placeImage;

        public ViewHolder(View itemView) {
            super(itemView);
            placeHolder = (LinearLayout) itemView.findViewById(R.id.mainHolder);
            placeName = (TextView) itemView.findViewById(R.id.placeName);
            placeNameHolder = (LinearLayout) itemView.findViewById(R.id.placeNameHolder);
            placeImage = (ImageView) itemView.findViewById(R.id.placeImage);
            placeHolder.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(itemView, getPosition());
            }
        }

    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }


}
