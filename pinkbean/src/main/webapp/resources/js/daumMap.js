(function() { //익명의 함수

	var self; //이건 뭐지?
	
	//cl.daumMap이란 익명 함수 객체 선언
	daumMap = function(){
		self = this;
		this.init(); 
	};
	
	// daumMap 객체들이 참고할 prototype이다.
	// prototype: 인스턴스된 모든 해당 객체에서 공유할 수 있는 객체.
	// 해당 객체의 설정되지 않은 속성에 접근을 시도할 때마다 javaScript는 객체.prototype에 대신 존재하는 속성이 있는지 살펴본다(prototype chain이 존재한다).
	// 위에서 this.init()을 호출하게 만들었지만, 해당 객체 내에는 init()이 존재하지 않는다. 따라서 daumMap 인스턴스는 prototype에서 아래의 init()을 찾아 호출하게 된다.
	daumMap.prototype.init = function(){
		this.daumMapSet();
		this.daumMiniMapSet();
	}
	
	// daum map api
	daumMap.prototype.daumMapSet = function(){
		var container = document.getElementById('map'); // 지도를 담을 영역의 DOM 레퍼런스
		var options = { // 지도를 생성할 때 필요한 기본 옵션
			center: new daum.maps.LatLng(33.450701, 126.570667), // 지도의 중심 좌표
			level: 3 // 지도의 레벨(확대, 축소)
		};

		var map = new daum.maps.Map(container, options); // 지도 생성 및 객체 리턴 			
	}
	
	// 다음 약도 생성
	daumMap.prototype.daumMiniMapSet = function(){
		new daum.roughmap.Lander({
			"timestamp" : "1522737398489",
			"key" : "nho8",
			"mapWidth" : "500",
			"mapHeight" : "200"
		}).render();		
	};
	
	$(function(){
		daumMap = new daumMap(); //daumMap 인스턴스 생성		
	});
})();