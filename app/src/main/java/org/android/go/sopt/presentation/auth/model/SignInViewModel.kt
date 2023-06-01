package org.android.go.sopt.presentation.auth.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.android.go.sopt.data.dto.RequestSignInDto
import org.android.go.sopt.data.dto.ResponseSignInDto
import org.android.go.sopt.data.factory.ServicePool
import org.android.go.sopt.util.enqueueUtil

class SignInViewModel : ViewModel() {

    private val _signInResult: MutableLiveData<ResponseSignInDto> = MutableLiveData()
    val signInResult: LiveData<ResponseSignInDto> = _signInResult

    private val signInService = ServicePool.signInService

    fun signIn(id: String, password: String, message: (String) -> Unit) {
        signInService.signIn(
            RequestSignInDto(
                id,
                password
            )
        ).enqueueUtil(
            onSuccess = {
                _signInResult.value = it
                message.invoke(it.message)
            },
            onError = {
                message.invoke("error:${it}")
            }
        )
    }
}