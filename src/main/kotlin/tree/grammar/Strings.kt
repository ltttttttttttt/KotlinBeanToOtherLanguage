package tree.grammar

class LineStrRef(
	val identifier: String
) : TreeNode(
	"LineStrRef",
	"identifier" to TreeString(identifier)
)

class LineStrText(
	val value: String
) : TreeNode(
	"LineStrText",
	"value" to TreeString(value)
)

class LineStrEscapedChar(
	val value: String
) : TreeNode(
	"LineStrEscapedChar",
	"value" to TreeString(value)
)

class MultiLineStringQuote(
	val count: Int
) : TreeNode(
	"MultiLineStringQuote",
	"count" to TreeNumber(count)
)

class MultiLineStrRef(
	val identifier: String
) : TreeNode(
	"MultiLineStrRef",
	"identifier" to TreeString(identifier)
)

class MultiLineStrText(
	val value: String
) : TreeNode(
	"MultiLineStrText",
	"value" to TreeString(value)
)