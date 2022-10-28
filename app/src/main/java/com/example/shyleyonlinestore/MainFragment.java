package com.example.shyleyonlinestore;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainFragment extends Fragment {

    private BottomNavigationView bottomNavView;
    private RecyclerView newItemsRecView, popularItemRecView, suggestedItemsRecView;
    private GroceryitemAdaptor newItemsAdaptor, popularItemsAdaptor, suggestedItemsAdaptor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        initView(view);
        initBottonNavView();
  //   Utils.clearSharedPreferences(getActivity());  //use to clear sharepreferences
//        initRecViews();
        return view;
    }

    @Override
    public void onResume() {

        initRecViews();
        super.onResume();
    }

    private void initRecViews(){
        newItemsAdaptor = new GroceryitemAdaptor(getActivity());
        newItemsRecView.setAdapter(newItemsAdaptor);
        newItemsRecView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));

        popularItemsAdaptor = new GroceryitemAdaptor(getActivity());
        popularItemRecView.setAdapter(popularItemsAdaptor);
        popularItemRecView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));

        suggestedItemsAdaptor = new GroceryitemAdaptor(getActivity());
        suggestedItemsRecView.setAdapter(suggestedItemsAdaptor);
        suggestedItemsRecView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));

        ArrayList<GroceryItem> newItems = Utils.getAllitems(getActivity());

        if (null != newItems){
            Comparator<GroceryItem> newItemsComparator = new Comparator<GroceryItem>() {
                @Override
                public int compare(GroceryItem groceryItem, GroceryItem t1) {
                    return groceryItem.getId() - t1.getId();
                }
            };

            Comparator<GroceryItem> reverseComparator = Collections.reverseOrder(newItemsComparator);
            Collections.sort(newItems, reverseComparator);
            newItemsAdaptor.setItems(newItems);
        }

        ArrayList<GroceryItem> popularItems = Utils.getAllitems(getActivity());
        if (null != popularItems){
            Comparator<GroceryItem> popularItemsComparator = new Comparator<GroceryItem>() {
                @Override
                public int compare(GroceryItem item, GroceryItem t1) {
                    return item.getPopolarityPoint() - t1.getPopolarityPoint();
                }
            };

            Collections.sort(popularItems, Collections.reverseOrder(popularItemsComparator));
            popularItemsAdaptor.setItems(popularItems);
        }

        ArrayList<GroceryItem> suggestedItems = Utils.getAllitems(getActivity());
        Comparator<GroceryItem> suggestedItemsComparator = new Comparator<GroceryItem>() {
            @Override
            public int compare(GroceryItem item, GroceryItem t1) {
                return item.getUserpoint() - t1.getUserpoint();
            }
        };
        Collections.sort(suggestedItems,Collections.reverseOrder(suggestedItemsComparator));
        suggestedItemsAdaptor.setItems(suggestedItems);



    }

//    private int newItemCustomComparator(GroceryItem item1, GroceryItem item2){
//        if (item1.getId() > item2.getId()){
//            return 1;
//        }else if (item1.getId() < item1.getId()){
//            return -1;
//        }else {
//            return 0;
//        }
//    }

    private void initBottonNavView(){
        bottomNavView.setSelectedItemId(R.id.home);
        // TODO: 6/21/2022 finish this
        bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:

                        break;
                    case R.id.search:
                        Intent intent = new Intent(getActivity(), search_activity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        break;
                    case R.id.cart:
                        Intent cartIntent = new Intent(getActivity(), CartActivity.class);
                        cartIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(cartIntent);

                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    private void initView(View view){

        bottomNavView = view.findViewById(R.id.bottomNavView);
        newItemsRecView = view.findViewById(R.id.newItemsRecView);
        popularItemRecView = view.findViewById(R.id.popularItemRecView);
        suggestedItemsRecView = view.findViewById(R.id.suggestedItemsRecView);

    }
}
