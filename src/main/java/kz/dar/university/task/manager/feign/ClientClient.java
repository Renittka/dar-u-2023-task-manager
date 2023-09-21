package kz.dar.university.task.manager.feign;

import kz.dar.university.task.manager.domain.dto.ClientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "client-core-api")
public interface ClientClient {

    @GetMapping("/{clientId}")
    ClientDTO getClientById(@PathVariable String clientId);

}
