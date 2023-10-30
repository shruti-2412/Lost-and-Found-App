package com.shruti.lofo.ui.Found;

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


public class FoundItemsAdapter extends FirestoreRecyclerAdapter<FoundItems, FoundItemsAdapter.ItemViewHolder> {

    Context context;

    private String category;



    public FoundItemsAdapter(@NonNull FirestoreRecyclerOptions<FoundItems> options, Context context, String category) {
        super(options);
        this.context = context;
        this.category = category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.found_item_card, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ItemViewHolder holder, int position, @NonNull FoundItems item) {
      if(category.isEmpty() || item.getCategory().equals(category))  {
            if (item.getImageURI() != null && !item.getImageURI().isEmpty()) {
                Glide.with(context)
                        .load(item.getImageURI())
                        .placeholder(R.drawable.placeholder_image) // Add a placeholder image while the actual image is loading
                        .error(R.drawable.baseline_image_search_24) // Add an error image if the image fails to load
                        .into(holder.itemImageView);
            }
            holder.itemNameTextView.setText(item.getItemName());
            holder.finderNameTextView.setText(item.getfinderName());
            holder.description.setText(item.getDescription());
            holder.location.setText(item.getLocation());
            holder.date.setText(item.getDateFound());

            // Set an onClickListener for the card view
            holder.itemView.setOnClickListener(v -> {
                // Perform action when the card is clicked
            });
        }
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImageView;
        TextView itemNameTextView;
        TextView finderNameTextView;
        TextView description;
        TextView location;
        TextView date;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImageView = itemView.findViewById(R.id.itemImageView);
            itemNameTextView = itemView.findViewById(R.id.itemNameTextView);
            finderNameTextView = itemView.findViewById(R.id.finderNameTextView);
            description= itemView.findViewById(R.id.item_description);
            location = itemView.findViewById((R.id.location));
            date = itemView.findViewById(R.id.dateFound);
        }
    }
}

