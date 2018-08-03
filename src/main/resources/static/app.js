
function checkIfAdviceNeeded(risk){
	if(risk>=3){
		alert("Your account has been locked. Please contact your System Administrator for further instruction.");
	}
}
function playAudio() { 
	var audio = new Audio('/sound/TADA.WAV');
	audio.play();
} 

function playAudioFailure(){
	var audio = new Audio('/sound/CHORD.WAV');
	audio.play();
}


function setConnected(connected) {

	$("#risks").html("");
}


function showRisk(message) {
	console.log("attempting to show risk");
	$("#risks").append("<tr><td>" + message + "</td></tr>");
}

function disappearAlert(){
	window.setTimeout(function() {
	    $(".alert").fadeTo(500, 0).slideUp(500, function(){
	        $(this).remove(); 
	    });
	}, 4000);
}

function getSecret() {
	$
			.get(
					"/secret",
					function(data, status) {
						$("#risks").append("<tr><td>" + data + "</td></tr>");
						$("#messages")
								.append(
										'<div class="alert alert-success" role="alert">'
												+ '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>'
												+ '<strong>Success!</strong> '
												+ '</div>');
						// play win 95 for david
						//playAudio();
						disappearAlert();
						//alert("Data: " + data + "\nStatus: " + status);

					}).fail(function() {
						$("#risks").append("<tr><td>" + "Deny"+ "</td></tr>");
						$("#messages")
								.append(
										'<div class="alert alert-danger" role="alert">'
												+ '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>'
												+ '<strong>Deny!</strong> '
												+ '</div>');
						// play win 95 audio
						//playAudioFailure();
						disappearAlert();
					    console.log("get error, fail, etc");
					    getRiskScore();
					  });
}

function getTopSecret() {
	$
			.get(
					"/topsecret",
					function(data, status) {
						$("#risks").append("<tr><td>" + data + "</td></tr>");
						$("#messages")
								.append(
										'<div class="alert alert-success" role="alert">'
												+ '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>'
												+ '<strong>Success!</strong> '
												+ '</div>');
						// play win 95 for david
						//playAudio();
						disappearAlert();
						//alert("Data: " + data + "\nStatus: " + status);

					}).fail(function() {
						$("#risks").append("<tr><td>" + "Deny"+ "</td></tr>");
						$("#messages")
								.append(
										'<div class="alert alert-danger" role="alert">'
												+ '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>'
												+ '<strong>Deny!</strong> '
												+ '</div>');
						// play win 95 failure
						//playAudioFailure();
						disappearAlert();
					    console.log("get error, fail, etc");
					    getRiskScore();
					  });
}

function getUnclassified() {
	$
			.get(
					"/unclassified",
					function(data, status) {
						$("#risks").append("<tr><td>" + data + "</td></tr>");
						$("#messages")
								.append(
										'<div class="alert alert-success" role="alert">'
												+ '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>'
												+ '<strong>Success!</strong> '
												+ '</div>');
						// play win 95 for david
						//playAudio();
						disappearAlert();
						//alert("Data: " + data + "\nStatus: " + status);
						
					}).fail(function() {
						$("#risks").append("<tr><td>" + "Deny"+ "</td></tr>");
						$("#messages")
								.append(
										'<div class="alert alert-danger" role="alert">'
												+ '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>'
												+ '<strong>Deny!</strong> '
												+ '</div>');
						// play win 95 failure
						//playAudioFailure();
						disappearAlert();
					    console.log("get error, fail, etc");
					    getRiskScore();
					  });
}

function getPii() {
	$
			.get(
					"/pii",
					function(data, status) {
						$("#risks").append("<tr><td>" + data + "</td></tr>");
						$("#messages")
								.append(
										'<div class="alert alert-success" role="alert">'
												+ '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>'
												+ '<strong>Success!</strong> '
												+ '</div>');
						// play win 95 for david
						//playAudio();
						disappearAlert();
						//alert("Data: " + data + "\nStatus: " + status);

					}).fail(function() {
						$("#risks").append("<tr><td>" + "Deny"+ "</td></tr>");
						$("#messages")
								.append(
										'<div class="alert alert-danger" role="alert">'
												+ '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>'
												+ '<strong>Deny!</strong> '
												+ '</div>');
						// play win 95 failure
						//playAudioFailure();
						disappearAlert();
					    console.log("get error, fail, etc");
					    getRiskScore();
					  });
}

function getCentos() {
	$
			.get(
					"/centos",
					function(data, status) {
						$("#risks").append("<tr><td>" + data + "</td></tr>");
						$("#messages")
								.append(
										'<div class="alert alert-success" role="alert">'
												+ '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>'
												+ '<strong>Success!</strong> '
												+ '</div>');
						// play win 95 for david
						//playAudio();
						disappearAlert();
						//alert("Data: " + data + "\nStatus: " + status);

					}).fail(function() {
						$("#risks").append("<tr><td>" + "Deny"+ "</td></tr>");
						$("#messages")
								.append(
										'<div class="alert alert-danger" role="alert">'
												+ '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>'
												+ '<strong>Deny!</strong> '
												+ '</div>');
						// play win 95 failure
						//playAudioFailure();
						disappearAlert();
					    console.log("get error, fail, etc");
					    getRiskScore();
					  });
}

