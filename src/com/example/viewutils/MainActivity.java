package com.example.viewutils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	@ViewInject(R.id.tv_1)
	private TextView tvx;
	
	@ViewInject(R.id.tv_2)
	private TextView tvy;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ViewUtils.inject(this);
		Log.d("tag", "tvx="+tvx);
		Log.d("tag", "tvy="+tvy);
	}
	
	@OnClick(R.id.bt)
	public void open(View view){
		Log.d("tag", "我被点击了");
	}
}
