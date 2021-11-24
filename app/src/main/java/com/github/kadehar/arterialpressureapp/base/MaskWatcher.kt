package com.github.kadehar.arterialpressureapp.base

import android.text.Editable
import android.text.TextWatcher

/*
https://medium.com/@diegoy_kuri/masks-in-android-edit-text-fields-33a2fd47f1af
 */
class MaskWatcher(private val mask: String) : TextWatcher {
    companion object {
        fun buildDefault() = MaskWatcher("###/###")
    }

    private var isRunning: Boolean = false
    private var isDeleting: Boolean = false

    override fun beforeTextChanged(charSequence: CharSequence, start: Int, count: Int, after: Int) {
        isDeleting = count > after
    }

    override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {

    }

    override fun afterTextChanged(editable: Editable) {
        if (isRunning || isDeleting) {
            return
        }
        isRunning = true

        val editableLength = editable.length
        if (editableLength < mask.length) {
            if (mask[editableLength] != '#') {
                editable.append(mask[editableLength])
            } else if (mask[editableLength-1] != '#') {
                editable.insert(editableLength-1, mask, editableLength-1, editableLength)
            }
        }

        isRunning = false
    }
}