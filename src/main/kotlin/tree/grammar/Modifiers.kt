package tree.grammar

class Modifiers(
	val sub: List<Sub>
) : TreeNode(
	"modifiers",
	"sub" to TreeList(sub)
) {
	class Sub(
		val annotation: Annotation?,
		val modifier: Modifier?
	) : TreeNode(
		"sub",
		"annotation" to annotation,
		"modifier" to modifier
	)
}

class ParameterModifiers(
	val sub: List<Sub>
) : TreeNode(
	"modifiers",
	"sub" to TreeList(sub)
) {
	class Sub(
		val annotation: Annotation?,
		val parameterModifier: ParameterModifier?
	) : TreeNode(
		"sub",
		"annotation" to annotation,
		"parameterModifier" to parameterModifier
	)
}

class Modifier(
	val classModifier: ClassModifier?,
	val memberModifier: MemberModifier?,
	val visibilityModifier: VisibilityModifier?,
	val functionModifier: FunctionModifier?,
	val propertyModifier: PropertyModifier?,
	val inheritanceModifier: InheritanceModifier?,
	val parameterModifier: ParameterModifier?,
	val platformModifier: PlatformModifier?
) : TreeNode(
	"modifier",
	"classModifier" to classModifier,
	"memberModifier" to memberModifier,
	"visibilityModifier" to visibilityModifier,
	"functionModifier" to functionModifier,
	"propertyModifier" to propertyModifier,
	"inheritanceModifier" to inheritanceModifier,
	"parameterModifier" to parameterModifier,
	"platformModifier" to platformModifier
)

class TypeModifiers(
	val typeModifier: List<TypeModifier>
) : TreeNode(
	"typeModifiers",
	"typeModifiers" to TreeList(typeModifier)
)

class TypeModifier(
	val annotation: Annotation?,
	val suspend: Boolean
) : TreeNode(
	"typeModifier",
	"annotation" to annotation,
	"suspend" to TreeBoolean(suspend)
)

class ClassModifier(
	val enum: Boolean,
	val sealed: Boolean,
	val annotation: Boolean,
	val data: Boolean,
	val inner: Boolean,
	val value: Boolean
) : TreeNode(
	"classModifier",
	"enum" to TreeBoolean(enum),
	"sealed" to TreeBoolean(sealed),
	"annotation" to TreeBoolean(annotation),
	"data" to TreeBoolean(data),
	"inner" to TreeBoolean(inner),
	"value" to TreeBoolean(value)
)

class MemberModifier(
	val override: Boolean,
	val lateInit: Boolean
) : TreeNode(
	"memberModifier",
	"override" to TreeBoolean(override),
	"lateInit" to TreeBoolean(lateInit)
)

class VisibilityModifier(
	val public: Boolean,
	val private: Boolean,
	val internal: Boolean,
	val protected: Boolean
) : TreeNode(
	"visibilityModifier",
	"public" to TreeBoolean(public),
	"private" to TreeBoolean(private),
	"internal" to TreeBoolean(internal),
	"protected" to TreeBoolean(protected)
)

class VarianceModifier(
	val out: Boolean
) : TreeNode(
	"varianceModifier",
	"out" to TreeBoolean(out)
)

class TypeParameterModifiers(
	val typeParameterModifier: List<TypeParameterModifier>
) : TreeNode(
	"typeParameterModifiers",
	"typeParameterModifier" to TreeList(typeParameterModifier)
)

class TypeParameterModifier(
	val reificationModifier: ReificationModifier?,
	val varianceModifier: VarianceModifier?,
	val annotation: Annotation?
) : TreeNode(
	"typeParameterModifier",
	"reificationModifier" to reificationModifier,
	"varianceModifier" to varianceModifier,
	"annotation" to annotation
)

class FunctionModifier(
	val tailrec: Boolean,
	val operator: Boolean,
	val infix: Boolean,
	val inline: Boolean,
	val external: Boolean,
	val suspend: Boolean
) : TreeNode(
	"functionModifier",
	"tailrec" to TreeBoolean(tailrec),
	"operator" to TreeBoolean(operator),
	"infix" to TreeBoolean(infix),
	"inline" to TreeBoolean(inline),
	"external" to TreeBoolean(external),
	"suspend" to TreeBoolean(suspend)
)

object PropertyModifier : TreeNode("propertyModifier")

class InheritanceModifier(
	val abstract: Boolean,
	val final: Boolean,
	val open: Boolean
) : TreeNode(
	"inheritanceModifier",
	"abstract" to TreeBoolean(abstract),
	"final" to TreeBoolean(final),
	"open" to TreeBoolean(open)
)

class ParameterModifier(
	val vararg: Boolean,
	val noInline: Boolean,
	val crossInline: Boolean
) : TreeNode(
	"parameterModifier",
	"vararg" to TreeBoolean(vararg),
	"noInline" to TreeBoolean(noInline),
	"crossInline" to TreeBoolean(crossInline)
)

object ReificationModifier : TreeNode("reificationModifier")

class PlatformModifier(
	val actual: Boolean
) : TreeNode(
	"platformModifier",
	"actual" to TreeBoolean(actual)
)