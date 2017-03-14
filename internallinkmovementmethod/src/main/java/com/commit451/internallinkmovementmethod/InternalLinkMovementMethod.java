package com.commit451.internallinkmovementmethod;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.Spannable;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Set this on a {@link TextView} and then you can potentially open links locally, modify the URLs,
 * etc
 */
public abstract class InternalLinkMovementMethod extends LinkMovementMethod {

    /**
     * A link has been clicked
     * @param textView the textview
     * @param link the link
     * @param text the text of the href link
     */
    protected abstract void onLinkClicked(@NonNull TextView textView, @NonNull String link, @Nullable String text);

    @Override
    public boolean onTouchEvent(TextView widget, Spannable buffer, MotionEvent event) {
        //http://stackoverflow.com/questions/1697084/handle-textview-link-click-in-my-android-app
        int action = event.getAction();
        if (action == MotionEvent.ACTION_UP) {
            int x = (int) event.getX();
            int y = (int) event.getY();

            x -= widget.getTotalPaddingLeft();
            y -= widget.getTotalPaddingTop();

            x += widget.getScrollX();
            y += widget.getScrollY();

            Layout layout = widget.getLayout();
            int line = layout.getLineForVertical(y);
            int off = layout.getOffsetForHorizontal(line, x);

            URLSpan[] link = buffer.getSpans(off, off, URLSpan.class);
            if (link.length > 0) {
                URLSpan span = link[0];
                String url = span.getURL();
                Spanned s = (Spanned) widget.getText();
                int start = s.getSpanStart(span);
                int end = s.getSpanEnd(span);
                //http://stackoverflow.com/a/19750721/895797
                String title = null;
                if (start != -1 && end != -1) {
                    CharSequence wordThatWasClicked = s.subSequence(start, end);
                    title = wordThatWasClicked.toString();
                }
                onLinkClicked(widget, url, title);
                return true;
            }
        }
        return super.onTouchEvent(widget, buffer, event);
    }
}
