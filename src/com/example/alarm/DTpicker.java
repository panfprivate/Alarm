package com.example.alarm;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class DTpicker extends DialogFragment {

	private DatePicker mDp;
	private TimePicker mTp;
	private DTtransfer mDtt;
	private TextView tv3;
	private int day, month, year, hour, minute;
//	private DTpicker mDtp;
	
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		tv3 = (TextView) getActivity().findViewById(R.id.textView3);

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
       
        
        builder.setView(inflater.inflate(R.layout.dtdialog, null))
        	.setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                   
        		
        		@Override
                   public void onClick(DialogInterface dialog, int id) {
        				
        				mDp = (DatePicker) getDialog().findViewById(R.id.dp);
        				mTp = (TimePicker) getDialog().findViewById(R.id.tp);
        				
        				int a =  mDp.getMonth() + 1;
                	   
        				day = mDp.getDayOfMonth();
        				month = mDp.getMonth() + 1; 
        				year = mDp.getYear();
        				hour = mTp.getCurrentHour();
        				minute = mTp.getCurrentMinute();
        				
        				tv3.setText(year+"-"+month+"-"+day+"  "+hour+":"+minute);
        				mListener.onDialogPositiveClick(DTpicker.this);
                   }
               })
               .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   	mListener.onDialogNegativeClick(DTpicker.this);
                	   	DTpicker.this.getDialog().cancel();
                   }
               });      
        
        // Create the AlertDialog object and return it
        return builder.create();
    }
	
	public DTtransfer getDTt(){
		
		return new DTtransfer(day, month, year, hour, minute);
	}
	
	public interface DTpickerListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }
	
	DTpickerListener mListener;
    
    // Override the Fragment.onAttach() method to instantiate the DTpickerListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the DTpickerListener so we can send events to the host
            mListener = (DTpickerListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement DTpickerListener");
        }
    }
}
