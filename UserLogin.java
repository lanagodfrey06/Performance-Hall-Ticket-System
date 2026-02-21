
import java.util.List;
import javax.swing.*;
public class UserLogin extends JFrame{
	private JComboBox<String> usernameBox;
	//List<User> users = new ArrayList<>();
	//List<User> users = new ArrayList<>();
    private List<User> users;

	    public UserLogin() {
	        setTitle("User Login");
	        setSize(500, 400);
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);
	        getContentPane().setLayout(null);
	        
	       // JComboBox usernameBox = new JComboBox();
	        usernameBox = new JComboBox<>();
	        usernameBox.setBounds(140, 47, 180, 47);
	        getContentPane().add(usernameBox);
	        
	        //loadUsersFromFile();
            users = FileHandler.loadUsers();
            for (User user : users) {
                usernameBox.addItem(user.getUsername());
        }

	        
	        JLabel userLabel = new JLabel("Choose a Username");
	        userLabel.setBounds(186, 11, 116, 24);
	        getContentPane().add(userLabel);
	        
	        JButton selectButton = new JButton("Select");
	        selectButton.setBounds(190, 117, 89, 23);
	        getContentPane().add(selectButton);
	        
			/*
			 * selectButton.addActionListener(e -> { String selectedUsername = (String)
			 * usernameBox.getSelectedItem(); if (selectedUsername != null) { for (User user
			 * : users) { if (user.getUsername().equals(selectedUsername)) {
			 * JOptionPane.showMessageDialog(this, user.toString(), "User Info",
			 * JOptionPane.INFORMATION_MESSAGE); break; } } } });
			 */

	        selectButton.addActionListener(e -> handleLogin());
	        setVisible(true);
	    }

	    // private void loadUsersFromFile() {
	    	
	    //     File inputFile = new File("UserAccounts.txt");

	    //     try (Scanner scanner = new Scanner(inputFile)) {
	    //         while (scanner.hasNextLine()) {
	    //             String[] details = scanner.nextLine().split(",");
	    //             if (details.length == 7) {
	    //             	String role = details[6].trim().toLowerCase();
	    //                 User user = null;

	    //                 String id = details[0].trim();
	    //                 String username = details[1].trim();
	    //                 String name = details[2].trim();
	    //                 int houseNumber = Integer.parseInt(details[3].trim());
	    //                 String postcode = details[4].trim();
	    //                 String city = details[5].trim();

	    //                 if (role.equals("admin")) {
	    //                     user = new Admin(id, username, name, houseNumber, postcode, city);
	    //                 } else if (role.equals("customer")) {
	    //                     user = new Customer(id, username, name, houseNumber, postcode, city);
	    //                 }
	                    
	    //                 if (user != null) {
	    //                     users.add(user);
	    //                     usernameBox.addItem(user.getUsername());
	    //                 }
	    //             }
	    //         }

		// 		/*
		// 		 * StringBuilder sb = new StringBuilder(); for (User user : users) {
		// 		 * sb.append(user.toString()).append("\n"); }
		// 		 */

	    //     } catch (IOException e) {
	    //         JOptionPane.showMessageDialog(this, "Error reading file", "Error", JOptionPane.ERROR_MESSAGE);
	    //         e.printStackTrace();
	    //     }
	    // }
	    private void handleLogin() {
	        String chosenUsername = (String) usernameBox.getSelectedItem();
	        if (chosenUsername == null) return;

	        // Find user from list
	        for (User user : users) {
	        	if (user.getUsername().equals(chosenUsername)) {
	                dispose(); // close login window

					if (user instanceof Admin) {
                    new AdminFrame(user.getName());
                } else if (user instanceof Customer) {
                    // Pass user info to CustomerFrame if needed
                    new CustomerFrame((Customer) user);
                } else {
                    JOptionPane.showMessageDialog(this, "Unknown role type.");
                }
	                break;
	            }
	        }
	            
	        }

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(UserLogin::new);
	    }
	}