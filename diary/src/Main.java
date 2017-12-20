import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Model model  = new Model();
        model.getConnection();
        Scanner sc = new Scanner(System.in);
        View v = new View();

        while(true) {
            int mode = v.selectMode();

            if (mode == 1) // 글 쓰기
            {
                model.setData();
            } else if (mode == 2)// 글 보기
            {
                model.getData();
            }
            else // 종료
                break;
        }
        model.closeConnection();
    }
}
