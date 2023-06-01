package org.android.go.sopt.presentation.main.search.model


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.R
import org.android.go.sopt.data.dto.ResponseListUsersDto
import org.android.go.sopt.data.factory.ServicePool
import org.android.go.sopt.presentation.main.search.adapter.UserAdapter
import org.android.go.sopt.util.enqueueUtil

class UserViewModel : ViewModel() {

    private val _getUserListResult: MutableLiveData<ResponseListUsersDto> = MutableLiveData()
    val getUserListResult: LiveData<ResponseListUsersDto> = _getUserListResult

    private val getUsersListService = ServicePool.getUsersListService

    fun getUserList(recyclerView: RecyclerView,  message: (String) -> Unit) {
        getUsersListService.listUsers().enqueueUtil(
            onSuccess = {
                _getUserListResult.value = it
                recyclerView.adapter = UserAdapter().apply { submitList(it.data) }
                message.invoke("서버 통신 성공")
            },
            onError = {
                message.invoke("error:${it}")
            }
        )
    }
}