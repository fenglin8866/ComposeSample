/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com

import android.app.Application
import com.example.crane.util.UnsplashSizingInterceptor
import coil.ImageLoader
import coil.ImageLoaderFactory
import com.example.jetcaster.Graph
import com.example.jetnews.data.AppContainer
import com.example.jetnews.data.AppContainerImpl
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SampleApplication : Application() , ImageLoaderFactory {

    // AppContainer instance used by the rest of classes to obtain dependencies
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(this)
        Graph.provide(this)
    }

    /**
     * Create the singleton [ImageLoader].
     * This is used by [rememberImagePainter] to load images in the app.
     */
    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .components {
                add(UnsplashSizingInterceptor)
            }
            // Disable `Cache-Control` header support as some podcast images disable disk caching.
            .respectCacheHeaders(false)
            .build()
    }
}
