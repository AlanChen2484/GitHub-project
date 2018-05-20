package lr31301207.zucc.com.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Properties;
import java.util.zip.DataFormatException;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button mCalcButton;
    private Button btn_his;
    private DBManager mgr;
    private TextView mConvertedTextView;
    private EditText mAmountEditText;
    private Spinner mForSpinner,mHomSpinner;
    private String[] mCurrencies;
    public static final String FOR="FOR_CURRENCY";
    public static final String HOM="HOM_CURRENCY";

    private String mkey;
    public static final String RATES="rates";
    public static final String URL_B2ASE=
            "http://openexchangerates.org/api/latest.json?app_id=";
    private static final DecimalFormat DECIMAL_FORMAT = new
            DecimalFormat("#,##0.00000");

    java.util.Date writeTime = new java.util.Date();
    @Override

    /*onCreate()函数是在activity初始化的时候调用的，通常情况下，
    我们需要在onCreate()中调用setContentView(int)函数填充屏幕的UI，
    一般通过findViewById(int)返回xml中定义的视图或组件的ID。
    子类在重写onCreate()方法的时候必须调用父类的onCreate()方法，
    即super.onCreate()，否则会抛出异常*/
    //初始化工作
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //提取货币代码

        ArrayList<String> arrayList = ((ArrayList<String>) getIntent().getSerializableExtra(SplashActivity.KEY_ARRAYLIST));
        Collections.sort(arrayList);//数组A到Z排序

        mCurrencies=arrayList.toArray(new String[arrayList.size()]);
        mgr = new DBManager(this);
        btn_his= (Button) findViewById(R.id.btn_his);
        mConvertedTextView=(TextView)findViewById(R.id.txt_converted);
        mAmountEditText=(EditText)findViewById(R.id.edt_amount);
        mCalcButton=(Button)findViewById(R.id.btn_calc);
        mForSpinner=(Spinner)findViewById(R.id.spn_for);
        mHomSpinner=(Spinner)findViewById(R.id.spn_hom);

        //给 Spinners 绑定 mCurrencies 数据
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(
                this,R.layout.spinner_closed ,mCurrencies);
        arrayAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        mHomSpinner.setAdapter(arrayAdapter);
        mForSpinner.setAdapter(arrayAdapter);

        mHomSpinner.setOnItemSelectedListener(this);
        mForSpinner.setOnItemSelectedListener(this);


        btn_his.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent();
                intent.setClass(MainActivity.this,HistoryActivity.class);
                startActivity(intent);
            }
        });


        //判断是否存在个性化的本币和外币数据
        if(savedInstanceState==null && (PrefsMgr.getString(this,FOR)==null&&
                PrefsMgr.getString(this,HOM)==null)){
            mForSpinner.setSelection(findPositionGivenCode("USD",mCurrencies));
            mHomSpinner.setSelection(findPositionGivenCode("CNY",mCurrencies));

            PrefsMgr.setString(this,FOR,"USD");
            PrefsMgr.setString(this,HOM,"CNY");
        }
        else{
           mForSpinner.setSelection(findPositionGivenCode(PrefsMgr.getString(this,
                    FOR),mCurrencies));
            mHomSpinner.setSelection(findPositionGivenCode(PrefsMgr.getString(this,
                    HOM),mCurrencies));
        }
        mCalcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CurrencyConverterTask().execute(URL_BASE+mkey);
            }
        });

        mkey=getKey("open_key");

    }

    //网络链接
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    // 提取字符串
    private String extractCodeFromCurrency(String currency) {
        return (currency).substring(0,3);
    }

    //获取key
    private String getKey(String keyName){
        AssetManager assetManager = this.getResources().getAssets();
        Properties properties = new Properties();
        try {
            InputStream inputStream = assetManager.open("keys.properties");
            properties.load(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return  properties.getProperty(keyName);

    }

    //遍历.定位
    private int findPositionGivenCode(String code, String[] currencies) {
        for (int i = 0; i < currencies.length; i++) {
            if (extractCodeFromCurrency(currencies[i]).equalsIgnoreCase(code)) {
                return i;
            }
        }
        //default
        return 0;
    }



    @Override
    //菜单调用
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    //菜单的执行方法
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.mnu_invert:
                //TODO define behavior here
                invertCurrencies();
                break;
            case R.id.mnu_codes:
                //TODO define behavior here
                launchBrowser(SplashActivity.URL_CODES);
                break;
            case R.id.mnu_exit:
                finish();
                break;
        }
        return true;
        // return super.onPrepareOptionsMenu(item);
    }


    //如果网络链接，启动浏览器。交互。
    private void launchBrowser(String strUri) {
        if (isOnline()) {
            Uri uri = Uri.parse(strUri);
//call an implicit intent
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }

    //交换币种的时候也能够保存该信息，写入本地数据库prefsmgr。
    private void invertCurrencies() {
        int nFor = mForSpinner.getSelectedItemPosition();
        int nHom = mHomSpinner.getSelectedItemPosition();
        mForSpinner.setSelection(nHom);
        mHomSpinner.setSelection(nFor);
        mConvertedTextView.setText("");

        PrefsMgr.setString(this, FOR, extractCodeFromCurrency((String)
                mForSpinner.getSelectedItem()));
        PrefsMgr.setString(this, HOM, extractCodeFromCurrency((String)
                mHomSpinner.getSelectedItem()));
    }

    //spanner每次选择都记住你的选择。
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spn_for:
                PrefsMgr.setString(this, FOR,
                        extractCodeFromCurrency((String)mForSpinner.getSelectedItem()));
                break;
            case R.id.spn_hom:
                PrefsMgr.setString(this, HOM,
                        extractCodeFromCurrency((String)mHomSpinner.getSelectedItem()));
                break;
            default:
                break;
        }

        mConvertedTextView.setText("");

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

   //钱币转换任务。异步解析
    private class CurrencyConverterTask extends AsyncTask<String, Void,
            JSONObject> {
        private ProgressDialog progressDialog;

        @Override//触发异步，异步第一步
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Calculating Result...");
            progressDialog.setMessage("One moment please...");
            progressDialog.setCancelable(true);
            progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE,
                    "Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            CurrencyConverterTask.this.cancel(true);
                            progressDialog.dismiss();
                        }
                    });
            progressDialog.show();
        }
        @Override//异步第二步
        protected JSONObject doInBackground(String... params) {
            return new JSONParser().getJSONFromUrl(params[0]);
        }

        @Override//异步第三部
        protected void onPostExecute(JSONObject jsonObject) {
            double dCalculated = 0.0;
            String strForCode =

                    extractCodeFromCurrency(mCurrencies[mForSpinner.getSelectedItemPosition()]);
            String strHomCode =
                    extractCodeFromCurrency(mCurrencies[mHomSpinner.
                            getSelectedItemPosition()]);
            String strAmount = mAmountEditText.getText().toString();
            try {
                if (jsonObject == null){
                    throw new JSONException("no data available.");
                }
                JSONObject jsonRates = jsonObject.getJSONObject(RATES);
                if (strHomCode.equalsIgnoreCase("USD")){
                    dCalculated = Double.parseDouble(strAmount) /
                            jsonRates.getDouble(strForCode);
                } else if (strForCode.equalsIgnoreCase("USD")) {
                    dCalculated = Double.parseDouble(strAmount) *
                            jsonRates.getDouble(strHomCode) ;
                }
                else {
                    dCalculated = Double.parseDouble(strAmount) *
                            jsonRates.getDouble(strHomCode)
                            / jsonRates.getDouble(strForCode) ;
                }
            } catch (JSONException e) {
                Toast.makeText(
                        MainActivity.this,
                        "There's been a JSON exception: " + e.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
                mConvertedTextView.setText("");
                e.printStackTrace();
            }
            mConvertedTextView.setText(DECIMAL_FORMAT.format(dCalculated) + " " + strHomCode);
            progressDialog.dismiss();

            //时间并写入表。
            String a=strAmount+" "+strForCode;
            String b=DECIMAL_FORMAT.format(dCalculated) + " " + strHomCode;
            // Timestamp now = new Timestamp(System.currentTimeMillis());
           /* SearchData searchData=new SearchData();
            searchData.addName(a,b,"123");*/
            SimpleDateFormat dt = new SimpleDateFormat("MM-dd HH:mm");
           // DateFormat dt=new SimpleDateFormat("HH:mm:aa");
            String dataString=dt.format(new Date());
            mgr.addName(a,b,dataString);

            //for testing
//            if (mCurrencyTaskCallback != null) {
            //               mCurrencyTaskCallback.executionDone();
            //          }
        }
    }

}
