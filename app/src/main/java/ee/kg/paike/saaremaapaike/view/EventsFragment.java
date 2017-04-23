package ee.kg.paike.saaremaapaike.view;

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
import ee.kg.paike.saaremaapaike.R;
import ee.kg.paike.saaremaapaike.model.DownloadWebpageAsyncTask;
import ee.kg.paike.saaremaapaike.model.Event;
import ee.kg.paike.saaremaapaike.presenter.eventlist.EventListPresenter;
import ee.kg.paike.saaremaapaike.presenter.eventlist.EventsListsAdapter;

public class EventsFragment extends Fragment {

    @BindView(R.id.events_fragment_recycleview)
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events, container, false);
        ButterKnife.bind(this, view);

        new DownloadWebpageAsyncTask().execute("http://saaremaasuvi.ee/");
        //new EventListPresenter(this).getMainPageHtml();

        //kuna see setting ei muuda layouti texti suurust, siis performance on parem
        recyclerView.setHasFixedSize(true);

        //määrab, kuidas kuvada erinevaid ridu
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<Event> eventsList = new ArrayList();
        /*Event event = new Event();
        event.heading = "Placeholder";
        event.category = "Category";
        event.date = "Date";
        event.day = "Day";
        event.location = "Location";
        eventsList.add(event);    see võrdub alumine rida*/

        /*eventsList.add(new Event("Esmaspäev", "27.03", "Coca-cola plaza", "Tallinn", "Film"));
        eventsList.add(new Event("Pühapeav", "26.03", "Kaabeltau - saalijalgpalli Liiga mängud", "Kuressaare Spordikeskus", "Kategooria:Sport"));
        eventsList.add(new Event("Esmaspäev", "27.03", "Tüdrukute käsitöötuba 'Salamärkmik'", "Saaremaa Veski", "Kategooria:Õpituba"));
        eventsList.add(new Event("Esmaspäev", "27.03", "Doonoripäev", "Kuressaare Kutuurikeskus", "Kategooria: Muu"));
        eventsList.add(new Event("Reede", "07.07", "Kiiktoolitund Merle Palmistega", "Kohvik Maiasmokk", "Kategooria: Muu"));
        eventsList.add(new Event("Esmaspäev", "27.03", "Coca-cola plaza", "Tallinn", "Film"));
        eventsList.add(new Event("Pühapeav", "26.03", "Kaabeltau - saalijalgpalli Liiga mängud", "Kuressaare Spordikeskus", "Kategooria:Sport"));
        eventsList.add(new Event("Esmaspäev", "27.03", "Tüdrukute käsitöötuba 'Salamärkmik'", "Saaremaa Veski", "Kategooria:Õpituba"));
        eventsList.add(new Event("Esmaspäev", "27.03", "Doonoripäev", "Kuressaare Kutuurikeskus", "Kategooria: Muu"));
        eventsList.add(new Event("Reede", "07.07", "Kiiktoolitund Merle Palmistega", "Kohvik Maiasmokk", "Kategooria: Muu"));
        eventsList.add(new Event("Esmaspäev", "27.03", "Coca-cola plaza", "Tallinn", "Film"));
        eventsList.add(new Event("Pühapeav", "26.03", "Kaabeltau - saalijalgpalli Liiga mängud", "Kuressaare Spordikeskus", "Kategooria:Sport"));
        eventsList.add(new Event("Esmaspäev", "27.03", "Tüdrukute käsitöötuba 'Salamärkmik'", "Saaremaa Veski", "Kategooria:Õpituba"));
        eventsList.add(new Event("Esmaspäev", "27.03", "Doonoripäev", "Kuressaare Kutuurikeskus", "Kategooria: Muu"));
        eventsList.add(new Event("Reede", "07.07", "Kiiktoolitund Merle Palmistega", "Kohvik Maiasmokk", "Kategooria: Muu"));
        eventsList.add(new Event("Esmaspäev", "27.03", "Coca-cola plaza", "Tallinn", "Film"));
        eventsList.add(new Event("Pühapeav", "26.03", "Kaabeltau - saalijalgpalli Liiga mängud", "Kuressaare Spordikeskus", "Kategooria:Sport"));
        eventsList.add(new Event("Esmaspäev", "27.03", "Tüdrukute käsitöötuba 'Salamärkmik'", "Saaremaa Veski", "Kategooria:Õpituba"));
        eventsList.add(new Event("Esmaspäev", "27.03", "Doonoripäev", "Kuressaare Kutuurikeskus", "Kategooria: Muu"));
        eventsList.add(new Event("Reede", "07.07", "Kiiktoolitund Merle Palmistega", "Kohvik Maiasmokk", "Kategooria: Muu"));
        */


        recyclerView.setAdapter(new EventsListsAdapter(getActivity(), eventsList));
        return view;
    }
}
