package nl.novi.FaunaFinder.service;
import nl.novi.FaunaFinder.exceptions.ImageNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import nl.novi.FaunaFinder.models.Image;
import nl.novi.FaunaFinder.repositories.FileUploadRepository;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@CrossOrigin
public class ImageService {

    private final FileUploadRepository repo;
    private final String storageLocation;

    public ImageService(@Value("${my.upload_location}") String storageLocation, FileUploadRepository repo) throws IOException {
        Path storagePath = Paths.get(storageLocation).toAbsolutePath().normalize();
        this.storageLocation = storageLocation;
        this.repo = repo;

        Files.createDirectories(storagePath);
    }

    public String storeFile(String id, MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(id + "_" + file.getOriginalFilename());
        Path filePath = Paths.get(storageLocation + "\\" + fileName).toAbsolutePath().normalize();

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        repo.save(new Image(fileName));
        return fileName;
    }


    public Resource downLoadFile(String fileName) {

        Path path = Paths.get(storageLocation).toAbsolutePath().resolve(fileName);

        Resource resource;

        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Issue in reading the file", e);
        }

        if(resource.exists()&& resource.isReadable()) {
            return resource;
        } else {
            throw new ImageNotFoundException();
        }
    }
}
