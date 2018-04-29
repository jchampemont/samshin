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
import org.springframework.stereotype.Service
import java.util.*

interface UserAccountService {
    fun create(login: String, email: String): UserAccount
    fun get(userId: String): UserAccount?
    fun update(userId: String, login: String?, email: String?)
}

@Service
class UserAccountServiceImpl(val repository: UserAccountRepository) : UserAccountService {

    override fun create(login: String, email: String): UserAccount {
        return repository.save(UserAccount(userId = UUID.randomUUID(), login = login, email = email))
    }

    override fun get(userId: String): UserAccount? {
        return repository.findById(userId).orElse(null)
    }

    override fun update(userId: String, login: String?, email: String?) {
        repository.findById(userId)
                .map {
                    it.login = login ?: it.login
                    it.email = email ?: it.email
                    it
                }
                .ifPresent({ repository.save(it) })
    }

}