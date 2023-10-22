package com.shruti.lofo.ui.Found;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.shruti.lofo.databinding.FragmentFoundBinding;

public class FoundFragment extends Fragment {

    private FragmentFoundBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FoundViewModel foundViewModel =
                new ViewModelProvider(this).get(FoundViewModel.class);

        binding = FragmentFoundBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textFound;
        textView.setVisibility(View.VISIBLE); // Set the visibility to be visible
        foundViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }


}
