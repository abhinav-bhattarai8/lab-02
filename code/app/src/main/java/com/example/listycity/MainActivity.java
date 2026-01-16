package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    int deletePosition=-1; //index of the list item to be deleted
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        cityList=findViewById(R.id.city_list);
        String []cities= {"Edmonton", "Berlin", "Moscow"};
        dataList= new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter= new ArrayAdapter<>(this,R.layout.content,dataList);
        cityList.setAdapter(cityAdapter);

        // adding cities
        Button addCity=findViewById(R.id.add_city);
        addCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  EditText newCity=findViewById(R.id.enter_city);
                  Button submitCity=findViewById(R.id.submit_city);
                  //toggle buttons and text section to add city on/off
                  if (newCity.getVisibility()==View.GONE) {
                      newCity.setVisibility(View.VISIBLE);
                      submitCity.setVisibility(View.VISIBLE);
                  }
                  else {
                      newCity.setVisibility(View.GONE);
                      submitCity.setVisibility(View.GONE);

                  }
                  submitCity.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          String name=newCity.getText().toString();
                          if (!(name.isEmpty())) { //check if city name is an empty submission
                              dataList.add(name);
                              cityList.setAdapter(cityAdapter);
                              newCity.setText("");
                          }
                      }
                  });


            }
        });

        //select item from list and register its position
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                deletePosition=position;
            }
        });

        //delete selected position
        Button deleteCity=findViewById(R.id.delete_city);
        deleteCity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (deletePosition!=-1){
                    dataList.remove(deletePosition);
                    deletePosition=-1;
                    cityList.setAdapter(cityAdapter);

                }
            }
        });



    }

}