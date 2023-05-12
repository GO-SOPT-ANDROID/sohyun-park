package org.android.go.sopt.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.android.go.sopt.*
import org.android.go.sopt.databinding.FragmentSearchBinding
import org.android.go.sopt.home.adapter.UserAdapter
import retrofit2.Call
import retrofit2.Response

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding
        get() = requireNotNull(_binding) { "앗! _binding이 null이다 !" }
    private lateinit var adapter: UserAdapter
    private val listUsersService = ServicePool.listUsersService

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
        super.onDestroyView()
        _binding = null
    }

    private fun setAdapter(itemList: List<ResponseListUsersDto.ListUsersData>?) {
        adapter = UserAdapter(requireContext())
        binding.rvSearchUsers.adapter = adapter
        adapter.submitList(itemList)

    }


    private fun getUserList() {
        listUsersService.listUsers().enqueue(
            object : retrofit2.Callback<ResponseListUsersDto> {
                override fun onResponse(
                    call: Call<ResponseListUsersDto>,
                    response: Response<ResponseListUsersDto>
                ) {
                    if (response.isSuccessful) {
                        val itemList = response.body()?.data
                        setAdapter(itemList)

                    } else {
                        // 응답 실패!
                        binding.root.showToast("서버통신 실패(40X)")

                    }

                }

                override fun onFailure(call: Call<ResponseListUsersDto>, t: Throwable) {
                    binding.root.showToast("서버통신 실패(응답값 X)")
                }
            }
        )

    }

}