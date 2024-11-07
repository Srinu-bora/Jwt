// package com.example.credentials.controller;

// import com.example.credentials.dto.LoginRequest;
// import com.example.credentials.model.CompanyUser;
// import com.example.credentials.service.CompanyUserService;
// //import com.example.credentials.util.JwtUtil;
// import com.example.credentials.util.JwtUtil;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/company")
// @CrossOrigin(origins = "http://localhost:3000") // Modify as needed for your front-end
// public class CompanyUserController {

//     @Autowired
//     private CompanyUserService companyUserService;

//     // // Register a new company
//     // @PostMapping("/register")
//     // public ResponseEntity<String> registerCompany(@RequestBody CompanyUser
//     // companyUser) {
//     // companyUser.setCompanyId(generateUniqueCompanyId());
//     // String response = companyUserService.registerCompany(companyUser);
//     // return new ResponseEntity<>(response, HttpStatus.CREATED);
//     // }
//     @PostMapping("/register")
//     public ResponseEntity<String> registerCompany(@RequestBody CompanyUser companyUser) {
//         // Check if the username already exists
//         if (companyUserService.usernameExists(companyUser.getUsername())) {
//             return new ResponseEntity<>("Username is already taken.", HttpStatus.BAD_REQUEST);
//         }

//         // If not, proceed with registration
//         companyUser.setCompanyId(generateUniqueCompanyId()); // Generate a unique company ID if needed
//         String response = companyUserService.registerCompany(companyUser);
//         return new ResponseEntity<>(response, HttpStatus.CREATED);
//     }

//     @PostMapping("/login")
//     public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
//     boolean authenticated =
//     companyUserService.authenticate(loginRequest.getUsername(),
//     loginRequest.getPassword());
//     if (authenticated) {
//     String token = JwtUtil.generateToken(loginRequest.getUsername());
//     return ResponseEntity.ok("Login successful!");
//     } else {
//     return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
//     }
//     }

//     // @PostMapping("/login")
//     // public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
//     // boolean authenticated =
//     // companyUserService.authenticate(loginRequest.getUsername(),
//     // loginRequest.getPassword());
//     // if (authenticated) {
//     // String token = JwtUtil.generateToken(loginRequest.getUsername());
//     // return ResponseEntity.ok(token);
//     // } else {
//     // return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username
//     // or password");
//     // }
//     // }

//     // Get all companies
//     @GetMapping
//     public List<CompanyUser> getAllCompanies() {
//         return companyUserService.getAllCompanies();
//     }

//     // Get a company by ID
//     @GetMapping("/{id}")
//     public ResponseEntity<CompanyUser> getCompanyById(@PathVariable Long id) {
//         CompanyUser companyUser = companyUserService.getCompanyById(id);
//         return companyUser != null ? ResponseEntity.ok(companyUser) : ResponseEntity.notFound().build();
//     }

//     // Update a company by ID
//     @PutMapping("/{id}")
//     public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody CompanyUser updatedCompany) {
//         boolean isUpdated = companyUserService.updateCompany(id, updatedCompany);
//         return isUpdated ? ResponseEntity.ok("Company updated successfully!")
//                 : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found");
//     }

//     // Delete a company by ID
//     @DeleteMapping("/{id}")
//     public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
//         boolean isDeleted = companyUserService.deleteCompany(id);
//         return isDeleted ? ResponseEntity.ok("Company deleted successfully!")
//                 : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found");
//     }

//     private String generateUniqueCompanyId() {
//         // Implement unique 4-digit ID generation logic here
//         return String.format("%04d", (int) (Math.random() * 10000));
//     }
// }


package com.example.credentials.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.credentials.dto.LoginRequest;
import com.example.credentials.model.CompanyUser;
import com.example.credentials.service.CompanyUserService;

@RestController
@RequestMapping("/api/auth")
public class CompanyUserController {

    @Autowired
    private CompanyUserService companyUserService;

    @PostMapping("/register")
    public CompanyUser register(@RequestBody CompanyUser companyUser) {
        return companyUserService.register(companyUser);
    }

    // @PostMapping("/login")
    // public String login(@RequestParam String username, @RequestParam String password) {
    //     return companyUserService.login(username, password);
    // }
    @PostMapping("/login")
public String login(@RequestBody LoginRequest loginRequest) {
    return companyUserService.login(loginRequest.getUsername(), loginRequest.getPassword());
}

}
