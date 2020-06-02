package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.Controller;
import com.example.myapplication.R;
import com.example.myapplication.model.userModel;
import com.example.myapplication.util.CommonUtils;
import com.example.myapplication.util.PreferenceManager;
import com.google.gson.JsonObject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinActivity extends AppCompatActivity {
    @BindView(R.id.bt_join)
    AppCompatButton bt_join;
    @BindView(R.id.join_check_1)
    AppCompatCheckBox check1;
    @BindView(R.id.join_check_2)
    AppCompatCheckBox check2;
    @BindView(R.id.join_check_all)
    AppCompatCheckBox check_all;
    PreferenceManager preferenceManager;
    private static final String PREF_TAG = "MAC";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_join);
        ButterKnife.bind(this);
        preferenceManager = PreferenceManager.getInstance(getApplicationContext());

        bt_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check1.isChecked()) {
                    if (check2.isChecked()) {
                        isValid();
                    }else{
                        Toast.makeText(getApplicationContext(),"이용약관에 동의해 주세요.",Toast.LENGTH_SHORT).show();;
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"MAC수집에 동의해 주세요.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void isValid(){
        String mac = CommonUtils.getMac();
       // preferenceManager.put(PREF_TAG,mac);
        if (!mac.isEmpty()) {
            Call<JsonObject> res = Controller.getInstance().getService().addUser(new userModel(mac,""));
            res.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    Log.d("Retrofit", response.toString());
                    if (response.body() != null){
                        String resultId = response.body().get("result").toString();
                        preferenceManager.put(PREF_TAG,resultId);
                        finish();
                        startActivity(new Intent(getApplication(),IntroActivity.class));
                    }
                }
                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Log.e("Err", "zzzzz");
                }
            });
        }
    }

    public void onCheckboxClicked(View view){
        boolean checked = ((AppCompatCheckBox)view).isChecked();
        switch(view.getId()){
            case R.id.join_check_1:
                break;
            case R.id.join_check_2:
                break;
            case R.id.join_check_all:
                if(check_all.isChecked()){
                    check1.setChecked(true);
                    check2.setChecked(true);
                }else{
                    check1.setChecked(false);
                    check2.setChecked(false);
                }
                break;
             case R.id.bt_join:
                 if(check1.isChecked()) {
                     if (check2.isChecked()) {
                         isValid();
                     }else{
                         Toast.makeText(getApplicationContext(),"이용약관에 동의해 주세요.",Toast.LENGTH_SHORT);
                     }
                 }else{
                     Toast.makeText(getApplicationContext(),"MAC수집에 동의해 주세요.",Toast.LENGTH_SHORT);
                     }
                 break;
        }
    }
}
