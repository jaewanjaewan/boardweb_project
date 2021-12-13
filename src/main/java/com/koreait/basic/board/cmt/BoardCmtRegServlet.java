package com.koreait.basic.board.cmt;

import com.koreait.basic.Utils;
import com.koreait.basic.board.model.BoardCmtEntity;
import com.koreait.basic.board.model.BoardDTO;
import com.koreait.basic.dao.BoardCmtDAO;
import com.koreait.basic.dao.BoardDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/cmt/reg")
public class BoardCmtRegServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        BoardCmtEntity param = new BoardCmtEntity();
        param.setIboard(Utils.getParameterInt(req, "iboard"));
        param.setCtnt(req.getParameter("ctnt"));
        param.setWriter(Utils.getLoginUserPk(req));

        int result = BoardCmtDAO.insBoardCmt(param);
        BoardDTO data = new BoardDTO();
        data.setIboard(param.getIboard());
        BoardDAO.updBoardCmtCount(data);
        res.sendRedirect("/board/detail?nohits=1&iboard="+Utils.getParameterInt(req, "iboard"));
    }
}
