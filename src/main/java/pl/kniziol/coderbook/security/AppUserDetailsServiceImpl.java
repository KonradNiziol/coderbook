package pl.kniziol.coderbook.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.kniziol.coderbook.exception.AppSecurityException;
import pl.kniziol.coderbook.repository.UserRepository;

import java.util.List;

@Service
@Qualifier("AppUserDetailsServiceImpl")
@RequiredArgsConstructor
public class AppUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .map(userFromDB -> new User(
                        userFromDB.getEmail(),
                        userFromDB.getPassword(),
                        userFromDB.getEnabled(), true, true, true,
                        List.of(new SimpleGrantedAuthority(userFromDB.getRole().getName()))
                )).orElseThrow(() -> new AppSecurityException("cannot find user with email " + email));
    }
}
