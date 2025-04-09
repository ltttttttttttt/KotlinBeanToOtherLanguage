package tree.syntax

import tree.grammar.ImportHeader
import tree.grammar.Statement
import tree.grammar.TopLevelObject
import tree.grammar.UnescapedAnnotation

class KotlinFile(
	val fileName: String,
	val fileAnnotations: List<UnescapedAnnotation>,
	val `package`: String?,
	val imports: List<ImportHeader>,
	val topLevelObjects: List<TopLevelObject>
) : AST(
	"KotlinFile",
	"fileName" to fileName,
	"fileAnnotations" to fileAnnotations,
	"package" to `package`,
	"imports" to imports,
	"topLevelObjects" to topLevelObjects
)

class KotlinScript(
	val fileName: String,
	val fileAnnotations: List<UnescapedAnnotation>,
	val `package`: String?,
	val imports: List<ImportHeader>,
	val statements: List<Statement>
) : AST(
	"KotlinScript",
	"fileName" to fileName,
	"fileAnnotations" to fileAnnotations,
	"package" to `package`,
	"imports" to imports,
	"statements" to statements
)