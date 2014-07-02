package softblue.example.camera;

import softblue.example.camera.adapter.ImageAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;

public class PicturesList extends Activity {
	
	float XIni = 0;
    float YIni = 0;
    float XFin = 0;
    float YFin = 0;	

	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.teste);
		GridView gridview = (GridView) findViewById(R.id.gridview);
	    gridview.setAdapter(new ImageAdapter(this));
	
	    final View touchView = findViewById(R.id.gridview);
	    	touchView.setOnTouchListener(new View.OnTouchListener() 
	    	{
	    	
	    		public boolean onTouch(View v, MotionEvent event)
	    		{
	    			final int action = event.getAction();
	     
	    			switch (action & MotionEvent.ACTION_MASK) 
	    			{
	    				case MotionEvent.ACTION_DOWN: 
	    				{
	    					XIni = event.getX();
	    					YIni = event.getY();
	    					break;
	    				}

	    				case MotionEvent.ACTION_MOVE:
	    				{
	    					XFin = event.getX();
	    					YFin = event.getY();
	    					if(XFin-XIni>100)
	    					{	
	    						Intent i = new Intent( PicturesList.this, Camera1Activity.class );
	    						startActivity(i);
	    					}
	                    
	    					break;
	    				}
	    			}
	    			return true;
	    		}
	    	
	    	});
	}
}
