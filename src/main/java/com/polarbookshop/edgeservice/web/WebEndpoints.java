package com.polarbookshop.edgeservice.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Configuration
public class WebEndpoints {

    /**
     * 함수형 REST 엔드포인트가 빈 내부에 정의된다.
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions.route()  // 라우트를 생성하기 위한 플루언트 API를 제공한다.
                .GET("/catalog-fallback", request ->    // GET 엔드포인트에 대한 폴백 응답
                        ServerResponse.ok().body(Mono.just(""), String.class))
                .POST("/catalog-fallback", request ->   // POST 엔드포인트에 대한 폴백 응답
                        ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE).build())
                .build();   // 함수형 엔드포인트롤 만든다.
    }

}
