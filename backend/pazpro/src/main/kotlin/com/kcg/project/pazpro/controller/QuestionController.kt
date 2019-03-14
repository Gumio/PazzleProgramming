package com.kcg.project.pazpro.controller

import com.kcg.project.pazpro.model.Question
import com.kcg.project.pazpro.service.QuestionService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/question")
class QuestionController(
    val questionService: QuestionService
) {
    @CrossOrigin
    @GetMapping("")
    fun find(): List<QuestionResponse> =
        questionService.findAll().map { it?.toResponse() ?: dummy(1) }

    @CrossOrigin
    @GetMapping("/{id}")
    fun select(@PathVariable id: Int): QuestionResponse =
        questionService.find(id)?.toResponse2() ?: dummy(1)

    data class QuestionResponse(
        val id: Int,
        val title: String,
        val content: String
    )

    fun Question.toResponse(): QuestionResponse = QuestionResponse(this.id, this.title, this.desc)
    fun Question.toResponse2(): QuestionResponse = QuestionResponse(this.id, this.title, this.content)

    private fun dummy(id: Int): QuestionResponse =
        QuestionResponse(1, "hoge", "fuga")
}