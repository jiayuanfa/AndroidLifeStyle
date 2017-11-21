package com.example.jiayuanfa.activitylifystyle;

import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

// 四种启动模式
// 第一种：standard ：默认的启动模式，每次启动创建不同内存的Activity
// 第二种：singleTop：当下次启动该Activity的时候，如果发现该活动已经在栈顶，则直接使用，不会再创建新的活动
// 第三种：singleTask：保证内存中只存在一个Activity
// 第四种：

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    /**
     * 公开的构造方法，用于外界快速启动该Activity
     * @param context
     * @param paramOne
     * @param paramTwo
     */
    public static void actionStart(Context context, String paramOne, String paramTwo) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("paramOne",paramOne);
        intent.putExtra("paramTwo",paramTwo);
//        测试Dev
        context.startActivity(intent);
    }

    /**
     * @param savedInstanceState  此参数用于恢复当前Activity被销毁之前临时保存的数据
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            String tempData = savedInstanceState.getString("data_key");
            Log.d(TAG, "onCreate: " + tempData);
        }
        
        Button startNormalActivityButton = findViewById(R.id.start_normal_activity);
        Button startDialogActivityButton = findViewById(R.id.start_dialog_activity);
        
        startNormalActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NormalActivity.class);
                startActivity(intent);
            }
        });
        
        startDialogActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,DialogActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    /**
     * 在此方法中可以将因为进入下一个Activity而当前的Activity被销毁的数据进行临时保存
     * @param outState
     * @param outPersistentState
     */
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        String tempData = "Something you just typed";
        outState.putString("data_key",tempData);
    }
}
