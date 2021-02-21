package healthcheck;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckApiController {

    @GetMapping("/v1/health-check")
    @ResponseStatus(HttpStatus.OK)
    public String returnHealthy() {
        return "I AM HEALTHY!";
    }
}
