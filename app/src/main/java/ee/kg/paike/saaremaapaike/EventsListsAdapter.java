package ee.kg.paike.saaremaapaike;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

class EventsListsAdapter extends RecyclerView.Adapter<EventsListsAdapter.ViewHolder> {

    Context adapterContext;


    public EventsListsAdapter(Context context) {
        adapterContext = context;

    }

    @Override
    public EventsListsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //teeb uue info rea
        LinearLayout row = (LinearLayout) LayoutInflater.from(adapterContext)
                .inflate(R.layout.events_lists, parent, false);

        //teeb inforeast viewHolderi
        ViewHolder eventListsViewHolder = new ViewHolder(row);

        return eventListsViewHolder;
    }

    @Override
    public void onBindViewHolder(EventsListsAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        //teeb vaated taaskasutatavaks
        @BindView(R.id.events_listitem_day)
        TextView day;
        @BindView(R.id.events_listitem_date)
        TextView date;
        @BindView(R.id.events_listitem_heading)
        TextView heading;
        @BindView(R.id.events_listitem_location)
        TextView location;
        @BindView(R.id.events_listitem_category)
        TextView category;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }
}
