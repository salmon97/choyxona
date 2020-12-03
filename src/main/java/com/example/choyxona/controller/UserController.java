package com.example.choyxona.controller;


import com.example.choyxona.entity.User;
import com.example.choyxona.payload.ApiResponse;
import com.example.choyxona.payload.ReqLogin;
import com.example.choyxona.payload.ReqRegister;
import com.example.choyxona.payload.ResToken;
import com.example.choyxona.security.CurrentUser;
import com.example.choyxona.security.JwtTokenProvider;
import com.example.choyxona.service.UserService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    final
    JwtTokenProvider jwtTokenProvider;

    final
    UserService userService;

    final
    AuthenticationManager authenticationManager;

    public UserController(JwtTokenProvider jwtTokenProvider, UserService userService, AuthenticationManager authenticationManager) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/resStaff")
    public HttpEntity<?> resStaff() {
        return ResponseEntity.ok(userService.resStaffs());
    }

    @PostMapping("/setting/register")
    public HttpEntity<?> registerStaff(@Valid @RequestBody ReqRegister reqRegister) {
        if (reqRegister.getId() == null) {
            ApiResponse apiResponse = userService.registerStaff(reqRegister);
            return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
        } else {
            ApiResponse apiResponse = userService.edit(reqRegister);
            return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
        }
    }

    @DeleteMapping("/setting/delete/{id}")
    public HttpEntity<?> delete(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.deleteStaff(id));
    }

    @PutMapping("/setting/changePassword")
    public HttpEntity<?> changePassword(@RequestBody ReqRegister reqRegister) {
        return ResponseEntity.ok(userService.changePassword(reqRegister));
    }

    @PutMapping("/setting/weekend/{weekend}")
    public HttpEntity<?> setWeekend(@PathVariable String weekend, @CurrentUser User user) {
        user.setWeekend(weekend);
        userService.SetWeekend(user);
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody ReqLogin reqLogin) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                reqLogin.getEmail(),
                reqLogin.getPassword()
        ));
        String token = jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new ResToken(token));
    }


    @GetMapping("/userMe")
    public HttpEntity<?> userMe(@CurrentUser User user) {
        return ResponseEntity.status(user == null ? 409 : 200).body(user);
//        return ResponseEntity.status(user==null?HttpStatus.CONFLICT:HttpStatus.OK).body(user);
    }
}
