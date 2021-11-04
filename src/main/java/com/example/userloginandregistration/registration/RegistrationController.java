package com.example.userloginandregistration.registration;

import com.example.userloginandregistration.appuser.AppUser;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }


    @PostMapping
    public String register(@RequestBody RegistrationRequest request){
        return registrationService.register(request);
    }

    @GetMapping
    public List<AppUser> getAllUsers(){
        return registrationService.getAllUsers();
    }

    @GetMapping("/{token}")
    public void confirmAppUser(@PathVariable String token){
        registrationService.confirmToken(token);
    }

}
