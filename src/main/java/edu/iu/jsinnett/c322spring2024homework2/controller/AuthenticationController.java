package edu.iu.jsinnett.c322spring2024homework2.controller;




import edu.iu.jsinnett.c322spring2024homework2.model.Customer;
import edu.iu.jsinnett.c322spring2024homework2.repository.CustomerRepository;
import edu.iu.jsinnett.c322spring2024homework2.security.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final CustomerRepository customerRepository;
    public AuthenticationController(AuthenticationManager authenticationManager,
                                    TokenService tokenService,
                                    CustomerRepository customerRepository) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.customerRepository = customerRepository;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Customer customer) {
        if (customerRepository.findByUsername(customer.getUsername()) != null) {
            // User already exists
            return new ResponseEntity<>("Username already exists", HttpStatus.CONFLICT);
        }
        // Encode the password
        customer.setPassword(new BCryptPasswordEncoder().encode(customer.getPassword()));
        customerRepository.save(customer);
        // Return the appropriate status, for example:
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> login(@RequestBody Customer customer) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            customer.getUsername(),
                            customer.getPassword()
                    )
            );
            String token = tokenService.generateToken(authentication);
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed: " + e.getMessage());
        }
    }

}