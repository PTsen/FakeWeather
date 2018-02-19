package be.ecam.a16333.fakeweather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Sen on 6/02/2018.
 */

public class Adaptater extends RecyclerView.Adapter<Adaptater.ItemAdapterViewHolder> {

        private String[] mData = null;
	public Adaptater() {

        }

        public class ItemAdapterViewHolder
                extends RecyclerView.ViewHolder {

            public final TextView mTextView;
            public ItemAdapterViewHolder(View view) {
                super(view);
                mTextView = (TextView) view.findViewById(R.id.weather_);
            }
        }

        @Override
        public ItemAdapterViewHolder onCreateViewHolder
        (ViewGroup viewGroup, int viewType) {

            Context context = viewGroup.getContext();
            int layoutIdForListItem = R.layout.activity_weather_list;
            LayoutInflater inflater = LayoutInflater.from(context);
            boolean shouldAttachToParentImmediately = false;

            View view = inflater.inflate(layoutIdForListItem,
                    viewGroup, shouldAttachToParentImmediately);
            return new ItemAdapterViewHolder(view);
        }

        @Override
        public void onBindViewHolder
        (ItemAdapterViewHolder itemAdapterViewHolder, int position) {

            String dataForThisItem = mData[position];
            itemAdapterViewHolder.mTextView.setText(dataForThisItem);
        }

        @Override
        public int getItemCount() {
            if (null == mData) return 0;
            return mData.length;
        }

    public void setData(String[] data) {
        mData = data;
        notifyDataSetChanged();
    }
}
