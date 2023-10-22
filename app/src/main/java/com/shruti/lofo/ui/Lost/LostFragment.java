package com.shruti.lofo.ui.Lost;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.shruti.lofo.databinding.FragmentLostBinding;

public class LostFragment extends Fragment {

private FragmentLostBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        LostViewModel lostViewModel =
                new ViewModelProvider(this).get(LostViewModel.class);

    binding = FragmentLostBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

        final TextView textView = binding.textLost;
        lostViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}