package com.example.patientsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static PatientAdapter patientAdapter;
    FloatingActionButton floatingActionButton;

    static View.OnClickListener versionClickListener;
    private static ArrayList<PatientModel> dataModel;
    DbHelper dbHelper;

    private static ArrayList<Integer> removedItems;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataModel = new ArrayList<>();
        dbHelper = new DbHelper(this);
        sharedPreferences = getSharedPreferences("PatientApp", MODE_PRIVATE);

        Log.d("Data",""+sharedPreferences.getAll());

        Gson gson = new Gson();

        Map<String,?> keys = sharedPreferences.getAll();

//        for(Map.Entry<String,?> entry : keys.entrySet()){
//            PatientModel obj = gson.fromJson(entry.getValue().toString(), PatientModel.class);
//            dataModel.add(obj);
//        }

        //floatingbtn
        FloatingActionButton floatingActionButton=findViewById(R.id.btn_floating);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this,PatientsDetail.class);
                startActivity(intent);
            }
        });

        //recyclerview
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        dataModel.clear();
        dataModel.addAll(dbHelper.getAllPatients());
        patientAdapter = new PatientAdapter(dataModel);
        recyclerView.setAdapter(patientAdapter);

      //  versionClickListener = new RecyclerClick(getApplicationContext());
        removedItems = new ArrayList<Integer>();
    }


 //   private static class RecyclerClick implements View.OnClickListener {

   //     private final Context context;

    private void fetchPatientList() {

    }

}

