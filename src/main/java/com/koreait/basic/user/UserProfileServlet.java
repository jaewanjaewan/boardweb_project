package com.koreait.basic.user;

import com.koreait.basic.Utils;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/user/profile")
public class UserProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String title = "프로필";
        req.setAttribute("subPage", "user/profile");
        Utils.displayView(title, "user/myPage", req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int loginUserPk = Utils.getLoginUserPk(req);
        int maxSize = 10_485_760; /* 파일크기 1024 * 1024 * 10 (10mb) 이하만 업로드 된다 */
        ServletContext application = req.getServletContext();
        String targetPath = application.getRealPath("/res/img/profile/" + loginUserPk);
        File file = new File(targetPath);
        file.mkdirs();
        /*서버의 절대경로에서 profile폴더까지 들어감? 저장되는곳?*/
        MultipartRequest mr = new MultipartRequest(req, targetPath, maxSize, "UTF-8", new DefaultFileRenamePolicy());
        /*  DefaultFileRenamePolicy()는 파일 name 중복 방지 */
        Enumeration files = mr.getFileNames();
        String fileNm = (String) files.nextElement();
    }
}
