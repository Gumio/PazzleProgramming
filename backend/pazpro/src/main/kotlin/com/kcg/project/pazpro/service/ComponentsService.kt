package com.kcg.project.pazpro.service

import com.kcg.project.pazpro.controller.ComponentsController
import com.kcg.project.pazpro.model.Components
import com.kcg.project.pazpro.repository.ComponentsRepository
import org.springframework.stereotype.Service

@Service
class ComponentsService(
    private val componentsRepository: ComponentsRepository
) {
    fun find(type: Int): Components? =
        componentsRepository.findOneByType(type)?.let {
            it
        }

    fun findAll(): List<ComponentsController.ComponentsResponse> {
        val componentsList: MutableList<ComponentsController.ComponentsResponse> = mutableListOf()
        componentsRepository.find().forEach {components ->
            components?.let {
                componentsList.add(ComponentsController.ComponentsResponse(it.type, it.name))
            }
        }

        return componentsList
    }
}