package ee.kg.paike.saaremaapaike.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import ee.kg.paike.saaremaapaike.MainActivity;
import ee.kg.paike.saaremaapaike.R;

public class EventsDetailsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events_details, container, false);
        ButterKnife.bind(this, view);
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
