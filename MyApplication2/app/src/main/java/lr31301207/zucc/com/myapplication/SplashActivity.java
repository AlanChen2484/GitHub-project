package lr31301207.zucc.com.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Iterator;



public class SplashActivity extends Activity {
    //url to currency codes used in this application
    public static final String URL_CODES =
            "http://openexchangerates.org/api/currencies.json";
    public static final String KEY_ARRAYLIST = "key_arraylist";
    //ArrayList of currencies that will be fetched and passed into MainActivity
    private ArrayList<String> mCurrencies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_splash);

        new FetchCodesTask().execute(URL_CODES);
    }

    //取码.json 解析  异步解析
    private class FetchCodesTask extends AsyncTask<String, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... params) {
            return new JSONParser().getJSONFromUrl(params[0]);
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            try {
                if (jsonObject == null) {
                    throw new JSONException("no data available.");
                }
                Iterator iterator = jsonObject.keys();
                String key = "";
                mCurrencies = new ArrayList<String>();
                while (iterator.hasNext()) {
                    key = (String) iterator.next();
                    mCurrencies.add(key + " | " + jsonObject.getString(key));
                }

                //setClass函数的第二个参数是一个Class对象，在当前场景下，应该传入需要被启动的Activity类的class对象
                Intent mainIntent=new Intent(SplashActivity.this,MainActivity.class);
                mainIntent.putExtra(KEY_ARRAYLIST,mCurrencies);
                startActivity(mainIntent);

                finish();

            } catch (JSONException e) {
                Toast.makeText(
                        SplashActivity.this,
                        "There's been a JSON exception: " + e.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
                e.printStackTrace();
                finish();
            }
        }
    }


}