package com.example.compose.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.findtime.TimeZoneHelper
import com.example.findtime.TimeZoneHelperImpl
import com.raywenderlich.compose.ui.MeetingDialog
import com.raywenderlich.compose.ui.NumberTimeCard
import com.raywenderlich.compose.ui.isSelected

// 1
@Composable
fun FindMeetingScreen(
    timezoneStrings: List<String>
) {
    val listState = rememberLazyListState()
// 2
// 8am
    val startTime = remember {
        mutableStateOf(8)
    }
// 5pm
    val endTime = remember {
        mutableStateOf(17)
    }
// 3
    val selectedTimeZones = remember {
        val selected = SnapshotStateMap<Int, Boolean>()
        for (i in timezoneStrings.indices) selected[i] = true
        selected
    }
// 4
    val timezoneHelper: TimeZoneHelper = TimeZoneHelperImpl()
    val showMeetingDialog = remember { mutableStateOf(false) }
    val meetingHours = remember { SnapshotStateList<Int>() }
// 5
    if (showMeetingDialog.value) {
        MeetingDialog(
            hours = meetingHours,
            onDismiss = {
                showMeetingDialog.value = false
            }
        )
    }
// TODO: Add Content
    // 1
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.size(16.dp))
// 2
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally),
            text = "Time Range",
            style = MaterialTheme.typography.h6
        )
        Spacer(modifier = Modifier.size(16.dp))
// 3
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, end = 4.dp)
                .wrapContentWidth(Alignment.CenterHorizontally),
        ) {
// 4
            Spacer(modifier = Modifier.size(16.dp))
            NumberTimeCard("Start", startTime)
            Spacer(modifier = Modifier.size(32.dp))
            NumberTimeCard("End", endTime)
        }
        Spacer(modifier = Modifier.size(16.dp))
// 5
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, end = 4.dp)
        ) {
            Text(modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally),
                text = "Time Zones",
                style = MaterialTheme.typography.h6
            )
        }
        Spacer(modifier = Modifier.size(16.dp))
// TODO: Add LazyColumn
        // 1
        LazyColumn(
            modifier = Modifier
                .weight(0.6F)
                .fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
            state = listState,
        ) {
// 2
            itemsIndexed(timezoneStrings) { i, timezone ->
                Surface(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                    ) {
// 3
                        Checkbox(checked = isSelected(selectedTimeZones,
                            i),
                            onCheckedChange = {
                                selectedTimeZones[i] = it
                            })
                        Text(timezone, modifier =
                        Modifier.align(Alignment.CenterVertically))
                    }
                }
            }
        }
        Spacer(Modifier.weight(0.1f))
        Row(
            modifier = Modifier.fillMaxWidth()
                .weight(0.2F)
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(start = 4.dp, end = 4.dp)
        ) {
// 4
            OutlinedButton(onClick = {
                meetingHours.clear()
                meetingHours.addAll(
                    timezoneHelper.search(
                        startTime.value,
                        endTime.value,
                        getSelectedTimeZones(timezoneStrings,
                            selectedTimeZones)
                    )
                )
                showMeetingDialog.value = true
            }) {
                Text("Search")
            }
        }
        Spacer(Modifier.size(16.dp))
    }
}
// TODO: Add getSelectedTimeZones
fun getSelectedTimeZones(
    timezoneStrings: List<String>,
    selectedStates: Map<Int, Boolean>
): List<String> {
    val selectedTimezones = mutableListOf<String>()
    selectedStates.keys.map {
        val timezone = timezoneStrings[it]
        if (isSelected(selectedStates, it) &&
            !selectedTimezones.contains(timezone)) {
            selectedTimezones.add(timezone)
        }
    }
    return selectedTimezones
}