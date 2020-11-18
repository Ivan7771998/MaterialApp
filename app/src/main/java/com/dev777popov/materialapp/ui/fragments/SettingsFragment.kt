package com.dev777popov.materialapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import com.dev777popov.materialapp.R
import com.dev777popov.materialapp.databinding.FragmentSettingsBinding
import com.dev777popov.materialapp.ui.activity.BaseActivity

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnDefaultTheme.setOnClickListener { setTheme(ColorEnum.DEFAULT) }
        binding.btnOrangeTheme.setOnClickListener { setTheme(ColorEnum.ORANGE) }
        binding.btnPurpleTheme.setOnClickListener { setTheme(ColorEnum.PURPLE) }
        binding.btnRedTheme.setOnClickListener { setTheme(ColorEnum.RED) }
    }

    private fun setTheme(id: ColorEnum) {
        when (id) {
            ColorEnum.DEFAULT -> setDefaultTheme()
            ColorEnum.ORANGE -> setOrangeTheme()
            ColorEnum.PURPLE -> setPurpleTheme()
            ColorEnum.RED -> setRedTheme()
        }
        requireActivity().recreate()
    }

    private fun setRedTheme() = (requireActivity() as BaseActivity).changeTheme(R.style.RedAppTheme)
    private fun setPurpleTheme() = (requireActivity() as BaseActivity).changeTheme(R.style.PurpleAppTheme)
    private fun setOrangeTheme() = (requireActivity() as BaseActivity).changeTheme(R.style.OrangeAppTheme)
    private fun setDefaultTheme() = (requireActivity() as BaseActivity).changeTheme(R.style.BaseAppTheme)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    enum class ColorEnum(id: Int) {
        DEFAULT(1),
        ORANGE(2),
        PURPLE(3),
        RED(4);
    }
}

