package com.example.mysmarthome.ui.components

import android.graphics.Typeface
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.mysmarthome.R
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mysmarthome.database.view_models.DevicesViewModel
import com.example.mysmarthome.ui.theme.blueColor
import com.example.mysmarthome.ui.theme.greenColor
import com.example.mysmarthome.ui.theme.redColor
import com.example.mysmarthome.ui.theme.yellowColor
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import java.util.ArrayList

@Composable
fun PieChart(optionSelected: String) {

    var powerLight by remember {
        mutableStateOf(0.0F)
    }

    var totalLight by remember {
        mutableStateOf(0.0F)
    }

    var powerPlug by remember {
        mutableStateOf(0.0F)
    }

    var overpowerPlug by remember {
        mutableStateOf(0.0F)
    }

    var totalPlug by remember {
        mutableStateOf(0.0F)
    }

    var powerBlindOpen by remember {
        mutableStateOf(0.0F)
    }

    var overpowerBlindOpen by remember {
        mutableStateOf(0.0F)
    }

    var totalBlindOpen by remember {
        mutableStateOf(0.0F)
    }

    var powerBlindClose by remember {
        mutableStateOf(0.0F)
    }

    var overpowerBlindClose by remember {
        mutableStateOf(0.0F)
    }

    var totalBlindClose by remember {
        mutableStateOf(0.0F)
    }

    val plugsViewModel: DevicesViewModel = viewModel()

    val plugMeterContent = plugsViewModel.getPlugMeter.observeAsState()

    plugsViewModel.getPlugMeter()

    if (plugMeterContent.value != null) {
        powerPlug = plugMeterContent.value!!.power
        overpowerPlug = plugMeterContent.value!!.overpower
        totalPlug = plugMeterContent.value!!.total
    }

    val lightsViewModel: DevicesViewModel = viewModel()

    val lightMeterContent = lightsViewModel.getLightMeter.observeAsState()

    lightsViewModel.getLightMeter()

    if (lightMeterContent.value != null) {
        powerLight = lightMeterContent.value!!.power
        totalLight = lightMeterContent.value!!.total
    }

    val blindsViewModel: DevicesViewModel = viewModel()

    val blindMeterOpenContent = blindsViewModel.getBlindMeterOpen.observeAsState()
    val blindMeterCloseContent = blindsViewModel.getBlindMeterClose.observeAsState()

    blindsViewModel.getBlindMeterOpen()

    if (blindMeterOpenContent.value != null) {
        powerBlindOpen = blindMeterOpenContent.value!!.power
        overpowerBlindOpen = blindMeterOpenContent.value!!.overpower
        totalBlindOpen = blindMeterOpenContent.value!!.total
    }

    blindsViewModel.getBlindMeterClose()
    if (blindMeterCloseContent.value != null) {
        powerBlindClose = blindMeterCloseContent.value!!.power
        overpowerBlindClose = blindMeterCloseContent.value!!.overpower
        totalBlindClose = blindMeterCloseContent.value!!.total
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Column(
            modifier = Modifier
                .padding(2.dp)
                .size(320.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            var totalEnergyByDevice: List<PieChartData> =
                deviceConsumptions(
                    "Light",
                    totalLight,
                    "Plug",
                    totalPlug,
                    "Blind",
                    totalBlindOpen + totalBlindClose
                )

            var totalPowerByDevice: List<PieChartData> =
                deviceConsumptions(
                    "Light",
                    powerLight,
                    "Plug",
                    powerPlug,
                    "Blind",
                    powerBlindOpen + powerBlindClose
                )

            if (optionSelected.equals("Energia (Watt/Min)")) {

                Crossfade(
                    targetState = totalEnergyByDevice

                ) { pieChartData ->

                    AndroidView(factory = { context ->

                        PieChart(context).apply {
                            layoutParams = LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT,
                            )

                            this.description.isEnabled = false

                            this.isDrawHoleEnabled = false

                            this.legend.isEnabled = true

                            this.legend.textSize = 14F

                            this.legend.horizontalAlignment =
                                Legend.LegendHorizontalAlignment.CENTER

                            this.setEntryLabelColor(resources.getColor(R.color.white))
                        }
                    },

                        modifier = Modifier
                            .wrapContentSize()
                            .padding(10.dp), update = {
                            updatePieChartWithData(it, pieChartData)
                        })

                }

            } else {
                Crossfade(
                    targetState = totalPowerByDevice

                ) { pieChartData ->

                    AndroidView(factory = { context ->

                        PieChart(context).apply {
                            layoutParams = LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT,
                            )

                            this.description.isEnabled = false

                            this.isDrawHoleEnabled = false

                            this.legend.isEnabled = true

                            this.legend.textSize = 14F

                            this.legend.horizontalAlignment =
                                Legend.LegendHorizontalAlignment.CENTER

                            this.setEntryLabelColor(resources.getColor(R.color.white))
                        }
                    },

                        modifier = Modifier
                            .wrapContentSize()
                            .padding(10.dp), update = {
                            updatePieChartWithData(it, pieChartData)
                        })
                }
            }
        }

        if (optionSelected.equals("Energia (Watt/Min)")) {
            Column(Modifier.fillMaxSize()) {
                Row(Modifier.fillMaxWidth()) {
                    Text(
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily.SansSerif,
                        //color = Color.Red,
                        modifier = Modifier.padding(top = 20.dp),
                        fontSize = 15.sp,
                        text = "Light: "
                    )
                    Text(
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily.SansSerif,
                        //color = Color.Black,
                        modifier = Modifier.padding(top = 20.dp),
                        fontSize = 15.sp,
                        text = totalLight.toString() + " Watt/Min"
                    )
                }
                Row(Modifier.fillMaxWidth()) {
                    Text(
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily.SansSerif,
                        //color = Color.Red,
                        modifier = Modifier.padding(top = 20.dp),
                        fontSize = 15.sp,
                        text = "Plug: "
                    )
                    Text(
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily.SansSerif,
                        //color = Color.Black,
                        modifier = Modifier.padding(top = 20.dp),
                        fontSize = 15.sp,
                        text = totalPlug.toString() + " Watt/Min"
                    )
                }
                Row(Modifier.fillMaxWidth()) {
                    Text(
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily.SansSerif,
                        //color = Color.Red,
                        modifier = Modifier.padding(top = 20.dp),
                        fontSize = 15.sp,
                        text = "Blind: "
                    )
                    Text(
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily.SansSerif,
                        //color = Color.Black,
                        modifier = Modifier.padding(top = 20.dp),
                        fontSize = 15.sp,
                        text = (totalBlindOpen + totalBlindClose).toString() + " Watt/Min"
                    )
                }
            }
        } else {
            Column(Modifier.fillMaxSize()) {
                Row(Modifier.fillMaxWidth()) {
                    Text(
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily.SansSerif,
                        //color = Color.Red,
                        modifier = Modifier.padding(top = 20.dp),
                        fontSize = 15.sp,
                        text = "Light: "
                    )
                    Text(
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily.SansSerif,
                        //color = Color.Black,
                        modifier = Modifier.padding(top = 20.dp),
                        fontSize = 15.sp,
                        text = powerLight.toString() + " Watt"
                    )
                }
                Row(Modifier.fillMaxWidth()) {
                    Text(
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily.SansSerif,
                        //color = Color.Red,
                        modifier = Modifier.padding(top = 20.dp),
                        fontSize = 15.sp,
                        text = "Plug: "
                    )
                    Text(
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily.SansSerif,
                        //color = Color.Black,
                        modifier = Modifier.padding(top = 20.dp),
                        fontSize = 15.sp,
                        text = powerPlug.toString() + " Watt"
                    )
                }
                Row(Modifier.fillMaxWidth()) {
                    Text(
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily.SansSerif,
                        //color = Color.Red,
                        modifier = Modifier.padding(top = 20.dp),
                        fontSize = 15.sp,
                        text = "Blind: "
                    )
                    Text(
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily.SansSerif,
                        //color = Color.Black,
                        modifier = Modifier.padding(top = 20.dp),
                        fontSize = 15.sp,
                        text = (powerBlindOpen + powerBlindClose).toString() + " Watt"
                    )
                }
            }
        }
    }
}

fun updatePieChartWithData(
    chart: PieChart,
    data: List<PieChartData>
) {

    val entries = ArrayList<PieEntry>()

    for (i in data.indices) {
        val item = data[i]
        entries.add(PieEntry(item.total, item.device))
    }

    val ds = PieDataSet(entries, "")

    ds.colors = arrayListOf(
        greenColor.toArgb(),
        blueColor.toArgb(),
        redColor.toArgb(),
        yellowColor.toArgb(),
    )

    ds.yValuePosition = PieDataSet.ValuePosition.INSIDE_SLICE

    ds.xValuePosition = PieDataSet.ValuePosition.INSIDE_SLICE

    ds.sliceSpace = 4f

    ds.valueTextColor = Color(R.color.white).toArgb()

    ds.valueTextSize = 18f

    ds.valueTypeface = Typeface.DEFAULT_BOLD

    val d = PieData(ds)

    chart.data = d

    chart.invalidate()

}