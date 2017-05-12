package ee.kg.paike.saaremaapaike.view;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import ee.kg.paike.saaremaapaike.MainActivity;
import ee.kg.paike.saaremaapaike.R;
import ee.kg.paike.saaremaapaike.model.Event;
import ee.kg.paike.saaremaapaike.utils.HtmlUtil;

public class EventsDetailsFragment extends Fragment {

    private static final String ARG_KEY_EVENT = "ARG_KEY_EVENT";
    @BindView(R.id.events_details_heading)
    TextView heading;
    @BindView(R.id.events_details_day)
    TextView day;
    @BindView(R.id.events_details_category)
    TextView category;
    @BindView(R.id.events_details_location)
    TextView location;
    @BindView(R.id.event_details_text)
    TextView description;
    @BindView(R.id.event_details_progressbar)
    ProgressBar progressBar;
    @BindView(R.id.events_details_header_image)
    ImageView headerImage;
    private Event openedEvent;

    public static EventsDetailsFragment newInstance(Event event) {
        EventsDetailsFragment fragment = new EventsDetailsFragment();
        Bundle arguments = new Bundle();
        arguments.putString(ARG_KEY_EVENT, new Gson().toJson(event));
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openedEvent = new Gson().fromJson(getArguments().getString(ARG_KEY_EVENT), Event.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events_details, container, false);
        ButterKnife.bind(this, view);
        Glide.with(this).load(openedEvent.imageUrl).fitCenter()
                .placeholder(R.drawable.bg_kevad).error(R.drawable.bg_kevad).into(headerImage);
        heading.setText(openedEvent.heading);
        day.setText(openedEvent.day + ", " + openedEvent.date);
        category.setText(openedEvent.category);
        location.setText(openedEvent.location);
        description.setText("");
        new GetEventDetailsAsyncTask().execute(openedEvent.link);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).showBackArrow(true);
    }

    @Override
    public void onPause() {
        super.onPause();
        ((MainActivity) getActivity()).showBackArrow(false);
    }

    private class GetEventDetailsAsyncTask extends AsyncTask<String, String, String> {

        String detailsResults = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... urls) {
            try {
                Document document = Jsoup.connect(urls[0]).get();
                Element sisu = document.getElementById("sisu-single");
                Elements children = sisu.children();
                children.remove(0);
                children.remove(0);
                for (Element paragraph : children) {
                    detailsResults += paragraph.toString() + "\n";
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(View.GONE);
            description.setText(HtmlUtil.fromHtml(detailsResults));
        }
    }
}

