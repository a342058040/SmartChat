
package com.feigezhou.tulingdemo;

import com.feigezhou.tulingdemo.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AboutActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		Button button1=(Button) this.findViewById(R.id.button1);  
        button1.setOnClickListener(new View.OnClickListener() {  
              
            @Override  
            public void onClick(View v) {  
                // TODO Auto-generated method stub  
                Toast.makeText(getApplicationContext(), "已是最新版本", Toast.LENGTH_SHORT).show();  
            }  
        });  

        Button button2=(Button) this.findViewById(R.id.button2);  
        button2.setOnClickListener(new View.OnClickListener() {  
              
            @Override  
            public void onClick(View v) {  
                // TODO Auto-generated method stub  
                Toast.makeText(getApplicationContext(), "逗你玩的呢，官网紧急维护中", Toast.LENGTH_SHORT).show();  
            }  
        });  
    }
}
			