package com.example.shyleyonlinestore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SecondCartFragment extends Fragment {

    public static final String ORDER_KEY = "order";

    private TextView txtemail, txtphonenuber, txtZipcode, txtAddress, txtwarning;
    private Button btnNext, btnBack;
    private EditText edtTxtemail, edtTxtphonenumber, edtTxtzipcode, edtTxtAddress ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart_second, container, false);
        initViews(view);

        Bundle bundle = getArguments();
        if (null != bundle){
            String jsonOrder = bundle.getString(ORDER_KEY);
            if (null != bundle){
                Gson gson = new Gson();
                Type type = new TypeToken<Order>(){}.getType();
                Order order = gson.fromJson(jsonOrder, type);

                if (null != order){

                    edtTxtzipcode.setText(order.getZipCode());
                    edtTxtemail.setText(order.getEmail());
                    edtTxtphonenumber.setText(order.getPhoneNumber());
                    edtTxtAddress.setText(order.getEmail());
                }
            }
        }
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, new FirstCartFragment());
                transaction.commit();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateData()){
                    txtwarning.setVisibility(View.GONE);

                    ArrayList<GroceryItem> cartItems = Utils.getCartItems(getActivity());
                    if (null != cartItems){
                        Order order = new Order();
                        order.setItems(cartItems);
                        order.setAddress(edtTxtAddress.getText().toString());
                        order.setPhoneNumber(edtTxtphonenumber.getText().toString());
                        order.setZipCode(edtTxtzipcode.getText().toString());
                        order.setEmail(edtTxtemail.getText().toString());
                        order.setTotalPrice(calculateTotalPrice(cartItems));

                        Gson gson = new Gson();
                        String jsonOrder = gson.toJson(order);
                        Bundle bundle = new Bundle();
                        bundle.putString(ORDER_KEY, jsonOrder);

                        ThirdCartFragment thirdCartFragment = new ThirdCartFragment();
                        thirdCartFragment.setArguments(bundle);
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.container, thirdCartFragment);
                        transaction.commit();
                    }
                }else {
                    txtwarning.setVisibility(View.VISIBLE);
                    txtwarning.setText("Please Fill All The Blanks");

                }
            }
        });
        return view;
    }

    private double calculateTotalPrice(ArrayList<GroceryItem> items) {
        double price = 0;
        for (GroceryItem item : items) {
            price += item.getPrice();
        }
        price = Math.round(price*100.0)/100.0;

        return price;

    }

    private boolean validateData(){
        if (edtTxtAddress.getText().toString().equals("") || edtTxtphonenumber.getText().toString().equals("") || edtTxtemail.getText().toString().equals("") || edtTxtzipcode.getText().toString().equals("")){
            return false;
        }

        return true;
    }

    private void initViews(View view){
        txtemail = view.findViewById(R.id.txtemail);
        txtwarning = view.findViewById(R.id.txtWarning);
        txtphonenuber = view.findViewById(R.id.txtphonenuber);
        txtZipcode = view.findViewById(R.id.txtZipcode);
        txtAddress = view.findViewById(R.id.txtAddress);
        edtTxtemail = view.findViewById(R.id.edtTxtemail);
        edtTxtphonenumber = view.findViewById(R.id.edtTxtphonenumber);
        edtTxtzipcode = view.findViewById(R.id.edtTxtzipcode);
        edtTxtAddress = view.findViewById(R.id.edtTxtAddress);
        btnNext = view.findViewById(R.id.btnNext);
        btnBack = view.findViewById(R.id.btnBack);



    }
}
