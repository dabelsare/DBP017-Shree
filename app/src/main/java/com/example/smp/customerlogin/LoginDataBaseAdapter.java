package com.example.smp.customerlogin;
import java.util.HashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;
import java.lang.String;

/**
 * Created by smp on 17/04/2017.
 */
public class LoginDataBaseAdapter {

    static final String DATABASE_NAME = "login.db";
    static final int DATABASE_VERSION = 3;
    public static final int NAME_COLUMN = 3;
    public static final String USER_ID       = "ID";
    public static final String USER_NAME     = "username";
    public static final String USER_PASSWORD = "password ";


    static final String DATABASE_CREATE = "create table "+"Agent"+
            "( " +"ID integer primary key autoincrement,"+ "PASSWORD text,"+ "FIRSTNAME text,"+ "LASTNAME text,"+ "EMAIL text,"+ "MOBILE text,"+ "CITY text) ";

    public SQLiteDatabase db;
    private final Context context;
    private DataBaseHelper dbHelper;

    public LoginDataBaseAdapter(Context _context)
    {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    public LoginDataBaseAdapter open() throws SQLException
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public void close()
    {
        db.close();
    }

    public SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }

    public void insertEntry(String password ,String firstname,String lastname,String email,String mobile,String city)
    {
        ContentValues newValues = new ContentValues();
        newValues.put("PASSWORD", password);
        newValues.put("FIRSTNAME",firstname);
        newValues.put("LASTNAME", lastname);
        newValues.put("EMAIL ", email);
        newValues.put("MOBILE", mobile);
        newValues.put("CITY", city);

        db.insert("Agent", null, newValues);
    }

    public int deleteEntry(String password)
    {
        String where="PASSWORD=?";
        int numberOFEntriesDeleted= db.delete("Agent", where, new String[]{password}) ;
        return numberOFEntriesDeleted;
    }

    public String getSinlgeEntry(String Username)
    {
        Cursor cursor=db.query("Agent", null, "EMAIL=?", new String[]{Username}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String PASSWORD= cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return PASSWORD;
    }
    public String getId(String Username)
    {
        Cursor cursor=db.query("Agent", null, "EMAIL=?", new String[]{Username}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String ID = cursor.getString(cursor.getColumnIndex("ID"));
        cursor.close();
        return ID;
    }
    public Cursor fetchUser(String username, String password) {
        Cursor cursor= db.query("Agent",
                new String[] { USER_ID, USER_NAME, USER_PASSWORD },
                "EMAIL=?"+ "='" + username + "' AND " +
                         "PASSWORD=?" + "='" + password + "'", null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }



    public String getAllTags(String a) {

        Cursor c = db.rawQuery("SELECT * FROM " + "Agent" + " where EMAIL = '" + a + "'", null);
        String str = null;
        if (c.moveToFirst()) {
            do {
                str = c.getString(c.getColumnIndex("PASSWORD"));
            } while (c.moveToNext());
        }
        return str;
    }


    public String getmobileno(String agentmobileno)
    {
        Cursor cursor=db.query("Agent", null, "MOBILE=?", new String[]{agentmobileno}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String MOBILE= cursor.getString(cursor.getColumnIndex("MOBILE"));
        cursor.close();
        return MOBILE;
    }
    public String  getemail(String email)
    {
        Cursor cursor=db.query("Agent", null, "EMAIL=?", new String[]{email}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String EMAIL= cursor.getString(cursor.getColumnIndex("EMAIL"));
        cursor.close();
        return EMAIL;


    }

    public void updateEntry(String password,String repassword)
    {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("PASSWORD", password);


        String where="USERNAME = ?";
        db.update("Agent", updatedValues, where, new String[]{password});
    }


    public HashMap<String, String> getAnimalInfo(String id) {
        HashMap<String, String> wordList = new HashMap<String, String>();
        String selectQuery = "SELECT * FROM LOGIN where id='"+id+"'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                wordList.put("PASSWORD", cursor.getString(1));
            } while (cursor.moveToNext());
        }
        return wordList;
    }
  /*  public String[] getAllDetail1(String singleid) {
        // final String TABLE_NAME = "Agent";
        int id=Integer.parseInt(singleid);
        String selectQuery = "SELECT  * FROM Agent where id='"+id+"'";
        SQLiteDatabase db  = dbHelper.getReadableDatabase();
        Cursor cursor      = db.rawQuery(selectQuery, null);
        String data[]      = null;

        if (cursor.moveToFirst()) {
            do {
              String d=new String(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return data;
    }*/

    public String getAllDetail(String singleid) {
     int id=Integer.parseInt(singleid);
     Cursor c = db.rawQuery("SELECT  * FROM Agent where id='"+id+"'", null);
        String str = null;
        if (c.moveToFirst()) {
            do {
                str=c.getString(2);
            } while (c.moveToNext());
        }
        return str;
    }

}
