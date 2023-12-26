function controller() {
	let tempElement;
	
	// Function area

	/* object handle attribute */
	const handleAttribute = {
		dbListAttribute: [],
		listValidateAttribute: {},
		add: function() {
			let combobox = $('.attribute__select');
			let id = combobox.options[combobox.selectedIndex].value;
			if(!id) {
				alert('Bạn chưa chọn thuộc tính!');
				return;
			}
			temp = {
				id: id,
				name: combobox.options[combobox.selectedIndex].innerText
			};
			const attrTemp = model.attribute.save(temp);
			const attrDOM = view.attribute.add(attrTemp);
			this.showError(attrTemp.code);

			// tao doi tuong validate cho no
			const validate = new Validate('#attr-item-' + attrTemp.code);
			this.listValidateAttribute[attrTemp.code] = validate;

			//// check lai cac variety
			handleVariety.checkAll();
		},
		createNew: function() {
			let name = prompt('Nhập tên thuộc tính: ');
			if(!name) {
				return;
			}
			const attrTemp = model.attribute.save({'name': name});
			const attrDOM = view.attribute.add(attrTemp);
			this.showError(attrTemp.code);

			// tao doi tuong validate cho no
			const validate = new Validate('#attr-item-' + attrTemp.code);
			this.listValidateAttribute[attrTemp.code] = validate;

			// check lai cac variety
			handleVariety.checkAll();
		},
		update: function(code) {
			// validate
			const data = this.checkView(code);
			if(!data) {
				this.showError(code);
				return;
			}
			if(data.value.startsWith('|') || data.value.endsWith('|')) {
				alert('Giá trị thuộc tính chưa hợp lệ!');
				this.showError(code);
				return;
			}
			let tempAttr = {
				code: code,
				name: data.name,
				value: data.value.split('|')
			};

			tempAttr = model.attribute.save(tempAttr);
			view.attribute.update(tempAttr);
			this.hideError(code);

			// check lại các Variety
			handleVariety.checkAll();
		},
		delete: function(code) {
			let check;
			check = confirm('Bạn muốn xóa thuộc tính?');
			if(!check) {
				return;
			}

			check = model.attribute.delete(code);
			if(!check) {
				alert('Xóa thuộc tính thất bại!');
				return;
			}
			delete this.listValidateAttribute[code];
			view.attribute.delete(code);

			// check variety
			handleVariety.checkAll();
		},
		renderListSelect: function(codeVariety = null) {
			const select = view.attribute.renderListSelect(model.attribute.findAll(), codeVariety);
		},
		getListAttributeSelect: function() {
			const listTemp = [];
			const listSelect = $$('.variety__option select');
			for(const select of listSelect) {
				const code = select.dataset.key;
				const value = select.value;
				if(!code || !value) {
					return null;
				}
				listTemp.push({
					code: code,
					value: value
				});
			}
			return listTemp;
		},
		getListAttributeFromExistedProduct: function(product = {}) {
			const listAttribute = [];
			let temp;
			product.listVariety.forEach(variety => {
				variety.listAttribute.forEach(attr => {
					temp = listAttribute.find(item => item.id == attr.id);
					if(!temp) {
						listAttribute.push({
							id: attr.id,
							name: attr.name,
							value: [attr.value]
						});
					}
					else {
						if(!temp.value.includes(attr.value)) {
							temp.value.push(attr.value);
						}
					}
				});
			});
			return listAttribute;
		},
		renderAttributeFromExistedProduct: function() {
			let temp;
			let listAttribute = this.getListAttributeFromExistedProduct(handleProduct.getProduct());
			listAttribute.forEach(attr => {
				temp = model.attribute.save(attr);
				view.attribute.add(temp);
				this.listValidateAttribute[temp.code] = new Validate(`#attr-item-${temp.code}`);
			});
		},
		showError:function(code) {
			const ele = $(`#attr-item-${code}`);
			handleClass(ele,'add','error');
		},
		hideError: function(code) {
			const ele = $(`#attr-item-${code}`);
			handleClass(ele, 'remove', 'error');
		},
		checkView: function(code) {
			return this.listValidateAttribute[code].validateForm();
		},
		checkViewAll: function() {
			let check = true;
			for(const key in this.listValidateAttribute) {
				if(!this.listValidateAttribute[key].validateForm()) {
					check = false;
					break;
				}
			}
			return check;
		},
		setup: function(dbListAttribute = []) {
			this.dbListAttribute = [...dbListAttribute];
			this.renderListSelect(this.dbListAttribute);
		},
		upload: async function() {
			let listAttrNotHasId = model.attribute.findAllNotHasId();
			if(listAttrNotHasId.length == 0) {
				return null;
			}
			await postData('/api-attribute?action=list', listAttrNotHasId, response => listAttrNotHasId = response);
			return listAttrNotHasId;
		}

	};

	const handleVariety = {
		varietySingleValidate: new Validate('.product__single'),
		listValidateVariety: {},
		displaySelectAttr: function() {
			handleClass($('.variety__option'));
		},
		hideSelectAttr: function() {
			handleClass($('.variety__option'), 'remove');
		},
		addNew: function() {

			if(!model.attribute.checkAll() || !handleAttribute.checkViewAll()) {
				alert(`Không tồn tại hoặc tồn tại thuộc tính không hợp lệ!`);
				return;
			}

			const select = $('.variety__select').value;
			// them mot bien the
			if(select == 1) {
				handleAttribute.renderListSelect();
				this.displaySelectAttr();
			} 
			// them tat ca bien the chua ton tai
			else {
				const list = model.variety.getAllListAttributeNotExist();
				list.forEach(listAttr => {
					this.handleAddOne({
						listAttribute: listAttr
					});
				});
				handleVariety.hideSelectAttr();
			}
		},
		handleAddOne: function(variety) {
			let tempVariety = model.variety.save(variety);
			const code = tempVariety.code;

			// render ra giao dien
			// them moi
			if(!variety.code) {
				view.variety.add(tempVariety);
				this.showError(tempVariety.code);

				// tao doi tuong validate
				this.listValidateVariety[code] = new Validate(`#item-var-${code}`);
			}
			else {
				// cap nhat data
				if(!variety.listAttribute) {
					if(this.check(code)) {
						this.hideError(code);
					}
					else {
						this.showError(code);
					}
				}
				// cap nhat thuoc tinh
				else {
					if(this.checkAttribute(code) && this.checkData(code)) {
						this.hideError(code);
					}
					else {
						this.showError(code);
					}
				}

				view.variety.update(tempVariety);
			}
		},
		addOneVariety: function(code = null) {
			const listAttrSelect = handleAttribute.getListAttributeSelect();
			if(!listAttrSelect) {
				alert('Bạn chưa chọn giá trị thuộc tính!');
				return;
			}

			const varietyExist = model.variety.existWithListAttribute(listAttrSelect);
			if(varietyExist && varietyExist.code != code) {
				alert('Đã tồn tại biến thể với các thuộc tính vừa chọn!');
				return;
			}

			let tempVariety = {
				listAttribute: listAttrSelect
			};
			if(code) {
				tempVariety.code = code;
			}
			this.handleAddOne(tempVariety);

			// an bang chon attribute
			this.hideSelectAttr();
		},
		updateData: function(code) {
			let variety = this.checkView(code);
			if(!variety) {
				return;
			}

			variety.code = code;
			this.handleAddOne(variety);
		},
		delete: function(code) {
			const check = confirm('Bạn muốn xóa biến thể?');
			if(!check) {
				return;
			}
			
			model.variety.delete(code);
			delete this.listValidateVariety[code];
			view.variety.delete(code);
		},
		checkAttribute: function(code) {
			return model.variety.checkAttribute(code);
		},
		checkData: function(code) {
			return model.variety.checkData(code);
		},
		checkView: function(code) {
			const tempElement = $(`#item-var-${code}`);
			const imageEle = $('.form__review__image', tempElement);
			const inputFile = $('input[type="file"]', tempElement);

			if(imageEle.dataset.selected) {
				// khi da co anh moi vao day (update)
				inputFile.setAttribute('rules', '');
			}
			
			const result = this.listValidateVariety[code].validateForm();
			if(!result) {
				return null;
			}
			
			result.percentDes = (result.price - result.priceSale) / result.price * 100;
			return result;
		},
		checkViewForSimpleProduct: function() {
			const tempElement = $('.product__single');
			const imageEle = $('.form__review__image', tempElement);
			const inputFile = $('input[type="file"]', tempElement);
			if(imageEle.dataset.selected) {
				inputFile.setAttribute('rules', '');
			}
			else {
				inputFile.setAttribute('rules', 'require');
			}
			const result = this.varietySingleValidate.validateForm();
			result.percentDes = (result.price - result.priceSale) / result.price * 100;
			return result;
		},
		check: function(code) {
			// check model
			if(!this.checkAttribute(code)) {
				return false;
			}
			// check view
			return !!this.checkView(code);
		},
		checkAll: function() {
			let code = null;
			let listVariety = model.variety.findAll();
			listVariety.forEach(variety => {
				code = variety.code;
				if(this.checkAttribute(code) && this.checkData(code)) {
					this.hideError(code);
				}
				else {
					this.showError(code);
				}
			});
		},
		showError: function(code) {
			const ele = $(`#item-var-${code}`);
			handleClass(ele, 'add', 'error');
		},
		hideError: function(code) {
			const ele = $(`#item-var-${code}`);
			handleClass(ele, 'remove', 'error');
		},
		renderVarietyFromExistedProduct: function() {
			let temp = null;
			// them code cho cac attribute
			handleProduct.getProduct().listVariety.forEach(variety => {
				variety.listAttribute.forEach(attr => {
					attr.code = model.attribute.findById(attr.id).code;
				});

				// them cac variety vao model
				temp = model.variety.save(variety);
				variety.code = temp.code;

				// render ra giao dien
				view.variety.add(variety);

				// them validate
				this.listValidateVariety[variety.code] = new Validate(`#item-var-${variety.code}`);
			});
			view.variety.renderListVarietyFromExistedProduct(handleProduct.getProduct());
		},
		uploadImage: async function(listVariety) {
			let temp = null;
			let listResult = null;
			const listFile = [];
			listVariety.forEach(variety => {
				temp = $(`#item-var-${variety.code}`);
				temp = $('input[type="file"]', temp).files;
				if(temp.length > 0) {
					listFile.push({
						name: variety.code,
						data: temp[0]
					});
				}
			});

			if(listFile.length == 0) {
				return null;
			}

			listResult = await uploadFile(listFile, '/api-file-upload?type=product');
			return listResult;
		}

	};

	const handleCategory = {
		imageNewCategory: null,
		categoryForm: $('.createCategory.form'),
		categoryValidate: new Validate('.createCategory.form'),
		setup: function(listDBCategory) {
			// import vao model
			const listResult = model.category.importFromListExist(listDBCategory);

			let code = null;
			const categoryId = handleProduct.getCategoryId();
			if(categoryId) {
				const category = model.category.findById(categoryId);
				code = category ? category.code : null;
			}

			// render ra view
			view.category.renderAll(listResult, code);
		},
		addNew: function() {
			let cate = this.categoryValidate.validateForm();
			if(!cate) {
				return;
			}

			cate = model.category.add(cate);
			view.category.renderAll(model.category.findAll(), cate.code);
			this.imageNewCategory = $('input[name="image"]', this.categoryForm).files[0];
			alert('Thêm danh mục thành công!');
		},
		// upload category by code va tra ve id
		upload: async function(code) {
			const category = model.category.findOne(code);
			let id = category.id;
			if(id) {
				return id;
			}

			// upload category
			// upload image
			const listFile = [];
			listFile.push({
				name: 'image',
				data: this.imageNewCategory
			});
			const dataImage = await uploadFile(listFile, '/api-file-upload?type=category');

			category.code = category.code_1;
			// xoa bo truong du thua, them cac truong can thiet
			delete category.image;
			delete category.code_1;
			category.parentId = 0;
			category.level = 0;
			category.imageId = dataImage['image'][0];

			// upload category
			await postData('/api-category', category, response => {
					 id = response.id;
				  });

			return id;
		}
	};

	const handleManufacture = {
		manufactureValidate: new Validate('.createManufacture.form'),
		setup: function(listDBManufacture) {
			// import vao model
			const listResult = model.manufacture.importFromListExist(listDBManufacture);

			let code = null;
			const manufactureId = handleProduct.getManufactureId();
			if(manufactureId) {
				const manufacture = model.manufacture.findById(manufactureId);
				code = manufacture ? manufacture.code : null;
			}

			// render ra view
			view.manufacture.renderAll(listResult, code);
		},
		addNew: function() {
			let manu = this.manufactureValidate.validateForm();
			if(!manu) {
				return;
			}

			manu = model.manufacture.add(manu);
			view.manufacture.renderAll(model.manufacture.findAll(), manu.code);
			alert('Thêm nhà sản xuất thành công!');
		},
		upload: async function(code) {
			const manufacture = model.manufacture.findOne(code);
			let id = manufacture.id;
			if(id) {
				return id;
			}

			// upload manufacture
			manufacture.code = manufacture.code_1;
			delete manufacture.code_1;
			await postData('/api-manufacture', manufacture, response => {
				id = response.id;
			})
			return id;
		}
	};

	const handleProduct = {
		product: {},
		productSimpleElement: $('.product__single'),
		productManyVarietyElement: $('.product__many__variety'),
		commonValidate: new Validate('.createProduct .form__body'),
	    toSimpleProduct: function() {
	    	this.product.typeProductId = 1;
	    },
	    toManyVarietyProduct: function() {
	    	this.product.typeProductId = 2;
	    },
	    isSimpleProduct: function() {
	    	return this.product.typeProductId == 1;
	    },
	    isUpdate: function() {
	    	return this.product.id;
	    },
	    getProduct: function() {
	    	return this.product;
	    },
	    getCategoryId: function() {
	    	return this.product.categoryId;
	    },
	    getManufactureId: function() {
	    	return this.product.manufactureId;
	    },
	    showSimpleProduct: function() {
	    	$('.select__variety').selectedIndex = 0;
	    	handleClass(this.productSimpleElement);
			handleClass(this.productManyVarietyElement, 'remove');
	    },
	    showManyVarietyProduct: function() {
	    	$('.select__variety').selectedIndex = 1;
	    	handleClass(this.productManyVarietyElement);
			handleClass(this.productSimpleElement,'remove');
	    },
	    notify: function(response) {
	    	const type = handleProduct.isUpdate() ? 'Cập nhật':'Thêm mới';
	    	if(response) {
	    		alert(`${type} sản phẩm thành công!`);
	    		window.location = `/admin-product?action=createProduct&id=${response}`;
	    	}
	    	else {
	    		alert(`Đã xảy ra lỗi! ${type} sản phẩm không thành công!`);
	    	}
	    },
	    addNewSimpleProduct: async function(dataCommon) {
			const varietySingleData = handleVariety.checkViewForSimpleProduct();
			if(varietySingleData) { 
				// upload image //
				const listFileImage = $('.product__single input[name="image"]').files;
				if(listFileImage.length > 0) {
					const dataImage = await uploadFile([{
						name: 'single',
						data: listFileImage[0]
					}], '/api-file-upload?type=product');
					varietySingleData.imageId = dataImage['single'][0];
				}
				else {
					varietySingleData.imageId = $('.product__single .form__review__image').dataset.selected;
				}

				// delete thuoc tinh du thua, them thuoc tinh can thiet
				delete varietySingleData.priceSale;
				delete varietySingleData.image;
				varietySingleData.isAvatar = 1;
				varietySingleData.listAttribute = [];

				Object.assign(this.product, dataCommon, {
					numberVariety: 1,
					listVariety: [varietySingleData]
				});
				
				// upload product
				console.log(this.product);
				const method = this.isUpdate() ? 'PUT' : 'POST';
				postData('/api-product', this.product, this.notify, method);
			}
		},

		addNewManyVarietyProduct: async function(dataCommon) {
			// kiem tra lai danh sach variety
	 		let listVariety = model.variety.findAll();
	 		if(listVariety.length == 0) {
	 			alert('Sản phẩm phải có ít nhất một biến thể!');
	 			return;
	 		}

	 		let check = listVariety.every(item => {
	 			return handleVariety.checkView(item.code) && handleVariety.checkAttribute(item.code) 
	 				                                      && handleVariety.checkData(item.code);
	 		});
	 		
	 		if(!check) {
	 			alert('Tồn tại biến thể chưa hợp lệ!');
	 			return;
	 		}

	 		// upload nhung thuoc tinh chua ton tai
	 		const listCodeIdAttribute = await handleAttribute.upload();

	 		// gan lai id cho cac attribute
	 		if(listCodeIdAttribute) {
	 			listCodeIdAttribute.forEach(attr => {
	 				model.attribute.save({
	 					code: attr.code,
	 					id: attr.id
	 				});
	 			});
	 		}

	 		// xu ly upload image cho cac bien the
	 		const listDataImageAfterUpload = await handleVariety.uploadImage(listVariety);
	 		
	 		// gan lai imageId cho cac Variety
	 		if(listDataImageAfterUpload) {
	 			for(const key in listDataImageAfterUpload) {
	 				model.variety.save({
	 					code: key,
	 					imageId: listDataImageAfterUpload[key][0]
	 				});
	 			}
	 			listVariety = model.variety.findAll();
	 		}

	 		//lay danh sach cac attribute cho moi variety
	 		listVariety.forEach(variety => {
	 			variety.listAttribute = model.variety_attribute.findListAttrHasIdByVarietyCode(variety.code);
	 		});

	 		// xoa bo cac cho tiet thua
	 		listVariety.forEach(variety => {
	 			delete variety.code;
	 			delete variety.image;
	 		});

	 		// chon variety la avatar
	 		let varietyAvatar = listVariety.find(item => item.isAvatar == 1);
	 		if(!varietyAvatar) {
	 			listVariety[0].isAvatar = 1;
	 			varietyAvatar = listVariety[0];
	 		}

	 		listVariety.forEach(item => {
	 			if(item != varietyAvatar) {
	 				item.isAvatar = 0;
	 			}
	 		});

	 		Object.assign(this.product, dataCommon, {
	 			numberVariety: listVariety.length,
	 			listVariety: listVariety
	 		});

	 		// upload product
			const method = this.isUpdate() ? 'PUT' : 'POST';
			postData('/api-product', this.product, this.notify, method);

		},
		setup: function(dbProduct) {
	    	// them moi product
	    	if(!dbProduct) {
	    		// don gian: 1, nhieu bien the: 2
	    		this.product.typeProductId = 1;
	    		this.listVariety = [];
	    	}
	    	// cap nhat product
	    	else {
	    		this.product = {...dbProduct};
	    		view.product.renderCommonData(this.product);

	    		// simple product
	    		if(this.isSimpleProduct()) {
	    			this.showSimpleProduct();
	    			view.variety.renderOneVarietyToView(this.product.listVariety[0]);
	    		}
	    		// many variety product
	    		else {
	    			this.showManyVarietyProduct();
	    			handleAttribute.renderAttributeFromExistedProduct();
	    			handleVariety.renderVarietyFromExistedProduct();
	    		}

	    		$('.create__product__apply').innerText = 'Cập nhật sản phẩm';
	    	}
	    },
	    getDataCommon: async function() {
	    	let dataCommon = this.commonValidate.validateForm();

	    	if(!dataCommon) {
	    		return null;
	    	}

	    	// xu ly upload Category
	    	const categoryId = await handleCategory.upload(dataCommon.categoryCode);
	    	dataCommon.categoryId = categoryId;
	    	delete dataCommon.categoryCode;

	    	// xu ly upload manufacture
	    	const manufactureId = await handleManufacture.upload(dataCommon.manufactureCode);
	    	dataCommon.manufactureId = manufactureId;
	    	delete dataCommon.manufactureCode;

	    	// them cac thuoc tinh can thiet
	    	dataCommon.view = 1;
	    	dataCommon.isHot = 0;
	    	dataCommon.star = 5;
	    	dataCommon.totalComment = 0;
	    	dataCommon.totalPreview = 0;

	    	return dataCommon;
	    },
	    upload: async function() {
			let commonData = await this.getDataCommon();
			if(commonData) {
				if(this.isSimpleProduct()) {
					this.addNewSimpleProduct(commonData);
				} 
				else {
					this.addNewManyVarietyProduct(commonData);
				}
			}
		}

};


	/* ======= Main ======= */
	handleProduct.setup(dbProduct);
	handleManufacture.setup(dbListManufacture);
	handleCategory.setup(dbListCategory);
	handleAttribute.setup(dbListAttribute);

	/* ======== Assign Event ========= */
	// click vao chon anh
	$('.body').addEventListener('click', ({target:element}) => {
		if(element.closest('.form__review__image')) {
			tempElement = getParentBySelector(element,'.formGroupt');
			tempElement.querySelector('input[type="file"]').click();
		}
	});

	// hien thi anh khi chon
	$$('.formGroupt input[type="file"]').forEach(ele => {
		ele.addEventListener('change', displayImage);
	});

	// Event add new category
	$('.category_addnew').addEventListener('click', function() {
		handleCategory.addNew();
	});

	// Event add new manufacture
	$('.manufacture_addnew').addEventListener('click', function() {
		handleManufacture.addNew();
	});

	// Event input priceSale
	$('.dataProduct').addEventListener('click', ({target:element}) => {
		if(element.closest('input[name="price"]')) {
			element.onkeyup = (e) => {
				getParentBySelector(e.target,'.row')
				.querySelector('input[name="priceSale"]').value = e.target.value;
			}
		}
	});

	// Event select type product
	$('.select__variety').addEventListener('change', ({target:element}) => {
		const typeProduct = element.options[element.selectedIndex].value;
		if(typeProduct == 0) {
			handleProduct.toSimpleProduct();
			handleProduct.showSimpleProduct();
		}
		else {
			handleProduct.toManyVarietyProduct();
			handleProduct.showManyVarietyProduct();

			// call API get List Attribute 
			if(!dbListAttribute) {
				//console.log("Call API get Attribute List!");
			}
		}
	});

	//Event Button Attribute and Variety
	$('.product__variety__btn').addEventListener('click', function({target:element}) {
		handleClass(element);
		if(element.closest('.attribute')) {
			handleClass(this.querySelector('.variety'), 'remove');
			handleClass(listAttributeElement);
			handleClass(listVarietyElement,'remove');
		}
		else if(element.closest('.variety')) {
			handleClass(this.querySelector('.attribute'),'remove');
			handleClass(listVarietyElement);
			handleClass(listAttributeElement,'remove');
		}


	});

	//Event on Attribute Element
	listAttributeElement.addEventListener('click', function({target:element}) {
		let tempElement = null;
		if(element.closest('.item__short__btn.detail')) {
			tempElement = getParentBySelector(element,'.attribute__item');
			handleClass(tempElement,'remove','short');
		}
		else if(element.closest('.attribute__item__btn.close')) {
			tempElement = getParentBySelector(element,'.attribute__item');
			handleClass(tempElement,'add','short');
		}
		else if(element.closest('.attribute__btn.add')) {
			handleAttribute.add();
		}
		else if(element.closest('.attribute__btn.add_new')) {
			handleAttribute.createNew();
		}
		else if(element.closest('.attribute__item__btn.save')) {
			const codeAttribute = getParentBySelector(element, '.attribute__item').dataset.key;
			handleAttribute.update(codeAttribute);
		}
		else if(element.closest('.item__short__btn.delete') || element.closest('.attribute__item__btn.delete')) {
			const codeAttribute = getParentBySelector(element, '.attribute__item').dataset.key;
			handleAttribute.delete(codeAttribute);
		}
	});

	//Event on Variety Element
	listVarietyElement.addEventListener('click', function({target:element}) {
		let tempElement = null;
		if(element.closest('.item__short__btn.detail')) {
			tempElement = getParentBySelector(element,'.variety__item');
			handleClass(tempElement,'remove','short');
		}
		else if(element.closest('.attribute__item__btn.close')) {
			tempElement = getParentBySelector(element,'.variety__item');
			handleClass(tempElement,'add','short');
		}
		else if(element.closest('.btn.variety__btn')) {
			handleVariety.addNew();
		}
		else if(element.closest('.variety__option__btn')) {
			const codeVariety = getParentBySelector(element, '.variety__option').dataset.key;
			handleVariety.addOneVariety(codeVariety);
		}
		else if(element.closest('.variety__lack__attr')) {
			const codeVariety = getParentBySelector(element,'.variety__item').dataset.key;
			handleAttribute.renderListSelect(codeVariety);
			handleVariety.displaySelectAttr();
		}
		else if(element.closest('.item__short__btn.delete') || element.closest('.attribute__item__btn.delete')) {
			const codeVariety = getParentBySelector(element, '.variety__item').dataset.key;
			handleVariety.delete(codeVariety);
		}
		else if(element.closest('.attribute__item__btn.save')) {
			const codeVariety = getParentBySelector(element, '.variety__item').dataset.key;
			handleVariety.updateData(codeVariety);
		}
	});

	// button add new product
	$('.btn.create__product__apply').addEventListener('click', () => {
		handleProduct.upload();
	});
}

