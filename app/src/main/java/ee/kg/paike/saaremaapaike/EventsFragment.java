package ee.kg.paike.saaremaapaike;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventsFragment extends Fragment {

    @BindView(R.id.events_fragment_recycleview)
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events, container, false);
        ButterKnife.bind(this, view);

        //kuna see setting ei muuda layouti texti suurust, siis performance on parem
        recyclerView.setHasFixedSize(true);

        //määrab, kuidas kuvada erinevaid ridu
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setAdapter(new EventsListsAdapter(getActivity()));
        return view;
    }
}
