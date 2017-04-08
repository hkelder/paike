package ee.kg.paike.saaremaapaike;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodFragment extends Fragment {

    @BindView(R.id.food_fragment_recycleview)
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food, container, false);
        ButterKnife.bind(this, view);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<Food> foodList = new ArrayList();

        foodList.add(new Food("Chameleon", "Saiake", "2.50$"));
        foodList.add(new Food("Arensburg Restoran", "Seapraad", "3.90$"));
        foodList.add(new Food("Hesburger", "Megaeine", "6.50$"));
        foodList.add(new Food("Chameleon", "Saiake", "2.50$"));
        foodList.add(new Food("Arensburg Restoran", "Seapraad", "3.90$"));
        foodList.add(new Food("Hesburger", "Megaeine", "6.50$"));
        foodList.add(new Food("Arensburg Restoran", "Seapraad", "3.90$"));
        foodList.add(new Food("Hesburger", "Megaeine", "6.50$"));
        foodList.add(new Food("Chameleon", "Saiake", "2.50$"));
        foodList.add(new Food("Arensburg Restoran", "Seapraad", "3.90$"));
        foodList.add(new Food("Hesburger", "Megaeine", "6.50$"));
        foodList.add(new Food("Arensburg Restoran", "Seapraad", "3.90$"));
        foodList.add(new Food("Hesburger", "Megaeine", "6.50$"));

        recyclerView.setAdapter(new FoodListsAdapter(getActivity(), foodList));


        return view;
    }
}
