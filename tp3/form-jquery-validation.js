$(function(){
	$( "#date_naissance" ).datepicker();
	//Date max
	//var maxDate =$( "#date_naissance" ).datepicker( "option", "maxDate" );
	//$( "#date_naissance" ).datepicker( "option", "maxDate", "+1m +1w" );
	
	$("#submitform").click(function(){
		
		if(($("#nom").val()=="")||($("#prenom").val()=="")||($("#date_naissance").val()=="") ||($("#address").val()=="") || ($("#email").val()==""))
		{
            $(".modal-body").html("Veuillez remplir tous les champs du formulaires");
            $(".modal").modal("show");
        }
		else{
	        $(".modal-title").html("Bienvenue "+$("#nom").val());
		    $(".modal-body").html("Vous êtes nés le "+$("#date_naissance").val() + " et vous habitez <br> <img src='https://maps.googleapis.com/maps/api/staticmap?center=&size=550x400'><br><a href='http://maps.google.com/maps?q='"+$("#address").val().replace(' ','+')+"></br>"+$("#address").val());
            $(".modal").modal("show");
		}
	});
});
