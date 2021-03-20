package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

/**
 * Root Menu and base JFrame for graphical user interface for the Mission Mindful app.
 */
public class RootMenu extends JFrame implements ActionListener {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;

    private JFrame frame;
    protected JButton chooseButton;
    protected JButton viewButton;
    protected JButton addButton;
    protected JButton saveButton;
    protected JButton loadButton;
    protected JButton exitButton;
    private List<JButton> rootMenuButtons;

    public RootMenu() {
        initializeGraphics();
        initializeInteraction();
    }

    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where the root menu will display
    private void initializeGraphics() {
        frame = new JFrame("Mission Mindful");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH,HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(6,1));
        addButtonsToMenu();
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS:  listens for button actions
    private void initializeInteraction() {
        for (JButton b : rootMenuButtons) {
            b.addActionListener(this);
        }
    }

    // MODIFIES: this
    // EFFECTS:  Adds root menu buttons to the frame
    public void addButtonsToMenu() {
        chooseButton = new JButton("Choose a mindfulness exercise");
        viewButton = new JButton("View completed exercises");
        addButton = new JButton("Add my own exercise");
        saveButton = new JButton("Save mindfulness program to file");
        loadButton = new JButton("Load mindfulness program from file");
        exitButton = new JButton("Exit");

        makeRootMenuButtonList();

        for (JButton b : rootMenuButtons) {
            frame.add(b);
        }
    }

    // MODIFIES: this
    // EFFECTS:  Remove root menu buttons from the frame
    public void removeButtonsFromRootMenu() {
        for (JButton b : rootMenuButtons) {
            frame.remove(b);
        }
    }

    // MODIFIES: this
    // EFFECTS:  Adds root menu button to form a collection of buttons
    public void makeRootMenuButtonList() {
        rootMenuButtons = new LinkedList<JButton>();
        rootMenuButtons.add(this.chooseButton);
        rootMenuButtons.add(this.viewButton);
        rootMenuButtons.add(this.addButton);
        rootMenuButtons.add(this.saveButton);
        rootMenuButtons.add(this.loadButton);
        rootMenuButtons.add(this.exitButton);
    }

    // EFFECTS: Handles buttonEvent and brings user to next screen based on button clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == chooseButton) {
            System.out.println("b1 was pressed");
            removeButtonsFromRootMenu();
            new ExerciseMenu(this.frame);
        } else if (e.getSource() == viewButton) {
            System.out.println("b2 was pressed");
        } else if (e.getSource() == addButton) {
            System.out.println("b3 was pressed");
        } else if (e.getSource() == saveButton) {
            System.out.println("b4 was pressed");
        } else if (e.getSource() == loadButton) {
            System.out.println("b5 was pressed");
        } else if (e.getSource() == exitButton) {
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
