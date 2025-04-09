package com.lt.kbtol.translate

import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.command.*
import com.lt.kbtol.config.*

/**
 * creator: lt  2023/7/4  lt.dygzs@qq.com
 * effect :
 * warning:
 */
object TranslateActionUtil {
    fun doAction(e: AnActionEvent, languageEnum: LanguageEnum, input: String) {
        //入口

        val editor = e.getData(CommonDataKeys.EDITOR) ?: return
        val model = editor.selectionModel
        var selectStart = model.selectionStart

        val output = languageEnum.translate.action(e, languageEnum, input)

        //只允许异步修改文件
        WriteCommandAction.runWriteCommandAction(e.project) {
            //在光标位置插入结果
            editor.document.replaceString(selectStart, selectStart, output)
        }
    }
}