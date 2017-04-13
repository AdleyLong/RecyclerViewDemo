package com.huaqin.recyclerviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by ubuntu on 17-4-13.
 */

public class BaseActivity extends AppCompatActivity {
    /**
     * toast弹框
     *
     * @param toastStr
     */
    public void showToast(String toastStr) {
        Toast.makeText(this, toastStr, Toast.LENGTH_SHORT).show();
    }

    /**
     * toast弹框
     *
     * @param toastId
     */
    public void showToast(int toastId) {
        Toast.makeText(this, toastId, Toast.LENGTH_SHORT).show();
    }
}
