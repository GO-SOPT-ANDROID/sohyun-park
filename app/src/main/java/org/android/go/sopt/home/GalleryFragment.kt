package org.android.go.sopt.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.android.go.sopt.R
import org.android.go.sopt.databinding.FragmentGalleryBinding
import org.android.go.sopt.home.adapter.GalleryAdapter

class GalleryFragment : Fragment() {
    private var _binding: FragmentGalleryBinding? = null
    private val binding: FragmentGalleryBinding
        get() = requireNotNull(_binding) { "앗! _binding is null!" }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vpGallery.adapter = GalleryAdapter().apply {
            setItemList(listOf(R.drawable.ic_lost_stars, R.drawable.ic_2002, R.drawable.ic_7years))
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }


}