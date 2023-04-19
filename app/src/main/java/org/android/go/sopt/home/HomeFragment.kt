package org.android.go.sopt.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.ConcatAdapter
import org.android.go.sopt.R
import org.android.go.sopt.databinding.FragmentHomeBinding
import org.android.go.sopt.home.adapter.RepoItemAdapter
import org.android.go.sopt.home.adapter.RepoHeaderAdapter
import org.android.go.sopt.home.data.Repo


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "앗! _binding이 null이다 !" }

    private val viewModel by viewModels<HomeViewModel>()

    class HomeViewModel : ViewModel() {
        val mockRepoList = listOf<Repo>(
            Repo(
                image = R.drawable.github,
                name = "CatchMe",
                author = "Sohyun-Park"
            ),
            Repo(
                image = R.drawable.github,
                name = "FILL-IN",
                author = "Sohyun-Park"
            ),
            Repo(
                image = R.drawable.github,
                name = "Hacker",
                author = "Sohyun-Park"
            ),
            Repo(
                image = R.drawable.github,
                name = "BeMe",
                author = "Sohyun-Park"
            ),
            Repo(
                image = R.drawable.github,
                name = "BeMe",
                author = "Sohyun-Park"
            ),
            Repo(
                image = R.drawable.github,
                name = "BeMe",
                author = "Sohyun-Park"
            ),
            Repo(
                image = R.drawable.github,
                name = "BeMe",
                author = "Sohyun-Park"
            )


        )
    }


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
        val repoItemAdapter = RepoItemAdapter(requireContext())
        val repoHeaderAdpater = RepoHeaderAdapter(requireContext())
        setAdapter(repoHeaderAdpater, repoItemAdapter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setAdapter(headerAdapter: RepoHeaderAdapter, itemAdapter: RepoItemAdapter) {
        binding.rvHomeRepos.adapter = ConcatAdapter(headerAdapter, itemAdapter)
        itemAdapter.setRepoList(viewModel.mockRepoList)
    }

    fun scrollToTop(){
        binding.rvHomeRepos.smoothScrollToPosition(0)
    }



}