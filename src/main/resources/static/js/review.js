var addTagButton = document.querySelector(".add-tag-button");


addTagButton.addEventListener("click", function() {
    const reviewId = this.getAttribute('data-id');
	var newTag = prompt("Please enter new tag");
	if (newTag) {
		 createNewTag(newTag, reviewId);
    }
});


//PUT (update)
createNewTag = function(newTag, reviewId) {
    const xhr = new XMLHttpRequest();
    
    xhr.onreadystatechange = function() {
        if (this.status === 200 && this.readyState === 4) {
            console.log(this.responseText);
        }
    };
    
    xhr.open('PUT', '/api/reviews/' + reviewId + '/tags/' + newTag);
    xhr.send();
    
};

