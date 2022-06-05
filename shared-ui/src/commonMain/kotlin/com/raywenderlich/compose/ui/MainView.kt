package com.raywenderlich.compose.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.compose.helper.bottomNavigationItems
import com.example.compose.theme.AppTheme
import com.example.compose.theme.emptyComposable
import com.example.compose.theme.topBarFun
import com.example.compose.ui.FindMeetingScreen


// 1
@Composable
// 2
fun MainView(actionBarFun: topBarFun = { emptyComposable() }) {
    // 3
    val showAddDialog = remember { mutableStateOf(false) }
// 4
    val currentTimezoneStrings = remember { SnapshotStateList<String>() }
// 5
    val selectedIndex = remember { mutableStateOf(0) }
// 6
    AppTheme {
// TODO: Add Scaffold
        Scaffold(
            topBar = {
// TODO: Add Toolbar
                actionBarFun(selectedIndex.value)
            },
            floatingActionButton = {
// TODO: Add Floating action button
                if (selectedIndex.value == 0) {
//
                    FloatingActionButton(
// 2
                        modifier = Modifier
                            .padding(16.dp),
// 3
                        onClick = {
                            showAddDialog.value = true
                        }
                    ) {
// 4
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null
                        )
                    }
                }
            },
            bottomBar = {
// TODO: Add bottom bar
                // 1
                BottomNavigation {
// 2
                    bottomNavigationItems.forEachIndexed { i, bottomNavigationItem ->
// 3
                        BottomNavigationItem(
// 4
                            icon = {
                                Icon(
                                    bottomNavigationItem.icon,
                                    contentDescription =
                                    bottomNavigationItem.iconContentDescription
                                )
                            },
// 5
                            selected = selectedIndex.value == i,
// 6
                            onClick = {
                                selectedIndex.value = i
                            }
                        )
                    }
                }
            }
        ) {
// TODO: Replace with Dialog
            // 1
            if (showAddDialog.value) {
                AddTimeZoneDialog(
// 2
                    onAdd = { newTimezones ->
                        showAddDialog.value = false
                        for (zone in newTimezones) {
// 3
                            if (!currentTimezoneStrings.contains(zone)) {
                                currentTimezoneStrings.add(zone)
                            }
                        }
                    },
                    onDismiss = {
// 4
                        showAddDialog.value = false
                    },
                )
            }
// TODO: Replace with screens
            when (selectedIndex.value) {
                0 -> TimeZoneScreen(currentTimezoneStrings)
                1 -> FindMeetingScreen(currentTimezoneStrings)
            }
        }

    }
}



