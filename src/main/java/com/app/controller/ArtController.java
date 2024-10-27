package com.app.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.custom_exceptions.CustomExcp;
import com.app.dto.request.ArtReqDto;
import com.app.services.interfaces.ArtImageService;
import com.app.services.interfaces.ArtServices;

@RestController	
@RequestMapping("/Art")
@CrossOrigin(origins={"http://localhost:3000","https://sobikola.netlify.app"})
public class ArtController {
	
	@Autowired
	ArtServices artService;
	
	@Autowired
	ArtImageService artImageService;
	
	//this is for download the image
	@GetMapping(value = "/images/{artId}",produces = {MediaType.IMAGE_GIF_VALUE,MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_PNG_VALUE})
	public ResponseEntity<?> downloadImage(@PathVariable Long artId) throws IOException{
		return ResponseEntity.ok(artImageService.serveImage(artId));
	}
	
	@GetMapping(value = "/getAllArts")
	public ResponseEntity<?> getAllArt(){
		return ResponseEntity.ok(artService.getAllArt());
	}
	
	@GetMapping("/getArtById/{artId}")
	public ResponseEntity<byte[]> serveImage(@PathVariable Long artId) {
	    try {
	        return artImageService.serveImage(artId);
	    } catch (CustomExcp e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Return 404 if art ID not found or image missing
	    } catch (IOException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Handle other exceptions
	    }
	}

	 
	@PostMapping(value = "/add")
	public ResponseEntity<Long> addNewArt(@RequestBody ArtReqDto artDto) {
		System.out.println(artDto);
	    System.out.println("Inside Art Controller");
	    return new ResponseEntity<>(artService.addNewArtDetails(artDto), HttpStatus.CREATED);
	}

	@PostMapping(value = "/add/{id}",consumes = "multipart/form-data")
	public ResponseEntity<String> addNewArt(@PathVariable Long id, @RequestParam MultipartFile imageFile) {
	    System.out.println("Inside Art Controller: "+id);
	    return new ResponseEntity<>(artImageService.upLoadImage(id, imageFile), HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{artId}")
	public ResponseEntity<?> updateArt(@PathVariable Long artId,@RequestBody ArtReqDto artDto){
		return ResponseEntity.status(HttpStatus.OK).body(artService.updateArt(artId, artDto));
	}
	
	@DeleteMapping("/delete/{artId}")
	public ResponseEntity<?> deleteArt(@PathVariable Long artId){
		return ResponseEntity.ok(artService.deleteArt(artId));
	}
}
