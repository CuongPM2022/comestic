(function() {
	const $ = document.querySelector.bind(document);
	var validate = new Validate('#form', handleLogin);

	function getData(path, callback)
	{
		return fetch(path)
			   .then(response => response.json())
			   .then(data => callback(data));
	}

	function postData(path, data, callback) {
		return fetch(path, {
					'method' : 'POST',
					'headers' : {
						'Content-Type' : 'application/json'
					},
					'body' : JSON.stringify(data)
				})
				.then(response => response.json())
				.then(data => callback(data));
	}

	function handleLogin(userData) {
		postData(`/api-user?action=login`, userData, data => {
			if(data === null) {
				alert(`Thông tin đăng nhập chưa chính xác. Vui lòng kiểm tra lại!`);
				return;
			}
			window.location = 'admin-home';
		});
	}

})();