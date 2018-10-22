package com.kcg.project.pazpro.service

import com.kcg.project.pazpro.model.Components
import com.kcg.project.pazpro.repository.ComponentsRepository
import org.springframework.stereotype.Service

@Service
class ComponentsService(
    private val componentsRepository: ComponentsRepository
) {
    fun find(id: Int): Components? =
        componentsRepository.findOneById(id)?.let {
            it
        }
}