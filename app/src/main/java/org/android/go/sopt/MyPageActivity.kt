package org.android.go.sopt


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.android.go.sopt.databinding.ActivityMyPageBinding

class MyPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setMyPage()

    }

    private fun setMyPage() {

        binding.tvMypageName.text = "이름 : ${intent.getStringExtra("name")}"
        binding.tvMypageSpecialty.text = "특기 : ${intent.getStringExtra("specialty")}"

    }


}