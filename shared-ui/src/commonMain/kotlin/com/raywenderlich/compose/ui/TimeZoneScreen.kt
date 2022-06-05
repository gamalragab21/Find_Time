package com.raywenderlich.compose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.compose.ui.AnimatedSwipeDismiss
import com.example.findtime.TimeZoneHelper
import com.example.findtime.TimeZoneHelperImpl
import kotlinx.coroutines.delay

const val timeMillis = 1000 * 60L // 1 second

@Composable
fun TimeZoneScreen(
    currentTimezoneStrings: SnapshotStateList<String>,
) {
// 1
    val timezoneHelper: TimeZoneHelper = TimeZoneHelperImpl()
// 2
    val listState = rememberLazyListState()
// 3
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
// TODO: Add Content
        // 1
        var time by remember { mutableStateOf(timezoneHelper.currentTime()) }
// 2
        LaunchedEffect(0) {
            while (true) {
                time = timezoneHelper.currentTime()
                delay(timeMillis) // Every minute
            }
        }
        // 3
        LocalTimeCard(
            city = timezoneHelper.currentTimeZone(),
            time = time, date =
            timezoneHelper.getDate(timezoneHelper.currentTimeZone())
        )
        Spacer(modifier = Modifier.size(16.dp))

        // 1
        LazyColumn(
            state = listState,
        ) {
// 2
            items(currentTimezoneStrings,
// 3
                key = { timezone ->
                    timezone
                }) { timezoneString ->
// 4
                AnimatedSwipeDismiss(
                    item = timezoneString,
// 5
                    background = { valueDissmis->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .height(50.dp)
                                .background(Color.Red)
                                .padding(
                                    start = 20.dp,
                                    end = 20.dp
                                )) {
                            val alpha = 1f
                            Icon(
                                Icons.Filled.Delete,
                                contentDescription = "Delete",
                                modifier = Modifier
                                    .align(Alignment.CenterEnd),
                                tint = Color.White.copy(alpha = alpha)
                            )
                        }
                    },
                    content = {
// 6
                        TimeCard(
                            timezone = timezoneString,
                            hours =
                            timezoneHelper.hoursFromTimeZone(timezoneString),
                            time = timezoneHelper.getTime(timezoneString),
                            date = timezoneHelper.getDate(timezoneString)
                        )
                    },
// 7
                    onDismiss = { zone ->
                        if (currentTimezoneStrings.contains(zone)) {
                            currentTimezoneStrings.remove(zone)
                        }
                    }
                )
            }
        }
    }
}

