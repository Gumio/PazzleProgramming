package com.kcg.project.pazpro.service

import com.github.h0tk3y.betterParse.grammar.parseToEnd
import com.kcg.project.pazpro.parsing.RubySyntaxChecker
import org.springframework.stereotype.Service

@Service
class ExecuteService {
    fun tryExecute(code: String): String = parsing(code)
    private fun parsing(code: String): String = RubySyntaxChecker.parseToEnd(code).toString()
}