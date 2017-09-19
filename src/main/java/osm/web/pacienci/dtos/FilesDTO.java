package osm.web.pacienci.dtos;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class FilesDTO {
	
	private List<MultipartFile> file;

	public List<MultipartFile> getFile() {
		return file;
	}

	public void setFiles(List<MultipartFile> file) {
		this.file = file;
	}
	

}
