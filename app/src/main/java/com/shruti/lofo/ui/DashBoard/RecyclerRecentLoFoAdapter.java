package com.shruti.lofo.ui.DashBoard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shruti.lofo.R;

import java.util.ArrayList;

public class RecyclerRecentLoFoAdapter extends RecyclerView.Adapter<RecyclerRecentLoFoAdapter.viewHolder> {

    Context context;
    ArrayList<DashBoardViewModel> arr_recent_lofo;
    RecyclerRecentLoFoAdapter(Context context,ArrayList<DashBoardViewModel> arr_recent_lofo){
//        this.context=context;
        this.arr_recent_lofo = arr_recent_lofo;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recent_lofo,parent,false);
        viewHolder viewholder = new viewHolder(v);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        DashBoardViewModel recentItems = arr_recent_lofo.get(position);

        Glide.with(context)
                .load(recentItems.getImageURI())
                .error(R.drawable.sample_img)
                .into(holder.imageURI);

        holder.category.setText(recentItems.getCategory());
        holder.description.setText(recentItems.getDescription());

        // Get the collection name from the view model
        String collectionName = recentItems.getCollectionName();

        // Determine the tag based on the collection name
        String tag;
        if ("lostItems".equals(collectionName)) {
            tag = "lost";
            holder.ownerName.setText(recentItems.getOwnerName());
        } else if ("foundItems".equals(collectionName)) {
            tag = "found";
            holder.ownerName.setText(recentItems.getFinderName());
        } else {
            tag = ""; // You can set a default value or handle other cases as needed
        }
        holder.ownerName.setText(recentItems.getDateLost());


        holder.tag.setText(recentItems.getTag());
        String determine_tag = recentItems.getTag();
        if (determine_tag.equalsIgnoreCase("Lost")) {
            holder.tag.setTextColor(context.getResources().getColor(android.R.color.holo_red_dark));

        }
        else {
            holder.tag.setTextColor(context.getResources().getColor(android.R.color.holo_green_light)); // Set the text color to black or any other color you prefer
        }
    }

    @Override
    public int getItemCount() {
        return arr_recent_lofo.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        TextView category,description,ownerName,tag;
        ImageView imageURI;
        public viewHolder(View itemView){
            super(itemView);

            category = itemView.findViewById(R.id.item_category);
            description = itemView.findViewById(R.id.item_description);
            ownerName = itemView.findViewById(R.id.item_ownerName);
            imageURI = itemView.findViewById(R.id.img_lofo_recent);
            tag = itemView.findViewById(R.id.tag);
        }
    }
}