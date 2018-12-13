package com.kcg.project.pazpro.controller

import com.kcg.project.pazpro.service.ExecuteService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/execute")
class ExecuteController(
    val executeService: ExecuteService
) {
    @GetMapping("")
    fun execute(@ModelAttribute code: String): String =
        executeService.tryExecute(code)
}