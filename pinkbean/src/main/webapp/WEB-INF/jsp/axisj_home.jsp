
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<!-- AxisjGridTest -->
<!-- css block -->
<link rel="stylesheet" type="text/css" href="/resources/axj/ui/arongi/AXJ.css">
<link rel="stylesheet" type="text/css" href="/resources/axj/ui/arongi/AXInput.css">
<link rel="stylesheet" type="text/css" href="/resources/axj/ui/arongi/AXButton.css">
<link rel="stylesheet" type="text/css" href="/resources/axj/ui/arongi/AXSelect.css">
<link rel="stylesheet" type="text/css" href="/resources/axj/ui/arongi/AXGrid.css">
<!-- 이것 저것 많이 넣어야 합니다. 이런저런 것들이 귀찮은 분들은 AXJ.min.css 선언해 주셔도 됩니다. -->
<!-- css block -->
<!-- js block -->
<script type="text/javascript" src="/resources/axj/jquery/jquery.min.js"></script>
<script type="text/javascript" src="/resources/axj/lib/AXJ.js"></script>
<script type="text/javascript" src="/resources/axj/lib/AXInput.js"></script>
<script type="text/javascript" src="/resources/axj/lib/AXSelect.js"></script>
<script type="text/javascript" src="/resources/axj/lib/AXGrid.js"></script>
<script type="text/javascript" src="/resources/axj/lib/AXProgress.js"></script>

<html>
<head>
<title>Home</title>
<script>
<!-- ex from: http://jdoc.axisj.com/document/index.html -->

$(function() {
	var myGrid = new AXGrid(); // 그리드 변수를 초기화 합니다.
	var fnObj = {
	    pageStart: function(){
	        myGrid.setConfig({
	            targetID : "AXGridTarget",
	            colGroup : [
	                {key:"no", label:"번호", width:"50", align:"right"},
	                {key:"title", label:"제목", width:"200"},
	                {key:"writer", label:"작성자", width:"100"},
	                {key:"regDate", label:"작성일", width:"100"},
	                {key:"price", label:"가격", width:"100", align:"right", formatter:"money"},
	                {key:"amount", label:"수량", width:"80", align:"right", formatter:"money"},
	                {key:"cost", label:"금액", width:"100", align:"right", formatter:function(){
	                    return (this.item.price.number() * this.item.amount.number()).money();
	                }},
	                {key:"desc", label:"비고", width:"*"}
	            ],
	            body : {
	                onclick: function(){
	                    toast.push(Object.toJSON(this.item));
	                }
	            }
	        });
	        
	        var list = [
	            {no:1, title:"AXGrid 첫번째 줄 입니다.", writer:"김기영", regDate:"2013-01-18", 
	                desc:"myGrid.setList 의 첫번째 사용법 list json 직접 지정 법", price:123000, amount:10},
	            {no:2, title:"AXGrid 두번째 줄 입니다.", writer:"정기영", regDate:"2013-01-18", 
	                desc:"myGrid.setList 의 첫번째 사용법 list json 직접 지정 법", price:12300, amount:7},
	            {no:3, title:"AXGrid 세번째 줄 입니다.", writer:"한기영", regDate:"2013-01-18", 
	                desc:"myGrid.setList 의 첫번째 사용법 list json 직접 지정 법", price:12000, amount:5},
	            {no:4, title:"AXGrid 세번째 줄 입니다.", writer:"박기영", regDate:"2013-01-18", 
	                desc:"myGrid.setList 의 첫번째 사용법 list json 직접 지정 법", price:13000, amount:4},
	            {no:5, title:"AXGrid 세번째 줄 입니다.", writer:"곽기영", regDate:"2013-01-18", 
	                desc:"myGrid.setList 의 첫번째 사용법 list json 직접 지정 법", price:3000, amount:3}
	        ];
	        myGrid.setList(list);
	    }
	};
	
	$("#changeListBtn").on("click", function() {
        var list = [
            {no:1, title:"AXGrid 첫번째 줄 입니다.", writer:"김기영", regDate:"2013-01-18", 
                desc:"클릭으로 리스트 변경하기 테스트", price:123000, amount:10}
        ];
        
        
		myGrid.setList(list);
	});
	
	$("#fileCheckBtn").on("click", function() {
        
        var formData = new FormData(); 
        for (var i = 0; i < $("#fileObj").get(0).files.length ; i++){
        	formData.append('file'+i, $("#fileObj").get(0).files[i]); 
        }
        
        for (var value of formData.values()) {
        	  console.log(value);
        }

		// file 전송 ajax
        $.ajax({
            url: "/file/upload", 
            data: formData,  
			processData: false,  // file전송시 필수
	        contentType: false,  // file전송시 필수         
            enctype: "multipart/form-data",
            type: "POST",    
            dataType: "json"   
        })
        .done(function(d) {
			console.log(d);
			//location.reload();
        })
        .fail(function(d) {

            alert("요청 실패" + d.value);
           // location.reload();
        });    
	});	
	
	fnObj.pageStart();
});
</script>
</head>
<body>
	<!-- grid test -->
	<div id="AXGridTarget" style="height:400px;"></div>
	
	<input type="button" value="test" id="changeListBtn"/>
	
	<form id="fileForm" action="/file/upload" method="post" enctype="multipart/form-data">
		<input multiple="multiple" type="file" id="fileObj" name="fileObj">	
	</form>
	
	<input type="button" value="test" id="fileCheckBtn"/>
</body>
</html>