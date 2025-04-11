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


    private val classDoc = mutableListOf<KBDoc>()//类的所有注释
    private val parameterDoc = mutableListOf<KBDoc>()//属性的所有注释
    private var input = ""

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
        this.input = input
        classDoc.clear()
        parameterDoc.clear()
        initDoc(input)
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
        val primaryConstructorParameters = mutableListOf<KBParameter>()
        val parameters = mutableListOf<KBParameter>()
        val innerClassList = mutableListOf<KBClass>()
        classDeclaration.primaryConstructor?.classParameters?.classParameter?.forEach {
            val constructorParameter = createPrimaryConstructorParameters(it)
            if (constructorParameter != null) {
                primaryConstructorParameters.add(constructorParameter)
                //如果只是构造参数(非val,var)则不添加到参数中(lr中没有包含相应信息)
                if (input.contains(Regex("va[rl][ \n]+${constructorParameter.name}[ :=\n]")))
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
            doc = getDoc(name, true, false),
            primaryConstructorParameters = primaryConstructorParameters,
            parameters = parameters,
            innerClass = innerClassList,
        )
    }

    //创建主构造函数参数
    private fun createPrimaryConstructorParameters(classParameter: ClassParameter): KBParameter? {
        val name = classParameter.simpleIdentifier.value
        return KBParameter(
            name = name,
            mutable = classParameter.mutable,
            doc = getDoc(name, false, true),
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
        val name = propertyDeclaration.variableDeclaration?.simpleIdentifier?.value ?: ""
        return KBParameter(
            name = name,
            mutable = propertyDeclaration.mutable,
            doc = getDoc(name, false, true),
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

    //初始化注释,目前使用正则匹配方式实现
    private fun initDoc(input: String) {
        val pattern = """
        ((/\*(?s).*?\*/)      # 文档注释
        |                       # 或
        (//.*))                 # 行注释
        [\s\S]*?                # 任意字符（非贪婪）
        \b(class|interface|object|val|var)\s+  # 类或属性关键字
        ([^\s(:]+)              # 类/属性名
    """.trimIndent().toRegex(RegexOption.COMMENTS)

        pattern.findAll(input).forEach { matchResult ->
            //找到的注释
            val (docComment) = matchResult.destructured
            //注释对应的属性名
            val elementName: String
            val comment = when {
                docComment.startsWith("/**") -> {
                    elementName = matchResult.groupValues[5]
                    docComment
                        .removeSurrounding("/**", "*/")
                        .trimIndent()
                        .lines()
                        .joinToString("\n") { it.replace(Regex("^\\s*\\* ?"), "").trim() }
                }

                docComment.startsWith("//") -> {
                    //如果是单行注释,并且是行后注释,则用正则匹配到对应的属性名
                    val leftName =
                        """(?:val|var)\s+(\w+).*?${docComment}""".toRegex().find(input)?.groupValues?.getOrNull(1)
                    elementName = if (leftName.isNullOrEmpty())
                        matchResult.groupValues[5]
                    else
                        leftName
                    docComment
                        .removePrefix("//")
                        .trim()
                }

                else -> return@forEach
            }

            val doc = KBDoc(elementName, comment)
            when (matchResult.groupValues[4]) {
                "class", "interface", "object" -> classDoc.add(doc)
                "val", "var" -> parameterDoc.add(doc)
            }
        }
    }

    //获取注释
    private fun getDoc(name: String, isClass: Boolean, isParameter: Boolean): String {
        return if (isClass) {
            classDoc.find {
                it.name == name
            }?.doc ?: ""
        } else if (isParameter) {
            parameterDoc.find {
                it.name == name
            }?.doc ?: ""
        } else {
            ""
        }
    }
}