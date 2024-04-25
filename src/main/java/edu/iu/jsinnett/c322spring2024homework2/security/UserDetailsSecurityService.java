package edu.iu.jsinnett.c322spring2024homework2.security;





import edu.iu.jsinnett.c322spring2024homework2.model.Customer;
import edu.iu.jsinnett.c322spring2024homework2.repository.CustomerRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsSecurityService implements UserDetailsService {
    CustomerRepository customerRepository;

    public UserDetailsSecurityService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByUsername(username);
        if (customer == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return User.withUsername(customer.getUsername())
                .password(customer.getPassword())
                .authorities("USER") // Specify the user's authorities
                .build();
    }
}