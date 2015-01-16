package com.devterous.aqn.facebook;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;
import com.devterous.aqn.R;
import com.devterous.aqn.settings.AppPreferences;
import com.devterous.aqn.settings.IPreferences;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Facebooker implements DialogListener{
	Activity mActivity;
	IPreferences mPref;
	private Facebook mFacebook;
	EditText mEditText;
	private TextView mTextView;
	private String mDescriptionText;
	
	public Facebooker(Activity activity, Facebook facebook, String descriptionText){
		mActivity = activity;
		mFacebook = facebook;
		mDescriptionText = descriptionText;
		mPref = new AppPreferences(activity.getBaseContext());
	}
		
	public void share(){
		final AlertDialog.Builder builder = initDialog(mActivity);
	    builder.setPositiveButton(R.string.accept,
	        new DialogInterface.OnClickListener() {
	          public void onClick(DialogInterface dialog, int which) {
	        	  onFacebookPost();
	          }
	        });
	    builder.setNegativeButton(R.string.decline,
	        new DialogInterface.OnClickListener() {
	          public void onClick(DialogInterface dialog, int which) {
	            
	          }
	        });
	    builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
	      public void onCancel(DialogInterface dialog) {
	        
	      }
	    });
	    
	    builder.show();		
	}
	
	private void onFacebookPost(){
		String response = null;
        
        try{
        	if(mPref.getFacebookToken() == null){
        		mFacebook.authorize(mActivity, new String[] {"publish_stream", "read_stream", "offline_access"}, this);
        	}
        	else{
        		postToFacebook();
        	}
        }
        catch(Exception e){
        	Toast.makeText(mActivity.getBaseContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
	}
	
	private void postToFacebook() {
		String response = null;
		
        Bundle parameters = new Bundle();
        //parameters.putString("caption", "Air Quality Daily");
        parameters.putString("link", "");
        parameters.putString("picture", "http://devterous.com/images/air_quality_index.gif");
        parameters.putString("message", mEditText.getText().toString());
        parameters.putString("description", mDescriptionText);
        parameters.putString("access_token", mPref.getFacebookToken());
        
        try {
        	response = mFacebook.request("me");
			response = mFacebook.request("me/feed", parameters, 
			        "POST");
			
			Toast.makeText(mActivity.getBaseContext(), response + ":", Toast.LENGTH_SHORT).show();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
 
	}

	private AlertDialog.Builder initDialog(Context context) {
	    AlertDialog.Builder builder = new AlertDialog.Builder(context);
	    builder.setCancelable(true);
	    builder.setTitle("Post to Facebook");
	    View view = LayoutInflater.from(context).inflate(R.layout.facebook, null);
	    mEditText = (EditText)view.findViewById(R.id.facebookerUserCustomText);
	    mTextView = (TextView)view.findViewById(R.id.facebookerAppText);
	    mTextView.setText("Values from user.", TextView.BufferType.EDITABLE);	    
	    
	    builder.setView(view);
	    
	    //builder.setMessage(ResourceUtils.readFile(context, R.raw.eula));
	    return builder;
	  }

	public void onComplete(Bundle values) {
		postToFacebook();		
	}

	public void onFacebookError(FacebookError e) {
		// TODO Auto-generated method stub
		
	}

	public void onError(DialogError e) {
		// TODO Auto-generated method stub
		
	}

	public void onCancel() {
		// TODO Auto-generated method stub
		
	}
}
