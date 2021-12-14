package com.koreait.basic.board;

import com.koreait.basic.Utils;
import com.koreait.basic.board.cmt.model.BoardCmtDTO;
import com.koreait.basic.board.model.BoardDTO;
import com.koreait.basic.board.model.BoardHeartEntity;
import com.koreait.basic.dao.BoardCmtDAO;
import com.koreait.basic.dao.BoardDAO;
import com.koreait.basic.dao.BoardHeartDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/detail")
public class BoardDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int nohits = Utils.getParameterInt(req, "nohits");
        int iboard = Integer.parseInt(req.getParameter("iboard"));
        BoardDTO dto = new BoardDTO();
        dto.setIboard(iboard);
        req.setAttribute("item", BoardDAO.selBoardDetail(dto));

        //로그인 한사람의 pk값과 data에 들어있는 writer값이 다르거나 혹은 로그인이 안되어있으면 hit값을 올려주세요/
        int loginUserPk = Utils.getLoginUserPk(req);

        if(loginUserPk > 0){ //로그인 되어 있어야 좋아요를 누를수 있기때문에
            BoardHeartEntity bhparam = new BoardHeartEntity();
            bhparam.setIuser(loginUserPk);
            bhparam.setIboard(iboard);
            req.setAttribute("isHeart", BoardHeartDAO.selIsHeart(bhparam));
        }

        if(BoardDAO.selBoardDetail(dto).getWriter() != loginUserPk && nohits != 1){ //로그인 안되어있으면 0, 로그인되어있으면 pk값
            BoardDAO.updBoardHitUp(dto);
        }

        BoardCmtDTO cmtParam = new BoardCmtDTO();
        cmtParam.setIboard(iboard);
        req.setAttribute("cmtList", BoardCmtDAO.selBoardCmtList(cmtParam));
        Utils.displayView("상세정보", "board/detail", req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}
