package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 * Created by margarita on 3/30/16.
 */
public class MineSweeper implements ActionListener {

    final char MINE = 'X';

    JFrame frame = new JFrame("Minesweeper");
    JButton reset = new JButton("Reset");
    JButton[][] buttons = new JButton[20][20];
    int[][] counts = new int[20][20];
    Container grid = new Container();

    public MineSweeper() {
        frame.setSize(1200, 800);
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

        IntStream.range(0, counts.length).forEach(i -> IntStream.range(0, counts[0].length).forEach(j -> list.add(i * 100 + j)));

        //reset counts,pick out 30 mines
        counts = new int[20][20];
        for (int a = 0; a < 30; a++) {
            int choice = (int) (Math.random() * list.size());
            counts[list.get(choice) / 100][list.get(choice) % 100] = MINE;
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

    public void lostGame() {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[0].length; j++) {
                if (buttons[i][j].isEnabled()) {
                    if (counts[i][j] != MINE) {
                        buttons[i][j].setText(counts[i][j] + "");
                        buttons[i][j].setEnabled(false);
                    } else if (counts[i][j] == 0) {
                        buttons[i][j].setText(counts[i][j] + "");
                        buttons[i][j].setEnabled(false);

                        ArrayList<Integer> massive = new ArrayList<>();
                        massive.add(i * 100 + j);
                        clearZeroes(massive);

                    }
                } else {
                    buttons[i][j].setText("X");
                    buttons[i][j].setEnabled(false);
                }
            }
        }
    }


    private void clearZeroes(ArrayList<Integer> toClear) {
        if (toClear.size() == 0) {
            return;
        } else {
            int x = toClear.get(0) / 100;
            int y = toClear.get(0) % 100;

            toClear.remove(0);
            if (counts[x][y] == 0) {

                if (x > 0 && y > 0) {  //up left
                    buttons[x - 1][y - 1].setText(counts[x - 1][y - 1] + "");
                    buttons[x - 1][y - 1].setEnabled(false);
                    if (counts[x - 1][y - 1] == 0) {
                        toClear.add((x - 1) * 100 + (y - 1));
                    }
                }

                if (y > 0) {  //up
                    buttons[x][y - 1].setText(counts[x][y - 1] + "");
                    buttons[x][y - 1].setEnabled(false);
                    if (counts[x][y - 1] == 0) {
                        toClear.add((x) * 100 + (y - 1));
                    }
                }

                if (x < counts.length - 1 && y > 0) {  //up right
                    buttons[x + 1][y - 1].setText(counts[x + 1][y - 1] + "");
                    buttons[x + 1][y - 1].setEnabled(false);
                    if (counts[x + 1][y - 1] == 0) {
                        toClear.add((x + 1) * 100 + (y - 1));
                    }
                }

                if (x > 0) {  // left
                    buttons[x - 1][y].setText(counts[x - 1][y] + "");
                    buttons[x - 1][y].setEnabled(false);
                    if (counts[x - 1][y] == 0) {
                        toClear.add((x - 1) * 100 + (y));
                    }
                }

                if (x < counts.length - 1) {
                    buttons[x + 1][y].setText(counts[x + 1][y] + "");
                    buttons[x + 1][y].setEnabled(false);
                    if (counts[x + 1][y] == 0) {
                        toClear.add((x + 1) * 100 + y);
                    }
                }

                if (x > 0 && y < counts[0].length - 1) {  //down left
                    buttons[x - 1][y + 1].setText(counts[x - 1][y + 1] + "");
                    buttons[x - 1][y + 1].setEnabled(false);
                    if (counts[x - 1][y + 1] == 0) {
                        toClear.add((x - 1) * 100 + (y + 1));
                    }
                }

                if (y < counts[0].length - 1) {  //down
                    buttons[x][y - 1].setText(counts[x][y - 1] + "");
                    buttons[x][y - 1].setEnabled(false);
                    if (counts[x][y - 1] == 0) {
                        toClear.add((x) * 100 + (y - 1));
                    }
                }

                if (x < counts.length - 1 && y < counts[0].length - 1) { //down right
                    buttons[x + 1][y + 1].setText(counts[x + 1][y + 1] + "");
                    buttons[x + 1][y + 1].setEnabled(false);
                    if (counts[x + 1][y + 1] == 0) {
                        toClear.add((x + 1) * 100 + (y + 1));
                    }

                }
            }
            clearZeroes(toClear);
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource().equals(reset)) {
            // TODO: reset the board
        } else {
            for (int x = 0; x < buttons.length; x++) {
                for (int y = 0; y < buttons[0].length; y++) {
                    if (event.getSource().equals(buttons[x][y])) {
                        buttons[x][y].setText(counts[x][y] + " ");
                        buttons[x][y].setEnabled(false);
                    }
                }
            }
        }
    }
}
