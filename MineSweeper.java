package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by margarita on 3/30/16.
 */
public class MineSweeper implements ActionListener {

    final int MINE = 0;

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
        createRandomMines();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void createRandomMines() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < counts.length; i++) {
            for (int j = 0; j < counts[0].length; j++) {
                list.add(i * 100 + j);
            }
        }

        //reset counts,pick out 30 mines
        counts = new int[20][20];
        for (int a = 0; a < 30; a++) {
            int choice = (int) (Math.random() * list.size());
            counts[list.get(choice)][list.get(choice) % 100] = MINE;
            list.remove(choice);
        }

        //initialize neighbor counts
        for (int i = 0; i < counts.length; i++) {
            for (int j = 0; j < counts[0].length; j++) {

                int neighborCounts = 0;
                if (counts[i][j] != MINE) {
                    if (i > 0 && j > 0 && counts[i - 1][j - 1] == MINE) {
                        neighborCounts++;
                    }

                    if (j > 0 && counts[i][j - 1] == MINE) {
                        neighborCounts++;
                    }

                    if (i < counts.length - 1 && j < counts[0].length - 1 && counts[i + 1][j + 1] == MINE) {
                        neighborCounts++;
                    }

                    counts[i][j] = neighborCounts;
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
