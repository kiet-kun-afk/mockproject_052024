package viettridao.mockproject.services.imp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import viettridao.mockproject.services.IFileService;

/**
 * FileService
 * Version: 2.0
 * Date: 5/23/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/23/2024 kiet-kun-afk Create
 */
@Service
@RequiredArgsConstructor
public class FileService implements IFileService {

    // @Override
    // public void saveImage(List<MultipartFile> images, Property property) throws
    // Exception {
    // if (images == null || images.isEmpty()) {
    // return;
    // }
    // List<Photo> imagePaths = new ArrayList<>();

    // for (MultipartFile image : images) {

    // if (!isImageFile(image) || image.getOriginalFilename() == null) {
    // throw new IOException("Invalid image format");
    // }

    // String directory = "images/";
    // String fileName = UUID.randomUUID().toString() + "_" +
    // image.getOriginalFilename();
    // Path filePath = Paths.get(directory, fileName);
    // Files.createDirectories(filePath.getParent());
    // Files.write(filePath, image.getBytes());
    // Photo photo = new Photo(null, property, fileName, null);
    // imagePaths.add(photo);
    // }
    // photoRepository.saveAll(imagePaths);
    // }
    @Override
    public List<String> saveImages(List<MultipartFile> images) throws Exception {
        if (images == null || images.isEmpty()) {
            return null;
        }
        List<String> imagePaths = new ArrayList<>();

        for (MultipartFile image : images) {

            if (!isImageFile(image) || image.getOriginalFilename() == null) {
                throw new IOException("Invalid image format");
            }

            String directory = "images/";
            String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
            Path filePath = Paths.get(directory, fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, image.getBytes());
            imagePaths.add(fileName);
        }
        return imagePaths;
    }

    private boolean isImageFile(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && contentType.startsWith("image/");
    }

    // not use yet
    @Override
    public Boolean isImage(MultipartFile image) {
        throw new UnsupportedOperationException("Unimplemented method 'isImage'");
    }

    @Override
    public String saveAttachment(MultipartFile attachment) throws Exception {
        if (attachment == null) {
            return "Empty attachment";
        }

        String directory = "attachments/";
        String fileName = UUID.randomUUID().toString() + "_" + attachment.getOriginalFilename();
        Path filePath = Paths.get(directory, fileName);
        Files.createDirectories(filePath.getParent());
        Files.write(filePath, attachment.getBytes());

        return fileName;
    }

}
