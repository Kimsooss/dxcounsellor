package com.example.myapplication;

import android.content.Context;

import com.example.myapplication.model.userModel;
import com.example.myapplication.util.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

public class Global {
    private volatile static Global uniqueInstance;
    private Global() {

    }
    PreferenceManager preferenceManager;

    public PreferenceManager getPreferenceManager() {
        return preferenceManager;
    }

    public String getPref(String key){
        return this.preferenceManager.getValue(key,"");
    }
    public void setPreferenceManager(Context ct) {
        this.preferenceManager = PreferenceManager.getInstance(ct);
    }

    public static Global getInstance() {
        if(uniqueInstance == null) {
            synchronized(Global.class) {
                if(uniqueInstance == null) {
                    uniqueInstance = new Global();
                }
            }
        }
        return uniqueInstance;
    }




    /**
     * 클레스 이동시 각 클레스를 저장해서, 현재 실행중인 화면을 확인
     */
    private List<Class> activityList;
    public void addClass(Class clazz) {
        if(activityList == null) {
            activityList = new ArrayList<>();
        }
        activityList.add(clazz);
    }
    public void removeClass(Class clazz) {
        if(activityList != null) {
            activityList.remove(clazz);
        }
    }




    public boolean checkClasses(Class... activities) {
        boolean result = false;
        for(int i = 0; i < activityList.size(); i++) {
            result = false;
            for(int j = 0; j < activities.length; j++) {
                if(activityList.get(i) == activities[j]) {
                    result = true;
                    break;
                }
            }
            if(result == false)
                break;
        }
        return result;
    }

    /**
     * Login get User Info.
     */
    public userModel usMd;
}
