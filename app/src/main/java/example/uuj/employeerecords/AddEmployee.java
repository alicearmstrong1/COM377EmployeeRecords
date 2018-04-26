package example.uuj.employeerecords;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddEmployee extends AppCompatActivity
{
    // Declare Variables
    DatabaseHelper db;
    private Button btnAdd;
    private TextView displayDate;
    private EditText name, address, town, postcode, contactnumber, email, jobtitle, salary;
    boolean isDataInserted ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        // Call database helper
        db = new DatabaseHelper(this);

        // Initialise the edit texts and buttons
        btnAdd = (Button) findViewById(R.id.btnAddEmployee );
        displayDate = (TextView) findViewById( R.id.txtdisplaydate );
        name = (EditText) findViewById( R.id.txtName );
        address = (EditText) findViewById( R.id.txtAddress );
        town = (EditText) findViewById( R.id.txtTown );
        postcode = (EditText) findViewById( R.id.txtPostcode );
        contactnumber = (EditText) findViewById( R.id.txtContactNumber );
        email = (EditText) findViewById( R.id.txtEmail );
        jobtitle = (EditText) findViewById( R.id.txtJobTitle );
        salary = (EditText) findViewById( R.id.txtSalary );

        AddData();
    }

    // Will display date picker
    public void onClickDate(View view)
    {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show( getFragmentManager(), "Date Picker" );
    }

    // Method to add data to the database
    public  void AddData() {
        btnAdd.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        // If statement for validation, if no enter is entered
                        if ( name.getText().toString().length() == 0)
                        {
                            name.setError( "Must enter name" );
                        }
                        else if (displayDate.getText().toString().length() == 0)
                        {
                            displayDate.setError( "Must select DOB" );
                        }
                        else if(address.getText().toString().length() == 0)
                        {
                            address.setError( "Must enter Address" );
                        }
                        else if (town.getText().toString().length() == 0)
                        {
                            town.setError( "Must enter Town" );
                        }
                        else if (postcode.getText().toString().length() == 0)
                        {
                            postcode.setError( "Must enter Postcode" );
                        }
                        else if (contactnumber.getText().toString().length() == 0)
                        {
                            contactnumber.setError( "Must enter Contact Number" );
                        }
                        else if (email.getText().toString().length() == 0)
                        {
                            email.setError( "Must enter Email" );
                        }
                        else if (jobtitle.getText().toString().length() == 0)
                        {
                            jobtitle.setError( "Must enter Job Title" );
                        }
                        else if (salary.getText().toString().length() == 0)
                        {
                            salary.setError( "Must enter Salary" );
                        }
                        // Insert data into database
                        else {
                            isDataInserted = db.addData(
                                    name.getText().toString(),
                                    displayDate.getText().toString(),
                                    address.getText().toString(),
                                    town.getText().toString(),
                                    postcode.getText().toString(),
                                    contactnumber.getText().toString(),
                                    email.getText().toString(),
                                    jobtitle.getText().toString(),
                                    salary.getText().toString());
                            }


                        // If statement to display message if either data is inserted or not
                        if(isDataInserted == true)
                        {
                            Toast.makeText( AddEmployee.this, "Data Inserted", Toast.LENGTH_LONG ).show();
                            // Reset all edit text fields
                            name.setText("");
                            displayDate.setText("");
                            address.setText("");
                            town.setText("");
                            postcode.setText("");
                            contactnumber.setText("");
                            email.setText("");
                            jobtitle.setText("");
                            salary.setText("");
                        }
                        else
                            Toast.makeText(AddEmployee.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
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
                Intent intent_cancel = new Intent( AddEmployee.this, EmployeeHomePage.class );
                startActivity( intent_cancel );
                finish();
            }
        });
    }


}