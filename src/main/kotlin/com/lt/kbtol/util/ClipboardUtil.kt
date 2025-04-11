package com.lt.kbtol.util

import java.awt.*
import java.awt.datatransfer.*


/**
 * creator: lt  2020/10/14  lt.dygzs@qq.com
 * effect : 剪切板工具类
 * warning:
 */
object ClipboardUtil {
    fun getClipboardText(): String? {
        val sysClip: Clipboard = Toolkit.getDefaultToolkit().systemClipboard
        // 获取剪切板中的内容
        // 获取剪切板中的内容
        val clipTf = sysClip.getContents(null)
        if (clipTf != null) {
            // 检查内容是否是文本类型
            if (clipTf.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                return clipTf.getTransferData(DataFlavor.stringFlavor) as? String
            }
        }
        return null
    }

    fun setClipboardText(
        text: String,
    ) {
        val clip = Toolkit.getDefaultToolkit().systemClipboard
        val tText: Transferable = StringSelection(text)
        clip.setContents(tText, null)
    }
}