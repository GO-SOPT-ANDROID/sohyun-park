package org.android.go.sopt.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StableIdKeyProvider
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.ConcatAdapter
import org.android.go.sopt.databinding.FragmentHomeBinding
import org.android.go.sopt.home.adapter.PopItemAdapter
import org.android.go.sopt.home.adapter.PopHeaderAdapter
import org.android.go.sopt.home.data.HomeViewModel


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "앗! _binding is null !" }

    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var popItemAdapter: PopItemAdapter




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        popItemAdapter = PopItemAdapter(requireContext())
        val popHeaderAdapter = PopHeaderAdapter(requireContext())

        setAdapter(popHeaderAdapter, popItemAdapter)
        setUpSelectionTracker(popItemAdapter)


    }

    private fun setUpSelectionTracker(adapter:PopItemAdapter){
       val tracker = binding.rvHomePops.let { recyclerView ->
            SelectionTracker.Builder(
                "mySelection",
                recyclerView,
                PopItemAdapter.RecyclerViewIdKeyProvider(recyclerView),
                PopItemAdapter.PopDetailsLookUp(recyclerView),
                StorageStrategy.createLongStorage()
            ).withSelectionPredicate(
                SelectionPredicates.createSelectSingleAnything()
            ).build()
        }

        adapter.setSelectionTracker(tracker)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setAdapter(headerAdapter: PopHeaderAdapter, itemAdapter: PopItemAdapter) {
        binding.rvHomePops.adapter = ConcatAdapter(headerAdapter, itemAdapter)
        itemAdapter.submitList(viewModel.mockPopLists)
    }

    fun scrollToTop() {
        binding.rvHomePops.smoothScrollToPosition(0)
    }


}