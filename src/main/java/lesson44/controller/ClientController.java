package lesson44.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lesson44.dao.TransferCardDTO;
import lesson44.model.ClientModel;
import lesson44.repository.BankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Tag(name = "Client Service", description = "Service to find client, and transfer between card")
@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    BankingRepository bankingRepository;

    @Operation(summary = "Get client by the ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Find the client",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = ClientModel.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Client not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ClientModel> getClientById(@PathVariable int id){
        ClientModel clientModel = bankingRepository.getClientById(id);
        return  new ResponseEntity<>(clientModel, HttpStatus.OK);
    }

    @Operation(summary = "Transfer between card client.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Transfer between card client", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Client not found", content = @Content)
    })
    @PostMapping(value = "/transfer", consumes = "application/json")
    public ResponseEntity<TransferCardDTO> transfer(@RequestBody TransferCardDTO dto){
        System.out.println("DTO - " + dto);
        bankingRepository.transfer(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
