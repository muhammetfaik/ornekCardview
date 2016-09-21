package com.example.muhammet.mainpagedemo.adapters;

/**
 * Created by MUHAMMET on 12.07.2016.
 */

//import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.muhammet.mainpagedemo.types.Note;
import com.example.muhammet.mainpagedemo.R;
import java.util.ArrayList;
import java.util.List;
//import com.squareup.picasso.Picasso;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.ViewHolder>  {

    OnItemClickListener mItemClickListener;
    private List<Note> mnoteList;

    public NoteListAdapter(List<Note> noteList) {
        this.mnoteList = noteList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_places, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

           final Note note = mnoteList.get(position);
           holder.placeName.setText(note.getId());
           holder.placePrice.setText(note.getPrice());
           holder.placegetUnivercityId.setText(note.getUniversity_id());

    }
    @Override
    public int getItemCount() {
       return mnoteList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public LinearLayout placeNameHolder;
        public TextView placeName;
        public TextView placePrice;
        public TextView placegetUnivercityId;

        public ViewHolder(View itemView) {
            super(itemView);

            placeName = (TextView) itemView.findViewById(R.id.placeName);
            placeNameHolder = (LinearLayout) itemView.findViewById(R.id.placeNameHolder);
            placePrice = (TextView)itemView.findViewById(R.id.placePrice);
            placegetUnivercityId = (TextView)itemView.findViewById(R.id.placeUnivercityId);

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
