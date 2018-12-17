package com.kcg.project.pazpro

import com.github.h0tk3y.betterParse.grammar.parseToEnd
import com.kcg.project.pazpro.parsing.*

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
                something([1, 2, 3])
            end
        end
        puts "PUTS TEST"
        """.trimIndent()

//        val treeGrammar = RubySyntaxChecker.liftToSyntaxTreeGrammar()
        val result = RubySyntaxChecker.parseToEnd(testCode)
        println(result)
    }
}