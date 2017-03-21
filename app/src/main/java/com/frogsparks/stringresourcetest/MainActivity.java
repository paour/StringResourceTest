package com.frogsparks.stringresourcetest;

import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public LinearLayout container;
    public LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = (LinearLayout) findViewById(R.id.linear_layout);

        layoutParams.rightMargin = getResources().getDimensionPixelSize(R.dimen.margin_test);

        String[] stringNames = getResources().getStringArray(R.array.test_string_names);

        for (String stringName : stringNames) {
            int stringId = getResources().getIdentifier(stringName, "string", getPackageName());

            if (stringId == 0) { continue; }

            getLabel(R.color.test, R.dimen.margin_test, R.dimen.margin_test).setText(stringName);

            int screenshotId = getResources().getIdentifier(stringName, "drawable", getPackageName());

            if (screenshotId != 0) {
                ImageView imageView = new ImageView(this);
                imageView.setImageResource(screenshotId);

                container.addView(imageView, withMargin(R.dimen.margin_test));
            }

            getLabel(R.color.method, R.dimen.margin_method).setText(R.string.set_text);
            getLabel(R.color.string, R.dimen.margin_string).setText(stringId);

            getLabel(R.color.method, R.dimen.margin_method).setText(R.string.set_text_from_html);
            getLabel(R.color.string, R.dimen.margin_string).setText(Html.fromHtml(getString(stringId)));

            getLabel(R.color.method, R.dimen.margin_method).setText(R.string.set_text_get_string);
            getLabel(R.color.string, R.dimen.margin_string).setText(getString(stringId));
        }
    }

    private TextView getLabel(@ColorRes int colorId, @DimenRes int marginLeftId) {
        return getLabel(colorId, marginLeftId, 0);
    }

    private TextView getLabel(@ColorRes int colorId, @DimenRes int marginLeftId, @DimenRes int marginTopId) {
        TextView label = new TextView(this);
        label.setBackgroundColor(getResources().getColor(colorId));

        int padding = getResources().getDimensionPixelSize(R.dimen.padding);
        label.setPadding(padding, padding, padding, padding);

        container.addView(label, withMargin(marginLeftId, marginTopId));

        return label;
    }

    LinearLayout.LayoutParams withMargin(@DimenRes int left) {
        return withMargin(left, 0);
    }

    LinearLayout.LayoutParams withMargin(@DimenRes int marginLeftId, @DimenRes int marginTopId) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(layoutParams);

        if (marginLeftId != 0) {
            lp.leftMargin = getResources().getDimensionPixelSize(marginLeftId);
        }
        if (marginTopId != 0) {
            lp.topMargin = getResources().getDimensionPixelSize(marginTopId);
        }

        return lp;
    }
}
