package com.xxh.sample.others.compose

import androidx.compose.animation.Animatable
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

/**
 * 说明：Compose不是一个单体式项目，它是由多个模块构建而成，这些模块组合在一起，形成一个完成架构
 *  开发原则是：提供可以组合在一起的重点突出的小块功能片段。而不是几个单体式组件
 *  理念：构建可重复使用的分层组件，这意味着不应该始终以构建较低级别的构建块为目标，许多较高级别的组件不仅能够提供更多功能，而且通常还会融入最佳实践，例如支持无障碍功能等。
 *
 *  1.使用适当的抽象级别来构建应用或库，了解何时可以“降级”到较低级别，以获取更多的控制权或更高的自定义程度。
 *  2.尽可能减少依赖项
 *
 *
 * 优点：1.控制  2.自定义
 */
class FrameDemo {
}

/**
 * 控制：越高级别的组件往往能完成操作越多，但是拥有的直接控制权较少，如果需要更多控制权，可以通过“降级”使用较低级别组件
 * 实践建议：较低级别的API的过程更为复杂，但提供更多的控制权，选择最符合需求的的抽象化级别
 *
 *
 * 选择合适的抽象化级别
 *        例如：为自定义组件添加手势支持，
 *             可以使用 Modifier.pointerInput 从头开始构建
 *             在此之上还有其他更高级别的组件，它们可以提供更好的起点，例如 Modifier.draggable、Modifier.scrollable 或 Modifier.swipeable。
 *        最好基于能提供您所需功能的最高级别的组件进行构建，以便从其包含的最佳实践中受益
 *
 * 示例：为某个组件的颜色添加动画效果
 */
@Composable
fun ColorUtil(condition: Boolean) {
    //较高级别的 animateColorAsState API 本身基于较低级别的 Animatable API 构建而成
    val color = animateColorAsState(if (condition) Color.Green else Color.Red, label = "")

    val color2 = remember {
        Animatable(Color.Gray)
    }
    //如果需要这个组件始终从灰色开始，此 animateColorAsState API 就无能为力了。过“降级”改用较低级别的 Animatable API
    LaunchedEffect(key1 = condition) {
        color2.animateTo(if (condition) Color.Green else Color.Red)
    }
}


/**
 * 自定义：通过将较小的构建块组合成更高级别的组件，可大幅降低按需自定义组件的难度
 * 实践建议：1、如果想替换更高级别，则可以为自己的实现提供更易于发现的名称。
 *              Jetpack Compose 为最高级别的组件保留了最为简洁的名称。
 *              例如，androidx.compose.material.Text 基于 androidx.compose.foundation.text.BasicText 构建。
 *         2、为组件创建分支意味着，不会从上游组件的任何未来增补项或 bug 修复中受益
 *
 *
 * 示例： Material 层提供的 Button 的实现
 *        1.Material Surface：用于提供背景、形状和点击处理方式等。
 *        2.CompositionLocalProvider：用于在启用或停用相应按钮时更改内容的 Alpha 值
 *        3.ProvideTextStyle：用于设置要使用的默认文本样式
 *        4.Row：用于为相应按钮的内容提供默认布局政策
 *    它只是组合了这 4 个组件来实现该按钮。Button 等组件会自行判断它们需要公开哪些参数，同时在实现常见的自定义项和可能使组件更难使用的参数突增之间创造平衡
 *    Material 组件可提供 Material Design 系统中指定的自定义项，这样可以轻松遵循 Material Design 原则。
 *
 */
@Composable
fun ButtonM3(
    /* onClick: () -> Unit,
     modifier: Modifier = Modifier,
     enabled: Boolean = true,
     shape: Shape = ButtonDefaults.shape,
     colors: ButtonColors = ButtonDefaults.buttonColors(),
     elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
     border: BorderStroke? = null,
     contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
     interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },*/
    content: @Composable RowScope.() -> Unit
) {
    /* val containerColor = colors.containerColor(enabled).value
     val contentColor = colors.contentColor(enabled).value
     val shadowElevation = elevation?.shadowElevation(enabled, interactionSource)?.value ?: 0.dp
     val tonalElevation = elevation?.tonalElevation(enabled, interactionSource)?.value ?: 0.dp*/
    Surface(
        /* onClick = onClick,
         modifier = modifier.semantics { role = Role.Button },
         enabled = enabled,
         shape = shape,
         color = containerColor,
         contentColor = contentColor,
         tonalElevation = tonalElevation,
         shadowElevation = shadowElevation,
         border = border,
         interactionSource = interactionSource*/
    ) {
        CompositionLocalProvider(/*LocalContentColor provides contentColor*/) {
            ProvideTextStyle(value = MaterialTheme.typography.labelLarge) {
                Row(
                    /* Modifier
                         .defaultMinSize(
                             minWidth = ButtonDefaults.MinWidth,
                             minHeight = ButtonDefaults.MinHeight
                         )
                         .padding(contentPadding),
                     horizontalArrangement = Arrangement.Center,
                     verticalAlignment = Alignment.CenterVertically,*/
                    content = content
                )
            }
        }
    }
}

/**
 * 如果希望在组件的参数之外进行自定义，可以“降级”到某个级别并为组件创建分支
 * 例如：Material Design 指定按钮应具有纯色背景
 *       如果您需要渐变背景，Button 参数就不适用了，因为它不支持此选项。在此类情况下，您可以将 Material Button 实现作为参考，并构建您自己的组件
 *
 * 当“降级”到较低层以自定义组件时，请确保不会因忽视无障碍功能支持等原因而使任何功能发生降级。您要为哪个组件创建分支，就应以哪个组件作为指导。
 */
@Composable
fun GradientButton(
    // …
    modifier: Modifier = Modifier,
    background: List<Color>,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        // …
        modifier = modifier
            // .clickable(/* … */)
            .background(
                Brush.horizontalGradient(background)
            )
    ) {
        CompositionLocalProvider(/* … */) { // set material LocalContentAlpha
            ProvideTextStyle(value = MaterialTheme.typography.labelLarge) {
                Row(
                    /* Modifier
                         .defaultMinSize(
                             minWidth = ButtonDefaults.MinWidth,
                             minHeight = ButtonDefaults.MinHeight
                         )
                         .padding(contentPadding),
                     horizontalArrangement = Arrangement.Center,
                     verticalAlignment = Alignment.CenterVertically,*/
                    content = content
                )
            }
        }
    }
}


/**
 * 如果您根本就不想使用 Material 概念，例如，在构建自己的定制设计系统时，可以“降级”为仅使用基础层组件
 */
@Composable
fun BespokeButton(
    // …
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        // …
        modifier = modifier
        //  .clickable(/* … */)
        //  .background(/* … */)
    ) {
        // No Material components used
        content()
    }
}
