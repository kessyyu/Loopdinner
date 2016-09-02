<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<script language="JavaScript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-json/2.5.1/jquery.json.min.js"></script>
<script type="text/javascript">  
    $(document).ready(function(){  
    	alert("12");
        var saveDataAry=[];  
        var data1={"name":"test","price":"gz"};  
        var data2={"name":"ququ","price":"gr"};  
        saveDataAry.push(data1);  
        saveDataAry.push(data2);         
        $.ajax({ 
            type:"POST", 
            url:"http://localhost:8080/server/saveUser", 
            dataType:"json",      
            contentType:"application/json",               
            data:JSON.stringify(saveDataAry), 
            success:function(data){ 
                                       
            } 
         }); 
    });  
</script> 
</head>
<body>
<input type='button' id='test' value='Click' onclick='test()'/>
</body>
</html>