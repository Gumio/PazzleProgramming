package com.kcg.project.pazpro.service

import com.github.h0tk3y.betterParse.grammar.parseToEnd
import com.kcg.project.pazpro.parsing.RubySyntaxChecker
import org.springframework.stereotype.Service

@Service
class ParseService {
    fun parsing(code: String): String {
        val result = RubySyntaxChecker.parseToEnd(code).toString()
        println(result)
        return result
    }
}