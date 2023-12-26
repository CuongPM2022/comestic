package com.comestic.service.implement;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.Part;

import com.comestic.constant.PathConstant;
import com.comestic.dao.IFileUploadDAO;
import com.comestic.service.IFileUploadService;

public class FileUploadService implements IFileUploadService {
	
	@Inject
	private IFileUploadDAO fileUploadDAO;

	@Override
	public Map<String, List<Long>> save(Collection<Part> parts, String rootPath, String type) {
		String path = "";
		if(type.equals("category")) {
			path = PathConstant.CATEGORY;
		}
		else if(type.equals("product")) {
			path = PathConstant.PRODUCT;
		}
		
		if(path.equals("")) {
			return null;
		} else {
			return fileUploadDAO.save(parts, rootPath + path);
		}
	}

}
