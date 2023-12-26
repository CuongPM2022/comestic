function Validate(formSelector, callback)
{
	this.formSelector = formSelector;
	this.form = document.querySelector(formSelector);
	this.listRuleByNameAttribute = {};
	this.listData = {};
	this.errorMessage = '';
	const _this = this;

	this.validateFunction = {
		'require' : function(value) {
			if(value.trim()) {
				return true;
			}
			_this.errorMessage = 'Trường này là bắt buộc!';
			return false;
		},
		'fullname' : function(value) {
			if(!this['require'](value)) { return false; }
			let kq = false;
			while(value.includes('  ')) {
				value = value.replace(' ');
			}
			kq = value.includes(' ');
			if(!kq) {
				_this.errorMessage = 'Họ tên không hợp lệ!';
			}
			return kq;
		},
		'email' : function(value) {
			if(!this['require'](value)) { return false; }
			let kq;
			let regexEmail = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
			kq = regexEmail.test(value);
			if(!kq) {
				_this.errorMessage = 'Email không hợp lệ!';
			}
			return kq;
		},
		'lmin' : function(lmin,value) {
			if(!this['require'](value)) { return false; }
			lmin = parseInt(lmin);
			if(value.length >= lmin) return true;
			_this.errorMessage = `Độ dài tối thiểu ${lmin} ký tự!`;
			return false;
		},
		'lmax' : function(lmax,value) {
			if(!this['require'](value)) { return false; }
			lmax = parseInt(lmax);
			if(value.length <= lmax) return true;
			_this.errorMessage = `Độ dài tối đa ${lmax} ký tự!`;
			return false;
		},
		'min' : function(min,value) {
			if(!this['require'](value)) { return false; }
			min = parseFloat(min);
			value = parseFloat(value);
			let kq = false;
			if(value >= min) {
				kq = true;
			}
			if(!kq) {
				_this.errorMessage = `Giá trị nhỏ nhất là ${min}!`;
			}
			return kq;
		},
		'max' : function(max,value) {
			if(!this['require'](value)) { return false; }
			max = parseFloat(max);
			value = parseFloat(value);
			let kq = false;
			if(value <= max) {
				kq = true;
			}
			if(!kq) {
				_this.errorMessage = `Giá trị lớn nhất là ${max}!`;
			}
			return kq;
		},
		'number' : function(value) {	
			if(!this['require'](value)) { return false; }
			let kq = true;
			if(!this.double(value)) {
				kq = false;
			}
			if(value.includes('.')) {
				kq = false;
			}

			if(!kq)
			{
				_this.errorMessage = 'Dữ liệu là số nguyên!';
			}

			return kq;
		},
		'double' : function(value) {
			if(!this['require'](value)) { return false; }
			let kq = !isNaN(value);
			if(!kq)
			{
				_this.errorMessage = 'Dữ liệu là số!';
			}
			return kq;
		},
		'requireSelectedFile' : function(files) 
		{
			let kq = files.length;
			if(kq === 0)
			{
				_this.errorMessage = 'Cần chọn File!';
			}
			return (kq != 0);
		},
		'smax' : function(smax,value)
		{
			let kq = true;
			smax = parseInt(smax) * 1024;
			kq = [...value].every(file => file.size <= smax);
			if(!kq) {
				_this.errorMessage = `Kích thước File không quá ${smax/1024}KB!`;
			}
			return kq;
		},
		'smaxs' : function(smaxs,value)
		{
			let kq =0;
			smaxs = parseInt(smaxs) * 1024;
			kq = [...value].reduce((kq,file) => kq + file.size,0);

			if(kq > smaxs) {
				_this.errorMessage = `Tổng kích thước các File không quá ${smaxs/1024}KB!`;
			}
			return kq <= smaxs;
		},
		'ext' : function(ext,value)
		{
			let temp;
			let kq;
			ext = ext.split(',');
			kq = [...value].every(file => {
					temp = file.name.split('.');
					temp = temp[temp.length -1];
					return ext.includes(temp);
				});

			if(!kq) {
				_this.errorMessage = 'File không đúng định dạng!';
			}
			return kq;
		},
		'numChecked' : function(element)
		{
			return (_this.form
					   .querySelectorAll(`[name="${element.name}"]:checked`)
					   .length);
			
		}
		,
		'requireRadioAndCheckBox' : function(element)
		{
			let kq = this.numChecked(element);
			if(kq == 0) {
				_this.errorMessage = 'Trường này là bắt buộc!';
			}
			return (kq != 0);
		},
		'cmin' : function(cmin,element)
		{
			let kq;
			cmin = parseInt(cmin);
			kq = this.numChecked(element) >= cmin;
			if(!kq) {
				_this.errorMessage = `Bạn cần tối thiểu ${cmin} lựa chọn!`;
			}
			return kq;
		},
		'cmax' : function(cmax, element)
		{
			let kq;
			cmax = parseInt(cmax);
			kq = this.numChecked(element) <= cmax;
			if(!kq) {
				_this.errorMessage = `Bạn có tối đa ${cmax} lựa chọn!`;
			}
			return kq;
		}

	}; // end Functions Validate

	/* Function common */
	this.getParentElementBySelector = function(element, selector) {
		if(element.matches(selector))
		{
			return element;
		}
		let temp = element;
		do {
			temp = temp.parentElement;
		} while(!temp.matches(selector));
		return temp;
	}

	this.showErrorMessage = function(element)
	{
		let temp = this.getParentElementBySelector(element,'.formGroupt');
		temp = temp.querySelector('.form-message');
		if(temp) {
			temp.innerText = this.errorMessage;
			temp.classList.add('show');
		}
	}

	this.hideErrorMessage = function(element)
	{
		let temp = this.getParentElementBySelector(element,'.formGroupt');
		temp = temp.querySelector('.form-message');
		if(temp) {
			temp.innerText = '';
			temp.classList.remove('show');
		}
	}

	this.getData = function(element)
	{
		if(this.listData[element.name]) { return; }
		
		if(element.type === 'checkbox')
		{
			this.listData[element.name] =
			[...this.form.querySelectorAll(`[name="${element.name}"]:not(disabled):checked`)]
				.map(ele => ele.value);
		}
		else if(element.type === 'file')
		{
			this.listData[element.name] =
			[...element.files].map(file => file.name);
		}
		else if(element.type === 'radio') {
			this.listData[element.name] =
			this.form
				.querySelector(`[name="${element.name}"]:not(disabled):checked`)
				.value;
		}
		else {
			this.listData[element.name] = element.value;
		}
	}

	this.validation = function(element, rules)
	{
		if(rules.trim() == '') {
			return true;
		}
		
		let temp;
		let kq =
		rules.split('|').every(rule => {
			if(element.type === 'file')
			{
				if(rule === 'require')
				{
					return this.validateFunction['requireSelectedFile'](element.files);
				}
				else {
					temp = rule.split('=');
					return this.validateFunction[temp[0]](temp[1],element.files);
				}
			}
			else if(element.type === 'radio' || element.type === 'checkbox')
			{
				if(rule === 'require')
				{
					return this.validateFunction['requireRadioAndCheckBox'](element);
				}
				else {
					temp = rule.split('=');
					return this.validateFunction[temp[0]](temp[1],element);
				}
			}
			else {
				if(rule.includes('=')) {
					temp = rule.split('=');
					return this.validateFunction[temp[0]](temp[1],element.value);
				}
				else {
					return this.validateFunction[rule](element.value);
				}
			}
		});

		if(kq) {
			this.hideErrorMessage(element);
		}
		else {
			this.showErrorMessage(element);
		}
		return kq;
	}

	this.assignEventValidate = function()
	{
		for(let key in this.listRuleByNameAttribute)
		{
			[...this.form.querySelectorAll(`[name="${key}"]:not(disabled)`)].forEach(ele => {
				ele.addEventListener('change',(e) => {
					this.validation(e.target,this.listRuleByNameAttribute[key]);
				});

				ele.addEventListener('input', (e) => {
					this.hideErrorMessage(e.target);
				});
			});
		}
	}

	this.validateForm = function() {
		let numError = 0;
		this.listData = {};
		
		[...this.form.querySelectorAll('[name]:not(disabled)')].forEach(ele => {
			if(ele.getAttribute('rules') != null)
			{
				if(!this.validation(ele,ele.getAttribute('rules'))) { numError += 1; }
				else {
					this.getData(ele);
				}
			}
		});

		if(numError != 0)
		{
			console.log(`${numError} error on the ${formSelector}!`);
			return null;
		}
		return this.listData;
	}

	/* End FUnction Common */

	// Main
	if(this.form) {
		[...this.form.querySelectorAll('[name][rules]:not(disabled)')].forEach(ele => {
			this.listRuleByNameAttribute[ele.name] = ele.getAttribute('rules');
		});

		this.assignEventValidate();

		const btnSubmitList = this.form.querySelectorAll('.form-submit');
		if(btnSubmitList)
		{
			let temp;
			[...btnSubmitList].forEach(btnSubmit => {
				btnSubmit.addEventListener('click', (e) => {
					e.preventDefault();
					temp = this.validateForm();
					if(temp) {
						if(typeof callback === 'function') {
							callback(temp);
						}
					}
				});
			});
		}

	}

	/* Comment 
	require: khong bo trong,
	fullname: ho va ten,
	email: email,
	lmin=: do dai chuoi nho nhat,
	lmax=: do dai chuoi lon nhat,
	number: so nguyen,
	double: so thuc,
	min=: gia tri so nho nhat,
	max=: gia tri so lon nhat,
	cmin=: so lua chon toi thieu,
	cmax=: so lua chon toi da;

	File:
	smax=: kich thuoc lon nhat (KB) cua moi file,
	smaxs=: kich thuoc lon nhat (KB) cua tong cac file,
	ext=jpg,jpeg,...: loai File duoc chon

	- De validate form bat ky, goi validateForm(): tra ve doi tuong du lieu or null
	*/
}