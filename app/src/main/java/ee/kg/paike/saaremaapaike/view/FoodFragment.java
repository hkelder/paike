package ee.kg.paike.saaremaapaike.view;

import android.app.Fragment;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ee.kg.paike.saaremaapaike.R;
import ee.kg.paike.saaremaapaike.adapters.FoodPlaceAdapter;
import ee.kg.paike.saaremaapaike.model.FoodPlace;
import ee.kg.paike.saaremaapaike.model.FoodPlaceMenuItem;

public class FoodFragment extends Fragment {
    @BindView(R.id.food_fragment_recycleview)
    RecyclerView recyclerView;

    @BindView(R.id.food_progress_bar)
    ProgressBar progressBar;

    private List<FoodPlace> foodPlaces;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food, container, false);
        ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        new DownloadWebPage().execute("https://www.paevapraad.ee/kuressaare/");

        return view;
    }

    private class DownloadWebPage extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            String url = params[0];

            try {
                foodPlaces = new ArrayList<FoodPlace>();

                Document doc = Jsoup.connect(url).get();

                Elements titleElements = doc.select("div.left_column > a > div > div:eq(2)");
                Elements menuContainers = doc.select("div.left_column > a > div > div:eq(1)");

                Log.d("TITLE ELEMENTS SIZE", titleElements.size() + "");
                Log.d("MENU ELEMENTS SIZE", menuContainers.size() + "");

                for (int i = 0; i < titleElements.size(); i++) {
                    String placeName = titleElements.get(i).text();
                    List<FoodPlaceMenuItem> menuItemList = new ArrayList<FoodPlaceMenuItem>();

                    Element menuContainer = menuContainers.get(i);
                    Elements menuItems = menuContainer.select(":root > div:gt(1)");

                    Log.d("TOTAL MENU ITEMS", menuItems.size() + "");

                    for (int j = 0; j < menuItems.size(); j++) {
                        Element menuItem = menuItems.get(j);

                        String food = menuItem.select("div").first().text().replace("€", "").trim();

                        Uri uri = Uri.parse(menuItem.select("div:eq(1) > img").attr("src"));
                        String price = uri.getQueryParameter("msg");

                        if (price == null) {
                            price = "0";
                        }

                        price = price.replace(',', '.');

                        menuItemList.add(new FoodPlaceMenuItem(food, Double.parseDouble(price)));

                        Log.d("FOOD", food);
                        Log.d("PRICE", price);
                    }

                    foodPlaces.add(new FoodPlace(placeName, menuItemList));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressBar.setVisibility(View.GONE);
            recyclerView.setAdapter(new FoodPlaceAdapter(foodPlaces));
        }
    }
}
