package com.mespitech.mvpbase.uikit;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import com.mespitech.mvpbase.R;

import java.util.Locale;

public class ReadMoreTextView extends AppCompatTextView {

    private static final String TAG = "ReadMoreTextView";
    private String readMoreText = "";
    private String collapseText = "";
    private int collapseLines = 4;
    private String rawText = "";

    private boolean isCollapse = true;

    public ReadMoreTextView(Context context) {
        super(context);
        init(context, null);
    }

    public ReadMoreTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ReadMoreTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ReadMoreTextView);
        readMoreText = ta.getString(R.styleable.ReadMoreTextView_readMoreText);
        collapseText = ta.getString(R.styleable.ReadMoreTextView_collapsingText);
        collapseLines = ta.getInt(R.styleable.ReadMoreTextView_collapseLines, 4);
        rawText = ta.getString(R.styleable.ReadMoreTextView_rawText);
        readMoreText = readMoreText == null ? "" : readMoreText;
        rawText = rawText == null ? "" : rawText;
        ta.recycle();

        makeText();
    }

    private void makeText() {
        if (rawText == null) {
            return;
        }
        if (rawText.length() < 90) {
            setText(rawText);
            return;
        }
        if (isCollapse) {
            String displayText = rawText.substring(0, 90);
//            Log.d(TAG, "makeText: " + displayText);
            SpannableString spannableString = new SpannableString(String.format(Locale.US, "%s ...%s", displayText, readMoreText));
//            Log.d(TAG, "makeText: " + spannableString.toString());
            ClickableSpan usernameClickableSpan = new ClickableSpan() {
                @Override
                public void onClick(@NonNull View widget) {
                    isCollapse = false;
                    makeText();
//                    Log.d(TAG, "onClick: ");
                }

                @Override
                public void updateDrawState(@NonNull TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setUnderlineText(false);
                }
            };
            int start = displayText.length();
            int end = start + 4 + readMoreText.length();
            spannableString.setSpan(usernameClickableSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new StyleSpan(Typeface.BOLD), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new ForegroundColorSpan(Color.BLACK), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            setText(spannableString);
            setMovementMethod(LinkMovementMethod.getInstance());
        } else {
//            setText(rawText);
            String displayText = rawText;
//            Log.d(TAG, "makeText: " + displayText);
            SpannableString spannableString = new SpannableString(String.format(Locale.US, "%s ...%s", displayText, collapseText));
//            Log.d(TAG, "makeText: " + spannableString.toString());
            ClickableSpan usernameClickableSpan = new ClickableSpan() {
                @Override
                public void onClick(@NonNull View widget) {
                    isCollapse = true;
                    makeText();
//                    Log.d(TAG, "onClick: ");
                }

                @Override
                public void updateDrawState(@NonNull TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setUnderlineText(false);
                }
            };
            int start = displayText.length();
            int end = start + 4 + collapseText.length();
            spannableString.setSpan(usernameClickableSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new StyleSpan(Typeface.BOLD), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new ForegroundColorSpan(Color.BLACK), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            setText(spannableString);
            setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    public void setRawText(String rawText) {
        this.rawText = rawText;
        makeText();
    }

    public void setCollapseText(String collapseText) {
        this.collapseText = collapseText;
    }

    public void setReadMoreText(String readMoreText) {
        this.readMoreText = readMoreText;
    }
}
