package com.koreait.basic.user;

import com.koreait.basic.FileUtils;
import com.koreait.basic.Utils;
import com.koreait.basic.dao.UserDAO;
import com.koreait.basic.user.model.UserEntity;
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
        int loginUserPk = Utils.getLoginUserPk(req);
        UserEntity entity = new UserEntity();
        entity.setIuser(loginUserPk);
        req.setAttribute("data", UserDAO.selUser(entity));
        String title = "프로필";
        req.setAttribute("subPage", "user/profile");
        Utils.displayView(title, "user/myPage", req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int loginUserPk = Utils.getLoginUserPk(req); /* pk값으로 폴더 만들기위해 */
        int maxSize = 10_485_760; /* 파일크기 1024 * 1024 * 10 (10mb) 이하만 업로드 된다 */
        ServletContext application = req.getServletContext();
        String targetPath = application.getRealPath("/res/img/profile/" + loginUserPk); /* 톰캣에서 돌아가는 프로젝트 root
        경로값 문자열을 준다. */
        File file = new File(targetPath);
        if(file.exists()){ /* 폴더에 이미지가 있다면 기존꺼 지운다 */
            FileUtils.delFolderFiles(targetPath, false);
        } else {
            file.mkdirs(); /* 중간에 없던 폴더도 다만들어줌 얘만 쓰면됨, 톰캣자체에서 생성한다? */
        }
        MultipartRequest mr = new MultipartRequest(req, targetPath, maxSize, "UTF-8", new DefaultFileRenamePolicy());
        /*  DefaultFileRenamePolicy()는 파일명 중복 방지 */
        String changedFileNm = mr.getFilesystemName("profileImg"); /* 어차피 기존 파일지우고 추가하는거라 필요없긴함 FM으로 만들어봄 */

        UserEntity entity = new UserEntity();
        entity.setIuser(loginUserPk); /* 누구의 정보 */
        entity.setProfileImg(changedFileNm); /* 어떤것을 바꿀지에 대한 정보 */
        int result = UserDAO.updUser(entity);
        res.sendRedirect("/user/profile"); /* 밑에꺼 쓰면 안된다 */
        /* doGet(req, res); 업로드하고 새로고침하면 또post 호출해서 마지막이 get방식이 될수있게해야됨 */
    }
}
