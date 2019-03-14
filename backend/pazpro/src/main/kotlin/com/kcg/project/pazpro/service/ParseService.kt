package com.kcg.project.pazpro.service

import com.github.h0tk3y.betterParse.grammar.parseToEnd
import com.kcg.project.pazpro.language.*
import com.kcg.project.pazpro.parsing.RubySyntaxChecker
import org.springframework.stereotype.Service

@Service
class ParseService {
    fun parsing(code: String): AST {
        val result = RubySyntaxChecker.parseToEnd(code)
        println("parse result -> ${result.toString()}")
        return result
    }

    fun visit(ast: AST, count: Int = 0): Int = when(ast) {
        is CompStatement -> {
            var c = count
            ast.expressions.forEach { a ->  c = visit(a, c) }
            c
        }
        is IfExpression -> visit(ast.falseBody, visit(ast.trueBody, count + 1))
        is ForExpression -> visit(ast.body, count + 1)
        is Put -> count + 1
        is Literal -> count + 1
        else -> count + 1
    }
}