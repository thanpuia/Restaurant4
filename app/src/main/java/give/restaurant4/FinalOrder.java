package give.restaurant4;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FinalOrder extends Activity {

    public static ListView listView;
    public static ArrayAdapter arrayAdapter;
    public static TextView orderTotalAmountTextView,deliveryAddress;
    public FirebaseConnector firebaseConnector;
    public SharedPreferences sharedPreferences;
    public static String sharedUserName,sharedShopName,sharedUserAddress,sharedUserPhone;
    public static String userFullAddress;
    public static Button finalOrder;
    public static String amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_order);

        sharedPreferences = this.getSharedPreferences("com.example.root.restaurant2", Context.MODE_PRIVATE);
        sharedUserName = sharedPreferences.getString("userName","");
        sharedShopName = sharedPreferences.getString("shopName","");
        sharedUserAddress = sharedPreferences.getString("userAddress","");
        sharedUserPhone = sharedPreferences.getString("userPhone","");

        userFullAddress =sharedUserName +"\n"+ sharedShopName+"\n"+sharedUserAddress+"\n"+sharedUserPhone;

        Intent intent = getIntent();
        amount = intent.getStringExtra("orderAmount");
        orderTotalAmountTextView = findViewById(R.id.orderAmount);
        deliveryAddress = findViewById(R.id.deliveryAddress);
        listView = findViewById(R.id.list_item);
        finalOrder = findViewById(R.id.finalOrder);

        arrayAdapter= new ArrayAdapter(this,android.R.layout.simple_list_item_1,MyAdapter.orders);
        listView.setAdapter(arrayAdapter);

        deliveryAddress.setText(userFullAddress);
        //orderTotalAmountTextView.setText("TOTAL ORDER AMOUNT: Rs." + amount);

        finalOrder.setText("CONFIRM/ORDER (Rs. "+amount+")");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    MyAdapter.orderEditor(view,i);
                    //listView.notifyAll();
            }
        });
    }

    public void finalOrderClick(View view) {
      //  firebaseConnector.writeToDataBase();

//        FirebaseApp.initializeApp(this);
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message");
//
//        myRef.setValue("Hello, World!");

        android.app.AlertDialog.Builder builder;

        builder = new android.app.AlertDialog.Builder(view.getContext());

        builder.setMessage("Confirm Order?");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),"Your Order is sent, wait for confirmation",Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(getApplicationContext(),"Order cancel",Toast.LENGTH_SHORT).show();

                // User cancelled the dialog
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void deliveryAddressEditClick(View view) {
        Intent intent = new Intent(this,AddressForm.class);
        startActivity(intent);
    }
}
