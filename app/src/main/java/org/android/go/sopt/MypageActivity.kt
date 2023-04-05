package org.android.go.sopt


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.android.go.sopt.databinding.ActivityMypageBinding

class MypageActivity : AppCompatActivity() {
    lateinit var binding: ActivityMypageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMypageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvMypageName.text="이름 : " + intent.getStringExtra("name")
        binding.tvMypageSpecialty.text="특기 : " + intent.getStringExtra("specialty")

    }

}