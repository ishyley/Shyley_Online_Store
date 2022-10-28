package com.example.shyleyonlinestore;

import static com.example.shyleyonlinestore.AllCategoriesDialog.ALL_CATEGORIES;
import static com.example.shyleyonlinestore.AllCategoriesDialog.CALLING_ACTIVITY;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class search_activity extends AppCompatActivity implements AllCategoriesDialog.GetCategory {
    private static final String TAG = "search_activity";

    @Override
    public void onGetCategoryResult(String category) {
    ArrayList<GroceryItem> items = Utils.getItemsByCategory(this,category);
    if (null != items){
        adaptor.setItems(items);
        increaseUserPoint(items);
    }
    }

    private MaterialToolbar toolbar;
    private EditText searchBox;
    private ImageView btnSearch;
    private TextView txtThirdCat, txtSecondCat, txtFirstCat, txtAllCategories;
    private BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerView;
    
    private GroceryitemAdaptor adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initViews();
        initBottonNavView();
        
        setSupportActionBar(toolbar);
        
        adaptor = new GroceryitemAdaptor(this);
        recyclerView.setAdapter(adaptor);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        Intent intent = getIntent();
        if (null != intent){
            String category = intent.getStringExtra("category");
            if (null != category){
                ArrayList<GroceryItem> items = Utils.getItemsByCategory(this, category);
                if (null != items){
                    adaptor.setItems(items);
                    increaseUserPoint(items);
                }
            }
        }
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initSearch();
            }
        });

        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                initSearch();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ArrayList<String> categories = Utils.getCategories(this);
        if (null != categories){
            if (categories.size()>0){
                if (categories.size() == 1) {
                    showCategories(categories, 1);
                }else if(categories.size() == 2){
                    showCategories(categories, 2);
                }else{
                    showCategories(categories, 3);
                }
                }
            }
        txtAllCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AllCategoriesDialog dialog = new AllCategoriesDialog();
                Bundle bundle = new Bundle();
                bundle.putStringArrayList(ALL_CATEGORIES, Utils.getCategories(search_activity.this));
                bundle.putString(CALLING_ACTIVITY, "search_activity");
                dialog.setArguments(bundle);
                dialog.show(getSupportFragmentManager(), "all categories dialog");
            }
        });
        }

        private void increaseUserPoint(ArrayList<GroceryItem> items){
        for (GroceryItem i: items){
            Utils.changeUserPoint(this, i, 1);
        }
        }

    private void showCategories (ArrayList<String> categories, int i){
    switch (i) {
        case 1:
            txtFirstCat.setVisibility(View.VISIBLE);
            txtFirstCat.setText(categories.get(0));
            txtSecondCat.setVisibility(View.GONE);
            txtThirdCat.setVisibility(View.GONE);
            txtFirstCat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ArrayList<GroceryItem> items = Utils.getItemsByCategory(search_activity.this, categories.get(0));
                    if (null != items){
                        adaptor.setItems(items);
                        increaseUserPoint(items);
                    }

                }
            });
            break;
        case 2:
            txtFirstCat.setVisibility(View.GONE);
            txtSecondCat.setVisibility(View.VISIBLE);
            txtSecondCat.setText(categories.get(1));
            txtThirdCat.setVisibility(View.GONE);
            txtFirstCat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ArrayList<GroceryItem> items = Utils.getItemsByCategory(search_activity.this, categories.get(0));
                    if (null != items){
                        adaptor.setItems(items);
                        increaseUserPoint(items);
                    }

                }
            });

            txtSecondCat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ArrayList<GroceryItem> items = Utils.getItemsByCategory(search_activity.this, categories.get(1));
                    if (null != items){
                        adaptor.setItems(items);
                        increaseUserPoint(items);
                    }


                }
            });

            break;
        case 3:

            txtFirstCat.setVisibility(View.GONE);
            txtSecondCat.setVisibility(View.GONE);
            txtThirdCat.setVisibility(View.VISIBLE);
            txtThirdCat.setText(categories.get(2));
            txtFirstCat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ArrayList<GroceryItem> items = Utils.getItemsByCategory(search_activity.this, categories.get(0));
                    if (null != items){
                        adaptor.setItems(items);
                        increaseUserPoint(items);
                    }

                }
            });

            txtSecondCat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ArrayList<GroceryItem> items = Utils.getItemsByCategory(search_activity.this, categories.get(1));
                    if (null != items){
                        adaptor.setItems(items);
                        increaseUserPoint(items);
                    }


                }
            });

            txtThirdCat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ArrayList<GroceryItem> items = Utils.getItemsByCategory(search_activity.this, categories.get(2));
                    if (null != items){
                        adaptor.setItems(items);
                        increaseUserPoint(items);
                    }

                }
            });

            break;
        default:
            txtFirstCat.setVisibility(View.GONE);
            txtSecondCat.setVisibility(View.GONE);
            txtThirdCat.setVisibility(View.GONE);
            break;
    }
    }


    
    private void initSearch(){
        if (!searchBox.getText().toString().equals("")){
            // TODO: 7/4/2022 get items

            String name = searchBox.getText().toString();
            ArrayList<GroceryItem> items = Utils.searchForItems(this, name);
            if (null != items){
                adaptor.setItems(items);
                increaseUserPoint(items);
            }
        }
    }

    private void initBottonNavView(){
        bottomNavigationView.setSelectedItemId(R.id.search);
        // TODO: 6/21/2022 finish this
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                       Intent intent = new Intent(search_activity.this, MainActivity.class );
                       intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                       startActivity(intent);

                        break;
                    case R.id.search:
                        
                        break;
                    case R.id.cart:
                        Intent cartIntent = new Intent(search_activity.this, CartActivity.class);
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

    private void initViews(){
        toolbar = findViewById(R.id.toolbar);
        searchBox = findViewById(R.id.searchBox);
        btnSearch = findViewById(R.id.btnSearch);
        txtFirstCat = findViewById(R.id.txtFirstCat);
        txtSecondCat = findViewById(R.id.txtSecondCat);
        txtThirdCat = findViewById(R.id.txtThirdCat);
        txtAllCategories = findViewById(R.id.txtAllCategories);
        bottomNavigationView = findViewById(R.id.bottomNavView);
        recyclerView = findViewById(R.id.recyclerView);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }



}