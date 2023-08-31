package com.micronaut.test

import io.micronaut.context.annotation.ConfigurationProperties

@ConfigurationProperties("app.config")
// With jvm ir enabled, it failed to inject the value of parameter [stringConfig].
data class Config(
	var stringConfig: String = "",
	var booleanConfig: Boolean = true,
	var intConfig: Int = 0,
)

// No problem with jvm ir enabled.
/*class Config {
	var stringConfig: String = ""
	var booleanConfig: Boolean = true
	var intConfig: Int = 0
}*/
