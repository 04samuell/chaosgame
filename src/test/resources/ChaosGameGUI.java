import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ChaosGameGUI {
    
    private ChaosGame game;
    private JPanel panel;
    private JFrame frame;

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
        game.offScreenImage = panel.createImage(WIDTH,HEIGHT);
        frame.getContentPane().add(panel);
        createMenuBar();
        frame.setVisible(true);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.CENTER));
        JMenuItem simulateButton = new JMenuItem("Simulate");
        simulateButton.addActionListener(new SimulateListener());
        menuBar.add(simulateButton);
        frame.setJMenuBar(menuBar);
    }

    public Graphics getGraphics() {
        return panel.getGraphics();
    }

    private class SimulateListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            game.beginSimulation(3);
            System.out.println("Simulation started");
        }
    }


}
