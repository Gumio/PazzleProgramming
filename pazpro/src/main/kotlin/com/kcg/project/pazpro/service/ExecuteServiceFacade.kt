package com.kcg.project.pazpro.service

import com.github.h0tk3y.betterParse.parser.ParseException
import com.kcg.project.pazpro.controller.ExecuteController
import com.kcg.project.pazpro.model.Question
import org.springframework.stereotype.Service
import java.lang.StringBuilder

@Service
class ExecuteServiceFacade(
    private val dockerService: DockerService,
    private val parseService: ParseService,
    val questionService: QuestionService

) {
    fun execute(plainCodeText: String, id: Int): ExecuteController.ExecuteResponse {
        val code = structuringToPlainText(plainCodeText)
        println("code -> $code")
        val question: Question? = questionService.find(id)
        return try {
            parseService.parsing(code)
            dockerService.createMainFile(code)
            val out = dockerService.runCode(question!!.args)
            println(out.trim())
            println(question.stdout)
            when (out.trim() == question.stdout) {
                true -> ExecuteController.ExecuteResponse(true, false, out.trim(), null)
                false -> ExecuteController.ExecuteResponse(false, false, out.trim(), null)
            }
        } catch (e: ParseException) {
            ExecuteController.ExecuteResponse(false, false, e.toString(), "構文が間違っているようです。")
        }
    }

    private fun structuringToPlainText(plainCodeText: String) : String =
        plainCodeText
            .split(";")
            .joinTo(buffer = StringBuilder(), separator = "\n")
            .toString()

}