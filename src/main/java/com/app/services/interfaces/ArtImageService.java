package com.app.services.interfaces;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.request.ArtReqDto;

public interface ArtImageService {
	
	public String upLoadImage(Long id,MultipartFile image);
	
	public ResponseEntity<byte[]> serveImage(Long artId) throws IOException;
}
