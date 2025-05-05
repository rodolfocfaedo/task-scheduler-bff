package com.rodolfocf.taskschedulerbff.controller;

import com.rodolfocf.taskschedulerbff.business.ClientService;
import com.rodolfocf.taskschedulerbff.business.dto.in.CellphoneDTORequest;
import com.rodolfocf.taskschedulerbff.business.dto.in.ClientDTORequest;
import com.rodolfocf.taskschedulerbff.business.dto.in.LoginDTORequest;
import com.rodolfocf.taskschedulerbff.business.dto.out.CellphoneDTOResponse;
import com.rodolfocf.taskschedulerbff.business.dto.out.ClientDTOResponse;
import com.rodolfocf.taskschedulerbff.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
@Tag(name = "Client", description = "Client register and login")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    @Operation(summary = "Save client", description = "Create new client")
    @ApiResponse(responseCode = "200", description = "Client saved successfully")
    @ApiResponse(responseCode = "400", description = "Client already registered")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    public ResponseEntity<ClientDTOResponse> saveClient(@RequestBody ClientDTORequest clientDTO) {
        return ResponseEntity.ok(clientService.saveClient(clientDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "Client Login", description = "Login the client")
    @ApiResponse(responseCode = "200", description = "Client logged in  successfully")
    @ApiResponse(responseCode = "401", description = "Invalid credentials")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    public String login(@RequestBody LoginDTORequest clientDTO) {
        return clientService.login(clientDTO);
    }


    @GetMapping
    @Operation(summary = "Find client data by email", description = "Search for client data")
    @ApiResponse(responseCode = "200", description = "Found successfully")
    @ApiResponse(responseCode = "404", description = "Client not found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    public ResponseEntity<ClientDTOResponse> searchClientByEmail(@RequestParam("email") String email,
                                                                 @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(clientService.searchClientByEmail(email, token));
    }


    @PutMapping
    @Operation(summary = "Update client data", description = "Update client data")
    @ApiResponse(responseCode = "200", description = "Updated successfully")
    @ApiResponse(responseCode = "404", description = "Client not found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    public ResponseEntity<ClientDTOResponse> updateClientData(@RequestBody ClientDTORequest clientDTO,
                                                              @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(clientService.updateClientData(token, clientDTO));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Delete client data by email", description = "Delete client")
    @ApiResponse(responseCode = "200", description = "Deleted successfully")
    @ApiResponse(responseCode = "404", description = "Client not found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    public ResponseEntity<Void> deleteClientByEmail(@PathVariable("email") String email,
                                                    @RequestHeader(name = "Authorization", required = false) String token) {
        clientService.deleteClientByEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/cellphone")
    @Operation(summary = "Save cellphone", description = "Create new cellphone")
    @ApiResponse(responseCode = "200", description = "Cellphone saved successfully")
    @ApiResponse(responseCode = "400", description = "Cellphone already registered")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    public ResponseEntity<CellphoneDTOResponse> cellphoneRegister(@RequestBody CellphoneDTORequest cellphoneDTO,
                                                                  @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(clientService.cellphoneRegister(token, cellphoneDTO));
    }

    @PutMapping("/cellphone")
    @Operation(summary = "Update client cellphone", description = "Update client cellphone")
    @ApiResponse(responseCode = "200", description = "Updated successfully")
    @ApiResponse(responseCode = "404", description = "Cellphone not found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    public ResponseEntity<CellphoneDTOResponse> cellphoneUpdate(@RequestBody CellphoneDTORequest cellphoneDTO,
                                                                @PathVariable("id") Long id,
                                                                @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(clientService.cellphoneUpdate(id, cellphoneDTO, token));
    }

    @DeleteMapping("/cellphones/{cellphoneId}")
    @Operation(summary = "Delete client cellphone by cellphone' id", description = "Delete client cellphone")
    @ApiResponse(responseCode = "200", description = "Deleted successfully")
    @ApiResponse(responseCode = "404", description = "Cellphone not found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    public ResponseEntity<Void> deleteCellphone(@PathVariable("cellphoneId") Long id,
                                                @RequestHeader(name = "Authorization", required = false) String token) {
        clientService.deleteCellphone(id, token);
        return ResponseEntity.ok().build();
    }
}


