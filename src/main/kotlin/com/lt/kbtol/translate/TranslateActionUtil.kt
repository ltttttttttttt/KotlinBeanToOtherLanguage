package com.lt.kbtol.translate

import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.command.*
import com.lt.kbtol.config.*
import com.lt.kbtol.util.*

/**
 * creator: lt  2023/7/4  lt.dygzs@qq.com
 * effect :
 * warning:
 */
object TranslateActionUtil {
    /**
     * 插入到用户当前光标
     */
    fun doInsertAction(e: AnActionEvent, languageEnum: LanguageEnum, input: String) {
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

    /**
     * 将内容插入到剪切板
     */
    fun doClipBoardAction(e: AnActionEvent, languageEnum: LanguageEnum, input: String) {
        val output = languageEnum.translate.action(e, languageEnum, input)
        ClipboardUtil.setClipboardText(output)
        NotificationUtil.showNotification("Copy to clipboard")
    }
}