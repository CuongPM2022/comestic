async function category() {
	let temp, temp1;
	const $ = document.querySelector.bind(document);
	const formCategory = $('.form__contain');
	const overload = $('.overload');
	const fileInput = $('.form__item__input[type="file"]');
	const selectParentElement = $('#select__category');
	let formData, id = null;
	var data = {}, objectCategory = {};
	let offset = 0, maxitem = 7, currentPage = 1;

	/* Functions Common */
	function getData(path, callback)
	{
		return fetch(path)
			   .then(response => response.json())
			   .then(data => callback(data));
	}

	function deleteData(path, callback)
	{
		return fetch(path, {
					method: 'DELETE'
				})
			   .then(response => response.json())
			   .then(data => callback(data));
	}

	function postData(path, data, callback, method='POST') {
		return fetch(path, {
			method: method,
			body: data
		})
		.then(response => response.json())
		.then(data => callback(data));
	}

	function handleClass(element, action = 'add', value = 'active') {
		if(action == 'add') {
			element.classList.add(value);
		}
		else if(action == 'remove') {
			element.classList.remove(value);
		}
	}

	function getParentBySelector(element, selector)
	{
		if(element.matches(selector)) { return element; }
		var temp = element;
		do {
			temp = temp.parentElement;
		} while(!temp.matches(selector));
		return temp;
	}

	function renderUISelectCategory() {
		return getData('/api-category?action=listParent', (listParentCategory) => {
			temp1 = '<option value="0">--- Chọn danh mục cha ---</option>';
			listParentCategory.forEach(category => {
				temp1 += `
					<option value="${category.id}">${category.name}</option>
				`;
			});
			selectParentElement.innerHTML = temp1;
		});
		
	}

	function getAllCategory() {
		return getData(`/api-category?action=allCategory`, dataResponse => {
				objectCategory = dataResponse;
				data.totalItem = objectCategory.totalItem;
		});
	}

	await renderUISelectCategory();

	await getAllCategory();

	function renderUIListCategory() {
		temp = '';
		
		let parentName;
		data.listData.forEach((category,index) => {
			parentName = category.parentCategoryName;
			temp += 
			`
				<tr data-key="${category.id}">
                    <td><input type="checkbox" ></td>
                    <td>${index + 1}</td>
                    <td>${category.code}</td>
                    <td>${category.name}</td>
                    <td>${parentName ?  parentName: 'Không'}</td>
                    <td><img src="${category.imageName}"></td>
                    <td>${category.strCreateDate}</td>
                    <td>${category.createBy}</td>
                    <td class="tbCenter">
                         <span class="td__action td__action--edit">
                            <i class="fa-regular fa-pen-to-square"></i>
                        </span>

                        <span class="td__action td__action--delete">
                            <i class="fa-solid fa-trash"></i>
                        </span>
                    </td>
                </tr>
			`;

			
		});

		$('.listTable tbody').innerHTML = temp;
	}

	function getItemPaging(offset,maxitem) {
		data.listData = [];
		for(let i = 0; i < maxitem; i++) {
			temp = offset + i;
			if(temp >= objectCategory.listData.length) {
				return;
			}
			else {
				data.listData.push(objectCategory.listData[temp]);
			}
		}
	}

	function getNewPage(page, check = false) {
		if(page != currentPage || check) {
			currentPage = page;
			offset = (page - 1) * maxitem;
			getItemPaging(offset,maxitem);
			renderUIListCategory();
			//pagination
			temp = Math.ceil(data.totalItem/maxitem);
			new Pagination('.listPaging', {
		        'currentPage' : currentPage,
		        'totalPage' : temp,
		        'maxpageItem' : maxitem,
		        'callback' : getNewPage
		    });
		}
	}
	getNewPage(1,true);

	function resetForm() {
		formCategory.querySelector('input[name="name"]')
					.value = '';
		formCategory.querySelector('input[name="code"]')
					.value = '';
		formCategory.querySelector('.form__review__image')
					.src = 'views/source/image/default_image.jpg';
		formCategory.querySelector('input[type="file"]')
					.value = '';
		selectParentElement.selectedIndex = 0;
	}

	function bindFormData(category) {
		formCategory.querySelector('input[name="name"]')
					.value = category.name;
		formCategory.querySelector('input[name="code"]')
					.value = category.code;
		formCategory.querySelector('.form__review__image')
					.src = category.imageName;
		// select parent id
		const options = selectParentElement.options;
		let length = options.length;
		for(let i=0; i < length; i++) {
			if(options[i].value == category.parentId) {
				selectParentElement.selectedIndex = i;
				break;
			}
		}
	}

	const handleCategory = {
		create: function() {
			formCategory.querySelector('.form__button.form-submit')
					.innerText = 'Thêm danh mục';
			fileInput.setAttribute('rules','require');
			id = null;
			handleClass(formCategory,'add','popup');
			handleClass(overload,'add');
		},
		edit: function(categoryId) {
			formCategory.querySelector('.form__button.form-submit')
					.innerText = 'Cập nhật danh mục';
			temp = data.listData.find(category => category.id == categoryId);
			bindFormData(temp);
			fileInput.removeAttribute('rules');
			id = categoryId;
			handleClass(formCategory,'add','popup');
			handleClass(overload,'add');
		},
		delete: function(listId) {
			let temp = encodeURIComponent(JSON.stringify(listId));
			deleteData(`/api-category?listId=${temp}`, data => {
				if(data.length < listId.length) {
					temp = listId.length - data.length;
					alert(`Không thể xóa ${temp} danh mục có chứa các sản phẩm trong hóa đơn chưa thanh toán!`);
				} else {
					alert('Xóa thành công!');
				}
				location.reload();
			});
		}
	};

	function uploadFile(data) {
		formData = new FormData();
		formData.append('image', fileInput.files[0]);
		return postData('/api-file-upload?type=category',formData, image => {
			data.imageId = image.image[0];
			data.imageName = null;
		});
	}

	async function create(data) {
		await uploadFile(data);
		postData('/api-category', JSON.stringify(data), category => {
			let parentId = category.parentId;
			if(parentId != 0) {
				category.parentCategoryName = objectCategory.listData
											.find(cate => cate.id == parentId)
											.name;
			}
			objectCategory.listData.unshift(category);
			renderUISelectCategory();
			resetForm();
			getNewPage(1,true);
		});
	}

	async function update(data) {
		data.id = id;
		data.imageName = null;
		if(fileInput.files && fileInput.files.length > 0) {
			await uploadFile(data);
		}
		postData('/api-category', JSON.stringify(data), category => {
			temp = objectCategory.listData;
			for(let i=0; i < temp.length; i++) {
				if(temp[i].id == category.id) {
					temp[i] = category;
					break;
				}
			}
			renderUISelectCategory();
			resetForm();
			getNewPage(1,true);
			
		}, 'PUT');
	}

	function validateAndSubmit(data) {
		if(!id) {
			create(data);
		}
		else {
			update(data);
		}
	}

	new listTable('#list_contain', data => {
		handleCategory[data.action](data.data);
	});

	const validate = new Validate('.form', data => {
		validateAndSubmit(data);
		overload.click();
	});

	// Assign event
	overload.onclick = function() {
		handleClass($('.form__contain'),'remove','popup');
	}

	formCategory.querySelector('.form__review__image').onclick = function() {
		fileInput.click();
	}

	fileInput.onchange = function() {
		temp = this.files[0];
		if(temp) {
			formCategory.querySelector('.form__review__image')
						.src = URL.createObjectURL(temp);
		}
	}

}
category();