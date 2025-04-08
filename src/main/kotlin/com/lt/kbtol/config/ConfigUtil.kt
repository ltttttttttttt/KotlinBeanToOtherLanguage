package com.lt.kbtol.config

import com.intellij.openapi.actionSystem.AnActionEvent
import com.lt.kbtol.util.jsonToAny
import com.lt.kbtol.util.toJson
import java.io.File

/**
 * creator: lt  2023/7/3  lt.dygzs@qq.com
 * effect :
 * warning:
 */
object ConfigUtil {
    fun getConfig(e: AnActionEvent): Config {
        val rootPath = e.project?.basePath ?: return Config()
        val configFile = File(rootPath, ".idea/kotlinBeanToOtherLanguage")
        if (!configFile.isFile)
            return Config()
        return configFile.readText().jsonToAny<Config>()
    }

    fun saveConfig(e: AnActionEvent, config: Config) {
        val rootPath = e.project?.basePath ?: return
        val configFile = File(rootPath, ".idea/kotlinBeanToOtherLanguage")
        if (!configFile.isFile) {
            if (!configFile.parentFile.isDirectory)
                configFile.parentFile.mkdirs()
            configFile.createNewFile()
        }
        configFile.writeText(config.toJson())
    }
}