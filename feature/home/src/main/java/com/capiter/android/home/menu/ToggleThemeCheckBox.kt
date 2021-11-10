package com.capiter.android.home.menu

import com.capiter.android.home.R




import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatCheckBox


/**
 * Animated button menu item check box to apply night/light mode.
 *
 * @see AppCompatCheckBox
 */
class ToggleThemeCheckBox @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : AppCompatCheckBox(context, attrs) {

    init {
        setButtonDrawable(R.drawable.asl_theme)
    }
}
