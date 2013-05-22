package cn.com.jdsc;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MoreActivity  extends Activity{
   @Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.main_more);
	
	TextView signal = (TextView) findViewById(R.id.title_Text);
	signal.setText("¸ü¶à");
}
}
