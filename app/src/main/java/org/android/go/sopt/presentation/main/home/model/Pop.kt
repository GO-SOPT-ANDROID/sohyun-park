package org.android.go.sopt.presentation.main.home.model

import androidx.annotation.DrawableRes

data class Pop(
    val id:Int,
    @DrawableRes val image: Int,
    val title: String,
    val singer: String
)

