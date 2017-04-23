package ee.kg.paike.saaremaapaike.presenter.eventlist;

import ee.kg.paike.saaremaapaike.model.request.EventListRequest;
import ee.kg.paike.saaremaapaike.view.EventsFragment;
import retrofit2.Response;

public class EventListPresenter {

    EventsFragment view;

    public EventListPresenter(EventsFragment view) {
        this.view = view;
    }

    public void onMainPageHtmlResponse(Response response) {

    }

    public void getMainPageHtml() {
        EventListRequest.getMainPageHtml(this);
    }

}
