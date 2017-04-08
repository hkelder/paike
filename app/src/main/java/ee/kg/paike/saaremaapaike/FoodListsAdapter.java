package ee.kg.paike.saaremaapaike;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

class FoodListsAdapter extends RecyclerView.Adapter<FoodListsAdapter.ViewHolder> {

    private Context adapterContext;
    private ArrayList<Food> foodList;

    FoodListsAdapter(Context context, ArrayList<Food> foodList) {
        adapterContext = context;
        this.foodList = foodList;

    }

    @Override
    public FoodListsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Uus info rida
        LinearLayout row = (LinearLayout) LayoutInflater.from(adapterContext)
                .inflate(R.layout.food_lists, parent, false);

        ViewHolder foodListsViewHolder = new ViewHolder(row);


        return foodListsViewHolder;
    }

    @Override
    public void onBindViewHolder(FoodListsAdapter.ViewHolder holder, int position) {

        Food food = foodList.get(position);
        holder.heading.setText(food.heading);
        holder.foodName.setText(food.foodName);
        holder.foodPrice.setText(food.foodPrice);

    }

    @Override
    public int getItemCount() {return foodList.size(); }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.food_listitem_heading)
        TextView heading;
        @BindView(R.id.food_name)
        TextView foodName;
        @BindView(R.id.food_price)
        TextView foodPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
