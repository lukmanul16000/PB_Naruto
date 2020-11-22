package com.example.pb_naruto.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.pb_naruto.model.AnimeList;

import java.util.ArrayList;

public class DBHelperApi extends SQLiteOpenHelper{
    public DBHelperApi(Context context) {
        super(context, "JSONAPI.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE JSONAPI(mal_id integer UNIQUE , mimage_url text,mtitle text,mepisodes integer, murl text,mairing text,msynopsis text,mtype text,mscore text,start_date text,end_date text,mmembers integer,mrated text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS JSONAPI");
        onCreate(db);
    }


    //insert user
    public Boolean insertDataApi(int mal_id,String mimage_url,String mtitle,int mepisodes,String murl,String mairing, String msynopsis,String mtype,String mscore,String start_date, String end_date,int mmembers,String mrated) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("mal_id", mal_id);
        contentValues.put("mimage_url", mimage_url);
        contentValues.put("mtitle", mtitle);
        contentValues.put("mepisodes", mepisodes);
        contentValues.put("murl", murl);
        contentValues.put("mairing", mairing);
        contentValues.put("msynopsis", msynopsis);
        contentValues.put("mtype", mtype);
        contentValues.put("mscore", mscore);
        contentValues.put("start_date", start_date);
        contentValues.put("end_date", end_date);
        contentValues.put("mmembers", mmembers);
        contentValues.put("mrated", mrated);
        long insert = db.insertWithOnConflict("JSONAPI", null, contentValues,SQLiteDatabase.CONFLICT_REPLACE);
        return insert != -1;
    }

    public ArrayList<AnimeList> ListAnime(){
        String sql = "select * from "+"JSONAPI";
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<AnimeList> animeLists = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{

                int mal_id = Integer.parseInt(cursor.getString(0));
                String mimage_url = cursor.getString(1);
                String mtitle= cursor.getString(2);
                int mepisodes = Integer.parseInt(cursor.getString(3));
                String murl = cursor.getString(4);
                String mairing = cursor.getString(5);
                String msynopsis= cursor.getString(6);
                String mtype= cursor.getString(7);
                String mscore= cursor.getString(8);
                String start_date= cursor.getString(9);
                String end_date= cursor.getString(10);
                int mmembers = Integer.parseInt(cursor.getString(11));
                String mrated= cursor.getString(12);
                animeLists.add(new AnimeList(mal_id,mimage_url, mtitle, mepisodes,murl,mairing,msynopsis,mtype,mscore,start_date,end_date,mmembers,mrated));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return animeLists;
    }



}
