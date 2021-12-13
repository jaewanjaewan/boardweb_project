package com.koreait.basic.board.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardVO { //값을 대표하는 객체(불변성), setter가 없음, 생성자 통해서 값넣어야함, view(select)용
    private int iboard;
    private String title;
    private String ctnt;
    private int writer;
    private int hit;
    private String rdt;
    private String mdt;
    private int cmtcount;
    private String writerNm;
}
