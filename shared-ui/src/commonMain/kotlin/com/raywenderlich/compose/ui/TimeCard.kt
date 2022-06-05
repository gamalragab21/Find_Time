package com.raywenderlich.compose.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
// 1
fun TimeCard(timezone: String, hours: Double, time: String, date: String) {
// 2
    Box(
        modifier = Modifier
            .fillMaxSize()
            .height(120.dp)
            .background(Color.White)
            .padding(8.dp)
    ) {
// 3
        Card(
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, Color.Gray),
            elevation = 4.dp,
            modifier = Modifier
                .fillMaxWidth()
        )
        {
// TODO: Add Content
            // 1
            Box(
                modifier = Modifier
                    .background(
                        color = Color.White
                    ).padding(16.dp)

            ) {
                // 2
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
// 3
                    Column(
                        horizontalAlignment = Alignment.Start
                    ) {
// 4
                        Text(
                            timezone, style = TextStyle(
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        )
                        Spacer(modifier = Modifier.weight(1.0f))
// 5
                        Row {
// 6
                            Text(
                                hours.toString(), style = TextStyle(
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                )
                            )
// 7
                            Text(
                                " hours from local", style = TextStyle(
                                    color = Color.Black,
                                    fontSize = 14.sp
                                )
                            )
                        }
                    }
                    Spacer(modifier = Modifier.weight(1.0f))
// 8
                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
// 9
                        Text(
                            time, style = TextStyle(
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp
                            )
                        )
                        Spacer(modifier = Modifier.weight(1.0f))
// 10
                        Text(
                            date, style = TextStyle(
                                color = Color.Black,
                                fontSize = 12.sp
                            )
                        )
                    }
                }


            }
        }
    }
}


