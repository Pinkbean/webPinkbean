(function() {

	var self;
	
	popUp = function(){
		self = this;
		this.init(); 
	};
	
	popUp.prototype.init = function(){
		// 클릭시 이미지 팝업 이벤트
		this.popup();
	}

	popUp.prototype.popup = function(){
		$('#popup').on('click',function(){
			var url = "/resources/images/pinkbean.jpg";
			var openParam = "width=581,height=817,status=no,center=yes,resizable=yes,scrollbars=yes";
			window.open(url,"",openParam);		
		});
		
		$('#popupContent').on('click',function(){
//			var returnURL = location.href;
//			//var url = "/WEB-INF/jsp/popup/pop.jsp;
//			var url = "/popup/pop";
//			
//            // window.name = "부모창 이름"; 
//            window.name = "parentForm";
//            // window.open("open할 window", "자식창 이름", "팝업창 옵션");
//            openWin = window.open(url,
//                    "childForm", "width=570, height=350, resizable = no, scrollbars = no"); 
			var parentName = "parentView";
			var childName  = "childView";
			
			window.open("", childName);
			$('#major').attr("target",childName);
			$('#major').attr("action","/popup/pop").submit();	
			$('#major').attr("target",'_self'); // 폼의 타깃을 부모창으로 돌리는 것
			
		});
	}
	
	
//	//로그인
//	function loginPop() {
//	  //var returnURL = location.href;
//	  //var returnURL = 'http://hansikfront.innodis.co.kr/kr';
//	  var returnURL = 'http://www.hansik.or.kr/kr';
//	  
//	  returnURL = returnURL.replace(/\?/gi, '@');
//	  returnURL = returnURL.replace(/\&/gi, '$');
//
//	  var url = returnURL + "/sso/ssostart.jsp?lang=kr&siteID=H&login=Y&returnURL="+returnURL+"&header_login";
//
//	  window.name='parantPage';
//	  window.open(url,'loginpop','width=600,height=650,resizable=yes,scrollbars=0,toolbar=0,status=0,menubar=0,left=0,top=0');
//	}	
//	

	$(function(){
		popUp = new popUp();
	});
})();