package com.lt.kbtol.translate

import com.intellij.openapi.actionSystem.*
import com.lt.kbtol.config.*
import com.lt.kbtol.structure.*

/**
 * creator: lt  2025/4/8  lt.dygzs@qq.com
 * effect : JavaScript
 * warning:
 */
object JSTranslate : ITranslate() {
    override fun doAction(
        e: AnActionEvent,
        languageEnum: LanguageEnum,
        input: String,
        classList: List<KBClass>,
    ): String {
        return classList.joinToString("\n\n", transform = ::createClass)
    }

    private fun createClass(kbClass: KBClass): String {
        val classDoc = if (kbClass.doc.isEmpty()) "" else "/**${kbClass.doc}*/\n"
        val innerClass = if (kbClass.innerClass.isEmpty()) "" else "\n\n${
            kbClass.innerClass.joinToString(
                "\n\n",
                transform = ::createClass
            )
        }"
        return """${classDoc}export class ${kbClass.name} {
${createParameter(kbClass)}
}$innerClass"""
    }

    private fun createParameter(kbClass: KBClass): String {
        return kbClass.parameters.joinToString("\n") { kbParameter ->
            val doc = if (kbParameter.doc.isEmpty()) "" else "/*${kbParameter.doc}*/\n    "
            val defaultValue = if (kbParameter.defaultValue.isNullOrEmpty()) "" else " = ${kbParameter.defaultValue}"
            """    ${doc}${kbParameter.name}${defaultValue}"""
        }
    }
}