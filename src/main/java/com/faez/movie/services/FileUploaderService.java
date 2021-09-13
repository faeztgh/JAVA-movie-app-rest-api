package com.faez.movie.services;

import com.faez.movie.exceptions.ApiRequestException;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@Slf4j
public class FileUploaderService {

    public String uploadFile(MultipartFile uploadFile, String uploadPath) {

        String uploadedFolder = new File("").getAbsolutePath() + uploadPath;
        String fileName = "(" + RandomString.make(5) + ")-" + uploadFile.getOriginalFilename();

        if (uploadFile.isEmpty()) {
            throw new ApiRequestException("Please select a file!");
        }

        try {
            saveUploadedFiles(List.of(uploadFile), uploadedFolder, fileName);
        } catch (IOException e) {
            throw new ApiRequestException("Something went wrong!" + e.getMessage());
        }

        return "File '" + uploadFile.getOriginalFilename() + "' Successfully Uploaded";
    }


    //save file
    private void saveUploadedFiles(List<MultipartFile> files, String uploadFolder, String fileName) throws IOException {

        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue; //next pls
            }

            File directory = new File(uploadFolder);
            if (!directory.exists()) {
                log.info("dir not exist");
                directory.mkdirs();
            }

            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadFolder + fileName);
            Files.write(path, bytes);
        }
    }
}

