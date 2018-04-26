package example.uuj.employeerecords;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{
    // Declaring variables
    public static final String DATABASE_NAME = "EmployeeRecords.db";
    public static final String TABLE_NAME = "Employees";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Name";
    public static final String COL_3 = "DOB";
    public static final String COL_4 = "Address";
    public static final String COL_5 = "Town";
    public static final String COL_6 = "Postcode";
    public static final String COL_7 = "ContactNumber";
    public static final String COL_8 = "Email";
    public static final String COL_9 = "JobTitle";
    public static final String COL_10 = "Salary";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // Create the database table
        db.execSQL("create table " + TABLE_NAME +" " +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR, DOB REAL, " +
                "Address VARCHAR, Town VARCHAR, Postcode VARCHAR, ContactNumber INT, Email VARCHAR," +
                "JobTitle VARCHAR, Salary REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // Drop table statement
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String Name, String DOB, String Address,String Town,String Postcode, String ContactNumber,
                           String Email, String JobTitle, String Salary)
    {
        // Add data into the database
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2,Name);
        contentValues.put(COL_3,DOB);
        contentValues.put(COL_4,Address);
        contentValues.put(COL_5,Town);
        contentValues.put(COL_6,Postcode);
        contentValues.put(COL_7,ContactNumber);
        contentValues.put(COL_8,Email);
        contentValues.put(COL_9,JobTitle);
        contentValues.put(COL_10,Salary);

        long result = db.insert(TABLE_NAME,null ,contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }

    // Method to display all the data within the database
    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor allData = db.rawQuery("select * from "+TABLE_NAME,null);
        return allData;
    }

    // Method to get all the employees names
    public Cursor getEmployeeName(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String employeeNames = "select " + COL_1 + " from " + TABLE_NAME +
                " where " + COL_2 + " = '" + name + "'";
        Cursor data = db.rawQuery(employeeNames, null);
        return data;
    }


    // Method to delete employees
    public Integer deleteData (String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "Name = ?",new String[] {name});
    }


}
