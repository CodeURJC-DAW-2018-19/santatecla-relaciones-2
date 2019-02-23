	function ajaxPag2(id) {
	            $(document).ready(function(){
	                   var p= document.getElementById(id).value;
	                   var rel =document.getElementById(id).name;
	                   var unidad =document.getElementById("unit").value;
	                   console.log(rel);
	                   console.log(unidad);
	                     $.ajax({url: "/rel/"+unidad+"/"+rel+"/"+p+"/", success: function(result){
	                         console.log(result);
	                         var suma = parseInt(p) + parseInt(1);
	                         document.getElementById(id).value= suma ; 
	                         console.log(suma);
	                         var d1 = document.getElementById(id+rel);
	                    d1.insertAdjacentHTML('beforeend', result);
	                    var n =document.getElementById(id).title;
	                    document.getElementById(id).title = parseInt(n) - parseInt(5); 
	                    var c = document.getElementById(id).title;
	                    if( c <=0 ){
	                    document.getElementById(id).disabled = true;
	                    document.getElementById(id).style.display = 'none';
	                    }
	                    }});
	        });
	        }