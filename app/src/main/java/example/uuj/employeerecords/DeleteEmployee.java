package example.uuj.employeerecords;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DeleteEmployee extends AppCompatActivity
{
    // Declare Variables
    DatabaseHelper db;
    ListView deleteEmployeeListView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_delete_employee );

        // Initialise the list view
        deleteEmployeeListView = (ListView) findViewById(R.id.listView);

        // Call database helper
        db = new DatabaseHelper(this);

        displayEmployeeNames();
    }

    // Method to display employee names in list view
    private void displayEmployeeNames()
    {
        // Get the data and create array list
        Cursor data = db.getAllData();
        ArrayList<String> listData = new ArrayList<>();

        while(data.moveToNext())
        {
            // Get the employee name from the database and add it to the array
            listData.add(data.getString(1));
        }

        // Create the list adapter and set the adapter
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        deleteEmployeeListView.setAdapter(adapter);

        // Click listener for when item in list view is clicked
        deleteEmployeeListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                final String name = adapterView.getItemAtPosition(i).toString();

                // Get employees names
                Cursor data = db.getEmployeeName(name);
                int employeeName = -1;
                while(data.moveToNext()){
                    employeeName = data.getInt(0);
                }
                if(employeeName > -1)
                {
                    AlertDialog.Builder confirmation = new AlertDialog.Builder(DeleteEmployee.this);

                    confirmation.setTitle("Confirmation");
                    confirmation.setMessage("Are you sure you want to remove " + name + " from the Employee Database?");

                    confirmation.setPositiveButton("YES", new DialogInterface.OnClickListener()
                    {

                        public void onClick(DialogInterface dialog, int which)
                        {

                            db.deleteData( name );
                            Toast.makeText( DeleteEmployee.this, "Employee removed from the database", Toast.LENGTH_LONG ).show();
                            dialog.dismiss();
                        }
                    });

                    confirmation.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            Toast.makeText( DeleteEmployee.this, "Employee was not removed from the database", Toast.LENGTH_LONG ).show();
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alert = confirmation.create();
                    alert.show();
                }
            }
        });
    }

    // Method for cancel button
    public void onClickCancel(View view)
    {
        // Create cancel button
        Button cancel= (Button) findViewById( R.id.btncancel );
        cancel.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent_cancel = new Intent( DeleteEmployee.this, EmployeeHomePage.class );
                startActivity( intent_cancel );
                finish();
            }
        });
    }

    // Method for refresh button
    public void onClickRefresh(View view)
    {
        // Create cancel button
        Button refresh= (Button) findViewById( R.id.btnrefresh );
        refresh.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent_cancel = new Intent( DeleteEmployee.this, DeleteEmployee.class );
                startActivity( intent_cancel );
                finish();
            }
        });
    }
}
