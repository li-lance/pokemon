package com.seraphim.pokemon.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val PokemonShape = Shapes(
    small = RoundedCornerShape(8.dp), // 用于小型组件的圆角，比如按钮
    medium = RoundedCornerShape(16.dp), // 用于中型组件的圆角
    large = RoundedCornerShape(0.dp) // 用于大型组件的圆角
)