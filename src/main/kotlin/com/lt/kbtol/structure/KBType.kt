package com.lt.kbtol.structure

/**
 * creator: lt  2025/4/9  lt.dygzs@qq.com
 * effect : 类型
 * warning:
 */
class KBType(
    //类型名(String)
    var name: String="",
    //是否可空
    var nullable: Boolean=true,
    //泛型
    var arguments: List<KBType> = listOf(),
)