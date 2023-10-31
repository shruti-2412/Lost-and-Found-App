package com.shruti.lofo.ui.MyItems;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.Query;
import com.shruti.lofo.R;
import com.shruti.lofo.Utility;
import com.shruti.lofo.databinding.FragmentLostBinding;
import com.shruti.lofo.ui.Found.FoundItems;
import com.shruti.lofo.ui.Found.FoundItemsAdapter;
import com.shruti.lofo.ui.Lost.LostItems;
import com.shruti.lofo.ui.Lost.LostItemsAdapter;

public class MyItems extends Fragment {

    private FragmentLostBinding binding;
    private RecyclerView recyclerView;
    private LostItemsAdapter lostAdapter;
    private FoundItemsAdapter foundAdapter;
    private String userId;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentLostBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.recyclerView);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        userId = currentUser.getUid();

        setupRecyclerView(true);

        return root;
    }

    void setupRecyclerView(boolean showDeleteButton) {
        Query lostQuery = Utility.getCollectionReferrenceForItems2().whereEqualTo("userId", userId);
        FirestoreRecyclerOptions<LostItems> lostOptions = new FirestoreRecyclerOptions.Builder<LostItems>()
                .setQuery(lostQuery, LostItems.class).build();

        Query foundQuery = Utility.getCollectionReferrenceForFound().whereEqualTo("userId", userId);
        FirestoreRecyclerOptions<FoundItems> foundOptions = new FirestoreRecyclerOptions.Builder<FoundItems>()
                .setQuery(foundQuery, FoundItems.class).build();

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        foundAdapter = new FoundItemsAdapter(foundOptions, requireContext(), "", showDeleteButton);
        recyclerView.setAdapter(foundAdapter);
        lostAdapter = new LostItemsAdapter(lostOptions, requireContext(), "", showDeleteButton);
        recyclerView.setAdapter(foundAdapter);

        recyclerView.setAdapter(lostAdapter);

    }

    @Override
    public void onStart() {
        super.onStart();
        if (lostAdapter != null) {
            lostAdapter.startListening();
        }
        if (foundAdapter != null) {
            foundAdapter.startListening();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (lostAdapter != null) {
            lostAdapter.stopListening();
        }
        if (foundAdapter != null) {
            foundAdapter.stopListening();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (lostAdapter != null) {
            lostAdapter.notifyDataSetChanged();
        }
        if (foundAdapter != null) {
            foundAdapter.notifyDataSetChanged();
        }
    }
}
