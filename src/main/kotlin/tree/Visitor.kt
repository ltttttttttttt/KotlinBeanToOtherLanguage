package tree

import com.strumenta.antlrkotlin.parsers.generated.KotlinLexer
import com.strumenta.antlrkotlin.parsers.generated.KotlinParser
import org.antlr.v4.kotlinruntime.tree.TerminalNode
import tree.grammar.*
import tree.syntax.KotlinScript

fun KotlinParser.KotlinFileContext.visit(name: String): tree.syntax.KotlinFile {
	return tree.syntax.KotlinFile(
		fileName = name,
		fileAnnotations = fileAnnotation().map { it.unescapedAnnotation() }.flatten().map { it.visit() },
		`package` = packageHeader().identifier()?.text,
		imports = importList().importHeader().map { it.visit() },
		topLevelObjects = topLevelObject().map { it.visit() }
	)
}

fun KotlinParser.ScriptContext.visit(name: String): KotlinScript {
	return KotlinScript(
		fileName = name,
		fileAnnotations = fileAnnotation().map { it.unescapedAnnotation() }.flatten().map { it.visit() },
		`package` = packageHeader().identifier()?.text,
		imports = importList().importHeader().map { it.visit() },
		statements = statement().map { it.visit() }
	)
}

fun KotlinParser.FileAnnotationContext.visit(): FileAnnotation {
	return FileAnnotation(
		unescapedAnnotation = unescapedAnnotation().map { it.visit() }
	)
}

fun KotlinParser.ImportHeaderContext.visit(): ImportHeader {
	return ImportHeader(
		identifier = identifier().visit(),
		multi = MULT() != null,
		importAlias = importAlias()?.visit()
	)
}

fun KotlinParser.ImportAliasContext.visit(): ImportAlias {
	return ImportAlias(
		simpleIdentifier = simpleIdentifier().visit()
	)
}

fun KotlinParser.TopLevelObjectContext.visit(): TopLevelObject {
	return TopLevelObject(
		declaration = declaration().visit()
	)
}

fun KotlinParser.TypeAliasContext.visit(): TypeAlias {
	return TypeAlias(
		modifiers = modifiers()?.visit(),
		simpleIdentifier = simpleIdentifier().visit(),
		typeParameters = typeParameters()?.visit(),
		type = type().visit()
	)
}

fun KotlinParser.DeclarationContext.visit(): Declaration {
	return Declaration(
		classDeclaration = classDeclaration()?.visit(),
		objectDeclaration = objectDeclaration()?.visit(),
		functionDeclaration = functionDeclaration()?.visit(),
		propertyDeclaration = propertyDeclaration()?.visit(),
		typeAlias = typeAlias()?.visit()
	)
}

fun KotlinParser.ClassDeclarationContext.visit(): ClassDeclaration {
	return ClassDeclaration(
		modifiers = modifiers()?.visit(),
		`class` = CLASS() != null,
		`fun` = FUN() != null,
		`interface` = INTERFACE() != null,
		simpleIdentifier = simpleIdentifier().visit(),
		typeParameters = typeParameters()?.visit(),
		primaryConstructor = primaryConstructor()?.visit(),
		delegationSpecifiers = delegationSpecifiers()?.visit(),
		typeConstraints = typeConstraints()?.visit(),
		classBody = classBody()?.visit(),
		enumClassBody = enumClassBody()?.visit()
	)
}

fun KotlinParser.PrimaryConstructorContext.visit(): PrimaryConstructor {
	return PrimaryConstructor(
		modifiers = modifiers()?.visit(),
		classParameters = classParameters().visit()
	)
}

fun KotlinParser.ClassBodyContext.visit(): ClassBody {
	return ClassBody(
		classMemberDeclarations = classMemberDeclarations().visit()
	)
}

fun KotlinParser.ClassParametersContext.visit(): ClassParameters {
	return ClassParameters(
		classParameter = classParameter().map { it.visit() }
	)
}

fun KotlinParser.ClassParameterContext.visit(): ClassParameter {
	return ClassParameter(
		modifiers = modifiers()?.visit(),
		mutable = VAR() != null,
		simpleIdentifier = simpleIdentifier().visit(),
		type = type().visit(),
		expression = expression()?.visit()
	)
}

fun KotlinParser.DelegationSpecifiersContext.visit(): DelegationSpecifiers {
	return DelegationSpecifiers(
		annotatedDelegationSpecifier = annotatedDelegationSpecifier().map { it.visit() }
	)
}

fun KotlinParser.DelegationSpecifierContext.visit(): DelegationSpecifier {
	return DelegationSpecifier(
		constructorInvocation = constructorInvocation()?.visit(),
		explicitDelegation = explicitDelegation()?.visit(),
		userType = userType()?.visit(),
		suspend = SUSPEND() != null,
		functionType = functionType()?.visit()
	)
}

fun KotlinParser.ConstructorInvocationContext.visit(): ConstructorInvocation {
	return ConstructorInvocation(
		userType = userType().visit(),
		valueArguments = valueArguments().visit()
	)
}

fun KotlinParser.AnnotatedDelegationSpecifierContext.visit(): AnnotatedDelegationSpecifier {
	return AnnotatedDelegationSpecifier(
		annotation = annotation().map { it.visit() },
		delegationSpecifier = delegationSpecifier().visit()
	)
}

fun KotlinParser.ExplicitDelegationContext.visit(): ExplicitDelegation {
	return ExplicitDelegation(
		userType = userType()?.visit(),
		functionType = functionType()?.visit(),
		expression = expression().visit()
	)
}

fun KotlinParser.TypeParametersContext.visit(): TypeParameters {
	return TypeParameters(
		typeParameter = typeParameter().map { it.visit() }
	)
}

fun KotlinParser.TypeParameterContext.visit(): TypeParameter {
	return TypeParameter(
		typeParameterModifiers = typeParameterModifiers()?.visit(),
		simpleIdentifier = simpleIdentifier().visit(),
		type = type()?.visit()
	)
}

