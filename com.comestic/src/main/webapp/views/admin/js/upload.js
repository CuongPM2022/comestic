(function() {
	const $ = document.querySelector.bind(document);
	var fileList = document.querySelectorAll(".files");

	$('#submit').onclick = function(e) {
		e.preventDefault();
		var formData = new FormData();

		fileList.forEach(fileInput => {
			let files = fileInput.files;
			[...files].forEach(file => {
				formData.append(fileInput.name,file);
				console.log(file);
			});
		});

		fetch('http://localhost:8080/api-file-upload?type=category', {
			method: 'POST',
			body: formData
		})
		.then(response => response.json())
		.then(result => {
			console.log(result);
		}); 
	}

})();

