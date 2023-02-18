package gui.sweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

public class MineSweeper extends JFrame {

    JLabel scoreLabel;
    int score;

    MineSweeper(){
        score = 0;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700,700);
        this.setLayout(new BorderLayout());
        scoreLabel = new JLabel("Score");
        scoreLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        this.add(scoreLabel, BorderLayout.NORTH);
        MineField field = new MineField();
        int i;
        for (i = 0; i < field.labels.size(); i++) {
            int finalI = i;
            field.labels.get(i).addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    if (!field.labels.get(finalI).clicked)
                    field.labels.get(finalI).setBackground(Color.blue);

                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (!field.labels.get(finalI).clicked)
                        if (!field.labels.get(finalI).surprise) {
                            field.labels.get(finalI).setBackground(Color.green);
                            score += 100;
                            scoreLabel.setText(String.valueOf(score));
                        }
                        else {
                            field.labels.get(finalI).setBackground(Color.red);
                            scoreLabel.setText(":(");
                        }
                    field.labels.get(finalI).clicked = true;
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    if (!field.labels.get(finalI).clicked)
                    field.labels.get(finalI).setBackground(Color.yellow);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (!field.labels.get(finalI).clicked)
                    field.labels.get(finalI).setBackground(Color.white);
                }
            });
        }



//        field.setBackground(Color.green);
        this.add(field, BorderLayout.CENTER);

    }

    public static void main(String[] args) {
        MineSweeper msw = new MineSweeper();
        msw.setVisible(true);
    }
}

class MineField extends JPanel {
    final int DIM = 5;
    ArrayList<SingleField> labels = new ArrayList<>();

    MineField() {
        this.setLayout(new GridLayout(DIM,DIM));
        SingleField gridLabel;
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                gridLabel = new SingleField(" ");
                this.add(gridLabel);
                labels.add(gridLabel);
            }
        }
        plantMine();
    }

    void plantMine(){
        Random r = new Random();
        int tag = r.nextInt(0, labels.size());
        labels.get(tag).surprise = true;
    }

}
class SingleField extends JLabel{
    boolean clicked = false;
    boolean surprise = false;

    SingleField(String text) {
        this.setText(text);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        this.setOpaque(true);
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createLineBorder(Color.black, 2));
    }
}