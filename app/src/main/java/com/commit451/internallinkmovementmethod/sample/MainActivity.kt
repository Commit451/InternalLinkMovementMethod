package com.commit451.internallinkmovementmethod.sample

import android.os.Bundle
import android.text.Html
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.commit451.internallinkmovementmethod.InternalLinkMovementMethod

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TEXT = "Featured on <a href=\"https://android-arsenal.com/details/1/1762\">Android Arsenal</a>"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.text)
        textView.text = Html.fromHtml(TEXT)
        textView.movementMethod = object : InternalLinkMovementMethod() {
            override fun onLinkClicked(textView: TextView, link: String, text: String?) {
                Toast.makeText(this@MainActivity, "Link: $link with text: $text clicked", Toast.LENGTH_LONG)
                        .show()
            }
        }
    }
}
