package com.lt.kbtol.translate

import com.intellij.openapi.actionSystem.*
import com.lt.kbtol.config.*

/**
 * creator: lt  2025/4/8  lt.dygzs@qq.com
 * effect : 翻译为特定语言
 * warning:
 */
abstract class ITranslate {
    protected abstract fun doAction(e: AnActionEvent, languageEnum: LanguageEnum, input: String): String

    fun action(e: AnActionEvent, languageEnum: LanguageEnum, input: String): String {
        return doAction(e, languageEnum, input)
    }
}