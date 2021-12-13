package com.koreait.basic.board;

import com.koreait.basic.Utils;
import com.koreait.basic.board.model.BoardDTO;
import com.koreait.basic.board.model.BoardEntity;
import com.koreait.basic.dao.BoardDAO;
import com.koreait.basic.user.model.LoginResult;
import com.koreait.basic.user.model.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/board/regmod")
public class BoardRegModServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int iboard = Utils.getParameterInt(req, "iboard");
        String title = "글등록";
        //수정 or 등록 분기
        if(iboard > 0) { //수정
            title = "글수정";
            if(req.getAttribute("item") == null){
                BoardDTO dto = new BoardDTO();
                dto.setIboard(iboard);
                req.setAttribute("item", BoardDAO.selBoardDetail(dto));
            }
        }
        Utils.displayView(title, "board/regmod", req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int loginUserPk = Utils.getLoginUserPk(req);
        if(loginUserPk == 0){ //즉 로그인이 안되어있을때
            res.sendRedirect("/user/login");
            return;
        }

        int iboard = Utils.getParameterInt(req, "iboard");
        String title = req.getParameter("title");
        String ctnt = req.getParameter("ctnt");
        /*UserEntity loginUser = Utils.getLoginUser(req);*/
        int result = 0;
        BoardEntity entity = new BoardEntity();
        entity.setTitle(title);
        entity.setCtnt(ctnt);
        entity.setWriter(loginUserPk);

        if(iboard == 0){ //등록
            result = BoardDAO.insBoardWithPk(entity);
        } else { //수정
            entity.setIboard(iboard);
            result = BoardDAO.updBoard(entity);
        }

        switch (result) {
            case 1:
                if(entity.getIboard() != 0) {
                    res.sendRedirect("/board/detail?iboard=" + entity.getIboard());
                    return;
                }
               break;
            default:
                req.setAttribute("err", "등록/수정에 실패하였습니다.");
                req.setAttribute("item", entity);
                doGet(req, res);
                break;
        }
        res.sendRedirect("/board/list");
    }
}
