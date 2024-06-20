import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ChaosGameGUI {
    
    private ChaosGame game;
    private JPanel panel;
    private JFrame frame;
    private JCheckBoxMenuItem randomPointsBox;
    private JComboBox<Integer> numPointsBox;

    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    
    public ChaosGameGUI(ChaosGame game) {
        this.game = game;
        createGUI();
    }

    private void createGUI() {
        frame = new JFrame("Chaos Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH,HEIGHT);
        frame.setResizable(false);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(WIDTH,HEIGHT));;
        panel.setBackground(Color.BLACK);
        frame.getContentPane().add(panel);
        createMenuBar();
        frame.setVisible(true);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.CENTER));

        JMenuItem simulateButton = new JMenuItem("Simulate");
        simulateButton.addActionListener(new SimulateListener());

        randomPointsBox = new JCheckBoxMenuItem("Random Points");
        JMenuItem clearButton = new JMenuItem("Clear");
        clearButton.addActionListener(new ClearButtonListener());

        numPointsBox = new JComboBox<>();
        numPointsBox.addItem(3);
        numPointsBox.addItem(4);
        numPointsBox.addItem(5);
        JPanel numPointsPanel = new JPanel();
        numPointsPanel.add(new JLabel("Points"));
        numPointsPanel.add(numPointsBox);
        
        menuBar.add(simulateButton);
        menuBar.add(randomPointsBox);
        menuBar.add(clearButton);
        menuBar.add(numPointsPanel);
        frame.setJMenuBar(menuBar);
    }

    public Graphics getGraphics() {
        return panel.getGraphics();
    }

    private class SimulateListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            boolean randomSimulation = randomPointsBox.isSelected();
            int numPoints = (int) numPointsBox.getSelectedItem();
            game.beginSimulation(numPoints, randomSimulation);
        }
    }

    private class ClearButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            game.endSimulation();
        }
    }


}
