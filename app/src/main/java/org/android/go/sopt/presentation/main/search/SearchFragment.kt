package org.android.go.sopt.presentation.main.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import org.android.go.sopt.databinding.FragmentSearchBinding
import org.android.go.sopt.presentation.main.search.model.UserViewModel
import org.android.go.sopt.util.showToast

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding
        get() = requireNotNull(_binding) { "앗! _binding is null !" }
    private val viewModel by viewModels<UserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getUserList()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun getUserList() {
        viewModel.getUserList(
            binding.rvSearchUsers,
            message = { str -> binding.root.showToast(str) }
        )
    }
}