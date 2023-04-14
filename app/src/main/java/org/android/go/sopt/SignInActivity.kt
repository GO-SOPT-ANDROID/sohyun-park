package org.android.go.sopt

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import org.android.go.sopt.databinding.ActivityMainBinding

class SignInActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lateinit var id:String
        lateinit var pw:String
        lateinit var name:String
        lateinit var specialty:String

        val resultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                result ->
                if(result.resultCode == Activity.RESULT_OK){
                    id = result.data?.getStringExtra("id")?:""
                    pw = result.data?.getStringExtra("pw")?:""
                    name = result.data?.getStringExtra("name")?:""
                    specialty = result.data?.getStringExtra("specialty")?:""
                }
            }


        binding.btnSignup.setOnClickListener {

            val intent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent)

        }

        binding.btnLogin.setOnClickListener {

            if(binding.etId.text.toString()==id && binding.etPw.text.toString()==pw){

                val intent = Intent(this,MypageActivity::class.java)

                Snackbar.make(binding.root,"로그인에 성공했습니다.", Snackbar.LENGTH_SHORT).show()
                intent.putExtra("name",name)
                intent.putExtra("specialty",specialty)
                startActivity(intent)
            }
            else{

                Snackbar.make(binding.root,"로그인에 실패했습니다.", Snackbar.LENGTH_SHORT).show()
            }

        }


    }


}