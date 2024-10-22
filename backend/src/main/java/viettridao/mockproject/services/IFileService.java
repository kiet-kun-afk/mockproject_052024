package viettridao.mockproject.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * IFileService
 * Version: 2.0
 * Date: 5/23/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/23/2024 kiet-kun-afk Create
 */
public interface IFileService {
    // public void saveImage(List<MultipartFile> images, Property property) throws
    // Exception;

    public List<String> saveImages(List<MultipartFile> images) throws Exception;

    public Boolean isImage(MultipartFile image);

    public String saveAttachment(MultipartFile attachment) throws Exception;
}
