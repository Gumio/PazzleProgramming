package com.kcg.project.pazpro.language

sealed class AST

object Skip : AST()

data class CompStatement(val expressions: List<AST>) : AST() {
    override fun toString(): String {
        return expressions.joinToString("\n")
    }
}

data class FunctionDeclaration(val name: String, val parameters: List<AST>) : AST()

//data class FunctionCall(val functionDeclaration: FunctionDeclaration, val arguments: List<AST>) : AST() {
//    init {
//        require(functionDeclaration.parameters.size == arguments.size)
//    }
//}

data class FunctionCall(val name: String, val parameters: List<AST>) : AST()

data class Return(val expr: AST) : AST()

data class Put(val str: AST) : AST()

data class Variable(val name: String) : AST() {
    override fun toString(): String {
        return "Variable($name)"
    }
}

data class IfExpression(val cond: AST, val trueBody: AST, val falseBody: AST) : AST() {
//    override fun toString(): String {
//        return "IfExpression\n" +
//                "\t$cond\n" +
//                "\t$trueBody\n" +
//                "\t$falseBody"
//    }
}
data class WhileExpression(val cond: AST, val body: AST) : AST()
data class ForExpression(val variable: AST, val expression: AST, val body: AST) : AST() {
//    override fun toString(): String {
//        return "ForExpression\n" +
//                "\t$variable in $expression\n" +
//                "\t$body"
//    }
}

sealed class Literal : AST()

data class StringLiteral(val value: String) : Literal() {
    override fun toString(): String {
        return "String($value)"
    }
}
data class IntLiteral(val value: Int) : Literal() {
    override fun toString(): String {
        return "Int($value)"
    }
}
data class BooleanLiteral(val value: Boolean) : Literal() {
    override fun toString(): String {
        return "Boolean($value)"
    }
}
data class ArrayLiteral(val value: List<AST>) : AST()

// assign
sealed class Assignment(id: AST, value: AST) : AST()
data class SimpleAssignment(val id: AST, val value: AST) : Assignment(id, value)
data class PlusAssignment(val id: AST, val value: AST) : Assignment(id, value)


data class UnaryOperation(val operand: AST, val kind: UnaryOperationKind) : AST()
sealed class UnaryOperationKind
object Not : UnaryOperationKind()

// lhs operator rhs
data class BinaryOperation(val left: AST, val right: AST, val kind: BinaryOperationKind) : AST() {
    override fun toString(): String {
        return "${kind::class.java.simpleName}($left, $right)"
    }
}
sealed class BinaryOperationKind
object Plus : BinaryOperationKind()
object Minus : BinaryOperationKind()
object Times : BinaryOperationKind()
object Div : BinaryOperationKind()
object Mod : BinaryOperationKind()
object Exponent : BinaryOperationKind()

object And : BinaryOperationKind()
object Or : BinaryOperationKind()

object Eq : BinaryOperationKind()
object Neq  : BinaryOperationKind()
object SpaceShip : BinaryOperationKind()
object CaseEq : BinaryOperationKind()
object Match : BinaryOperationKind()
object NotMatch : BinaryOperationKind()

object Gt : BinaryOperationKind()
object Lt : BinaryOperationKind()
object Leq : BinaryOperationKind()
object Geq : BinaryOperationKind()

object Rs : BinaryOperationKind()   // right shift  >>
object Ls : BinaryOperationKind()   // left shift   <<

object BitAnd : BinaryOperationKind()
object BitOr : BinaryOperationKind()

object Range2 : BinaryOperationKind()
object Range3 : BinaryOperationKind()


