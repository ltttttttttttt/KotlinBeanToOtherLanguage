package tree.grammar

class Type(
	val typeModifiers: TypeModifiers?,
	val functionType: FunctionType?,
	val parenthesizedType: ParenthesizedType?,
	val nullableType: NullableType?,
	val typeReference: TypeReference?,
	val definitelyNonNullableType: DefinitelyNonNullableType?
) : TreeNode(
	"type",
	"typeModifiers" to typeModifiers,
	"functionType" to functionType,
	"parenthesizedType" to parenthesizedType,
	"nullableType" to nullableType,
	"typeReference" to typeReference,
	"definitelyNonNullableType" to definitelyNonNullableType
)

class TypeReference(
	val userType: UserType?,
	val dynamic: Boolean
) : TreeNode(
	"typeReference",
	"userType" to userType,
	"dynamic" to TreeBoolean(dynamic)
)

class NullableType(
	val typeReference: TypeReference?,
	val parenthesizedType: ParenthesizedType?
) : TreeNode(
	"nullableType",
	"typeReference" to typeReference,
	"parenthesizedType" to parenthesizedType
)

class UserType(
	val simpleUserType: List<SimpleUserType>
) : TreeNode(
	"userType",
	"simpleUserType" to TreeList(simpleUserType)
)

class SimpleUserType(
	val simpleIdentifier: SimpleIdentifier,
	val typeArguments: TypeArguments?
) : TreeNode(
	"simpleUserType",
	"simpleIdentifier" to simpleIdentifier,
	"typeArguments" to typeArguments
)

class TypeProjection(
	val typeProjectionModifiers: TypeProjectionModifiers?,
	val type: Type?,
	val multi: Boolean
) : TreeNode(
	"typeProjection",
	"typeProjectionModifiers" to typeProjectionModifiers,
	"type" to type,
	"multi" to TreeBoolean(multi)
)

class TypeProjectionModifiers(
	val typeProjectionModifier: List<TypeProjectionModifier>
) : TreeNode(
	"typeProjectionModifiers",
	"typeProjectionModifier" to TreeList(typeProjectionModifier)
)

class TypeProjectionModifier(
	val varianceModifier: VarianceModifier?,
	val annotation: Annotation?
) : TreeNode(
	"typeProjectionModifier",
	"varianceModifier" to varianceModifier,
	"annotation" to annotation
)

class FunctionType(
	val receiverType: ReceiverType?,
	val functionTypeParameters: FunctionTypeParameters,
	val type: Type
) : TreeNode(
	"functionType",
	"receiverType" to receiverType,
	"functionTypeParameters" to functionTypeParameters,
	"type" to type
)

class FunctionTypeParameters(
	val parameter: List<Parameter>,
	val type: List<Type>
) : TreeNode(
	"functionTypeParameters",
	"parameter" to TreeList(parameter),
	"type" to TreeList(type)
)

class ParenthesizedType(
	val type: Type
) : TreeNode(
	"parenthesizedType",
	"type" to type
)

class ReceiverType(
	val typeModifiers: TypeModifiers?,
	val parenthesizedType: ParenthesizedType?,
	val nullableType: NullableType?,
	val typeReference: TypeReference?
) : TreeNode(
	"receiverType",
	"typeModifiers" to typeModifiers,
	"parenthesizedType" to parenthesizedType,
	"nullableType" to nullableType,
	"typeReference" to typeReference
)

class ParenthesizedUserType(
	val userType: UserType?,
	val parenthesizedUserType: ParenthesizedUserType?
) : TreeNode(
	"parenthesizedUserType",
	"userType" to userType,
	"parenthesizedUserType" to parenthesizedUserType
)

class DefinitelyNonNullableType(
	val typeModifiersLeft: TypeModifiers?,
	val userTypeLeft: UserType?,
	val parenthesizedUserTypeLeft: ParenthesizedUserType?,
	val typeModifiersRight: TypeModifiers?,
	val userTypeRight: UserType?,
	val parenthesizedUserTypeRight: ParenthesizedUserType?
) : TreeNode(
	"definitelyNonNullableType",
	"typeModifiersLeft" to typeModifiersLeft,
	"userTypeLeft" to userTypeLeft,
	"parenthesizedUserTypeLeft" to parenthesizedUserTypeLeft,
	"typeModifiersRight" to typeModifiersLeft,
	"userTypeRight" to userTypeLeft,
	"parenthesizedUserTypeRight" to parenthesizedUserTypeLeft
)