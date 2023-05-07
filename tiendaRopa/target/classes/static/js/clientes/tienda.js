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
