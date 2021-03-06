package tsubasakato.com.notebrowser;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.lang.String;

public class NoteBrowserActivity extends Activity {
	
	    private EditText editText;
	    private Button button;
	    private SharedPreferences pref;
	    EditText URLText;
	    Button GoButton;
	    WebView Browser;

    /** Called when the activity is first created. */
	 private class MyWebViewClient extends WebViewClient {
	     @Override
	     public boolean shouldOverrideUrlLoading(WebView view, String url) {
	         view.loadUrl(url);
	         return true;
	     }
	     
	     public void onPageFinished(WebView view, String url) {
	    	 url = url.replace("https://","");
	    	 url = url.replace("http://","");
	    	 URLText.setText(url);
	     }
	 }
	 
   
    
    
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
       
        URLText = (EditText)findViewById(R.id.URL);
        GoButton = (Button)findViewById(R.id.Go);
        Browser = (WebView) findViewById(R.id.WebEngine);
        Browser.loadUrl("http://www.google.com/");
        Browser.setWebViewClient(new WebViewClient());
        Browser.requestFocus(View.FOCUS_DOWN);
        Browser.getSettings().setJavaScriptEnabled(true);
        Browser.getSettings().setBuiltInZoomControls(true);
         GoButton.setOnClickListener(new OnClickListener() {
    public void onClick(View v) {
	 	Browser.setWebViewClient(new MyWebViewClient());
	 	Browser.loadUrl("http://"+URLText.getText().toString());
	 	Browser.requestFocus(View.FOCUS_DOWN);
	 	String url = Browser.getUrl();
	 	
 			}
 });
     
         
      
         editText = (EditText) findViewById(R.id.editText1);
         pref = getPreferences(MODE_PRIVATE);
         editText.setText(pref.getString("test", ""));

         button = (Button) findViewById(R.id.save);
         button.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View view) {
                 Editor editor = pref.edit();
                 editor.putString("test", editText.getText().toString());
                 editor.commit();
                 Toast.makeText(getApplicationContext(), "Note Saved.",
                         Toast.LENGTH_SHORT).show();
                 
             }
         });

         button = (Button) findViewById(R.id.buttonForward);
         button.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View view) {
           
                Browser.goForward();
                
             }
         });
         
         button = (Button) findViewById(R.id.buttonBack);
         button.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View view) {
                Browser.goBack();
                
             }
         });
         
      
              
    }  
    
  
    
}
