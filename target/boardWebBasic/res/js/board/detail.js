function isDelCmt(iboard, icmt){
    if(confirm('댓글을 삭제 하시겠습니까?')){ /*confirm함수는 내장함수이며 yes누르면 true no 누르면 false가 넘어온다*/
        location.href = '/board/cmt/del?iboard=' + iboard + '&icmt='+ icmt;
    }
}

var cmtModContainerElem = document.querySelector('.cmtModContainer'); /*queryselector은 클래스 하나만 들고오고 queryselectorAll은 다갖고옴*/
var btnCancelElem = cmtModContainerElem.querySelector('#btnCancel');
btnCancelElem.addEventListener('click', function () {
    cmtModContainerElem.style.display = 'none';
});

function openModForm(icmt, ctnt){
        cmtModContainerElem.style.display = 'flex';
        var cmtModFrmElem = cmtModContainerElem.querySelector('#cmtModFrm');
        cmtModFrmElem.icmt.value = icmt;
        cmtModFrmElem.ctnt.value = ctnt;
}