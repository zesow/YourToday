package com.gus.diary.view;

import com.gus.diary.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Intro extends JFrame{
    JPanel jp = new JPanel(); // 패널 초기화

    JButton btn1 = new JButton("1. 글 쓰기"); // 버튼 초기화
    JButton btn2 = new JButton("2. 글 보기"); // 버튼 초기화
    JButton btn3 = new JButton("3. 프로그램 종료"); // 버튼 초기화

    public Intro() throws HeadlessException {
        super("Diary");

        jp.add(btn1);
        jp.add(btn2);
        jp.add(btn3);
        add(jp);

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Write wr = new Write();
            }
        });

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              com.gus.diary.view.List li = new com.gus.diary.view.List();
            }
        });

        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Model.closeConnection();
                System.exit(0);
            }
        });

        setSize(400, 300); // 윈도우의 크기 가로x세로
        setVisible(true); // 창을 보여줄떄 true, 숨길때 false
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x 버튼을 눌렀을때 종료

    }
}
