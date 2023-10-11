package br.com.marcobilotta.todolist.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.marcobilotta.todolist.model.UserModel;
import br.com.marcobilotta.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @PostMapping("/")
    public ResponseEntity createUser(@RequestBody UserModel userModel){
        var user = this.userRepository.findByUsername(userModel.getUsername());
        if (user != null) {
            System.out.println("Usuário já existente!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário Existente!");
        }
        userModel.setPassword(BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray()));
        var userCreated = this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
    }
}
