package com.kcg.project.pazpro.service

import org.springframework.stereotype.Service

@Service
class ExecuteServiceFacade(
    private val dockerService: DockerService,
    private val parseService: ParseService
) {
    fun execute(code: String): String {
        parseService.parsing(code)
        dockerService.createMainFile(code)
        return dockerService.runCode()
    }
}