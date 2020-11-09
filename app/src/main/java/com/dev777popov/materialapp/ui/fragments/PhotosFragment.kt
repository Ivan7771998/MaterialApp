package com.dev777popov.materialapp.ui.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.dev777popov.materialapp.R
import com.dev777popov.materialapp.tools.Const.Companion.CHANGE_THEME
import com.dev777popov.materialapp.ui.activity.SettingsActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.w3c.dom.Text


class PhotosFragment : Fragment() {

    private var editLayout: TextInputLayout? = null
    private var textEdit: TextInputEditText? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_photos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        view?.findViewById<Button>(R.id.btn_start)?.setOnClickListener {
            goToSecond()
        }

        editLayout = view?.findViewById<TextInputLayout>(R.id.edit_layout)
        textEdit = view?.findViewById<TextInputEditText>(R.id.edit_text)
        textEdit?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (shouldShowError()) showError() else editLayout?.error = null
            }

        })
    }

    private fun shouldShowError(): Boolean {
        val textLength: Int = textEdit?.text?.length ?: 0
        return textLength > 6
    }

    private fun showError() {
        editLayout?.error = getString(R.string.error)
    }

    private fun goToSecond() {
        startActivityForResult(SettingsActivity.newIntent(requireContext()), CHANGE_THEME)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                CHANGE_THEME -> {
                    requireActivity().recreate()
                }
            }
        }
    }

    companion object {
        fun newInstance() = PhotosFragment()
    }
}