package com.shruti.lofo.ui.Lost;

import android.content.Context;
import com.bumptech.glide.Glide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.shruti.lofo.R;

public class LostItemsAdapter extends FirestoreRecyclerAdapter<LostItems, LostItemsAdapter.ItemViewHolder> {

    Context context;
    public LostItemsAdapter(@NonNull FirestoreRecyclerOptions<LostItems> options, Context context ) {
        super(options);
        this.context=context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lost_item_card, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ItemViewHolder holder, int position, @NonNull LostItems item) {
        if (item.getImageURI() != null && !item.getImageURI().isEmpty()) {
            Glide.with(context)
                    .load(item.getImageURI())
                    .placeholder(R.drawable.placeholder_image) // Add a placeholder image while the actual image is loading
                    .error(R.drawable.baseline_image_search_24) // Add an error image if the image fails to load
                    .into(holder.itemImageView);
        }
        holder.itemNameTextView.setText(item.getItemName());
        holder.ownerNameTextView.setText(item.getOwnerName());
        holder.description.setText(item.getDescription());
        holder.location.setText(item.getLocation());
        holder.date.setText(item.getDateLost());

        // Set an onClickListener for the card view
        holder.itemView.setOnClickListener(v -> {
            // Perform action when the card is clicked
        });
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImageView;
        TextView itemNameTextView;
        TextView ownerNameTextView;
        TextView description;
        TextView location;
        TextView date;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImageView = itemView.findViewById(R.id.itemImageView);
            itemNameTextView = itemView.findViewById(R.id.itemNameTextView);
            ownerNameTextView = itemView.findViewById(R.id.ownerNameTextView);
            description= itemView.findViewById(R.id.item_description);
            location = itemView.findViewById((R.id.location));
            date = itemView.findViewById(R.id.dateLost);
        }
    }
}

