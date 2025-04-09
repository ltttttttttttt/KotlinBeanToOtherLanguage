package tree.grammar

class Expression(
	val disjunction: Disjunction
) : TreeNode(
	"expression",
	"disjunction" to disjunction
)

class Disjunction(
	val conjunction: List<Conjunction>
) : TreeNode(
	"disjunction",
	"conjunction" to TreeList(conjunction),
)

class Conjunction(
	val equality: List<Equality>
) : TreeNode(
	"conjunction",
	"equality" to TreeList(equality),
)

class Equality(
	val comparison: List<Comparison>,
	val equalityOperator: List<EqualityOperator>
) : TreeNode(
	"equality",
	"comparison" to TreeList(comparison),
	"equalityOperator" to TreeList(equalityOperator)
)

class Comparison(
	val genericCallLikeComparison: List<GenericCallLikeComparison>,
	val comparisonOperator: List<ComparisonOperator>
) : TreeNode(
	"comparison",
	"genericCallLikeComparison" to TreeList(genericCallLikeComparison),
	"comparisonOperator" to TreeList(comparisonOperator)
)

class GenericCallLikeComparison(
	val infixOperation: InfixOperation,
	val callSuffix: List<CallSuffix>
) : TreeNode(
	"genericCallLikeComparison",
	"infixOperation" to infixOperation,
	"callSuffix" to TreeList(callSuffix)
)

class InfixOperation(
	val elvisExpression: ElvisExpression,
	val sub: List<Sub>
) : TreeNode(
	"infixOperation",
	"elvisExpression" to elvisExpression,
	"sub" to TreeList(sub)
) {
	sealed class Sub(name: String, vararg properties: Pair<String, Tree?>) : TreeNode(name, *properties)

	class In(
		val operator: InOperator,
		val right: ElvisExpression
	) : Sub(
		"in",
		"operator" to operator,
		"right" to right
	)

	class Is(
		val operator: IsOperator,
		val right: Type
	) : Sub(
		"in",
		"operator" to operator,
		"right" to right
	)
}

class ElvisExpression(
	val infixFunctionCall: List<InfixFunctionCall>
) : TreeNode(
	"elvisExpression",
	"infixFunctionCall" to TreeList(infixFunctionCall)
)

class InfixFunctionCall(
	val rangeExpression: List<RangeExpression>,
	val simpleIdentifier: List<SimpleIdentifier>
) : TreeNode(
	"infixFunctionCall",
	"rangeExpression" to TreeList(rangeExpression),
	"simpleIdentifier" to TreeList(simpleIdentifier)
)

class RangeExpression(
	val additiveExpression: List<AdditiveExpression>,
	val sub: List<Sub>
) : TreeNode(
	"rangeExpression",
	"additiveExpression" to TreeList(additiveExpression),
	"sub" to TreeList(sub)
) {
	sealed class Sub(name: String) : TreeNode(name)
	object Range : Sub("RANGE")
	object RangeUntil : Sub("RANGE_UNTIL")
}

class AdditiveExpression(
	val multiplicativeExpression: List<MultiplicativeExpression>,
	val additiveOperator: List<AdditiveOperator>
) : TreeNode(
	"additiveExpression",
	"multiplicativeExpression" to TreeList(multiplicativeExpression),
	"additiveOperator" to TreeList(additiveOperator)
)

class MultiplicativeExpression(
	val asExpression: List<AsExpression>,
	val multiplicativeOperator: List<MultiplicativeOperator>
) : TreeNode(
	"multiplicativeExpression",
	"asExpression" to TreeList(asExpression),
	"multiplicativeOperator" to TreeList(multiplicativeOperator)
)

class AsExpression(
	val prefixUnaryExpression: PrefixUnaryExpression,
	val asOperator: List<AsOperator>,
	val type: List<Type>
) : TreeNode(
	"asExpression",
	"prefixUnaryExpression" to prefixUnaryExpression,
	"asOperator" to TreeList(asOperator),
	"type" to TreeList(type)
)

class PrefixUnaryExpression(
	val unaryPrefix: List<UnaryPrefix>,
	val postfixUnaryExpression: PostfixUnaryExpression
) : TreeNode(
	"prefixUnaryExpression",
	"unaryPrefix" to TreeList(unaryPrefix),
	"postfixUnaryExpression" to postfixUnaryExpression
)

class UnaryPrefix(
	val annotation: Annotation?,
	val label: Label?,
	val prefixUnaryOperator: PrefixUnaryOperator?
) : TreeNode(
	"unaryPrefix",
	"annotation" to annotation,
	"label" to label,
	"prefixUnaryOperator" to prefixUnaryOperator
)

