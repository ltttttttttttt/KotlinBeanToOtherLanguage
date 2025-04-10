package com.lt.kbtol.translate

import com.intellij.openapi.actionSystem.*
import com.lt.kbtol.config.*
import com.lt.kbtol.structure.*
import com.strumenta.antlrkotlin.parsers.generated.*
import org.antlr.v4.kotlinruntime.*
import tree.*
import tree.grammar.*

/**
 * creator: lt  2025/4/8  lt.dygzs@qq.com
 * effect : 翻译为特定语言
 * warning:
 */
abstract class ITranslate {

    /**
     * 特定语言自定义的翻译
     */
    protected abstract fun doAction(
        e: AnActionEvent,
        languageEnum: LanguageEnum,
        input: String,
        classList: List<KBClass>,
    ): String

    /**
     * 开始翻译
     */
    fun action(e: AnActionEvent, languageEnum: LanguageEnum, input: String): String {
        val lexer = KotlinLexer(StringCharStream(input))
        val parser = KotlinParser(CommonTokenStream(lexer))
        val kotlinFile = parser.kotlinFile().visit("Main")
        val classList = kotlinFile.topLevelObjects.mapNotNull { topLevel ->
            createClass(topLevel)
        }
        return doAction(e, languageEnum, input, classList)
    }

    //创建类
    private fun createClass(topLevel: TopLevelObject): KBClass? {
        val classDeclaration = topLevel.declaration.classDeclaration ?: return null
        return createClass(classDeclaration)
    }

    private fun createClass(classDeclaration: ClassDeclaration): KBClass? {
        if (classDeclaration.`class` != true) return null
        classDeclaration.simpleIdentifier.value
        val name = classDeclaration.simpleIdentifier.value
        val doc = ""
        val primaryConstructorParameters = mutableListOf<KBParameter>()
        val parameters = mutableListOf<KBParameter>()
        val innerClassList = mutableListOf<KBClass>()
        classDeclaration.primaryConstructor?.classParameters?.classParameter?.forEach {
            val constructorParameter = createPrimaryConstructorParameters(it)
            if (constructorParameter != null) {
                primaryConstructorParameters.add(constructorParameter)
                parameters.add(constructorParameter)
            }
        }
        classDeclaration.classBody?.classMemberDeclarations?.classMemberDeclaration?.forEach {
            val parameter = createParameter(it)
            if (parameter != null)
                parameters.add(parameter)
            it.declaration?.classDeclaration?.let {
                val innerClass = createClass(it)
                if (innerClass != null)
                    innerClassList.add(innerClass)
            }
        }
        return KBClass(
            name = name,
            doc = doc,
            primaryConstructorParameters = primaryConstructorParameters,
            parameters = parameters,
            innerClass = innerClassList,
        )
    }

    //创建主构造函数参数
    private fun createPrimaryConstructorParameters(classParameter: ClassParameter): KBParameter? {
        return KBParameter(
            name = classParameter.simpleIdentifier.value,
            mutable = classParameter.mutable,
            doc = "",
            type = createType(classParameter.type),
            defaultValue = getDefaultValue(classParameter.expression),
        )
    }

    //获取参数默认值
    private fun getDefaultValue(expression: Expression?): String {
        val primaryExpression = expression?.disjunction?.conjunction?.firstOrNull()
            ?.equality?.firstOrNull()
            ?.comparison?.firstOrNull()
            ?.genericCallLikeComparison?.firstOrNull()
            ?.infixOperation?.elvisExpression?.infixFunctionCall?.firstOrNull()
            ?.rangeExpression?.firstOrNull()
            ?.additiveExpression?.firstOrNull()
            ?.multiplicativeExpression?.firstOrNull()
            ?.asExpression?.firstOrNull()
            ?.prefixUnaryExpression?.postfixUnaryExpression?.primaryExpression
        //字面量
        primaryExpression?.literalConstant
            ?.let {
                it.booleanLiteral?.value?.let { return it.toString() }
                it.integerLiteral?.value?.let { return it }
                it.hexLiteral?.value?.let { return it.replace("0x", "").toInt(16).toString() }
                it.binLiteral?.value?.let { return it.replace("0b", "").toInt(2).toString() }
                it.characterLiteral?.value?.let { return "'$it'" }
                it.realLiteral?.value?.let { return it }
                it.nullLiteral?.let { return "null" }
                it.longLiteral?.value?.let { return it }
                it.unsignedLiteral?.value?.let { return it }
            }
        //字符串
        primaryExpression?.stringLiteral?.lineStringLiteral?.sub?.firstOrNull()
            ?.lineStringContent?.lineStrText?.value
            ?.let { return "\"$it\"" }
        return ""
    }

    //创建类普通参数
    private fun createParameter(classMemberDeclaration: ClassMemberDeclaration): KBParameter? {
        val propertyDeclaration = classMemberDeclaration.declaration?.propertyDeclaration ?: return null
        return KBParameter(
            name = propertyDeclaration.variableDeclaration?.simpleIdentifier?.value ?: "",
            mutable = propertyDeclaration.mutable,
            doc = "",
            type = createType(propertyDeclaration.variableDeclaration?.type),
            defaultValue = getDefaultValue(propertyDeclaration.expression),
        )
    }

    //创建类型
    private fun createType(type: Type?): KBType {
        val kbType = KBType()
        val userType = type?.typeReference?.userType ?: type?.nullableType?.typeReference?.userType
        userType?.simpleUserType?.firstOrNull()?.let {
            kbType.name = it.simpleIdentifier.value
            kbType.arguments = it.typeArguments?.typeProjection?.map {
                createType(it.type)
            } ?: listOf()
        }
        kbType.nullable = type?.nullableType != null
        return kbType
    }

}