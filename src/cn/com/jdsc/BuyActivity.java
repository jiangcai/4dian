package cn.com.jdsc;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import cn.com.util.*;

public class BuyActivity  extends Activity{
	


   @Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.main_buy);
	
	Button login = (Button) findViewById(R.id.login_comfirm_button);
	
	login.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
	      

		}
		
	});
   }
   
}
