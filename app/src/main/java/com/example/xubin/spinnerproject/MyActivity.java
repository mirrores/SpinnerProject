package com.example.xubin.spinnerproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
//import android.widget.CalendarView.OnDateChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;
import android.widget.DatePicker.OnDateChangedListener;

public class MyActivity extends Activity {

    private Spinner city=null;
    private TextView info=null;
    private Spinner area=null;
    private String[][] araeData=new String[][]{
            {"西湖","江干","拱墅","下城"},
            {"浦东","宝山"},
            {"双牌"}
    };

    private ArrayAdapter<CharSequence> adapterArea=null;

    private EditText input=null;
    private DatePicker date=null;
    private TimePicker time=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        this.city=(Spinner)super.findViewById(R.id.city);
        this.info=(TextView)super.findViewById(R.id.info);
        this.area=(Spinner)super.findViewById(R.id.area);
        this.city.setOnItemSelectedListener(new OnItemSelectedListenerImp());

        this.input=(EditText)super.findViewById(R.id.input);
        this.date=(DatePicker)super.findViewById(R.id.date);
        this.time=(TimePicker)super.findViewById(R.id.time);
        this.time.setIs24HourView(true);
        this.time.setOnTimeChangedListener(new OnTimeChangedListenerImp());
        this.date.init(this.date.getYear(),
                this.date.getMonth(),
                this.date.getDayOfMonth(),
                new OnDateChangedListenerImp());
        this.setDateTime();
    }

    private class  OnTimeChangedListenerImp implements OnTimeChangedListener{
        public void onTimeChanged(TimePicker view,int hourOfDay,int minute){
            MyActivity.this.setDateTime();

        }
    }

    private class OnDateChangedListenerImp implements OnDateChangedListener{
        public void onDateChanged(DatePicker view,int year,int monthOfYear,int dayOfMonth){
            MyActivity.this.setDateTime();

        }
    }
    public void setDateTime(){
        this.input.setText(this.date.getYear()+"-"+(this.date.getMonth()+1)+"-"+this.date.getDayOfMonth()
        +" "+this.time.getCurrentHour()+"-"+this.time.getCurrentMinute());
    }

    private class OnItemSelectedListenerImp implements OnItemSelectedListener{

        public void onItemSelected(AdapterView<?> parent,View view,
                                   int position,long id){
          //  String value=parent.getItemAtPosition(position).toString();
            MyActivity.this.adapterArea=new ArrayAdapter<CharSequence>(MyActivity.this,android.R.layout.simple_spinner_item,
                    MyActivity.this.araeData[position]);
            MyActivity.this.area.setAdapter(MyActivity.this.adapterArea);
            //MyActivity.this.info.setText("你喜欢的城市是："+value);

        }
        public void onNothingSelected(AdapterView<?> argo){

        }
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
}
