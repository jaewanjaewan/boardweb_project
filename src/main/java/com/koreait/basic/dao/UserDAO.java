package com.koreait.basic.dao;

import com.koreait.basic.DbUtils;
import com.koreait.basic.user.model.LoginResult;
import com.koreait.basic.user.model.UserEntity;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

public class UserDAO {
    public static int join(UserEntity param){
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO t_user(uid, upw, nm, gender) VALUES(?,?,?,?)";
        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, param.getUid());
            ps.setString(2, param.getUpw());
            ps.setString(3, param.getNm());
            ps.setInt(4, param.getGender());
            return ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }
    //무조건 LoginResult객체 주소값 리턴
    //result 0: 실패, 1: 로그인 성공, 2: 아이디 없음, 3: 비밀번호 틀림
    //result가 1이었을때만 loginUser에 로그인 한 유저의 iuser, id, nm, gender값을 저장한 객체를 담는다.
    public static LoginResult login(UserEntity entity){
        int result = 0;
        UserEntity loginUser = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT iuser, upw, nm, gender, profileImg FROM t_user WHERE uid = ? ";
        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, entity.getUid());
            rs = ps.executeQuery();
            if(rs.next()) { //아이디는 맞음
                String dbPw = rs.getString("upw");
                if (BCrypt.checkpw(entity.getUpw(), dbPw)) { //true 비밀번호 맞음
                    result = 1;
                    loginUser = new UserEntity();
                    loginUser.setIuser(rs.getInt("iuser"));
                    loginUser.setUid(entity.getUid());
                    loginUser.setNm(rs.getString("nm"));
                    loginUser.setGender(rs.getInt("gender"));
                    loginUser.setProfileImg(rs.getString("profileImg"));
                } else {result = 3;}
            } else {result = 2;}
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return new LoginResult(result, loginUser);
    }
    public static UserEntity selUser(UserEntity entity){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT uid, nm, gender, rdt, profileImg FROM t_user WHERE iuser = ? ";
        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, entity.getIuser());
            rs = ps.executeQuery();
            if(rs.next()){
                UserEntity vo = new UserEntity();
                vo.setUid(rs.getString("uid"));
                vo.setNm(rs.getString("nm"));
                vo.setGender(rs.getInt("gender"));
                vo.setRdt(rs.getString("rdt"));
                vo.setProfileImg(rs.getString("profileImg"));
                return vo;
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return null;
    }
    //비밀번호, 프로필이미지도 변경할수있음
    public static int updUser(UserEntity entity){
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "UPDATE t_user SET ";
        String changeVal = null;
        if(entity.getUpw() != null && !"".equals(entity.getUpw())){
            sql += "upw = ?";
            changeVal = entity.getUpw();
        } else if(entity.getProfileImg() != null && !"".equals(entity.getProfileImg())){
            sql += "profileImg = ?";
            changeVal = entity.getProfileImg();
        }
        sql += " WHERE iuser = ?";
        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, changeVal);
            ps.setInt(2, entity.getIuser());
            return ps.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }
    public static UserEntity selUser2(UserEntity entity){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT iuser,uid,upw,nm, gender, rdt , profileImg FROM t_user WHERE uid=?";
        if(entity.getIuser() >0){
            sql = sql.replace("uid=","iuser=");
        }
        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            if(entity.getIuser()>0){
                ps.setInt(1,entity.getIuser());
            }else{
                ps.setString(1,entity.getUid());
            }
            rs = ps.executeQuery();
            if(rs.next()){
                UserEntity vo = new UserEntity();
                vo.setIuser(rs.getInt("iuser"));
                vo.setUid(rs.getString("uid"));
                vo.setUpw(rs.getString("upw"));
                vo.setNm(rs.getString("nm"));
                vo.setGender(rs.getInt("gender"));
                vo.setRdt(rs.getString("rdt"));
                vo.setProfileImg(rs.getString("profileImg"));
                return vo;
            }
        }catch (Exception e){e.printStackTrace();
        }finally { DbUtils.close(con,ps,rs);}
        return null;
    }
}
