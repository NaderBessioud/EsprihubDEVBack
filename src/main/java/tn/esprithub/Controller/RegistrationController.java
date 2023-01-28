package tn.esprithub.Controller;

import lombok.AllArgsConstructor;
import tn.esprithub.Entities.RegistrationRequest;
import tn.esprithub.Services.RegistrationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/registration")


public class RegistrationController {
	@Autowired
    private  RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }
    @GetMapping(path = "confirm")
    public RedirectView confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}
