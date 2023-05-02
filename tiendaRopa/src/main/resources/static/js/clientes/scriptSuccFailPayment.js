// Obtener el contenedor del pop-up y el botón de continuar
var popupContainer = document.getElementById('popup-container');
var continueButton = document.getElementById('continue-button');

// Función para mostrar el pop-up
function showPopup() {
  popupContainer.style.display = 'block';
}

// Función para ocultar el pop-up
function hidePopup() {
  popupContainer.style.display = 'none';
}

// Mostrar el pop-up cuando se carga la página
showPopup();

// Ocultar el pop-up cuando se hace clic en el botón de continuar
continueButton.addEventListener('click', hidePopup);