fun KotlinParser.TypeConstraintsContext.visit(): TypeConstraints {
	return TypeConstraints(
		typeConstraint = typeConstraint().map { it.visit() }
	)
}

fun KotlinParser.TypeConstraintContext.visit(): TypeConstraint {
	return TypeConstraint(
		annotation = annotation().map { it.visit() },
		simpleIdentifier = simpleIdentifier().visit(),
		type = type().visit()
	)
}

fun KotlinParser.ClassMemberDeclarationsContext.visit(): ClassMemberDeclarations {
	return ClassMemberDeclarations(
		classMemberDeclaration = classMemberDeclaration().map { it.visit() }
	)
}

fun KotlinParser.ClassMemberDeclarationContext.visit(): ClassMemberDeclaration {
	return ClassMemberDeclaration(
		declaration = declaration()?.visit(),
		companionObject = companionObject()?.visit(),
		anonymousInitializer = anonymousInitializer()?.visit(),
		secondaryConstructor = secondaryConstructor()?.visit()
	)
}

fun KotlinParser.AnonymousInitializerContext.visit(): AnonymousInitializer {
	return AnonymousInitializer(
		block = block().visit()
	)
}

fun KotlinParser.CompanionObjectContext.visit(): CompanionObject {
	return CompanionObject(
		modifiers = modifiers()?.visit(),
		data = DATA() != null,
		simpleIdentifier = simpleIdentifier()?.visit(),
		delegationSpecifiers = delegationSpecifiers()?.visit(),
		classBody = classBody()?.visit()
	)
}

fun KotlinParser.FunctionValueParametersContext.visit(): FunctionValueParameters {
	return FunctionValueParameters(
		functionValueParameter = functionValueParameter().map { it.visit() }
	)
}

fun KotlinParser.FunctionValueParameterContext.visit(): FunctionValueParameter {
	return FunctionValueParameter(
		parameterModifiers = parameterModifiers()?.visit(),
		parameter = parameter().visit(),
		expression = expression()?.visit()
	)
}

fun KotlinParser.FunctionDeclarationContext.visit(): FunctionDeclaration {
	return FunctionDeclaration(
		modifiers = modifiers()?.visit(),
		typeParameters = typeParameters()?.visit(),
		receiverType = receiverType()?.visit(),
		simpleIdentifier = simpleIdentifier().visit(),
		functionValueParameters = functionValueParameters().visit(),
		type = type()?.visit(),
		typeConstraints = typeConstraints()?.visit(),
		functionBody = functionBody()?.visit()
	)
}

fun KotlinParser.FunctionBodyContext.visit(): FunctionBody {
	return FunctionBody(
		block = block()?.visit(),
		expression = expression()?.visit()
	)
}

fun KotlinParser.VariableDeclarationContext.visit(): VariableDeclaration {
	return VariableDeclaration(
		annotation = annotation().map { it.visit() },
		simpleIdentifier = simpleIdentifier().visit(),
		type = type()?.visit()
	)
}

fun KotlinParser.MultiVariableDeclarationContext.visit(): MultiVariableDeclaration {
	return MultiVariableDeclaration(
		variableDeclaration = variableDeclaration().map { it.visit() }
	)
}

fun KotlinParser.PropertyDeclarationContext.visit(): PropertyDeclaration {
	return PropertyDeclaration(
		modifiers = modifiers()?.visit(),
		mutable = VAR() != null,
		typeParameters = typeParameters()?.visit(),
		receiverType = receiverType()?.visit(),
		multiVariableDeclaration = multiVariableDeclaration()?.visit(),
		variableDeclaration = variableDeclaration()?.visit(),
		typeConstraints = typeConstraints()?.visit(),
		expression = expression()?.visit(),
		propertyDelegate = propertyDelegate()?.visit(),
		getter = getter()?.visit(),
		setter = setter()?.visit()
	)
}

fun KotlinParser.PropertyDelegateContext.visit(): PropertyDelegate {
	return PropertyDelegate(
		expression = expression().visit()
	)
}

fun KotlinParser.GetterContext.visit(): Getter {
	return Getter(
		modifiers = modifiers()?.visit(),
		type = type()?.visit(),
		functionBody = functionBody()?.visit()
	)
}

fun KotlinParser.SetterContext.visit(): Setter {
	return Setter(
		modifiers = modifiers()?.visit(),
		functionValueParameterWithOptionalType = functionValueParameterWithOptionalType()?.visit(),
		type = type()?.visit(),
		functionBody = functionBody()?.visit()
	)
}

fun KotlinParser.ParametersWithOptionalTypeContext.visit(): ParametersWithOptionalType {
	return ParametersWithOptionalType(
		functionValueParameterWithOptionalType = functionValueParameterWithOptionalType().map { it.visit() }
	)
}

fun KotlinParser.FunctionValueParameterWithOptionalTypeContext.visit(): FunctionValueParameterWithOptionalType {
	return FunctionValueParameterWithOptionalType(
		parameterModifiers = parameterModifiers()?.visit(),
		parameterWithOptionalType = parameterWithOptionalType().visit(),
		expression = expression()?.visit()
	)
}

fun KotlinParser.ParameterWithOptionalTypeContext.visit(): ParameterWithOptionalType {
	return ParameterWithOptionalType(
		simpleIdentifier = simpleIdentifier().visit(),
		type = type()?.visit()
	)
}

fun KotlinParser.ParameterContext.visit(): Parameter {
	return Parameter(
		simpleIdentifier = simpleIdentifier().visit(),
		type = type().visit()
	)
}

fun KotlinParser.ObjectDeclarationContext.visit(): ObjectDeclaration {
	return ObjectDeclaration(
		modifiers = modifiers()?.visit(),
		simpleIdentifier = simpleIdentifier().visit(),
		delegationSpecifiers = delegationSpecifiers()?.visit(),
		classBody = classBody()?.visit()
	)
}

fun KotlinParser.SecondaryConstructorContext.visit(): SecondaryConstructor {
	return SecondaryConstructor(
		modifiers = modifiers()?.visit(),
		functionValueParameters = functionValueParameters().visit(),
		constructorDelegationCall = constructorDelegationCall()?.visit(),
		block = block()?.visit()
	)
}

