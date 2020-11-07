package com.dev777popov.materialapp.ui.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.dev777popov.materialapp.R
import com.dev777popov.materialapp.tools.Const.Companion.CHANGE_THEME
import com.dev777popov.materialapp.ui.activity.SettingsActivity

class PhotosFragment : Fragment() {

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