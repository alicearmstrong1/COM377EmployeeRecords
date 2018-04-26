package example.uuj.employeerecords;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class EmployeeHomePage extends AppCompatActivity
{
    // Declaring variables
    Button btnviewAll;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_employee_home_page );

        // Initialise the button
        btnviewAll = (Button)findViewById(R.id.btnviewall);

        // Call database helper
        db = new DatabaseHelper(this);

        viewAll();
    }

    // Method to open Add Employee activity
    public void onClickAddEmployee(View view)
    {
        // Create Add Employee button
        Button addEmployee= (Button) findViewById( R.id.btnAddEmployee );
        addEmployee.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // Create intent to open activity
                Intent intent_addEmployee = new Intent( EmployeeHomePage.this, AddEmployee.class );
                startActivity( intent_addEmployee );
                finish();
            }
        });
    }

    // Method to open Delete Employee activity
    public void onClickDeleteEmployee(View view)
    {
        // Create Delete Employee button
        Button deleteEmployee= (Button) findViewById( R.id.btndelete );
        deleteEmployee.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent_deleteEmployee = new Intent( EmployeeHomePage.this, DeleteEmployee.class );
                startActivity( intent_deleteEmployee );
                finish();
            }
        });
    }

    // Method to view all data from the database
    public void viewAll()
    {
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        Cursor allData = db.getAllData();
                        if(allData.getCount() == 0)
                        {
                            // show message
                            showMessage("Error","No Employees on record. ");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (allData.moveToNext()) {
                            buffer.append("ID: "+ allData.getString(0)+"\n");
                            buffer.append("Name: "+ allData.getString(1)+"\n");
                            buffer.append("DOB: "+ allData.getString(2)+"\n");
                            buffer.append("Address: "+ allData.getString(3)+"\n");
                            buffer.append("Town: "+ allData.getString(4)+"\n");
                            buffer.append("Postcode: "+ allData.getString(5)+"\n\n");
                            buffer.append("ContractNumber: "+ allData.getString(6)+"\n");
                            buffer.append("Email: "+ allData.getString(7)+"\n\n");
                            buffer.append("JobTitle: "+ allData.getString(8)+"\n");
                            buffer.append("Salary: "+ allData.getString(9)+"\n\n\n");
                        }

                        // Show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }


    public void showMessage(String Title,String Message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(Title);
        builder.setMessage(Message);
        builder.show();
    }
}
