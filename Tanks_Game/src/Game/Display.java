package Game;

import javax.swing.*;
import java.awt.*;

class Display {
    private JFrame frame;
    private String title;
    private int width, height;
    private Canvas canvas;

    Display(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;

        initDisplay();
    }

    private void initDisplay(){
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false);
        frame.add(canvas);
        frame.pack();
    }

    public JFrame getFrame(){
        return frame;
    }

    public Canvas getCanvas(){
        return canvas;
    }
}
