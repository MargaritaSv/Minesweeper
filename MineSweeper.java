package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by margarita on 3/30/16.
 */
public class MineSweeper implements ActionListener {
    JFrame frame = new JFrame("Minesweeper");
    JButton reset = new JButton("Reset");
    JButton[][] buttons = new JButton[20][20];
    int[][] counts = new int[20][20];
    Container grid = new Container();

    public MineSweeper() {
        frame.setSize(200, 200);
        frame.setLayout(new BorderLayout());
        frame.add(reset, BorderLayout.NORTH);

        grid.setLayout(new GridLayout(20, 20));
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[0].length; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].addActionListener(this);
                grid.add(buttons[i][j]);
            }
        }
        
        frame.add(grid, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
