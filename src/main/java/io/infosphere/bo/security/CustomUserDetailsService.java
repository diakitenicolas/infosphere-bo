package io.infosphere.bo.security;


import io.infosphere.bo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        var user = userOptional.get();
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        if (null != user.getFunction()) {
//            authorities = user.getFunction().getPermissions()
//                    .stream()
//                    .map(InvestigationPermission::name)
//                    .map(SimpleGrantedAuthority::new)
//                    .collect(Collectors.toList());
//        }
        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
