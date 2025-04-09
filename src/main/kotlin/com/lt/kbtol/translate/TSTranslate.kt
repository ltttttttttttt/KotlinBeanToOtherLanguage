package com.lt.kbtol.translate

import com.intellij.openapi.actionSystem.*
import com.lt.kbtol.config.*

/**
 * creator: lt  2025/4/8  lt.dygzs@qq.com
 * effect : TypeScript
 * warning:
 */
object TSTranslate : ITranslate() {
    override fun doAction(
        e: AnActionEvent,
        languageEnum: LanguageEnum,
        input: String,
    ): String {
        return input
    }

}