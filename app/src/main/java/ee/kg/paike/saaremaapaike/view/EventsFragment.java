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
import ee.kg.paike.saaremaapaike.MainActivity;
import ee.kg.paike.saaremaapaike.R;
import ee.kg.paike.saaremaapaike.model.Event;
import ee.kg.paike.saaremaapaike.presenter.eventlist.EventsListsAdapter;

public class EventsFragment extends Fragment {

    @BindView(R.id.events_fragment_recycleview)
    RecyclerView recyclerView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events, container, false);
        ButterKnife.bind(this, view);

        //kuna see setting ei muuda layouti texti suurust, siis performance on parem
        recyclerView.setHasFixedSize(true);

        //määrab, kuidas kuvada erinevaid ridu
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setAdapter(new EventsListsAdapter((MainActivity) getActivity()));
        new DownloadWebpageAsyncTask().execute("http://saaremaasuvi.ee/");
        return view;
    }

    private class DownloadWebpageAsyncTask extends AsyncTask<String, String, String> {

        List<Event> eventList = new ArrayList<>();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);

        }

        protected String doInBackground(String... urls) {
            try {
                Document document = Jsoup.connect(urls[0]).get();
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

                    eventList.add(new Event(link, day, date, heading, location, category));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressBar.setVisibility(View.GONE);
            ((EventsListsAdapter) recyclerView.getAdapter()).addEvents(eventList);
        }
    }
}
