package com.app.services.interfaces;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.app.dto.ArtRespDto;
import com.app.dto.request.ArtReqDto;

public interface ArtServices {
	
	public Long addNewArtDetails(ArtReqDto artdto);
	
	public String deleteArt(Long artId);
	
	public String updateArt(Long artId, ArtReqDto artDto);
	
	public List<ArtRespDto> getAllArt();
	
//	public Long getIdByNameAndType(String artName, String artType) ;
}
