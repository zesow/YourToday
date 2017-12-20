import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Model {
    Connection con;
    Statement stmt;
    ResultSet rs;

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
        String sql = "SELECT id,subject,content,created FROM diary LIMIT " + (nowPage - 1) * 5 + "," + 5;
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
        int select;
        int totalPage = totalPage();
        int nowPage = 1;

        try{
            stmt = con.createStatement();
            pageSQL(nowPage,stmt);

            while(true){
                System.out.println("보고 싶은 글 번호를 선택하세요. 더이상 글을 보고싶지 않으면 0번을 눌러주세요.");
                System.out.println("이전 페이지는 b , 다음 페이지는 n.");
                System.out.println("페이지 :" + nowPage + " / " + totalPage);
                Scanner sc = new Scanner(System.in);
                select = sc.next().charAt(0);
                if(select == 'b'){
                    if(nowPage == 1){
                        System.out.println("첫 번째 페이지입니다.");
                        pageSQL(nowPage,stmt);
                        continue;
                    }

                    nowPage--;
                    pageSQL(nowPage,stmt);
                }
                else if(select == 'n'){
                    if(nowPage == totalPage){
                        System.out.println("마지막 페이지입니다.");
                        pageSQL(nowPage,stmt);
                        continue;
                    }

                    nowPage++;
                    pageSQL(nowPage,stmt);
                }
                else if(select == '0'){
                    break;
                }
                else{
                    selectWrite(select - 48);
                    pageSQL(nowPage,stmt);
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

//    글 쓰기
    private String getTitle()
    {
        System.out.println("글의 제목을 입력해 주세요.");
        String title = sc.nextLine();

        return title;
    }
    private String getContent()
    {
        System.out.println("글의 내용을 입력해 주세요.");
        String content = sc.nextLine();

        return content;
    }

    public void setData(){
        String title = getTitle();
        String content = getContent();

        try{
            stmt = con.createStatement();
            String sql = "INSERT INTO diary(subject,content) VALUES ('" + title +"','" + content + "')";
            stmt.executeUpdate(sql);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public void closeConnection(){
        try{
            if(rs != null)
                rs.close();
            stmt.close();
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
