const searchButton = document.getElementById('searchButton');

searchButton.addEventListener('click', function(e) {
	
	e.preventDefault();
	
  	const searchTerm = document.getElementById('searchInput').value;

  	window.location.href = '/fruttidivestiti/trabajadores/categorias/search?term=' + searchTerm;
});