var mapContainer = document.getElementById('map'); 
var mapOption = { 
    center: new kakao.maps.LatLng(37.566535, 126.97796919999996), // 지도 중심 좌표 (서울시청)
    level: 4 // 지도 확대 레벨 
}; 

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
var infowindow = new kakao.maps.InfoWindow({zIndex:1}); // 인포윈도우를 생성합니다
var ps = new kakao.maps.services.Places(); // 장소 검색 객체를 생성합니다
var markers = []; // 현재 지도에 표시된 마커를 저장할 배열
var currCategory = ''; // 현재 선택된 카테고리를 저장할 변수
var placeOverlay = new kakao.maps.CustomOverlay({zIndex:1}), 
    contentNode = document.createElement('div');

// HTML5의 geolocation으로 사용할 수 있는지 확인합니다 
if (navigator.geolocation) {
    // GeoLocation을 이용해서 접속 위치를 얻어옵니다
    navigator.geolocation.getCurrentPosition(function(position) {
        var lat = position.coords.latitude, // 위도
            lon = position.coords.longitude; // 경도
        var locPosition = new kakao.maps.LatLng(lat, lon); // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
        map.setCenter(locPosition); // 지도 중심 좌표를 접속 위치로 변경합니다
    });
} else { // HTML5의 GeoLocation을 사용할 수 없을 때
    var locPosition = new kakao.maps.LatLng(37.566535, 126.97796919999996); // 서울시청을 기본 위치로 설정합니다
    map.setCenter(locPosition);
}

document.getElementById('searchButton').addEventListener('click', function() {
    var keyword = document.getElementById('keyword').value;
    var radius = document.getElementById('radius').value * 1000; // km를 m로 변환
    var center = map.getCenter();

    clearMarkers(); // 기존 마커 제거

    if (keyword) {
        searchKeyword(keyword, center, radius);
    } else if (currCategory) {
        searchCategory(currCategory, center, radius);
    } else {
        alert('키워드 또는 카테고리를 입력해주세요.');
    }
});

function searchKeyword(keyword, locPosition, radius) {
    var callback = function(result, status) {
        if (status === kakao.maps.services.Status.OK) {
            displayPlaces(result);
        }
    };
    ps.keywordSearch(keyword, callback, { location: locPosition, radius: radius });
}

function searchCategory(category, locPosition, radius) {
    var callback = function(result, status) {
        if (status === kakao.maps.services.Status.OK) {
            displayPlaces(result);
        }
    };
    ps.categorySearch(category, callback, { location: locPosition, radius: radius });
}

function displayPlaces(places) {
    var listEl = document.getElementById('placesList'), 
    fragment = document.createDocumentFragment(), 
    bounds = new kakao.maps.LatLngBounds();
    
    listEl.innerHTML = '';
    
    for (var i=0; i<places.length; i++) {
        var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
            itemEl = getListItem(i, places[i]), // 검색 결과 항목 Element를 생성합니다
            marker = addMarker(placePosition, i); // 마커를 생성하고 지도에 표시합니다
        
        bounds.extend(placePosition);
        
        (function(marker, place) {
            kakao.maps.event.addListener(marker, 'click', function() {
                displayPlaceInfo(place);
            });
            
            itemEl.onclick = function() {
                map.setCenter(placePosition);
                displayPlaceInfo(place);
            };
        })(marker, places[i]);
        
        fragment.appendChild(itemEl);
    }
    
    listEl.appendChild(fragment);
    map.setBounds(bounds);
}

function getListItem(index, places) {
    var el = document.createElement('li'),
    itemStr = '<span class="markerbg marker_' + (index+1) + '"></span>' +
              '<div class="info">' +
              '   <h5>' + places.place_name + '</h5>';

    el.innerHTML = itemStr;
    el.className = 'item';

    return el;
}

// 마커를 생성하고 지도에 표시하는 함수입니다
function addMarker(position, idx) {
    var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 URL
        imageSize = new kakao.maps.Size(36, 37),  // 마커 이미지의 크기
        imgOptions =  {
            spriteSize : new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
            spriteOrigin : new kakao.maps.Point(0, (idx*46)+10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
            offset: new kakao.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        },
        markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
            marker = new kakao.maps.Marker({
            position: position, // 마커의 위치
            image: markerImage 
        });

    marker.setMap(map); // 지도 위에 마커를 표시합니다
    markers.push(marker); // 배열에 추가하여 관리
    return marker;
}

// 기존 마커 제거 함수
function clearMarkers() {
    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(null);
    }
    markers = [];
}

// 클릭한 마커에 대한 장소 상세정보를 커스텀 오버레이로 표시하는 함수입니다
function displayPlaceInfo(place) {
    var content = '<div class="placeinfo">' +
                    '   <a class="title" href="' + place.place_url + '" target="_blank" title="' + place.place_name + '">' + place.place_name + '</a>';   

    if (place.road_address_name) {
        content += '    <span title="' + place.road_address_name + '">' + place.road_address_name + '</span>' +
                    '  <span class="jibun" title="' + place.address_name + '">(지번 : ' + place.address_name + ')</span>';
    } else {
        content += '    <span title="' + place.address_name + '">' + place.address_name + '</span>';
    }                
   
    content += '    <span class="tel">' + place.phone + '</span>' + 
                '</div>' + 
                '<div class="after"></div>';

    contentNode.innerHTML = content;
    placeOverlay.setPosition(new kakao.maps.LatLng(place.y, place.x));
    placeOverlay.setMap(map);  
}

// 각 카테고리에 클릭 이벤트를 등록합니다
function addCategoryClickEvent() {
    var category = document.getElementById('category'),
        children = category.children;

    for (var i=0; i<children.length; i++) {
        children[i].onclick = onClickCategory;
    }
}

// 카테고리를 클릭했을 때 호출되는 함수입니다
function onClickCategory() {
    var id = this.id,
        className = this.className;

    placeOverlay.setMap(null);

    if (className === 'on') {
        currCategory = '';
        changeCategoryClass();
        clearMarkers();
    } else {
        currCategory = id;
        changeCategoryClass(this);
        searchCategory(id, map.getCenter(), document.getElementById('radius').value * 1000);
    }
}

// 클릭된 카테고리에만 클릭된 스타일을 적용하는 함수입니다
function changeCategoryClass(el) {
    var category = document.getElementById('category'),
        children = category.children,
        i;

    for (i=0; i<children.length; i++) {
        children[i].className = '';
    }

    if (el) {
        el.className = 'on';
    }
}

// 커스텀 오버레이의 컨텐츠 노드에 css class를 추가합니다 
contentNode.className = 'placeinfo_wrap';
placeOverlay.setContent(contentNode);

// 커스텀 오버레이의 컨텐츠 노드에 mousedown, touchstart 이벤트가 발생했을때
// 지도 객체에 이벤트가 전달되지 않도록 이벤트 핸들러로 kakao.maps.event.preventMap 메소드를 등록합니다 
addEventHandle(contentNode, 'mousedown', kakao.maps.event.preventMap);
addEventHandle(contentNode, 'touchstart', kakao.maps.event.preventMap);

// 엘리먼트에 이벤트 핸들러를 등록하는 함수입니다
function addEventHandle(target, type, callback) {
    if (target.addEventListener) {
        target.addEventListener(type, callback);
    } else {
        target.attachEvent('on' + type, callback);
    }
}

// 각 카테고리에 클릭 이벤트를 등록합니다
addCategoryClickEvent();
