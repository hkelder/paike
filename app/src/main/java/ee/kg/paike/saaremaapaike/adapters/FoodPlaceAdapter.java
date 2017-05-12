package ee.kg.paike.saaremaapaike.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import ee.kg.paike.saaremaapaike.R;
import ee.kg.paike.saaremaapaike.model.FoodPlace;
import ee.kg.paike.saaremaapaike.model.FoodPlaceMenuItem;

public class FoodPlaceAdapter extends RecyclerView.Adapter<FoodPlaceAdapter.FoodPlaceViewHolder> {
    private List<FoodPlace> foodPlaces;

    public FoodPlaceAdapter(List<FoodPlace> foodPlaces) {
        this.foodPlaces = foodPlaces;
    }

    @Override
    public FoodPlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.food_list_item, parent, false);

        return new FoodPlaceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FoodPlaceViewHolder holder, int position) {
        FoodPlace place = foodPlaces.get(position);

        holder.name.setText(place.name);

        if (place.menuItems.size() == 0) {
            ((TextView) holder.menuItems.get(0).findViewById(R.id.food_name)).setText("Menüü puudub");
            ((TextView) holder.menuItems.get(0).findViewById(R.id.food_price)).setText("");

            for (int i = 1; i < holder.menuItems.size(); i++) {
                holder.menuItems.get(i).setVisibility(View.GONE);
            }

            return;
        }

        for (int i = 0; i < holder.menuItems.size(); i++) {
            View item = holder.menuItems.get(i);

            try {
                FoodPlaceMenuItem menuItem = place.menuItems.get(i);

                String name = menuItem.name;
                String price = String.format("%.2f €", menuItem.price);

                ((TextView) item.findViewById(R.id.food_name)).setText(name);
                ((TextView) item.findViewById(R.id.food_price)).setText(price);

                item.setVisibility(View.VISIBLE);
            } catch (IndexOutOfBoundsException e){
                item.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return foodPlaces.size();
    }

    public class FoodPlaceViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.food_place_name)
        TextView name;

        @BindViews({ R.id.food_menu_item_1, R.id.food_menu_item_2, R.id.food_menu_item_3, R.id.food_menu_item_4, R.id.food_menu_item_5})
        List<View> menuItems;

        public FoodPlaceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
