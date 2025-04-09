package tree.grammar

class Statements(
	val statement: List<Statement>
) : TreeNode(
	"statements",
	"statement" to TreeList(statement)
)

class Statement(
	val label: List<Label>,
	val annotation: List<Annotation>,
	val declaration: Declaration?,
	val assignment: Assignment?,
	val loopStatement: LoopStatement?,
	val expression: Expression?
) : TreeNode(
	"statement",
	"label" to TreeList(label),
	"annotation" to TreeList(annotation),
	"declaration" to declaration,
	"assignment" to assignment,
	"loopStatement" to loopStatement,
	"expression" to expression
)

class Label(
	val simpleIdentifier: SimpleIdentifier
) : TreeNode(
	"label",
	"simpleIdentifier" to simpleIdentifier
)

class ControlStructureBody(
	val block: Block?,
	val statement: Statement?
) : TreeNode(
	"controlStructureBody",
	"block" to block,
	"statement" to statement
)

class Block(
	val statements: Statements
) : TreeNode(
	"block",
	"statements" to statements
)

class LoopStatement(
	val forStatement: ForStatement?,
	val whileStatement: WhileStatement?,
	val doWhileStatement: DoWhileStatement?
) : TreeNode(
	"loopStatement",
	"forStatement" to forStatement,
	"whileStatement" to whileStatement,
	"doWhileStatement" to doWhileStatement
)

class ForStatement(
	val annotation: List<Annotation>,
	val variableDeclaration: VariableDeclaration?,
	val multiVariableDeclaration: MultiVariableDeclaration?,
	val expression: Expression,
	val controlStructureBody: ControlStructureBody?
) : TreeNode(
	"forStatement",
	"annotation" to TreeList(annotation),
	"variableDeclaration" to variableDeclaration,
	"multiVariableDeclaration" to multiVariableDeclaration,
	"expression" to expression,
	"controlStructureBody" to controlStructureBody
)

class WhileStatement(
	val expression: Expression,
	val controlStructureBody: ControlStructureBody?
) : TreeNode(
	"whileStatement",
	"expression" to expression,
	"controlStructureBody" to controlStructureBody
)

class DoWhileStatement(
	val controlStructureBody: ControlStructureBody?,
	val expression: Expression
) : TreeNode(
	"doWhileStatement",
	"controlStructureBody" to controlStructureBody,
	"expression" to expression
)

class Assignment(
	val directlyAssignableExpression: DirectlyAssignableExpression?,
	val assignableExpression: AssignableExpression?,
	val assignmentAndOperator: AssignmentAndOperator?,
	val expression: Expression
) : TreeNode(
	"assignment",
	"directlyAssignableExpression" to directlyAssignableExpression,
	"assignableExpression" to assignableExpression,
	"assignmentAndOperator" to assignmentAndOperator,
	"expression" to expression
)