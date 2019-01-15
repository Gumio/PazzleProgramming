package com.kcg.project.pazpro.service

import com.github.h0tk3y.betterParse.grammar.parseToEnd
import com.kcg.project.pazpro.language.AST
import com.kcg.project.pazpro.language.IfExpression
import com.kcg.project.pazpro.parsing.RubySyntaxChecker
import org.springframework.stereotype.Service

@Service
class ParseService {
    fun parsing(code: String): AST {
        val result = RubySyntaxChecker.parseToEnd(code)
        println("parse result -> ${result.toString()}")
        return result
    }

    /* TODO
    fun parsingStep(obj: AST, step: Int = 0): Int {
        when(obj) {
            is IfExpression -> {

            }
        }
        return step
    }
    */
}