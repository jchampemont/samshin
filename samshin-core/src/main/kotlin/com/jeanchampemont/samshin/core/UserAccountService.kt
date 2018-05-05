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

import com.jeanchampemont.samshin.model.UserAccount
import com.jeanchampemont.samshin.persistence.UserAccountRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

interface UserAccountService {
    fun create(email: String, password: String): UserAccount
}

@Service
class UserAccountServiceImpl(
        private val repository: UserAccountRepository,
        private val passwordEncoder: PasswordEncoder
) : UserAccountService {

    override fun create(email: String, password: String) = repository.save(UserAccount(userId = UUID.randomUUID(), email = email, password = passwordEncoder.encode(password)))

}