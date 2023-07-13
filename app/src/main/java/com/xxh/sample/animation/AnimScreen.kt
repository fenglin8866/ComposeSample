package com.xxh.sample.animation

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

@Composable
fun AnimScreen() {
    //AnimVisibility()
    AnimVisibility5()
}

@Composable
fun AnimVisibility() {

    var visible by remember { mutableStateOf(true) }
    val density = LocalDensity.current

    Row(modifier = Modifier.padding(12.dp)) {
        Button(onClick = { visible = !visible }, modifier = Modifier.padding(end = 30.dp)) {
            Text(text = "变化")
        }
        AnimatedVisibility(
            visible = visible,
            enter = slideInVertically {
                // Slide in from 40 dp from the top.
                with(density) { -40.dp.roundToPx() }
            } + expandVertically(
                // Expand from the top.
                expandFrom = Alignment.Top
            ) + fadeIn(
                // Fade in with the initial alpha of 0.3f.
                initialAlpha = 0.3f
            ),
            exit = slideOutVertically() + shrinkVertically() + fadeOut()
        ) {
            Text(
                "Hello",
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        }


    }

}


@Composable
fun AnimVisibility2() {
    // Create a MutableTransitionState<Boolean> for the AnimatedVisibility.
    val state = remember {
        MutableTransitionState(false).apply {
            // Start the animation immediately.
            targetState = true
        }
    }
    Column {
        AnimatedVisibility(visibleState = state) {
            Text(text = "Hello, world!")
        }

        // Use the MutableTransitionState to know the current animation state
        // of the AnimatedVisibility.
        Text(
            text = when {
                state.isIdle && state.currentState -> "Visible"
                !state.isIdle && state.currentState -> "Disappearing"
                state.isIdle && !state.currentState -> "Invisible"
                else -> "Appearing"
            }
        )
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimVisibility3() {
    Row {
        var count by remember { mutableStateOf(0) }
        Button(onClick = { count++ }, modifier = Modifier.padding(10.dp)) {
            Text("Add")
        }
        AnimatedContent(
            targetState = count,
            transitionSpec = {
                // Compare the incoming number with the previous number.
                if (targetState > initialState) {
                    // If the target number is larger, it slides up and fades in
                    // while the initial (smaller) number slides up and fades out.
                    slideInVertically { height -> height } + fadeIn() with
                            slideOutVertically { height -> -height } + fadeOut()
                } else {
                    // If the target number is smaller, it slides down and fades in
                    // while the initial number slides down and fades out.
                    slideInVertically { height -> -height } + fadeIn() with
                            slideOutVertically { height -> height } + fadeOut()
                }.using(
                    // Disable clipping since the faded slide-in/out should
                    // be displayed out of bounds.
                    SizeTransform(clip = false)
                )
            }
        ) { targetCount ->
            Text(text = "$targetCount")
        }
    }
}

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialApi::class)
@Composable
fun AnimVisibility4() {
    var expanded by remember { mutableStateOf(false) }
    Surface(
        color = MaterialTheme.colors.primary,
        onClick = { expanded = !expanded },
        modifier = Modifier.wrapContentSize()
    ) {
        AnimatedContent(
            targetState = expanded,
            transitionSpec = {
                fadeIn(animationSpec = tween(150, 150)) with
                        fadeOut(animationSpec = tween(150)) using
                        SizeTransform { initialSize, targetSize ->
                            if (targetState) {
                                keyframes {
                                    // Expand horizontally first.
                                    IntSize(targetSize.width, initialSize.height) at 150
                                    durationMillis = 300
                                }
                            } else {
                                keyframes {
                                    // Shrink vertically first.
                                    IntSize(initialSize.width, targetSize.height) at 150
                                    durationMillis = 300
                                }
                            }
                        }
            }
        ) { targetExpanded ->
            if (targetExpanded) {
                Expanded()
            } else {
                ContentIcon()
            }
        }
    }
}

@Composable
fun Expanded() {
    Box(modifier = Modifier.size(200.dp)) {
        Text(text = "asdfjasdfkasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfassdfasdfassdfasdfasdfasdfasdf")
    }
}

@Composable
fun ContentIcon() {
    Box(modifier = Modifier.size(50.dp)) {
        Icon(Icons.Filled.Phone, contentDescription = null)
    }
}


@Composable
fun AnimVisibility5() {
    var currentPage by remember { mutableStateOf("A") }
    Row() {
        Button(onClick = {
            currentPage = if (currentPage == "A") {
                "B"
            } else {
                "A"
            }
        }) {
            Text(text = "Crossfade")
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(200.dp)
        ) {
            Crossfade(targetState = currentPage,

                ) { screen ->
                when (screen) {
                    "A" -> Text("Page A")
                    "B" -> Text("Page B")
                }
            }
        }
    }


}


@Composable
fun AnimVisibility6() {
    var currentState by remember { mutableStateOf(BoxState.Collapsed) }
    val transition = updateTransition(currentState, label = "")

    val rect by transition.animateRect(label = "") { state ->
        when (state) {
            BoxState.Collapsed -> Rect(0f, 0f, 100f, 100f)
            BoxState.Expanded -> Rect(100f, 100f, 300f, 300f)
        }
    }
    val borderWidth by transition.animateDp(label = "") { state ->
        when (state) {
            BoxState.Collapsed -> 1.dp
            BoxState.Expanded -> 0.dp
        }
    }

    val color by transition.animateColor(
        transitionSpec = {
            when {
                BoxState.Expanded isTransitioningTo BoxState.Collapsed ->
                    spring(stiffness = 50f)
                else ->
                    tween(durationMillis = 500)
            }
        }, label = ""
    ) { state ->
        when (state) {
            BoxState.Collapsed -> MaterialTheme.colors.primary
            BoxState.Expanded -> MaterialTheme.colors.background
        }
    }



}

enum class BoxState {
    Collapsed,
    Expanded
}


enum class DialerState { DialerMinimized, NumberPad }

@Composable
fun DialerButton(isVisibleTransition: Transition<Boolean>) {
    // `isVisibleTransition` spares the need for the content to know
    // about other DialerStates. Instead, the content can focus on
    // animating the state change between visible and not visible.
}

@Composable
fun NumberPad(isVisibleTransition: Transition<Boolean>) {
    // `isVisibleTransition` spares the need for the content to know
    // about other DialerStates. Instead, the content can focus on
    // animating the state change between visible and not visible.
}

@OptIn(ExperimentalTransitionApi::class)
@Composable
fun Dialer(dialerState: DialerState) {
    val transition = updateTransition(dialerState, label = "")
    Box {
        // Creates separate child transitions of Boolean type for NumberPad
        // and DialerButton for any content animation between visible and
        // not visible
        NumberPad(
            transition.createChildTransition {
                it == DialerState.NumberPad
            }
        )
        DialerButton(
            transition.createChildTransition {
                it == DialerState.DialerMinimized
            }
        )
    }
}