package com.lznby.bigdemo.lznby.fragment.control.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;

import com.lznby.bigdemo.R;

/**
 * @author Lznby
 * @time 2018/9/6 12:53
 * Class Note:
 * 1.DialogFragment的使用.
 */
public class MyDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        /**
         * 1、创建要在DialogFragment中显示的视图。
         */
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_crime_dialog_fragment,null);

        //1、创建要在DialogFragment中显示的视图
        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle("DialogFragment练习")
                .setPositiveButton(android.R.string.ok,null)
                .create();

    }
}
