package com.shruti.lofo.ui.Lost;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.shruti.lofo.R;
import com.shruti.lofo.databinding.FragmentLostBinding;

public class LostFragment extends Fragment {

    private FragmentLostBinding binding;
    FloatingActionButton addBtn;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        LostViewModel lostViewModel =
                new ViewModelProvider(this).get(LostViewModel.class);

        binding = FragmentLostBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textLost;
        textView.setVisibility(View.VISIBLE); // Set the visibility to be visible
        lostViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        addBtn = root.findViewById(R.id.add_lost); // Replace your_fab_id with the ID of your FloatingActionButton

        // Set an onClickListener for the FloatingActionButton
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


}
