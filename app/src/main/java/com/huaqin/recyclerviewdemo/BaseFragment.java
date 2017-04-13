package com.huaqin.recyclerviewdemo;

import android.support.v4.app.Fragment;
import android.widget.Toast;

/**
 * Created by ubuntu on 17-4-13.
 */

public class BaseFragment extends Fragment {

    /**
     * toast弹框
     * @param toastStr
     */
    public void showToast(String toastStr){
        Toast.makeText(this.getActivity(),toastStr,Toast.LENGTH_SHORT).show();
    }

    /**
     * toast弹框
     * @param toastId
     */
    public void showToast(int toastId){
        Toast.makeText(this.getActivity(),toastId, Toast.LENGTH_SHORT).show();
    }
}
