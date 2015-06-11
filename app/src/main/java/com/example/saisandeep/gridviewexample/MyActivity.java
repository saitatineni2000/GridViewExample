package com.example.saisandeep.gridviewexample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class MyActivity extends Activity implements AdapterView.OnItemClickListener {


    ArrayList<Country> list1=new ArrayList<Country>();
    GridView grid1;
   

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        grid1= (GridView) findViewById(R.id.gridView);
        CustomAdapter adapter= new CustomAdapter(this,list1);
        grid1.setAdapter(adapter);
        grid1.setOnItemClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent=new Intent(this,SecondActivity.class);

        MyViewHolder holder= (MyViewHolder) view.getTag();
        Country temp= (Country) holder.images.getTag();

        intent.putExtra("countryImage",temp.images);
        intent.putExtra("countryName",temp.countryName);

        startActivity(intent);
    }
}
//create a new reference or model class
class Country
{
    String countryName;
    int images;

    Country(String countryName, int images) {
        this.countryName = countryName;
        this.images = images;
    }
}

class MyViewHolder{
    ImageView images;
    TextView name;

    MyViewHolder(View v)
    {
        images= (ImageView) v.findViewById(R.id.imageView);
        name= (TextView) v.findViewById(R.id.textView);
    }

}


class CustomAdapter extends BaseAdapter{
ArrayList<Country> list;
    //better to use arraylist instead of array because the getcount methods all require the sizes etc
Context context;
    CustomAdapter(Context contex, ArrayList<Country> listadapter)
    {this.context=contex;
        list=new ArrayList<Country>();//this list is empty we need to keep in values into this
        this.list=listadapter;

        //procees to keep values into the arraylist
        Resources res=contex.getResources();
        String[] tempnames=res.getStringArray(R.array.country);
        int[] images={R.drawable.about_top_bg,R.drawable.app_icon,R.drawable.bg_gettingstarted,R.drawable.about_top_bg,R.drawable.app_icon};

        for(int i=0;i<5;i++)
        {

            Country countryDetails=new Country(tempnames[i],images[i]);
            list.add(countryDetails);
        }
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

   /* class MyViewHolder{
        ImageView images;
        TextView name;

        MyViewHolder(View v)
        {
            images= (ImageView) v.findViewById(R.id.imageView);
            name= (TextView) v.findViewById(R.id.textView);
        }

    }*/

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View row=view;
        MyViewHolder holder=null;
        if(row == null)
        {//need a new object every time use a layoutinflater however if u want use a same object every time use findviewbyid
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(R.layout.single_row,viewGroup,false);
            holder=new MyViewHolder(row);
            row.setTag(holder);
        }

        else
        {
            holder= (MyViewHolder) row.getTag();
        }

        Country tempname=list.get(i);
        holder.images.setImageResource(tempname.images);
        holder.name.setText(tempname.countryName);

        holder.images.setTag(tempname);
        //holder.name.setTag(tempname);
        return row;
    }
}