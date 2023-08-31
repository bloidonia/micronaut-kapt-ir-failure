package com.micronaut.test

import io.micronaut.context.annotation.Context
import io.micronaut.context.annotation.Factory
import io.micronaut.runtime.event.annotation.EventListener
import io.micronaut.runtime.server.event.ServerShutdownEvent
import io.micronaut.runtime.server.event.ServerStartupEvent
import jakarta.annotation.PostConstruct
import jakarta.inject.Inject
import io.github.oshai.kotlinlogging.KotlinLogging

@Factory
@Context
class ApplicationFactory {

	private val log = KotlinLogging.logger {}

	@Inject
	lateinit var config: Config

	@PostConstruct
	fun init() {
		log.info { "init" }
	}

	@EventListener
	fun onStart(event: ServerShutdownEvent) {
		log.info { "start" }
		log.info { "config:$config"}
	}

	@EventListener
	fun onStart(event: ServerStartupEvent) {
		log.info { "startup" }
		log.info { "config:$config"}
	}
}