class PostfixUnaryExpression(
	val primaryExpression: PrimaryExpression,
	val postfixUnarySuffix: List<PostfixUnarySuffix>
) : TreeNode(
	"postfixUnaryExpression",
	"primaryExpression" to primaryExpression,
	"postfixUnarySuffix" to TreeList(postfixUnarySuffix)
)

class PostfixUnarySuffix(
	val postfixUnaryOperator: PostfixUnaryOperator?,
	val typeArguments: TypeArguments?,
	val callSuffix: CallSuffix?,
	val indexingSuffix: IndexingSuffix?,
	val navigationSuffix: NavigationSuffix?
) : TreeNode(
	"postfixUnarySuffix",
	"postfixUnaryOperator" to postfixUnaryOperator,
	"typeArguments" to typeArguments,
	"callSuffix" to callSuffix,
	"indexingSuffix" to indexingSuffix,
	"navigationSuffix" to navigationSuffix
)

class DirectlyAssignableExpression(
	val postfixUnaryExpression: PostfixUnaryExpression?,
	val assignableSuffix: AssignableSuffix?,
	val simpleIdentifier: SimpleIdentifier?,
	val parenthesizedDirectlyAssignableExpression: ParenthesizedDirectlyAssignableExpression?
) : TreeNode(
	"directlyAssignableExpression",
	"postfixUnaryExpression" to postfixUnaryExpression,
	"assignableSuffix" to assignableSuffix,
	"simpleIdentifier" to simpleIdentifier,
	"parenthesizedDirectlyAssignableExpression" to parenthesizedDirectlyAssignableExpression
)

class ParenthesizedDirectlyAssignableExpression(
	val directlyAssignableExpression: DirectlyAssignableExpression
) : TreeNode(
	"parenthesizedDirectlyAssignableExpression",
	"directlyAssignableExpression" to directlyAssignableExpression
)

class AssignableExpression(
	val prefixUnaryExpression: PrefixUnaryExpression?,
	val parenthesizedAssignableExpression: ParenthesizedAssignableExpression?
) : TreeNode(
	"assignableExpression",
	"prefixUnaryExpression" to prefixUnaryExpression,
	"parenthesizedAssignableExpression" to parenthesizedAssignableExpression
)

class ParenthesizedAssignableExpression(
	val assignableExpression: AssignableExpression
) : TreeNode(
	"parenthesizedAssignableExpression",
	"assignableExpression" to assignableExpression
)

class AssignableSuffix(
	val typeArguments: TypeArguments?,
	val indexingSuffix: IndexingSuffix?,
	val navigationSuffix: NavigationSuffix?
) : TreeNode(
	"assignableSuffix",
	"typeArguments" to typeArguments,
	"indexingSuffix" to indexingSuffix,
	"navigationSuffix" to navigationSuffix
)

class IndexingSuffix(
	val expression: List<Expression>
) : TreeNode(
	"indexingSuffix",
	"expression" to TreeList(expression)
)

class NavigationSuffix(
	val memberAccessOperator: MemberAccessOperator,
	val simpleIdentifier: SimpleIdentifier?,
	val parenthesizedExpression: ParenthesizedExpression?,
	val `class`: Boolean
) : TreeNode(
	"navigationSuffix",
	"memberAccessOperator" to memberAccessOperator,
	"simpleIdentifier" to simpleIdentifier,
	"parenthesizedExpression" to parenthesizedExpression,
	"class" to TreeBoolean(`class`)
)

class CallSuffix(
	val typeArguments: TypeArguments?,
	val valueArguments: ValueArguments?,
	val annotatedLambda: AnnotatedLambda?
) : TreeNode(
	"callSuffix",
	"valueArguments" to valueArguments,
	"annotatedLambda" to annotatedLambda
)

class AnnotatedLambda(
	val annotation: List<Annotation>,
	val label: Label?,
	val lambdaLiteral: LambdaLiteral
) : TreeNode(
	"annotatedLambda",
	"annotation" to TreeList(annotation),
	"label" to label,
	"lambdaLiteral" to lambdaLiteral
)

class TypeArguments(
	val typeProjection: List<TypeProjection>
) : TreeNode(
	"typeArguments",
	"typeProjection" to TreeList(typeProjection)
)

class ValueArguments(
	val valueArgument: List<ValueArgument>
) : TreeNode(
	"valueArguments",
	"valueArgument" to TreeList(valueArgument)
)

