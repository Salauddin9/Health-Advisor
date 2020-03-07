package com.example.salauddinrubel.healthadviser;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DatabaseHelper  extends SQLiteOpenHelper {

    public static String database_name = "HealthAdviser.db";
    private static final int database_version = 1;
    private static final String table_user = "users";
    private static final String table_info = "info";
    private static final String key_id = "id";
    private static final String key_fnam = "fname";
    private static final String key_lnam = "lname";
    private static final String key_user_name = "user_name";
    private static final String key_password = "user_password";
    private static final String info_id = "id";
    private static final String info_uname = "info_user_name";
    private static final String info_gender = "gender";
    private static final String info_age = "age";
    private static final String info_weight = "weight";
    private static final String info_feet = "feet";
    private static final String info_inch = "inch";
    private static final String info_bmr = "bmr";
    private static final String info_bmi = "bmi";
    private Context context;

    private static final String create_table_user = "create table "  + table_user + "( " +key_id +" integer primary key autoincrement, " +key_fnam+" TEXT not null, " +key_lnam+" TEXT not null, "  + key_user_name + " TEXT not null, " + key_password + " TEXT not null); ";
    private static final String create_table_info = "create table "+table_info+"( "+info_id+" integer primary key autoincrement, " +info_uname+" TEXT not null, "+info_gender +" TEXT not null, "+info_age+" TEXT not null, "+info_weight+" TEXT not null, "+info_feet+" TEXT not null, "+info_inch+" TEXT not null, "+info_bmr+" TEXT not null, "+info_bmi+" TEXT not null); ";

    private static final String DROP_TABLE = " DROP TABLE IF EXISTS "+table_user;
    private static final String DROP_TABLE_INFO = " DROP TABLE IF EXISTS "+table_info;

    public DatabaseHelper(Context context){
        super(context, database_name, null, database_version);
        this.context = context;

    }


   // Intent intn ;
   // String usa = intn.getExtras().getString("Username");

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(DROP_TABLE);
            db.execSQL(DROP_TABLE_INFO);
            db.execSQL(create_table_user);
            db.execSQL(create_table_info);

        }catch(Exception e){
            Toast.makeText(context, "Exception: "+e, Toast.LENGTH_LONG).show();

        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{

            db.execSQL(DROP_TABLE);
            db.execSQL(DROP_TABLE_INFO);
            onCreate(db);
        }catch (Exception e){
            Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
        }
    }


    public  long insertData(UserDetails userDetails){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(key_fnam, userDetails.getFirstname());
        contentValues.put(key_lnam, userDetails.getLastname());
        contentValues.put(key_user_name, userDetails.getUsername());
        contentValues.put(key_password, userDetails.getPassword());


        long rowId = db.insert(table_user, null, contentValues);
        return rowId;

    }

    public void insertInfo(UserInfo userInfo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues ct2 = new ContentValues();






            ct2.put(info_id, userInfo.getId());
            ct2.put(info_uname, userInfo.getUsername());
            ct2.put(info_gender, userInfo.getGender());
            ct2.put(info_age, userInfo.getAge());
            ct2.put(info_weight, userInfo.getWeight());
            ct2.put(info_feet, userInfo.getFeet());
            ct2.put(info_inch, userInfo.getInch());
            ct2.put(info_bmi, userInfo.getBmi());
            ct2.put(info_bmr, userInfo.getBmr());





       // long id = db.insertWithOnConflict(table_info, null, ct2, SQLiteDatabase.CONFLICT_IGNORE);

       long rowId2 = db.insert(table_info, null, ct2);
        //return rowId2;

    }






    public Boolean findPassword(String uname, String upass){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+table_user,null);
        Boolean result = false;

        if(cursor.getCount()==0){
            Toast.makeText(context, "No data is founded", Toast.LENGTH_LONG).show();

        }else{
            while(cursor.moveToNext()){
                String username = cursor.getString(3);
                String password = cursor.getString(4);

                if(username.equals(uname) && password.equals(upass)){
                    result = true;
                    break;
                }

            }
        }


        return result;
    }



    public Cursor getAllUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + table_user,  null);

        return res;
    }

    public Cursor getAllUserInfo(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res2 = db.rawQuery("select * from "+table_info, null);
        return res2;
    }

    public Cursor showMyInfo(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res3 = db.rawQuery("select * from "+ table_user + " inner join "+ table_info+" on user_name = info_user_name ", null);
        return res3;

    }

    public boolean AddBmi(String unams, String bism){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv3 = new ContentValues();
        cv3.put(info_bmi, bism);
        db.update(table_info, cv3,  "info_user_name = ?",new String[]{unams} );
        return true;
    }

    public boolean UpdateUser(String ids, String fnams, String lnams, String usrs, String paws){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv4 = new ContentValues();
        cv4.put(key_id, ids);
        cv4.put(key_fnam, fnams);
        cv4.put(key_lnam, lnams);
        cv4.put(key_user_name, usrs);
        cv4.put(key_password, paws);

        db.update(table_user, cv4, " id = ?", new String[]{ids});

        return true;


    }

    public boolean DeleteUser(String id2){

            // delete row in students table based on id
            SQLiteDatabase db = this.getWritableDatabase();

            db.delete(table_user, key_id+ " = ?", new String[]{id2});
            return  true;
    }




}























