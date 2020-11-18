package com.dev777popov.materialapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.dev777popov.materialapp.databinding.FragmentPhotosBinding
import com.dev777popov.materialapp.tools.DataStoreHelper
import com.dev777popov.materialapp.tools.ShowInfo
import com.dev777popov.materialapp.ui.adapter.PhotosAdapter
import com.dev777popov.materialapp.ui.vm.VmPhotosFragment

class PhotosFragment : Fragment() {

    private lateinit var vmPhotosFragment: VmPhotosFragment
    private var _binding: FragmentPhotosBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPhotosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVM()
        initView()
    }

    private fun initVM() {
        vmPhotosFragment = ViewModelProvider(requireActivity()).get(VmPhotosFragment::class.java)
    }

    private fun initView() {
        binding.fab.setOnClickListener {
            dispatchTakePictureIntent()
        }
        binding.listPhoto.setHasFixedSize(true)
        binding.listPhoto.layoutManager = GridLayoutManager(requireContext(), 2)
        vmPhotosFragment.updateImageList(DataStoreHelper(requireContext()))
        vmPhotosFragment.getImageLiveData().observe(viewLifecycleOwner, Observer {
            binding.listPhoto.adapter = PhotosAdapter(it)
        })
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(requireActivity().packageManager)?.also {
                val photoURL = DataStoreHelper(requireContext()).getPhotoURL()
                if (photoURL != null) {
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURL)
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == AppCompatActivity.RESULT_OK) {
            vmPhotosFragment.updateImageList(DataStoreHelper(requireContext()))
            ShowInfo.snack(binding.fab, "Фото успешно добавлено!")
        }
    }

    companion object {
        const val REQUEST_TAKE_PHOTO = 7777
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}