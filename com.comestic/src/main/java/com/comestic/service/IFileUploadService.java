package com.comestic.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Part;

public interface IFileUploadService {
	Map<String,List<Long>> save(Collection<Part> parts, String rootPath, String type);
}
