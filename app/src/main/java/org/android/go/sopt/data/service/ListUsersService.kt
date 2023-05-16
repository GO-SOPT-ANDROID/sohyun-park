package org.android.go.sopt.data.service


import org.android.go.sopt.data.dto.ResponseListUsersDto
import retrofit2.Call
import retrofit2.http.GET

interface ListUsersService {
    @GET("/api/users?.page=2")
    fun listUsers(): Call<ResponseListUsersDto>
}