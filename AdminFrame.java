
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AdminFrame extends JFrame {
	private JTextField idField;
	private JTextField nameField;
	private JTextField feeField;
	private JTextField quantityField;
	private JTextField priceField;
	private JTextField infoField;
	
	//private final Admin admin;
	// private JComboBox<String> categoryComboBox, typeBox, restrictionBox;
	private JComboBox<String> categoryComboBox, typeBox;
	private JComboBox<AgeRestrictionCategory> restrictionBox;
   // @SuppressWarnings("unchecked")
	public AdminFrame(String name) {
		//this.admin = admin;
    	 setTitle("Admin Dashboard - " + name);
    	    setSize(900, 500);
    	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	    setLocationRelativeTo(null);
    	    getContentPane().setLayout(new BorderLayout());

    	    // 1) Create your tabbed pane and panels
    	    JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    	    JPanel viewPanel = new JPanel(new BorderLayout());    // give it a BorderLayout
    	    JPanel addPanel  = new JPanel();

    	    tabbedPane.addTab("View Events", viewPanel);
    	    tabbedPane.addTab("Add Events", addPanel);
    	    addPanel.setLayout(null);
    	    
    	    idField = new JTextField();
    	    idField.setBounds(75, 25, 96, 20);
    	    addPanel.add(idField);
    	    idField.setColumns(10);
    	    
    	    JLabel idLabel = new JLabel("Event ID");
    	    idLabel.setBounds(10, 28, 48, 14);
    	    addPanel.add(idLabel);
    	    
    	 //   JComboBox categoryComboBox = new JComboBox();
    	 //   categoryComboBox = new JComboBox(LiveEventCategory.values());
    	    categoryComboBox = new JComboBox<>(new String[]{ "MUSIC","PERFORMANCE"});
    	    categoryComboBox.setBounds(75, 78, 96, 22);
    	    addPanel.add(categoryComboBox);
    	    
    	    JLabel categoryLabel = new JLabel("Category");
    	    categoryLabel.setBounds(10, 82, 48, 14);
    	    addPanel.add(categoryLabel);
    	    
    	    nameField = new JTextField();
    	    nameField.setBounds(75, 175, 96, 20);
    	    addPanel.add(nameField);
    	    nameField.setColumns(10);
    	    
    	    feeField = new JTextField();
    	    feeField.setBounds(564, 25, 96, 20);
    	    addPanel.add(feeField);
    	    feeField.setColumns(10);
    	    
    	    quantityField = new JTextField();
    	    quantityField.setBounds(564, 79, 96, 20);
    	    addPanel.add(quantityField);
    	    quantityField.setColumns(10);
    	    
    	    priceField = new JTextField();
    	    priceField.setBounds(564, 126, 96, 20);
    	    addPanel.add(priceField);
    	    priceField.setColumns(10);
    	    
    	   restrictionBox = new JComboBox<>(AgeRestrictionCategory.values());
    	   // restrictionBox = new JComboBox<>(new String[]{ "ALL","ADULTS"});
    	    restrictionBox.setBounds(75, 225, 96, 22);
    	    addPanel.add(restrictionBox);
    	    
    	    JLabel lblNewLabel = new JLabel("Event Type");
    	    lblNewLabel.setBounds(10, 129, 55, 14);
    	    addPanel.add(lblNewLabel);
    	    
    	    JLabel lblNewLabel_1 = new JLabel("Event Name");
    	    lblNewLabel_1.setBounds(10, 178, 59, 14);
    	    addPanel.add(lblNewLabel_1);
    	    
    	    JLabel lblNewLabel_2 = new JLabel("Age Restriction");
    	    lblNewLabel_2.setBounds(10, 229, 48, 14);
    	    addPanel.add(lblNewLabel_2);
    	    
    	    JLabel lblNewLabel_3 = new JLabel("Performance Fee");
    	    lblNewLabel_3.setBounds(444, 28, 110, 14);
    	    addPanel.add(lblNewLabel_3);
    	    
    	    JLabel lblNewLabel_4 = new JLabel("Ticket Quantity");
    	    lblNewLabel_4.setBounds(444, 82, 96, 14);
    	    addPanel.add(lblNewLabel_4);
    	    
    	    JLabel lblNewLabel_5 = new JLabel("Price");
    	    lblNewLabel_5.setBounds(444, 129, 48, 14);
    	    addPanel.add(lblNewLabel_5);
    	    
    	    infoField = new JTextField();
    	    infoField.setBounds(564, 175, 96, 20);
    	    addPanel.add(infoField);
    	    infoField.setColumns(10);
    	    
    	    JLabel lblNewLabel_6 = new JLabel("Extra Info ( number of bands/djs or language)");
    	    lblNewLabel_6.setBounds(329, 166, 225, 39);
    	    addPanel.add(lblNewLabel_6);
    	    
    	    JButton addEventButton = new JButton("Add Event");
    	    addEventButton.setBounds(571, 229, 89, 23);
    	    addEventButton.addActionListener(e -> saveEvent());
    	    addPanel.add(addEventButton);
    	    
    	    typeBox = new JComboBox<>(new String[]{ "Live Concert","DJ Set", "Theatre", "Stand-up Comedy", "Magic"});
    	    typeBox.setBounds(75, 125, 96, 22);
    	    addPanel.add(typeBox);

    	    // 2) Load your data and build the JTable
    	    String[] columns = { "ID","Category","Name","Age","Qty","Price","Perf. Fee","Extra Info" };
    	   // List<LiveEvent> events = LoadStock.loadStock("Stock.txt");
           List<LiveEvent> events = FileHandler.loadEvents();
    	    String[][] rowData = new String[events.size()][columns.length];
    	    for (int i = 0; i < events.size(); i++) {
    	        LiveEvent e = events.get(i);
    	        String extra = "";
                if (e instanceof LiveConcert) {
                    extra = ((LiveConcert) e).getNumberOfBands() + " bands";
                } else if (e instanceof DjSet) {
                    extra = ((DjSet) e).getNumberOfDjs() + " DJs";
                } else if (e instanceof Theatre) {
                    extra = ((Theatre) e).getLanguage();
                } else if (e instanceof StandUpComedy) {
                    extra = ((StandUpComedy) e).getLanguage();
                } else if (e instanceof MagicShow) {
                    extra = ((MagicShow) e).getLanguage();
                }
                

    	        rowData[i] = new String[]{
    	          String.valueOf(e.getEventID()),
    	          e.getEventCategory().toString(),
    	          e.getEventName(),
    	          e.getRestriction().toString(),
    	          String.valueOf(e.getQuantityInStock()),
    	          String.format("%.2f", e.getTicketPrice()),
    	          String.format("%.2f", e.getPerformanceFee()),
    	          extra
    	        };
    	    }
    	    JTable table = new JTable(new DefaultTableModel(rowData, columns));
    	    JScrollPane scrollPane = new JScrollPane(table);

    	    // 3) Add the scrollpane *to* the viewPanel
    	    viewPanel.add(scrollPane, BorderLayout.CENTER);
    	    
    	   

    	    // 4) Finally add the tabbed pane to the frame
    	    getContentPane().add(tabbedPane, BorderLayout.CENTER);

    	    setVisible(true);
    	}
	private void saveEvent() {
		 try {
				String idText = idField.getText().trim();
				String name = nameField.getText().trim();
				String category = (String) categoryComboBox.getSelectedItem();
				String type = (String) typeBox.getSelectedItem();
				AgeRestrictionCategory restriction = (AgeRestrictionCategory) restrictionBox.getSelectedItem();
				String quantityText = quantityField.getText().trim();
				String feeText = feeField.getText().trim();
				String priceText = priceField.getText().trim();
				String additional = infoField.getText().trim();

				int id = Integer.parseInt(idText);
				int quantity = Integer.parseInt(quantityText);
				double fee = Double.parseDouble(feeText);
				double price = Double.parseDouble(priceText);
			//	String extra = additional;

				LiveEvent event = EventFactory.createEvent(id, name, restriction, quantity, fee, price, type, additional);

		// Write to file using FileHandler
		boolean success = FileHandler.writeEventToStock(event, type, additional);

        if (success) {
            JOptionPane.showMessageDialog(this, "Event added successfully!");
            // Optionally refresh your table here
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add event.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Invalid input: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

		        // Basic validation
		//         if (idText.isEmpty() || name.isEmpty() || category == null || type == null ||
		//             restriction == null || quantityText.isEmpty() || feeText.isEmpty() ||
		//             priceText.isEmpty() || additional.isEmpty()) {
		//             JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Warning", JOptionPane.WARNING_MESSAGE);
		//             return;
		//         }

		//         int eventID = Integer.parseInt(idText);
		//         int quantity = Integer.parseInt(quantityText);
		//         double fee = Double.parseDouble(feeText);
		//         double price = Double.parseDouble(priceText);

		//         String line = String.format("%d,%s,%s,%s,%s,%d,%.2f,%.2f,%s",
		//                 eventID, category, type, name, restriction, quantity, fee, price, additional);

		//         System.out.println("Writing line: " + line);

		//         try (FileWriter writer = new FileWriter("Stock.txt", true)) {
		//             //writer.write(line + System.lineSeparator());
		//         	writer.write(line + "\n");  // add your event line + newline
		//         	writer.close();
		//         }

		//         JOptionPane.showMessageDialog(this, "Event saved successfully!");
		//         clearForm();

		//     } catch (NumberFormatException e) {
		//         e.printStackTrace();
		//         JOptionPane.showMessageDialog(this, "Please enter valid numbers for ID, quantity, fee, and price.", "Input Error", JOptionPane.ERROR_MESSAGE);
		//     } catch (Exception e) {
		//         e.printStackTrace();
		//         JOptionPane.showMessageDialog(this, "Unexpected error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		//     }
		// }
}

	private void clearForm() {
	    idField.setText("");
	    nameField.setText("");
	    quantityField.setText("");
	    feeField.setText("");
	    priceField.setText("");
	    infoField.setText("");
	    categoryComboBox.setSelectedIndex(0);
	    typeBox.setSelectedIndex(0);
	    restrictionBox.setSelectedIndex(0);
	}
}

