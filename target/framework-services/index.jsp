<html>
<head>
<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript">

var json='{"requestURI":{"reqURL":"http://www.google.com","reqMethod":"GET","reqHeader":{"User-Agent":"Mozilla/5.0"},"reqPayload":""},"lockPathID":"12345"}';

$(document).ready(function() 
{ 
		var result = "";
		result = make_request("api/coordination-services/locking/v1/acquire-lock", "post",json);
				result.done(function (response) {    
		        }).fail(ajax_error);
			   
				
		function make_request(url, type, data) {
						return $.ajax({
								url: url,
								type: type,
								dataType: "json",
								contentType: 'application/json; charset=UTF-8',
								data: data
						});
		}
			
		function ajax_error(xhr, status, errorThrown) {
				    alert("Status: "+status+" Error: " + errorThrown);
				    console.dir(xhr);
		}
});
    
</script>
</head>
<body>
    <h2>Coordination Services Testing app</h2>
</body>
</html>