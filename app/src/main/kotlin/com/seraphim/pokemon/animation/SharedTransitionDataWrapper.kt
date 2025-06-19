@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.seraphim.pokemon.animation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

data class SharedTransitionData(
    val animatedVisibilityScope: AnimatedVisibilityScope,
    val sharedTransitionScope: SharedTransitionScope,
)

val LocalSharedTransitionData = compositionLocalOf<SharedTransitionData?> { null }

interface SharedTransitionModifierScope : SharedTransitionScope, Modifier


private class SharedTransitionModifierScopeImpl(
    sharedTransitionScope: SharedTransitionScope,
    modifier: Modifier,
) : SharedTransitionScope by sharedTransitionScope, Modifier by modifier,
    SharedTransitionModifierScope

@Composable
fun Modifier.sharedTransitionScope(
    scope: @Composable SharedTransitionModifierScope.(animatedVisibilityScope: AnimatedVisibilityScope) -> Modifier
): Modifier {
    val data = LocalSharedTransitionData.current
    return if (data == null) {
        this
    } else {
        val scopeImpl = remember {
            SharedTransitionModifierScopeImpl(
                sharedTransitionScope = data.sharedTransitionScope,
                modifier = this
            )
        }
        scope(scopeImpl, data.animatedVisibilityScope)
    }
}

//object SharedTransitionDataWrapper : DestinationWrapper {
//    @Composable
//    override fun <T> DestinationScope<T>.Wrap(screenContent: @Composable () -> Unit) {
//        val sharedTransitionScope = buildDependencies().require<SharedTransitionScope>()
//        val sharedTransitionData = remember {
//            (this as? AnimatedVisibilityScope)?.let {
//                SharedTransitionData(
//                    animatedVisibilityScope = it,
//                    sharedTransitionScope = sharedTransitionScope
//                )
//            }
//        }
//        CompositionLocalProvider(
//            LocalSharedTransitionData provides sharedTransitionData
//        ) {
//            screenContent()
//        }
//    }
//}