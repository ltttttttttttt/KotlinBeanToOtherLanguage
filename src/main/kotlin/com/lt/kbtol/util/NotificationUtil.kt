package com.lt.kbtol.util

import com.intellij.notification.*

/**
 * creator: lt  2025/4/11  lt.dygzs@qq.com
 * effect : 右下角弱提示
 * warning:
 */
object NotificationUtil {
    fun showNotification(msg: String) {
        // 创建通知
        val notification = Notification(
            "KotlinBeanToOtherLanguage", // 通知组名
            "KotlinBeanToOtherLanguage",               // 标题
            msg,   // 内容
            NotificationType.INFORMATION // 类型（INFO、WARNING、ERROR）
        )

        // 显示通知
        Notifications.Bus.notify(notification)
    }
}