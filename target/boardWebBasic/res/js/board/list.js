function moveToDetail(iboard){
    location.href="/board/detail?iboard="+iboard;
}
var searchFrmElem = document.querySelector('#searchFrm');
if(searchFrmElem){
    var rowCntSelectElem = searchFrmElem.rowCnt;
    rowCntSelectElem.addEventListener('change', function (e) {
       searchFrmElem.submit();
    });
}