import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PetSimGUIComponent extends JFrame implements ActionListener {

    private Pet pet;
    private JButton foodButton, healButton, hugButton;

    private JProgressBar foodBar, healBar, hugBar;
    private final int DECREMENT_AMOUNT = 1;
    private final int TIMER_DELAY = 2000;
    private SoundMusic music;

    public PetSimGUIComponent() {
        super("Pet SIMULATOR");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        music = new SoundMusic("src/sounds/background.wav");
        music.start();

        music.setVolume(0.7f);

        String[] animals = { "Cat", "Dog" };
        String selectedAnimal = (String) JOptionPane.showInputDialog(
                this,
                "Which animil would you like to choose?",
                "Choose Your Pet",
                JOptionPane.QUESTION_MESSAGE,
                null,
                animals,
                animals[0]);

        if (selectedAnimal != null) {
            pet = createPet(selectedAnimal);
            addGUIComponent();
            startAutoDecrease();
            deathPet();
        } else {
            System.exit(0);
        }

        addGUIComponent();

    }

    public static Pet createPet(String animaType) {
        Image petImage = new ImageIcon("src\\images\\" + animaType + "_pet.png").getImage();
        return new Pet(animaType, petImage);
    }

    public void addGUIComponent() {

        JPanel petContainer = new JPanel(new BorderLayout());
        petContainer.setBackground(new Color(152, 255, 117));
        JLabel petLabel = new JLabel(new ImageIcon(pet.getImage()));
        petContainer.add(petLabel, BorderLayout.CENTER);

        petContainer.add(statsPanel(), BorderLayout.NORTH);

        petContainer.add(userAction(), BorderLayout.SOUTH);

        add(petContainer);

    }

    public JPanel userAction() {
        JPanel userAction = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 25));
        userAction.setBackground(new Color(71, 48, 0));
        userAction.setPreferredSize(new Dimension(getWidth(), 100));

        foodButton = new JButton("FEED");
        foodButton.setPreferredSize(new Dimension(150, 50));
        foodButton.setBackground(Color.ORANGE);

        healButton = new JButton("HEAL");
        healButton.setPreferredSize(new Dimension(150, 50));
        healButton.setBackground(Color.RED);
        hugButton = new JButton("HUG");
        hugButton.setPreferredSize(new Dimension(150, 50));
        hugButton.setBackground(Color.GREEN);

        foodButton.addActionListener(this);
        healButton.addActionListener(this);
        hugButton.addActionListener(this);

        userAction.add(foodButton);
        userAction.add(healButton);
        userAction.add(hugButton);
        return userAction;
    }

    public JPanel statsPanel() {
        JPanel statsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 20));
        statsPanel.setBackground(new Color(0, 242, 255));
        statsPanel.setPreferredSize(new Dimension(getWidth(), 100));
        foodBar = new JProgressBar(0, 100);
        foodBar.setValue(100);
        foodBar.setStringPainted(true);
        foodBar.setForeground(Color.ORANGE);
        statsPanel.add(foodBar);
        healBar = new JProgressBar(0, 100);
        healBar.setValue(100);
        healBar.setStringPainted(true);
        healBar.setForeground(Color.red);
        statsPanel.add(healBar);
        hugBar = new JProgressBar(0, 100);
        hugBar.setForeground(Color.GREEN);
        hugBar.setValue(100);
        hugBar.setStringPainted(true);
        statsPanel.add(hugBar);

        return statsPanel;
    }

    private void startAutoDecrease() {
        Timer timer = new Timer(TIMER_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decreaseProgressBar(foodBar);
                decreaseProgressBar(healBar);
                decreaseProgressBar(hugBar);
                deathPet();
            }
        });
        timer.start();
    }

    private void decreaseProgressBar(JProgressBar progressBar) {
        int value = progressBar.getValue();
        if (value > 0) {
            progressBar.setValue(Math.max(value - DECREMENT_AMOUNT, 0));
        }
    }

    public void deathPet() {
        if (healBar.getValue() == 0) {
            JOptionPane.showMessageDialog(this, "Your are Dead");
            System.exit(0);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int foodValue = foodBar.getValue();
        int healValue = healBar.getValue();
        int hugValue = hugBar.getValue();
        if (e.getSource() == foodButton) {
            foodBar.setValue(foodValue + 10);
        } else if (e.getSource() == healButton) {
            healBar.setValue(healValue + 5);
        } else if (e.getSource() == hugButton) {
            hugBar.setValue(hugValue + 10);
        }
    }
}