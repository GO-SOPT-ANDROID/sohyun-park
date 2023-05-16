package org.android.go.sopt.presentation.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import org.android.go.sopt.databinding.FragmentHomeBinding
import org.android.go.sopt.presentation.main.home.adapter.PopAdapter
import org.android.go.sopt.presentation.main.home.model.HomeViewModel


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "앗! _binding is null !" }

    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var adapter: PopAdapter


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

        setAdapter()
        setUpSelectionTracker()
    }

    private fun setUpSelectionTracker() {
        val tracker = binding.rvHomePops.let { recyclerView ->
            SelectionTracker.Builder(
                "mySelection",
                recyclerView,
                PopAdapter.SelectionItemKeyProvider(recyclerView),
                PopAdapter.PopDetailsLookUp(recyclerView),
                StorageStrategy.createLongStorage()
            ).withSelectionPredicate(
                SelectionPredicates.createSelectSingleAnything()
            ).build()
        }

        adapter.setSelectionTracker(tracker)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun setAdapter() {
        adapter = PopAdapter(requireContext())
        binding.rvHomePops.adapter = adapter
        adapter.submitList(viewModel.mockPopLists)
    }

    fun scrollToTop() {
        binding.rvHomePops.smoothScrollToPosition(0)
    }


}