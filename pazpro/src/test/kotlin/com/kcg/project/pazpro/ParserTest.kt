package com.kcg.project.pazpro

import com.github.h0tk3y.betterParse.grammar.parseToEnd
import com.kcg.project.pazpro.parsing.RubySyntaxChecker

fun main(args: Array<String>) {
    TestCase.test02()
}

object TestCase {
    fun test01() {
        val testCode = """
        puts "test"
        if 1 != 2 then
            return while true do
                puts "1"
            end
        elsif false
            return "fuga"
        elsif false
            return "sushi"
        else
            return "elel"
        end
    """.trimIndent()

        val result = RubySyntaxChecker.parseToEnd(testCode)
        println(result)
    }

    fun test02() {
        val testCode = """
        for hoge in 1...5
            if hoge != 1
                puts "hoge"
                hoge(123, [4, 5, 6])
            end
        end
        puts "hoge"
        """.trimIndent()

//        val treeGrammar = RubySyntaxChecker.liftToSyntaxTreeGrammar()
        val result = RubySyntaxChecker.parseToEnd(testCode)
        println(result)
    }
}