package com.kcg.project.pazpro.service

import com.kcg.project.pazpro.model.Question
import com.kcg.project.pazpro.repository.QuestionRepository
import org.springframework.stereotype.Service

@Service
class QuestionService(
    val questionRepository: QuestionRepository
) {
    fun find(type: Int): Question? =
        questionRepository.findOneById(type)?.let {
            it
        }

    fun findAll(): List<Question?> =
        questionRepository.find()
}