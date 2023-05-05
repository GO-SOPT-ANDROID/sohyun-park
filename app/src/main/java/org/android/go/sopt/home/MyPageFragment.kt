package org.android.go.sopt.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.android.go.sopt.databinding.FragmentMyPageBinding

class MyPageFragment:Fragment() {
    private var _binding: FragmentMyPageBinding?=null
    private val binding: FragmentMyPageBinding
        get() = requireNotNull(_binding){"앗! _binding이 null이다 !"}


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyPageBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setMyPage()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setMyPage() {

        binding.tvMypageName.text = "이름 : ${requireActivity().intent.getStringExtra("name")}"
        binding.tvMypageSpecialty.text = "특기 : ${requireActivity().intent.getStringExtra("specialty")}"

    }


}