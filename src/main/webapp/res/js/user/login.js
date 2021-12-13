var frm = document.querySelector('#frm');
if(frm){
    function frmSubmitEvent(e){
        //아이디가 5글자 미만 혹은 20글자 초과가 되면 "아이디는 5~20글자 입니다."
        if(frm.uid.value.length < 5 || frm.uid.value.length > 20 ){
            alert('아이디는 5~20글자 입니다.');
            e.preventDefault; //이벤트를 그냥 막는것, 문제가 생겼을때 막음(함수종료X)
            return;
        }
        //비밀번호는 5글자 미만일때 "비밀번호를 확인해 주세요."
        if(frm.upw.value.length < 5){
            alert('비밀번호를 확인해주세요');
            e.preventDefault;
            return;
        }
        alert('로그인 성공!');
    }
    frm.addEventListener('submit', frmSubmitEvent);

    var btnShowPw = document.querySelector('#btnShowPw');
    if(btnShowPw){
        btnShowPw.addEventListener('click', function (){
            if(frm.upw.type === 'password'){
                frm.upw.type = 'text';
                btnShowPw.value = '비밀번호 숨기기';
            } else {
                frm.upw.type = 'password';
                btnShowPw.value = '비밀번호 보이기';
            }
        });
    }
}
