package ideallions.sayhello;

/**
 * Created by jose de corea on 2017-02-05.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class NationAdapter extends RecyclerView.Adapter<NationAdapter.ItemViewHolder> {

    private Context mContext;
    private ArrayList<SingleItem> originDB;


    public NationAdapter(Context context, ArrayList<SingleItem> aarSrc) {
        mContext = context;
        originDB = aarSrc;
     //   thisDB = new ArrayList<>();
  //      thisDB.addAll(originDB);
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
//        originDB.clear();
        if (charText.length() == 0) {
       //     originDB.addAll(thisDB);
        } else {
           ArrayList<SingleItem> thisDB = new ArrayList<>();
           thisDB.addAll(originDB);
            originDB.clear();
            for (SingleItem wp : thisDB) {
                if (wp.getTitle().toLowerCase(Locale.getDefault()).contains(charText)) {
                    originDB.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
    public void changeLanguage(ArrayList<SingleItem> aarSrc) {
        originDB.clear();
     //   thisDB.clear();
        originDB = aarSrc;
     //   thisDB.addAll(originDB);
        notifyDataSetChanged();
    }

    @Override
    public NationAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.single_item, parent, false);

        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(NationAdapter.ItemViewHolder holder, final int position) {

        holder.conFlagIv.setImageResource(originDB.get(position).getIcon());
        holder.conNameTv.setText(originDB.get(position).getTitle());

        holder.conNameTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(mContext, HelloActivity.class);
                intent.putExtra("nation_code", (originDB.get(position).getNcode()));
                mContext.startActivity(intent);
            }
        });
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView conFlagIv;    // flag of a country
        TextView conNameTv;     // name of a country

        public ItemViewHolder(View itemView) {
            super(itemView);

            conFlagIv = (ImageView) itemView.findViewById(R.id.imageView1);
            conNameTv = (TextView) itemView.findViewById(R.id.textView1);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return originDB.size();
    }
}

