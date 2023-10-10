package br.com.marcobilotta.todolist.usercase;

import br.com.marcobilotta.todolist.model.UserModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping("/")
    public void createUser(@RequestBody UserModel userModel){
        System.out.println(userModel.getUsername());
    }
}
