package com.kcg.project.pazpro.controller

import com.kcg.project.pazpro.service.ExecuteServiceFacade
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/execute")
class ExecuteController(
    val executeServiceFacade: ExecuteServiceFacade
) {
    @GetMapping("")
    fun execute(): String {
        val code = """
            for i in 1..30
                if i%15==0
                    puts "FizzBuzz!"
                elsif i%3==0
                    puts "Fizz!"
                elsif i%5==0
                    puts "Buzz!"
                else
                    puts i
                end
            end
        """.trimIndent()
        return executeServiceFacade.execute(code)
    }
}