package com.ison.app.dao;

import java.util.List;

import com.ison.app.shared.dto.ClientDetailDto;
import com.ison.app.shared.dto.ContactDetailDto;

public interface ClientDAO {


	public ClientDetailDto create(List<ClientDetailDto> clientDetailDtoList) throws Exception;
	
	public List<ClientDetailDto>  findByClient(ClientDetailDto clientDetailDto) throws Exception;
	
	public ClientDetailDto getClientDetails() throws Exception;
	
	public ClientDetailDto getRegionDetails(ClientDetailDto clientDetailDto) throws Exception;
	
	public ClientDetailDto getCenterDetails(ClientDetailDto clientDetailDto) throws Exception;
	
	public ClientDetailDto getContactDetails(ClientDetailDto clientDetailDto) throws Exception;
	
	public ClientDetailDto getClientReportMapDetails(ContactDetailDto contactDetailDto) throws Exception;

}
