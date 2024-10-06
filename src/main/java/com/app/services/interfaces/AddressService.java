package com.app.services.interfaces;

import java.util.List;

import com.app.custom_exceptions.CustomExcp;
import com.app.dto.AddressRespDto;
import com.app.dto.ApiResponse;
import com.app.dto.request.AddressReqDto;

public interface AddressService {
	
	 List<AddressRespDto> getAllAddress();
	 
	 AddressRespDto getAddressByUser(Long userId);
	 
	 AddressReqDto addAddress(Long userId,AddressReqDto dto);
	 
	 AddressReqDto updateAddress(Long userId, AddressReqDto dto);
	 
	 ApiResponse deleteAddress(Long addressId);
	 
	 
}
