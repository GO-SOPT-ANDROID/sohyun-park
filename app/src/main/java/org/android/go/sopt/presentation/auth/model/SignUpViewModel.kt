package org.android.go.sopt.presentation.auth.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.android.go.sopt.data.dto.RequestSignUpDto
import org.android.go.sopt.data.dto.ResponseSignUpDto
import org.android.go.sopt.data.factory.ServicePool
import org.android.go.sopt.util.enqueueUtil

class SignUpViewModel : ViewModel() {

    private val _signUpResult: MutableLiveData<ResponseSignUpDto> = MutableLiveData()
    val signUpResult: LiveData<ResponseSignUpDto> = _signUpResult

    private val signUpService = ServicePool.signUpService

    fun signUp(
        id: String,
        password: String,
        name: String,
        skill: String,
        message: (String) -> Unit
    ) {
        signUpService.signUp(
            RequestSignUpDto(
                id,
                password,
                name,
                skill
            )
        ).enqueueUtil(
            onSuccess = {
                _signUpResult.value = it
                message.invoke(it.message)
            },
            onError = {
                message.invoke("error:${it}")
            }
        )
    }
}