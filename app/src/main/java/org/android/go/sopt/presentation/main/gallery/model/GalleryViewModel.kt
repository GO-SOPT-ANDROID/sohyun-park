package org.android.go.sopt.presentation.main.gallery.model

import androidx.lifecycle.ViewModel
import org.android.go.sopt.R

class GalleryViewModel : ViewModel() {
    val mockGalleryLists = listOf(
        Gallery(
            id = 0,
            image = R.drawable.ic_lost_stars
        ),
        Gallery(
            id = 1,
            image = R.drawable.ic_2002
        ),
        Gallery(
            id = 2,
            image = R.drawable.ic_7years
        ),
        Gallery(
            id = 3,
            image = R.drawable.ic_attention
        ),
        Gallery(
            id = 4,
            image = R.drawable.ic_numb_little_bug
        )
    )
}