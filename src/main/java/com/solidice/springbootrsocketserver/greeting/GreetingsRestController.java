package com.solidice.springbootrsocketserver.greeting;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsRestController {

    @Autowired
    private RSocketRequester requester;

    @GetMapping("/greet/{name}")
    public Publisher<GreetingsResponse> greet(@PathVariable String name) {
        GreetingsRequest request = new GreetingsRequest(name);
        return requester
            .route("greet")
            .data(request)
            .retrieveMono(GreetingsResponse.class);
    }


    @GetMapping(value = "/greet-stream/{name}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Publisher<GreetingsResponse> greetStream(@PathVariable String name) {
        return requester
            .route("greet-stream")
            .data(new GreetingsRequest(name))
            .retrieveFlux(GreetingsResponse.class);
    }
}
