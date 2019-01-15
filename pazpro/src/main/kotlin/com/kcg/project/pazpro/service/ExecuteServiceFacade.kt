package com.kcg.project.pazpro.service

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
        parseService.parsing(code)
        dockerService.createMainFile(code)
        val out = dockerService.runCode(question!!.args)
        println(out.trim())
        println(question.stdout)
        if (out.trim() == question.stdout) {
            return ExecuteController.ExecuteResponse(true, false)
        } else {
            return ExecuteController.ExecuteResponse(false, false)
        }
    }

    private fun structuringToPlainText(plainCodeText: String) : String =
        plainCodeText
            .split(";")
            .joinTo(buffer = StringBuilder(), separator = "\n")
            .toString()

}