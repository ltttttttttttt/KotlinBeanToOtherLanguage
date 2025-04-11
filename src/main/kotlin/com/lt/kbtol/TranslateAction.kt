package com.lt.kbtol

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.awt.*
import androidx.compose.ui.unit.*
import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.project.*
import com.intellij.openapi.ui.*
import com.intellij.util.ui.*
import com.lt.kbtol.config.*
import com.lt.kbtol.translate.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.swing.*

class TranslateAction : DumbAwareAction() {

    override fun actionPerformed(e: AnActionEvent) {
        //入口,判断用户是否选择了文本,如果选择了文本则直接处理并放入剪切板,否则使用ui
        val selectedText = getSelectedText(e)
        if (selectedText.isNullOrEmpty())
            doUIAction(e)
        else
            doAction(e, selectedText)
    }

    //获取当前选中的文本
    private fun getSelectedText(e: AnActionEvent): String? {
        val editor = e.getData(CommonDataKeys.EDITOR) ?: return null
        val model = editor.selectionModel
        return model.selectedText
    }

    //直接处理入口
    private fun doAction(e: AnActionEvent, selectedText: String) {
        TranslateActionUtil.doClipBoardAction(e, ConfigUtil.getConfig(e).language, selectedText)
    }

    //ui入口
    private fun doUIAction(e: AnActionEvent) {
        SampleDialog(e).show()
    }

    class SampleDialog(val e: AnActionEvent) : DialogWrapper(e.project) {
        val scope = CoroutineScope(Dispatchers.Default)
        val config = ConfigUtil.getConfig(e)
        var language by mutableStateOf(LanguageEnum.TS)
        var kotlinCode by mutableStateOf("")


        init {
            title = "Kotlin Bean To Other Language"
            init()

            language = config.language
            scope.launch {
                snapshotFlow { language }.collect {
                    config.language = it
                    ConfigUtil.saveConfig(e, config)
                }
            }
        }

        override fun dispose() {
            super.dispose()
            scope.cancel()
        }

        override fun createCenterPanel(): JComponent {
            return ComposePanel().apply {
                setBounds(0, 0, 800, 600)
                setContent {
                    MaterialTheme(
                        if (UIUtil.isUnderDarcula()) darkColors() else lightColors()
                    ) {
                        Surface {
                            App()
                        }
                    }
                }
            }
        }

        @OptIn(ExperimentalLayoutApi::class)
        @Composable
        fun App() {
            Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(horizontal = 12.dp)) {
                FlowRow(verticalAlignment = Alignment.CenterVertically) {
                    Text("To language: ")
                    LanguageEnum.values().forEach {
                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable { language = it }) {
                            RadioButton(language == it, {
                                language = it
                            })
                            Text(it.language)
                            Spacer(Modifier.width(12.dp))
                        }
                    }
                }
                Spacer(Modifier.height(15.dp))
                TextField(kotlinCode, ::kotlinCode::set, Modifier.fillMaxSize().heightIn(500.dp), placeholder = {
                    Text("Enter kotlin bean code")
                })
            }
        }

        override fun doOKAction() {
            super.doOKAction()
            //输出
            TranslateActionUtil.doInsertAction(e, language, kotlinCode)
        }
    }
}

