package com.example.samplemarkdownrenderer;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.savvyapps.togglebuttonlayout.Toggle;
import com.savvyapps.togglebuttonlayout.ToggleButtonLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import ru.noties.markwon.Markwon;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_text_view)
    TextView mTextView;
    @BindView(R.id.main_edit_text)
    EditText mEditText;
    @BindView(R.id.toggle_button_layout)
    ToggleButtonLayout mToggleButtonLayout;


    private String mFullText = "";
    private String mTextTemp = "";
    private String mLastAdd = "";
    private String mMarkDownPrefixExpression = "";
    private String mMarkDownSuffixExpression = "";
    private int mTextLength = 0;
    private boolean isAdd = false;
    private ArrayList<String> mListItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mListItems.add("h2");
        mListItems.add("strike-stroke");

        final Markwon markwon = Markwon.create(getApplicationContext());
        markwon.setMarkdown(mTextView, mTextTemp);

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(start+before < mTextLength){
                    mTextLength -= 1;
                } else {
                    mTextLength += 1;
                    mTextTemp += String.valueOf(s.charAt(start + before));
                }
                markwon.setMarkdown(mTextView, mFullText + mMarkDownPrefixExpression + mTextTemp + mMarkDownSuffixExpression);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mToggleButtonLayout.setOnToggledListener(new Function2<Toggle, Boolean, Unit>() {
            @Override
            public Unit invoke(Toggle toggle, Boolean aBoolean) {
                return MainActivity.this.showToggle(toggle, aBoolean);
            }
        });
    }

    private Unit showToggle(Toggle toggle, Boolean isToggle) {
        if (isToggle) {
            mFullText += mMarkDownPrefixExpression + mTextTemp + mMarkDownPrefixExpression + " ";
            mTextTemp = "";
            switch (toggle.getId()) {
                case R.id.toggle_h2:
                    mMarkDownPrefixExpression = "## ";
                    mMarkDownSuffixExpression = "";
                    break;
                case R.id.toggle_italic:
                    mMarkDownPrefixExpression = "_";
                    mMarkDownSuffixExpression = "_";
                    break;
                case R.id.toggle_bold:
                    mMarkDownPrefixExpression = "**";
                    mMarkDownSuffixExpression = "**";
                    break;
                case R.id.toggle_tab:
                    mMarkDownPrefixExpression = "> ";
                    mMarkDownSuffixExpression = "";
                    break;
            }
        }
        return null;
    }
}
