package fr.ItsPyDevs.Genderize;

import java.awt.*;
import javax.swing.*;

public class Main {
	public static void main(String[] args) {
		JFrame main_frame = new JFrame("Genderize by ItsPyDevs");
		
		main_frame.setLayout(null);
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main_frame.setSize(600, 500);
		main_frame.setResizable(false);
		
		int bouton_width = 30;
		int bouton_height = 100;
		
		int input_width = 30;
		int input_height = 400;
		
		int result_label_height = 600;
		int result_label_width = 100;
		
		int bouton_x = 455;
		int bouton_y = 100;
		
		int input_x = 50;
		int input_y = 100;
		
		int result_label_x = 90;
		int result_label_y = 150;
		
		Font font = new Font("Ubuntu", Font.PLAIN, 30);
		
		
		JTextField name_input = new JTextField(18);
		JButton gender_button = new JButton("get");
		JLabel result_label = new JLabel("Antoine is MALE (98%)");
		
        result_label.setFont(font);
		
		
		
		name_input.setBounds(input_x, input_y, input_height, input_width);
		gender_button.setBounds(bouton_x, bouton_y, bouton_height, bouton_width);
		result_label.setBounds(result_label_x, result_label_y, result_label_height, result_label_width);
		
		main_frame.add(name_input);
		main_frame.add(gender_button);
		main_frame.add(result_label);
		
		gender_button.addActionListener(e -> {
			
		});
		
		main_frame.setVisible(true);
		
		
	}

}
