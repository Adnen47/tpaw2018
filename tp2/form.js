window.onload = function () {
    document.querySelector("form").addEventListener("submit", function (event) {
        event.preventDefault();
        var errorMsg = "";
        
        if (document.getElementById("lastname").value.length < 5) {
            errorMsg ="Le nom doit contenir au moins 5 caractères <br/>";
        }

        if (document.getElementById("firstname").value.length < 5) {
             errorMsg += "Le prénom doit contenir au moins 5 caractères <br/>";
        }

        if (document.getElementById("date").value.length < 8) {
              errorMsg +="La date est obligatoire <br/>";
        }
		if (document.getElementById("adresse").value.length < 8) {
              errorMsg +="L'adresse est obligatoire <br/>";
        }
        if (document.getElementById("email").value.length < 5) {
              errorMsg +="L'email est obligatoire <br/>";
        }

     if(errorMsg != ""){
            errorMessageShow(errorMsg);
      }
	  else{
            messageShow();
      }

    });
}

function errorMessageShow(_msg){
     document.getElementById("errorMessage").innerHTML = _msg;;
    document.getElementById("errorMessage").classList.remove("hidden");
}
function messageShow(){
    document.getElementById("errorMessage").classList.add("hidden");

     document.getElementById("message").innerHTML = "Bienvenue " + document.getElementById("lastname").value;
    document.getElementById("message").classList.remove("hidden");
};