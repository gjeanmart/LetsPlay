if (window.console) {
  console.log("Welcome to your Play application's JavaScript! yeahhhhh");
}

/**
  * Launch Change locale POST
  */
var form = document.querySelector('form.change-locale');
  if (form !== null) {
    form.addEventListener('change', function () {
        console.log("change")
        form.submit();
    });
}