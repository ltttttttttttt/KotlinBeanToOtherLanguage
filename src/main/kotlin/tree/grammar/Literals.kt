package tree.grammar

class RealLiteral(
	val value: String
) : TreeNode(
	"RealLiteral",
	"value" to TreeString(value)
)

class IntegerLiteral(
	val value: String
) : TreeNode(
	"IntegerLiteral",
	"value" to TreeString(value)
)

class HexLiteral(
	val value: String
) : TreeNode(
	"HexLiteral",
	"value" to TreeString(value)
)

class BinLiteral(
	val value: String
) : TreeNode(
	"BinLiteral",
	"value" to TreeString(value)
)

class UnsignedLiteral(
	val value: String
) : TreeNode(
	"UnsignedLiteral",
	"value" to TreeString(value)
)

class LongLiteral(
	val value: String
) : TreeNode(
	"LongLiteral",
	"value" to TreeString(value)
)

class BooleanLiteral(
	val value: Boolean
) : TreeNode(
	"BooleanLiteral",
	"value" to TreeBoolean(value)
)

object NullLiteral : TreeNode("NullLiteral")

class CharacterLiteral(
	val value: String
) : TreeNode(
	"CharacterLiteral",
	"value" to TreeString(value)
)