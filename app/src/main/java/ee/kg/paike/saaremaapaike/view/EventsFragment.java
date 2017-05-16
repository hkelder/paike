package ee.kg.paike.saaremaapaike.view;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ee.kg.paike.saaremaapaike.MainActivity;
import ee.kg.paike.saaremaapaike.R;
import ee.kg.paike.saaremaapaike.model.Event;
import ee.kg.paike.saaremaapaike.pojo.ParallaxImageData;
import ee.kg.paike.saaremaapaike.presenter.eventlist.EventsListsAdapter;

public class EventsFragment extends Fragment {

    public static String imagesBaseUrl = "http://saaremaasuvi.ee/wp-content/themes/saaremaasuvi/";

    @BindView(R.id.events_observable_scroll_view)
    ObservableScrollView scrollView;

    @BindView(R.id.events_fragment_recycleview)
    RecyclerView recyclerView;

    @BindView(R.id.images_container)
    RelativeLayout imageContainer;

    ImageView headerImage;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events, container, false);
        ButterKnife.bind(this, view);

        headerImage = (ImageView) imageContainer.findViewById(R.id.header_image);

        ParallaxImageData parallaxImageData = new ParallaxImageData();
        parallaxImageData.mScrollView = scrollView;
        parallaxImageData.mImageContainer = imageContainer;
        parallaxImageData.mLogoView = imageContainer.findViewById(R.id.header_logo);

        ((MainActivity)getActivity()).setScrollViewCallbacks(parallaxImageData);

        //kuna see setting ei muuda layouti texti suurust, siis performance on parem
        recyclerView.setHasFixedSize(false);

        //määrab, kuidas kuvada erinevaid ridu
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setAdapter(new EventsListsAdapter((MainActivity) getActivity()));
        new DownloadWebpageAsyncTask().execute("http://saaremaasuvi.ee/");
        return view;
    }

    public void loadHeaderImage(String url) {
        if (url == null || url.isEmpty() || headerImage == null) return;

        Glide
            .with(getActivity())
            .load(url)
            .centerCrop()
            .into(headerImage);

    }

    private class DownloadWebpageAsyncTask extends AsyncTask<String, String, String> {

        ArrayList<Event> eventList = new ArrayList<>();
        String headerImageUrl = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);

        }

        protected String doInBackground(String... urls) {
            try {
                Document css = Jsoup.connect("http://saaremaasuvi.ee/wp-content/themes/saaremaasuvi/style.css").get();
                String cssElement = getCssIdElementFromDocument(css, "taust");
                headerImageUrl = getUrlFromElement(cssElement);

                Document document = Jsoup.connect(urls[0]).get();
                parseListItemsFromHtml(document);
                parseAdsFromHtml(document);


            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressBar.setVisibility(View.GONE);
            loadHeaderImage(headerImageUrl);
            ((EventsListsAdapter) recyclerView.getAdapter()).addEvents(eventList);
        }

        private String getCssIdElementFromDocument(Document document, String elementName) {
            String[] cssElements = document.toString().split("#");
            for (String element : cssElements) {
                if (element.startsWith(elementName)) return element;
            }
            return null;
        }

        private String getUrlFromElement(String element) {
            String[] parameters = element.split(";");
            for (String parameter : parameters) {
                if (parameter.contains("background:url"))
                    return imagesBaseUrl + parameter.substring(parameter.indexOf("(") + 1, parameter.indexOf(")"));
            }
            return null;
        }

        private void parseListItemsFromHtml(Document document) {

            Element uritused = document.getElementById("uritused");
            Elements uritusedList = uritused.select("li");

            for (Element element : uritusedList) {
                String link = element.select("a").first().attr("href");
                String day = element.getElementById("paevanimi").text();
                String date = element.getElementById("kuupaev").text();
                String heading = element.getElementById("pealk").text();
                String location = element.getElementById("asukoht").text();
                String category = element.getElementById("kategooria").text();

                if (category.length() > 14) category = category.substring(14);

                eventList.add(Event.createEventItem(link, day, date, heading, location, category, headerImageUrl));
            }

        }

        private void parseAdsFromHtml(Document document) {
            List<Event> promotionList = new ArrayList<>();

            Elements slides = document.select("ul.slides > li");
            for (Element element : slides) {
                String link = element.select("a").first().attr("href");
                String img = element.select("img").first().attr("src");
                promotionList.add(Event.createPromotionItem(link, img));
            }
            addPromotionToEventList(promotionList);
        }

        private void addPromotionToEventList(List<Event> promotionList) {
            if (eventList == null || eventList.size() < 3)
                return;

            int promotionsLooperCounter = 0;
            for (int promotionsPositionCounter = 3; eventList.size() > promotionsPositionCounter; promotionsPositionCounter += 10) {
                if (promotionsLooperCounter == promotionList.size()) promotionsLooperCounter = 0;
                eventList.add(promotionsPositionCounter, promotionList.get(promotionsLooperCounter));
                promotionsPositionCounter += 10;
                promotionsLooperCounter += 1;
            }
        }
    }
}
