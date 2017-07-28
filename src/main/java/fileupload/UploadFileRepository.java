package fileupload;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fileupload.model.FileEO;
import fileupload.resources.FileRO;

public interface UploadFileRepository extends CrudRepository<FileEO, Long> {
	
	List<FileEO> findAll();
	
	FileEO save(FileEO f);

}
