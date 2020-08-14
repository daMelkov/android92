package com.astra.melkovhw92;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Spinner mLanguagesSpinner;
    private Spinner mDimensionsSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Utils.onActivityCreateSetTheme(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        mLanguagesSpinner = findViewById(R.id.spinner_language);
        mDimensionsSpinner = findViewById(R.id.spinner_dimensions);

        Button btnChange = findViewById(R.id.button_change);
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* set locale */
                Locale locale = new Locale("en");

                switch((int) mLanguagesSpinner.getSelectedItemId()) {
                    case 0:
                        locale = new Locale("en");
                        break;
                    case 1:
                        locale = new Locale("ru");

                }

                /* set dimensions */
                switch(mDimensionsSpinner.getSelectedItemPosition()) {
                    case 0:
                        Utils.changeUI(MainActivity.this, Utils.SIZE_SMALL, locale);
                        break;
                    case 1:
                        Utils.changeUI(MainActivity.this, Utils.SIZE_NORMAL, locale);
                        break;
                    case 2:
                        Utils.changeUI(MainActivity.this, Utils.SIZE_LARGE, locale);
                }
            }
        });

        initSpinners();
    }

    private void initSpinners() {
        ArrayAdapter<CharSequence> adapterLanguages = ArrayAdapter.createFromResource(this, R.array.languages, android.R.layout.simple_spinner_item);
        adapterLanguages.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mLanguagesSpinner.setAdapter(adapterLanguages);
        if(Utils.getLocale().toString().equals("ru")) {
            mLanguagesSpinner.setSelection(1);
        }

        ArrayAdapter<CharSequence> adapterStyles = ArrayAdapter.createFromResource(this, R.array.dimensions, android.R.layout.simple_spinner_item);
        adapterStyles.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mDimensionsSpinner.setAdapter(adapterStyles);
        if(Utils.getTheme() < adapterStyles.getCount()) {
            mDimensionsSpinner.setSelection(Utils.getTheme());
        }
    }
}