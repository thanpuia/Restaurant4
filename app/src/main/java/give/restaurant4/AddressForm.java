package give.restaurant4;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddressForm extends AppCompatActivity {

    EditText userName, shopName, userAddress, userPhone;
    public String userNameStr, shopNameStr, userAddressStr, userPhoneStr;

    public SharedPreferences sharedPreferences;
    public static String sharedUserName,sharedShopName,sharedUserAddress,sharedUserPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        sharedPreferences = this.getSharedPreferences("com.example.root.restaurant2", Context.MODE_PRIVATE);

        userName = findViewById(R.id.userName);
        shopName = findViewById(R.id.shopName);
        userAddress = findViewById(R.id.userAddress);
        userPhone = findViewById(R.id.userPhone);

        sharedUserName = sharedPreferences.getString("userName","");
        sharedShopName = sharedPreferences.getString("shopName","");
        sharedUserAddress = sharedPreferences.getString("userAddress","");
        sharedUserPhone = sharedPreferences.getString("userPhone","");
//  address = sharedPreferences.getString("address","");
        userName.setText(sharedUserName);
        shopName.setText(sharedShopName);
        userAddress.setText(sharedUserAddress);
        userPhone.setText(sharedUserPhone);
    }

    public void addressSaveClick(View view) {

        userNameStr = String.valueOf(userName.getText());
        shopNameStr = String.valueOf(shopName.getText());
        userAddressStr = String.valueOf(userAddress.getText());
        userPhoneStr = String.valueOf(userPhone.getText());

        if( !userNameStr.equals("") && !shopNameStr.equals("") &&
                !userAddressStr.equals("") && !userPhoneStr.equals("")){
            Toast.makeText(this,"evetuthing is filled",Toast.LENGTH_SHORT).show();

            //PUT THE USER ADDRESS IN THE SHARED-PREFERENCES
            sharedPreferences.edit().putString("userName", userAddressStr).apply();
            sharedPreferences.edit().putString("shopName",shopNameStr).apply();
            sharedPreferences.edit().putString("userAddress",userAddressStr).apply();
            sharedPreferences.edit().putString("userPhone", userPhoneStr).apply();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else
            Toast.makeText(this,"Fill everything",Toast.LENGTH_SHORT).show();

    }
}
// final SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.root.restaurant", Context.MODE_PRIVATE);

//  address = sharedPreferences.getString("address","");

//  sharedPreferences.edit().putString("address" , "A:3/1, Electric veng").apply();
