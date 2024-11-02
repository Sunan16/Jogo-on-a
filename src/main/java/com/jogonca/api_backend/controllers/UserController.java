package com.jogonca.api_backend.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jogonca.api_backend.exceptions.NotFoundException;
import com.jogonca.api_backend.interfaces.AbstractCrudController;
import com.jogonca.api_backend.models.User;
import com.jogonca.api_backend.models.dtos.receiveDTOs.RecoveryRequestDTO;
import com.jogonca.api_backend.models.dtos.receiveDTOs.UserDTO;
import com.jogonca.api_backend.models.dtos.sendDTOs.UserSendDTO;
import com.jogonca.api_backend.services.UserService;
import com.jogonca.api_backend.utils.Utils;

@RestController
@RequestMapping("/user")
public class UserController extends AbstractCrudController<UserDTO, User, UserSendDTO, String> {

    public UserController(UserService service) {
        super(service);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> findAll() {
        var list = service.findAll();
        list.forEach(dto -> {
            dto.add(linkTo(methodOn(this.getClass()).findById(String.valueOf(dto.getKey()))).withSelfRel());
            dto.add(linkTo(methodOn(LoginController.class).recoveryPassEmail(null)).withSelfRel());
        });
        return list;
    }

    @Override
    @GetMapping(value = "/{param}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public UserDTO findById(@PathVariable String param) {
        var item = service.findById(Utils.toLong(param));
        if (item == null) {
            // Handle not found scenario
            throw new NotFoundException("Usuário não encontrado com ID: " + param);
        }
        item.add(linkTo(methodOn(this.getClass()).findById(param)).withSelfRel());
        item.add(linkTo(methodOn(LoginController.class).recoveryPassEmail(new RecoveryRequestDTO())).withSelfRel());
        return item;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE })
    public UserDTO create(@RequestBody UserSendDTO json) {
        // Hashing should be done using a secure algorithm like BCrypt
        String hashedPassword = hashPassword(json.getPasswordHash());
        json.setPasswordHash(hashedPassword);
        json.setRecoveryToken(null);
        json.setTokenExpiration(null);
        json.setCoins(null);

        UserDTO user = service.insert(json);
        user.add(linkTo(methodOn(this.getClass()).findById(String.valueOf(user.getKey()))).withSelfRel());
        user.add(linkTo(methodOn(LoginController.class).recoveryPassEmail(new RecoveryRequestDTO())).withSelfRel());
        return user;
    }

    private String hashPassword(String password) {
        // Implement a proper password hashing mechanism, e.g., using BCrypt
        return password; // Placeholder for actual hashing
    }
}
