package com.mymusictaste.scrollholdersample.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mymusictaste.scrollholdersample.R;
import com.mymusictaste.scrollholdersample.type.Sample;

import java.util.ArrayList;

/**
 * Created by JKtheUnique on 2014-11-17.
 */
public class SampleAdapter extends ArrayAdapter<Sample> {
    private Context context;
    public SampleAdapter(Context context, ArrayList<Sample> sampleList){
        super(context,R.layout.adapter_sample, sampleList);
        this.context = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder = new ViewHolder();
        if(convertView==null){
            convertView = View.inflate(getContext(), R.layout.adapter_sample, null);
            holder.thumbnail = (ImageView)convertView.findViewById(R.id.sample_thumbnail);
            holder.title = (TextView)convertView.findViewById(R.id.sample_title);
            holder.subTitle = (TextView)convertView.findViewById(R.id.sample_subtitle);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        Sample result = getItem(position);
        holder.thumbnail.setImageResource(R.mipmap.ic_launcher);
        holder.title.setText(result.getTitle());
        holder.subTitle.setText(result.getSubTitle());
        return convertView;
    }

    static class ViewHolder{
        ImageView thumbnail;
        TextView title;
        TextView subTitle;
    }
}
