package com.example.myapplication;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

public class BaseApplication extends Application {
    private static BaseApplication mInstance = null;
    private AppStatus mAppStatus = AppStatus.FOREGROUND;

    @Override
    public void onCreate() {
        mInstance = this;
        super.onCreate();

        // Activity 라이프 사이클을 탐지하여 포그라운드와 백그라운드 체크
        registerActivityLifecycleCallbacks(new CActivityLifecycleCallbacks());
    }

    public static BaseApplication getInstance() {
        return mInstance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public AppStatus getAppStatus() {
        return mAppStatus;
    }

    public enum AppStatus {
        BACKGROUND,
        RETURNED_TO_FOREGROUND, // 홈 버튼으로 내린후 다시 실행 된 경우, 처음 실행시
        FOREGROUND,
        MEMORY_OUT; // 최근 앱 사용 목록에서 앱이 삭제 되었을 경우
    }

    public class CActivityLifecycleCallbacks implements ActivityLifecycleCallbacks {
        private int running = 0;

        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override
        public void onActivityStarted(Activity activity) {
            if (++running == 1) {
                mAppStatus = AppStatus.RETURNED_TO_FOREGROUND;
            } else if (running > 1) {
                mAppStatus = AppStatus.FOREGROUND;
            }
        }

        @Override
        public void onActivityResumed(Activity activity) {//포그라운드로 돌아왔을때.
        }

        @Override
        public void onActivityPaused(Activity activity) {
        }

        @Override
        public void onActivityStopped(Activity activity) {
            if (--running == 0) {
                mAppStatus = AppStatus.BACKGROUND;
            }
        }

        private void notifyForeground() {
            // This is where you can notify listeners, handle session tracking, etc
        }

        private void notifyBackground() {
            // This is where you can notify listeners, handle session tracking, etc
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            mAppStatus = AppStatus.MEMORY_OUT;
            // 메인 Activity가 Destory 된다는 것은 '최근 앱 사용 목록'에서 지우기를 한것임
        }
    }
}