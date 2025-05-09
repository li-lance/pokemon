package com.seraphim.pokemon.pages

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph

@OptIn(ExperimentalSharedTransitionApi::class)
@Destination<RootGraph>
@Composable
fun PokemonDetailScreen(
//    sharedTransitionScope: SharedTransitionScope,
//    animatedVisibilityScope: AnimatedVisibilityScope,
    url: String
) {
    Column {
//        with(sharedTransitionScope) {
//            AsyncImage(
//                model = url, // 替换为实际图片 URL
//                contentDescription = "Pokemon Image",
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .sharedElement(
//                        rememberSharedContentState(key = "image"),
//                        animatedVisibilityScope = animatedVisibilityScope
//                    )
//            )
//        }
    }
}