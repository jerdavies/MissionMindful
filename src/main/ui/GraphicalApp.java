package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GraphicalApp extends JFrame implements ActionListener {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    protected JButton b1;
    protected JButton b2;
    protected JButton b3;
    protected JButton b4;
    protected JButton b5;
    protected JButton b6;
    private JFrame frame;

    public GraphicalApp() {
        initializeGraphics();
        initializeInteraction();
    }

    // MODIFIES: this
    // EFFECTS:  listens for button actions
    private void initializeInteraction() {
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where the main menu will display
    private void initializeGraphics() {
        JFrame frame = new JFrame("Mission Mindful");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH,HEIGHT);
        frame.setLayout(new GridLayout(6,1));
        buttonDemo(frame);
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS:  Adds main menu buttons to the frame
    public void buttonDemo(JFrame frame) {
        b1 = new JButton("Button 1");
        b2 = new JButton("Button 2");
        b3 = new JButton("Button 3");
        b4 = new JButton("Button 4");
        b5 = new JButton("Button 5");
        b6 = new JButton("Button 6");

        frame.add(b1);
        frame.add(b2);
        frame.add(b3);
        frame.add(b4);
        frame.add(b5);
        frame.add(b6);
    }

    // !!! ADD SPECIFICAITON
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            System.out.println("b1 was pressed");
        } else if (e.getSource() == b2) {
            System.out.println("b2 was pressed");
        } else if (e.getSource() == b3) {
            System.out.println("b3 was pressed");
        } else if (e.getSource() == b4) {
            System.out.println("b4 was pressed");
        } else if (e.getSource() == b5) {
            System.out.println("b5 was pressed");
        } else if (e.getSource() == b6) {
            System.out.println("b6 was pressed");
        }
    }


    // DON'T DELETE ----------------------------------------------

//    /** Returns an ImageIcon, or null if the path was invalid. */
//    protected static ImageIcon createImageIcon(String path) {
//        java.net.URL imgURL = ButtonDemo.class.getResource(path);
//        if (imgURL != null) {
//            return new ImageIcon(imgURL);
//        } else {
//            System.err.println("Couldn't find file: " + path);
//            return null;
//        }
//    }

    // --------------------------------------------------------------




    // GRAVEYARD STARTS ----------------------------------------------

    // MODIFIES: this
    // EFFECTS:  a helper method which declares and instantiates all tools
//    private void createTools() {
//        JPanel toolArea = new JPanel();
//        toolArea.setLayout(new GridLayout(0,1));
//        toolArea.setSize(new Dimension(0, 0));
//        add(toolArea, BorderLayout.SOUTH);
//
//        MoveTool moveTool = new MoveTool(this, toolArea);
//
//        ResizeTool resizeTool = new ResizeTool(this, toolArea);
//    }
//
//    // EFFECTS: if activeTool != null, then mousePressedInDrawingArea is invoked on activeTool, depends on the
//    //          type of the tool which is currently activeTool
//    private void handleMousePressed(MouseEvent e)  {
//        if (activeTool != null)
//            activeTool.mousePressedInDrawingArea(e);
//        repaint();
//    }
//
//    // EFFECTS: if activeTool != null, then mouseReleasedInDrawingArea is invoked on activeTool, depends on the
//    //          type of the tool which is currently activeTool
//    private void handleMouseReleased(MouseEvent e) {
//        if (activeTool != null)
//            activeTool.mouseReleasedInDrawingArea(e);
//        repaint();
//    }
//
//    // EFFECTS: if activeTool != null, then mouseClickedInDrawingArea is invoked on activeTool, depends on the
//    //          type of the tool which is currently activeTool
//    private void handleMouseClicked(MouseEvent e) {
//        if (activeTool != null)
//            activeTool.mouseClickedInDrawingArea(e);
//        repaint();
//    }


//    private class DrawingMouseListener extends MouseAdapter {
//
//        // EFFECTS: Forward mouse pressed event to the active tool
//        public void mousePressed(MouseEvent e) {
//            handleMousePressed(translateEvent(e));
//        }
//
//        // EFFECTS: Forward mouse released event to the active tool
//        public void mouseReleased(MouseEvent e) {
//            handleMouseReleased(translateEvent(e));
//        }
//
//        // EFFECTS:Forward mouse clicked event to the active tool
//        public void mouseClicked(MouseEvent e) {
//            handleMouseClicked(translateEvent(e));
//        }
//    }

}
