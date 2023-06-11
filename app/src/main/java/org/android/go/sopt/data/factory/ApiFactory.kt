package org.android.go.sopt.data.factory

import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.android.go.sopt.BuildConfig
import org.android.go.sopt.data.App
import org.android.go.sopt.data.service.ListUsersService
import org.android.go.sopt.data.service.SignInService
import org.android.go.sopt.data.service.SignUpService
import retrofit2.Retrofit

object ApiFactory {

    private const val AUTH_BASE_URL = BuildConfig.AUTH_BASE_URL
    private const val REQRES_BASE_URL = BuildConfig.REQRES_BASE_URL

    private val client by lazy {
        OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }).addNetworkInterceptor(FlipperOkhttpInterceptor(App.networkFlipperPlugin)).build()
    }

    val authRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(AUTH_BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .client(client).build()
    }

    val reqresRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(REQRES_BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .client(client).build()
    }

    inline fun <reified T> authCreate(): T = authRetrofit.create<T>(T::class.java)
    inline fun <reified T> reqresCreate(): T = reqresRetrofit.create<T>(T::class.java)

}

object ServicePool {
    val signUpService = ApiFactory.authCreate<SignUpService>()
    val signInService = ApiFactory.authCreate<SignInService>()
    val getUsersListService = ApiFactory.reqresCreate<ListUsersService>()

}