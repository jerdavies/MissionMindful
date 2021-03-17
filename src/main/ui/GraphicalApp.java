package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class GraphicalApp extends JFrame implements ActionListener {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    protected JButton b1;
    protected JButton b2;
    protected JButton b3;

    public GraphicalApp() {
        super("Main Menu: Mission Mindful");
        initializeFields();
        initializeGraphics();
        initializeInteraction();
    }

    // MODIFIES: this
    // EFFECTS:  listens for button acctions
    private void initializeInteraction() {
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS:  sets activeTool, currentDrawing to null, and instantiates drawings and tools with ArrayList
    //           this method is called by the DrawingEditor constructor
    private void initializeFields() {
//        activeTool = null;
//        currentDrawing = null;
//        tools = new ArrayList<Tool>();
    }

    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where the main menu will display, and populates the buttons
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        buttonDemo();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    public void buttonDemo() {
//        ImageIcon leftButtonIcon = createImageIcon("images/right.gif");
//        ImageIcon middleButtonIcon = createImageIcon("images/middle.gif");
//        ImageIcon rightButtonIcon = createImageIcon("images/left.gif");

        b1 = new JButton("Button 1");

        b2 = new JButton("Button 2");

        b3 = new JButton("Button 3");

        //Add Components to this container, using the default FlowLayout.
        add(b1);
        add(b2);
        add(b3);
    }

    // !!! ADD SPECIFICAITON
    public void actionPerformed(ActionEvent e) {
        if (b1.getModel().isPressed()) {
            System.out.println("the button is pressed");
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
