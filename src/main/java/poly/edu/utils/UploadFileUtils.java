package poly.edu.utils;

import java.io.File;
import java.nio.file.Files;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileUtils {
	public File handleUploadFile(
		MultipartFile uploadFile ,String fileName	
	) {
		String folderPath = "C:/Users/admin/eclipse-workspace/AsmJava6/src/main/webapp/WEB-INF/images";
		File myUploadFoder = new File(folderPath);
		
		//Kiem tra thu muc luu tru file co ton tai hay khong
		if(!myUploadFoder.exists()) {
			myUploadFoder.mkdirs();
		}
		File saveFile = null;
			
		try {
			saveFile = new File(myUploadFoder,fileName);
			uploadFile.transferTo(saveFile);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return saveFile;
	}

	
}
