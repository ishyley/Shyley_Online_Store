package com.example.shyleyonlinestore.DatabaseFiles;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.shyleyonlinestore.GroceryItem;

import java.util.ArrayList;

@Database(entities = {GroceryItem.class, Cartitem.class}, version = 1)
public abstract class ShopDatabase extends RoomDatabase {
    public abstract GroceryitemDao groceryitemDao();
    public abstract CartItemDao cartItemDao();

    private static ShopDatabase instance;

    public static synchronized ShopDatabase getInstance(Context context){

        if (null == instance){
            instance = Room.databaseBuilder(context, ShopDatabase.class, "shop_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .addCallback(initialCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback initialCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new InitialAsyncTask(instance).execute();


        }
    };

    private static class InitialAsyncTask extends AsyncTask<Void, Void, Void>{
        private GroceryitemDao groceryitemDao;


        private InitialAsyncTask(ShopDatabase db){
            this.groceryitemDao = db.groceryitemDao();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            ArrayList<GroceryItem> allItems = new ArrayList<>();
            GroceryItem Milk = new GroceryItem("Milk", "UHT MILK is also known as ‘fresh milk’. It has a natural milk taste and is easy to drink because it does not require boiling or mixing. As a wholesome, healthy food supplement it is packed with entire essential nutrients like protein, carbohydrates, calcium, minerals & vitamins to give you that wholesome goodness of fresh milk.\n" +
                    "Fresh and creamy\n" +
                    "Rich in vitamins and  \n" +
                    "Suitable for all cereal \n" +
                    "Affordable", "https://www-konga-com-res.cloudinary.com/w_auto,f_auto,fl_lossy,dpr_auto,q_auto/media/catalog/product/C/N/61495_1614880449.jpg", "Drink",
                    5900, 15);
            allItems.add(Milk);

            GroceryItem IceCream = new GroceryItem("Ice Cream", "Delicious", "https://tse2.mm.bing.net/th?id=OIP._eT3gonZIsUFvIZV6ZbyRQHaHa&pid=Api&P=0&w=168&h=168", "Food", 1100, 15);

            allItems.add(IceCream);

            GroceryItem Soda = new GroceryItem("Soda", "Tasty", "https://tse1.mm.bing.net/th?id=OIP.jdRSfD_KbGNDDL6d_DnSuQHaHa&pid=Api&P=0&w=176&h=176", "Drink", 2500, 12);

            allItems.add(Soda);

            GroceryItem Juice = new GroceryItem("Juice", "Contains twenty four (24) 10 ounces bottles of Tropicana Apple Juice; Packaging may Vary\n" +
                    "Tropicana 100 percent Apple Juice is the perfect beverage to pack in lunches or drink on the go\n" +
                    "Add Tropicana Apple Juice to your daily routine for a delicious and convenient source of vitamin C\n" +
                    "Refreshing apple juice with a deliciously sweet taste\n" +
                    "Get this 24 count juice pack delivered right to your door", "https://m.media-amazon.com/images/I/71jzRLQFqaL._SX569_.jpg", "Drink", 2500, 45);

            allItems.add(Juice);

            GroceryItem Spaghetti = new GroceryItem("Spaghetti", "Country of origin is United States\n" +
                    "Package Dimensions: 28.702 L x 27.686 H x 14.732 W (centimeters)\n" +
                    "Ingredients: Durum Wheat Semolina\n" +
                    "Safety warning: Allergen Statement: Contains wheat.", "https://images-na.ssl-images-amazon.com/images/I/41sJNYNf3HL._SX300_SY300_QL70_FMwebp_.jpg", "Food", 4900, 82);

            allItems.add(Spaghetti);

            GroceryItem Noodles = new GroceryItem("Soda", "Convenient cup allows you to quickly prepare hot soup virtually anywhere\n" +
                    "Chicken flavor with hearty vegetables creates great-tasting balance. Form: Powdered, Dried\n" +
                    "Instant recipe prepares in just 3 minutes\n" +
                    "Perfect for quick meals at the office, while camping, on the beach and more\n", "https://m.media-amazon.com/images/I/91uMP0uU4HL._SX569_PIbundle-12,TopRight,0,0_AA569SH20_.jpg", "Food", 3500, 42);

            allItems.add(Soda);

            for (GroceryItem g: allItems){
                groceryitemDao.insert(g);
            }


            return null;
        }
    }

}
