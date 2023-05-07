const menuBtn = document.querySelector('#imgAbrir');
const menu = document.querySelector('nav:first-of-type > ul');

menuBtn.addEventListener('click', function() {
  menu.classList.toggle('nav-expanded');
});
