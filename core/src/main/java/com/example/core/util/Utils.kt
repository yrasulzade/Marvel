package com.example.core.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.WindowManager
import com.example.core.R

object Utils {

    fun showLoadingDialog(context: Context?): Dialog {
        val progressDialog = Dialog(context!!)

        progressDialog.apply {
            show()
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND) // makes transparent background
            setContentView(R.layout.progress_dialog)
            setCancelable(false)
            setCanceledOnTouchOutside(false)
        }

        return progressDialog
    }

    inline fun <T> T?.ifNonNull(block: (T) -> Unit): T? {
        this?.let(block)
        return this@ifNonNull
    }
}
