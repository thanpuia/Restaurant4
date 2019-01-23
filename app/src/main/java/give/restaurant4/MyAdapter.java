package give.restaurant4;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
    ArrayList<String> orders = new ArrayList<>();
    ;

     int[] menuItemName = new int[]{R.string.mixed_chow_half ,R.string.mixed_chow_full ,R.string.mushroom_chow ,R.string.chow_egg_top ,R.string.fried_chow_egg ,R.string.fried_chow_chicken ,
            R.string.fried_chow_beef ,R.string.fried_chow_pork ,R.string.veg_fried_chow ,R.string.chowmein_egg ,R.string.chowmein_chicken ,R.string.chowmein_beef ,R.string.chowmein_pork ,R.string.chowmein_veg ,R.string.hakka_noodle ,R.string.american_chopsuey ,R.string.chinese_chopsuey ,
            R.string.jerrys_special_chopsuey ,R.string.jerrys_special_chow ,R.string.noodle_soup_chicken ,R.string.noodle_soup_beef ,R.string.noodle_soup_pork};

    int[] menuImage = new int[]{R.drawable.burger,R.drawable.chow,R.drawable.eggroll,R.drawable.friedrice,R.drawable.firedrice2,R.drawable.burger,
            R.drawable.burger,R.drawable.chow,R.drawable.eggroll,R.drawable.friedrice,R.drawable.firedrice2,R.drawable.burger,
            R.drawable.burger,R.drawable.chow,R.drawable.eggroll,R.drawable.friedrice,R.drawable.firedrice2,R.drawable.burger,
            R.drawable.burger,R.drawable.chow,R.drawable.eggroll,R.drawable.friedrice,R.drawable.firedrice2,R.drawable.burger,
            R.drawable.burger,R.drawable.chow,R.drawable.eggroll,R.drawable.friedrice,R.drawable.firedrice2};

     int elegentNumber=1;


    @NonNull
    @Override
    public MyAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.model,viewGroup,false);
        MyHolder holder = new MyHolder(v);
        /*context = viewGroup.getContext();
        layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        elegantNumberButton = new ElegantNumberButton(context);
        layout.addView(elegantNumberButton);
*/
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyAdapter.MyHolder holder, final int i) {

        holder.textView_MenuItem.setText(menuItemName[i]);
//
//        holder.imageViewMenu.setImageResource(menuImage[i]);
//
        holder.elegantNumberButton.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                elegentNumber=newValue;
                Toast.makeText(holder.itemView.getContext()," old:"+oldValue+ " new:"+newValue,Toast.LENGTH_SHORT).show();
            }
        });
        holder.addButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Log.i("TAG", "Index numer: " + i);

                //Toast.makeText(holder.itemView.getContext(),"",Toast.LENGTH_LONG).show();
                String s = String.valueOf(holder.itemView.getContext().getText(menuItemName[i]));

                    AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext());
// Add the buttons
                builder.setMessage("Are you sure?"+
                                "\n"+ s +" X"+elegentNumber);

                String s2 = s+" X"+elegentNumber;
orders.add(s2);
        elegentNumber = 1;
                //elegantNumberButton.setNumber("1");

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User clicked OK button
                            Toast.makeText(holder.itemView.getContext(),""+orders,Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                        }
                    });
// Set other dialog properties


// Create the AlertDialog
                    AlertDialog dialog = builder.create();
                    dialog.show();

            }
        });






    }

    @Override
    public int getItemCount() {
        return menuItemName.length;
    }

    class MyHolder extends RecyclerView.ViewHolder {

        TextView textView_MenuItem;
        Button addButton;
        ImageView imageViewMenu;
        ElegantNumberButton elegantNumberButton;


        public MyHolder(View itemView) {
            super(itemView);

            this.textView_MenuItem = itemView.findViewById(R.id.textViewMenuItem);
            this.addButton=  itemView.findViewById(R.id.addButton);
            this.imageViewMenu=  itemView.findViewById(R.id.imageViewMenu);
            this.elegantNumberButton = itemView.findViewById(R.id.elegantNumber);

        }
    }


}
