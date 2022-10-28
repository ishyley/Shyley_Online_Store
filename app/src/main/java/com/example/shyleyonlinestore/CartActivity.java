package com.example.shyleyonlinestore;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CartActivity extends AppCompatActivity {

    private MaterialToolbar toolbar;
    private BottomNavigationView bottomNavView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        initViews();
        initBottomNavView();
        setSupportActionBar(toolbar);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, new FirstCartFragment());
        transaction.commit();
    }

    private void initBottomNavView(){
        bottomNavView.setSelectedItemId(R.id.cart);
        bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.search:
                        Intent searchintent = new Intent(CartActivity.this, search_activity.class);
                        searchintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(searchintent);
                    case R.id.home:
                        Intent intent = new Intent(CartActivity.this, MainActivity.class );
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    case R.id.cart:

                        break;
                    default:
                        break;
                }
                return false;
            }
        });


        };
     //   bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);


        private void initViews(){
        toolbar = findViewById(R.id.toolbar);
            bottomNavView = findViewById(R.id.bottomNavView);
        }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    }
