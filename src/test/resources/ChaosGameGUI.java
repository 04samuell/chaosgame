import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.imageio.ImageIO;

public class ChaosGameGUI {
    
    private ChaosGame game;
    private JPanel panel;
    private JFrame frame;
    private JCheckBoxMenuItem randomPointsBox;
    private JComboBox<String> fractalTypeBox;

    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;

    private static final boolean DEBUGGING = true;
    
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
        loadIconImage();
        
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

        fractalTypeBox = new JComboBox<>();
        fractalTypeBox.addItem("Serpinskis Triangle");
        fractalTypeBox.addItem("Serpinskis Pentagon");
        fractalTypeBox.addItem("Barnsleys Fern");
        JPanel fractalTypePanel = new JPanel();
        fractalTypePanel.add(new JLabel("Fractal"));
        fractalTypePanel.add(fractalTypeBox);
        
        menuBar.add(simulateButton);
        menuBar.add(randomPointsBox);
        menuBar.add(clearButton);
        menuBar.add(fractalTypePanel);
        frame.setJMenuBar(menuBar);
    }

    public Graphics getGraphics() {
        return panel.getGraphics();
    }

    private void loadIconImage() {
        String filepath;
        if(DEBUGGING) {
            filepath = "test/resources/icon.png";
        } else {
            filepath = "icon.png";
        }
        try {
            Image icon = ImageIO.read(ChaosGameGUI.class.getClassLoader().getResourceAsStream(filepath));
            frame.setIconImage(icon);
        } catch (Exception e) {
            System.out.println("Error loading icon image");
        }
    }

    private class SimulateListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            boolean randomSimulation = randomPointsBox.isSelected();
            String fractal = (String) fractalTypeBox.getSelectedItem();
            game.beginSimulation(fractal, randomSimulation);
        }
    }

    private class ClearButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            game.endSimulation();
        }
    }


}
