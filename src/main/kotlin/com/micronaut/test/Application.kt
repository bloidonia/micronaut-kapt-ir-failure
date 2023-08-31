package com.micronaut.test

import io.micronaut.runtime.Micronaut.build

fun main(args: Array<String>) {
	build()
		.args(*args)
		.packages("com.micronaut.test")
		.start()
}