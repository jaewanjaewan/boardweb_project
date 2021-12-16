package com.koreait.basic.dao;

import com.koreait.basic.DbUtils;
import com.koreait.basic.board.model.BoardVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardRankDAO {
    //조회수 top 10
    public static List<BoardVO> selBoardHitsRankList(){ // 파라미터를 안받는다는것은 쿼리문에 ?가없다
        List<BoardVO> list = new ArrayList();
        BoardVO vo = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT A.iboard, A.title, A.writer, A.hit as cnt, A.rdt, B.nm AS writerNm " +
                "FROM t_board A INNER JOIN t_user B ON A.writer = B.iuser " +
                "WHERE hit > 0 ORDER BY hit DESC LIMIT 10";
        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                vo = BoardVO.builder()
                        .iboard(rs.getInt("iboard"))
                        .title(rs.getString("title"))
                        .writer(rs.getInt("writer"))
                        .rdt(rs.getString("rdt"))
                        .cnt(rs.getInt("cnt"))
                        .writerNm(rs.getString("writerNm"))
                        .build();
                list.add(vo);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return list;
    }
    //댓글수 top 10
    public static List<BoardVO> selBoardCmtRankList(){
        List<BoardVO> list = new ArrayList();
        BoardVO vo = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT A.iboard, A.title, A.writer, A.rdt, B.nm AS writerNm, C.cnt FROM t_board A " +
                "INNER JOIN t_user B ON A.writer = B.iuser INNER JOIN " +
                "(SELECT iboard, COUNT(icmt) AS cnt FROM t_board_cmt GROUP BY iboard) C " +
                "ON A.iboard = C.iboard ORDER BY C.cnt DESC LIMIT 10";
        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                vo = BoardVO.builder()
                        .iboard(rs.getInt("iboard"))
                        .title(rs.getString("title"))
                        .writer(rs.getInt("writer"))
                        .rdt(rs.getString("rdt"))
                        .cnt(rs.getInt("cnt"))
                        .writerNm(rs.getString("writerNm"))
                        .build();
                list.add(vo);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return list;
    }
    //좋아요 top 10
    public static List<BoardVO> selBoardHeartRankList(){
        List<BoardVO> list = new ArrayList();
        BoardVO vo = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT A.iboard, A.title, A.writer, A.rdt, B.nm AS writerNm, C.cnt FROM t_board A " +
                "INNER JOIN t_user B ON A.writer = B.iuser INNER JOIN " +
                "(SELECT iboard, COUNT(iuser) AS cnt FROM t_board_heart GROUP BY iboard) C " +
                "ON A.iboard = C.iboard ORDER BY C.cnt DESC LIMIT 10";
        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                vo = BoardVO.builder()
                        .iboard(rs.getInt("iboard"))
                        .title(rs.getString("title"))
                        .writer(rs.getInt("writer"))
                        .rdt(rs.getString("rdt"))
                        .cnt(rs.getInt("cnt"))
                        .writerNm(rs.getString("writerNm"))
                        .build();
                list.add(vo);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return list;
    }
}
