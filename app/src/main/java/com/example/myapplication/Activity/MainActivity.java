package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.BaseApplication;
import com.example.myapplication.Controller;
import com.example.myapplication.Global;
import com.example.myapplication.R;
import com.example.myapplication.model.userModel;
import com.example.myapplication.util.PreferenceManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.Collection;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements BeaconConsumer , AutoPermissionsListener {
    BeaconManager beaconManager;
    protected final String TAG = "BeaconSearch";
    private int REQUEST_TEST = 1;
    TextView v;

    PreferenceManager preferenceManager;


    void status(boolean b){
        if(b){
            v.setVisibility(View.GONE);
            v.setClickable(false);
        }else{
            v.setVisibility(View.VISIBLE);
            v.setClickable(true);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Global.getInstance().setPreferenceManager(getApplication());
        BottomNavigationView navView = findViewById(R.id.nav_view);
        v = findViewById(R.id.lock);
        //status(t);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
     //   NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        AutoPermissions.Companion.loadAllPermissions(this, 101);
        beaconManager = BeaconManager.getInstanceForApplication(this);
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:2-3=02150215,i:4-19,i:20-21,i:22-23,p:24-24"));
        beaconManager.bind(this);

        getUserINfo();
    }
    boolean t = false;
    public void logOutTest(){
        if(t) {
            Intent intent = new Intent(this, LogoutActivity.class);
           // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.startActivityForResult(intent, REQUEST_TEST);
            t = false;
            status(t);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //logOutTest();
        status(t);
        //체크인상태 아니면
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TEST) {
            if (resultCode == 101) {
                Toast.makeText(MainActivity.this, "Result: " + data.getStringExtra("result"), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_MAIN);//Intent.ACTION_MAIN); //태스크의 첫 액티비티로 시작
                intent.addCategory(Intent.CATEGORY_HOME);   //홈화면 표시
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //새로운 태스크를 생성하여 그 태스크안에서 액티비티 추가
                startActivity(intent);
            } else {   // RESULT_CANCEL
                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
//        } else if (requestCode == REQUEST_ANOTHER) {
//            ...
        }
    }

    @Override
    public void onBeaconServiceConnect() {
        beaconManager.addRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                Log.i(TAG, "2");
                if (beacons.size() > 0) {
                    Log.i(TAG, "Im Interested in this Beacon: " + beacons.iterator().next().getId1());
                    Log.i(TAG, "Im Interested in this Beacon: " + beacons.iterator().next().getDistance());//meter
                    if(beacons.iterator().next().getDistance() > 5.0){
                        Log.e("LOGOUT",""+beacons.iterator().next().getDistance());
                        logOutTest();
                    }else{
                        t=true;
                        //실행중이 아니라면 실행.
                        if(BaseApplication.getInstance().getAppStatus() == BaseApplication.AppStatus.BACKGROUND) {
                            // TODO
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            // IMPORTANT: in the AndroidManifest.xml definition of this activity, you must set android:launchMode="singleInstance" or you will get two instances
                            // created when a user launches the activity manually and it gets launched from here.
                            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                            startActivity(intent);
                        }if((BaseApplication.getInstance().getAppStatus() == BaseApplication.AppStatus.RETURNED_TO_FOREGROUND)
                        || (BaseApplication.getInstance().getAppStatus() == BaseApplication.AppStatus.FOREGROUND)){
                            status(t);
                        }
                    }
                }

            }
        });
        beaconManager.addMonitorNotifier(new MonitorNotifier() {
            @Override
            public void didEnterRegion(Region region) {
                Log.i(TAG, "I just saw an beacon for the first time!");
            }

            @Override
            public void didExitRegion(Region region) {
                Log.i(TAG, "I no longer see an beacon");
                logOutTest();
            }

            @Override
            public void didDetermineStateForRegion(int state, Region region) {
                Log.i(TAG, "I have just switched from seeing/not seeing beacons: "+state);
            }
        });

        try {
            beaconManager.startMonitoringBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
        } catch (RemoteException e) {    }

        try {
            Log.i(TAG,"3");
            beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
        } catch (RemoteException e) {
            Log.i(TAG,"4");
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        beaconManager.unbind(this);
    }


    @Override
    public void onDenied(int i, String[] strings) {

    }

    @Override
    public void onGranted(int i, String[] strings) {
    }

    void getUserINfo(){
        String id  = Global.getInstance().getPreferenceManager().getValue(getString(R.string.pref),"78:F8:82:B3:D3:E8");
        if (!id.isEmpty()) {
            Call<userModel> res = Controller.getInstance().getService().getUser(id);
            res.enqueue(new Callback<userModel>() {
                @Override
                public void onResponse(Call<userModel> call, Response<userModel> response) {
                    Log.d("Retrofit", response.toString());
                    if (response.body() != null)
                        Global.getInstance().usMd = response.body();
                }
                @Override
                public void onFailure(Call<userModel> call, Throwable t) {
                    Log.e("Err", "zzzzz");
                }
            });
        }
    }
}