fun KotlinParser.ConstructorDelegationCallContext.visit(): ConstructorDelegationCall {
	return ConstructorDelegationCall(
		`super` = SUPER() != null,
		valueArguments = valueArguments().visit()
	)
}

fun KotlinParser.EnumClassBodyContext.visit(): EnumClassBody {
	return EnumClassBody(
		enumEntries = enumEntries()?.visit(),
		classMemberDeclarations = classMemberDeclarations()?.visit()
	)
}

fun KotlinParser.EnumEntriesContext.visit(): EnumEntries {
	return EnumEntries(
		enumEntry = enumEntry().map { it.visit() }
	)
}

fun KotlinParser.EnumEntryContext.visit(): EnumEntry {
	return EnumEntry(
		modifiers = modifiers()?.visit(),
		simpleIdentifier = simpleIdentifier().visit(),
		valueArguments = valueArguments()?.visit(),
		classBody = classBody()?.visit()
	)
}

fun KotlinParser.TypeContext.visit(): Type {
	return Type(
		typeModifiers = typeModifiers()?.visit(),
		functionType = functionType()?.visit(),
		parenthesizedType = parenthesizedType()?.visit(),
		nullableType = nullableType()?.visit(),
		typeReference = typeReference()?.visit(),
		definitelyNonNullableType = definitelyNonNullableType()?.visit()
	)
}

fun KotlinParser.TypeReferenceContext.visit(): TypeReference {
	return TypeReference(
		userType = userType()?.visit(),
		dynamic = DYNAMIC() != null
	)
}

fun KotlinParser.NullableTypeContext.visit(): NullableType {
	return NullableType(
		typeReference = typeReference()?.visit(),
		parenthesizedType = parenthesizedType()?.visit()
	)
}

fun KotlinParser.UserTypeContext.visit(): UserType {
	return UserType(
		simpleUserType = simpleUserType().map { it.visit() }
	)
}

fun KotlinParser.SimpleUserTypeContext.visit(): SimpleUserType {
	return SimpleUserType(
		simpleIdentifier = simpleIdentifier().visit(),
		typeArguments = typeArguments()?.visit()
	)
}

fun KotlinParser.TypeProjectionContext.visit(): TypeProjection {
	return TypeProjection(
		typeProjectionModifiers = typeProjectionModifiers()?.visit(),
		type = type()?.visit(),
		multi = MULT() != null
	)
}

fun KotlinParser.TypeProjectionModifiersContext.visit(): TypeProjectionModifiers {
	return TypeProjectionModifiers(
		typeProjectionModifier = typeProjectionModifier().map { it.visit() }
	)
}

fun KotlinParser.TypeProjectionModifierContext.visit(): TypeProjectionModifier {
	return TypeProjectionModifier(
		varianceModifier = varianceModifier()?.visit(),
		annotation = annotation()?.visit()
	)
}

fun KotlinParser.FunctionTypeContext.visit(): FunctionType {
	return FunctionType(
		receiverType = receiverType()?.visit(),
		functionTypeParameters = functionTypeParameters().visit(),
		type = type().visit()
	)
}

fun KotlinParser.FunctionTypeParametersContext.visit(): FunctionTypeParameters {
	return FunctionTypeParameters(
		parameter = parameter().map { it.visit() },
		type = type().map { it.visit() }
	)
}

fun KotlinParser.ParenthesizedTypeContext.visit(): ParenthesizedType {
	return ParenthesizedType(
		type = type().visit()
	)
}

fun KotlinParser.ReceiverTypeContext.visit(): ReceiverType {
	return ReceiverType(
		typeModifiers = typeModifiers()?.visit(),
		parenthesizedType = parenthesizedType()?.visit(),
		nullableType = nullableType()?.visit(),
		typeReference = typeReference()?.visit()
	)
}

fun KotlinParser.ParenthesizedUserTypeContext.visit(): ParenthesizedUserType {
	return ParenthesizedUserType(
		userType = userType()?.visit(),
		parenthesizedUserType = parenthesizedUserType()?.visit()
	)
}

fun KotlinParser.DefinitelyNonNullableTypeContext.visit(): DefinitelyNonNullableType {
	val child = children!!.filter { !(it is TerminalNode && it.symbol.type == KotlinLexer.Tokens.NL) }
	val typeModifiersLeft = child[0].let {
		when (it) {
			is KotlinParser.TypeModifiersContext -> it.visit()
			else -> null
		}
	}
	val subLeft = when (typeModifiersLeft) {
		null -> child[0]
		else -> child[1]
	}
	val userTypeLeft = when (subLeft) {
		is KotlinParser.UserTypeContext -> subLeft.visit()
		else -> null
	}
	val parenthesizedUserTypeLeft = when (subLeft) {
		is KotlinParser.ParenthesizedUserTypeContext -> subLeft.visit()
		else -> null
	}

	val childRight = child.drop(
		child.indexOfFirst {
			it is TerminalNode && it.symbol.type == KotlinLexer.Tokens.AMP
		} + 1
	)
	val typeModifiersRight = childRight[0].let {
		when (it) {
			is KotlinParser.TypeModifiersContext -> it.visit()
			else -> null
		}
	}
	val subRight = when (typeModifiersRight) {
		null -> childRight[0]
		else -> childRight[1]
	}
	val userTypeRight = when (subRight) {
		is KotlinParser.UserTypeContext -> subRight.visit()
		else -> null
	}
	val parenthesizedUserTypeRight = when (subRight) {
		is KotlinParser.ParenthesizedUserTypeContext -> subRight.visit()
		else -> null
	}

	return DefinitelyNonNullableType(
		typeModifiersLeft = typeModifiersLeft,
		userTypeLeft = userTypeLeft,
		parenthesizedUserTypeLeft = parenthesizedUserTypeLeft,
		typeModifiersRight = typeModifiersRight,
		userTypeRight = userTypeRight,
		parenthesizedUserTypeRight = parenthesizedUserTypeRight
	)
}

