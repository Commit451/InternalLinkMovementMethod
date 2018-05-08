package com.commit451.internallinkmovementmethod.sample;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;
import android.widget.Toast;

import com.commit451.internallinkmovementmethod.InternalLinkMovementMethod;

public class MainActivity extends AppCompatActivity {

    private static final String TEXT = "Featured on <a href=\"https://android-arsenal.com/details/1/1762\">Android Arsenal</a>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.text);
        textView.setText(Html.fromHtml(TEXT));
        textView.setMovementMethod(new InternalLinkMovementMethod() {
            @Override
            protected void onLinkClicked(@NonNull TextView textView, @NonNull String link, @Nullable String text) {
                Toast.makeText(MainActivity.this, "Link: " + link + " with text: " + text + " clicked", Toast.LENGTH_LONG)
                        .show();
            }
        });
    }
}
