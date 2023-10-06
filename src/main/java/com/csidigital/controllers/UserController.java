package com.csidigital.controllers;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.csidigital.models.*;
import com.csidigital.payload.request.CompletProfile;
import com.csidigital.payload.response.MessageResponse;
import com.csidigital.repository.CustomerRepository;
import com.csidigital.repository.RoleRepository;
import com.csidigital.repository.UserRepository;
import com.csidigital.security.jwt.JwtUtils;
import com.csidigital.security.services.UserDetailsImpl;
import com.csidigital.service.Imp.UserServiceImp;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private UserServiceImp userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private CustomerRepository CustomerRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//Ajouter un role
    @PutMapping("/customers/{customerId}/role")
    public ResponseEntity<?> updateCustomerRole(@PathVariable Long customerId, @RequestParam ERole roleName, Authentication authentication) {
        // Get the authenticated user's ID
        Long userId = ((UserDetailsImpl) authentication.getPrincipal()).getId();

        // Find the customer by ID
        Customer customer = CustomerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        // Check if the authenticated user is the owner of the customer
        if (!customer.getUser().getId().equals(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse(false, "You are not authorized to update this customer"));
        }

        // Find the role by name
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new EntityNotFoundException("Role not found"));

        // Update the customer's roles
        customer.getRoles().clear();
        customer.getRoles().add(role);
        CustomerRepository.save(customer);

        return ResponseEntity.ok(new ApiResponse(true, "Customer role updated successfully"));
    }
    @PostMapping("/completeProfile")
    public ResponseEntity<?> completeProfile(@Valid @RequestBody CompletProfile completProfileRequest) {
        // Get the user object by username from the database
        Optional<User> optionalUser = userRepository.findByUsername(completProfileRequest.getUsername());
        Optional<Customer> optionalCustomer = CustomerRepository.findByUsername(completProfileRequest.getUsername());

        // Update the user object with the new profile information
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
        user.setFullname(completProfileRequest.getFullname());
        user.setPhone(completProfileRequest.getPhone());
        user.setAdresse(completProfileRequest.getAdresse());
        user.setWebsite(completProfileRequest.getWebsite());
        userRepository.save(user);
        }else if (optionalCustomer.isPresent()) {
                Customer customer = optionalCustomer.get();
        // Get the customer object associated with the user, if any

            // Update the customer object with the new profile information
            customer.setFullname(completProfileRequest.getFullname());
            customer.setPhone(completProfileRequest.getPhone());
            customer.setAdresse(completProfileRequest.getAdresse());
            CustomerRepository.save(customer);
        }

        return ResponseEntity.ok(new MessageResponse("Your profile has been updated successfully."));
    }


    @PostMapping("/{userId}/image")
    public ResponseEntity<?> uploadImage(@PathVariable Long userId, @RequestParam("file") MultipartFile file) {
        try {
            // Get the user or customer object by ID from the database
            User user = userRepository.findById(userId).orElse(null);
            Customer customer = CustomerRepository.findById(userId).orElse(null);

            // Check if the user or customer exists
            if (user == null && customer == null) {
                return ResponseEntity.notFound().build();
            }

            // Check if the file is not empty and is an image
            if (file.isEmpty() || !file.getContentType().startsWith("image/")) {
                return ResponseEntity.badRequest().build();
            }

            // Save the image to a file on the server
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String uploaddir = "user-images/" + userId;
            String fileExtension = fileName.substring(fileName.lastIndexOf("."));
            String newFileName = UUID.randomUUID().toString() + fileExtension;
            Path uploadPath = Paths.get(uploaddir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = file.getInputStream()) {
                Path filePath = uploadPath.resolve(newFileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            }

            // Update the user or customer object with the new image file name
            if (user != null) {
                user.setImage(newFileName);
                userRepository.save(user);
            }
            if (customer != null) {
                customer.setImage(newFileName);
                CustomerRepository.save(customer);
            }

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/{userId}/image")
    public ResponseEntity<byte[]> getImage(@PathVariable Long userId) {
        // Get the user or customer object by ID from the database
        User user = userRepository.findById(userId).orElse(null);
        Customer customer = CustomerRepository.findById(userId).orElse(null);

        // Check if the user or customer exists and has an image
        if ((user == null || user.getImage() == null) && (customer == null || customer.getImage() == null)) {
            return ResponseEntity.notFound().build();
        }

        try {
            // Read the image file from disk
            String imageName;
            if (user != null) {
                imageName = user.getImage();
            } else {
                imageName = customer.getImage();
            }
            Path imagePath = Paths.get("user-images/" + userId + "/" + imageName);
            byte[] imageBytes = Files.readAllBytes(imagePath);

            // Set the response headers to indicate the image content type
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);

            // Return the image data as a ResponseEntity with the appropriate headers
            return new ResponseEntity<byte[]>(imageBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return ResponseEntity.ok(roles);
    }


}
