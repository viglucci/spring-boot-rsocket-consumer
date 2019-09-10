package com.solidice.springbootrsocketserver.config;

import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.frame.decoder.PayloadDecoder;
import io.rsocket.transport.ClientTransport;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.transport.netty.client.WebsocketClientTransport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.MetadataExtractor;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.util.MimeTypeUtils;

import java.net.URI;
import java.time.Duration;

@Configuration
public class ClientConfiguration {

    @Bean
    public RSocket rSocket() {
        URI websocketUri = URI.create("ws://127.0.0.1:8080/ws");
        WebsocketClientTransport ws = WebsocketClientTransport.create(websocketUri);
        return RSocketFactory
            .connect()
            .mimeType(
                MetadataExtractor.ROUTING.toString(),
                MimeTypeUtils.APPLICATION_JSON_VALUE)
            .frameDecoder(PayloadDecoder.ZERO_COPY)
            .transport(ws)
            .start()
            .block();
    }

    @Bean
    RSocketRequester rSocketRequester(RSocketStrategies rSocketStrategies) {
        return RSocketRequester.wrap(
            rSocket(),
            MimeTypeUtils.APPLICATION_JSON,
            MetadataExtractor.ROUTING,
            rSocketStrategies);
    }
}
