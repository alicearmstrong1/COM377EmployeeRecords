package example.uuj.employeerecords;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener
{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        // Use the current date to get the default date
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create and return a new instance of DatePickerDialog
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }
    // onDateSet() method to display date in textview
    public void onDateSet(DatePicker view, int year, int month, int day)
    {
        // Display date selected in text view
        TextView tv = (TextView) getActivity().findViewById( R.id.txtdisplaydate);
        tv.setText(tv.getText()+ String.valueOf(day) + " / " + String.valueOf(month) + " / " + String.valueOf(year)+ "\n");
    }
}