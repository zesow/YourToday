package com.gus.diary.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Model {
    static Connection con;
    static Statement stmt;
    static ResultSet rs;

    String url = "jdbc:mysql://localhost:3306/Diary";
    String id = "root";
    String pw = "200101";
    Scanner sc = new Scanner(System.in);
    public Model(){

        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void getConnection(){
        try{
            con = DriverManager.getConnection(url,id,pw);
            System.out.println("연결 완료");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

//    글 읽기
    public void selectWrite(int writeNum){
        String sql = "SELECT content FROM diary WHERE id=" + writeNum;
        try {
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                System.out.println("*******************");
                System.out.println(rs.getString("content"));
                System.out.println("*******************");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

//    페이지 갯수 계산
    private int totalCount(){
        int totalCount = 0;
        String sql = "SELECT count(*) FROM diary";
        try{
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                totalCount = rs.getInt("count(*)");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return totalCount;

    }
    private int totalPage(){
        int totalCount = totalCount();
        int countList = 5;

        int totalPage = totalCount / countList;

        if(totalCount % countList > 0)
            totalPage++;

        return totalPage;
    }
    private void pageSQL(int nowPage,Statement stmt){
//        String sql = "SELECT id,subject,content,created FROM diary LIMIT " + (nowPage - 1) * 5 + "," + 5;
        String sql = "SELECT id,subject,created FROM diary";
        try {
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println("------------------");
                System.out.println(rs.getString("id") + " / " + rs.getString("created")
                        + " / " + rs.getString("subject"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void getData(){
        String select;
        int totalPage = totalPage();
        int nowPage = 1;

        try{
            stmt = con.createStatement();
            pageSQL(nowPage,stmt);

            while(true){
                select = sc.next();
                System.out.println(select);
                if(select.equals("b")){
                    if(nowPage == 1){ // 첫 번째 페이지
                        pageSQL(nowPage,stmt);
                        continue;
                    }

                    nowPage--;
                    pageSQL(nowPage,stmt);
                }
                else if(select.equals("n")){ // 마지막 페이지
                    if(nowPage == totalPage){
                        pageSQL(nowPage,stmt);
                        continue;
                    }

                    nowPage++;
                    pageSQL(nowPage,stmt);
                }
                else if(select.equals("0")){
                    break;
                }
                else{
                    selectWrite(Integer.parseInt(select));
                    pageSQL(nowPage,stmt);
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

//    글 쓰기
    public static void setData(String _title,String _content){
        String title = _title;
        String content = _content;

        try{
            stmt = con.createStatement();
            String sql = "INSERT INTO diary(subject,content) VALUES ('" + title +"','" + content + "')";
            stmt.executeUpdate(sql);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public static void closeConnection(){
        try{
            if(rs != null)
                rs.close();
            if(stmt != null)
                stmt.close();
            if(con != null)
                con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
