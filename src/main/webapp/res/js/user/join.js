function joinChk(){
    var frm = document.querySelector('#frm');
    if(frm.uid.value.length < 5 || frm.uid.value.length > 20){
        alert('아이디를 5~20자 사이로 작성해 주세요.');
        return false;
    } else if(frm.upw.value.length < 5){
        alert('비밀번호를 5자 이상 작성해 주세요.');
        return false;
    } else if(frm.upw.value !== frm.reupw.value){
        alert('비밀번호를 확인해 주세요.');
        return false;
    } else if(frm.nm.value.length > 5){
        alert('이름을 확인해 주세요.');
        return false;
    }
    return true;
}
/*
var a = 10; 변수선언은 var로
console.log(a);
10
a = '10';
'10'
10 == '10' 안에있는 값만 비교 type은 노상관 true
10 === '10' 타입, 값 다맞아야됨 false
*/