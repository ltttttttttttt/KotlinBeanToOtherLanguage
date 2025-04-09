package com.lt.kbtol.structure

/**
 * creator: lt  2025/4/9  lt.dygzs@qq.com
 * effect : 参数
 * warning:
 */
class KBParameter(
    //参数名
    val name: String,
    //是否可变(final)
    val mutable: Boolean,
    //注释(暂不可用)
    val doc: String,
    //参数类型
    val type: KBType,
    //默认值(暂不可用)
    val defaultValue: String?,
)