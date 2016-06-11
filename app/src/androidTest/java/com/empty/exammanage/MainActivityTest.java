package com.empty.exammanage;

import android.test.ActivityUnitTestCase;


/**
 * Created by wallds on 2016/5/28.
 */
public class MainActivityTest extends ActivityUnitTestCase<MainActivity> {
    private MainActivity mActivity = null;
    public MainActivityTest(){
        super(MainActivity.class);
    }
    @Override
    public void setUp() throws Exception {

    }
    @Override
    public void tearDown() throws Exception {

    }
    public void testHello() throws Exception {
        MainActivity mainActivity = getActivity();

    }
}