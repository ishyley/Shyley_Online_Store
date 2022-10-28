package com.example.shyleyonlinestore;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddReviewDialog  extends DialogFragment {

    public interface AddReview{
        void onAddReviewResult(Review review);
    }

    private AddReview addReview;

    private static final String GROCERY_ITEM_KEY = "incoming_item" ;
    private TextView txtDescription, txtItemName, txtWarning;
    private EditText edtTxtUserName, edtTxtReview;
    private Button btnAddReview;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_add_review, null);
        initViews(view);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setView(view);

        Bundle bundle = getArguments();
        if (null != bundle){
           final GroceryItem item = bundle.getParcelable(GROCERY_ITEM_KEY);
            if (null != item){
//                txtItemName.setText(item.getName());
                btnAddReview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String username = edtTxtUserName.getText().toString();
                        String review = edtTxtReview.getText().toString();
                        String date = getCurrentDate();
                        if (username.equals("") || review.equals("")){
                            txtWarning.setText("Fill all the blanks");
                            txtWarning.setVisibility(View.VISIBLE);
                        }else{
                            txtWarning.setVisibility(View.GONE);
                            try {
                                addReview = (AddReview) getActivity();
                                addReview.onAddReviewResult(new Review(item.getId(), username, review, date));
                                dismiss();
                            }catch (ClassCastException e){
                                e.printStackTrace();
                            }

                        }
                    }
                });

            }
        }

        return builder.create();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            sdf = new SimpleDateFormat("MM-dd-YYYY");

        }
        return sdf.format(calendar.getTime());
    }

    private void initViews(View view){
        txtDescription = view.findViewById(R.id.txtDescription);
        txtWarning = view.findViewById(R.id.txtWarning);
        edtTxtReview = view.findViewById(R.id.edtTxtReview);
        edtTxtUserName = view.findViewById(R.id.edtTxtUserName);
        btnAddReview = view.findViewById(R.id.btnAddReview);

    }
}
