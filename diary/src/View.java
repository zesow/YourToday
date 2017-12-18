import java.util.Scanner;

public class View {

    public int selectMode(){
        System.out.println("---------------------");
        System.out.println("번호를 선택하세요");
        System.out.println("1. 글 쓰기");
        System.out.println("2. 글 보기");
        System.out.println("---------------------");

        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        return num;
    }


}
