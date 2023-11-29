package Services;

import Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import security.UsDetails;

import java.util.Optional;

@Component
public class AuthDetailsService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public AuthDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userService.getUserByEmail(username);

        if (user.isEmpty()) {
            System.out.println("hello user");
            throw new UsernameNotFoundException("User not found");
        }

        return new UsDetails(user.get());
    }
}
