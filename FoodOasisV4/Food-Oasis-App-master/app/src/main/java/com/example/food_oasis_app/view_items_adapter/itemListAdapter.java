package com.example.food_oasis_app.view_items_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.food_oasis_app.R;
import com.example.food_oasis_app.VendorInformation;

import java.util.ArrayList;

public class itemListAdapter extends ArrayAdapter<VendorInformation> {

    private static final String TAG = "PersonListAdapter";

    private Context mContext;
    private int mResource;


    /**
     * Holds variables in a View
     */
    private static class ViewHolder {
        TextView item;
        TextView price;
        TextView quantity;
        TextView itemKey;
    }

    /**
     * Default constructor for the PersonListAdapter
     * @param context
     * @param resource
     * @param objects
     */
    public itemListAdapter (Context context, int resource, ArrayList<VendorInformation> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the persons information
        String item = getItem(position).getItem();
        String quantity = getItem(position).getQuantity();
        String price = getItem(position).getPrice();
        String key = getItem(position).getItemKey();

        VendorInformation item1 = new VendorInformation(item,price,quantity);

        //ViewHolder object
        ViewHolder holder;


        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder= new ViewHolder();
            holder.item = (TextView) convertView.findViewById(R.id.text_1);
            holder.quantity = (TextView) convertView.findViewById(R.id.text_2);
            holder.price = (TextView) convertView.findViewById(R.id.text_3);
            holder.itemKey = (TextView) convertView.findViewById(R.id.hiddenTextview);

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }


        holder.item.setText(item1.getItem());
        holder.quantity.setText("Quantity: " +item1.getQuantity());
        holder.price.setText("Price: " + item1.getPrice());
        holder.itemKey.setText(item1.getItemKey());


        return convertView;
    }


}
