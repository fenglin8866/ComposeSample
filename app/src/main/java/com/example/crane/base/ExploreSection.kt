/*
 * Copyright 2020 The Android Open Source Project
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

package com.example.crane.base

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.example.crane.data.ExploreModel
import com.example.crane.home.OnExploreItemClicked
import com.example.crane.ui.BottomSheetShape
import com.example.crane.ui.crane_caption
import com.example.crane.ui.crane_divider_color
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest.Builder
import com.xxh.sample.R
import com.google.accompanist.insets.navigationBarsHeight
import kotlinx.coroutines.launch

@Composable
fun ExploreSection(
    modifier: Modifier = Modifier,
    title: String,
    exploreList: List<ExploreModel>,
    onItemClicked: OnExploreItemClicked
) {
    Surface(modifier = modifier.fillMaxSize(), color = Color.White, shape = BottomSheetShape) {
        Column(modifier = Modifier.padding(start = 24.dp, top = 20.dp, end = 24.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.caption.copy(color = crane_caption)
            )
            Spacer(Modifier.height(8.dp))
            // TODO Codelab: derivedStateOf step
            // TODO: Show "Scroll to top" button when the first item of the list is not visible
           /* val listState = rememberLazyListState()
            ExploreList(exploreList, onItemClicked, listState = listState)*/

            Box(Modifier.weight(1f)) {
                val listState = rememberLazyListState()
                ExploreList(exploreList, onItemClicked, listState = listState)

                // DO NOT DO THIS - It's executed on every recomposition
                val showButton2 = listState.firstVisibleItemIndex > 0

                /**
                 * todo 8.derivedStateOf的使用
                 * 当您想要的某个 Compose State 衍生自另一个 State 时，请使用 derivedStateOf。
                 * 每当内部状态发生变化时，系统都会执行 derivedStateOf 计算块，但只有当计算结果与上一项不同时，可组合函数才会重组。这样可以最大限度地减少读取 showButton 的函数的重组次数。
                 * 在这种情况下，使用 derivedStateOf API 是一种更好且更高效的替代方案。我们还会使用 remember API 来封装调用，因此计算得出的值在重组后继续有效。
                 */
                // Show the button if the first visible item is past
                // the first item. We use a remembered derived state to
                // minimize unnecessary compositions

                val showButton by remember {
                    derivedStateOf {
                        listState.firstVisibleItemIndex > 0
                    }
                }
                if (showButton) {
                    val coroutineScope = rememberCoroutineScope()
                    FloatingActionButton(
                        backgroundColor = MaterialTheme.colors.primary,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .navigationBarsPadding()
                            .padding(bottom = 8.dp),
                        onClick = {
                            coroutineScope.launch {
                                listState.scrollToItem(0)
                            }
                        }
                    ) {
                        Text("Up!")
                    }
                }
            }
        }
    }
}

@Composable
private fun ExploreList(
    exploreList: List<ExploreModel>,
    onItemClicked: OnExploreItemClicked,
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState()
) {
    LazyColumn(modifier = modifier, state = listState) {
        items(exploreList) { exploreItem ->
            Column(Modifier.fillParentMaxWidth()) {
                ExploreItem(
                    modifier = Modifier.fillParentMaxWidth(),
                    item = exploreItem,
                    onItemClicked = onItemClicked
                )
                Divider(color = crane_divider_color)
            }
        }
        item {
            Spacer(modifier = Modifier.navigationBarsHeight())
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
private fun ExploreItem(
    modifier: Modifier = Modifier,
    item: ExploreModel,
    onItemClicked: OnExploreItemClicked
) {
    Row(
        modifier = modifier
            .clickable { onItemClicked(item) }
            .padding(top = 12.dp, bottom = 12.dp)
    ) {
        ExploreImageContainer {
            Box {
                val painter = rememberAsyncImagePainter(
                    model = Builder(LocalContext.current)
                        .data(item.imageUrl)
                        .crossfade(true)
                        .build()
                )
                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                )

                if (painter.state is AsyncImagePainter.State.Loading) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_crane_logo),
                        contentDescription = null,
                        modifier = Modifier
                            .size(36.dp)
                            .align(Alignment.Center),
                    )
                }
            }
        }
        Spacer(Modifier.width(24.dp))
        Column {
            Text(
                text = item.city.nameToDisplay,
                style = MaterialTheme.typography.h6
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = item.description,
                style = MaterialTheme.typography.caption.copy(color = crane_caption)
            )
        }
    }
}

@Composable
private fun ExploreImageContainer(content: @Composable () -> Unit) {
    Surface(Modifier.size(width = 60.dp, height = 60.dp), RoundedCornerShape(4.dp)) {
        content()
    }
}
