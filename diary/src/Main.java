public class Main {

    public static void main(String[] args){
        Model model  = new Model();
        model.getConnection();

        View v = new View();
        int mode = v.selectMode();

        if(mode == 1) // 글 쓰기
        {
            model.setData();
        }
        else // 글 보기
        {
            model.getData();
        }

        model.closeConnection();
    }
}
