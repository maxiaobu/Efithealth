package com.efithealth.app.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.efithealth.R;
import com.efithealth.app.maxiaobu.base.BaseFrg;

/**
 * A simple {@link Fragment} subclass.
 */
public class CourseOrderFragment extends BaseFrg {


    public CourseOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_course_order, container, false);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

}
