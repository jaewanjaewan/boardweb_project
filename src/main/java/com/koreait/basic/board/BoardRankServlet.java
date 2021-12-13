package com.koreait.basic.board;

import com.koreait.basic.Utils;
import com.koreait.basic.dao.BoardRankDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/rank")
public class BoardRankServlet extends HttpServlet { // 조회수, 좋아요, 댓글수 top list servlet한곳으로 가능
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String type = req.getParameter("type");
        String title = null;
        switch (type){
            case "1":
                title = "조회수 top 10";
                break;
        }
        req.setAttribute("item", BoardRankDAO.selBoardHitsRankList());
        Utils.displayView(title, "board/rank", req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}
