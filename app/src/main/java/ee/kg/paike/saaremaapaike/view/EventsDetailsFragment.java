package ee.kg.paike.saaremaapaike.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import ee.kg.paike.saaremaapaike.MainActivity;
import ee.kg.paike.saaremaapaike.R;
import ee.kg.paike.saaremaapaike.model.Event;

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
        heading.setText(openedEvent.heading);
        day.setText(openedEvent.day + ", " + openedEvent.date);
        category.setText(openedEvent.category);
        location.setText(openedEvent.location);
        description.setText("");
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
}
