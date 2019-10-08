package com.commit451.internallinkmovementmethod

import android.text.Spannable
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.URLSpan
import android.view.MotionEvent
import android.widget.TextView

/**
 * Set this on a [TextView] and then you can potentially open links locally, modify the URLs,
 * etc
 */
abstract class InternalLinkMovementMethod : LinkMovementMethod() {

    /**
     * A link has been clicked
     * @param textView the textview
     * @param link the link
     * @param text the text of the href link
     */
    protected abstract fun onLinkClicked(textView: TextView, link: String, text: String?)

    override fun onTouchEvent(widget: TextView, buffer: Spannable, event: MotionEvent): Boolean {
        //http://stackoverflow.com/questions/1697084/handle-textview-link-click-in-my-android-app
        val action = event.action
        if (action == MotionEvent.ACTION_UP) {
            var x = event.x.toInt()
            var y = event.y.toInt()

            x -= widget.totalPaddingLeft
            y -= widget.totalPaddingTop

            x += widget.scrollX
            y += widget.scrollY

            val layout = widget.layout
            val line = layout.getLineForVertical(y)
            val off = layout.getOffsetForHorizontal(line, x.toFloat())

            val link = buffer.getSpans<URLSpan>(off, off, URLSpan::class.java)
            if (link.isNotEmpty()) {
                val span = link[0]
                val url = span.url
                val s = widget.text as Spanned
                val start = s.getSpanStart(span)
                val end = s.getSpanEnd(span)
                //http://stackoverflow.com/a/19750721/895797
                var title: String? = null
                if (start != -1 && end != -1) {
                    val wordThatWasClicked = s.subSequence(start, end)
                    title = wordThatWasClicked.toString()
                }
                onLinkClicked(widget, url, title)
                return true
            }
        }
        return super.onTouchEvent(widget, buffer, event)
    }
}
