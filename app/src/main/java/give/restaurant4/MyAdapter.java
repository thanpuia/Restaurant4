package give.restaurant4;

import android.app.AlertDialog;
import android.app.Dialog;
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
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import java.util.ArrayList;
import static give.restaurant4.FinalOrder.arrayAdapter;
import static give.restaurant4.FinalOrder.listView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
    public static ArrayList<String> orders = new ArrayList<>();
    public static ArrayList<Integer> orders_amount = new ArrayList<>();

    public int DEFAULT_ELEGANT_NUMBER = 1;

    int[] amount = new int[] {60,120,80,
                               70,60,60,
                              60,60,50,
                              90,90,90,

                               90,90,80,
                               120,120,80,
                                70,70,70,
                                 70};

    public static Dialog settingDialog;

    int[] menuItemName = new int[]{
             R.string.mixed_chow_half ,  R.string.mixed_chow_full ,     R.string.mushroom_chow ,
             R.string.chow_egg_top ,     R.string.fried_chow_egg ,     R.string.fried_chow_chicken ,
             R.string.fried_chow_beef ,  R.string.fried_chow_pork ,    R.string.veg_fried_chow ,
             R.string.chowmein_egg ,     R.string.chowmein_chicken ,   R.string.chowmein_beef ,

             R.string.chowmein_pork ,    R.string.chowmein_veg , R.string.hakka_noodle ,
             R.string.american_chopsuey ,R.string.chinese_chopsuey ,   R.string.jerrys_special_chopsuey ,
             R.string.jerrys_special_chow ,R.string.noodle_soup_chicken ,R.string.noodle_soup_beef ,
             R.string.noodle_soup_pork};

    int[] menuImage = new int[]{
            R.drawable.chowmein,  R.drawable.chowmein_egg,      R.drawable.chow_pork,
            R.drawable.chow_egg_top, R.drawable.chow_pork, R.drawable.chowmein_egg,
            R.drawable.american_chopseuy,     R.drawable.fired_rice_mixed,      R.drawable.chow_pork,
            R.drawable.chowmein_egg,  R.drawable.chowmein,R.drawable.chowmein_egg,

            R.drawable.chowmein,    R.drawable.chowmein_egg,       R.drawable.chowmein_egg,
            R.drawable.american_chopseuy,R.drawable.chinese_chopsuey,   R.drawable.american_chopseuy,
            R.drawable.chinese_chopsuey,    R.drawable.rice,        R.drawable.noodles_3,
            R.drawable.chow_egg_top,R.drawable.noodles_1,  R.drawable.noodles_2,
            R.drawable.noodles_3};

     int elegentNumber=DEFAULT_ELEGANT_NUMBER;

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

        String itemNameDisplay =menuItemName[i]+String.valueOf(amount[i]);
        Log.i("TAG","menuItemname: "+menuItemName[i]+" amount: "+ amount[i]);
        holder.textView_MenuItem.setText(menuItemName[i]);
        holder.textView_MenuItemPrice.setText("Rs."+String.valueOf(amount[i]));
        holder.imageViewMenu.setImageResource(menuImage[i]);
        holder.imageViewMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(holder.itemView.getContext()," " + i,Toast.LENGTH_LONG).show();
                showDialogInImage(i,holder);
            }
        });

        holder.elegantNumberButton.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                elegentNumber=newValue;

                //Toast.makeText(holder.itemView.getContext()," old:"+oldValue+ " new:"+newValue,Toast.LENGTH_SHORT).show();
            }
        });
        holder.addButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Log.i("TAG", "Index numer: " + i);


                //Toast.makeText(holder.itemView.getContext(),"",Toast.LENGTH_LONG).show();
                String s = String.valueOf(holder.itemView.getContext().getText(menuItemName[i]));



                //THESE ARE THE SETTING FOR DIALOG OF ADD BUTTON
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext());

                //COMBINATION OF ITEM NAME + PRICE + X + NUMBER OF PLATE (this is for showing in the final order page
                final int singleOrderAmount = amount[i]*elegentNumber;
                final String singleItemOrder = s+ "\nRs."+amount[i]+" X "+elegentNumber +"= "+" Rs."+singleOrderAmount;

                // Add the buttons
//                builder.setMessage("Are you sure?"+
//                                "\n"+ s+ "\n"+amount[i] +" X "+elegentNumber +"= "+"Rs."+singleOrderAmount);
                builder.setMessage("Are you sure?"+
                        "\n"+ singleItemOrder);


                Log.i("TAG","Single order amount: " +singleOrderAmount+"  \n"+"amount:"+amount[i]);

                //
                //elegantNumberButton.setNumber("1");

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User clicked OK button
                         //   Toast.makeText(holder.itemView.getContext()," "+orders,Toast.LENGTH_SHORT).show();
                            orders.add(singleItemOrder);
                            orders_amount.add(singleOrderAmount);
                            Log.i("TAG","Order Amount: " +orders_amount);

                            //RETURN THE ELEGANT NUMBER TO 1 SO THAT OTHER ORDER CAN START AGAIN FROM ONE
                            elegentNumber=1;
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                        }
                    });
// Create the AlertDialog
                    AlertDialog dialog = builder.create();
                    dialog.show();
               // holder.elegantNumberButton.setNumber(String.valueOf(1));


            }
        });
    }

    private void showDialogInImage(int i, MyHolder holder) {

        LayoutInflater inflater = (LayoutInflater) holder.itemView.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        settingDialog = new Dialog(holder.itemView.getContext());
        settingDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        settingDialog.setContentView(inflater.inflate(R.layout.image_layout, null));

        settingDialog.show();

    }

    @Override
    public int getItemCount() {
        return menuItemName.length;
    }

    class MyHolder extends RecyclerView.ViewHolder {

        TextView textView_MenuItem, textView_MenuItemPrice;
        Button addButton;
        ImageView imageViewMenu;
        ElegantNumberButton elegantNumberButton;

        public MyHolder(View itemView) {
            super(itemView);

            this.textView_MenuItem = itemView.findViewById(R.id.textViewMenuItem);
            this.addButton=  itemView.findViewById(R.id.addButton);
            this.imageViewMenu=  itemView.findViewById(R.id.imageViewMenu);
            this.elegantNumberButton = itemView.findViewById(R.id.elegantNumber);
            this.textView_MenuItemPrice = itemView.findViewById(R.id.textViewMenuItemPrice);
        }
    }

    //THIS IS IN THE FINAL PAGE. FUNCTION FOR REMOVING ITEMS FROM THE FINAL ORDER
    public static void orderEditor(final
                                   View view, final int position) {

         AlertDialog.Builder builder;

        builder = new AlertDialog.Builder(view.getContext());
        builder.setPositiveButton("Remove", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                orders.remove(position);
                orders_amount.remove(position);

                String newOrderAmount = MainActivity.totalAmount();
                FinalOrder.orderTotalAmountTextView.setText(newOrderAmount);

                arrayAdapter= new ArrayAdapter(view.getContext(),android.R.layout.simple_list_item_1,orders);
                listView.setAdapter(arrayAdapter);
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
