package org.example.mangbo.client;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "UserClient", url = "${feign.user-management-api.addr}")
@ConditionalOnProperty(value = "feign.user-management-api.addr")
public interface UserClient {

    @DeleteMapping("/user-info/delete/{userId}")
    ResponseEntity<Void> quew(@PathVariable("userId") Long userId);
}
