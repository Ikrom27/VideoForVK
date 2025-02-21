package ru.ikrom.videoforvk

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import ru.ikrom.player.PlayerScreen
import ru.ikrom.videolist.VideoListScreen

@Composable
fun MainNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = VideoList,
        modifier = modifier) {

        composable<VideoList> {
            VideoListScreen(
                onVideoClick = { navController.navigate(route = Player(it)) }
            )
        }
        composable<Player> { backStackEntry ->
            val player = backStackEntry.toRoute<Player>()
            PlayerScreen(videoUrl = player.videoId)
        }
    }

}

@Serializable
object VideoList
@Serializable
data class Player(val videoId: String)