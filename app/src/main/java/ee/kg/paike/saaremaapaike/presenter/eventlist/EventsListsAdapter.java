package ee.kg.paike.saaremaapaike.presenter.eventlist;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ee.kg.paike.saaremaapaike.MainActivity;
import ee.kg.paike.saaremaapaike.R;
import ee.kg.paike.saaremaapaike.model.Event;
import ee.kg.paike.saaremaapaike.view.EventsDetailsFragment;

public class EventsListsAdapter extends RecyclerView.Adapter<EventsListsAdapter.ViewHolder> {

    private MainActivity adapterContext;
    private List<Event> eventList = new ArrayList<>();


    public EventsListsAdapter(MainActivity context) {
        adapterContext = context;
    }

    public void addEvents(List<Event> eventList) {
        this.eventList = eventList;
        notifyDataSetChanged();
    }

    @Override
    public EventsListsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //teeb uue info rea
        RelativeLayout row = (RelativeLayout) LayoutInflater.from(adapterContext)
                .inflate(R.layout.events_listitem, parent, false);

        //teeb inforeast viewHolderi
        ViewHolder eventListsViewHolder = new ViewHolder(row);

        return eventListsViewHolder;
    }

    @Override
    public void onBindViewHolder(EventsListsAdapter.ViewHolder holder, int position) {

        Event event = eventList.get(position);
        Log.d("!!!!!!!!!", event.toString());
        if (event.type == Event.ListItemType.EVENT) {
            holder.imageView.setVisibility(View.GONE);
            holder.eventLayout.setVisibility(View.VISIBLE);
            holder.day.setText(event.day);
            holder.date.setText(event.date);
            holder.heading.setText(event.heading);
            holder.location.setText(event.location);
            holder.category.setText(event.category);
        } else if (event.type == Event.ListItemType.PROMOTION) {
            holder.imageView.setVisibility(View.VISIBLE);
            holder.eventLayout.setVisibility(View.GONE);
            if (event.promotionLink == null || event.promotionLink.isEmpty())
                return;
            Glide.with(adapterContext).load(event.promotionLink).into(holder.imageView);
        }

    }

    @Override
    public int getItemCount() {
        return eventList.size();
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
        @BindView(R.id.events_promotion_image)
        ImageView imageView;
        @BindView(R.id.events_listitem_event_layout)
        LinearLayout eventLayout;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position == RecyclerView.NO_POSITION) return;
                    Event clickedEvent = eventList.get(position);
                    if (clickedEvent.type == Event.ListItemType.EVENT) {
                        EventsDetailsFragment fragment = EventsDetailsFragment.newInstance(clickedEvent);
                        adapterContext.openFragment(fragment, true);
                    } else if (clickedEvent.type == Event.ListItemType.PROMOTION) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(clickedEvent.promotionUrl));
                        adapterContext.startActivity(browserIntent);
                    }


                }
            });
        }
    }
}
