package com.example.mysql_lite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    private  static final  String  DATABASE_NAME = "Student.db";
    private  static final  String  TABLE_NAME = "Student_table";
    private  static final  String  COL1 = "ID";
    private  static final  String  COL2 = "NAME";
    private  static final  String  COL3 = "SURNAME";
    private  static final  String  COL4 = "MARKS";


    public DataBaseHelper( Context context) {
        super(context, DATABASE_NAME, null, 1);
      // SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
           db.execSQL("create table "
                   + TABLE_NAME
                   + "( ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME STRING,SURNAME STRING,MARKS INTEGER)");
    }                     //   primary key decide a unquie value in an data entry in database

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        // creating database again
        onCreate(db);
    }

    public boolean insertData(String name,String surname,String marks){
         SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,name);
        contentValues.put(COL3,surname);
        contentValues.put(COL4,marks);
          long result =  db.insert(TABLE_NAME,null,contentValues);
          if(result == -1){
              return false;
          }
          else {
              return true;
          }
    }

    //   This interface provides random read-write access to the result set returned
   //    by a database query.
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
          Cursor res = db.rawQuery(" select * from " +TABLE_NAME,null);
          return res;
    }

     public boolean UpdateData(String id,String name,String surname,String marks){
         SQLiteDatabase db = this.getWritableDatabase();
         ContentValues contentValues = new ContentValues();
         contentValues.put(COL1,id);
         contentValues.put(COL2,name);
         contentValues.put(COL3,surname);
         contentValues.put(COL4,marks);
         db.update(TABLE_NAME,contentValues,"ID = ?", new String[] {(id)});
         return true;
     }

    public int DeleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
         return  db.delete(TABLE_NAME,"ID = ?",new String[]{id});
    }

}
