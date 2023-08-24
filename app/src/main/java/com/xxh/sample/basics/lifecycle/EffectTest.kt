package com.xxh.sample.basics.lifecycle

import android.media.Image
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@Composable
fun EffectTest() {
    Column(modifier = Modifier.padding(10.dp)) {
        LaunchedEffectScreen()
        // MoviesScreen()
    }
}

/**
 * LaunchedEffect:在某个可组合项的作用域内运行挂起函数
 *
 * 当 LaunchedEffect 进入组合时，它会启动一个协程，并将代码块作为参数传递。如果 LaunchedEffect 退出组合，协程将取消
 * 如果使用不同的键重组 LaunchedEffect，系统将取消现有协程，并在新的协程中启动新的挂起函数
 *
 * 在 Scaffold 中显示 Snackbar 是通过 SnackbarHostState.showSnackbar 函数完成的
 *
 */
@Composable
fun LaunchedEffectScreen(
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    var isHasError by remember {
        mutableStateOf(false)
    }
    // If the UI state contains an error, show snackbar
    if (isHasError) {
        // `LaunchedEffect` will cancel and re-launch if
        // `scaffoldState.snackbarHostState` changes
        LaunchedEffect(scaffoldState.snackbarHostState) {
            // Show snackbar using a coroutine, when the coroutine is cancelled the
            // snackbar will automatically dismiss. This coroutine will cancel whenever
            // `state.hasError` is false, and only start when `state.hasError` is true
            // (due to the above if-check), or if `scaffoldState.snackbarHostState` changes.
            scaffoldState.snackbarHostState.showSnackbar(
                message = "Error message",
                actionLabel = "Retry message"
            )
        }
    }

    /**
     * rememberCoroutineScope：获取组合感知作用域，以便在可组合项外启动协程
     *
     * 由于 LaunchedEffect 是可组合函数，因此只能在其他可组合函数中使用
     *
     * 为了在可组合项外启动协程，但存在作用域限制，以便协程在退出组合后自动取消
     * 如果需要手动控制一个或多个协程的生命周期,例如：在用户事件发生时取消动画
     * rememberCoroutineScope 是一个可组合函数，会返回一个 CoroutineScope，该 CoroutineScope 绑定到调用它的组合点。调用退出组合后，作用域将取消。
     * 重组时，重新执行该协程
     *
     */
    // Creates a CoroutineScope bound to the MoviesScreen's lifecycle
    val scope = rememberCoroutineScope()

    Scaffold(scaffoldState = scaffoldState) {
        Column(modifier = Modifier.padding(it)) {
            Button(onClick = {
                isHasError = !isHasError
            }) {
                Text("show snackbar")
            }

            Button(
                onClick = {
                    // Create a new coroutine in the event handler to show a snackbar
                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar("Something happened!")
                    }
                }
            ) {
                Text("Press me")
            }

            RememberUpdatedStateTest()
        }
    }
}

/**
 *
 * rememberUpdatedState：在效应中引用某个值，该效应在值改变时不应重启
 * 针对是一些特殊场景：LaunchedEffect内有耗时操作，同时在未完成操作时LaunchedEffect有重组，导致耗时重新开始。
 * 如果不希望耗时重新开始，同时改变逻辑在耗时之后。可以使用rememberUpdatedState。
 *
 *
 * key发生变化，LaunchedEffect会重启。
 * 当其中一个键参数发生变化时，LaunchedEffect 会重启。
 * 在某些情况下，可能希望在效应中捕获某个值，但如果该值发生变化，不希望效应重启
 *
 * 需要使用 rememberUpdatedState 来创建对可捕获和更新的该值的引用。
 * 这种方法对于包含长期操作的效应十分有用，因为重新创建和重启这些操作可能代价高昂或令人望而却步。
 *
 * 假设您的应用的 LandingScreen 在一段时间后消失。即使 LandingScreen 已重组，等待一段时间并发出时间已过通知的效应也不应该重启。
 * 为创建与调用点的生命周期相匹配的效应，永不发生变化的常量（如 Unit 或 true）将作为参数传递
 * LaunchedEffect(true) 与 while(true) 一样可疑。尽管 LaunchedEffect(true) 有一些有效用例，但请始终暂停使用这些用例，并确保那是您需要的内容。
 *
 * 使用 LaunchedEffect(true)。为了确保 onTimeout lambda 始终包含重组 LandingScreen 时使用的最新值，
 * onTimeout 需使用 rememberUpdatedState 函数封装。效应中应使用代码中返回的 State、currentOnTimeout
 *
 */
@Composable
fun RememberUpdatedStateTest() {
    var change by remember {
        mutableStateOf(true)
    }
    Column {
        Button(onClick = {
            change = !change
        }) {
            Text(text = "state change")
        }

        LandingScreen {
            Log.d("xxh", "end $change")
        }
        LandingScreen2(change) {
            Log.d("xxh", "end2$change")
        }
    }
}

