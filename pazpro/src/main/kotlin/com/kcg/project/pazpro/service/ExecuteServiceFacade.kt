package com.kcg.project.pazpro.service

import org.springframework.stereotype.Service
import java.lang.StringBuilder

@Service
class ExecuteServiceFacade(
    private val dockerService: DockerService,
    private val parseService: ParseService
) {
    fun execute(plainCodeText: String): String {
        val code = structuringToPlainText(plainCodeText)
        println("code -> $code")
        parseService.parsing(code)
        dockerService.createMainFile(code)
        return dockerService.runCode()
    }

    private fun structuringToPlainText(plainCodeText: String) : String =
        plainCodeText
            .split(";")
            .joinTo(buffer = StringBuilder(), separator = "\n")
            .toString()

}