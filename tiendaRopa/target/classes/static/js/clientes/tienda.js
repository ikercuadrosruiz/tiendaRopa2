// Obtén los elementos necesarios
var selectWrapper = document.querySelector('.select-wrapper');
var selectSelected = selectWrapper.querySelector('.select-selected');
var selectOptions = selectWrapper.querySelector('.select-options');

// Mostrar/ocultar opciones al hacer clic en el select
selectSelected.addEventListener('click', function() {
	selectOptions.style.display = (selectOptions.style.display === 'block') ? 'none' : 'block';
});

// Manejar la selección de una opción
selectOptions.addEventListener('click', function(e) {
	var selectedOption = e.target.textContent;
	selectSelected.textContent = selectedOption;
	selectOptions.style.display = 'none';
});


// Impedir arrastrar imagenes
const imageProducto = document.querySelectorAll('#cont--producto img:nth-of-type(2)');
imageProducto.forEach(image => {
  image.addEventListener('dragstart', bloquearArrastre);
});

const imageHeart = document.querySelectorAll('#cont--producto img:nth-of-type(1)');
imageHeart.forEach(image => {
  image.addEventListener('dragstart', bloquearArrastre);
});


function bloquearArrastre(event) {
  event.preventDefault();
}


