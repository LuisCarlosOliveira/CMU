import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

//utility function a usar sempre que for preciso alterar a view dependendo do ecra
@Composable
fun rememberWindowInfo(): WindowInfo{
    //para criar o objecto é necessario obter a configuração do objecto através do localconfiguration
    val configuration = LocalConfiguration.current

    //return WindowType dependendo das medidas da altura e largura
    return WindowInfo(
        screenWidthInfo = when{
            configuration.screenWidthDp < 600 -> WindowInfo.WindowType.Compact
            configuration.screenWidthDp < 840 -> WindowInfo.WindowType.Medium
            else -> WindowInfo.WindowType.Expanded
        },
        screenHeightInfo = when{
            configuration.screenHeightDp < 480 -> WindowInfo.WindowType.Compact
            configuration.screenHeightDp < 900 -> WindowInfo.WindowType.Medium
            else -> WindowInfo.WindowType.Expanded
        },
        screenWidth =  configuration.screenWidthDp.dp,
        screenHeight = configuration.screenHeightDp.dp
    )
}

//contém a informação sobre o tipo de ecrã, bem como altura e largura
data class WindowInfo(
    val screenWidthInfo: WindowType,
    val screenHeightInfo: WindowType,
    val screenWidth: Dp,
    val screenHeight: Dp
){
    sealed class WindowType{
        object Compact: WindowType()
        object Medium: WindowType()
        object Expanded: WindowType()
    }
}