fun KotlinParser.StatementsContext.visit(): Statements {
	return Statements(
		statement = statement().map { it.visit() }
	)
}

fun KotlinParser.StatementContext.visit(): Statement {
	return Statement(
		label = label().map { it.visit() },
		annotation = annotation().map { it.visit() },
		declaration = declaration()?.visit(),
		assignment = assignment()?.visit(),
		loopStatement = loopStatement()?.visit(),
		expression = expression()?.visit()
	)
}

fun KotlinParser.LabelContext.visit(): Label {
	return Label(
		simpleIdentifier = simpleIdentifier().visit()
	)
}

fun KotlinParser.ControlStructureBodyContext.visit(): ControlStructureBody {
	return ControlStructureBody(
		block = block()?.visit(),
		statement = statement()?.visit()
	)
}

fun KotlinParser.BlockContext.visit(): Block {
	return Block(
		statements = statements().visit()
	)
}

fun KotlinParser.LoopStatementContext.visit(): LoopStatement {
	return LoopStatement(
		forStatement = forStatement()?.visit(),
		whileStatement = whileStatement()?.visit(),
		doWhileStatement = doWhileStatement()?.visit()
	)
}

fun KotlinParser.ForStatementContext.visit(): ForStatement {
	return ForStatement(
		annotation = annotation().map { it.visit() },
		variableDeclaration = variableDeclaration()?.visit(),
		multiVariableDeclaration = multiVariableDeclaration()?.visit(),
		expression = expression().visit(),
		controlStructureBody = controlStructureBody()?.visit()
	)
}

fun KotlinParser.WhileStatementContext.visit(): WhileStatement {
	return WhileStatement(
		expression = expression().visit(),
		controlStructureBody = controlStructureBody()?.visit()
	)
}

fun KotlinParser.DoWhileStatementContext.visit(): DoWhileStatement {
	return DoWhileStatement(
		controlStructureBody = controlStructureBody()?.visit(),
		expression = expression().visit()
	)
}

fun KotlinParser.AssignmentContext.visit(): Assignment {
	return Assignment(
		directlyAssignableExpression = directlyAssignableExpression()?.visit(),
		assignableExpression = assignableExpression()?.visit(),
		assignmentAndOperator = assignmentAndOperator()?.visit(),
		expression = expression().visit()
	)
}

fun KotlinParser.ExpressionContext.visit(): Expression {
	return Expression(
		disjunction = disjunction().visit()
	)
}

fun KotlinParser.DisjunctionContext.visit(): Disjunction {
	return Disjunction(
		conjunction = conjunction().map { it.visit() }
	)
}

fun KotlinParser.ConjunctionContext.visit(): Conjunction {
	return Conjunction(
		equality = equality().map { it.visit() }
	)
}

fun KotlinParser.EqualityContext.visit(): Equality {
	return Equality(
		comparison = comparison().map { it.visit() },
		equalityOperator = equalityOperator().map { it.visit() }
	)
}

fun KotlinParser.ComparisonContext.visit(): Comparison {
	return Comparison(
		genericCallLikeComparison = genericCallLikeComparison().map { it.visit() },
		comparisonOperator = comparisonOperator().map { it.visit() }
	)
}

fun KotlinParser.GenericCallLikeComparisonContext.visit(): GenericCallLikeComparison {
	return GenericCallLikeComparison(
		infixOperation = infixOperation().visit(),
		callSuffix = callSuffix().map { it.visit() }
	)
}

fun KotlinParser.InfixOperationContext.visit(): InfixOperation {
	return InfixOperation(
		elvisExpression = elvisExpression(0)!!.visit(),
		sub = buildList {
			val list = children!!.drop(1).dropWhile {
				it is TerminalNode && it.symbol.type == KotlinLexer.Tokens.NL
			}
			for (i in 0 until list.size / 2) {
				val operator = list[i * 2]
				val right = list[i * 2 + 1]
				add(
					when {
						operator is KotlinParser.InOperatorContext && right is KotlinParser.ElvisExpressionContext -> {
							InfixOperation.In(
								operator = operator.visit(),
								right = right.visit()
							)
						}

						operator is KotlinParser.IsOperatorContext && right is KotlinParser.TypeContext -> {
							InfixOperation.Is(
								operator = operator.visit(),
								right = right.visit()
							)
						}

						else -> error("operator: $operator, right: $right")
					}
				)
			}
		}
	)
}

fun KotlinParser.ElvisExpressionContext.visit(): ElvisExpression {
	return ElvisExpression(
		infixFunctionCall = infixFunctionCall().map { it.visit() }
	)
}

fun KotlinParser.InfixFunctionCallContext.visit(): InfixFunctionCall {
	return InfixFunctionCall(
		rangeExpression = rangeExpression().map { it.visit() },
		simpleIdentifier = simpleIdentifier().map { it.visit() }
	)
}

fun KotlinParser.RangeExpressionContext.visit(): RangeExpression {
	return RangeExpression(
		additiveExpression = additiveExpression().map { it.visit() },
		sub = buildList {
			val list = children!!.drop(1).dropWhile {
				it is TerminalNode && it.symbol.type == KotlinLexer.Tokens.NL
			}
			for (i in 0 until list.size / 2) {
				val operator = list[i * 2] as TerminalNode
				add(
					when (operator.symbol.type) {
						KotlinLexer.Tokens.RANGE -> RangeExpression.Range
						KotlinLexer.Tokens.RANGE_UNTIL -> RangeExpression.RangeUntil
						else -> error(operator)
					}
				)
			}
		}
	)
}

fun KotlinParser.AdditiveExpressionContext.visit(): AdditiveExpression {
	return AdditiveExpression(
		multiplicativeExpression = multiplicativeExpression().map { it.visit() },
		additiveOperator = additiveOperator().map { it.visit() }
	)
}

fun KotlinParser.MultiplicativeExpressionContext.visit(): MultiplicativeExpression {
	return MultiplicativeExpression(
		asExpression = asExpression().map { it.visit() },
		multiplicativeOperator = multiplicativeOperator().map { it.visit() }
	)
}

