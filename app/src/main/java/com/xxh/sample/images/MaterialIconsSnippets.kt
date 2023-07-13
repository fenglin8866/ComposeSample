package com.xxh.sample.images

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.LocalContentColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.xxh.sample.R

/*
* Copyright 2022 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
@Preview
@Composable
fun MaterialIconsSnippets() {
    // [START android_compose_images_icon_resources]
    Column {
        Icon(
            painter = painterResource(R.drawable.baseline_directions_bus_24),
            contentDescription = stringResource(id = R.string.bus_content_description)
        )
        // [END android_compose_images_icon_resources]

        // [START android_compose_images_icon_prebuilt]
        Icon(
            Icons.Filled.Lock,
            tint= Color.Blue,
            modifier= Modifier.background(Color.White),
            contentDescription = stringResource(id = R.string.shopping_cart_content_desc)
        )
        // [END android_compose_images_icon_prebuilt]

        Icon(
            Icons.Filled.Lock,
           // tint= Color.Blue,
            tint= LocalContentColor.current.copy(alpha=0.5f),
            contentDescription = stringResource(id = R.string.shopping_cart_content_desc)
        )
    }


    Canvas(modifier = Modifier.graphicsLayer {  } ){

    }

}