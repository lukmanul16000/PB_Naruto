package com.example.pb_naruto.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "loginSQLite.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE session(id integer PRIMARY KEY, login text)");
        db.execSQL("CREATE TABLE user(id integer PRIMARY KEY AUTOINCREMENT, username text UNIQUE, password text)");
        db.execSQL("INSERT INTO session(id, login) VALUES(1, 'kosong')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS session");
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }

    //check session
    public Boolean checkSession(String sessionValues) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM session WHERE login = ?", new String[]{sessionValues});
        if (cursor.getCount() > 0) {
            return true;
        }
        else {
            return false;
        }
    }

    //upgrade session
    public Boolean upgradeSession(String sessionValues, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("login", sessionValues);
        long update = db.update("session", contentValues, "id="+id, null);
        if (update == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    //insert user
    public Boolean insertUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long insert = db.insertWithOnConflict("user", null, contentValues,SQLiteDatabase.CONFLICT_REPLACE);
        if (insert == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    //check login
    public Boolean checkLogin(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE username = ? AND password = ?", new String[]{username, password});
        if (cursor.getCount() > 0) {
            return true;
        }
        else {
            return false;
        }
    }

//    public boolean IsItemExist(String name,String mobile) {
//        try
//        {
//            SQLiteDatabase db=this.getReadableDatabase();
//            Cursor cursor=db.rawQuery("SELECT "+NAME+" FROM "+TABLE+" WHERE "+NAME+"=?",new String[]{name});
//            Cursor cursor1=db.rawQuery("SELECT "+MOBILE+" FROM "+TABLE+" WHERE "+MOBILE+"=?",new String[]{mobile});
//            if (cursor.moveToFirst() && cursor1.moveToFirst())
//            {
//                db.close();
//                Log.d("Record  Already Exists", "Table is:"+TABLE+" ColumnName:"+NAME);
//                return true;//record Exists
//
//            }
//            Log.d("New Record  ", "Table is:"+TABLE+" ColumnName:"+NAME+" Column Value:"+NAME);
//            db.close();
//        }
//        catch(Exception errorException)
//        {
//            Log.d("Exception occured", "Exception occured "+errorException);
//            // db.close();
//        }
//        return false;
//
//    }
}
