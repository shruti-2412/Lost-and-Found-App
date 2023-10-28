package com.shruti.lofo.ui.Lost;

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
import com.shruti.lofo.databinding.FragmentLostBinding;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.Query.Direction;


public class LostFragment extends Fragment {

    private FragmentLostBinding binding;
    FloatingActionButton addBtn;
    RecyclerView recyclerView;
    LostItemsAdapter adapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        LostViewModel lostViewModel =
                new ViewModelProvider(this).get(LostViewModel.class);

        binding = FragmentLostBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.recyclerView);
        setupRecyclerView();

        addBtn = root.findViewById(R.id.add_lost);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

// Check if the user is logged in
            addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Perform the desired action here
                    LostItemsFragment dialogFragment = new LostItemsFragment();
                    dialogFragment.show(getParentFragmentManager(), "form_dialog");
                }
            });


        return root;
    }

    void setupRecyclerView(){
        Query query = Utility.getCollectionReferrenceForItems2().orderBy("dateLost",Query.Direction.DESCENDING);
        FirestoreRecyclerOptions <LostItems> options = new FirestoreRecyclerOptions.Builder<LostItems>()
                .setQuery(query,LostItems.class).build();

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        // Set the adapter
         adapter = new LostItemsAdapter(options, requireContext());
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