fun KotlinParser.AsExpressionContext.visit(): AsExpression {
	return AsExpression(
		prefixUnaryExpression = prefixUnaryExpression().visit(),
		asOperator = asOperator().map { it.visit() },
		type = type().map { it.visit() }
	)
}

fun KotlinParser.PrefixUnaryExpressionContext.visit(): PrefixUnaryExpression {
	return PrefixUnaryExpression(
		unaryPrefix = unaryPrefix().map { it.visit() },
		postfixUnaryExpression = postfixUnaryExpression().visit()
	)
}

fun KotlinParser.UnaryPrefixContext.visit(): UnaryPrefix {
	return UnaryPrefix(
		annotation = annotation()?.visit(),
		label = label()?.visit(),
		prefixUnaryOperator = prefixUnaryOperator()?.visit()
	)
}

fun KotlinParser.PostfixUnaryExpressionContext.visit(): PostfixUnaryExpression {
	return PostfixUnaryExpression(
		primaryExpression = primaryExpression().visit(),
		postfixUnarySuffix = postfixUnarySuffix().map { it.visit() }
	)
}

fun KotlinParser.PostfixUnarySuffixContext.visit(): PostfixUnarySuffix {
	return PostfixUnarySuffix(
		postfixUnaryOperator = postfixUnaryOperator()?.visit(),
		typeArguments = typeArguments()?.visit(),
		callSuffix = callSuffix()?.visit(),
		indexingSuffix = indexingSuffix()?.visit(),
		navigationSuffix = navigationSuffix()?.visit()
	)
}

fun KotlinParser.DirectlyAssignableExpressionContext.visit(): DirectlyAssignableExpression {
	return DirectlyAssignableExpression(
		postfixUnaryExpression = postfixUnaryExpression()?.visit(),
		assignableSuffix = assignableSuffix()?.visit(),
		simpleIdentifier = simpleIdentifier()?.visit(),
		parenthesizedDirectlyAssignableExpression = parenthesizedDirectlyAssignableExpression()?.visit()
	)
}

fun KotlinParser.ParenthesizedDirectlyAssignableExpressionContext.visit(): ParenthesizedDirectlyAssignableExpression {
	return ParenthesizedDirectlyAssignableExpression(
		directlyAssignableExpression = directlyAssignableExpression().visit()
	)
}

fun KotlinParser.AssignableExpressionContext.visit(): AssignableExpression {
	return AssignableExpression(
		prefixUnaryExpression = prefixUnaryExpression()?.visit(),
		parenthesizedAssignableExpression = parenthesizedAssignableExpression()?.visit()
	)
}

fun KotlinParser.ParenthesizedAssignableExpressionContext.visit(): ParenthesizedAssignableExpression {
	return ParenthesizedAssignableExpression(
		assignableExpression = assignableExpression().visit()
	)
}

fun KotlinParser.AssignableSuffixContext.visit(): AssignableSuffix {
	return AssignableSuffix(
		typeArguments = typeArguments()?.visit(),
		indexingSuffix = indexingSuffix()?.visit(),
		navigationSuffix = navigationSuffix()?.visit()
	)
}

fun KotlinParser.IndexingSuffixContext.visit(): IndexingSuffix {
	return IndexingSuffix(
		expression = expression().map { it.visit() }
	)
}

fun KotlinParser.NavigationSuffixContext.visit(): NavigationSuffix {
	return NavigationSuffix(
		memberAccessOperator = memberAccessOperator().visit(),
		simpleIdentifier = simpleIdentifier()?.visit(),
		parenthesizedExpression = parenthesizedExpression()?.visit(),
		`class` = CLASS() != null
	)
}

fun KotlinParser.CallSuffixContext.visit(): CallSuffix {
	return CallSuffix(
		typeArguments = typeArguments()?.visit(),
		valueArguments = valueArguments()?.visit(),
		annotatedLambda = annotatedLambda()?.visit()
	)
}

fun KotlinParser.AnnotatedLambdaContext.visit(): AnnotatedLambda {
	return AnnotatedLambda(
		annotation = annotation().map { it.visit() },
		label = label()?.visit(),
		lambdaLiteral = lambdaLiteral().visit()
	)
}

fun KotlinParser.TypeArgumentsContext.visit(): TypeArguments {
	return TypeArguments(
		typeProjection = typeProjection().map { it.visit() }
	)
}

fun KotlinParser.ValueArgumentsContext.visit(): ValueArguments {
	return ValueArguments(
		valueArgument = valueArgument().map { it.visit() }
	)
}

fun KotlinParser.ValueArgumentContext.visit(): ValueArgument {
	return ValueArgument(
		annotation = annotation()?.visit(),
		simpleIdentifier = simpleIdentifier()?.visit(),
		multi = MULT() != null,
		expression = expression().visit()
	)
}

fun KotlinParser.PrimaryExpressionContext.visit(): PrimaryExpression {
	return PrimaryExpression(
		parenthesizedExpression = parenthesizedExpression()?.visit(),
		simpleIdentifier = simpleIdentifier()?.visit(),
		literalConstant = literalConstant()?.visit(),
		stringLiteral = stringLiteral()?.visit(),
		callableReference = callableReference()?.visit(),
		functionLiteral = functionLiteral()?.visit(),
		objectLiteral = objectLiteral()?.visit(),
		collectionLiteral = collectionLiteral()?.visit(),
		thisExpression = thisExpression()?.visit(),
		superExpression = superExpression()?.visit(),
		ifExpression = ifExpression()?.visit(),
		whenExpression = whenExpression()?.visit(),
		tryExpression = tryExpression()?.visit(),
		jumpExpression = jumpExpression()?.visit()
	)
}

fun KotlinParser.ParenthesizedExpressionContext.visit(): ParenthesizedExpression {
	return ParenthesizedExpression(
		expression = expression().visit()
	)
}

fun KotlinParser.CollectionLiteralContext.visit(): CollectionLiteral {
	return CollectionLiteral(
		expression = expression().map { it.visit() }
	)
}

