package com.lt.kbtol.translate

import com.intellij.openapi.actionSystem.*
import com.lt.kbtol.config.*
import com.lt.kbtol.structure.*

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
            val defaultValue = if (kbParameter.defaultValue.isNullOrEmpty()) "" else "= ${kbParameter.defaultValue}"
            """    ${doc}${kbParameter.name}: ${createType(kbParameter.type)}${defaultValue}"""
        }
    }

    private fun createType(type: KBType): String {
        val typeName = when (type.name) {
            "String", "Char" -> "string"
            "Long", "Int", "Double", "Float", "Byte", "Short" -> "number"
            "Boolean" -> "boolean"
            "List", "ArrayList", "MutableList" -> {
                val first = type.arguments.firstOrNull()
                val listType = if (first != null) createType(first) else ""
                "$listType[]"
            }

            "Map", "MutableMap", "HashMap" -> "object"
            else -> type.name
        }
        val typeString = if (type.nullable) " | null " else ""
        return "$typeName$typeString"
    }
}