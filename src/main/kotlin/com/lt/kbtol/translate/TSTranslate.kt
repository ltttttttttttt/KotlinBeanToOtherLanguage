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
        return classList.joinToString("\n\n") { kbClass ->
            val classDoc = if (kbClass.doc.isEmpty()) "" else "/*${kbClass.doc}/*\n"
            """${classDoc}export class ${kbClass.name} {
${createParameter(kbClass)}
}"""
        }
    }

    private fun createParameter(kbClass: KBClass): String {
        return kbClass.parameters.joinToString("\n") { kbParameter ->
            val doc = if (kbParameter.doc.isEmpty()) "" else "    //${kbClass.doc}\n"
            val defaultValue = if (kbParameter.defaultValue.isNullOrEmpty()) "" else "= ${kbParameter.defaultValue}"
            """    ${doc}${kbParameter.name}: ${createType(kbParameter.type)}${defaultValue}"""
        }
    }

    private fun createType(type: KBType): String {
        val typeName = when (type.name) {
            "String", "Char" -> "string"
            "Long", "Int", "Double", "Float", "Byte", "Short" -> "number"
            "Boolean" -> "boolean"
            "List", "ArrayList", "MutableList" -> "[]"
            else -> "object"
        }
        val typeString = if (type.nullable) "| null " else ""
        return "$typeName $typeString"
    }
}