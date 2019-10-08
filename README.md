# InternalLinkMovementMethod
LinkMovementMethod that allows for custom overrides

[![Build Status](https://travis-ci.org/Commit451/InternalLinkMovementMethod.svg?branch=master)](https://travis-ci.org/Commit451/InternalLinkMovementMethod) [![](https://jitpack.io/v/Commit451/InternalLinkMovementMethod.svg)](https://jitpack.io/#Commit451/InternalLinkMovementMethod)

# Usage

```java
textView.setText(Html.fromHtml(TEXT))
textView.movementMethod = object : InternalLinkMovementMethod() {
    override fun onLinkClicked(textView: TextView, link: String, text: String?) {
        Toast.makeText(this@MainActivity, "Link: $link with text: $text clicked", Toast.LENGTH_LONG)
                .show()
    }
}
```

License
--------

    Copyright 2019 Commit 451

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
