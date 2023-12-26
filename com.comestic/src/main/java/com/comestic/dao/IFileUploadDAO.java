package com.comestic.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Part;

public interface IFileUploadDAO {
	Map<String,List<Long>> save(Collection<Part> parts, String path);
}