fun KotlinParser.LiteralConstantContext.visit(): LiteralConstant {
	return LiteralConstant(
		booleanLiteral = BooleanLiteral()?.let { BooleanLiteral(it.text.toBoolean()) },
		integerLiteral = IntegerLiteral()?.let { IntegerLiteral(it.text) },
		hexLiteral = HexLiteral()?.let { HexLiteral(it.text) },
		binLiteral = BinLiteral()?.let { BinLiteral(it.text) },
		characterLiteral = CharacterLiteral()?.let { CharacterLiteral(it.text) },
		realLiteral = RealLiteral()?.let { RealLiteral(it.text) },
		nullLiteral = NullLiteral()?.let { NullLiteral },
		longLiteral = LongLiteral()?.let { LongLiteral(it.text) },
		unsignedLiteral = UnsignedLiteral()?.let { UnsignedLiteral(it.text) }
	)
}

fun KotlinParser.StringLiteralContext.visit(): StringLiteral {
	return StringLiteral(
		lineStringLiteral = lineStringLiteral()?.visit(),
		multiLineStringLiteral = multiLineStringLiteral()?.visit()
	)
}

fun KotlinParser.LineStringLiteralContext.visit(): LineStringLiteral {
	return LineStringLiteral(
		sub = children!!.drop(1).dropLast(1).map {
			when (it) {
				is KotlinParser.LineStringContentContext -> LineStringLiteral.Sub(
					lineStringContent = it.visit(),
					lineStringExpression = null
				)

				is KotlinParser.LineStringExpressionContext -> LineStringLiteral.Sub(
					lineStringContent = null,
					lineStringExpression = it.visit()
				)

				else -> error(it)
			}
		}
	)
}

fun KotlinParser.MultiLineStringLiteralContext.visit(): MultiLineStringLiteral {
	return MultiLineStringLiteral(
		sub = buildList {
			children!!.filter {
				!(it is TerminalNode &&
						(it.symbol.type == KotlinLexer.Tokens.TRIPLE_QUOTE_OPEN ||
								it.symbol.type == KotlinLexer.Tokens.TRIPLE_QUOTE_CLOSE))
			}.forEach {
				add(
					MultiLineStringLiteral.Sub(
						multiLineStringContent = (it as? KotlinParser.MultiLineStringContentContext)?.visit(),
						multiLineStringExpression = (it as? KotlinParser.MultiLineStringExpressionContext)?.visit(),
						multiLineStringQuote = it is TerminalNode
					)
				)
			}
		}
	)
}

fun KotlinParser.LineStringContentContext.visit(): LineStringContent {
	return LineStringContent(
		lineStrText = LineStrText()?.let { LineStrText(it.text) },
		lineStrEscapedChar = LineStrEscapedChar()?.let { LineStrEscapedChar(it.text) },
		lineStrRef = LineStrRef()?.let { LineStrRef(it.text) }
	)
}

fun KotlinParser.LineStringExpressionContext.visit(): LineStringExpression {
	return LineStringExpression(
		expression = expression().visit()
	)
}

fun KotlinParser.MultiLineStringContentContext.visit(): MultiLineStringContent {
	return MultiLineStringContent(
		multiLineStrText = MultiLineStrText()?.let { MultiLineStrText(it.text) },
		multiLineStringQuote = MultiLineStringQuote()?.let { MultiLineStringQuote(it.text.length) },
		multiLineStrRef = MultiLineStrRef()?.let { MultiLineStrRef(it.text) }
	)
}

fun KotlinParser.MultiLineStringExpressionContext.visit(): MultiLineStringExpression {
	return MultiLineStringExpression(
		expression = expression().visit()
	)
}

fun KotlinParser.LambdaLiteralContext.visit(): LambdaLiteral {
	return LambdaLiteral(
		lambdaParameters = lambdaParameters()?.visit(),
		statements = statements().visit()
	)
}

fun KotlinParser.LambdaParametersContext.visit(): LambdaParameters {
	return LambdaParameters(
		lambdaParameter = lambdaParameter().map { it.visit() }
	)
}

fun KotlinParser.LambdaParameterContext.visit(): LambdaParameter {
	return LambdaParameter(
		variableDeclaration = variableDeclaration()?.visit(),
		multiVariableDeclaration = multiVariableDeclaration()?.visit(),
		type = type()?.visit()
	)
}

fun KotlinParser.AnonymousFunctionContext.visit(): AnonymousFunction {
	val list = children!!.filter { it !is TerminalNode }
	val type = list[0].let {
		when (it) {
			is KotlinParser.TypeContext -> it.visit()
			else -> null
		}
	}
	val typeRightIndex = when (type) {
		null -> 1
		else -> 2
	}
	val typeRight = list[typeRightIndex].let {
		when (it) {
			is KotlinParser.TypeContext -> it.visit()
			else -> null
		}
	}
	return AnonymousFunction(
		suspend = SUSPEND() != null,
		type = type,
		parametersWithOptionalType = parametersWithOptionalType().visit(),
		typeRight = typeRight,
		typeConstraints = typeConstraints()?.visit(),
		functionBody = functionBody()?.visit()
	)
}

fun KotlinParser.FunctionLiteralContext.visit(): FunctionLiteral {
	return FunctionLiteral(
		lambdaLiteral = lambdaLiteral()?.visit(),
		anonymousFunction = anonymousFunction()?.visit()
	)
}

fun KotlinParser.ObjectLiteralContext.visit(): ObjectLiteral {
	return ObjectLiteral(
		data = DATA() != null,
		delegationSpecifiers = delegationSpecifiers()?.visit(),
		classBody = classBody()?.visit()
	)
}

fun KotlinParser.ThisExpressionContext.visit(): ThisExpression {
	return ThisExpression(
		at = THIS_AT()?.text?.drop("this@".length)
	)
}

fun KotlinParser.SuperExpressionContext.visit(): SuperExpression {
	return SuperExpression(
		type = type()?.visit(),
		simpleIdentifier = simpleIdentifier()?.visit(),
		at = SUPER_AT()?.text?.drop("super@".length)
	)
}

