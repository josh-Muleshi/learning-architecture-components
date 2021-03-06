package com.yveskalumenoble.architecturecomponents.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

import com.yveskalumenoble.architecturecomponents.R
import com.yveskalumenoble.architecturecomponents.data.entity.Picture
import com.yveskalumenoble.architecturecomponents.databinding.FragmentPicturesBinding
import com.yveskalumenoble.architecturecomponents.ui.adapter.PictureAdapter
import com.yveskalumenoble.architecturecomponents.ui.viewmodel.PictureViewModel
import com.yveskalumenoble.architecturecomponents.util.OnItemClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class PicturesFragment : Fragment(),OnItemClickListener {

    private lateinit var binding: FragmentPicturesBinding
    private val pictureViewModel by viewModel<PictureViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_pictures,container,false)

        val adapter = PictureAdapter(this)
        binding.adapter = adapter
        pictureViewModel.pictures.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        return binding.root
    }

    override fun onItemClick(picture: Picture) {
        this.findNavController().navigate(PicturesFragmentDirections.actionPicturesFragmentToSinglePictureFragment(picture))
    }

}
