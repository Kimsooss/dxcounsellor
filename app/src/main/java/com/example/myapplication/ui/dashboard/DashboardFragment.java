package com.example.myapplication.ui.dashboard;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.Controller;
import com.example.myapplication.R;
import com.example.myapplication.model.postModel;
import com.example.myapplication.util.CommonUtils;
import com.example.myapplication.util.SoftKeyboard;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonObject;

import java.util.Random;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment {
    @BindView(R.id.txttitle)
    TextInputEditText txttitle;
    @BindView(R.id.txtCat)
    TextView txtCat;
    @BindView(R.id.txtDesc)
    TextInputEditText txtDesc;
    @BindView(R.id.ti_1)
    TextInputLayout ti_1;
    @BindView(R.id.ti_2)
    TextInputLayout ti_title;
    @BindView(R.id.bt_save)
    AppCompatButton bt_save;
    @BindView(R.id.lnout)
    LinearLayout lnout;
    @BindView(R.id.lin_outline)
    LinearLayout lin_outline;
    SoftKeyboard softKeyboard;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        ButterKnife.bind(this,root);

        InputMethodManager mgr = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.showSoftInput(txtDesc, InputMethodManager.SHOW_IMPLICIT);

        ti_title.setCounterEnabled(true);
        ti_title.setCounterMaxLength(20);
        ti_1.setCounterEnabled(true);
        ti_1.setCounterMaxLength(200);
        softKeyboard = new SoftKeyboard(lin_outline, mgr);
        softKeyboard.setSoftKeyboardCallback(new SoftKeyboard.SoftKeyboardChanged() {
            @Override
            public void onSoftKeyboardHide() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        ti_1.setCounterMaxLength(200);
                    }
                });
            }
            @Override
            public void onSoftKeyboardShow() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        ti_1.setCounterMaxLength(200);
                    }
                });
            }
        });


        txttitle.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    ti_1.setCounterMaxLength(200);
                } else {
                    ti_1.setCounterMaxLength(200);
                }
            }
        });
        txtDesc.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
//                    lnout.setVisibility(View.GONE);
                } else {
//                    lnout.setVisibility(View.VISIBLE);
                }
            }
        });

        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = CommonUtils.isNull(txttitle.getText().toString(),"notitle");
                String body = CommonUtils.isNull(txtDesc.getText().toString(),"");

                Call<JsonObject> res = Controller.getInstance().getService().addPost(new postModel(getTestId(), CommonUtils.getMac(),title,body,""));
                res.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        Log.d("Retrofit", response.toString());
                        if (response.body() != null){
                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                            builder.setTitle("고민등록완료!")
                            .setMessage("등록이 완료되었습니다.")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Navigation.findNavController(view).navigate(R.id.action_navigation_dashboard_to_navigation_home);
                                }
                            });
                            builder.show();
                        }

                    }
                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Log.e("Err", "zzzzz");
                    }
                });
            }
        });


        return root;
    }
    String getTestId(){
        StringBuffer temp = new StringBuffer();
        Random rnd = new Random();
        for (int i = 0; i < 20; i++) {
            int rIndex = rnd.nextInt(3);
            switch (rIndex) {
                case 0:
                    // a-z
                    temp.append((char) ((int) (rnd.nextInt(26)) + 97));
                    break;
                case 1:
                    // A-Z
                    temp.append((char) ((int) (rnd.nextInt(26)) + 65));
                    break;
                case 2:
                    // 0-9
                    temp.append((rnd.nextInt(10)));
                    break;
            }
        }
        return temp.toString();
    }


}