class ValueArgument(
	val annotation: Annotation?,
	val simpleIdentifier: SimpleIdentifier?,
	val multi: Boolean,
	val expression: Expression
) : TreeNode(
	"valueArgument",
	"annotation" to annotation,
	"simpleIdentifier" to simpleIdentifier,
	"multi" to TreeBoolean(multi),
	"expression" to expression
)

class PrimaryExpression(
	val parenthesizedExpression: ParenthesizedExpression?,
	val simpleIdentifier: SimpleIdentifier?,
	val literalConstant: LiteralConstant?,
	val stringLiteral: StringLiteral?,
	val callableReference: CallableReference?,
	val functionLiteral: FunctionLiteral?,
	val objectLiteral: ObjectLiteral?,
	val collectionLiteral: CollectionLiteral?,
	val thisExpression: ThisExpression?,
	val superExpression: SuperExpression?,
	val ifExpression: IfExpression?,
	val whenExpression: WhenExpression?,
	val tryExpression: TryExpression?,
	val jumpExpression: JumpExpression?
) : TreeNode(
	"primaryExpression",
	"parenthesizedExpression" to parenthesizedExpression,
	"simpleIdentifier" to simpleIdentifier,
	"literalConstant" to literalConstant,
	"stringLiteral" to stringLiteral,
	"callableReference" to callableReference,
	"functionLiteral" to functionLiteral,
	"ObjectLiteral" to objectLiteral,
	"collectionLiteral" to collectionLiteral,
	"thisExpression" to thisExpression,
	"superExpression" to superExpression,
	"ifExpression" to ifExpression,
	"whenExpression" to whenExpression,
	"tryExpression" to tryExpression,
	"jumpExpression" to jumpExpression
)

class ParenthesizedExpression(
	val expression: Expression
) : TreeNode(
	"parenthesizedExpression",
	"expression" to expression
)

class CollectionLiteral(
	val expression: List<Expression>
) : TreeNode(
	"collectionLiteral",
	"expression" to TreeList(expression)
)

class LiteralConstant(
	val booleanLiteral: BooleanLiteral?,
	val integerLiteral: IntegerLiteral?,
	val hexLiteral: HexLiteral?,
	val binLiteral: BinLiteral?,
	val characterLiteral: CharacterLiteral?,
	val realLiteral: RealLiteral?,
	val nullLiteral: NullLiteral?,
	val longLiteral: LongLiteral?,
	val unsignedLiteral: UnsignedLiteral?
) : TreeNode(
	"literalConstant",
	"booleanLiteral" to booleanLiteral,
	"integerLiteral" to integerLiteral,
	"hexLiteral" to hexLiteral,
	"binLiteral" to binLiteral,
	"characterLiteral" to characterLiteral,
	"realLiteral" to realLiteral,
	"nullLiteral" to nullLiteral,
	"longLiteral" to longLiteral,
	"unsignedLiteral" to unsignedLiteral
)

class StringLiteral(
	val lineStringLiteral: LineStringLiteral?,
	val multiLineStringLiteral: MultiLineStringLiteral?,
) : TreeNode(
	"stringLiteral",
	"lineStringLiteral" to lineStringLiteral,
	"multiLineStringLiteral" to multiLineStringLiteral
)

class LineStringLiteral(
	val sub: List<Sub>
) : TreeNode(
	"lineStringLiteral",
	"sub" to TreeList(sub)
) {
	class Sub(
		val lineStringContent: LineStringContent?,
		val lineStringExpression: LineStringExpression?
	) : TreeNode(
		"sub",
		"lineStringContent" to lineStringContent,
		"lineStringExpression" to lineStringExpression
	)
}

class MultiLineStringLiteral(
	val sub: List<Sub>
) : TreeNode(
	"multiLineStringLiteral",
	"sub" to TreeList(sub)
) {
	class Sub(
		val multiLineStringContent: MultiLineStringContent?,
		val multiLineStringExpression: MultiLineStringExpression?,
		val multiLineStringQuote: Boolean
	) : TreeNode(
		"sub",
		"multiLineStringContent" to multiLineStringContent,
		"multiLineStringExpression" to multiLineStringExpression,
		"multiLineStringQuote" to TreeBoolean(multiLineStringQuote)
	)
}

class LineStringContent(
	val lineStrText: LineStrText?,
	val lineStrEscapedChar: LineStrEscapedChar?,
	val lineStrRef: LineStrRef?
) : TreeNode(
	"lineStringContent",
	"lineStrText" to lineStrText,
	"lineStrEscapedChar" to lineStrEscapedChar,
	"lineStrRef" to lineStrRef
)