fun KotlinParser.IfExpressionContext.visit(): IfExpression {
	val list = children!!.filter {
		it is KotlinParser.ControlStructureBodyContext ||
				(it is TerminalNode && it.symbol.type == KotlinLexer.Tokens.ELSE)
	}
	val controlStructureBody = list[0].let {
		when (it) {
			is KotlinParser.ControlStructureBodyContext -> it.visit()
			else -> null
		}
	}
	val elseControlStructureBody = when {
		list.any { it is TerminalNode } -> (list.last() as KotlinParser.ControlStructureBodyContext).visit()
		else -> null
	}
	return IfExpression(
		expression = expression().visit(),
		controlStructureBody = controlStructureBody,
		elseControlStructureBody = elseControlStructureBody
	)
}

fun KotlinParser.WhenSubjectContext.visit(): WhenSubject {
	return WhenSubject(
		annotation = annotation().map { it.visit() },
		variableDeclaration = variableDeclaration()?.visit(),
		expression = expression().visit()
	)
}

fun KotlinParser.WhenExpressionContext.visit(): WhenExpression {
	return WhenExpression(
		whenSubject = whenSubject()?.visit(),
		whenEntry = whenEntry().map { it.visit() }
	)
}

fun KotlinParser.WhenEntryContext.visit(): WhenEntry {
	return WhenEntry(
		whenCondition = whenCondition().map { it.visit() },
		`else` = ELSE() != null,
		controlStructureBody = controlStructureBody().visit()
	)
}

fun KotlinParser.WhenConditionContext.visit(): WhenCondition {
	return WhenCondition(
		expression = expression()?.visit(),
		rangeTest = rangeTest()?.visit(),
		typeTest = typeTest()?.visit()
	)
}

fun KotlinParser.RangeTestContext.visit(): RangeTest {
	return RangeTest(
		inOperator = inOperator().visit(),
		expression = expression().visit()
	)
}

fun KotlinParser.TypeTestContext.visit(): TypeTest {
	return TypeTest(
		isOperator = isOperator().visit(),
		type = type().visit()
	)
}

fun KotlinParser.TryExpressionContext.visit(): TryExpression {
	return TryExpression(
		block = block().visit(),
		catchBlock = catchBlock().map { it.visit() },
		finallyBlock = finallyBlock()?.visit()
	)
}

fun KotlinParser.CatchBlockContext.visit(): CatchBlock {
	return CatchBlock(
		annotation = annotation().map { it.visit() },
		simpleIdentifier = simpleIdentifier().visit(),
		type = type().visit(),
		block = block().visit()
	)
}

fun KotlinParser.FinallyBlockContext.visit(): FinallyBlock {
	return FinallyBlock(
		block = block().visit()
	)
}

fun KotlinParser.JumpExpressionContext.visit(): JumpExpression {
	return JumpExpression(
		`throw` = THROW() != null,
		`return` = RETURN() != null || RETURN_AT() != null,
		`continue` = CONTINUE() != null || CONTINUE_AT() != null,
		`break` = BREAK() != null || BREAK_AT() != null,
		expression = expression()?.visit(),
		at = (children!![0] as TerminalNode).let {
			when (it.symbol.type) {
				KotlinLexer.Tokens.RETURN_AT -> it.text.drop("return@".length)
				KotlinLexer.Tokens.CONTINUE_AT -> it.text.drop("continue@".length)
				KotlinLexer.Tokens.BREAK_AT -> it.text.drop("break@".length)
				else -> null
			}
		}
	)
}

fun KotlinParser.CallableReferenceContext.visit(): CallableReference {
	return CallableReference(
		receiverType = receiverType()?.visit(),
		simpleIdentifier = simpleIdentifier()?.visit()
	)
}

fun KotlinParser.AssignmentAndOperatorContext.visit(): AssignmentAndOperator {
	return AssignmentAndOperator(
		add = ADD_ASSIGNMENT() != null,
		sub = SUB_ASSIGNMENT() != null,
		mul = MULT_ASSIGNMENT() != null,
		div = DIV_ASSIGNMENT() != null,
		mod = MOD_ASSIGNMENT() != null
	)
}

fun KotlinParser.EqualityOperatorContext.visit(): EqualityOperator {
	return EqualityOperator(
		hardEq = EXCL_EQEQ() != null || EQEQEQ() != null,
		excl = EXCL_EQ() != null || EXCL_EQEQ() != null
	)
}

fun KotlinParser.ComparisonOperatorContext.visit(): ComparisonOperator {
	return ComparisonOperator(
		eq = LE() != null || GE() != null,
		greater = RANGLE() != null || GE() != null
	)
}

fun KotlinParser.InOperatorContext.visit(): InOperator {
	return InOperator(
		not = NOT_IN() != null
	)
}

fun KotlinParser.IsOperatorContext.visit(): IsOperator {
	return IsOperator(
		not = NOT_IS() != null
	)
}

fun KotlinParser.AdditiveOperatorContext.visit(): AdditiveOperator {
	return AdditiveOperator(
		add = ADD() != null
	)
}

fun KotlinParser.MultiplicativeOperatorContext.visit(): MultiplicativeOperator {
	return MultiplicativeOperator(
		mul = MULT() != null,
		div = DIV() != null,
		mod = MOD() != null
	)
}

fun KotlinParser.AsOperatorContext.visit(): AsOperator {
	return AsOperator(
		safe = AS_SAFE() != null
	)
}

fun KotlinParser.PrefixUnaryOperatorContext.visit(): PrefixUnaryOperator {
	return PrefixUnaryOperator(
		increment = INCR() != null,
		decrement = DECR() != null,
		sub = SUB() != null,
		add = ADD() != null,
		excl = excl() != null
	)
}

fun KotlinParser.PostfixUnaryOperatorContext.visit(): PostfixUnaryOperator {
	return PostfixUnaryOperator(
		increment = INCR() != null,
		decrement = DECR() != null,
		excl = excl() != null
	)
}

fun KotlinParser.MemberAccessOperatorContext.visit(): MemberAccessOperator {
	return MemberAccessOperator(
		safe = safeNav() != null,
		reference = COLONCOLON() != null
	)
}

