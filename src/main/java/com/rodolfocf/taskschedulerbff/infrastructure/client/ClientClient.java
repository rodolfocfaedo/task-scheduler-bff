package com.rodolfocf.taskschedulerbff.infrastructure.client;

import com.rodolfocf.taskschedulerbff.business.dto.in.CellphoneDTORequest;
import com.rodolfocf.taskschedulerbff.business.dto.in.ClientDTORequest;
import com.rodolfocf.taskschedulerbff.business.dto.in.LoginDTORequest;
import com.rodolfocf.taskschedulerbff.business.dto.out.CellphoneDTOResponse;
import com.rodolfocf.taskschedulerbff.business.dto.out.ClientDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "client", url = "${client.url}")
public interface ClientClient {

    @GetMapping
    ClientDTOResponse searchClientByEmail(@RequestParam("email") String email,
                                          @RequestHeader("Authorization") String token);

    @PostMapping
    ClientDTOResponse saveClient(@RequestBody ClientDTORequest clientDTO);

    @PostMapping("/login")
    String login(@RequestBody LoginDTORequest clientDTO);

    @PutMapping
    ClientDTOResponse updateClientData(@RequestBody ClientDTORequest clientDTO,
                                       @RequestHeader("Authorization") String token);

    @DeleteMapping("/{email}")
    void deleteClientByEmail(@PathVariable("email") String email,
                             @RequestHeader("Authorization") String token);


    @PutMapping("/cellphone")
    CellphoneDTOResponse cellphoneUpdate(@RequestBody CellphoneDTORequest cellphoneDTO,
                                         @RequestParam("id") Long id,
                                         @RequestHeader("Authorization") String token);

    @PostMapping("/cellphone")
    CellphoneDTOResponse cellphoneRegister(@RequestBody CellphoneDTORequest cellphoneDTO,
                                           @RequestHeader("Authorization") String token);


    @DeleteMapping("/cellphones/{cellphoneId}")
    void deleteCellphone(@PathVariable("cellphoneId") Long id,
                         @RequestHeader("Authorization") String token);

}
