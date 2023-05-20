package org.android.go.sopt.presentation.auth

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.android.go.sopt.util.SharedPreferences
import org.android.go.sopt.databinding.FragmentMyPageBinding

class MyPageFragment : Fragment() {
    private var _binding: FragmentMyPageBinding? = null
    private val binding: FragmentMyPageBinding
        get() = requireNotNull(_binding) { "앗! _binding이 null이다 !" }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setMyPage()
        clickLogout()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setMyPage() {

        binding.tvMyPageName.text = "이름 : ${SharedPreferences.MySharedPreferences.getUserName(requireContext())}"
        binding.tvMyPageSpecialty.text =
            "특기 : ${SharedPreferences.MySharedPreferences.getUserSpeciality(requireContext())}"

    }

    private fun checkLogoutDialog() {
        AlertDialog.Builder(this.requireActivity())
            .setTitle("")
            .setMessage("로그아웃하시겠습니까?")
            .setPositiveButton("Yes", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface, which: Int) {
                    Log.d("MyTag", "positive")
                    SharedPreferences.MySharedPreferences.delete(requireContext())
                    startActivity(Intent(requireContext(), SignInActivity::class.java))
                }
            })
            .setNegativeButton("NO", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface, which: Int) {
                    Log.d("MyTag", "negative")
                }
            })
            .create()
            .show()


    }

    private fun clickLogout() {
        binding.btnMyPageLogout.setOnClickListener {
            checkLogoutDialog()
        }

    }


}
