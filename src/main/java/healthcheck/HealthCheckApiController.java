package healthcheck;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckApiController {

    @ResponseStatus(HttpStatus.OK)
    public String returnHealthy() {
        return "I AM HEALTHY!";
    }
}
