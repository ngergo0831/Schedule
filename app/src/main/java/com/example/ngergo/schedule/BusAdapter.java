package com.example.ngergo.schedule;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class BusAdapter extends RecyclerView.Adapter<BusAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<BusNumber> mList;
    BusAdapter(Context context, ArrayList<BusNumber> list){
        mContext=context;
        mList=list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.jaratok_card,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BusNumber busNumber = mList.get(position);
        TextView tw1,tw2,tw3;
        tw1=holder.tw1;
        tw2=holder.tw2;
        tw3=holder.tw3;
        tw1.setText(busNumber.getName());
        tw2.setText(busNumber.getRouteNumber());
        tw3.setText(busNumber.getBus_back());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tw1,tw2,tw3;
        public View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("card:",Integer.toString(getAdapterPosition()));

                }
            });
            tw1=itemView.findViewById(R.id.twBusNumber);
            tw2=itemView.findViewById(R.id.twSor1);
            tw3=itemView.findViewById(R.id.twSor2);
        }
    }
}
