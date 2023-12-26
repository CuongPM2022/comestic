const $ = (selector,parent = document) => parent.querySelector(selector);
const $$ = (selector,parent = document) => parent.querySelectorAll(selector);
const listAttributeElement = $('.product__attribute');
const listVarietyElement = $('.product__variety');
const active = 'active';

/* Data Common */
var dbProduct = null;
var dbListCategory = null;
var dbListManufacture = null;
var dbListAttribute = null;

/* Functions Common */
function handleClass(element, action = 'add', value = active) {
	if(action == 'add') {
		element.classList.add(value);
	}
	else if(action == 'remove') {
		element.classList.remove(value);
	}
	else if(action == 'toggle') {
		element.classList.toggle(value);
	}
}

function getData(path, callback)
{
	return fetch(path)
		   .then(response => response.json())
		   .then(data => callback(data));
}

function postData(path, data, callback, method='POST') {
	return fetch(path, {
		method: method,
		body: JSON.stringify(data)
	})
	.then(response => response.json())
	.then(data => callback(data));
}

async function uploadFile(listFile, url) { // listFile = [{name:1, data: {}}, {name:2, data: {}},...];
	const formData = new FormData();
	listFile.forEach(file => {
			formData.append(file.name, file.data);
	});

	const response = await fetch(url, {
		method: 'POST',
		body: formData
	});
	const data = await response.json();
	return data;
}

function getParentBySelector(element, selector)
{
	if(element.matches(selector)) { return element; }
	let temp = element;
	do {
		temp = temp.parentElement;
	} while(!temp.matches(selector));
	return temp;
}

// ham hien thi anh khi chon
function displayImage({target:element}) {
	tempElement = getParentBySelector(element,'.formGroupt');
	if(!element.files[0]) {
		return;
	}

	const imageEle = tempElement.querySelector('.form__review__image');
	imageEle.src = URL.createObjectURL(element.files[0]);
}

