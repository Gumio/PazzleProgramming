package com.kcg.project.pazpro.parsing

import com.github.h0tk3y.betterParse.combinators.*
import com.github.h0tk3y.betterParse.grammar.Grammar
import com.github.h0tk3y.betterParse.grammar.parser
import com.github.h0tk3y.betterParse.parser.Parser
import com.kcg.project.pazpro.language.*

object RubySyntaxChecker : Grammar<Any>() {
    private val LPAR by token("\\(")
    private val RPAR by token("\\)")

    private val LSQ by token("\\[")
    private val RSQ by token("]")

    private val LBRC by token("\\{")
    private val RBRC by token("}")

    // Binary(math)
    private val PLUS by token("\\+")
    private val MINUS by token("-")
    private val DIV by token("/")
    private val MOD by token("%")
    private val TIMES by token("\\*")
    private val EXPO by token("\\*{2}")
    // Binary(shift)
    private val RS by token(">>")
    private val LS by token("<<")
    // Binary(bit)
    private val BITAND by token("&")
    private val BITOR by token("[\\^\\|]")
    // Binary(comparison)
    private val LEQ by token("<=")
    private val GEQ by token(">=")
    private val LT by token("<")
    private val GT by token(">")
    // Binary(equal, pattern)
    private val SPACESHIP by token("<=>")
    private val EQU by token("==")
    private val CASEEQU by token("===")
    private val NEQ by token("!=")
    private val MATCH by token("=~")
    private val NOTMATCH by token("!~")
    // Binary(and, or)
    private val AND by token("&&")
    private val OR by token("\\|\\|")
    // Binary(range)
    private val RANGE3 by token("\\.{3}")
    private val RANGE2 by token("\\.{2}")

    // Ternary
    private val TERNARY by token("\\b\\?\\b")
    private val CORON by token(":")

    // Unary
    private val NOT by token("!")

    // Assignment
    private val ASGN by token("=")
    private val PASGN by token("\\+=")

    // Separator
    private val COMMA by token(",")
    private val SEMI by token(";")
    private val THEN by token("then\\b")
    private val DO by token("do\\b")
    private val END by token("end\\b")

    // Literal
    private val TRUE by token("true")
    private val FALSE by token("false")
    private val STRINGLIT by token("['\"].*?['\"]")
    private val NUMBER by token("\\d+")
    private val NIL by token("nil")
    private val SELF by token("self")

    // Expression modifiers
    private val RETURN by token("return\\b")

    private val IF by token("if\\b")
    private val ELSIF by token("elsif\\b")
    private val ELSE by token("else\\b")
    private val UNLESS by token("unless\\b")

    private val WHILE by token("while\\b")
    private val FOR by token("for\\b")
    private val IN by token("in\\b")

    private val PUT by token("puts\\b")

    // System
    private val ID by token("[a-zA-Z]\\w*")

    private val GLOBAL by token("\\$")
    private val INSTANCEVAR by token("@")

    private val DOT by token("\\.")

    private val NEWLINE by token("[\\r\\n]+", ignore = true)
    private val WHITESPACE by token("[ ]+", ignore = true)
    private val TAB by token("[\\t]+", ignore = true)

    // Other
    private val SCORPOP by token("::")

    private val operator = mapOf(
            PLUS to Plus,
            MINUS to Minus,
            TIMES to Times,
            DIV to Div,
            MOD to Mod,
            AND to And,
            OR to Or,
            EQU to Eq,
            NEQ to Neq,
            GT to Gt,
            LT to Lt,
            LEQ to Leq,
            GEQ to Geq,
            RANGE2 to Range2,
            RANGE3 to Range3,
            EXPO to Exponent,
            SPACESHIP to SpaceShip,
            CASEEQU to CaseEq,
            MATCH to Match,
            NOTMATCH to NotMatch,
            RS to Rs,
            LS to Ls,
            BITAND to BitAnd,
            BITOR to BitOr
    )

    // Separator
    private val terminator by SEMI or NEWLINE
    private val then by terminator or THEN or terminator * THEN
    private val `do` by terminator or DO or terminator * DO
//    private val end by END or RBRC

    // literals
    private val stringLiteral by STRINGLIT use { StringLiteral(text.removeSurrounding("\"", "\"")) }
    private val numLiteral by (optional(MINUS) map { if (it == null) 1 else -1 }) * NUMBER map { (i, num) -> IntLiteral(i * num.text.toInt()) }
    private val boolLiteral by (TRUE or FALSE) use { BooleanLiteral(text == "true") }
//    private val symbolLiteral by -CORON * parser(::varName)

    private val arrayLiteral by -LSQ * separatedTerms(parser(::expr), COMMA, acceptZero = true) * -RSQ map { ArrayLiteral(it) }

    private val literal by stringLiteral or numLiteral or boolLiteral or arrayLiteral

    // Unary
    private val not by -NOT * parser(::primary) map { UnaryOperation(it, Not) }

    /**
     * Binary
    オペレーター優先度
    配列       []  []=
    累乗       **
    単項       !   ~   +   -
    計算       *   /   %
    計算       +   -
    シフト     >>  <<
    AND       &
    OR        ^  |
    比較       <=  <   >   >=
    比較       <=> ==  === !=  =~  !~
     */
    private val multiplicationOperator by TIMES or DIV or MOD
    private val multiplicationOrExpr by leftAssociative(parser(::primary), multiplicationOperator) { l, o, r -> BinaryOperation(l, r, operator[o.type]!!) }

    private val sumOperator by PLUS or MINUS
    private val math by leftAssociative(multiplicationOrExpr, sumOperator) { l, o, r -> BinaryOperation(l, r, operator[o.type]!!) }

