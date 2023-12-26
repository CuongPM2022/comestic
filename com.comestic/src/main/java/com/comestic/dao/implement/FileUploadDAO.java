package com.comestic.dao.implement;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.Part;
import com.comestic.dao.IFileUploadDAO;
import com.comestic.dao.IImageDAO;
import com.comestic.utils.StringRandom;

public class FileUploadDAO implements IFileUploadDAO {
	
	@Inject
	private IImageDAO imageDAO;
	private List<Long> element;
	private String[] arrTemp;
	private String strTemp, savePath;
	private File file;
	private Long imageId;

	@Override
	public Map<String, List<Long>> save(Collection<Part> parts, String path) {
		Map<String,List<Long>> listResult = new HashMap<>();
		
		String inputName;
		file = new File(path);
		if(!file.exists()) {
			file.mkdirs();
		}
		
		for(Part part:parts) {
			inputName = part.getName();
			element = listResult.get(inputName);
			
			if(element == null) {
				element = new ArrayList<>();
				listResult.put(inputName, element);
			}
			
			String fileName = getFileName(part);
			do {
				savePath = path + File.separator + fileName;
			} while(new File(savePath).exists());
			
			imageId = imageDAO.save(fileName).getId();
			element.add(imageId);
			try {
				part.write(savePath);
			} catch (IOException e) {
				e.printStackTrace();
				listResult = null;
			}
		}
		return listResult;
	}
	
	private String getFileName(Part part) {
		strTemp = StringRandom.nextString(10) + (new Date()).getTime();
		arrTemp = part.getSubmittedFileName().split("\\.");
		if(arrTemp.length >= 2) {
			strTemp += "." + arrTemp[arrTemp.length - 1];
		}
		return strTemp;
	}

}