class LineStringExpression(
	val expression: Expression
) : TreeNode(
	"lineStringExpression",
	"expression" to expression
)

class MultiLineStringContent(
	val multiLineStrText: MultiLineStrText?,
	val multiLineStringQuote: MultiLineStringQuote?,
	val multiLineStrRef: MultiLineStrRef?
) : TreeNode(
	"multiLineStringContent",
	"multiLineStrText" to multiLineStrText,
	"multiLineStringQuote" to multiLineStringQuote,
	"multiLineStrRef" to multiLineStrRef
)

class MultiLineStringExpression(
	val expression: Expression
) : TreeNode(
	"multiLineStringExpression",
	"expression" to expression
)

class LambdaLiteral(
	val lambdaParameters: LambdaParameters?,
	val statements: Statements
) : TreeNode(
	"lambdaLiteral",
	"lambdaParameters" to lambdaParameters,
	"statements" to statements
)

class LambdaParameters(
	val lambdaParameter: List<LambdaParameter>
) : TreeNode(
	"lambdaParameters",
	"lambdaParameter" to TreeList(lambdaParameter)
)

class LambdaParameter(
	val variableDeclaration: VariableDeclaration?,
	val multiVariableDeclaration: MultiVariableDeclaration?,
	val type: Type?
) : TreeNode(
	"lambdaParameter",
	"variableDeclaration" to variableDeclaration,
	"multiVariableDeclaration" to multiVariableDeclaration,
	"type" to type
)

class AnonymousFunction(
	val suspend: Boolean,
	val type: Type?,
	val parametersWithOptionalType: ParametersWithOptionalType,
	val typeRight: Type?,
	val typeConstraints: TypeConstraints?,
	val functionBody: FunctionBody?
) : TreeNode(
	"anonymousFunction",
	"suspend" to TreeBoolean(suspend),
	"type" to type,
	"parametersWithOptionalType" to parametersWithOptionalType,
	"typeRight" to typeRight,
	"typeConstraints" to typeConstraints,
	"functionBody" to functionBody
)

class FunctionLiteral(
	val lambdaLiteral: LambdaLiteral?,
	val anonymousFunction: AnonymousFunction?
) : TreeNode(
	"functionLiteral",
	"lambdaLiteral" to lambdaLiteral,
	"anonymousFunction" to anonymousFunction
)

class ObjectLiteral(
	val data: Boolean,
	val delegationSpecifiers: DelegationSpecifiers?,
	val classBody: ClassBody?
) : TreeNode(
	"objectLiteral",
	"delegationSpecifiers" to delegationSpecifiers,
	"classBody" to classBody
)

class ThisExpression(
	val at: String?
) : TreeNode(
	"thisExpression",
	"at" to at?.let { TreeString(it) }
)

class SuperExpression(
	val type: Type?,
	val simpleIdentifier: SimpleIdentifier?,
	val at: String?
) : TreeNode(
	"superExpression",
	"type" to type,
	"simpleIdentifier" to simpleIdentifier,
	"at" to at?.let { TreeString(it) }
)

class IfExpression(
	val expression: Expression,
	val controlStructureBody: ControlStructureBody?,
	val elseControlStructureBody: ControlStructureBody?
) : TreeNode(
	"ifExpression",
	"expression" to expression,
	"controlStructureBody" to controlStructureBody,
	"elseControlStructureBody" to elseControlStructureBody
)

class WhenSubject(
	val annotation: List<Annotation>,
	val variableDeclaration: VariableDeclaration?,
	val expression: Expression
) : TreeNode(
	"whenSubject",
	"annotation" to TreeList(annotation),
	"variableDeclaration" to variableDeclaration,
	"expression" to expression
)

class WhenExpression(
	val whenSubject: WhenSubject?,
	val whenEntry: List<WhenEntry>
) : TreeNode(
	"whenExpression",
	"whenSubject" to whenSubject,
	"whenEntry" to TreeList(whenEntry)
)

class WhenEntry(
	val whenCondition: List<WhenCondition>,
	val `else`: Boolean,
	val controlStructureBody: ControlStructureBody
) : TreeNode(
	"whenEntry",
	"whenCondition" to TreeList(whenCondition),
	"else" to TreeBoolean(`else`),
	"controlStructureBody" to controlStructureBody
)

class WhenCondition(
	val expression: Expression?,
	val rangeTest: RangeTest?,
	val typeTest: TypeTest?
) : TreeNode(
	"whenCondition",
	"expression" to expression,
	"rangeTest" to rangeTest,
	"typeTest" to typeTest
)

