package com.example.covid19.helper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class GeoWebViewClient extends WebViewClient {
    Context context;

    public GeoWebViewClient(Context context) {
        this.context = context;
    }

    private static final String TEL_PREFIX = "tel:";
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        // When user clicks a hyperlink, load in the existing WebView
        view.loadUrl(url);

        if(url.startsWith(TEL_PREFIX)) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse(url));
             context.startActivity(intent);
            return true;
        }
        return false;
    }


}
