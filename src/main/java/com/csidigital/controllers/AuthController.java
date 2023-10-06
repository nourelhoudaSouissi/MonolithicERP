package com.csidigital.controllers;



import com.csidigital.exception.ResourceNotFoundException;
import com.csidigital.payload.request.*;

import java.io.IOException;

import java.util.*;
import java.util.stream.Collectors;

import com.csidigital.repository.PartnerRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.csidigital.Utils.EmailService;

import com.csidigital.models.*;
import com.csidigital.payload.response.JwtResponse;
import com.csidigital.payload.response.MessageResponse;
import com.csidigital.payload.response.TokenRefreshResponse;
import com.csidigital.payload.response.UserResponse;
import com.csidigital.repository.CustomerRepository;
import com.csidigital.repository.RoleRepository;
import com.csidigital.repository.UserRepository;
import com.csidigital.security.jwt.JwtUtils;
import com.csidigital.security.services.RefreshTokenService;
import com.csidigital.security.services.UserDetailsImpl;
import com.csidigital.service.Imp.UserServiceImp;

import jakarta.mail.MessagingException;


import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.web.bind.annotation.*;

import com.csidigital.exception.TokenRefreshException;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Value("${bezkoder.app.jwtSecret}")
  private  String SECRET_KEY ;
  @Autowired
  private JavaMailSender javaMailSender;
  @Autowired
  AuthenticationManager authenticationManager;
  @Autowired
  private EmailService emailService;
  @Autowired
  private PartnerRepository partnerRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  CustomerRepository CustomerRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @Autowired
  RefreshTokenService refreshTokenService;

  @Autowired
  private UserServiceImp userService;

  @Autowired
  private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();



  @PostMapping("/signup/customer")
  public ResponseEntity<?> registerCustomer(@RequestBody SignupRequest signupRequest) {
    //Verification si le mail existe ou nn dans le repo user et custumer
    if (userRepository.existsByEmail(signupRequest.getEmail())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Email is already in use!"));
    }
    //Test par username exist ou pas dans le repo user et custumer
    if (CustomerRepository.existsByUsername(signupRequest.getUsername())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Username is already in use!"));
    }

    if (userRepository.existsByUsername(signupRequest.getUsername())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Username is already in use!"));
    }

// Create new customer's account et lui associer un role ( custumer par defaut)
    User user = new User(signupRequest.getUsername(),
            signupRequest.getEmail(), passwordEncoder.encode(signupRequest.getPassword()));
    Set<Role> roles = new HashSet<>();
    Role userRole = roleRepository.findByName(ERole.ROLE_CUSTOMER)
            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    roles.add(userRole);
    user.setRoles(roles);
    user.setConfirmationToken(UUID.randomUUID().toString());
    userRepository.save(user);
    // l'url de l'@ Ip du serveur
    String confirmationUrl = "http://localhost:4200/sessions/signup?confirmationToken=" + user.getConfirmationToken();
    String message = "To confirm your account, please click here: " + confirmationUrl;

    emailService.sendSimpleMessage(new Email(null,signupRequest.getEmail(), "Account Confirmation", message));

    return ResponseEntity.ok(new MessageResponse("A confirmation email has been sent to your email address."));
  }


  @PostMapping("/signup/customer-user")
  public ResponseEntity<?> registerCustomerUser(@RequestBody SignupRequest signupRequest, @RequestParam Long customerId) {
    if (userRepository.existsByEmail(signupRequest.getEmail())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Email is already in use!"));
    }
    if (userRepository.existsByUsername(signupRequest.getUsername())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Username is already in use!"));
    }
    if (CustomerRepository.existsByUsername(signupRequest.getUsername())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Username is already in use!"));
    }

    User user = userRepository.findById(customerId)
            .orElseThrow(() -> new RuntimeException("Error: Customer not found."));

    // Create new customer user's account
    Customer customer = new Customer(signupRequest.getUsername(),
            signupRequest.getEmail(),
            passwordEncoder.encode(signupRequest.getPassword()));
    Set<Role> roles = new HashSet<>();
    Role customerUserRole = roleRepository.findByName(ERole.ROLE_CUSTOMER_USER)
            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    roles.add(customerUserRole);
    customer.setRoles(roles);

    customer.setUser(user);
    customer.setConfirmationToken(UUID.randomUUID().toString());
    CustomerRepository.save(customer);

    String confirmationUrl = "http://localhost:4200/sessions/signup?confirmationToken=" + customer.getConfirmationToken();
    String message = "Your User "+customer.getUsername()+"Your Email is :"+customer.getEmail()+"--Yor Password is :"+signupRequest.getPassword()+ "your Provider is"+customer.getUser().getUsername() +"To confirm your account, please click here: " + confirmationUrl;

    emailService.sendSimpleMessage(new Email(null,signupRequest.getEmail(), "Account Confirmation", message));

    return ResponseEntity.ok(new MessageResponse("A confirmation email has been sent to your email address."));
  }






  @GetMapping("/{username}")
  public ResponseEntity<UserResponse> getbyusername(@PathVariable("username") String username) {
    Optional<User> optionalUser = userRepository.findByUsername(username);
    Optional<Customer> optionalCustomer = CustomerRepository.findByUsername(username);

    if (optionalUser.isPresent()) {
      User user = optionalUser.get();
      UserResponse response = new UserResponse(user.getId(),
              user.getUsername(), user.getImage(), user.getEmail(), user.getFullname(), user.getPhone(), user.getAdresse(), user.getWebsite());
      return ResponseEntity.ok(response);
    } else if (optionalCustomer.isPresent()) {
      Customer customer = optionalCustomer.get();
      UserResponse response = new UserResponse(customer.getId(),
              customer.getUsername(), customer.getImage(), customer.getEmail(), customer.getFullname(), customer.getPhone(), customer.getAdresse(), null);
      return ResponseEntity.ok(response);
    } else {
      throw new RuntimeException("Error: User/Customer not found.");
    }
  }


  @GetMapping("/findbyid/{id}")
  public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long id) {
    User user = userService.GetbyId(id);
    if (user == null) {
      return ResponseEntity.notFound().build();
    } else {
      UserResponse response = new UserResponse(id, user.getUsername(), user.getImage(), user.getEmail(), user.getFullname(), user.getPhone(), user.getAdresse(), user.getWebsite());
      return ResponseEntity.ok(response);
    }
  }
