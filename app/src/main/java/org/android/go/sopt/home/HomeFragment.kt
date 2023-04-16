package org.android.go.sopt.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.android.go.sopt.R
import org.android.go.sopt.databinding.FragmentHomeBinding
import org.android.go.sopt.home.adapter.RepoAdapter
import org.android.go.sopt.home.data.Repo

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "앗! _binding이 null이다 !" }

    private val mockRepoList = listOf<Repo>(
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
        )

    )

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
        val adapter = RepoAdapter(requireContext())
        binding.rvHomeRepos.adapter=adapter
        adapter.setRepoList(mockRepoList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}