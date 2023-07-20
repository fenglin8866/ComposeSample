package com.xxh.sample.state.basic

import android.content.res.Resources
import android.graphics.BitmapShader
import android.graphics.Shader
import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.xxh.sample.R
import com.xxh.sample.common.component.XDivider
import kotlinx.parcelize.Parcelize

/**
 *rememberSaveable 配置更改或进程意外杀死状态继续保存，保存在Bundle中
 * 如果不兼容Bundle类型
 * 1.Parcelize注解
 * 2.自定义Saver（mapSaver，listSaver）
 */

data class City(var name: String, val id: Int)

@Parcelize
data class CityA(val name: String, val id: Int) : Parcelable

@Composable
fun ShowCities() {

    var city1 by remember {
        mutableStateOf(City("北京", 1))
    }
    /*直接crash，不兼容Bundle
    var city1 by rememberSaveable {
        mutableStateOf(City("北京", 1))
    }*/

    var cityA by rememberSaveable {
        mutableStateOf(CityA("北京", 1))
    }

    val sMap = run {
        val nameKey = "Name"
        val idKey = "ID"
        mapSaver(
            save = { mapOf(nameKey to it.name, idKey to it.id) },
            restore = { City(it[nameKey] as String, it[idKey] as Int) })
    }

    var cityB = rememberSaveable(stateSaver = sMap) {
        mutableStateOf(City("北京", 1))
    }

    val sList = listSaver<City, Any>(
        save = { listOf(it.name, it.id) },
        restore = { City(it[0] as String, it[1] as Int) }
    )

    var cityC: MutableState<City> = rememberSaveable(stateSaver = sList) {
        mutableStateOf(City("北京", 1))
    }

    var input by remember {
        mutableStateOf("")
    }

    ShowCities(city1, cityA, cityB, cityC, input) {
        city1 = City(it, 11)
        cityA = CityA(it, 1111)
        cityB.value = City(it, 2222)
        cityC.value = City(it, 3333)
        input = it
    }
}

@Composable
fun ShowCities(
    city: City,
    cityA: CityA,
    cityB: MutableState<City>,
    cityC: MutableState<City>,
    value: String,
    onValueChange: (String) -> Unit
) {

    Column {
        Text(
            "城市名字:${city.name} , 城市ID:${city.id}",
            modifier = Modifier.padding(bottom = 10.dp)
        )
        XDivider()
        Text(
            "城市名字:${cityA.name} , 城市ID:${cityA.id}",
            modifier = Modifier.padding(bottom = 10.dp)
        )
        XDivider()
        Text(
            "城市名字:${cityB.value.name} , 城市ID:${cityB.value.id}",
            modifier = Modifier.padding(bottom = 10.dp)
        )
        XDivider()
        Text(
            "城市名字:${cityC.value.name} , 城市ID:${cityC.value.id}",
            modifier = Modifier.padding(bottom = 10.dp)
        )
        XDivider()
        TextField(
            value = value,
            onValueChange = onValueChange
        )

    }
}


@Composable
fun BackgroundBanner(
    modifier: Modifier = Modifier,
) {
    var resId by remember {
        mutableIntStateOf(R.drawable.fc6_nightly_wind_down)
    }
    BackgroundBanner(avatarRes = resId, onClick1 = {
        resId = R.drawable.fc6_nightly_wind_down
    }) {
        resId = R.drawable.ab1_inversions
    }

}

/**
 * 使用 remember 将初始化或计算成本高昂的对象或操作结果存储在组合中
 */
@Composable
fun BackgroundBanner(
    @DrawableRes avatarRes: Int,
    modifier: Modifier = Modifier,
    res: Resources = LocalContext.current.resources,
    onClick1: () -> Unit,
    onClick2: () -> Unit,
) {

    val brush = remember(key1 = avatarRes) {
        ShaderBrush(
            BitmapShader(
                ImageBitmap.imageResource(res, avatarRes).asAndroidBitmap(),
                Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP
            )
        )
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(brush)
            .size(230.dp)
    ) {
        Row {
            Button(onClick = onClick1) {
                Text(text = "风景")
            }
            Button(onClick = onClick2, modifier = modifier.padding(start = 10.dp)) {
                Text(text = "人物")
            }
        }
    }
}