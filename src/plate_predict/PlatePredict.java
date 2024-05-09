package plate_predict;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.*;
import org.jpl7.Query;
import org.jpl7.Term;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlatePredict {

    private static JFrame frame;
    private static List<String> userFoods = new ArrayList<>();
    private static String[] allFoods = {"do_you_prefer_dishes_with_melted_cheese","do_you_enjoy_raw_fish_or_seafood","are_you_a_fan_of_sandwiches_with_various_fillings","do_you_like_pasta_dishes_with_different_types_of_sauces","do_you_prefer_light_meals_with_fresh_vegetables","do_you_enjoy_spicy_dishes","are_you_a_meat_lover","do_you_enjoy_noodle_based_dishes","are_you_a_fan_of_grilled_or_pan_seared_meat","do_you_prefer_your_meals_between_two_slices_of_bread","do_you_like_dishes_with_a_blend_of_spices_and_herbs","do_you_enjoy_dishes_with_various_toppings"};
    private static int currentFoodIndex = 0;

    public static void main(String[] args) {
        // Initialize Prolog
        Query q1 = new Query("consult", new Term[]{new org.jpl7.Atom("expertsystem.pl")});
        System.out.println("Prolog consult " + (q1.hasSolution() ? "succeeded." : "failed."));

        javax.swing.SwingUtilities.invokeLater(PlatePredict::createAndShowGUI);
    }

private static void createAndShowGUI() {
    frame = new JFrame("PlatePredict");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    // Set the frame size to match the image size
    frame.setSize(640, 480);
    frame.setResizable(false);

    JPanel panel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            ImageIcon background = new ImageIcon("background.jpg");
            // Draw the image at the specified location
            g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), null);
        }
    };
    panel.setLayout(new BorderLayout()); // Set BorderLayout to allow centering of components
    frame.add(panel);
    layoutComponents(panel);
    frame.setVisible(true);
}


private static void layoutComponents(JPanel panel) {
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    
    
    JLabel nameLabel1 = new JLabel("<html><center><font face='Poppins' size='16' color='#D24545'>PlatePredict</font></center></html>");
   
    
    nameLabel1.setHorizontalAlignment(SwingConstants.CENTER);


    JLabel descriptionLabel = new JLabel("<html><center><font face='Poppins' size='5'>PlatePredict utilizes advanced algorithms to recommend personalized culinary experiences based on individual taste preferences.</font></center></html>");

    JPanel panelMargin = new JPanel(new BorderLayout());
    panelMargin.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 10));

    
    JLabel nameLabel = new JLabel("<html><font face='Poppins' size='5' color='#A94438'>First, Let's start with your name</font></html>");
    panelMargin.add(nameLabel, BorderLayout.CENTER);

    JTextField nameField = new JTextField();
    JButton startButton = new JButton("Let's Choose a Plate!");
    startButton.setFont(new Font("Poppins", Font.BOLD, 16)); // Changing text size
    startButton.setForeground(Color.WHITE); // Set text color to white
    startButton.setBackground(Color.decode("#B80000")); // Change button color to red
    startButton.setPreferredSize(new Dimension(200, 20)); // Set preferred size for button

    
    // Set preferred size for the text field
    nameField.setMaximumSize(new Dimension(500, nameField.getPreferredSize().height));

    panel.add(nameLabel1);
    panel.add(Box.createVerticalStrut(10)); // Adding vertical space
    panel.add(descriptionLabel);
    panel.add(Box.createVerticalStrut(30)); // Adding vertical space
    panel.add(nameLabel);
    panel.add(nameField);
    panel.add(Box.createVerticalStrut(20)); // Adding vertical space
    panel.add(startButton);

    startButton.addActionListener(e -> {
        userFoods.clear(); // Clear previous foods
        currentFoodIndex = 0;
        showNextFood(panel, nameField.getText(), nameField);
        }
    );
}

    private static void showNextFood(JPanel panel, String userName, JTextField nameField) {
    if (currentFoodIndex >= allFoods.length) {
        // Done gathering foods, show predict
        displayPredict(panel, userName);
        return;
    }

    String food = allFoods[currentFoodIndex];
    String food_new=food.replaceAll("_", " ");
    food_new = food_new.substring(0, 1).toUpperCase() + food_new.substring(1);
    JLabel descriptionLabel = new JLabel("<html><center><font face='Poppins' color='#A94438' size='6' >" + nameField.getText() + ", </font><font face='Poppins' size='6' color='A94438'>Help us to predict a plate for you answering following questions.</font></center></html>");
    descriptionLabel.setForeground(Color.WHITE);

    JLabel foodLabel = new JLabel(food_new.concat("?"));
    // Set text color and font style for "Do you have"
    foodLabel.setForeground(Color.BLACK); // Example: change to red
    Font labelFont = foodLabel.getFont();
    foodLabel.setFont(new Font("Poppins", Font.BOLD, 20));

    JButton yesButton = new JButton("Yes");
    yesButton.setFont(new Font("Poppins", Font.BOLD, 18)); // Changing text size
    yesButton.setForeground(Color.WHITE); // Set text color to white
    yesButton.setPreferredSize(new Dimension(200, 20)); // Set preferred size for button

    JButton noButton = new JButton("No ");
    noButton.setFont(new Font("Poppins", Font.BOLD, 18)); // Changing text size
    noButton.setForeground(Color.WHITE); // Set text color to white
    noButton.setPreferredSize(new Dimension(200, 20)); // Set preferred size for button


        yesButton.addActionListener(e -> {
        userFoods.add(food);
        panel.removeAll();
        currentFoodIndex++;
        showNextFood(panel, userName, nameField); // Show next food
    });

    noButton.addActionListener(e -> {
        currentFoodIndex++;
        showNextFood(panel, userName, nameField); // Show next food
    });
    
    // Set preferred size for buttons
    yesButton.setPreferredSize(new Dimension(300, 40)); // Set width to 100 and height to 40
    noButton.setPreferredSize(new Dimension(100, 40)); // Set width to 100 and height to 40

    // Set background colors for buttons
    yesButton.setBackground(Color.decode("#B80000"));
    noButton.setBackground(new Color(202, 162, 172)); // #caa2ac

    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Set layout to vertical BoxLayout
    panel.removeAll();
    panel.add(descriptionLabel);
    panel.add(foodLabel);
    panel.add(Box.createVerticalStrut(20)); // Adding vertical space
    panel.add(yesButton);
    panel.add(Box.createVerticalStrut(20)); // Adding vertical space
    panel.add(noButton);
    panel.revalidate();
    panel.repaint();
}


private static void displayPredict(JPanel panel, String userName) {
    String query = "predict([" + String.join(",", userFoods) + "], Foods).";
    Query q2 = new Query(query);
    Map<String, Term>[] solutions = q2.allSolutions();

    List<String> foods = new ArrayList<>();
    for (Map<String, Term> solution : solutions) {
        Term foodTerm = solution.get("Foods");
        String food = foodTerm.toString();
        foods.add(food);
    }

    StringBuilder foodsText = new StringBuilder();
    for (String food : foods) {
    foodsText.append("<br>").append("<font color='#D24545' size='30'>").append(food).append("</font>");
}

JLabel predictLabel = new JLabel("<html><center><font face='Poppins' color='#000000' size='8'>Based on our algorithm, " + userName + ", you may like:" + foodsText.toString().replace("[","").replace("]","").substring(0, 1).toUpperCase()+foodsText.substring(1) + " </center></html>");
predictLabel.setForeground(Color.WHITE);
panel.removeAll();
panel.add(predictLabel);
panel.revalidate();
panel.repaint();
}

}
