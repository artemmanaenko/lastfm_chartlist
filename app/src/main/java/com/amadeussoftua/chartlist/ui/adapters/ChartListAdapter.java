package com.amadeussoftua.chartlist.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amadeussoftua.chartlist.R;
import com.amadeussoftua.chartlist.network.data.Artist;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Artem on 26.07.2014.
 */
public class ChartListAdapter extends BaseAdapter {

    private final int MEDIUM_IMAGE_INDEX = 1;

    private List<Artist> artistList;
    private Context context;

    public ChartListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        if (artistList == null)
            return 0;
        else
            return artistList.size();
    }

    @Override
    public Object getItem(int position) {
        return artistList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_list_artist, null);
            viewHolder = createViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Artist artist = (Artist) getItem(position);
        if (artist.getRank() != null)
            viewHolder.rank.setText(Integer.toString(artist.getRank().getRank()));
        viewHolder.name.setText(artist.getName());
        viewHolder.listeners.setText(context.getString(R.string.item_listeners) + artist.getListeners());
        String imageUrl = artist.getImages().get(MEDIUM_IMAGE_INDEX).getUrl();
        Picasso.with(context).load(imageUrl).into(viewHolder.avatar);
        return view;
    }

    protected ViewHolder createViewHolder(View view) {
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.avatar = (ImageView) view.findViewById(R.id.artist_image);
        viewHolder.name = (TextView) view.findViewById(R.id.artist_name);
        viewHolder.listeners = (TextView) view.findViewById(R.id.artist_listeners);
        viewHolder.rank = (TextView) view.findViewById(R.id.artist_rank);
        return viewHolder;
    }

    private class ViewHolder {
        public TextView rank;
        public ImageView avatar;
        public TextView listeners;
        public TextView name;
    }


    public void setArtistList(List<Artist> artistList) {
        this.artistList = artistList;
        notifyDataSetChanged();
    }

    public void clearItems() {
        if (artistList != null) {
            artistList.clear();
            notifyDataSetChanged();
        }
    }

}
