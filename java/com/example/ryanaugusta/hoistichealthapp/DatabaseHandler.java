// Project:     Holistic Health App
// Author:      Augusta
// Date:        2018
// File:        DatabaseHandler.java
// Description: This class handles all database activity
package com.example.ryanaugusta.hoistichealthapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.FilterQueryProvider;

import static android.content.ContentValues.TAG;

public class DatabaseHandler extends SQLiteOpenHelper {

    SQLiteDatabase dbase;

    public Context context;
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "remedies";

    // table names for vitamins, supplements, and oils
     static final String TABLE_VITAMIN = "vitamin";
     static final String TABLE_SUPPLEMENT = "supplement";
     static final String TABLE_MY_STACK = "mystack";

    // course Table columns names
     static final String COL_ID = "_id"; //this is needed! has to be called _id to hook the cursor
     static final String COL_NAME = "name";
     static final String COL_INFO = "info";

    // constructor
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) // this is where you build the tables
    {
        System.out.println("SQLite onCreate called");

        // SQLite Create syntax
        //CREATE TABLE NameOfTable(Column1 Type, Column2 Type);

        // declare string with SQL command to create the vitamin table
        String CREATE_VITAMIN_TABLE = "CREATE TABLE " + TABLE_VITAMIN + "("
                + COL_ID + " INTEGER PRIMARY KEY,"
                + COL_NAME + " TEXT,"
                + COL_INFO + " TEXT"
                + ")";

        db.execSQL(CREATE_VITAMIN_TABLE);

        // declare string with SQL command to create the supplement table
        String CREATE_SUPPLEMENT_TABLE = "CREATE TABLE " + TABLE_SUPPLEMENT + "("
                + COL_ID + " INTEGER PRIMARY KEY,"
                + COL_NAME + " TEXT,"
                + COL_INFO + " TEXT"
                + ")";

        db.execSQL(CREATE_SUPPLEMENT_TABLE);


        // declare string with SQL command to create the oil table
        String CREATE_MYSTACK_TABLE = "CREATE TABLE " + TABLE_MY_STACK + "("
                + COL_ID + " INTEGER PRIMARY KEY,"
                + COL_NAME + " TEXT,"
                + COL_INFO + " TEXT"
                + ")";

        db.execSQL(CREATE_MYSTACK_TABLE);

    }

    // try this
    // search SQL for supplements
    public Cursor retrieve(String keywords) {

        StringBuilder selectQuery = new StringBuilder();

        selectQuery.append("SELECT *" + " FROM " + TABLE_SUPPLEMENT + " WHERE " +
                COL_NAME + " LIKE " + "'%" + keywords +"%' OR " +
                COL_INFO + " LIKE " + "'%" + keywords + "%'");


        if (selectQuery.length() > 0) {
            selectQuery.append(" ORDER BY " + COL_NAME);
        }

        if (selectQuery.length() == 0) {
            Cursor cursor = null;
            return cursor;
        }


        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("Query", selectQuery.toString());

        // execute raw SQLite Query
        Cursor cursor = db.rawQuery(selectQuery.toString(), null);

        return cursor;
    }


    // search SQL for vitamins
    public Cursor retrieveV(String keywords) {

        StringBuilder selectQueryV = new StringBuilder();

        selectQueryV.append("SELECT *" + " FROM " + TABLE_VITAMIN +
                " WHERE " + COL_NAME + " LIKE " + "'%" + keywords +"%' OR " +
                COL_INFO + " LIKE " + "'%" + keywords + "%'");


        if (selectQueryV.length() > 0) {
            selectQueryV.append(" ORDER BY " + COL_NAME);
        }

        if (selectQueryV.length() == 0) {
            Cursor cursor = null;
            return cursor;
        }


        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("Query", selectQueryV.toString());

        // execute raw SQLite Query
        Cursor cursor = db.rawQuery(selectQueryV.toString(), null);

        return cursor;
    }

    public void deleteMyStackObjects() {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_MY_STACK, null, null);
       // db.delete(TABLE_SUPPLEMENT, null, null);
       // db.delete(TABLE_VITAMIN, null, null);
        db.close();
    }

    public void deleteMyStackObjectsByName(String someName) {
        SQLiteDatabase db = this.getWritableDatabase();

         db.delete(TABLE_MY_STACK, COL_NAME + " = ?",
                new String[] { someName });

        db.close();
    }

    public void addMyStackInfo(MyStackInfo mystack) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_NAME, mystack.getName());
        // values.put(COL_INFO, mystack.getInfo());
        db.insert(TABLE_MY_STACK, null, values);
        db.close();
    }

    public void testMyStackAdd(String name) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_NAME, name);

        db.insert(TABLE_MY_STACK, null, values);
        db.close();
    }

    public SupplementInfo getSupplementObjectByName(String someName) {

        // create reference to ContactBusiness for return
        SupplementInfo supplementInfo = null;

        try
        {
            // get the database from the SQLiteHelper
            SQLiteDatabase db = this.getReadableDatabase();

            // SQLite query command
            // query(String table, String[] columns, String selection, String[] selectionArgs,
            //          String groupBy, String having, String orderBy, String limit)
            Cursor cursor = db.query(TABLE_SUPPLEMENT, new String[]{COL_ID,
                            COL_NAME, COL_INFO}, COL_NAME + "=?",
                    new String[]{someName}, null, null, null, null);

            // if the cursor is not null and count is > 0, move to the first position.
            if (cursor != null && cursor.getCount() > 0)
            {
                cursor.moveToFirst();

                // create ContactBusiness object from cursor
                supplementInfo = new SupplementInfo(cursor.getString(1), cursor.getString(2));

            } else
            {
                System.out.println("getSupplementObjectByName cursor is null");
                supplementInfo = null;
            }

            // close the cursor
            cursor.close();

            // close the database
            db.close();

            // return contact
            return supplementInfo;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            // return contact
            return supplementInfo;
        }
    }

    public VitaminInfo getVitaminObjectByName(String someName) {

        // create reference to ContactBusiness for return
        VitaminInfo vitaminInfo = null;

        try
        {
            // get the database from the SQLiteHelper
            SQLiteDatabase db = this.getReadableDatabase();

            // SQLite query command
            // query(String table, String[] columns, String selection, String[] selectionArgs,
            //          String groupBy, String having, String orderBy, String limit)
            Cursor cursor = db.query(TABLE_VITAMIN, new String[]{COL_ID,
                            COL_NAME, COL_INFO}, COL_NAME + "=?",
                    new String[]{someName}, null, null, null, null);

            // if the cursor is not null and count is > 0, move to the first position.
            if (cursor != null && cursor.getCount() > 0)
            {
                cursor.moveToFirst();

                // create ContactBusiness object from cursor
                vitaminInfo = new VitaminInfo(cursor.getString(1), cursor.getString(2));

            } else
            {
                System.out.println("getSupplementObjectByName cursor is null");
                vitaminInfo = null;
            }

            // close the cursor
            cursor.close();

            // close the database
            db.close();

            // return contact
            return vitaminInfo;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            // return contact
            return vitaminInfo;
        }
    }


    public Cursor getAllMyStackObjects() {

        String selectQuery = "SELECT *" +
                " FROM " + TABLE_MY_STACK + " ORDER BY " + COL_NAME;

        // get the database from the SQLiteHelper
        SQLiteDatabase db = this.getReadableDatabase();

        // execute a raw SQLite query
        Cursor cursor = db.rawQuery(selectQuery, null);

        // return the Cursor
        return cursor;
    }

    // delete a ContacBusiness by name from the business table
    public void deleteMyStackByName(String someName)
    {
        // get a writeable database
        SQLiteDatabase db = this.getWritableDatabase();

        // use SQLite convenience method to delete
        // delete(String table, String whereClause, String[] whereArgs)
        db.delete(TABLE_MY_STACK, COL_NAME + " = ?",
                new String[] { someName });

        db.close(); // Close database connection
    }

    // delete any existing entries for test
    public void deleteVitaminObjects() {

        // get the database
        SQLiteDatabase db = this.getWritableDatabase();

        // delete the business contacts and close
        // 2nd arg is where clause
        // 3rd arg is where values
        db.delete(TABLE_VITAMIN, null, null);
        db.close(); // Closing database connection
    }

    // add vitamin info to table
    public void addVitaminInfo(VitaminInfo vitamin) {

        // get the database from the SQLiteHelper
        SQLiteDatabase db = this.getWritableDatabase();

        // ContentValues is used to store a set of key/values that the ContentResolver can process.
        // These values will be inserted into the matching columns
        ContentValues values = new ContentValues();
        values.put(COL_NAME, vitamin.getName());
        values.put(COL_INFO, vitamin.getInfo());
        // Inserting Row
        // SQLite Syntax
        // INSERT INTO TableName(ColumnValue, ColumnValue)

        // Use the SQLite insert method
        // first arg is table name
        // second arg If you specify null, like in this code sample,
        // the framework does not insert a row when there are no values.
        db.insert(TABLE_VITAMIN, null, values);
        db.close(); // Closing database connection
    }

    // get all the objects
    public Cursor getAllVitaminObjects() {

        String selectQuery = "SELECT *" +
                " FROM " + TABLE_VITAMIN + " ORDER BY " + COL_NAME;

        // get the database from the SQLiteHelper
        SQLiteDatabase db = this.getReadableDatabase();

        // execute a raw SQLite query
        Cursor cursor = db.rawQuery(selectQuery, null);

        // return the Cursor
        return cursor;
    }

    // delete
    public void deleteSupplementObjects() {

        // get the database
        SQLiteDatabase db = this.getWritableDatabase();

        // delete the business contacts and close
        // 2nd arg is where clause
        // 3rd arg is where values
        db.delete(TABLE_SUPPLEMENT, null, null);
        db.close(); // Closing database connection
    }

    // add info into table
    public void addSupplementInfo(SupplementInfo supplement) {

        // get the database from the SQLiteHelper
        SQLiteDatabase db = this.getWritableDatabase();

        // ContentValues is used to store a set of key/values that the ContentResolver can process.
        // These values will be inserted into the matching columns
        ContentValues values = new ContentValues();
        values.put(COL_NAME, supplement.getName());
        values.put(COL_INFO, supplement.getInfo());
        // Inserting Row
        // SQLite Syntax
        // INSERT INTO TableName(ColumnValue, ColumnValue)

        // Use the SQLite insert method
        // first arg is table name
        // second arg If you specify null, like in this code sample,
        // the framework does not insert a row when there are no values.
        db.insert(TABLE_SUPPLEMENT, null, values);
        db.close(); // Closing database connection

    }

    // get all objects
    public Cursor getAllSupplementObjects() {

        String selectQuery = "SELECT *" +
                " FROM " + TABLE_SUPPLEMENT + " ORDER BY " + COL_NAME;

        // get the database from the SQLiteHelper
        SQLiteDatabase db = this.getReadableDatabase();

        // execute a raw SQLite query
        Cursor cursor = db.rawQuery(selectQuery, null);

        // return the Cursor
        return cursor;
    }

    // not sure if I will need to use this method
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("SQLite onUpgrade called");

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VITAMIN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUPPLEMENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MY_STACK);

        // Create tables again
        onCreate(db);
    }
}
