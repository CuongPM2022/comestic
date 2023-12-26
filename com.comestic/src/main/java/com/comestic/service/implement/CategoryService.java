package com.comestic.service.implement;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.comestic.constant.PathConstant;
import com.comestic.dao.ICategoryDAO;
import com.comestic.dao.implement.ImageDAO;
import com.comestic.model.CategoryModel;
import com.comestic.service.ICategoryService;
import com.comestic.utils.Paging;

public class CategoryService implements ICategoryService {
	
	@Inject
	private ICategoryDAO categoryDAO;
	@Inject
	private ImageDAO imageDAO;
	
	private Map<String, Object> map;
	private String path, imageName;
	private Long imageId, parentId;

	@Override
	public Map<String, Object> findAll(Paging paging) {
		map = new HashMap<String, Object>();
		map.put("listData", categoryDAO.findAll(paging));
		map.put("totalItem", categoryDAO.totalItem(paging));
		return map;
	}

	@Override
	public Integer totalItem(Paging paging) {
		return categoryDAO.totalItem(paging);
	}

	@Override
	public CategoryModel save(CategoryModel categoryModel, String rootPath) {
		CategoryModel temp;
		if(categoryModel.getId() == null) {
			temp = categoryDAO.create(categoryModel);
		}
		else 
		{
			CategoryModel oldCategoryModel = categoryDAO.findOne(categoryModel.getId());
			imageId = categoryModel.getImageId();
			if(imageId != null) {
				imageName = imageDAO.findOne(oldCategoryModel.getImageId()).getName();
				path = rootPath + PathConstant.CATEGORY + File.separator + imageName;
				//xoa anh cu
				(new File(path)).delete();
				oldCategoryModel.setImageId(categoryModel.getImageId());
			}
			
			oldCategoryModel.setName(categoryModel.getName());
			oldCategoryModel.setCode(categoryModel.getCode());
			oldCategoryModel.setParentId(categoryModel.getParentId());
			oldCategoryModel.setUserId(categoryModel.getUserId());
			parentId = categoryModel.getParentId();
			if(parentId != 0) {
				oldCategoryModel.setLevel(categoryDAO.findOne(parentId).getLevel() + 1);
			} else {
				oldCategoryModel.setLevel(0);
			}

			temp = categoryDAO.update(oldCategoryModel);
		}
		return temp;
	}

	@Override
	public List<Long> deleteList(List<Long> listId) {
		List<Long> listResult = new ArrayList<>();
		Long idTemp = null;
		for(Long id:listId) {
			idTemp = categoryDAO.deleteOne(id);
			if(idTemp != null) {
				listResult.add(idTemp);
			}
		}
		return listResult;
	}

	@Override
	public List<CategoryModel> findListParent() {
		return categoryDAO.findListParent();
	}

	@Override
	public List<CategoryModel> findListHasNotChild() {
		return categoryDAO.findListHasNotChild();
	}

}
