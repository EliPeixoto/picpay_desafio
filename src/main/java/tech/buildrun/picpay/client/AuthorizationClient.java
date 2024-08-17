package tech.buildrun.picpay.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import tech.buildrun.picpay.client.dto.AuthorizationResponseDto;

@FeignClient(
        name = "AuthorizationClient",
        url = "${client.authorization-service.url}")
public interface AuthorizationClient {

    @GetMapping
    ResponseEntity<AuthorizationResponseDto>isAuthorized();
}
