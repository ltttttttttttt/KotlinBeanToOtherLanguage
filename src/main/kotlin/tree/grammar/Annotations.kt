package tree.grammar

class Annotation(
	val singleAnnotation: SingleAnnotation?,
	val multiAnnotation: MultiAnnotation?
) : TreeNode(
	"annotation",
	"singleAnnotation" to singleAnnotation,
	"multiAnnotation" to multiAnnotation
)

class SingleAnnotation(
	val annotationUseSiteTarget: AnnotationUseSiteTarget?,
	val unescapedAnnotation: UnescapedAnnotation
) : TreeNode(
	"singleAnnotation",
	"annotationUseSiteTarget" to annotationUseSiteTarget,
	"unescapedAnnotation" to unescapedAnnotation
)

class MultiAnnotation(
	val annotationUseSiteTarget: AnnotationUseSiteTarget?,
	val unescapedAnnotation: List<UnescapedAnnotation>
) : TreeNode(
	"multiAnnotation",
	"annotationUseSiteTarget" to annotationUseSiteTarget,
	"unescapedAnnotation" to TreeList(unescapedAnnotation)
)

class AnnotationUseSiteTarget(
	val field: Boolean,
	val property: Boolean,
	val get: Boolean,
	val set: Boolean,
	val receiver: Boolean,
	val param: Boolean,
	val setParam: Boolean,
	val delegate: Boolean
) : TreeNode(
	"annotationUseSiteTarget",
	"field" to TreeBoolean(field),
	"property" to TreeBoolean(property),
	"get" to TreeBoolean(get),
	"set" to TreeBoolean(set),
	"receiver" to TreeBoolean(receiver),
	"param" to TreeBoolean(param),
	"setParam" to TreeBoolean(setParam),
	"delegate" to TreeBoolean(delegate)
)

class UnescapedAnnotation(
	val constructorInvocation: ConstructorInvocation?,
	val userType: UserType?
) : TreeNode(
	"unescapedAnnotation",
	"constructorInvocation" to constructorInvocation,
	"userType" to userType
)