    // >> <<
    private val shiftOperator by RS or LS
    private val shiftChain by leftAssociative(math, shiftOperator) { l, o, r -> BinaryOperation(l, r, operator[o.type]!!) }

    // &
    private val bitAndOperator by BITAND
    private val bitAndChain by leftAssociative(shiftChain, bitAndOperator) { l, o, r -> BinaryOperation(l, r, operator[o.type]!!) }
    // ^ |
    private val bitOrOperator by BITOR
    private val bitOrChain by leftAssociative(bitAndChain, bitOrOperator) { l, o, r -> BinaryOperation(l, r, operator[o.type]!!) }

    // <= < > >=
    private val comparisonOperator by LEQ or LT or GT or GEQ
    //    private val comparisonOrMath by (math * optional(comparisonOperator * math))
//            .map { (left, tail) -> tail?.let { (op, r) -> BinaryOperation(left, r, operator[op.type]!!) } ?: left }
    private val comparisonChain by leftAssociative(bitOrChain, comparisonOperator) { l, o, r -> BinaryOperation(l, r, operator[o.type]!!) }

    // <=> == === != =~ !~
    private val equalityAndPatternOperator by SPACESHIP or EQU or CASEEQU or NEQ or MATCH or NOTMATCH
    private val equalityAndPatternChain by leftAssociative(comparisonChain, equalityAndPatternOperator) { l, o, r -> BinaryOperation(l, r, operator[o.type]!!)}

    private val andChain by leftAssociative(equalityAndPatternChain, AND) { l, _, r  -> BinaryOperation(l, r, And)}
    private val orChain by leftAssociative(andChain, OR) { l, _, r  -> BinaryOperation(l, r, Or) }

    private val rangeOperator by RANGE3 or RANGE2
    private val rangeChain by leftAssociative(orChain, rangeOperator) { l, o, r -> BinaryOperation(l, r, operator[o.type]!!) }

    // Ternary Operator
    private val ternaryOperation by parser(::primary) * -TERNARY * parser(::primary) * -CORON * parser(::primary) map { (cond, ifB, elB) -> IfExpression(cond, ifB, elB) }

    // assignment
    private val global by -GLOBAL * ID              // $varName
    private val instanceVar by -INSTANCEVAR * ID    // @varName
    private val varName by ID or global or instanceVar
    private val variable by varName use { Variable(text) } //　or NIL or SELF

    private val simpleAssignment by variable * -ASGN * parser(::expr) map { (v, value) -> SimpleAssignment(v, value) }
    private val plusAssignment by variable * -PASGN * parser(::expr) map { (v, value) -> PlusAssignment(v, value) }
    private val arrayAssignment by variable * -LSQ * literal * -RSQ * parser(::expr) map { (v, key, value) -> ArrayAssignment(v, key, value) }

    private val assignment by simpleAssignment or plusAssignment or arrayAssignment

    // functions
    private val args by separatedTerms(parser(::expr), COMMA, acceptZero = true) //map {
//        (f, s) ->
//        val args = listOf(f)
//        if (s != null) {
//            args.plus(s)
//        }
//        args
//    }
//    private val functionDeclaration by
    private val functionCall by varName * optional(-LPAR * args * -RPAR) map {
        (name, args) ->
        if (args != null)
            FunctionCall(name.text, args)
        else
            FunctionCall(name.text, emptyList())
    }

    // Expressions
    // if <expr> then <stmt> else <stmt>
    private val ifExpression by -IF * parser(::expr) * -then * parser(::compStmt) *
            zeroOrMore(-ELSIF * parser(::expr) * -then * parser(::compStmt)) *
            optional(-ELSE * parser(::compStmt)).map { it ?: Skip } *
            -END map { (ifCond, ifBody, elif, elseBody) ->
        val elses = elif.foldRight(elseBody) { (elifC, elifB), el ->
            IfExpression(elifC, elifB, el)
        }
        IfExpression(ifCond, ifBody, elses)
    }
    // TODO: 要確認
    private val unlessExpression by -UNLESS * parser(::expr) * -then * parser(::compStmt) *
            optional(-ELSE * parser(::compStmt)).map { it ?: Skip } *
            -END map { (unCond, body, elseBody) ->
        IfExpression(UnaryOperation(unCond, Not), body, elseBody)
    }
    private val whileExpression by (-WHILE * parser(::expr) * -`do` * parser(::compStmt) * -END).map { (cond, body) -> WhileExpression(cond, body) }
    private val forExpression by (-FOR * variable * -IN * parser(::expr) * -`do` * parser(::compStmt) * -END) map { (v, obj, body) ->
        ForExpression(v, obj, body)
    }
    private val returnExpression by -RETURN * optional(parser(::expr)) map { Return(it ?: Skip) }
    private val putExpression by -PUT * parser(::expr) map { Put(it) }

    // expressions
    private val parenExpr by -LPAR * parser(::compStmt) * -RPAR
    private val primary: Parser<AST> by parenExpr or
            functionCall or
            returnExpression or
            assignment or
            ifExpression or
            unlessExpression or
            whileExpression or
            forExpression or
            variable or
            literal or
            putExpression

    // Operation > Expressions
    private val expr: Parser<AST> by rangeChain or not or ternaryOperation or primary

    private val statement by expr

    private val compStmt by statement * zeroOrMore(-terminator * expr) * -optional(terminator) map {
        (first, stmts) ->
        stmts.fold(first) { f, ast ->
            if (f is CompStatement) {
                val exprs = f.expressions.toMutableList()
                exprs.add(ast)
                CompStatement(exprs.toList())
            } else {
                CompStatement(listOf(f, ast))
            }
        }
    }

    override val rootParser: Parser<AST> by compStmt
}