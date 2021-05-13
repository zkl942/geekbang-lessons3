package springboot.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.service.NumberService;

@RestController
@RequestMapping(path = "/number", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class NumberController {

    @Autowired
    private NumberService numberService;

    @GetMapping(path = "/square/{number}")
    public String getThing(@PathVariable Long number) {
        return String.format("{\"square\": %s}", numberService.square(number));
    }
}
