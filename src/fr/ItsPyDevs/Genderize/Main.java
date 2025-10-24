package fr.ItsPyDevs.Genderize;

import java.awt.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.swing.*;
import org.json.*;


public class Main {
	public static void main(String[] args) {
		JFrame main_frame = new JFrame("Genderize by ItsPyDevs");
		Font font = new Font("Ubuntu", Font.PLAIN, 30);
		Color bg_color = new Color(28, 29, 34);
		JTextField name_input = new JTextField(18);
		JButton gender_button = new JButton("get");
		JLabel result_label = new JLabel("Enter a name");
		HttpClient client = HttpClient.newHttpClient();
		
		main_frame.setLayout(null);
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main_frame.setSize(600, 500);
		main_frame.setResizable(false);
		main_frame.getContentPane().setBackground(bg_color);
		main_frame.add(name_input);
		main_frame.add(gender_button);
		main_frame.add(result_label);
		
		gender_button.setFocusable(false);
		
		result_label.setFont(font);
        result_label.setForeground(Color.white);
        
		int bouton_width = 30;
		int bouton_height = 100;
		int input_width = 30;
		int input_height = 400;		
		int result_label_height = 600;
		int result_label_width = 100;

		int bouton_x = 455;
		int bouton_y = 150;
		int input_x = 50;
		int input_y = 150;
		int result_label_x = 50;
		int result_label_y = 80;
		
		
        result_label.setBounds(result_label_x, result_label_y, result_label_height, result_label_width);
        gender_button.setBounds(bouton_x, bouton_y, bouton_height, bouton_width);
        name_input.setBounds(input_x, input_y, input_height, input_width);
        
		gender_button.addActionListener(e -> {
			String name = name_input.getText();
			result_label.setText("Loading...");
			if (name_input.getText() == null || name.isEmpty()  || name.trim().isEmpty()  || name.length() < 2  || name.length() > 50  || name.contains(" ")) {
				result_label.setText("Invalid input");
				return;
			}
			
			try {
				HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.genderize.io?name=" + name)).build();
				HttpResponse<String> response;
				response = client.send(request, HttpResponse.BodyHandlers.ofString());
				JSONObject json_resp = new JSONObject(response.body());
				String gender = json_resp.getString("gender");
				Float probability = json_resp.getFloat("probability");
				probability = probability * 100;
				result_label.setText(name.toUpperCase() + " is " + gender + " (" + probability + "%)");
			} catch (Exception e1) {
				result_label.setText("Error: " + e1.getMessage());
				e1.printStackTrace();
			}
		});
		
		main_frame.setVisible(true);
		
		
	}

}
