package cz.legas.ss.grantedauthority.demo

import cz.legas.ss.grantedauthority.demo.auth.CustomAuthentication
import cz.legas.ss.grantedauthority.demo.auth.CustomGrantedAuthority
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.test.context.support.WithSecurityContext
import org.springframework.security.test.context.support.WithSecurityContextFactory
import org.springframework.test.web.reactive.server.WebTestClient

@AutoConfigureWebTestClient(timeout = "PT1M")
@SpringBootTest
class ApplicationTests {

	@Autowired
	private lateinit var webClient: WebTestClient

	@Test
	@MockCustomUser
	fun `can access demo endpoint`() {
		val result = webClient
			.get()
			.uri("/demo")
			.exchange()
			.expectStatus().is2xxSuccessful
			.expectBody(String::class.java)
			.returnResult().responseBody!!

		Assertions.assertThat(result).isEqualTo("Hi!")
	}
}

@Retention(AnnotationRetention.RUNTIME)
@WithSecurityContext(factory = MockCustomUserSecurityContextFactory::class)
annotation class MockCustomUser

class MockCustomUserSecurityContextFactory : WithSecurityContextFactory<MockCustomUser> {
	override fun createSecurityContext(
		customUser: MockCustomUser
	): SecurityContext = SecurityContextHolder.createEmptyContext().also {
		it.authentication = CustomAuthentication(
			authorities = setOf(CustomGrantedAuthority.USER_AUTHORITY),
			authenticated = true
		)
	}
}
