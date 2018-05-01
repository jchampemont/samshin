/*
 * Copyright (C) 2018 Samshin
 *
 * This file is part of the Samshin project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jeanchampemont.samshin.core

import com.jeanchampemont.samshin.model.Site
import com.jeanchampemont.samshin.persistence.SiteRepository
import org.springframework.stereotype.Service

interface SiteService {
    fun create(code: String, name: String, description: String?): Site

    fun getByCode(code: String): Site?

    fun getAll(): List<Site>
}

@Service
class SiteServiceImpl(private val repository: SiteRepository) : SiteService {
    override fun create(code: String, name: String, description: String?) = repository.save(Site(code = code, name = name, description = description))

    override fun getByCode(code: String) = repository.findByCode(code)

    override fun getAll(): List<Site> = repository.findAll().toCollection(mutableListOf()).toList()
}