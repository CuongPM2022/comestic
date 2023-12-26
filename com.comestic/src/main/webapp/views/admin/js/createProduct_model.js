const model = {
	attribute: {
		code: 1,
		tblAttribute: [],
		getCode: function() {
			return this.code++;
		},
		findOne: function(code) {
			const temp = this.tblAttribute.find(item => item.code == code);
			return {...temp};
		},
		findAll: function() {
			const listTemp = [];
			this.tblAttribute.forEach(attr => {
				listTemp.push({
					code: attr.code,
					name: attr.name,
					value: attr.value ? [...attr.value] : []
				});
			});
			return listTemp;
		},
		findAllNotHasId: function() {
			return this.tblAttribute.filter(item => !item.id).map(item => {
				return {
					name: item.name,
					code: item.code
				}
			});
		},
		findById: function(id) {
			let temp = this.tblAttribute.find(item => item.id == id);
			return {...temp};
		},
		save: function(attr) {
			//them moi - tra ve object vua them
			if(!attr.code) {
				attr.code = this.getCode();
				this.tblAttribute.push(attr);
				return this.findOne(attr.code);
			}
			// cap nhat - tra ve object vua cap nhat
			else {
				const temp = this.tblAttribute.find(item => item.code == attr.code);
				Object.assign(temp,attr);
				return temp;
			}
		},
		delete: function(code) {
			let check = false;
			for(let i = 0; i < this.tblAttribute.length; i++) {
				if(this.tblAttribute[i].code == code) {
					this.tblAttribute.splice(i,1);
					check = true;
					break;
				}
			}
			return check;
		},
		count: function() {
			return this.tblAttribute.length;
		},
		checkAll: function() {
			if(this.count() == 0) {
				return false;
			}

			let check = true;
			for(const attr of this.tblAttribute) {
				if(!attr.value || attr.value.length == 0) {
					check = false;
					break;
				}
			}
			return check;
		}
	},
	variety: {
		code: 1,
		tblVariety: [],
		getCode: function() {
			return this.code++;
		},
		findOne: function(code) {
			const tempVariety = this.tblVariety.find(item => item.code == code);
			let result = null;
			if(tempVariety) {
				result = {...tempVariety};
				result.listAttribute = model.variety_attribute.findAllByVarietyCode(code);
			}
			return result;
		},
		findAll() {
			let listTemp = [];
			this.tblVariety.forEach(item => {
				listTemp.push({...item});
			});
			return listTemp;
		},
		// luu va tra ve variety vua luu
		save: function(varietyInput) {
			let variety = {...varietyInput};

			// them moi
			if(!variety.code) {
				variety.code = this.getCode();
				// save danh sach attribute
				const listAttribute = model.variety_attribute.saveListAttribute(variety);
				
				// save variety
				delete variety.listAttribute;
				this.tblVariety.push(variety);

				// tra ve variety
				return {
					code: variety.code,
					listAttribute: listAttribute
				};
			}
			// cap nhat
			else {
				// cap nhat thuoc tinh
				if(variety.listAttribute) {
					// xoa cac attribute cu
					model.variety_attribute.deleteAllWithCodeVariety(variety.code);

					// save danh sach attribute moi
					const listAttribute = model.variety_attribute.saveListAttribute(variety);

					// tra ve variety
					return {
						code: variety.code,
						listAttribute: listAttribute
					};
				}
				// cap nhat data
				else {
					const tempVariety = this.tblVariety.find(item => item.code == variety.code);
					Object.assign(tempVariety,variety);
					delete tempVariety.priceSale;
					return tempVariety;
				}
			}
		},
		// kiem tra xem co bien the co listAttribute nhu vay chua
		existWithListAttribute: function(listAttribute=[]) {
			let temp = this.tblVariety.find(variety => {
				return model.variety_attribute.hasVarietyWithListAttribute(variety.code, listAttribute);
			});
			return temp;
		},
		getAllListAttribute() {
			let listAttribute = [];
			let listTemp = [];
			model.attribute.findAll().forEach(item => {
				listTemp = [];
				item.value.forEach(value => {
					if(listAttribute.length == 0) {
						listTemp.push([{
							code: item.code,
							value: value
						}]);
					}
					else {
						listAttribute.forEach(attr => {
							listTemp.push([
								...attr,
								{
									code: item.code,
									value: value
								}
							]);
						});
					}
				});
				listAttribute = listTemp;
			});
			return listAttribute;
		},
		getAllListAttributeNotExist: function() {
			const listListAttribute = [];
			for(const listAttr of this.getAllListAttribute()) {
				if(!this.existWithListAttribute(listAttr)) {
					listListAttribute.push(listAttr);
				}
			}
			return listListAttribute;
		},
		delete: function(code) {
			// xoa tat ca thuoc tinh
			model.variety_attribute.findAllByVarietyCode(code);

			// xoa variety voi code
			for(let i=0; i<this.tblVariety.length; i++) {
				if(this.tblVariety[i].code == code) {
					this.tblVariety.splice(i,1);
					break;
				}
			}
		},
		checkAttribute: function(code) {
			let countRight = 0, totalAttr = 0;
			const listAttribute = model.attribute.findAll();
			model.variety_attribute.tblVariety_Attribute.forEach(item => {
				if(item.codeVariety == code) {
					totalAttr++;
					const attr = listAttribute.find(attribute => {
						return attribute.code = item.codeAttribute && attribute.value.includes(item.value);
					});

					if(attr) {
						countRight++;
					}
				}
			});
			return countRight === totalAttr && countRight === listAttribute.length;
		},
		checkData: function(code) {
			const variety = this.tblVariety.find(item => item.code == code);
			if(!variety) {
				return false;
			}
			return (variety.price && variety.number && variety.image);
		}

	},
	variety_attribute: {
		code: 1,
		tblVariety_Attribute: [],
		getCode: function() {
			return this.code++;
		},
		findOne: function(code) {
			const temp = this.tblVariety_Attribute.find(item => item.code == code);
			return {...temp};
		},
		findAllByVarietyCode: function(codeVariety) {
			return this.tblVariety_Attribute.filter(item => item.codeVariety == codeVariety)
											.map(item => { return {...item}; });
		},
		findListAttrHasIdByVarietyCode: function(codeVariety) {
			let temp = null;
			const listVarAttr = this.tblVariety_Attribute.filter(item => item.codeVariety == codeVariety);
			const listResult = [];
			listVarAttr.forEach(var_attr => {
				temp = model.attribute.findOne(var_attr.codeAttribute);
				listResult.push({
					id: temp.id,
					value: var_attr.value
				});
			});
			return listResult;
		},
		save: function(codeVariety, attribute = {}) {
			const tempVariety = {
				code: this.getCode(),
				codeVariety: codeVariety,
				codeAttribute: attribute.code,
				value: attribute.value
			};
			this.tblVariety_Attribute.push(tempVariety);
			return this.findOne(tempVariety.code);
		},
		saveListAttribute: function(variety) {
			const listAttribute = [];
			variety.listAttribute.forEach(attr => {
				const tempAttr = this.save(variety.code, attr);
				listAttribute.push({
					codeAttribute: tempAttr.codeAttribute,
					value: tempAttr.value
				});
			});
			return listAttribute;
		},
		hasVarietyWithListAttribute: function(codeVariety, listAttribute = []) {
			if(this.tblVariety_Attribute.length == 0) {
				return false;
			}
			let length = listAttribute.length;
			let count = 0;
			for(const item of this.tblVariety_Attribute) {
				if(item.codeVariety == codeVariety) {
					const temp = listAttribute.find(attr => {
						return attr.code == item.codeAttribute && attr.value == item.value;
					});
					if(temp) {
						count++;
					}
				}
			}
			if(count == length) {
				return true;
			}
			return false;
		},
		deleteAllWithCodeVariety: function(codeVariety) {
			const length = this.tblVariety_Attribute.length;
			for(let i = length - 1; i >= 0; i--) {
				if(this.tblVariety_Attribute[i].codeVariety == codeVariety) {
					this.tblVariety_Attribute.splice(i,1);
				}
			}
		}


	},
	category: {
		code: 1,
		tblCategory: [],
		getCode: function() {
			return this.code++;
		},
		add: function(category = {}) {
			const tempCategory = {...category};
			tempCategory.code = this.getCode();
			this.tblCategory.push(tempCategory);
			return {...tempCategory};
		},
		findOne: function(code) {
			let tempCategory = this.tblCategory.find(item => item.code == code);
			return {...tempCategory};
		},
		findAll: function() {
			return this.tblCategory.map(item => { return {...item}; });
		},
		findById: function(id) {
			const result = this.tblCategory.find(item => item.id == id);
			return {...result};
		},
		importFromListExist: function(listCategory = []) {
			const listTemp = [];
			let temp = null;
			listCategory.forEach(cate => {
				temp = this.add(cate);
				listTemp.push(temp);
			});
			return listTemp;
		}
	},
	manufacture: {
		code: 1,
		tblManufacture: [],
		getCode: function() {
			return this.code++;
		},
		add: function(manufacture = {}) {
			const tempManufacture = {...manufacture};
			tempManufacture.code = this.getCode();
			this.tblManufacture.push(tempManufacture);
			return {...tempManufacture};
		},
		findOne: function(code) {
			let tempManufacture = this.tblManufacture.find(item => item.code == code);
			return {...tempManufacture};
		},
		findAll: function() {
			return this.tblManufacture.map(item => { return {...item}; });
		},
		findById: function(id) {
			const result = this.tblManufacture.find(item => item.id == id);
			return {...result};
		},
		importFromListExist: function(listManufacture = []) {
			const listTemp = [];
			let temp = null;
			listManufacture.forEach(manu => {
				temp = this.add(manu);
				listTemp.push(temp);
			});
			return listTemp;
		}

	}
	
};

