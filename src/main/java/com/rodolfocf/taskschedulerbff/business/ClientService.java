package com.rodolfocf.taskschedulerbff.business;


import com.rodolfocf.taskschedulerbff.business.dto.in.CellphoneDTORequest;
import com.rodolfocf.taskschedulerbff.business.dto.in.ClientDTORequest;
import com.rodolfocf.taskschedulerbff.business.dto.in.LoginDTORequest;
import com.rodolfocf.taskschedulerbff.business.dto.out.CellphoneDTOResponse;
import com.rodolfocf.taskschedulerbff.business.dto.out.ClientDTOResponse;
import com.rodolfocf.taskschedulerbff.infrastructure.client.ClientClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClientService {

    private final ClientClient clientClient;

    public ClientDTOResponse saveClient(ClientDTORequest clientDTO) {
        return clientClient.saveClient(clientDTO);
    }

    public String login(LoginDTORequest clientDTO) {
        return clientClient.login(clientDTO);
    }

    public ClientDTOResponse searchClientByEmail(String email, String token) {
        return clientClient.searchClientByEmail(email, token);
    }

    public void deleteClientByEmail(String email, String token) {
        clientClient.deleteClientByEmail(email, token);

    }

//------------------------------------------------------ UPDATES -----------------------------------------------------------------------------

    public ClientDTOResponse updateClientData(String token, ClientDTORequest dto) {
        return clientClient.updateClientData(dto, token);

    }

    public CellphoneDTOResponse cellphoneUpdate(Long cellphoneId, CellphoneDTORequest cellphoneDTO, String token) {
        return clientClient.cellphoneUpdate(cellphoneDTO, cellphoneId, token);
    }

//-------------------------------------------------------------------------------------------------------------------------------------

    public CellphoneDTOResponse cellphoneRegister(String token, CellphoneDTORequest cellphoneDTO) {
        return clientClient.cellphoneRegister(cellphoneDTO, token);
    }


    public void deleteCellphone(Long id, String token) {
        clientClient.deleteCellphone(id, token);
    }


}
