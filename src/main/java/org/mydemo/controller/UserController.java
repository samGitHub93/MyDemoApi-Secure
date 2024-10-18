package org.mydemo.controller;

import jakarta.validation.Valid;
import org.mydemo.dto.UserCreateDTO;
import org.mydemo.dto.UserEmailPwdDTO;
import org.mydemo.dto.UserIdentityDTO;
import org.mydemo.dto.UserUsernamePwdDTO;
import org.mydemo.entity.UserEntity;
import org.mydemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RefreshScope // Refresh for update configuration automatically, calling POST localhost:8080/actuator/refresh
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public List<UserEntity> getAll(){
        return userService.getAll();
    }

    @GetMapping("/")
    public UserEntity getByIdParam(@RequestParam("id") int id){
        return userService.getById(id);
    }

    @GetMapping("/{id}")
    public UserEntity getById(@PathVariable("id") int id){
        return userService.getById(id);
    }

    @PostMapping(value = "/age", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserEntity> getByAge(@RequestBody String age){
        int ageInt = Integer.parseInt(age);
        return userService.getByAge(ageInt);
    }

    @PostMapping(value = "/existsByUsername", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String existsByUsername(@RequestBody String username){
        return String.valueOf(userService.existsByUsername(username));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<UserIdentityDTO> create(@Valid @RequestBody UserCreateDTO userCreateDTO){
        return new ResponseEntity<>(userService.register(userCreateDTO), HttpStatusCode.valueOf(200));
    }

    @PostMapping(value = "/getIdentityInfoByUsername")
    public ResponseEntity<UserIdentityDTO> getIdentityInfoByUsername(@Valid @RequestBody UserUsernamePwdDTO userUsernamePwdDTO){
        return new ResponseEntity<>(userService.getIdentityInfoByUsername(userUsernamePwdDTO), HttpStatusCode.valueOf(200));
    }

    @PostMapping(value = "/getIdentityInfoByEmail")
    public ResponseEntity<UserIdentityDTO> getIdentityInfoByEmail(@Valid @RequestBody UserEmailPwdDTO userEmailPwdDTO){
        return ResponseEntity.ok(userService.getIdentityInfoByEmail(userEmailPwdDTO));
    }
}
