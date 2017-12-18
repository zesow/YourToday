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
    private void selectWrite(int writeNum){
        String sql = "SELECT content FROM diary WHERE id=" + writeNum;
        try {
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                System.out.println(rs.getString("content"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void getData(){
        try{
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT id,subject,content,created from diary");
            System.out.println("보고 싶은 글 번호를 선택하세요.");
            int writeNum;
            while(rs.next()){
                System.out.println("------------------");
                System.out.println(rs.getString("id") + " / " + rs.getString("created")
                                    + " / " + rs.getString("subject"));
            }
            writeNum = sc.nextInt();
            selectWrite(writeNum);
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
