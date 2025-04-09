package tree.grammar

class ClassMemberDeclarations(
	val classMemberDeclaration: List<ClassMemberDeclaration>
) : TreeNode(
	"classMemberDeclarations",
	"classMemberDeclaration" to TreeList(classMemberDeclaration)
)

class ClassMemberDeclaration(
	val declaration: Declaration?,
	val companionObject: CompanionObject?,
	val anonymousInitializer: AnonymousInitializer?,
	val secondaryConstructor: SecondaryConstructor?
) : TreeNode(
	"classMemberDeclaration",
	"declaration" to declaration,
	"companionObject" to companionObject,
	"anonymousInitializer" to anonymousInitializer,
	"secondaryConstructor" to secondaryConstructor
)

class AnonymousInitializer(
	val block: Block
) : TreeNode(
	"anonymousInitializer",
	"block" to block
)

class CompanionObject(
	val modifiers: Modifiers?,
	val data: Boolean,
	val simpleIdentifier: SimpleIdentifier?,
	val delegationSpecifiers: DelegationSpecifiers?,
	val classBody: ClassBody?
) : TreeNode(
	"companionObject",
	"modifiers" to modifiers,
	"data" to TreeBoolean(data),
	"simpleIdentifier" to simpleIdentifier,
	"delegationSpecifiers" to delegationSpecifiers,
	"classBody" to classBody
)

class FunctionValueParameters(
	val functionValueParameter: List<FunctionValueParameter>
) : TreeNode(
	"functionValueParameters",
	"functionValueParameter" to TreeList(functionValueParameter)
)

class FunctionValueParameter(
	val parameterModifiers: ParameterModifiers?,
	val parameter: Parameter,
	val expression: Expression?
) : TreeNode(
	"functionValueParameter",
	"parameterModifiers" to parameterModifiers,
	"parameter" to parameter,
	"expression" to expression
)

class FunctionDeclaration(
	val modifiers: Modifiers?,
	val typeParameters: TypeParameters?,
	val receiverType: ReceiverType?,
	val simpleIdentifier: SimpleIdentifier,
	val functionValueParameters: FunctionValueParameters,
	val type: Type?,
	val typeConstraints: TypeConstraints?,
	val functionBody: FunctionBody?
) : TreeNode(
	"functionDeclaration",
	"modifiers" to modifiers,
	"typeParameters" to typeParameters,
	"receiverType" to receiverType,
	"simpleIdentifier" to simpleIdentifier,
	"functionValueParameters" to functionValueParameters,
	"type" to type,
	"typeConstraints" to typeConstraints,
	"functionBody" to functionBody
)

class FunctionBody(
	val block: Block?,
	val expression: Expression?
) : TreeNode(
	"functionBody",
	"block" to block,
	"expression" to expression
)

class VariableDeclaration(
	val annotation: List<Annotation>,
	val simpleIdentifier: SimpleIdentifier,
	val type: Type?
) : TreeNode(
	"variableDeclaration",
	"annotation" to TreeList(annotation),
	"simpleIdentifier" to simpleIdentifier,
	"type" to type
)

class MultiVariableDeclaration(
	val variableDeclaration: List<VariableDeclaration>
) : TreeNode(
	"multiVariableDeclaration",
	"variableDeclaration" to TreeList(variableDeclaration)
)

class PropertyDeclaration(
	val modifiers: Modifiers?,
	val mutable: Boolean,
	val typeParameters: TypeParameters?,
	val receiverType: ReceiverType?,
	val multiVariableDeclaration: MultiVariableDeclaration?,
	val variableDeclaration: VariableDeclaration?,
	val typeConstraints: TypeConstraints?,
	val expression: Expression?,
	val propertyDelegate: PropertyDelegate?,
	val getter: Getter?,
	val setter: Setter?
) : TreeNode(
	"propertyDeclaration",
	"modifiers" to modifiers,
	"mutable" to TreeBoolean(mutable),
	"typeParameters" to typeParameters,
	"receiverType" to receiverType,
	"multiVariableDeclaration" to multiVariableDeclaration,
	"variableDeclaration" to variableDeclaration,
	"typeConstraints" to typeConstraints,
	"expression" to expression,
	"propertyDelegate" to propertyDelegate,
	"getter" to getter,
	"setter" to setter
)

class PropertyDelegate(
	val expression: Expression
) : TreeNode(
	"propertyDelegate",
	"expression" to expression
)

class Getter(
	val modifiers: Modifiers?,
	val type: Type?,
	val functionBody: FunctionBody?
) : TreeNode(
	"getter",
	"modifiers" to modifiers,
	"type" to type,
	"functionBody" to functionBody
)

class Setter(
	val modifiers: Modifiers?,
	val functionValueParameterWithOptionalType: FunctionValueParameterWithOptionalType?,
	val type: Type?,
	val functionBody: FunctionBody?
) : TreeNode(
	"setter",
	"modifiers" to modifiers,
	"functionValueParameterWithOptionalType" to functionValueParameterWithOptionalType,
	"type" to type,
	"functionBody" to functionBody
)

class ParametersWithOptionalType(
	val functionValueParameterWithOptionalType: List<FunctionValueParameterWithOptionalType>
) : TreeNode(
	"parametersWithOptionalType",
	"functionValueParameterWithOptionalType" to TreeList(functionValueParameterWithOptionalType)
)

class FunctionValueParameterWithOptionalType(
	val parameterModifiers: ParameterModifiers?,
	val parameterWithOptionalType: ParameterWithOptionalType,
	val expression: Expression?
) : TreeNode(
	"functionValueParameterWithOptionalType",
	"parameterModifiers" to parameterModifiers,
	"parameterWithOptionalType" to parameterWithOptionalType,
	"expression" to expression
)

class ParameterWithOptionalType(
	val simpleIdentifier: SimpleIdentifier,
	val type: Type?
) : TreeNode(
	"parameterWithOptionalType",
	"simpleIdentifier" to simpleIdentifier,
	"type" to type
)

class Parameter(
	val simpleIdentifier: SimpleIdentifier,
	val type: Type
) : TreeNode(
	"parameterWithOptionalType",
	"simpleIdentifier" to simpleIdentifier,
	"type" to type
)

class ObjectDeclaration(
	val modifiers: Modifiers?,
	val simpleIdentifier: SimpleIdentifier,
	val delegationSpecifiers: DelegationSpecifiers?,
	val classBody: ClassBody?
) : TreeNode(
	"objectDeclaration",
	"modifiers" to modifiers,
	"simpleIdentifier" to simpleIdentifier,
	"delegationSpecifiers" to delegationSpecifiers,
	"classBody" to classBody
)

class SecondaryConstructor(
	val modifiers: Modifiers?,
	val functionValueParameters: FunctionValueParameters,
	val constructorDelegationCall: ConstructorDelegationCall?,
	val block: Block?
) : TreeNode(
	"secondaryConstructor",
	"modifiers" to modifiers,
	"functionValueParameters" to functionValueParameters,
	"constructorDelegationCall" to constructorDelegationCall,
	"block" to block
)

class ConstructorDelegationCall(
	val `super`: Boolean,
	val valueArguments: ValueArguments
) : TreeNode(
	"constructorDelegationCall",
	"super" to TreeBoolean(`super`),
	"valueArguments" to valueArguments
)