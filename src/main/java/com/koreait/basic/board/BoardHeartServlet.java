package com.koreait.basic.board;

import com.koreait.basic.Utils;
import com.koreait.basic.board.model.BoardHeartEntity;
import com.koreait.basic.dao.BoardHeartDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/heart")
public class BoardHeartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int iboard = Utils.getParameterInt(req, "iboard");
        BoardHeartEntity param = new BoardHeartEntity();
        param.setIboard(iboard);
        param.setIuser(Utils.getLoginUserPk(req));
        int proc = Utils.getParameterInt(req, "proc");
        switch (proc){
            case 1:
                BoardHeartDAO.insBoardHeart(param);
                break;
            case 2:
                BoardHeartDAO.delBoardHeart(param);
                break;
        }
        res.sendRedirect("/board/detail?nohits=1&iboard="+iboard);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}
