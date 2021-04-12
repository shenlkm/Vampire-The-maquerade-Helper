package com.example.vampiremasterhelper.views

import android.app.Activity
import android.app.Dialog
import android.view.Gravity
import android.view.MotionEvent
import android.view.WindowManager
import android.widget.ListPopupWindow
import com.example.vampiremasterhelper.R

class BottomDialogView constructor(activity: Activity, private val height: Int = ListPopupWindow.WRAP_CONTENT) : Dialog(activity, R.style.PauseDialog) {

    private var oldy = 0F

    override fun onWindowAttributesChanged(params: WindowManager.LayoutParams?) {
        params?.gravity = Gravity.BOTTOM
        params?.width = ListPopupWindow.MATCH_PARENT
        params?.height = height
        super.onWindowAttributesChanged(params)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        window?.let {
            it.decorView.y = 0F
            oldy = it.decorView.y
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                oldy = event.y
            }
            MotionEvent.ACTION_MOVE -> {
                val dY = event.y - oldy
                if (dY > 0) {
                    window?.let {
                        it.decorView.y += dY
                    }
                }
                if (dY > 100) {
                    dismiss()
                }
            }
        }
        return super.onTouchEvent(event)
    }
}