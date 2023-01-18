package com.rover.model

sealed class Either<out L, out R>
data class Result<out L>(val result: L) : Either<L, Nothing>()
data class Error<out R>(val error: R) : Either<Nothing, R>()