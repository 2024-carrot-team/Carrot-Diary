package com.carrot.presentation.view.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.Window
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.content.res.AppCompatResources
import com.carrot.presentation.databinding.DialogLoadingBinding

class LoadingDialogBuilder(private val context: Context) {

    private var title: String? = null
    private var message: String? = null
    private var icon: Drawable? = null
    private var onDismiss: (() -> Unit)? = null

    fun setTitle(title: String): LoadingDialogBuilder {
        this.title = title
        return this
    }

    fun setTitle(@StringRes titleRes: Int): LoadingDialogBuilder {
        this.title = context.getString(titleRes)
        return this
    }

    fun setIcon(@DrawableRes drawableId: Int): LoadingDialogBuilder {
        this.icon = AppCompatResources.getDrawable(context, drawableId)
        return this
    }

    fun setMessage(message: String): LoadingDialogBuilder {
        this.message = message
        return this
    }

    fun setMessage(@StringRes messageRes: Int): LoadingDialogBuilder {
        this.message = context.getString(messageRes)
        return this
    }

    fun setOnDismissListener(callback: () -> Unit): LoadingDialogBuilder {
        this.onDismiss = callback
        return this
    }

    fun build(): Dialog {
        return Dialog(context).apply {
            window?.run {
                requestFeature(Window.FEATURE_NO_TITLE)
                setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
            setCancelable(false)

            val binding = DialogLoadingBinding.inflate(layoutInflater)
            binding.apply {
                root.clipToOutline = true

                title?.let { title ->
                    textViewTitle.text = title
                }
                icon?.let { icon ->
                    imageViewIcon.setImageDrawable(icon)
                }
                message?.let { message ->
                    textViewMessage.text = message
                }
            }
            setContentView(binding.root)
            setOnDismissListener {
                onDismiss?.invoke()
            }
        }
    }

    fun show(): Dialog {
        return build().also { it.show() }
    }
}
