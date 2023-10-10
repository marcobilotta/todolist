package br.com.marcobilotta.todolist.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cadastro")
public class ToDoListController {

    @GetMapping("/")
    public String Mensagem(){
        return "Funcionou";
    }
}
