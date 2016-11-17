<html>
  <head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <link href='http://fonts.googleapis.com/css?family=Oleo+Script' rel='stylesheet' type='text/css'>
	
	<link rel="apple-touch-icon" href="/favicon.png">
	<meta name="apple-mobile-web-app-capable" content="yes">
    <meta name = "viewport" content = "width = 320,
       initial-scale = 1, user-scalable = no">
<meta name="apple-mobile-web-app-capable" content="yes">
    <style>
      .content {
        margin:10px;
        padding:10px;
        text-align:center;
      }

      h1.result {
        font: 400 100px/1.3 'Oleo Script', Helvetica, sans-serif;
          color: #2b2b2b;
          font-size:56px;
          padding-top:20px;
          padding-bottom:20px;
          text-shadow: 4px 4px 0px rgba(0,0,0,0.1);
          margin: 0;
    	  padding: 0;
      }
    </style>


  </head>
  <body>
    <div class="content">
      <div class="jumbotron">
        <h3>Trouve un prénom !</h3>
 			  <h1 id="prenom" class="result"></h1>			
			  <p id="type"></p>
			  <p id="generateButton"><a class="btn btn-primary btn-lg" href="#" onclick="randomName()" role="button" id="theButton">Choisir un prénom au hazard</a></p>
	  </div>
	</div>
    <script   src="https://code.jquery.com/jquery-3.1.0.min.js"   integrity="sha256-cCueBR6CsyA4/9szpPfrX3s49M9vUU5BgtiJj06wt/s="   crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script type="text/javascript">
    	function randomName() {
    		$.ajax({
    			  url: "http://ilet.fr:8080/match/api/firstname/random",
    			  success: function(response) {
    				  $("#prenom").text(response.firstName.prenom);
    				  
    				  if (response.firstName.genre.indexOf("m") > -1 && 
    					  response.firstName.genre.indexOf("f") > -1) {
    					  $("#type").text("Masculin et féminin");
    				  } else if (response.firstName.genre.indexOf("m") > -1) {
    					  $("#type").text("Masculin");
    				  } else {
    					  $("#type").text("Féminin");
    				  }
    				 
    			  }
    			});
    	}
    </script>
  </body>
</html>
