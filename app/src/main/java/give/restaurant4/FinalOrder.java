package give.restaurant4;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

public class FinalOrder extends Activity {

    public static ListView listView;
    public static ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_order);
        setActionBar(new Toolbar(this));


        listView = findViewById(R.id.list_item);

         arrayAdapter= new ArrayAdapter(this,android.R.layout.simple_list_item_1,MyAdapter.orders);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    MyAdapter.orderEditor(view,i);
                    //listView.notifyAll();
            }
        });
    }
}
