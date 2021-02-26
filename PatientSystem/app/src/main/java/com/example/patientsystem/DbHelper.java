package com.example.patientsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.RadioButton;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import static java.lang.String.valueOf;

public class DbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "PATIENT_DB.db";

    //TABLE FOR PATIENT RECORD
    public static final String TBL_PATIENT = "TBL_PATIENT";

    //COLUMN FOR PATIENT
    public static final String PATIENT_NAME = "PATIENT_NAME";
    public static final String PATIENT_FATHERNAME = "PATIENT_FATHERNAME";
    public static final String PATIENT_GENDER = "PATIENT_GENDER";
    public static final String PATIENT_AGE = "PATIENT_AGE";
    public static final String PATIENT_CONSULTANCYTYPE = "PATIENT_CONSULTANCYTYPE";


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    public DbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table TBL_PATIENT " +
                        "(id integer primary key, PATIENT_NAME text,PATIENT_FATHERNAME text,PATIENT_GENDER text, PATIENT_AGE text,PATIENT_CONSULTANCYTYPE text)"


        );


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS TBL_PATIENT");
        onCreate(db);
    }

    public boolean insertPatient(String PatientName, String FatherName, String PatientGender, String PatientAge, ArrayList<String> PatientConsultancyType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PATIENT_NAME, PatientName);
        contentValues.put(PATIENT_FATHERNAME, FatherName);
        contentValues.put(PATIENT_GENDER, PatientGender);
        contentValues.put(PATIENT_AGE, PatientAge);
        contentValues.put(PATIENT_CONSULTANCYTYPE, valueOf(PatientConsultancyType));
        db.insert("TBL_PATIENT", null, contentValues);
        return true;
    }

    public Cursor getDataByid(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from TBL_PATIENT where id=" + id + "", null);
        return res;
    }

//    public void getAllData(SQLiteDatabase db) {
//        db = this.getReadableDatabase();
//        Cursor res =  db.rawQuery( "select * from TBL_PATIENT ="+id+"", null );
//
//    }
    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TBL_PATIENT);
        return numRows;
    }

//    public boolean updateContact (Integer id, String name, String phone, String email, String street,String place) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("name", name);
//        contentValues.put("phone", phone);
//        contentValues.put("email", email);
//        contentValues.put("street", street);
//        contentValues.put("place", place);
//        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
//        return true;
//    }

    public ArrayList<PatientModel> getAllPatients() {
        ArrayList<PatientModel> array_list = new ArrayList<PatientModel>();
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from TBL_PATIENT", null);

//        while(res.moveToNext()){
//            String gender = res.getString(3);
//            RadioButton male = null;
//            if (gender == "male") {
//                male = male.findViewById(R.id.male);
//            } else {
//                male = male.findViewById(R.id.female);
//            }
//            male.setChecked(true);
//        }
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            PatientModel model = new PatientModel();
            model.setName(res.getString(res.getColumnIndex(PATIENT_NAME)));
            model.setFathername(res.getString(res.getColumnIndex(PATIENT_FATHERNAME)));
            model.setAge(res.getString(res.getColumnIndex(PATIENT_AGE)));
            model.setGender(res.getString(res.getColumnIndex(PATIENT_GENDER)));
            array_list.add(model);
            res.moveToNext();
        }
        return array_list;
    }
}
