package com.lt.kbtol.structure

/**
 * creator: lt  2025/4/9  lt.dygzs@qq.com
 * effect : 类
 * warning:
 */
class KBClass(
    //类名
    val name: String,
    //类注释(暂不可用)
    val doc: String,
    //主构造函数的属性
    val primaryConstructorParameters: List<KBParameter>,
    //所有属性
    val parameters: List<KBParameter>,
)