class RangeTest(
	val inOperator: InOperator,
	val expression: Expression
) : TreeNode(
	"rangeTest",
	"inOperator" to inOperator,
	"expression" to expression
)

class TypeTest(
	val isOperator: IsOperator,
	val type: Type
) : TreeNode(
	"rangeTest",
	"isOperator" to isOperator,
	"type" to type
)

class TryExpression(
	val block: Block,
	val catchBlock: List<CatchBlock>,
	val finallyBlock: FinallyBlock?
) : TreeNode(
	"tryExpression",
	"block" to block,
	"catchBlock" to TreeList(catchBlock),
	"finallyBlock" to finallyBlock
)

class CatchBlock(
	val annotation: List<Annotation>,
	val simpleIdentifier: SimpleIdentifier,
	val type: Type,
	val block: Block
) : TreeNode(
	"catchBlock",
	"annotation" to TreeList(annotation),
	"simpleIdentifier" to simpleIdentifier,
	"type" to type,
	"block" to block
)

class FinallyBlock(
	val block: Block
) : TreeNode(
	"finallyBlock",
	"block" to block
)

class JumpExpression(
	val `throw`: Boolean,
	val `return`: Boolean,
	val `continue`: Boolean,
	val `break`: Boolean,
	val expression: Expression?,
	val at: String?
) : TreeNode(
	"jumpExpression",
	"return" to TreeBoolean(`return`),
	"continue" to TreeBoolean(`continue`),
	"break" to TreeBoolean(`break`),
	"expression" to expression,
	"at" to at?.let { TreeString(it) }
)

class CallableReference(
	val receiverType: ReceiverType?,
	val simpleIdentifier: SimpleIdentifier?
) : TreeNode(
	"callableReference",
	"receiverType" to receiverType,
	"simpleIdentifier" to simpleIdentifier
)

class AssignmentAndOperator(
	val add: Boolean,
	val sub: Boolean,
	val mul: Boolean,
	val div: Boolean,
	val mod: Boolean
) : TreeNode(
	"assignmentAndOperator",
	"add" to TreeBoolean(add),
	"sub" to TreeBoolean(sub),
	"mul" to TreeBoolean(mul),
	"div" to TreeBoolean(div),
	"mod" to TreeBoolean(mod),
)

class EqualityOperator(
	val hardEq: Boolean,
	val excl: Boolean
) : TreeNode(
	"equalityOperator",
	"hardEq" to TreeBoolean(hardEq),
	"excl" to TreeBoolean(excl)
)

class ComparisonOperator(
	val eq: Boolean,
	val greater: Boolean,
) : TreeNode(
	"comparisonOperator",
	"eq" to TreeBoolean(eq),
	"greater" to TreeBoolean(greater)
)

class InOperator(
	val not: Boolean
) : TreeNode(
	"inOperator",
	"not" to TreeBoolean(not)
)

class IsOperator(
	val not: Boolean
) : TreeNode(
	"isOperator",
	"not" to TreeBoolean(not)
)

class AdditiveOperator(
	val add: Boolean
) : TreeNode(
	"additiveOperator",
	"add" to TreeBoolean(add)
)

class MultiplicativeOperator(
	val mul: Boolean,
	val div: Boolean,
	val mod: Boolean
) : TreeNode(
	"multiplicativeOperator",
	"mul" to TreeBoolean(mul),
	"div" to TreeBoolean(div),
	"mod" to TreeBoolean(mod)
)

class AsOperator(
	val safe: Boolean
) : TreeNode(
	"asOperator",
	"safe" to TreeBoolean(safe)
)

class PrefixUnaryOperator(
	val increment: Boolean,
	val decrement: Boolean,
	val sub: Boolean,
	val add: Boolean,
	val excl: Boolean
) : TreeNode(
	"prefixUnaryOperator",
	"increment" to TreeBoolean(increment),
	"decrement" to TreeBoolean(decrement),
	"sub" to TreeBoolean(sub),
	"add" to TreeBoolean(add),
	"excl" to TreeBoolean(excl)
)

class PostfixUnaryOperator(
	val increment: Boolean,
	val decrement: Boolean,
	val excl: Boolean
) : TreeNode(
	"postfixUnaryOperator",
	"increment" to TreeBoolean(increment),
	"decrement" to TreeBoolean(decrement),
	"excl" to TreeBoolean(excl)
)

class MemberAccessOperator(
	val safe: Boolean,
	val reference: Boolean
) : TreeNode(
	"memberAccessOperator",
	"safe" to TreeBoolean(safe),
	"reference" to TreeBoolean(reference)
)