package lesson44.controller;

import lesson44.model.ClientModel;
import lesson44.repository.BankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    BankingRepository bankingRepository;

    @GetMapping("/{id}")
    public ResponseEntity<ClientModel> getClientById(@PathVariable int id){
        ClientModel clientModel = bankingRepository.getClientById(id);
        return  new ResponseEntity<>(clientModel, HttpStatus.OK);
    }
}
