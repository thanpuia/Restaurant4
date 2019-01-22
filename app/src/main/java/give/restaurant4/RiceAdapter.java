package give.restaurant4;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RiceAdapter extends RecyclerView.Adapter<RiceAdapter.MyHolder> {

    int[] menuItemName = new int[]{R.string.mixed_rice_half ,R.string.mixed_rice_full ,R.string.rice_egg_top ,
            R.string.fried_rice_egg ,R.string.fried_rice_chicken ,R.string.fried_rice_beef ,R.string.fried_rice_pork };

    int[] menuImage = new int[]{R.drawable.burger,R.drawable.chow,R.drawable.eggroll,R.drawable.friedrice,
            R.drawable.firedrice2,R.drawable.burger, R.drawable.burger};

    @NonNull
    @Override
    public RiceAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.model,viewGroup,false);
        MyHolder holder = new MyHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int i) {
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
