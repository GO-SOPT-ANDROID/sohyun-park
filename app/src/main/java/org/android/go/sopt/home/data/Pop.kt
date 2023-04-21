package org.android.go.sopt.home.data

import androidx.annotation.DrawableRes

data class Pop(
    val id:Int,
    @DrawableRes val image: Int,
    val title: String,
    val singer: String
)
