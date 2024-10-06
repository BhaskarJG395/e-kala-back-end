package com.app.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.custom_exceptions.CustomExcp;
import com.app.dao.ArtEntityDao;
import com.app.dto.ArtRespDto;
import com.app.dto.request.ArtReqDto;
import com.app.entities.ArtEntity;
import com.app.services.interfaces.ArtImageService;
import com.app.services.interfaces.ArtServices;

@Service
@Transactional
public class ArtServiceImpl implements ArtServices {

	@Autowired
	ArtEntityDao artDao;
	
	@Autowired
	ModelMapper mapper;

	@Autowired
	private ArtImageService artImageServive;
	
	//get all method
	@Override
	public List<ArtRespDto> getAllArt() {
		return artDao.findAll().stream().map(a->mapper.map(a, ArtRespDto.class)).collect(Collectors.toList());
	}
	
	//add new art 
	@Override
	public Long addNewArtDetails(ArtReqDto dto) {
		System.out.println("In art Service "+dto);
		ArtEntity artEntity=mapper.map(dto, ArtEntity.class);
		artDao.save(artEntity);
		System.out.println(artEntity.getId());
		return artEntity.getId(); //latter we have to convert it into ArtResDto
	}
	
	//update art using id
	@Override
	public String updateArt(Long artId, ArtReqDto artDto) {
		ArtEntity art=artDao.findById(artId).orElseThrow(()->new CustomExcp("Id not found"));
		mapper.map(artDto, art);
		artDao.save(art);
		return "Art details are updated successfully";
	}
	
	//deleting art with artId
	@Override
	public String deleteArt(Long artId) {
		artDao.deleteById(artId);
		return "Art deleted successfully";
	}
}
