package tree.grammar

class ClassDeclaration(
	val modifiers: Modifiers?,
	val `class`: Boolean,
	val `fun`: Boolean,
	val `interface`: Boolean,
	val simpleIdentifier: SimpleIdentifier,
	val typeParameters: TypeParameters?,
	val primaryConstructor: PrimaryConstructor?,
	val delegationSpecifiers: DelegationSpecifiers?,
	val typeConstraints: TypeConstraints?,
	val classBody: ClassBody?,
	val enumClassBody: EnumClassBody?
) : TreeNode(
	"classDeclaration",
	"modifiers" to modifiers,
	"class" to TreeBoolean(`class`),
	"fun" to TreeBoolean(`fun`),
	"interface" to TreeBoolean(`interface`),
	"simpleIdentifier" to simpleIdentifier,
	"typeParameters" to typeParameters,
	"primaryConstructor" to primaryConstructor,
	"delegationSpecifiers" to delegationSpecifiers,
	"typeConstraints" to typeConstraints,
	"classBody" to classBody,
	"enumClassBody" to enumClassBody
)

class PrimaryConstructor(
	val modifiers: Modifiers?,
	val classParameters: ClassParameters
) : TreeNode(
	"primaryConstructor",
	"modifiers" to modifiers,
	"classParameters" to classParameters
)

class ClassBody(
	val classMemberDeclarations: ClassMemberDeclarations
) : TreeNode(
	"classBody",
	"classMemberDeclarations" to classMemberDeclarations
)

class ClassParameters(
	val classParameter: List<ClassParameter>
) : TreeNode(
	"classParameters",
	"classParameter" to TreeList(classParameter)
)

class ClassParameter(
	val modifiers: Modifiers?,
	val mutable: Boolean,
	val simpleIdentifier: SimpleIdentifier,
	val type: Type,
	val expression: Expression?
) : TreeNode(
	"classParameter",
	"modifiers" to modifiers,
	"mutable" to TreeBoolean(mutable),
	"simpleIdentifier" to simpleIdentifier,
	"type" to type,
	"expression" to expression
)

class DelegationSpecifiers(
	val annotatedDelegationSpecifier: List<AnnotatedDelegationSpecifier>
) : TreeNode(
	"delegationSpecifiers",
	"annotatedDelegationSpecifier" to TreeList(annotatedDelegationSpecifier)
)

class DelegationSpecifier(
	val constructorInvocation: ConstructorInvocation?,
	val explicitDelegation: ExplicitDelegation?,
	val userType: UserType?,
	val suspend: Boolean?,
	val functionType: FunctionType?,
) : TreeNode(
	"delegationSpecifier",
	"constructorInvocation" to constructorInvocation,
	"explicitDelegation" to explicitDelegation,
	"userType" to userType,
	"suspend" to suspend?.let { TreeBoolean(it) },
	"functionType" to functionType
)

class ConstructorInvocation(
	val userType: UserType,
	val valueArguments: ValueArguments
) : TreeNode(
	"constructorInvocation",
	"userType" to userType,
	"valueArguments" to valueArguments
)

class AnnotatedDelegationSpecifier(
	val annotation: List<Annotation>,
	val delegationSpecifier: DelegationSpecifier
) : TreeNode(
	"annotatedDelegationSpecifier",
	"annotation" to TreeList(annotation),
	"delegationSpecifier" to delegationSpecifier
)

class ExplicitDelegation(
	val userType: UserType?,
	val functionType: FunctionType?,
	val expression: Expression
) : TreeNode(
	"explicitDelegation",
	"userType" to userType,
	"functionType" to functionType,
	"expression" to expression
)

class TypeParameters(
	val typeParameter: List<TypeParameter>
) : TreeNode(
	"typeParameters",
	"typeParameter" to TreeList(typeParameter)
)

class TypeParameter(
	val typeParameterModifiers: TypeParameterModifiers?,
	val simpleIdentifier: SimpleIdentifier,
	val type: Type?
) : TreeNode(
	"typeParameter",
	"typeParameterModifiers" to typeParameterModifiers,
	"simpleIdentifier" to simpleIdentifier,
	"type" to type
)

class TypeConstraints(
	val typeConstraint: List<TypeConstraint>
) : TreeNode(
	"typeConstraints",
	"typeConstraint" to TreeList(typeConstraint)
)

class TypeConstraint(
	val annotation: List<Annotation>,
	val simpleIdentifier: SimpleIdentifier,
	val type: Type
) : TreeNode(
	"typeConstraint",
	"annotation" to TreeList(annotation),
	"simpleIdentifier" to simpleIdentifier,
	"type" to type
)