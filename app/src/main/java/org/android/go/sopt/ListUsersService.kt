package org.android.go.sopt


import org.android.go.sopt.home.ResponseListUsersDto
import retrofit2.Call
import retrofit2.http.GET

interface ListUsersService {
    @GET("/api/users?.page=2")
    fun listUsers(): Call<ResponseListUsersDto>
}