// Conrmation de l' account
  @GetMapping("/confirm")
  public ResponseEntity<?> confirmAccount(@RequestParam String confirmationToken) {
    User user = userRepository.findByConfirmationToken(confirmationToken);
    Customer customer = CustomerRepository.findByConfirmationToken(confirmationToken);
    if (user != null) {
      user.setConfirme(true);
      user.setConfirmationToken(null);
      userRepository.save(user);
      return ResponseEntity.ok(new MessageResponse("User confirmed"));
    } else if (customer != null) {
      customer.setConfirme(true);
      customer.setConfirmationToken(null);
      CustomerRepository.save(customer);
      return ResponseEntity.ok(new MessageResponse("Customer confirmed"));
    } else {
      return ResponseEntity.badRequest().body(new MessageResponse("Invalid or expired token"));
    }
  }

// Forget PW
  @PostMapping("/forgot-password")
  public ResponseEntity<?> forgotPassword(@RequestParam String email) throws MessagingException {
    User user = userRepository.findByEmail(email);
    Customer customer = CustomerRepository.findByEmail(email);
    if (user == null && customer == null) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Email not valid or not registered!"));
    }
    String passwordResetToken = UUID.randomUUID().toString();
    if (user != null) {
      user.setPasswordResetToken(passwordResetToken);
      userRepository.save(user);
    }
    if (customer != null) {
      customer.setPasswordResetToken(passwordResetToken);
      CustomerRepository.save(customer);
    }
    String resetUrl = "http://localhost:4200/sessions/reset-pass?passwordResetToken=" + passwordResetToken;
    String message = "To reset your password, please click here: " + resetUrl;
    emailService.sendSimpleMessage(new Email(null, email, "Password Reset Request", message));
    return ResponseEntity.ok(new MessageResponse("A password reset email has been sent to your email address."));
  }

  @PostMapping("/reset-password")
  public ResponseEntity<?> resetPassword(@RequestParam String passwordResetToken, @RequestParam String password) {
    User user = userRepository.findByPasswordResetToken(passwordResetToken);
    Customer customer = CustomerRepository.findByPasswordResetToken(passwordResetToken);
    if (user == null && customer == null) {
      return ResponseEntity.badRequest().body(new MessageResponse("Invalid or expired token"));
    }
    if (user != null) {
      user.setPassword(encoder.encode(password));
      user.setPasswordResetToken(null);
      userRepository.save(user);
    }
    if (customer != null) {
      customer.setPassword(encoder.encode(password));
      customer.setPasswordResetToken(null);
      CustomerRepository.save(customer);
    }
    return ResponseEntity.ok(new MessageResponse("Password reset successfully!"));
  }
  //User affiche son custumer
  @GetMapping("/profile")
  public ResponseEntity<?> getProfile(@RequestHeader(name = "Authorization") String authHeader) {
    String jwtToken = authHeader.substring(7); // remove "Bearer " prefix from token
    if (jwtUtils.validateJwtToken(jwtToken)) {
      String username = jwtUtils.getUserNameFromJwtToken(jwtToken);
      // fetch user profile using the username from token
      Optional<User> userProfile = userRepository.findByUsername(username);
      return ResponseEntity.ok(userProfile);
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
  }
  @PostMapping("/refreshtoken")
  public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
    String requestRefreshToken = request.getRefreshToken();

    return refreshTokenService.findByToken(requestRefreshToken)
            .map(refreshTokenService::verifyExpiration)
            .map(RefreshToken::getUser)
            .map(user -> {
              String token = jwtUtils.generateTokenFromUsernameAndEmail(user.getUsername(),user.getEmail());
              return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
            })
            .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                    "Refresh token is not in database!"));
  }

  @PostMapping("/signout")
  public ResponseEntity<?> logoutUser() {
    UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    Long userId = userDetails.getId();
    refreshTokenService.deleteByUserId(userId);
    return ResponseEntity.ok(new MessageResponse("Log out successful!"));
  }



