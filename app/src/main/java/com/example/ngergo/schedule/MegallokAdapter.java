package com.example.ngergo.schedule;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.text.Collator;
import java.text.ParseException;
import java.text.RuleBasedCollator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

public class MegallokAdapter extends RecyclerView.Adapter<MegallokAdapter.ViewHolder> {

    private Context mContext;
    private List<Megallok> mList;
    private List<String> mList2;
    private HashSet<String> hs;
    MegallokAdapter(Context context, ArrayList<Megallok> list) {
        mContext=context;
        mList=list;
        hs = new HashSet<>();
        for(Megallok m : mList) {
            hs.add(m.getStop());
        }
        mList.clear();
        mList2 = new ArrayList<>(hs);
        String hunRules = ("< 0 < 1 < 2 < 3 < 4 < 5 < 6 < 7 < 8 < 9 < a,A < á,Á < b,B < c,C < cs,Cs,CS < d,D < dz,Dz,DZ < dzs,Dzs,DZS "
                + " < e,E < é,É < f,F < g,G < gy,Gy,GY < h,H < i,I < í,Í < j,J"
                + " < k,K < l,L < ly,Ly,LY < m,M < n,N < ny,Ny,NY < o,O < ó,Ó "
                + " < ö,Ö < ő,Ő < p,P < q,Q < r,R < s,S < sz,Sz,SZ < t,T "
                + " < ty,Ty,TY < u,U < ú,Ú < ü,Ü < ű,Ű < v,V < w,W < x,X < y,Y < z,Z < zs,Zs,ZS");
        String words[] =mList2.toArray(new String[mList2.size()]);
        try {
            RuleBasedCollator huCollator = new RuleBasedCollator(hunRules);
            sortStrings(huCollator, words);
        } catch (ParseException pe) {
            Log.d("teszt",pe.getMessage());
        }
        mList2.clear();
        mList2=Arrays.asList(words);
        for(String s : mList2)
        {
            mList.add(new Megallok(s));
        }
        Collections.sort(mList, new Comparator<Megallok>() {
            @Override
            public int compare(final Megallok object1, final Megallok object2) {
                return object1.getStop().compareTo(object2.getStop());
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.megallok_card,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Megallok megallok = mList.get(position);
        TextView tw1;
        tw1=holder.tw1;
        tw1.setText(megallok.getStop());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tw1;
        public View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("card:","onClick works");
                }
            });
            tw1=itemView.findViewById(R.id.textMegalloID);
        }
    }

    public static void sortStrings(Collator collator, String[] words) {
        String tmp;
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (collator.compare(words[i], words[j]) > 0) {
                    tmp = words[i];
                    words[i] = words[j];
                    words[j] = tmp;
                }
            }
        }
    }

}
