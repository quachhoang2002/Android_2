package food.app.activity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import food.app.activity.Activities.FoodDetailActivity;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    List<FoodItemModel> list;
    Context context;

    public FoodAdapter(List<FoodItemModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item_layout, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        System.out.println( "here"  + list.get(position).getId());
//        holder.food_image.setImageResource(list.get(position).id);
        holder.food_name.setText(list.get(position).name);
        holder.food_price.setText(list.get(position).description);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FoodDetailActivity.class);
                intent.putExtra("food_name", list.get(position).getId());
                intent.putExtra("food_price", list.get(position).getName());
//                intent.putExtra("food_image", list.get(position).getDescription());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder {

        ImageView food_image;
        TextView food_name, food_price;
        View view;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            food_image = itemView.findViewById(R.id.food_image);
            food_name = itemView.findViewById(R.id.food_name);
            food_price = itemView.findViewById(R.id.food_price);


        }
    }
}
