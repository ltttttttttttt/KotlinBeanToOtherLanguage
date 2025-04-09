package tree.grammar

class SimpleIdentifier(
	val value: String
) : TreeNode(
	"simpleIdentifier",
	"value" to TreeString(value)
)

class Identifier(
	val simpleIdentifier: List<SimpleIdentifier>
) : TreeNode(
	"identifier",
	"simpleIdentifier" to TreeList(simpleIdentifier)
)