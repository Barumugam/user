package com.ison.app.services;

import java.util.List;

import com.ison.app.shared.dto.ClientDetailDto;

public interface ClientService {
	
	public ClientDetailDto create(List<ClientDetailDto> clientDetailDtoList) throws Exception;
	
	public ClientDetailDto showAllClients() throws Exception;
	
	public ClientDetailDto checkClientDetailsAlreadyExists(ClientDetailDto clientDetailDto) throws Exception;

}
