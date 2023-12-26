(async function createProduct() {

	// cap nhat san pham
	if(id) {
		await getData(`/api-product?action=detailProduct&id=${id}`, resp => dbProduct = resp);
	}

	// lay danh muc co the chua san pham
	await getData('/api-category?action=listHasNotChild', resp => dbListCategory = resp);

	// lay manufacture List
	await getData('/api-manufacture?action=allItem', resp => dbListManufacture = resp);

	// lay attribute List
	await getData('/api-attribute?action=allAttribute', resp => dbListAttribute = resp);

	// goi controller - gan cac su kien
	controller();

})();


