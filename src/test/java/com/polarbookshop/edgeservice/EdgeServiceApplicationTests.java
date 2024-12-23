package com.polarbookshop.edgeservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@Testcontainers
class EdgeServiceApplicationTests {

	private static final int REDIS_PORT = 6379;

	// 테스트를 위한 레디스 컨테이너 정의
	@Container
	static GenericContainer<?> redis = new GenericContainer<>(DockerImageName.parse("redis:7.0"));

	// 테스트 인스턴스를 사용하도록 레디스 설정 변경
	@DynamicPropertySource
	static void redisProperties(DynamicPropertyRegistry registry) {
		// ()-> 지연평가 : 컨테이너가 만들어지고 레지스트리가 데이터를 필요로할때 redis.getHost() 를 실행
		// redis.getHost()만 넣게 되면 바로 실행되서 컨테이너가 생성되기 전에 실행 될 수 있음
		registry.add("spring.redis.host", () -> redis.getHost());
		registry.add("spring.redis.port", () -> redis.getMappedPort(REDIS_PORT));
	}

	@Test
	void contextLoads() {
		// 애플리케이션 콘텍스트가 올바르게 로드되었는지, 레디스 연결이 성공적으로 됐는지를 확인하기 위한 테스트로 비어 있다.
	}

}
