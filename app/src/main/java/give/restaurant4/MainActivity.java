package give.restaurant4;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import static give.restaurant4.FinalOrder.arrayAdapter;
import static give.restaurant4.FinalOrder.listView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerView1;
    Button button,fab,fab1,fab2,fab3;
    int  fabCounter=2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView1 = findViewById(R.id.recyclerView1);

        MyAdapter adapter = new MyAdapter();

        recyclerView1.setItemViewCacheSize(20);
        recyclerView1.setDrawingCacheEnabled(true);
        recyclerView1.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        recyclerView1.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        recyclerView1.setAdapter(adapter);

        fab =  findViewById(R.id.fab);
        fab1 =  findViewById(R.id.fab1);
        fab2 =  findViewById(R.id.fab2);
        fab3 =  findViewById(R.id.fab3);

        fab.setVisibility(View.GONE);
        fab1.setVisibility(View.GONE);
        fab2.setVisibility(View.GONE);
        fab3.setVisibility(View.GONE);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

                recyclerView1.getLayoutManager().scrollToPosition(0);
            };
        });

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView1.getLayoutManager().scrollToPosition(16);
            };
        });
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView1.getLayoutManager().scrollToPosition(9);
            };
        });
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView1.getLayoutManager().scrollToPosition(19);

            };
        });


        // Create the show or hide menu button
        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(fabCounter%2 == 0){
                    fab.setVisibility(View.GONE);
                    fab1.setVisibility(View.GONE);
                    fab2.setVisibility(View.GONE);
                    fab3.setVisibility(View.GONE);
                    button.setText("Menu +");
                }
                else{
                    fab.setVisibility(View.VISIBLE);
                    fab1.setVisibility(View.VISIBLE);
                    fab2.setVisibility(View.VISIBLE);
                    fab3.setVisibility(View.VISIBLE);
                    button.setText("Menu -");
                }
                fabCounter=fabCounter+1;

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, AddressForm.class);
            startActivity(intent);
            return true;        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.menu_pfd) {
            Toast.makeText(this, "Menu downloading...", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_gallery) {
            Toast.makeText(this, "Gallery will be shown in the full version", Toast.LENGTH_SHORT).show();

         } else if (id == R.id.delivery_address) {
            Intent intent = new Intent(this, AddressForm.class);
            startActivity(intent);

        } else if (id == R.id.contact_us) {
            dialog();

        } else if (id == R.id.nav_share) {
            Toast.makeText(this, "Will be functioning in the full version", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_send) {
            Toast.makeText(this, "Will be functioning in the full version", Toast.LENGTH_SHORT).show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void dismissListener(View view) {
        //Toast.makeText(this,"dfdf",Toast.LENGTH_LONG).show();
        MyAdapter.settingDialog.cancel();
    }

    public void orderedClick(View view) {
        Intent intent = new Intent(this,FinalOrder.class);
        String totAmountString = totalAmount();
        intent.putExtra("orderAmount",totAmountString);
        startActivity(intent);
    }

    public static String totalAmount() {
        int totAmount=0;
        Log.i("TAG","order Amount new Price: "+ MyAdapter.orders_amount);
        for(int i=0; i<MyAdapter.orders_amount.size();i++) {

            totAmount= MyAdapter.orders_amount.get(i) +totAmount;
        }
        String totAmountString = String.valueOf(totAmount);

        return totAmountString;
    }

    public void dialog(){
        AlertDialog.Builder builder;

        builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.contact_us);
        builder.setPositiveButton("Call", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),"Calling feature will be added soon",Toast.LENGTH_SHORT).show();

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
