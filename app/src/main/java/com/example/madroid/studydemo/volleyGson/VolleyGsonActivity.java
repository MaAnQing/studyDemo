package com.example.madroid.studydemo.volleyGson;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.madroid.studydemo.Constant;
import com.example.madroid.studydemo.Keeper;
import com.example.madroid.studydemo.R;
import com.google.gson.Gson;

import org.apache.http.util.EncodingUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class VolleyGsonActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "VolleyGsonActivity" ;
    private Toolbar mToolbar ;
    private DataType mType ;

    private EditText mEditText ;
    private TextView mDisplayTxt ;
    private ImageView mImageView ;
    private RadioGroup mJsonGroup ;
    private Spinner mSpinner ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_gson);

        init();
    }

    private void init() {
        mType = DataType.JSON ;
        initView();
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar) ;
        setSupportActionBar(mToolbar);

        mEditText = (EditText) findViewById(R.id.http_url) ;
        mDisplayTxt = (TextView) findViewById(R.id.display_text) ;
        mImageView = (ImageView) findViewById(R.id.display_img) ;
        findViewById(R.id.load_data).setOnClickListener(this);

        mJsonGroup = (RadioGroup) findViewById(R.id.json_group);
        mJsonGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.i(TAG, "===onCheckedChanged");
                //if (group.getId() == R.id.json_group) {
                switch (checkedId) {
                    case R.id.radio_local_json:
                        Keeper.keepJsonType(Constant.LOCAL_JSON);
                        break;
                    case R.id.radio_json:
                        Keeper.keepJsonType(Constant.JSON);
                        break;
                    case R.id.radio_gson:
                        Keeper.keepJsonType(Constant.GSON);
                        break;
                    default:
                        break;
                }
            }

        });

        final String[] dataType = getResources().getStringArray(R.array.data_type) ;
        mSpinner = (Spinner) findViewById(R.id.data_type);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,dataType) ;
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(this, "已选择" + dataType[position] + "设备", Toast.LENGTH_LONG).show();

                switch (position){
                    case 0 :
                        mType = DataType.TXT ;
                        break;
                    case 2 :
                        mType = DataType.JSON ;
                        break;
                    case 1 :
                        mType = DataType.IMAGE ;
                        break;
                    default:
                        break;
                }

                if (mType == DataType.JSON){
                    mJsonGroup.setVisibility(View.VISIBLE);
                    if (Keeper.readJsonType() == Constant.JSON){
                        mJsonGroup.check(R.id.radio_json);
                    }else if (Keeper.readJsonType() == Constant.GSON){
                        mJsonGroup.check(R.id.radio_gson);
                    }else {
                        mJsonGroup.check(R.id.radio_local_json);
                    }

                }else {
                    mJsonGroup.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        if (R.id.load_data == v.getId() ) loadData();
    }


    private void loadData() {

        switch (mType) {
            case TXT:
                loadStringData();
                break;
            case JSON:
                loadJsonData() ;
                break;
            case IMAGE:
                loadImageData() ;
                break;
        }
    }

    private void loadImageData() {
        String url = "http://i.imgur.com/7spzG.png";
        mImageView.setVisibility(View.VISIBLE);
        mDisplayTxt.setVisibility(View.GONE);

        // Retrieves an image specified by the URL, displays it in the UI.
        ImageRequest request = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        mImageView.setImageBitmap(bitmap);
                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        mImageView.setVisibility(View.GONE);
                    }
                });
        // Access the RequestQueue through your singleton class.
        RequestManager.getInstance(this).addToRequestQueue(request);

    }

    private void loadJsonData() {
        mImageView.setVisibility(View.GONE);
        mDisplayTxt.setVisibility(View.VISIBLE);

        int jsonType = Keeper.readJsonType() ;
        switch (jsonType) {
            case Constant.LOCAL_JSON :
                getLocalJson();
                break;

            case Constant.JSON:
                getJson();
                break;

            case Constant.GSON:
                getGson();
                break;

        }

    }

    private void getLocalJson(){
        String json = "" ;
        mDisplayTxt.append("/assets/user.json" + "\n");
        try {
            InputStream in = getResources().getAssets().open("user.json") ;
            int len = in.available() ;
            byte[] temp = new byte[len] ;
            in.read(temp) ;
            json = EncodingUtils.getString(temp,"UTF-8") ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        //将Json转换成java对象
        Userinfo info = new Gson().fromJson(json,Userinfo.class) ;
        mDisplayTxt.append(info.toString()+ "\n");
        Log.i(TAG, "madroid : " + info.getName() + "\n") ;
    }

    private void showToast(String txt){
        Toast.makeText(getApplicationContext(),txt,Toast.LENGTH_LONG).show();
    }

    private void checkURL(String url){
        String URL = mEditText.getText().toString().trim() ;
        if ("".equals(URL)){
            mEditText.setText(url);
        }
    }

    private void getJson(){
        String url = "http://www.weather.com.cn/data/sk/101010100.html" ;
        mDisplayTxt.append(url + "\n");
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        mDisplayTxt.append("Response: " + response.toString() + "\n");
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        mDisplayTxt.append("Response: " + error.toString() + "\n");
                    }
                });

        // Access the RequestQueue through your singleton class.
        RequestManager.getInstance(this).addToRequestQueue(jsObjRequest);
    }

    private void getGson(){
        String url = "http://www.weather.com.cn/data/sk/101010100.html" ;
        mDisplayTxt.append(url + "\n");
        GsonRequest gsonRequest = new GsonRequest(url, Weatherinfo.class, null, new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                Log.i(TAG,response.toString()) ;
                mDisplayTxt.append(((Weatherinfo)response).toString() + "\n");
                //mDisplayTxt.append("city name:" + ((Weatherinfo)response).getWeatherinfo().city+ "\n");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG,error.toString()) ;
                mDisplayTxt.append(error.toString() + "\n");
            }
        });
        RequestManager.getInstance(this).addToRequestQueue(gsonRequest);
    }

    private void loadStringData() {
        mImageView.setVisibility(View.GONE);
        mDisplayTxt.setVisibility(View.VISIBLE);

        String url = "http://www.baidu.com" ;
        mDisplayTxt.append(url + "\n");
        checkURL(url);
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mDisplayTxt.append(mType.toString());
                mDisplayTxt.append(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mDisplayTxt.append(mType.toString());
                mDisplayTxt.setText(error.toString());
            }
        }) ;
        request.setTag(TAG) ;
        RequestManager.getInstance(getApplicationContext()).addToRequestQueue(request);
    }

    enum DataType {TXT, JSON, IMAGE}
}
