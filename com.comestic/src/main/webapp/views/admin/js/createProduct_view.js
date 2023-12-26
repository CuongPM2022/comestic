const view = {
	attribute: {
		listAttribute: $('.attribute__list'),
		// them attribute - tra ve attribute vua them
		add: function(attribute) {
			/* Short: .short
            Error data: .error */
			let html = `
				<div class="attribute__item short" id="attr-item-${attribute.code}" data-key="${attribute.code}">
                    <div class="attribute__item__short">
                        <p class="item__short__text">${attribute.name}</p>
                        <p>
                            <span class="item__short__btn detail">
                                <i class="fas fa-info-circle"></i>
                            </span>
                            <span class="item__short__btn delete">
                                <i class="fa-solid fa-trash"></i>
                            </span> 
                        </p>
                    </div>

                    <div class="attribute__item__detail">
                        <div class="row">
                            <div class="col p-4">
                                <div class="formGroupt">
                                    <p class="form__item__name">Tên thuộc tính</p>
                                    <input class="form__item__input" name="name" type="text" value="${attribute.name}" rules="require">
                                    <!-- show: .show -->
                                    <span class="form-message">Trường này không thể bỏ trống</span>
                                </div>
                            </div>


                            <div class="col p-8">
                                <div class="formGroupt">
                                    <p class="form__item__name">Giá trị các thuộc tính</p>
                                    <textarea class="form__item__input"
                                              placeholder="Lớn|Nhỏ|Vừa" 
                                              rules="require" 
                                              name="value">${ attribute.value ? attribute.value.join('|') : '' }</textarea>
                                    <!-- show: .show -->
                                    <span class="form-message">Trường này không thể bỏ trống</span>
                                </div>
                            </div>

                            <div class="col p-4">
                                <div class="formGroupt">
                                    <input class="form__item__input" checked type="checkbox"> Nhận biết qua ảnh
                                </div>
                            </div>

                            <div class="col p-8" align="right">
                                <button class="btn attribute__item__btn close">Thu gọn</button>
                                <button class="btn attribute__item__btn delete">Xóa</button>
                                <button class="btn attribute__item__btn save">Lưu lại</button>
                            </div>
                        </div>
                    </div>
                </div>`;
                const tempElement = document.createElement('div');
                this.listAttribute.appendChild(tempElement);
                tempElement.outerHTML = html;
                return tempElement;
		},
		// cap nhat - tra ve phan tu duoc cap nhat
		update: function(attribute) {
			const tempAttr = $(`#attr-item-${attribute.code}`, this.listAttribute);
			$('.item__short__text', tempAttr).innerText = attribute.name;
			handleClass(tempAttr, 'add', 'short');
			return tempAttr;
		},
		delete: function(code) {
			let check = false;
			const tempAttr = $(`#attr-item-${code}`, this.listAttribute);
			this.listAttribute.removeChild(tempAttr);
			check = true;
			return check;
		},
        renderListSelect: function(listAttribute = [], codeVariety) {
            let varietyTemp = null;
            if(codeVariety) {
                varietyTemp = model.variety.findOne(codeVariety);
            }
            
            let html = ``;
            let attrTemp = null;
            listAttribute.forEach(attr => {
                // tim thuoc tinh giong thuoc tinh cua bien the
                attrTemp = varietyTemp && varietyTemp.listAttribute.find(item => item.codeAttribute == attr.code);

                let optionHTML = '';
                attr.value.forEach(value => {
                    optionHTML += `<option ${attrTemp != null && attrTemp.value == value ? 'selected':''} value="${value}">
                                        ${value}
                                    </option>`;
                });

                html += `
                        <div class="col p-6">
                            <div class="formGroupt small">
                                <select class="form__item__input" data-key="${attr.code}">
                                    <option value="">Chọn ${attr.name}</option>
                                    ${optionHTML}
                                </select>
                                <span class="form-message">Trường này không thể bỏ trống</span>
                            </div>
                        </div>`;
            });
            $('.variety__option .row').innerHTML = html;
            const temp = $('.variety__option');
            if(codeVariety) {
                temp.dataset.key = codeVariety;
            }
            else {
                delete temp.dataset.key;
            }
            $('.variety__option__btn', temp).innerText = codeVariety ? 'Cập nhật biến thể' : 'Thêm biến thể';
            return temp;
        }

	},
    variety: {
        listVariety: $('.variety__list'),
        varietySimple: $('.product__single'),
        add: function(variety) {
            /* Short: .short
               error data: .error */

            let listAttrHTML = '';
            variety.listAttribute.forEach(attr => {
                listAttrHTML += `<span class="btn variety__short__value">${attr.value}</span>`;
            });

            const html = 
                        `<div class="variety__item short" id="item-var-${variety.code}" data-key="${variety.code}">
                            <div class="variety__item__short">
                                <div class="variety__short__list">
                                    ${listAttrHTML}
                                </div>
                                <span class="variety__lack__attr"><i class="fa-solid fa-gear"></i></span>
                                <div align="right" class="variety__item__action">
                                        <span class="item__short__btn detail">
                                            <i class="fas fa-info-circle"></i>
                                        </span>
                                        <span class="item__short__btn delete">
                                            <i class="fa-solid fa-trash"></i>
                                        </span> 
                                </div>
                            </div>

                            <div class="variety__item__detail">
                                <div class="row">
                                    <div class="col p-6">
                                        <div class="formGroupt">
                                            <img class="form__review__image" src="/views/source/image/default_image.jpg">
                                            <input class="form__item__input" type="file" onchange="displayImage({target: this})"
                                                   rules="require" name="image" hidden>
                                            <span class="form-message">Trường này không thể bỏ trống</span>
                                        </div>
                                    </div>

                                    <div class="col p-6">
                                        <div class="formGroupt">
                                            <p class="form__item__name">Số lượng</p>
                                            <input class="form__item__input" type="number"
                                                   rules="number|min=1|max=200" name="number">
                                            <span class="form-message">Trường này không thể bỏ trống</span>
                                        </div>
                                    </div>

                                    <div class="col p-6">
                                        <div class="formGroupt">
                                            <p class="form__item__name">Giá bán</p>
                                            <input class="form__item__input" type="number"
                                                   rules="double|min=0" name="price">
                                            <span class="form-message">Trường này không thể bỏ trống</span>
                                        </div>
                                    </div>

                                    <div class="col p-6">
                                        <div class="formGroupt">
                                            <p class="form__item__name">Giá ưu đãi</p>
                                            <input class="form__item__input" type="number"
                                                    name="priceSale" rules="require|double|min=0">
                                            <span class="form-message">Trường này không thể bỏ trống</span>
                                        </div>
                                    </div>

                                    <div class="col p-12" align="right">
                                        <button class="btn attribute__item__btn close">Thu gọn</button>
                                        <button class="btn attribute__item__btn delete">Xóa</button>
                                        <button class="btn attribute__item__btn save">Lưu lại</button>
                                    </div>

                                </div>
                            </div>
                        </div>`;
            const tempElement = document.createElement('div');
            this.listVariety.appendChild(tempElement);
            tempElement.outerHTML = html;
            return tempElement;
        },
        update: function(variety) {
            const tempElement = $(`#item-var-${variety.code}`, this.listVariety);
            handleClass(tempElement, 'add', 'short');
            
            if(!variety.listAttribute) {
                // khong cap nhat thuoc tinh, chi cap nhat Data
                return;
            }

            let listAttrHTML = '';
            variety.listAttribute.forEach(attr => {
                listAttrHTML += `<span class="btn variety__short__value">${attr.value}</span>`;
            });
            $('.variety__short__list', tempElement).innerHTML = listAttrHTML;
        },
        delete: function(code) {
            const tempElement = $(`#item-var-${code}`, this.listVariety);
            this.listVariety.removeChild(tempElement);
        },
        renderOneVarietyToView: function(variety, viewElement = this.varietySimple) {
            const listInput = $$('[name]', viewElement);
            listInput.forEach(input => {
                if(input.type == 'file') {
                    const imageEle =  $('.form__review__image', viewElement);
                    imageEle.src = variety[input.name];
                    imageEle.dataset.selected = variety['imageId'];
                }
                else if(input.type == 'number') {
                    if(input.name == 'priceSale') {
                        input.value = variety.price * (1 - variety.percentDes/100);
                    }
                    else {
                        input.value = variety[input.name];
                    }
                }
            });
        },
        renderListVarietyFromExistedProduct: function(product) {
            let code = null;
            product.listVariety.forEach(variety => {
                code = variety.code;
                this.renderOneVarietyToView(variety, $(`#item-var-${code}`, this.listVariety));
            });
        }
    },
    category: {
        listCategory: $('.form__item__input.category'),
        renderAll: function(listCategory = [], code = null) {
            let html = '<option value=""> Chọn danh mục </option>';
            listCategory.forEach(cate => {
                html += `<option value="${cate.code}" ${cate && cate.code == code ? 'selected':''}>${cate.name}</option>`;
            });
            this.listCategory.innerHTML = html;
        }
    },
    manufacture: {
        listManufacture: $('.form__item__input.manufacture'),
        renderAll: function(listManufacture = [], code = null) {
            let html = '<option value=""> Chọn nhà sản xuất </option>';
            listManufacture.forEach(manu => {
                html += `<option value="${manu.code}" ${code && manu.code == code ? 'selected':''}>${manu.name}</option>`;
            });
            this.listManufacture.innerHTML = html;
        }
    },
    product: {
        commonProduct: $('.form__body'),
        renderCommonData: function(product) {
            const listInput = $$('[name]', this.commonProduct);
            listInput.forEach(input => {
                if(input.type == 'text' || input.type == 'number' || input.type == 'textarea') {
                    input.value = product[input.name];
                }
                else if(input.type == 'radio') {
                    input.checked = (input.value == product[input.name]);
                }
            });
        }
    }


};