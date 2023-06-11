package org.android.go.sopt.presentation.main.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import org.android.go.sopt.R
import org.android.go.sopt.databinding.FragmentSearchBinding
import org.android.go.sopt.presentation.main.search.model.UserViewModel
import org.android.go.sopt.util.showToast

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding
        get() = requireNotNull(_binding) { R.string.binding_null }
    private val viewModel by viewModels<UserViewModel>()
    private lateinit var dialog: LoadingDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        dialog = LoadingDialog(requireContext())
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getUserList()
        observe()

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

    private fun observe() {
        dialog.show()
        viewModel.getUserListResult.observe(viewLifecycleOwner) {
            dialog.dismiss()
        }

    }
}