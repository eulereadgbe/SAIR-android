package com.seafdec.sair;

import android.content.Intent;
import android.net.MailTo;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebViewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if(url.startsWith("mailto:")) {
            MailTo mt = MailTo.parse(url);
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_EMAIL, new String[]{mt.getTo()});
            i.putExtra(Intent.EXTRA_SUBJECT, mt.getSubject());
            i.putExtra(Intent.EXTRA_CC, mt.getCc());
            i.putExtra(Intent.EXTRA_TEXT, mt.getBody());
            view.getContext().startActivity(i);
            view.reload();
            return true;
        }

            if (Uri.parse(url).getHost().endsWith("repository.seafdec.org.ph") || (Uri.parse(url).getHost().contains("hdl.handle.net".toLowerCase()))){
            return false;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        view.getContext().startActivity(intent);
        return true;
    }
}
