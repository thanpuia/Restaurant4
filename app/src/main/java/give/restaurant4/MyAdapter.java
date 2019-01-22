package give.restaurant4;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
    static int[] menuItemName = new int[]{R.string.mixed_chow_half ,R.string.mixed_chow_full ,R.string.mushroom_chow ,R.string.chow_egg_top ,R.string.fried_chow_egg ,R.string.fried_chow_chicken ,
            R.string.fried_chow_beef ,R.string.fried_chow_pork ,R.string.veg_fried_chow ,R.string.chowmein_egg ,R.string.chowmein_chicken ,R.string.chowmein_beef ,R.string.chowmein_pork ,R.string.chowmein_veg ,R.string.hakka_noodle ,R.string.american_chopsuey ,R.string.chinese_chopsuey ,
            R.string.jerrys_special_chopsuey ,R.string.jerrys_special_chow ,R.string.noodle_soup_chicken ,R.string.noodle_soup_beef ,R.string.noodle_soup_pork};

    int[] menuImage = new int[]{R.drawable.burger,R.drawable.chow,R.drawable.eggroll,R.drawable.friedrice,R.drawable.firedrice2,R.drawable.burger,
            R.drawable.burger,R.drawable.chow,R.drawable.eggroll,R.drawable.friedrice,R.drawable.firedrice2,R.drawable.burger,
            R.drawable.burger,R.drawable.chow,R.drawable.eggroll,R.drawable.friedrice,R.drawable.firedrice2,R.drawable.burger,
            R.drawable.burger,R.drawable.chow,R.drawable.eggroll,R.drawable.friedrice,R.drawable.firedrice2,R.drawable.burger,
            R.drawable.burger,R.drawable.chow,R.drawable.eggroll,R.drawable.friedrice,R.drawable.firedrice2};

    @NonNull
    @Override
    public MyAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.model,viewGroup,false);
        MyHolder holder = new MyHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyAdapter.MyHolder holder, final int i) {

        holder.textView_MenuItem.setText(menuItemName[i]);

        holder.imageViewMenu.setImageResource(menuImage[i]);

        holder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TAG","Index numer: "+i);
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

        public MyHolder(View itemView) {
            super(itemView);

            this.textView_MenuItem = itemView.findViewById(R.id.textViewMenuItem);
            this.addButton=  itemView.findViewById(R.id.addButton);
            this.imageViewMenu=  itemView.findViewById(R.id.imageViewMenu);

        }
    }
}
