var geocoder;
var map;
// initialisation de la carte Google Map de d�part
function initialiserCarte() {
  geocoder = new google.maps.Geocoder();
  // Ici j'ai mis la latitude et longitude de l'universit� paris 13
  var latlng = new google.maps.LatLng(48.9561507,2.3412625999999364);
  var mapOptions = {
    zoom      : 14,
    center    : latlng,
    mapTypeId : google.maps.MapTypeId.ROADMAP
  }
  // map-canvas est le conteneur HTML de la carte Google Map
  map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
}
 
function TrouverAdresse() {
  // R�cup�ration de l'adresse tap�e dans le formulaire
  var adresse = document.getElementById('address').value;
  geocoder.geocode( { 'address': adresse}, function(results, status) {
    if (status == google.maps.GeocoderStatus.OK) {
      map.setCenter(results[0].geometry.location);
      // R�cup�ration des coordonn�es GPS du lieu tap� dans le formulaire
      var strposition = results[0].geometry.location+"";
      strposition=strposition.replace('(', '');
      strposition=strposition.replace(')', '');
      // Affichage des coordonn�es dans le <span>
      document.getElementById('text_latlng').innerHTML='Coordonn�es : '+strposition;
      // Cr�ation du marqueur du lieu (�pingle)
      var marker = new google.maps.Marker({
          map: map,
          position: results[0].geometry.location
      });
    } else {
      alert('Adresse introuvable: ' + status);
    }
  });
}
// Lancement de la construction de la carte google map
google.maps.event.addDomListener(window, 'load', initialiserCarte);