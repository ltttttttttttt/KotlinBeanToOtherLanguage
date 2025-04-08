package com.lt.kbtol.config

data class Config(
    var language: LanguageEnum = LanguageEnum.TS,//转意为哪种语言
)

//参考: https://api.fanyi.baidu.com/doc/21
enum class LanguageEnum(
    val language: String,//编程语言名字
) {
    TS("TypeScript"),
    JS("Javascript"),
}