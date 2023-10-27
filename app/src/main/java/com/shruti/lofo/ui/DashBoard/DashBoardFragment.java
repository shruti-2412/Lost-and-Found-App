package com.shruti.lofo.ui.DashBoard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shruti.lofo.R;
import com.shruti.lofo.databinding.FragmentDashboardBinding;

import java.util.ArrayList;


public class DashBoardFragment extends Fragment {
private FragmentDashboardBinding binding;


    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;



ArrayList<DashBoardViewModel> arr_recent_lofo = new ArrayList<>();
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        DashBoardViewModel DashboardViewModel = new ViewModelProvider(this).get(DashBoardViewModel.class);
    binding = FragmentDashboardBinding.inflate(inflater, container, false);
    View root = binding.getRoot();
    super.onCreate(savedInstanceState);
        RecyclerView recentLostFoundList = root.findViewById(R.id.recent_lost_found_list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        recentLostFoundList.setLayoutManager(layoutManager);

        arr_recent_lofo.add(new DashBoardViewModel(R.drawable.sample_img,"Spectacals","I lost my spects","Raj Tiwari"));
        arr_recent_lofo.add(new DashBoardViewModel(R.drawable.sample_img,"B","I lost my B","Raj Tiwari"));
        arr_recent_lofo.add(new DashBoardViewModel(R.drawable.sample_img,"C","I lost my C","Ram"));
        arr_recent_lofo.add(new DashBoardViewModel(R.drawable.sample_img,"D","I lost my D","Vedika"));
        arr_recent_lofo.add(new DashBoardViewModel(R.drawable.sample_img,"E","I lost my E","Shruti"));
        arr_recent_lofo.add(new DashBoardViewModel(R.drawable.sample_img,"F","I lost my F","Vaibhavi"));
        arr_recent_lofo.add(new DashBoardViewModel(R.drawable.sample_img,"G","I lost my G","Isha"));
        arr_recent_lofo.add(new DashBoardViewModel(R.drawable.sample_img,"H","I lost my H","Sharwari"));
        arr_recent_lofo.add(new DashBoardViewModel(R.drawable.sample_img,"I","I lost my I","Sakshi"));
        arr_recent_lofo.add(new DashBoardViewModel(R.drawable.sample_img,"J","I lost my J","Simran"));


        RecyclerRecentLoFoAdapter adapter = new RecyclerRecentLoFoAdapter(requireContext(),arr_recent_lofo);
        recentLostFoundList.setAdapter(adapter);
//        final TextView textView = binding.textDashboard;
//        DashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);


        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
