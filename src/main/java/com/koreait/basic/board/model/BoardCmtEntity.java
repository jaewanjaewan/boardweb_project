package com.koreait.basic.board.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BoardCmtEntity {
    private int icmt;
    private int iboard;
    private String ctnt;
    private int writer;
    private String rdt;
}