fun KotlinParser.ModifiersContext.visit(): Modifiers {
	return Modifiers(
		sub = children!!.map {
			Modifiers.Sub(
				annotation = (it as? KotlinParser.AnnotationContext)?.visit(),
				modifier = (it as? KotlinParser.ModifierContext)?.visit()
			)
		}
	)
}

fun KotlinParser.ParameterModifiersContext.visit(): ParameterModifiers {
	return ParameterModifiers(
		sub = children!!.map {
			ParameterModifiers.Sub(
				annotation = (it as? KotlinParser.AnnotationContext)?.visit(),
				parameterModifier = (it as? KotlinParser.ParameterModifierContext)?.visit()
			)
		}
	)
}

fun KotlinParser.ModifierContext.visit(): Modifier {
	return Modifier(
		classModifier = classModifier()?.visit(),
		memberModifier = memberModifier()?.visit(),
		visibilityModifier = visibilityModifier()?.visit(),
		functionModifier = functionModifier()?.visit(),
		propertyModifier = propertyModifier()?.visit(),
		inheritanceModifier = inheritanceModifier()?.visit(),
		parameterModifier = parameterModifier()?.visit(),
		platformModifier = platformModifier()?.visit()
	)
}

fun KotlinParser.TypeModifiersContext.visit(): TypeModifiers {
	return TypeModifiers(
		typeModifier = typeModifier().map { it.visit() }
	)
}

fun KotlinParser.TypeModifierContext.visit(): TypeModifier {
	return TypeModifier(
		annotation = annotation()?.visit(),
		suspend = SUSPEND() != null
	)
}

fun KotlinParser.ClassModifierContext.visit(): ClassModifier {
	return ClassModifier(
		enum = ENUM() != null,
		sealed = SEALED() != null,
		annotation = ANNOTATION() != null,
		data = DATA() != null,
		inner = INNER() != null,
		value = VALUE() != null
	)
}

fun KotlinParser.MemberModifierContext.visit(): MemberModifier {
	return MemberModifier(
		override = OVERRIDE() != null,
		lateInit = LATEINIT() != null
	)
}

fun KotlinParser.VisibilityModifierContext.visit(): VisibilityModifier {
	return VisibilityModifier(
		public = PUBLIC() != null,
		private = PRIVATE() != null,
		internal = INTERNAL() != null,
		protected = PROTECTED() != null
	)
}

fun KotlinParser.VarianceModifierContext.visit(): VarianceModifier {
	return VarianceModifier(
		out = OUT() != null
	)
}

fun KotlinParser.TypeParameterModifiersContext.visit(): TypeParameterModifiers {
	return TypeParameterModifiers(
		typeParameterModifier = typeParameterModifier().map { it.visit() }
	)
}

fun KotlinParser.TypeParameterModifierContext.visit(): TypeParameterModifier {
	return TypeParameterModifier(
		reificationModifier = reificationModifier()?.visit(),
		varianceModifier = varianceModifier()?.visit(),
		annotation = annotation()?.visit()
	)
}

fun KotlinParser.FunctionModifierContext.visit(): FunctionModifier {
	return FunctionModifier(
		tailrec = TAILREC() != null,
		operator = OPERATOR() != null,
		infix = INFIX() != null,
		inline = INLINE() != null,
		external = EXTERNAL() != null,
		suspend = SUSPEND() != null
	)
}

fun KotlinParser.PropertyModifierContext.visit(): PropertyModifier {
	return PropertyModifier
}

fun KotlinParser.InheritanceModifierContext.visit(): InheritanceModifier {
	return InheritanceModifier(
		abstract = ABSTRACT() != null,
		final = FINAL() != null,
		open = OPEN() != null
	)
}

fun KotlinParser.ParameterModifierContext.visit(): ParameterModifier {
	return ParameterModifier(
		vararg = VARARG() != null,
		noInline = NOINLINE() != null,
		crossInline = CROSSINLINE() != null
	)
}

fun KotlinParser.ReificationModifierContext.visit(): ReificationModifier {
	return ReificationModifier
}

fun KotlinParser.PlatformModifierContext.visit(): PlatformModifier {
	return PlatformModifier(
		actual = ACTUAL() != null
	)
}

fun KotlinParser.AnnotationContext.visit(): tree.grammar.Annotation {
	return tree.grammar.Annotation(
		singleAnnotation = singleAnnotation()?.visit(),
		multiAnnotation = multiAnnotation()?.visit()
	)
}

fun KotlinParser.SingleAnnotationContext.visit(): SingleAnnotation {
	return SingleAnnotation(
		annotationUseSiteTarget = annotationUseSiteTarget()?.visit(),
		unescapedAnnotation = unescapedAnnotation().visit()
	)
}

fun KotlinParser.MultiAnnotationContext.visit(): MultiAnnotation {
	return MultiAnnotation(
		annotationUseSiteTarget = annotationUseSiteTarget()?.visit(),
		unescapedAnnotation = unescapedAnnotation().map { it.visit() }
	)
}

fun KotlinParser.AnnotationUseSiteTargetContext.visit(): AnnotationUseSiteTarget {
	return AnnotationUseSiteTarget(
		field = FIELD() != null,
		property = PROPERTY() != null,
		get = GET() != null,
		set = SET() != null,
		receiver = RECEIVER() != null,
		param = PARAM() != null,
		setParam = SETPARAM() != null,
		delegate = DELEGATE() != null
	)
}

fun KotlinParser.UnescapedAnnotationContext.visit(): UnescapedAnnotation {
	return UnescapedAnnotation(
		constructorInvocation = constructorInvocation()?.visit(),
		userType = userType()?.visit()
	)
}

fun KotlinParser.SimpleIdentifierContext.visit(): SimpleIdentifier {
	return SimpleIdentifier(
		value = text
	)
}

fun KotlinParser.IdentifierContext.visit(): Identifier {
	return Identifier(
		simpleIdentifier = simpleIdentifier().map { it.visit() }
	)
}