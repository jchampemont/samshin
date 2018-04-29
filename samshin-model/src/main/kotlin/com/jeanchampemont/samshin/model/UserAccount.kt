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
package com.jeanchampemont.samshin.model

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.couchbase.core.mapping.Document
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy.USE_ATTRIBUTES
import org.springframework.data.couchbase.core.mapping.id.IdAttribute
import java.util.*
import javax.validation.constraints.Email
import javax.validation.constraints.Size

@Document
data class UserAccount(
        @field:Id @field:GeneratedValue(strategy = USE_ATTRIBUTES)
        var id: String? = null,

        @field:IdAttribute
        var userId: UUID,

        @field:Version
        var version: Long? = null,

        @field:Size(min = 3, max = 128)
        var login: String,

        @field:Email
        @field:Size(min = 3, max = 128)
        var email: String
)