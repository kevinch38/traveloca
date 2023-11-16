package com.enigma.traveloca.service.impl;

import com.enigma.traveloca.constant.ERole;
import com.enigma.traveloca.dto.request.AuthRequest;
import com.enigma.traveloca.dto.request.CreateAdminRequest;
import com.enigma.traveloca.dto.request.CreateCustomerRequest;
import com.enigma.traveloca.dto.response.LoginResponse;
import com.enigma.traveloca.dto.response.RegisterResponse;
import com.enigma.traveloca.entity.AppUser;
import com.enigma.traveloca.entity.Role;
import com.enigma.traveloca.entity.UserCredential;
import com.enigma.traveloca.repository.UserCredentialRepository;
import com.enigma.traveloca.security.JwtUtil;
import com.enigma.traveloca.service.AdminService;
import com.enigma.traveloca.service.AuthService;
import com.enigma.traveloca.service.CustomerService;
import com.enigma.traveloca.service.RoleService;
import com.enigma.traveloca.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserCredentialRepository userCredentialRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomerService customerService;
    private final AdminService adminService;
    private final RoleService roleService;
    private final JwtUtil jwtUtil;
    private final ValidationUtil validationUtil;
    private final AuthenticationManager authenticationManager;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RegisterResponse registerCustomer(AuthRequest request) {
        try {
            validationUtil.validate(request);
            Role role = roleService.getOrSave(Role.builder().name(ERole.ROLE_CUSTOMER).build());

            UserCredential userCredential = UserCredential.builder()
                    .username(request.getUsername().toLowerCase())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(role)
                    .build();
            userCredentialRepository.saveAndFlush(userCredential);

            CreateCustomerRequest customer = CreateCustomerRequest.builder()
                    .userCredentialId(userCredential.getId())
                    .name(request.getName())
                    .build();
            customerService.save(customer);

            return RegisterResponse.builder()
                    .username(userCredential.getUsername())
                    .role(userCredential.getRole().getName().toString())
                    .build();
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exist");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RegisterResponse registerAdmin(AuthRequest request) {
        try {
            validationUtil.validate(request);
            Role role = roleService.getOrSave(Role.builder().name(ERole.ROLE_ADMIN).build());

            UserCredential userCredential = UserCredential.builder()
                    .username(request.getUsername().toLowerCase())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(role)
                    .build();
            userCredentialRepository.saveAndFlush(userCredential);

            CreateAdminRequest admin = CreateAdminRequest.builder()
                    .userCredentialId(userCredential.getId())
                    .name(request.getName())
                    .build();
            adminService.save(admin);

            return RegisterResponse.builder()
                    .username(userCredential.getUsername())
                    .role(userCredential.getRole().getName().toString())
                    .build();
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exist");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public LoginResponse login(AuthRequest request) {
        validationUtil.validate(request);
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername().toLowerCase(),
                request.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        AppUser appUser = (AppUser) authenticate.getPrincipal();
        String token = jwtUtil.generateToken(appUser);

        return LoginResponse.builder()
                .token(token)
                .role(appUser.getRole().name())
                .build();
    }
}
