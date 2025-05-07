package com.example.androidpracticumcustomview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

class CustomContainer @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

    override fun addView(child: View?) {
        if (childCount >= 2) {
            Toast.makeText(context, "В контейнер помещено более двух дочерних элементов!", Toast.LENGTH_SHORT).show()

            if (context is android.app.Activity) {
                (context as android.app.Activity).finish()
            }
            return
        }
        super.addView(child)
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            measureChild(child, widthMeasureSpec, heightMeasureSpec)
        }

        setMeasuredDimension(width, height)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

        val width = r - l
        val height = b - t
        val centerX = width / 2
        val centerY = height / 2

        for (i in 0 until childCount) {

            val child = getChildAt(i)
            val childWidth = child.measuredWidth
            val childHeight = child.measuredHeight

            val left = centerX - childWidth / 2
            val top = when (i) {
                0 -> 0
                1 -> height - childHeight
                else -> 0
            }

            child.layout(left, top, left + childWidth, top + childHeight)

            child.alpha = 0f
            child.translationY = centerY.toFloat() - top

            child.animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(5000)
                .start()
        }
    }
}