package fr.brenard.blogs.services.impl;

import fr.brenard.blogs.exceptions.InvalidCredentialsException;
import fr.brenard.blogs.models.DTOs.AuthDTO;
import fr.brenard.blogs.models.forms.users.LoginForm;
import fr.brenard.blogs.services.LoginService;
import fr.brenard.blogs.tools.JWTProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;


@Service
public class LoginServiceImpl implements LoginService {


    private final JWTProvider jwtProvider;
    private final AuthenticationManager authenticationManager;

    public LoginServiceImpl(JWTProvider jwtProvider, AuthenticationManager authenticationManager) {
        this.jwtProvider = jwtProvider;
        this.authenticationManager = authenticationManager;
    }


    @Override
    public AuthDTO login(LoginForm form) {
        try{
            Authentication auth = new UsernamePasswordAuthenticationToken(form.getUsername(),form.getPassword());
            auth = authenticationManager.authenticate(auth);
            return new AuthDTO(auth.getName(), jwtProvider.createToken(auth));
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new InvalidCredentialsException();
        }
    }
}
