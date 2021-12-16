var frmElem = document.querySelector('#frm');
var submitBtnElem = document.querySelector('#submitBtn');

submitBtnElem.addEventListener('click', function () {
    if(frmElem.upw.value.length < 5 || frmElem.changedUpw.value.length < 5){
        alert('비밀번호를 5자 이상 입력해주세요');
        /*e.preventDefault()는 form태그안에서 submit으로 보낼때 사용*/
    } else if(frmElem.changedUpw.value !== frmElem.changedUpwConfirm.value){
        alert('변경 비밀번호와 확인 비밀번호가 다릅니다.');
    } else {
        frmElem.submit();
    }
});