@Composable
fun LandingScreen2(key: Boolean, onTimeout: () -> Unit) {
    LaunchedEffect(key) {
        Log.d("xxh", "start2")
        delay(5000)
        onTimeout()
    }
    Text(text = "RememberUpdatedStateTest")
}


@Composable
fun LandingScreen(onTimeout: () -> Unit) {
    // This will always refer to the latest onTimeout function that
    // LandingScreen was recomposed with
    val currentOnTimeout by rememberUpdatedState(onTimeout)
    // Create an effect that matches the lifecycle of LandingScreen.
    // If LandingScreen recomposes, the delay shouldn't start again.
    LaunchedEffect(true) {
        Log.d("xxh", "start")
        delay(5000)
        currentOnTimeout()
    }
    Text(text = "RememberUpdatedStateTest")
}


/**
 * DisposableEffect：需要清理的效应
 *
 * 对于需要在键发生变化或可组合项退出组合后进行清理的附带效应，请使用 DisposableEffect
 * 如果 DisposableEffect 键发生变化，可组合项需要处理（执行清理操作）其当前效应，并通过再次调用效应进行重置。
 * DisposableEffect 必须在其代码块中添加 onDispose 子句作为最终语句
 *
 * 在 onDispose 中放置空块并不是最佳做法。始终再想想是否存在某种更适合您使用场景的效应
 *
 * 可能需要使用 LifecycleObserver，根据 Lifecycle 事件发送分析事件。
 * 如需在 Compose 中监听这些事件，请根据需要使用 DisposableEffect 注册和取消注册观察器。
 *
 * 效应将 observer 添加到 lifecycleOwner。如果 lifecycleOwner 发生变化，系统会通过 lifecycleOwner 处理并再次重启效应。
 */
@Composable
fun HomeScreen(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onStart: () -> Unit, // Send the 'started' analytics event
    onStop: () -> Unit // Send the 'stopped' analytics event
) {
    // Safely update the current lambdas when a new one is provided
    val currentOnStart by rememberUpdatedState(onStart)
    val currentOnStop by rememberUpdatedState(onStop)

    // If `lifecycleOwner` changes, dispose and reset the effect
    DisposableEffect(lifecycleOwner) {
        // Create an observer that triggers our remembered callbacks
        // for sending analytics events
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                currentOnStart()
            } else if (event == Lifecycle.Event.ON_STOP) {
                currentOnStop()
            }
        }

        // Add the observer to the lifecycle
        lifecycleOwner.lifecycle.addObserver(observer)

        // When the effect leaves the Composition, remove the observer
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    /* Home screen content */
}

/**
 * SideEffect：将 Compose 状态发布为非 Compose 代码
 * 针对的是：将非Compose对象与Compose重组关联。
 *
 * 如需与非 Compose 管理的对象共享 Compose 状态，请使用 SideEffect 可组合项，因为每次成功重组时都会调用该可组合项
 *
 * 分析库可能允许通过将自定义元数据（在此示例中为“用户属性”）附加到所有后续分析事件，来细分用户群体。
 * 如需将当前用户的用户类型传递给的分析库，请使用 SideEffect 更新其值
 */
/*@Composable
fun rememberAnalytics(user: User): FirebaseAnalytics {
    val analytics: FirebaseAnalytics = remember {
         //...
        FirebaseAnalytics(null)
    }

    // On every successful composition, update FirebaseAnalytics with
    // the userType from the current User, ensuring that future analytics
    // events have this metadata attached
    SideEffect {
        analytics.setUserProperty("userType", user.userType)
    }
    return analytics
}*/



data class User(val userType: String)

/**
 * produceState：将非 Compose 状态转换为 Compose 状态
 * produceState 会启动一个协程，该协程将作用域限定为可将值推送到返回的 State 的组合。使用此协程将非 Compose 状态转换为 Compose 状态，例如将外部订阅驱动的状态（如 Flow、LiveData 或 RxJava）引入组合。
 *
 * 该制作工具在 produceState 进入组合时启动，在其退出组合时取消。返回的 State 重组；设置相同的值不会触发重组。
 *
 * 即使 produceState 创建了一个协程，它也可用于观察非挂起的数据源。如需移除对该数据源的订阅，请使用 awaitDispose 函数。
 *
 * 以下示例展示了如何使用 produceState 从网络加载图像。loadNetworkImage 可组合函数会返回可以在其他可组合项中使用的 State。
 *
 */
