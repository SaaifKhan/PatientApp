package com.example.patientsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PatientsDetail extends AppCompatActivity {
    EditText ed_name, ed_fathername, ed_age;
    RadioGroup radioGroup;
    Switch patientStatus;
    Button btnsubmit;
    AppCompatSpinner spinner;
    //SpinnerListener spinnerListener;
    SpinnerListener spinnerListener;

    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients_detail);
        init();
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<String> categories = new ArrayList<String>();
                categories.add("Physician");
                categories.add("cardiologists");
                categories.add("dermatologists");
                categories.add("Psychiatrist");
                categories.add("Radiologist");

                String patientName = ed_name.getText().toString();
                String fatherName = ed_fathername.getText().toString();
                String age = ed_age.getText().toString();

                String Gender = "Male";

                if(patientName.isEmpty() || fatherName.isEmpty() || age.isEmpty()){
                    Toast.makeText(PatientsDetail.this, "Please enter complete detail", Toast.LENGTH_SHORT).show();
                }else{
                    String uniqueID = UUID.randomUUID().toString();
                    PatientModel patientModel = new PatientModel(patientName,fatherName,"",age,"","",uniqueID);

                    Gson gson = new Gson();
                    String json = gson.toJson(patientModel);

                    Boolean isPatientSave = editor.putString(uniqueID,json.toString()).commit();
                    if(isPatientSave){
                        Toast.makeText(PatientsDetail.this,"Patient Save",Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        Toast.makeText(PatientsDetail.this,"Patient Not Save",Toast.LENGTH_SHORT).show();
                    }

                    DbHelper Dbhelper = new DbHelper(getApplicationContext());
                    SQLiteDatabase database = Dbhelper.getWritableDatabase();
                    Dbhelper.insertPatient(patientName,fatherName,Gender,age,categories);
                    fetchData();
                }

            }
        });
        //spinner
        spinner = findViewById(R.id.spinner);
        spinnerListener = new SpinnerListener();
        spinner.setOnItemSelectedListener(spinnerListener);


         List<String> categories = new ArrayList<String>();
        categories.add("Physician");
        categories.add("cardiologists");
        categories.add("dermatologists");
        categories.add("Psychiatrist");
        categories.add("Radiologist");


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, categories);
        spinner.setAdapter(dataAdapter);

    }

    private void fetchData() {







    }

    private  void init(){
        editor = getSharedPreferences("PatientApp", MODE_PRIVATE).edit();

        ed_name = findViewById(R.id.name);
        ed_fathername = findViewById(R.id.fathername);
        radioGroup = findViewById(R.id.patientStatusGroup);
        spinner = findViewById(R.id.spinner);
        ed_age = findViewById(R.id.age);
        patientStatus = findViewById(R.id.status);
        btnsubmit = findViewById(R.id.btnSubmit);
    }

    private static class SpinnerListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
            String item = adapterView.getItemAtPosition(position).toString();
            Toast.makeText(adapterView.getContext(), item, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }


    }
}