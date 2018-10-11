package com.example.pc.time_date_picker;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.SimpleTimeZone;

public class MainActivity extends AppCompatActivity {

    EditText edt1,edt2;
    Button button1,button2,button3;
    TextView txt1,txt2;
    ListView L1;
    ArrayList<String> arrayList;
    String[] day;
    String[] noi_dung;
    String[] thoi_gian;
    int dem=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt1=findViewById(R.id.edit1);
        edt2=findViewById(R.id.edit2);
        button1=findViewById(R.id.btn1);
        button2=findViewById(R.id.btn2);
        button3=findViewById(R.id.btn3);
        txt1=findViewById(R.id.ngay_thang);
        txt2=findViewById(R.id.thoigian);
        L1=findViewById(R.id.list);
        noi_dung=new String[100];
        day=new String[100];
        thoi_gian=new String[100];
        arrayList=new ArrayList<>();
        final ArrayAdapter adapter= new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_activated_1,arrayList);
        L1.setAdapter(adapter);

        L1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                edt2.setText(noi_dung[position]);
                txt1.setText(day[position]);
                txt2.setText(thoi_gian[position]);
            }
        });

        L1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                arrayList.remove(position);
                adapter.notifyDataSetChanged();
                return false;
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChonNgay();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChonGio();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a=edt1.getText().toString();
                String b=txt1.getText().toString();
                String c=txt2.getText().toString();
                arrayList.add(a+"-"+b+"-"+c);
                adapter.notifyDataSetChanged();
                dem+=1;
                noi_dung[dem]=a;
                day[dem]=b;
                thoi_gian[dem]=c;
            }
        });
    }
    private void ChonNgay(){
        final Calendar calendar= Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog=new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);
                SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd/MM/yyyy");
                txt1.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, nam,thang,ngay);
        datePickerDialog.show();
    }
    private  void ChonGio(){
        final Calendar calendar1= Calendar.getInstance();
        int gio=calendar1.get(Calendar.HOUR_OF_DAY);
        int phut=calendar1.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog=new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar1.set(0,0,0,hourOfDay,minute);
                SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("HH:mm");
                if(hourOfDay>=12)
                txt2.setText(simpleDateFormat1.format(calendar1.getTime())+" PM");
                else txt2.setText(simpleDateFormat1.format(calendar1.getTime())+" AM");
            }
        },gio,phut,true);
        timePickerDialog.show();
    }
}
