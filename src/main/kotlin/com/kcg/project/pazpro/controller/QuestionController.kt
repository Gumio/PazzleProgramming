package com.kcg.project.pazpro.controller

import com.kcg.project.pazpro.model.Question
import com.kcg.project.pazpro.service.QuestionService
import org.springframework.web.bind.annotation.*

@RestController
class QuestionController(
        val questionService: QuestionService
) {
    @GetMapping("/question/{id}")
    fun select(@PathVariable id: Int): QuestionResponse =
        questionService.find(id)?.toResponse() ?: dummy(1)

    data class QuestionResponse(
            val title: String,
            val content: String
    )

    fun Question.toResponse(): QuestionResponse = QuestionResponse(this.title, this.content)

    private fun dummy(id: Int): QuestionResponse =
            QuestionResponse("hoge", "fuga")
}