package com.koreait.basic.board.cmt;

import com.koreait.basic.Utils;
import com.koreait.basic.board.cmt.model.BoardCmtEntity;
import com.koreait.basic.board.model.BoardDTO;
import com.koreait.basic.dao.BoardCmtDAO;
import com.koreait.basic.dao.BoardDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/cmt/del")
public class BoardCmtDelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int iboard = Utils.getParameterInt(req, "iboard");
        int loginUserPk = Utils.getLoginUserPk(req);
        BoardCmtEntity entity = new BoardCmtEntity();
        entity.setIcmt(Utils.getParameterInt(req, "icmt"));
        entity.setWriter(loginUserPk);
        int result = BoardCmtDAO.delBoardCmt(entity);
        BoardDTO data = new BoardDTO();
        data.setIboard(iboard);
        BoardDAO.delBoardCmtCount(data);
        res.sendRedirect("/board/detail?iboard="+iboard);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}
