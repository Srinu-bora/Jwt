// package com.example.credentials.service;

// import com.example.credentials.model.CompanyUser;
// import com.example.credentials.repository.CompanyUserRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.stereotype.Service;

// import java.util.List;
// import java.util.Optional;

// @Service
// public class CompanyUserService {

//     @Autowired
//     private CompanyUserRepository companyUserRepository;

//     private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//     // // Register the company user
//     // public String registerCompany(CompanyUser companyUser) {
//     //     // Generate a unique 4-digit company ID
//     //     companyUser.setCompanyId(generateUniqueCompanyId());
//     //     companyUser.setPassword(passwordEncoder.encode(companyUser.getPassword()));
//     //     companyUserRepository.save(companyUser);
//     //     return "Company registered successfully!";
//     // }
//     public String registerCompany(CompanyUser companyUser) {
//         // Check if the username already exists
//         if (usernameExists(companyUser.getUsername())) {
//             return "Username is already taken!";
//         }
    
//         // If not, proceed with registration
//         companyUser.setCompanyId(generateUniqueCompanyId());
//         companyUser.setPassword(passwordEncoder.encode(companyUser.getPassword())); // Encrypt password
//         companyUserRepository.save(companyUser); // Save company in the repository
//         return "Company registered successfully!";
//     }
    
//     public boolean usernameExists(String username) {
//         // Check if the username already exists in the database
//         return companyUserRepository.existsByUsername(username);
//     }
    
    

//     // Authenticate user
//     public boolean authenticate(String username, String rawPassword) {
//         CompanyUser companyUser = companyUserRepository.findByUsername(username)
//                 .orElseThrow(() -> new RuntimeException("Invalid username or password"));
//         return passwordEncoder.matches(rawPassword, companyUser.getPassword());
//     }

// //     public boolean authenticate(String username, String rawPassword) {
// //     CompanyUser companyUser = companyUserRepository.findByUsername(username)
// //             .orElseThrow(() -> new RuntimeException("Invalid username or password"));
// //     return rawPassword.equals(companyUser.getPassword());
// // }


//     // Get all companies
//     public List<CompanyUser> getAllCompanies() {
//         return companyUserRepository.findAll();
//     }

//     // Get company by ID
//     public CompanyUser getCompanyById(Long id) {
//         return companyUserRepository.findById(id).orElse(null);
//     }

//     // Update company by ID
//     public boolean updateCompany(Long id, CompanyUser updatedCompany) {
//         Optional<CompanyUser> optionalCompanyUser = companyUserRepository.findById(id);
//         if (optionalCompanyUser.isPresent()) {
//             CompanyUser existingCompany = optionalCompanyUser.get();
//             existingCompany.setCompanyName(updatedCompany.getCompanyName());
//             existingCompany.setOwnerName(updatedCompany.getOwnerName());
//             existingCompany.setLocation(updatedCompany.getLocation());
//             existingCompany.setAddress(updatedCompany.getAddress());
//             existingCompany.setPhoneNumber(updatedCompany.getPhoneNumber());
//             companyUserRepository.save(existingCompany);
//             return true;
//         }
//         return false;
//     }

//     // Delete company by ID
//     public boolean deleteCompany(Long id) {
//         if (companyUserRepository.existsById(id)) {
//             companyUserRepository.deleteById(id);
//             return true;
//         }
//         return false;
//     }

//     // Generate unique 4-digit company ID
//     private String generateUniqueCompanyId() {
//         // Loop until a unique company ID is found
//         String companyId;
//         do {
//             companyId = String.format("%04d", (int) (Math.random() * 10000));
//         } while (companyUserRepository.existsByCompanyId(companyId));  // Ensure it's unique
//         return companyId;
//     }
    
// }
package com.example.credentials.service;

import com.example.credentials.model.CompanyUser;
import com.example.credentials.repository.CompanyUserRepository;
import com.example.credentials.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CompanyUserService {

    @Autowired
    private CompanyUserRepository companyUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public CompanyUser register(CompanyUser companyUser) {
        companyUser.setPassword(passwordEncoder.encode(companyUser.getPassword()));
        return companyUserRepository.save(companyUser);
    }

    public String login(String username, String password) {
        Optional<CompanyUser> userOpt = companyUserRepository.findByUsername(username);
        
        if (userOpt.isPresent() && passwordEncoder.matches(password, userOpt.get().getPassword())) {
            return jwtUtil.generateToken(username);
        }
        throw new RuntimeException("Invalid username or password");
    }
}
