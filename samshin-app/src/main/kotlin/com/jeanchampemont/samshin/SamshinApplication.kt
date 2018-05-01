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
package com.jeanchampemont.samshin

import com.jeanchampemont.samshin.core.UserAccountService
import org.springframework.beans.factory.InitializingBean
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import org.springframework.data.convert.WritingConverter
import org.springframework.data.couchbase.config.BeanNames
import org.springframework.data.couchbase.core.convert.CouchbaseCustomConversions
import org.springframework.data.couchbase.core.mapping.event.ValidatingCouchbaseEventListener
import org.springframework.data.couchbase.repository.support.IndexManager
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean
import java.util.*


@SpringBootApplication
class SamshinApplication {
    @Bean
    fun initialize(accountService: UserAccountService) = InitializingBean { accountService.create("toto", "toto@samshin.com") }

    @Bean(name = [BeanNames.COUCHBASE_INDEX_MANAGER])
    fun indexManager() = IndexManager(true, false, false)

    @Bean
    fun validator() = LocalValidatorFactoryBean()

    @Bean
    fun validationEventListener() = ValidatingCouchbaseEventListener(validator())

    @Bean(name = [BeanNames.COUCHBASE_CUSTOM_CONVERSIONS])
    fun customConversions() = CouchbaseCustomConversions(listOf(UUIDReadingConverter, UUIDWritingConverter))
}

@WritingConverter
object UUIDWritingConverter : Converter<UUID, String> {
    override fun convert(source: UUID): String? = source.toString()
}

@ReadingConverter
object UUIDReadingConverter : Converter<String, UUID> {
    override fun convert(source: String): UUID? = UUID.fromString(source)
}

fun main(args: Array<String>) {
    runApplication<SamshinApplication>(*args)
}

