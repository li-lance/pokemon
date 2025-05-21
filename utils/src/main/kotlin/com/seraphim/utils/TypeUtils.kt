package com.seraphim.utils

/**
 * 类型转换处理，进行默认值的设置
 * */
fun <T> T?.defaultIfNull(defaultValue: T): T {
    return this ?: defaultValue
}

fun Int?.defaultIfNull(defaultValue: Int = 0): Int {
    return this ?: defaultValue
}

fun String?.defaultIfNull(defaultValue: String = ""): String {
    return this ?: defaultValue
}

fun Boolean?.defaultIfNull(defaultValue: Boolean = false): Boolean {
    return this ?: defaultValue
}

fun <T> List<T>?.defaultIfEmpty(defaultValue: List<T> = emptyList()): List<T> {
    return this ?: defaultValue
}