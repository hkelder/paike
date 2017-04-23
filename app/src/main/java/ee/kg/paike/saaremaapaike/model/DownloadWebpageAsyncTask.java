package ee.kg.paike.saaremaapaike.model;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DownloadWebpageAsyncTask extends AsyncTask<String, String, String> {

    List<Event> eventList = new ArrayList<>();

    protected String doInBackground(String... urls) {
        try {
            Document document = Jsoup.connect(urls[0]).get();
            Element uritused = document.getElementById("uritused");
            Elements uritusedList = uritused.select("li");

            for (Element element : uritusedList) {
                String link = element.select("a").first().attr("href");
                String day = element.getElementById("paevanimi").text();
                Log.d("!!!!!!!!!!!!!!!!", day);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(String result) {
    }
}
