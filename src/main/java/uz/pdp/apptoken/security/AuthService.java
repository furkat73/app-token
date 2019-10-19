package uz.pdp.apptoken.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.apptoken.entity.User;
import uz.pdp.apptoken.payload.ReqRegister;
import uz.pdp.apptoken.repository.UserRepository;

import java.util.UUID;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    public boolean register(ReqRegister reqRegister) {
        if (!userRepository.existsUserByUsername(reqRegister.getUsername())) {
            User user = new User();
            user.setFirstName(reqRegister.getFirstName());
            user.setLastName(reqRegister.getLastName());
            user.setUsername(reqRegister.getUsername());
            user.setPassword(passwordEncoder.encode(reqRegister.getPassword()));
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public UserDetails loadUserById(UUID userId){
        return userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException(userId.toString()));
    }



}
