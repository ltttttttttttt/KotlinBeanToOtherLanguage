package tree.grammar

class EnumClassBody(
	val enumEntries: EnumEntries?,
	val classMemberDeclarations: ClassMemberDeclarations?
) : TreeNode(
	"enumClassBody",
	"enumEntries" to enumEntries,
	"classMemberDeclarations" to classMemberDeclarations
)

class EnumEntries(
	val enumEntry: List<EnumEntry>
) : TreeNode(
	"enumEntries",
	"enumEntry" to TreeList(enumEntry)
)

class EnumEntry(
	val modifiers: Modifiers?,
	val simpleIdentifier: SimpleIdentifier,
	val valueArguments: ValueArguments?,
	val classBody: ClassBody?
) : TreeNode(
	"enumEntry",
	"modifiers" to modifiers,
	"simpleIdentifier" to simpleIdentifier,
	"valueArguments" to valueArguments,
	"classBody" to classBody
)