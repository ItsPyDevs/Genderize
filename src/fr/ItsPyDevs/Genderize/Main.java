package fr.ItsPyDevs.Genderize;
import java.awt.Font;
import java.awt.Color;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import org.json.*;

public class Main {
	public static void main(String[] args) {
		JFrame main_frame = new JFrame("Genderize by ItsPyDevs");
		JTextField name_input = new JTextField(18);
		JButton gender_button = new JButton("get");
		JLabel result_label = new JLabel("Enter a name");
		
		main_frame.setLayout(null);
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main_frame.setSize(600, 500);
		main_frame.setResizable(false);
		main_frame.getContentPane().setBackground(new Color(28, 29, 34));
		main_frame.add(name_input);
		main_frame.add(gender_button);
		main_frame.add(result_label);
		gender_button.setFocusable(false);
		gender_button.setBackground(new Color(65, 105, 225));
		result_label.setFont(new Font("Ubuntu", Font.PLAIN, 30));
        result_label.setForeground(Color.white);
		
        result_label.setBounds(50, 80, 600, 100);
        gender_button.setBounds(455, 150, 100, 30);
        name_input.setBounds(50, 150, 400, 30);
        
		gender_button.addActionListener(e -> {
			String name = name_input.getText();
			result_label.setText("Loading...");
			if (name_input.getText() == null || name.isEmpty() || name.trim().isEmpty() || name.length() < 2 || name.length() > 50 || name.contains(" ")) {
				result_label.setText("Invalid input");
				return;
			}
			
			try {
				HttpResponse<String> response;
				response = HttpClient.newHttpClient().send(HttpRequest.newBuilder().uri(URI.create("https://api.genderize.io?name=" + name)).build(), HttpResponse.BodyHandlers.ofString());
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
