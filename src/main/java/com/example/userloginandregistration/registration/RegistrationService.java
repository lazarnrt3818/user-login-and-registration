package com.example.userloginandregistration.registration;

import com.example.userloginandregistration.appuser.AppUser;
import com.example.userloginandregistration.appuser.AppUserRole;
import com.example.userloginandregistration.appuser.AppUserService;
import com.example.userloginandregistration.registration.token.ConfirmationToken;
import com.example.userloginandregistration.registration.token.ConfirmationTokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RegistrationService {

    private final EmailValidator emailValidator;
    private final AppUserService appUserService;
    private final ConfirmationTokenService confirmationTokenService;


    public RegistrationService(EmailValidator emailValidator, AppUserService appUserService, ConfirmationTokenService confirmationTokenService) {
        this.appUserService = appUserService;
        this.emailValidator = emailValidator;
        this.confirmationTokenService = confirmationTokenService;
    }

    public String register(RegistrationRequest request){
        if(!emailValidator.test(request.email()))
            throw new IllegalStateException(String.format("Email %s not valid.",request.email()));

        return appUserService.signUpUser(
                new AppUser(
                        request.firstName(),
                        request.lastName(),
                        request.email(),
                        request.password(),
                        AppUserRole.USER
                )
        );
    }

    public List<AppUser> getAllUsers() {
        return appUserService.getAllUsers();
    }

    @Transactional
    public void confirmToken(String token){
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token)
                .orElseThrow(() -> new IllegalStateException("Token not found."));

        if(confirmationToken.getConfirmedAt()!=null)
            throw new IllegalStateException("Token already confirmed.");

        LocalDateTime expiresAt = confirmationToken.getExpiresAt();
        if (LocalDateTime.now().isAfter(expiresAt))
            throw new IllegalStateException("Token already expired");

        confirmationTokenService.setConfirmedAt(token);
        appUserService.enableAppUser(
                confirmationToken.getAppUser().getEmail()
        );
    }
}
