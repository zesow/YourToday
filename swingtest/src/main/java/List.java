import com.mommoo.flat.layout.linear.LinearLayout;
import com.mommoo.flat.layout.linear.Orientation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class List extends JFrame{
    JPanel jp = new JPanel();
    JButton btn = new JButton("뒤로");
    JList list = null;
    public List() throws HeadlessException {
        super("list");

        list = new JList(addSubjects());
        jp.setLayout(new LinearLayout(Orientation.VERTICAL));
        jp.add(list);
        jp.add(btn);
        add(jp);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Intro it = new Intro();
            }
        });
        setSize(400, 300); // 윈도우의 크기 가로x세로
        setVisible(true); // 창을 보여줄떄 true, 숨길때 false
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x 버튼을 눌렀을때 종료
    }

    private Vector<String> addSubjects(){
    //데이터베이서에서 데이터 불러와서 벡터 형식으로 저장해서 반환.
        Vector<String> sbj = null;

        return sbj;
    }
}
