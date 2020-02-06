package gov.tn.taxecommune.service.userDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import gov.tn.taxecommune.entity.User;
import gov.tn.taxecommune.service.UserService;

/**
 * Created by Keno&Kemo on 18.02.2018..
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
    private UserService userService;
    
    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return new UserDetailsImpl(user);
    }
}
