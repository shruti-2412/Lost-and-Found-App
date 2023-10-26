package com.shruti.lofo.ui.Lost;

import android.content.Context;
import android.net.Uri;
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
        // Set image URI to itemImageView
        // Assuming item.getImageURI() returns a valid image URI
//        holder.itemImageView.setImageURI(Uri.parse(item.getImageURI()));
        holder.itemNameTextView.setText(item.getItemName());
        holder.ownerNameTextView.setText(item.getOwnerName());

        // Set an onClickListener for the card view
        holder.itemView.setOnClickListener(v -> {
            // Perform action when the card is clicked
        });
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImageView;
        TextView itemNameTextView;
        TextView ownerNameTextView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
//            itemImageView = itemView.findViewById(R.id.itemImageView);
            itemNameTextView = itemView.findViewById(R.id.itemNameTextView);
            ownerNameTextView = itemView.findViewById(R.id.ownerNameTextView);
        }
    }
}

