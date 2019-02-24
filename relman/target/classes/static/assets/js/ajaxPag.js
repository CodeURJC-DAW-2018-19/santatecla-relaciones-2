	function ajaxPag(id) {
			$(document).ready(function(){	
  				 var p= document.getElementById(id).value;
  				 var name =document.getElementById(id).name;
	  			 $('#spinner').html("<img src='images/spinner.gif' width='50em'/>");
  				 $.ajax({url: "/"+name+"/"+ p +"/10", success: function(result){
  	 		 	 	var suma = parseInt(p) + parseInt(1);
  	 		 	 	document.getElementById(id).value= suma ; 
   	  			    var d1 = document.getElementById(id+name);
					d1.insertAdjacentHTML('beforeend', result);
					var n =document.getElementById(id).title;
					document.getElementById(id).title = parseInt(n) - parseInt(10); 
					var c = document.getElementById(id).title;
					if( c <=0 ){
					document.getElementById(id).disabled = true;
					document.getElementById(id).style.display = 'none';
					}	
	
		  			$('#spinner').html("");
   	 			}});	 		
		});
		}