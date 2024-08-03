package lesson44.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
@Tag(name = "File Management System")
@RequestMapping("/files")
public class FileController {

    private String directory = "C:\\TMSCources";

    @PostMapping(value = "/upload")
    @Operation( summary= "Upload a file", description = "Provide a file to be uploaded to the server")
    public ResponseEntity<String> uploadFile(@Parameter(name = "File to be uploaded") @RequestParam("file") MultipartFile multipartFile){
        try{
            Files.copy(multipartFile.getInputStream(), Paths.get(directory).resolve(multipartFile.getOriginalFilename()));
            return ResponseEntity.ok("Upload: " + multipartFile.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/download/{filename}")
    @Operation( summary= "Download a file")
    public  ResponseEntity<Resource> downloadFile(@Parameter(name = "Name of the file to be downloaded") @PathVariable("filename") String filename){
        Path file = Paths.get(directory).resolve(filename);
        Resource resource = null;
        try{
            resource = new UrlResource(file.toUri());
            if (!resource.exists() || !resource.isReadable()){
                throw new RuntimeException("ff");
            }
        }catch (MalformedURLException e ){
            throw new RuntimeException("Error: "+ e.getMessage());
        }
        return  ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment;filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
