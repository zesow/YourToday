package com.gus.diary.view;

import com.mommoo.flat.layout.linear.constraints.LinearConstraints;
import com.mommoo.flat.layout.linear.constraints.LinearSpace;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.gus.diary.model.Model;

public class Write extends JFrame {
    JPanel jp = new JPanel(); // 패널 초기화

    JTextField subject = new JTextField(20);
    JTextField content = new JTextField(20);
    JButton btn1 = new JButton("제출");
    JButton btn2 = new JButton("뒤로");
    JLabel jl1 = new JLabel("제목");
    JLabel jl2 = new JLabel("내용");

    public Write() throws HeadlessException {
        super("write");
//        jp.setLayout(new LinearLayout(Orientation.));
        jp.add(jl1);
        jp.add(subject,new LinearConstraints().setLinearSpace(LinearSpace.MATCH_PARENT));
        jp.add(jl2);
        jp.add(content,new LinearConstraints().setWeight(4).setLinearSpace(LinearSpace.MATCH_PARENT));
        jp.add(btn1);
        jp.add(btn2);
        add(jp);
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sbj = subject.getText();
                String con = content.getText();
                Model.setData(sbj,con);

                Intro it = new Intro();
            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Intro it = new Intro();
            }
        });
        setSize(400, 300); // 윈도우의 크기 가로x세로
        setVisible(true); // 창을 보여줄떄 true, 숨길때 false
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x 버튼을 눌렀을때 종료
    }
}
