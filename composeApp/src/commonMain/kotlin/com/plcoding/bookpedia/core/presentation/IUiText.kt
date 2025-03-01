package com.plcoding.bookpedia.core.presentation

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource


sealed interface IUiText {
    data class DynamicString(val value: String): IUiText
    class StringResourceId(
        val id: StringResource,
        val args: Array<Any> = arrayOf()
    ): IUiText

    @Composable
    fun asString(): String {
        return when(this) {
            is DynamicString -> value
            is StringResourceId -> stringResource(resource = id, formatArgs = args)
        }
    }
}