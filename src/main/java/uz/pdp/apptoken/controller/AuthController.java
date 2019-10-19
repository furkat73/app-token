package uz.pdp.apptoken.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.apptoken.payload.JwtToken;
import uz.pdp.apptoken.payload.ReqLogin;
import uz.pdp.apptoken.payload.ReqRegister;
import uz.pdp.apptoken.security.AuthService;
import uz.pdp.apptoken.security.JwtTokenProvider;

@Controller
@RequestMapping("/api/auth")
public class AuthController {


    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody ReqLogin reqLogin) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(reqLogin.getUsername(), reqLogin.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtToken(token,"Bearer "));
    }

    @PostMapping("/register")
    public HttpEntity<?>signup(@RequestBody ReqRegister reqRegister) {
        boolean register = authService.register(reqRegister);
        if(register){
            return ResponseEntity.ok("Registraciya otdi");

        }
        return ResponseEntity.status(409).body("Royxatdan otaolmadiz");
    }

}
