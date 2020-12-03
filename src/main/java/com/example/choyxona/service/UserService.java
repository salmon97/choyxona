package com.example.choyxona.service;


import com.example.choyxona.entity.User;
import com.example.choyxona.entity.enums.RoleName;
import com.example.choyxona.payload.ApiResponse;
import com.example.choyxona.payload.ReqRegister;
import com.example.choyxona.payload.ResStaff;
import com.example.choyxona.repository.RoleRepository;
import com.example.choyxona.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    public void SetWeekend(User user) {
        userRepository.save(user);
    }

    public ApiResponse registerStaff(ReqRegister reqRegister) {
        if (!userRepository.existsByEmail(reqRegister.getEmail())) {
            User user = new User();
            user.setEmail(reqRegister.getEmail());
            user.setPassword(passwordEncoder.encode(reqRegister.getPassword()));
            user.setRoles(Collections.singleton(roleRepository.findByRoleName(RoleName.ROLE_STAFF)));
            userRepository.save(user);
            return new ApiResponse("ok", true);
        }
        return new ApiResponse("bunday email mavjud", false);
    }


    public ApiResponse edit(ReqRegister reqRegister) {
        User user = userRepository.findById(reqRegister.getId()).orElseThrow(() -> new ResourceNotFoundException("getUser"));
        if (!userRepository.existsByEmail(reqRegister.getEmail()) || user.getEmail().equals(reqRegister.getEmail())) {
            user.setEmail(reqRegister.getEmail());
            user.setPassword(passwordEncoder.encode(reqRegister.getPassword()));
            userRepository.save(user);
            return new ApiResponse("ok", true);
        }
        return new ApiResponse("bunday email mavjud", false);
    }

    public ApiResponse deleteStaff(UUID id) {
        userRepository.deleteById(id);
        return new ApiResponse(id.toString(), true);
    }

    public ApiResponse resStaffs() {
        List<User> staffs = userRepository.getStaffs();
        List<ResStaff> resStaffs = new ArrayList<>();
        for (User staff : staffs) {
            resStaffs.add(new ResStaff(staff.getId(), staff.getEmail()));
        }
        return new ApiResponse("ok", true, resStaffs);
    }

    public ApiResponse changePassword(ReqRegister reqRegister) {
        Optional<User> byEmail = userRepository.findByEmail(reqRegister.getEmail());
        if (byEmail.isPresent()) {
            if (passwordEncoder.matches(reqRegister.getPassword(), byEmail.get().getPassword())) {
                byEmail.get().setPassword(passwordEncoder.encode(reqRegister.getPrePassword()));
                userRepository.save(byEmail.get());
                return new ApiResponse("ok", true);
            }
            return new ApiResponse("login yoki parol xato", false);
        }
        return new ApiResponse("login yoki parol xato", false);
    }

    public UserDetails getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("getUser"));
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByEmail(s).orElseThrow(() -> new UsernameNotFoundException("getUser"));
    }
}
