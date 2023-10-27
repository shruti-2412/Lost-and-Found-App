package com.shruti.lofo.ui.DashBoard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shruti.lofo.R;

import java.util.ArrayList;

public class RecyclerRecentLoFoAdapter extends RecyclerView.Adapter<RecyclerRecentLoFoAdapter.viewHolder> {

    Context context;
    ArrayList<DashBoardViewModel> arr_recent_lofo;
    RecyclerRecentLoFoAdapter(Context context, ArrayList<DashBoardViewModel> arr_recent_lofo){
        this.context=context;
        this.arr_recent_lofo = arr_recent_lofo;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.recent_lofo,parent,false);
        viewHolder viewHolder = new viewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.img.setImageResource(arr_recent_lofo.get(position).img);
        holder.name.setText(arr_recent_lofo.get(position).name);
        holder.description.setText(arr_recent_lofo.get(position).description);
        holder.posted_by.setText(arr_recent_lofo.get(position).posted_by);
    }

    @Override
    public int getItemCount() {
        return arr_recent_lofo.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        TextView name,description,posted_by;
        ImageView img;
        public viewHolder(View itemView){
            super(itemView);

            name = itemView.findViewById(R.id.item_name);
            description = itemView.findViewById(R.id.item_description);
            posted_by = itemView.findViewById(R.id.item_posted_by);
            img = itemView.findViewById(R.id.img_lofo_recent);
        }
    }
}
