package com.xxh.sample.images

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.xxh.sample.R

// Snippets for https://developer.android.com/jetpack/compose/graphics/images/loading
@Preview
@Composable
fun LoadingImageFromDisk() {
    // [START android_compose_images_load_disk]
   /* Image(
        painter = painterResource(id = R.drawable.a1),
        contentDescription = stringResource(id = R.string.dog_content_description)
    )*/
    Image(
        ImageBitmap.imageResource(id = R.drawable.ab1_inversions),
        contentDescription = stringResource(id = R.string.dog_content_description)
    )
    // [END android_compose_images_load_disk]
}

@Preview
@Composable
fun LoadingImageFromInternet() {
    // [START android_compose_images_load_internet_coil]
    AsyncImage(
        model = "https://upload-images.jianshu.io/upload_images/11951295-b72ccc49112b90ae.png?imageMogr2/auto-orient/strip|imageView2/2/w/501/format/webp",
        contentDescription = "Translated description of what the image contains"
    )
    // [END android_compose_images_load_internet_coil]
}

@Composable
fun LoadingImageFromInternet2() {
    val imageUrl= "https://upload-images.jianshu.io/upload_images/11951295-b72ccc49112b90ae.png?imageMogr2/auto-orient/strip|imageView2/2/w/501/format/webp"
    val image = rememberAsyncImagePainter(model = imageUrl)
    Image(painter = image, contentDescription = "My Image", modifier = Modifier.size(200.dp))
}
