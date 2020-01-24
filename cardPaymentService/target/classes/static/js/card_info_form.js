$(document).ready(function(){
	
	console.log('usao korisnik.js');

	//Link na pocetnu stranu
	$("#sendForm").submit(send);
	
});

function send(){
	var proba = {broj: 5, tekst: "fld"};
	$.post({
		url : 'https://localhost:8841/test/testHelloSubmit',
		contentType : 'application/json',
		dataType: 'json',
		data : JSON.stringify(proba),
		success : function(pr){
		
			//radi kad nema redirekcije...
			$.session.set('somekey', 'a value');
			alert($.session.get('somekey'));
			localStorage.setItem('loc', 'loc');
			
			//$(location).attr('href', 'http://stackoverflow.com')
			alert(pr.broj);
			alert("uspeh");
			console.log("uspeh");
		},
		error : function(a, b, c){
			 alert("fail");
			 console.log("uspeh");
		}
	});
}