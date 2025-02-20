package ru.ikrom.videoforvk

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import ru.ikrom.videolist.VideoList

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
            VideoList(
                onVideoClick = { navController.navigate(route = Player(it)) }
            )
        }
        composable<Player> { backStackEntry ->
            val player = backStackEntry.toRoute<Player>()
            Player(id = player.id)

        }
    }

}

@Serializable
object VideoList
@Serializable
data class Player(val id: Int)