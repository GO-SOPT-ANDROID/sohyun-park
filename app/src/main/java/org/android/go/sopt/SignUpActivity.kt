package org.android.go.sopt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import org.android.go.sopt.databinding.ActivitySignUpBinding


class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this,SignInActivity::class.java)


        binding.btnSignupComplete.setOnClickListener {

            if(binding.etSignupId.length() in 6..10 && binding.etSignupPw.length() in 8..12
                &&binding.etSignupName.text.isNotEmpty()&&binding.etSignupSpecialty.text.isNotEmpty()){

                Snackbar.make(binding.root,"회원가입이 완료되었습니다.",Snackbar.LENGTH_SHORT).show()

                intent.putExtra("id",binding.etSignupId.text.toString())
                intent.putExtra("pw",binding.etSignupPw.text.toString())
                intent.putExtra("name",binding.etSignupName.text.toString())
                intent.putExtra("specialty",binding.etSignupSpecialty.text.toString())
                setResult(RESULT_OK,intent)
                finish()
            }
            else{
                Snackbar.make(binding.root,"회원가입에 실패했습니다.",Snackbar.LENGTH_SHORT).show()
            }

        }




    }
}