function getAdmin() {
	$
			.get(
					"/admin",
					function(data, status) {
						$("#risks").append("<tr><td>" + data + "</td></tr>");
						$("#messages")
								.append(
										'<div class="alert alert-success" role="alert">'
												+ '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>'
												+ '<strong>Success!</strong> '
												+ '</div>');
						// play win 95 for david
						//playAudio();
						disappearAlert();
						//alert("Data: " + data + "\nStatus: " + status);

					}).fail(function() {
						$("#risks").append("<tr><td>" + "Deny"+ "</td></tr>");
						$("#messages")
								.append(
										'<div class="alert alert-danger" role="alert">'
												+ '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>'
												+ '<strong>Deny!</strong> '
												+ '</div>');
						// play win 95 failure
						//playAudioFailure();
						disappearAlert();
					    console.log("get error, fail, etc");
					    getRiskScore();
					  });
}

function getMidLevel() {
	$
			.get(
					"/midlevel",
					function(data, status) {
						$("#risks").append("<tr><td>" + data + "</td></tr>");
						$("#messages")
								.append(
										'<div class="alert alert-success" role="alert">'
												+ '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>'
												+ '<strong>Success!</strong> '
												+ '</div>');
						// play win 95 for david
						//playAudio();
						disappearAlert();
						//alert("Data: " + data + "\nStatus: " + status);

					}).fail(function() {
						$("#risks").append("<tr><td>" + "Deny"+ "</td></tr>");
						$("#messages")
								.append(
										'<div class="alert alert-danger" role="alert">'
												+ '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>'
												+ '<strong>Deny!</strong> '
												+ '</div>');
						// play win 95 failure
						//playAudioFailure();
						disappearAlert();
					    console.log("get error, fail, etc");
					    // update risk score
					    getRiskScore();
					  });
}

function getHr() {
	$
			.get(
					"/hr",
					function(data, status) {
						$("#risks").append("<tr><td>" + data + "</td></tr>");
						$("#messages")
								.append(
										'<div class="alert alert-success" role="alert">'
												+ '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>'
												+ '<strong>Success!</strong> '
												+ '</div>');
						// play win 95 for david
						//playAudio();
						disappearAlert();
						//alert("Data: " + data + "\nStatus: " + status);

					}).fail(function() {
						$("#risks").append("<tr><td>" + "Deny"+ "</td></tr>");
						$("#messages")
								.append(
										'<div class="alert alert-danger" role="alert">'
												+ '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>'
												+ '<strong>Deny!</strong> '
												+ '</div>');
						// play win 95 failure
						//playAudioFailure();
						disappearAlert();
					    console.log("get error, fail, etc");
					    // update risk score
					    getRiskScore();
					  });
}

function getIt() {
	$
			.get(
					"/it",
					function(data, status) {
						$("#risks").append("<tr><td>" + data + "</td></tr>");
						$("#messages")
								.append(
										'<div class="alert alert-success" role="alert">'
												+ '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>'
												+ '<strong>Success!</strong> '
												+ '</div>');
						// play win 95 for david
						//playAudio();
						disappearAlert();
						//alert("Data: " + data + "\nStatus: " + status);

					}).fail(function() {
						$("#risks").append("<tr><td>" + "Deny"+ "</td></tr>");
						$("#messages")
								.append(
										'<div class="alert alert-danger" role="alert">'
												+ '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>'
												+ '<strong>Deny!</strong> '
												+ '</div>');
						// play win 95 failure
						//playAudioFailure();
						disappearAlert();
					    console.log("get error, fail, etc");
					    // update risk score
					    getRiskScore();
					  });
}

function getRiskScore(){
	$
	.get(
			"/riskscore",
			function(data, status) {
				console.log("risk score is : " + data);
				document.getElementById("riskscore").innerHTML = data;
				
				checkIfAdviceNeeded(data);

			}).fail(function() {
				
			    console.log("error getting risk score in getRiskScore()");
			  });
}

$(function() {

	$("#secret").click(function() {
		getSecret();
	});
	
	$("#topsecret").click(function() {
		getTopSecret();
	});
	
	$("#unclassified").click(function() {
		getUnclassified();
	});
	
	$("#pii").click(function() {
		getPii();
	});
	
	$("#centos").click(function() {
		getCentos();
	});
	

	$("#admin").click(function() {
		getAdmin();
	});
	
	$("#midlevel").click(function() {
		getMidLevel();
	});
	
	$("#hr").click(function() {
		getHr();
	});
	
	$("#it").click(function() {
		getIt();
	});
});
