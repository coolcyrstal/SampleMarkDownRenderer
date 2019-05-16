package com.example.samplemarkdownrenderer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.chinalwb.are.render.AreTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main2Activity extends AppCompatActivity {

    @BindView(R.id.are_text_view)
    AreTextView mAreTextView;

    private String mText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mText = bundle.getString("result");
        }

        mAreTextView.fromHtml(mText);
    }
}
