package softblue.example.camera;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Esta activity mostra a integração com a câmera usando a aplicação nativa de câmera do Android
 * @author Carlos Tosin @ Softblue
 */
public class Camera1Activity extends Activity {

	float XIni = 0;
    float YIni = 0;
    float XFin = 0;
    float YFin = 0;	
    int numFotos = 0;
    File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "SmartEvent");
	/**
	 * Código de requisição para poder identificar quando a activity da câmera é finalizada
	 */
	private static final int REQUEST_PICTURE = 1000;

	/**
	 * View onde a foto tirada será exibida
	 */
	private ImageView imageView;
	
	/**
	 * Local de armazenamento da foto tirada 
	 */
	private File imageFile;

	/**
	 * Invocado quando a activity é criada
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.teste);

	    GridView gridview = (GridView) findViewById(R.id.gridview);
	    gridview.setAdapter(new ImageAdapter(this));

	    gridview.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	            Toast.makeText(Camera1Activity.this, "" + position, Toast.LENGTH_SHORT).show();
	        }
	    });
	//}

	//@Override
	//public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		this.imageView = (ImageView) findViewById(R.id.imagem);
		
		// Obtém o local onde as fotos são armazenadas na memória externa do dispositivo
		File picsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		
		// Define o local completo onde a foto será armazenada (diretório + arquivo) 
		File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "SmartEvent");
		this.imageFile = new File(mediaStorageDir.getPath(), "foto_"+ numFotos +".jpg");

	    final View touchView = findViewById(R.id.entire_view);
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
	    	        		imageView.setImageResource(R.drawable.ic_launcher);
	    	            }
	                    if(XIni-XFin>100)
	    	            {
	    	        		setContentView(R.layout.teste);
	    	            }

	                    break;
	                }
	            }
	            return true;
	        }

	    });
	}
	
	public class ImageAdapter extends BaseAdapter {
	    private Context mContext;

	    public ImageAdapter(Context c) {
	        mContext = c;
	    }

	    public int getCount() {
	        return mThumbIds.length;
	    }

	    public Object getItem(int position) {
	        return null;
	    }

	    public long getItemId(int position) {
	        return 0;
	    }

	    // create a new ImageView for each item referenced by the Adapter
	    public View getView(int position, View convertView, ViewGroup parent) {
	        ImageView imageView;
	        if (convertView == null) {  // if it's not recycled, initialize some attributes
	            imageView = new ImageView(mContext);
	            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
	            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
	            imageView.setPadding(8, 8, 8, 8);
	        } else {
	            imageView = (ImageView) convertView;
	        }

	        imageView.setImageResource(mThumbIds[position]);
	        return imageView;
	    }

	    // references to our images
	    private Integer[] mThumbIds = {
	            mediaStorageDir.getPath('foto_'+numFotos), R.drawable.sample_3,
	            R.drawable.sample_4, R.drawable.sample_5,
	            R.drawable.sample_6, R.drawable.sample_7,
	            R.drawable.sample_0, R.drawable.sample_1,
	            R.drawable.sample_2, R.drawable.sample_3,
	            R.drawable.sample_4, R.drawable.sample_5,
	            R.drawable.sample_6, R.drawable.sample_7,
	            R.drawable.sample_0, R.drawable.sample_1,
	            R.drawable.sample_2, R.drawable.sample_3,
	            R.drawable.sample_4, R.drawable.sample_5,
	            R.drawable.sample_6, R.drawable.sample_7
	    };
	}

	/**
	 * Método que tira uma foto
	 * @param v
	 */
	public void takePicture(View v) {
		// Cria uma intent que será usada para abrir a aplicação nativa de câmera
		Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		
		// Indica na intent o local onde a foto tirada deve ser armazenada
		i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile));
		
		// Abre a aplicação de câmera
		startActivityForResult(i, REQUEST_PICTURE);
		
		numFotos++;
	}

	/**
	 * Método chamado quando a aplicação nativa da câmera é finalizada
	 * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// Verifica o código de requisição e se o resultado é OK (outro resultado indica que
		// o usuário cancelou a tirada da foto)
		if (requestCode == REQUEST_PICTURE && resultCode == RESULT_OK) {
		
			FileInputStream fis = null;
			
			try {
				try {
					// Cria um FileInputStream para ler a foto tirada pela câmera
					fis = new FileInputStream(imageFile);
					
					// Converte a stream em um objeto Bitmap
					Bitmap picture = BitmapFactory.decodeStream(fis);
					
					// Exibe o bitmap na view, para que o usuário veja a foto tirada
					imageView.setImageBitmap(picture);
				} finally {
					if (fis != null) {
						fis.close();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}