package org.android.go.sopt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import org.android.go.sopt.databinding.ActivitySignUpBinding


class SignUp : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this,MainActivity::class.java)


        binding.btnComplete.setOnClickListener {

            if(binding.etSignId.length() in 6..10 && binding.etSignPw.length() in 8..12
                &&binding.etSignName.text.isNotEmpty()&&binding.etSignSpecialty.text.isNotEmpty()){

                Snackbar.make(binding.root,"회원가입이 완료되었습니다.",Snackbar.LENGTH_SHORT).show()

                intent.putExtra("id",binding.etSignId.text.toString())
                intent.putExtra("pw",binding.etSignPw.text.toString())
                intent.putExtra("name",binding.etSignName.text.toString())
                intent.putExtra("specialty",binding.etSignSpecialty.text.toString())
                setResult(RESULT_OK,intent)
                finish()
            }
            else{
                Snackbar.make(binding.root,"회원가입에 실패했습니다.",Snackbar.LENGTH_SHORT).show()
            }

        }




    }
}