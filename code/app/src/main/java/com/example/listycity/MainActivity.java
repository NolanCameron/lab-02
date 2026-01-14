package com.example.listycity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
    View confirmView;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    String selectedCity = null;

    // Create a message handling object as an anonymous class.
    private AdapterView.OnItemClickListener messageClickedHandler = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            selectedCity = cityAdapter.getItem(position);
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        confirmView = findViewById(R.id.LinearLayout2);

        String[] cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        cityList.setOnItemClickListener(messageClickedHandler);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void addCity(View view){
        //Log.d("MainActivity", cityList.getSelectedItem().toString());
        confirmView.setVisibility(View.VISIBLE);
    }

    public void deleteCity(View view){
        if(selectedCity == null)
            return;

        Log.d("MainActivity", selectedCity);
        cityAdapter.remove(selectedCity);
        cityAdapter.notifyDataSetChanged();
        cityList.clearChoices();
        cityList.setAdapter(cityAdapter);
        cityList.requestLayout();

    }

    public void confirmCity(View view){
        EditText text = findViewById(R.id.editTextText);
        String cityName = text.getText().toString();
        if (!cityName.isEmpty())
            cityAdapter.add(text.getText().toString());
        cityAdapter.notifyDataSetChanged();
        cityList.requestLayout();
        confirmView.setVisibility(View.GONE);
    }



}