@Composable
fun loadNetworkImage(
    url: String,
    imageRepository: ImageRepository
): State<Result<Image>> {
    // Creates a State<T> with Result.Loading as initial value
    // If either `url` or `imageRepository` changes, the running producer
    // will cancel and will be re-launched with the new inputs.
    return produceState<Result<Image>>(initialValue = Result.Loading, url, imageRepository) {
        // In a coroutine, can make suspend calls
        val image = imageRepository.load(url)

        // Update State with either an Error or Success result.
        // This will trigger a recomposition where this State is read
        value = if (image == null) {
            Result.Error
        } else {
            Result.Success(image)
        }
    }
}

class ImageRepository {
    fun load(url: String): Image? {
        return null
    }

}


sealed interface Result<T> {
    object Loading : Result<Image>
    object Error : Result<Image>
    class Success<T>(t: T) : Result<T>
}


/**
 * derivedStateOf：将一个或多个状态对象转换为其他状态
 * 如果某个状态是从其他状态对象计算或派生得出的，请使用 derivedStateOf。使用此函数可确保仅当计算中使用的状态之一发生变化时才会进行计算。
 *
 * 以下示例展示了基本的“待办事项”列表，其中具有用户定义的高优先级关键字的任务将首先显示
 * derivedStateOf 保证每当 todoTasks 发生变化时，系统都会执行 highPriorityTasks 计算，并相应地更新界面。
 * 如果 highPriorityKeywords 发生变化，系统将执行 remember 代码块，并且会创建新的派生状态对象并记住该对象，以代替旧的对象。
 * 由于执行过滤以计算 highPriorityTasks 的成本很高，因此应仅在任何列表发生更改时才执行，而不是在每次重组时都执行。
 *
 * 新 derivedStateOf 生成的状态不会导致可组合项在声明它的位置重组，Compose 仅会对返回状态为已读的可组合项
 * （在本例中，指 LazyColumn 中的可组合项）进行重组
 *
 * 该代码还假设 highPriorityKeywords 的变化频率显著低于 todoTasks。否则，该代码会使用 remember(todoTasks, highPriorityKeywords) 而不是 derivedStateOf。
 */
@Composable
fun TodoList(highPriorityKeywords: List<String> = listOf("Review", "Unblock", "Compose")) {
    val todoTasks = remember { mutableStateListOf<String>() }
    // Calculate high priority tasks only when the todoTasks or highPriorityKeywords
    // change, not on every recomposition
    val highPriorityTasks by remember(highPriorityKeywords) {
        derivedStateOf {
            todoTasks.filter {
                //it.containsWord(highPriorityKeywords)
                it.contains("")
                //highPriorityKeywords.contains()
            }
        }
    }
    Box(Modifier.fillMaxSize()) {
        LazyColumn {
            items(highPriorityTasks) { /* ... */ }
            items(todoTasks) { /* ... */ }
        }
        /* Rest of the UI where users can add elements to the list */
    }
}

/**
 * snapshotFlow:将 Compose 的 State 转换为 Flow
 * 显示了一项附带效应，是系统在用户滚动经过要分析的列表的首个项目时记录下来的
 * listState.firstVisibleItemIndex 被转换为一个 Flow，从而可以受益于 Flow 运算符的强大功能
 */
@Composable
fun SnapshotFlowTest() {
    val listState = rememberLazyListState()

    LazyColumn(state = listState) {
        // ...
    }

    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .map { index -> index > 0 }
            .distinctUntilChanged()
            .filter { it == true }
            .collect {
                //MyAnalyticsService.sendScrolledPastFirstItemEvent()
            }
    }
}


/**
 *
 * 在上面显示的 DisposableEffect 代码中，效应将其块中使用的 lifecycleOwner 作为参数，因为对它们的任何更改都会导致效应重新开始。
 *
 * 无需使用 currentOnStart 和 currentOnStop 作为 DisposableEffect 键，因为我们使用了 rememberUpdatedState，所以它们的值在组合中绝不会发生变化。
 * 如果您没有传递 lifecycleOwner 作为参数，并且它发生变化，那么 HomeScreen 会重组，但 DisposableEffect 不会被处理和重新开始。这
 * 会导致出现问题，因为此后会使用错误的 lifecycleOwner。
 */
@Composable
fun HomeScreen2(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onStart: () -> Unit, // Send the 'started' analytics event
    onStop: () -> Unit // Send the 'stopped' analytics event
) {
    // These values never change in Composition
    val currentOnStart by rememberUpdatedState(onStart)
    val currentOnStop by rememberUpdatedState(onStop)

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            /* ... */
        }

        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}

/*
    作用   运行时机   是否启动协程

LaunchedEffect:在可组合项作用域内运行挂起函数
SideEffect:将Compose状态发布为非Compose代码
DisposableEffect:需要清理的效应

produceState:将非Compose状态转换为Compose状态
derivedStateOf:将一个或多个状态转换为其他状态
snapshotFlow:将Compose的State转换为Flow

 */


