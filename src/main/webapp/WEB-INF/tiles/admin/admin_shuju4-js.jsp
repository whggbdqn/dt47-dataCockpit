<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- page script -->
<script>
  $(function () {
	var t =	$('#example1').DataTable({
		 "ajax"		: {
	    	  "url"		: "<%=request.getContextPath()%>/dt_list2.shtml",
	    	  "method"	: "POST",
	    	  "headers" : {"Content-Type": "application/json"},
	    	  "data"	: function(d){
	    		  return JSON.stringify(d);
	    	  }
	      },
	      "columns": [
	    	  {"data": "office","defaultContent": "1"},
	    	  {"data":"email"},
	    	  {"data":"name"},
	    	  {"data":"job"},
	    	  {"data":"phone"},	    	  
	    	  ],
	      "columnDefs": [
			  {
			    "targets": 5,
			    "render": function ( data, type, row, meta ) {
			     /*将当前值传入controller */	    		    	
			     return '<a href="./admin_delete.shtml?id='+row.id+'" onclick="return dd();"  class="button button-primary button-rounded button-small" style="font-size: 14px;">删除</a>';	 			    			
			    }
			  }
			],	      
	});
	    	  
		t.on('order.dt search.dt',
		 function() {
   		       t.column(0, {
   		              search: 'applied',
   		              order: 'applied',
   		        }).nodes().each(function(cell, i) {
   		              cell.innerHTML = i + 1;
   		  	  
   		  	   })        	 	
   		  	   }).draw();		
  })
  
  
</script>
<script language="jscript"> 
	function dd() { if(confirm("请确认是否删除！" )) return true; return false;  }
</script> 
