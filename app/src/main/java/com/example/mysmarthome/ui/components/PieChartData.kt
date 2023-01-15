package com.example.mysmarthome.ui.components

data class PieChartData(
    var device: String,
    var total: Float
)

fun deviceConsumptions(
    device1: String,
    total1: Float,
    device2: String,
    total2: Float,
    device3: String,
    total3: Float
): List<PieChartData> {

    var getPieChartData = listOf(
        PieChartData(device1, total1),
        PieChartData(device2, total2),
        PieChartData(device3, total3)
    )

    return getPieChartData
}