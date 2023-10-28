package com.shruti.lofo.ui.Found;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.shruti.lofo.R;
import com.shruti.lofo.Utility;
import com.shruti.lofo.databinding.FragmentFoundBinding;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.Query.Direction;
import com.shruti.lofo.ui.Found.FoundItems;
import com.shruti.lofo.ui.Found.FoundItemsAdapter;

import java.util.ArrayList;
import java.util.List;

public class FoundFragment extends Fragment {

    private FragmentFoundBinding binding;
    FloatingActionButton addBtn;
    RecyclerView recyclerView;
    FoundItemsAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentFoundBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        recyclerView = root.findViewById(R.id.recyclerView);
        setupRecyclerView();

        addBtn = root.findViewById(R.id.add_found);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform the desired action here
                FoundItemsFragment dialogFragment = new FoundItemsFragment();
                dialogFragment.show(getParentFragmentManager(), "form_dialog");
            }
        });


        return root;
    }
    void setupRecyclerView(){
        Query query = Utility.getCollectionReferrenceForFound().orderBy("dateFound",Query.Direction.DESCENDING);
        FirestoreRecyclerOptions <FoundItems> options = new FirestoreRecyclerOptions.Builder<FoundItems>()
                .setQuery(query,FoundItems.class).build();

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        // Set the adapter
        adapter = new FoundItemsAdapter(options, requireContext());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

}