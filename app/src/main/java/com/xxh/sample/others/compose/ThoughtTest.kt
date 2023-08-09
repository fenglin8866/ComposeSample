package com.xxh.sample.others.compose

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.HomeMax
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.xxh.sample.R
import com.xxh.sample.basics.udf.LoginViewModel

class ThoughtTest {
}



@Composable
private fun StateRead(modifier: Modifier = Modifier) {
    // State read without property delegate.
    val paddingState: MutableState<Dp> = remember {
        mutableStateOf(8.dp)
    }

    // State read with property delegate.
    var padding by remember {
        mutableStateOf(8.dp)
    }

    var offsetX by remember { mutableStateOf(8.dp) }

    var color by remember { mutableStateOf(Color.Red) }

    Column {

        Text(text = "test", modifier = modifier.padding(paddingState.value))

        //根据组合结果，Compose 界面会运行布局和绘制阶段。如果内容保持不变，并且大小和布局也未更改，界面可能会跳过这些阶段。
        Text(
            text = "test2",
            // The `padding` state is read in the composition phase
            // when the modifier is constructed.
            // Changes in `padding` will invoke recomposition.
            modifier = modifier.padding(padding)
        )


        Text(
            text = "Hello",
            modifier = modifier.offset {
                // The `offsetX` state is read in the placement step
                // of the layout phase when the offset is calculated.
                // Changes in `offsetX` restart the layout.
                IntOffset(offsetX.roundToPx(), 0)
            }
        )


        Canvas(modifier = modifier) {
            // The `color` state is read in the drawing phase
            // when the canvas is rendered.
            // Changes in `color` restart the drawing.
            drawRect(color)
        }

    }


    Box {
        val listState = rememberLazyListState()

        Image(imageVector = Icons.Filled.HomeMax, contentDescription = null,
            // Non-optimal implementation!
            Modifier.offset(
                with(LocalDensity.current) {
                    // State read of firstVisibleItemScrollOffset in composition
                    (listState.firstVisibleItemScrollOffset / 2).toDp()
                }
            )
        )

        //LazyColumn(state = listState)
    }

    Box {
        val listState = rememberLazyListState()

        Image(imageVector = Icons.Filled.HomeMax, contentDescription = null,
            Modifier.offset {
                // State read of firstVisibleItemScrollOffset in Layout
                IntOffset(x = 0, y = listState.firstVisibleItemScrollOffset / 2)
            }
        )

        //  LazyColumn(state = listState)
    }


    /**
     * 错误是否，产生了两帧的变化
     */
    Box {
        var imageHeightPx by remember { mutableStateOf(0) }

        Image(
            painter = painterResource(R.drawable.dog),
            contentDescription = "I'm above the text",
            modifier = Modifier
                .fillMaxWidth()
                .onSizeChanged { size ->
                    // Don't do this
                    imageHeightPx = size.height
                }
        )

        Text(
            text = "I'm below the image",
            modifier = Modifier.padding(
                top = with(LocalDensity.current) { imageHeightPx.toDp() }
            )
        )
    }

}

//================================构建界面的代码示例Start============================================



//================================构建界面的代码示例End============================================


//================================Compose 编程思想============================================
@Composable
fun Greeting(names: List<String>) {
    for (name in names) {
        Text("Hello $name")
    }
}

@Composable
fun ClickCounter(clicks: Int, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text("I've been clicked $clicks times")
    }
}

@Composable
fun SharedPrefsToggle(
    text: String,
    value: Boolean,
    onValueChanged: (Boolean) -> Unit
) {
    Row {
        Text(text)
        Checkbox(checked = value, onCheckedChange = onValueChanged)
    }
}

@Composable
fun ListComposable(myList: List<String>) {
    Row(horizontalArrangement = Arrangement.SpaceBetween) {
        Column {
            for (item in myList) {
                Text("Item: $item")
            }
        }
        Text("Count: ${myList.size}")
    }
}

@Composable
@Deprecated("Example with bug")
fun ListWithBug(myList: List<String>) {
    var items = 0

    Row(horizontalArrangement = Arrangement.SpaceBetween) {
        Column {
            for (item in myList) {
                Text("Item: $item")
                items++ // Avoid! Side-effect of the column recomposing.
            }
        }
        Text("Count: $items")
    }
}


/**
 * Display a list of names the user can click with a header
 */
@Composable
fun NamePicker(
    header: String,
    names: List<String>,
    onNameClicked: (String) -> Unit
) {
    Column {
        // this will recompose when [header] changes, but not when [names] changes
        Text(header, style = MaterialTheme.typography.bodyMedium)
        Divider()
        // LazyColumn is the Compose version of a RecyclerView.
        // The lambda passed to items() is similar to a RecyclerView.ViewHolder.
        LazyColumn {
            items(names) { name ->
                // When an item's [name] updates, the adapter for that item
                // will recompose. This will not recompose when [header] changes
                NamePickerItem(name, onNameClicked)
            }
        }
    }
}

/**
 * Display a single name the user can click.
 */
@Composable
private fun NamePickerItem(name: String, onClicked: (String) -> Unit) {
    Text(name, Modifier.clickable(onClick = { onClicked(name) }))
}