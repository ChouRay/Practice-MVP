package com.example.jarry.practice_mvp.view.actvity;

import android.os.Bundle;

import com.example.jarry.practice_mvp.R;
import com.example.jarry.practice_mvp.view.fragment.BookDetailFragment;


public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            this.addFragment(R.id.layout_container, BookDetailFragment.newInstance());
        }
    }



}
