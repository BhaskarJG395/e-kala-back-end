package com.app.services;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.apache.commons.io.FileUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.custom_exceptions.CustomExcp;
import com.app.dao.ArtEntityDao;
import com.app.dto.request.ArtReqDto;
import com.app.entities.ArtEntity;
import com.app.services.interfaces.ArtImageService;

@Service
@Transactional
public class ArtImageServiceImpl implements ArtImageService {

    @Autowired
    private ArtEntityDao artDao;
    
    @Autowired
    private ModelMapper mapper;
    
    @Value("${folder.location}") // Using SPEL: Spring Expression Language
    private String folderLocation;
    
    @PostConstruct
    public void init() {
        System.out.println("In init " + folderLocation);
        // It will check if the folder exists or not
        System.out.println("folderLocation: "+folderLocation);
        File folder = new File(folderLocation);
        if (folder.exists()) {
        	System.out.println("folder: "+folder);
            System.out.println("Folder already exists..!");
        } else {
            // Create folder
            folder.mkdir();
            System.out.println("Folder created..!");
        }
    }
	 
    @Override
    public String upLoadImage(Long id, MultipartFile image) {

        ArtEntity art = artDao.findById(id)
                .orElseThrow(() -> new CustomExcp("Art ID not found..!"));
		System.out.println(art.toString());
        String uniqueFilename = System.currentTimeMillis() + "_" + image.getOriginalFilename();
        String path = folderLocation.concat(File.separator).concat(uniqueFilename);

        try {
            // Save the image file to the specified folder
            FileUtils.writeByteArrayToFile(new File(path), image.getBytes());
            
            // Update the ArtEntity with the image path
            art.setArtImagePath(path);
            artDao.save(art); // Save the updated entity to the database
            
        } catch (IOException e) {
            e.printStackTrace();
            throw new CustomExcp("Failed to upload image: " + e.getMessage());
        }
        return mapper.map(art, String.class);
    }

    @Override
    public ResponseEntity<byte[]> serveImage(Long artId) throws IOException {
        ArtEntity art = artDao.findById(artId)
                .orElseThrow(() -> new CustomExcp("Art ID not found..!"));
        String path = art.getArtImagePath();

        if (path != null) {
            byte[] imageBytes = FileUtils.readFileToByteArray(new File(path));

            // Determine content type dynamically based on file extension
            String fileExtension = path.substring(path.lastIndexOf(".") + 1).toLowerCase();
            MediaType mediaType;

            switch (fileExtension) {
                case "png":
                    mediaType = MediaType.IMAGE_PNG;
                    break;
                case "jpeg":
                case "jpg":
                    mediaType = MediaType.IMAGE_JPEG;
                    break;
                case "gif":
                    mediaType = MediaType.IMAGE_GIF;
                    break;
                default:
                    mediaType = MediaType.APPLICATION_OCTET_STREAM;
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(mediaType);

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(imageBytes.length)
                    .body(imageBytes);

        } else {
            throw new CustomExcp("Image not assigned yet");
        }
    }



}