//validation du token (duree expirer ou nn )
  @GetMapping("/verifyToken")
  public ResponseEntity<String> verifyToken(@RequestParam("token") String token) {
    // Check if the token is valid
    if (jwtUtils.validateJwtToken(token)) {
      return ResponseEntity.ok("Token is valid");
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token is not valid");
    }
  }


  @GetMapping("/user")
  public ResponseEntity<?> getUserFromToken(@RequestHeader("Authorization") String tokenHeader) {
    try {
      String token = tokenHeader.substring(7);
      boolean isValid = jwtUtils.validateJwtToken(token);
      if (isValid) {
        String username = jwtUtils.getUserNameFromJwtToken(token);
        return ResponseEntity.ok(new MessageResponse("Username: " + username));
      } else {
        return ResponseEntity.badRequest().body(new MessageResponse("Invalid token"));
      }
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: " + e.getMessage()));
    }
  }

  @GetMapping("/{userId}/customers")
  public List<Customer> getAllCustomersForUser(@PathVariable Long userId, HttpServletResponse response) throws IOException, IOException {
    Optional<User> user = userRepository.findById(userId);
    if (!user.isPresent()) {
      response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
      return null;
    }
    return CustomerRepository.findByUser(user.get());
  }
  @DeleteMapping("/{userId}/customers/{customerId}")
  public ResponseEntity<?> deleteCustomer(@PathVariable("userId") Long userId, @PathVariable("customerId") Long customerId) {
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    Customer customer = user.getCustomers().stream()
            .filter(c -> c.getId().equals(customerId))

            .findFirst()
            .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
    System.out.println(customerId);
    // Delete all refresh tokens associated with the customer
    refreshTokenService.deleteByCustomerId(customerId);


    user.getCustomers().remove(customer);
    userRepository.save(user);

    return ResponseEntity.ok().build();
  }

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
    Authentication authentication;
    try {
      //authenticationManager biblio spring security
      authentication = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
      System.out.println(authentication);
      System.out.println(loginRequest.getPassword());
    } catch (BadCredentialsException ex) {
      return ResponseEntity
              .status(HttpStatus.UNAUTHORIZED)
              .body(new MessageResponse("Error: Invalid username or password"));
    }

    SecurityContextHolder.getContext().setAuthentication(authentication);
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

    String jwt = jwtUtils.generateJwtToken(userDetails);

    List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
            .collect(Collectors.toList());

    RefreshToken refreshToken;
    if (roles.contains("ROLE_CUSTOMER_USER")) {
      Customer customer = CustomerRepository.findByUsername(loginRequest.getUsername())
              .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + loginRequest.getUsername()));

      if (!customer.getConfirme()) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new MessageResponse("Error: Email not confirmed for user: " + loginRequest.getUsername()));
      }

      refreshToken = refreshTokenService.createRefreshTokenForCustomer(customer.getId());
      System.out.println(jwt);
      System.out.println(refreshToken.getToken());
      System.out.println(roles);
      System.out.println(userDetails.getUsername());

      return ResponseEntity.ok(new JwtResponse(jwt,
              refreshToken.getToken(),
              customer.getId(),
              customer.getUsername(),
              roles
      ));
    } else {
      User user = userRepository.findById(userDetails.getId())
              .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + userDetails.getId()));

      if (!user.getConfirme()) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new MessageResponse("Error: Email not confirmed for user: " + user.getUsername()));
      }

      refreshToken = refreshTokenService.createRefreshTokenForUser(user.getId());
      System.out.println(jwt);
      System.out.println(refreshToken.getToken());
      System.out.println(roles);
      System.out.println(userDetails.getUsername());

      return ResponseEntity.ok(new JwtResponse(jwt,
              refreshToken.getToken(),
              userDetails.getId(),
              userDetails.getUsername(),
              roles
      ));
    }
  }


}





