package com.koreait.basic.board.cmt;

import com.koreait.basic.Utils;
import com.koreait.basic.board.cmt.model.BoardCmtEntity;
import com.koreait.basic.dao.BoardCmtDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/cmt/mod")
public class BoardCmtModServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        BoardCmtEntity param = new BoardCmtEntity();
        int iboard = Utils.getParameterInt(req, "iboard");
        param.setWriter(Utils.getLoginUserPk(req));
        param.setIcmt(Utils.getParameterInt(req, "icmt"));
        param.setCtnt(req.getParameter("ctnt"));
        int result = BoardCmtDAO.updBoardCmt(param);
        res.sendRedirect("/board/detail?iboard="+iboard);
    }
}
