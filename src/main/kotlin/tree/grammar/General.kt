package tree.grammar

class KotlinFile(
	val fileAnnotation: List<FileAnnotation>,
	val packageHeader: PackageHeader,
	val importList: ImportList,
	val topLevelObject: List<TopLevelObject>
) : TreeNode(
	"kotlinFile",
	"fileAnnotation" to TreeList(fileAnnotation),
	"packageHeader" to packageHeader,
	"importList" to importList,
	"topLevelObject" to TreeList(topLevelObject)
)

class Script(
	val fileAnnotation: List<FileAnnotation>,
	val packageHeader: PackageHeader,
	val importList: ImportList,
	val statement: List<Statement>
) : TreeNode(
	"script",
	"fileAnnotation" to TreeList(fileAnnotation),
	"packageHeader" to packageHeader,
	"importList" to importList,
	"statement" to TreeList(statement)
)

class FileAnnotation(
	val unescapedAnnotation: List<UnescapedAnnotation>
) : TreeNode(
	"fileAnnotation",
	"unescapedAnnotation" to TreeList(unescapedAnnotation)
)

class PackageHeader(
	val identifier: Identifier?
) : TreeNode(
	"packageHeader",
	"identifier" to identifier
)

class ImportList(
	val importHeader: List<ImportHeader>
) : TreeNode(
	"importList",
	"importHeader" to TreeList(importHeader)
)

class ImportHeader(
	val identifier: Identifier,
	val multi: Boolean,
	val importAlias: ImportAlias?
) : TreeNode(
	"importHeader",
	"identifier" to identifier,
	"multi" to TreeBoolean(multi),
	"importAlias" to importAlias
)

class ImportAlias(
	val simpleIdentifier: SimpleIdentifier
) : TreeNode(
	"importAlias",
	"simpleIdentifier" to simpleIdentifier
)

class TopLevelObject(
	val declaration: Declaration
) : TreeNode(
	"topLevelObject",
	"declaration" to declaration
)

class TypeAlias(
	val modifiers: Modifiers?,
	val simpleIdentifier: SimpleIdentifier,
	val typeParameters: TypeParameters?,
	val type: Type
) : TreeNode(
	"typeAlias",
	"modifiers" to modifiers,
	"simpleIdentifier" to simpleIdentifier,
	"typeParameters" to typeParameters,
	"type" to type
)

class Declaration(
	val classDeclaration: ClassDeclaration?,
	val objectDeclaration: ObjectDeclaration?,
	val functionDeclaration: FunctionDeclaration?,
	val propertyDeclaration: PropertyDeclaration?,
	val typeAlias: TypeAlias?
) : TreeNode(
	"declaration",
	"classDeclaration" to classDeclaration,
	"objectDeclaration" to objectDeclaration,
	"functionDeclaration" to functionDeclaration,
	"propertyDeclaration" to propertyDeclaration,
	"typeAlias" to typeAlias
)