package com.file.shareby.controller;

import com.file.shareby.DTO.UserDTO;
import com.file.shareby.customexception.InvalidUserDataException;
import com.file.shareby.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Api(tags = "User Services")
@RequestMapping("/api")
@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO, HttpSession httpSession) {
        try {
            userService.registerUser(userDTO, httpSession);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (InvalidUserDataException e) {
            log.debug("No details found with this user");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
