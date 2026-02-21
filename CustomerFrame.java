import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CustomerFrame extends JFrame {
    private Customer customer;
    private List<LiveEvent> events;
    private JTable eventTable, basketTable;
    private DefaultTableModel basketModel;

    public CustomerFrame(Customer customer) {
        this.customer = customer;
        setTitle("Customer Dashboard - " + customer.getName());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        events = FileHandler.loadEvents();

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

        // --- View Events Tab ---
        JPanel viewPanel = new JPanel(new BorderLayout());
        String[] columns = { "ID", "Category", "Name", "Age", "Qty", "Price", "Extra Info" };
        String[][] rowData = new String[events.size()][columns.length];
        for (int i = 0; i < events.size(); i++) {
            LiveEvent e = events.get(i);
            String extra = "";
            if (e instanceof LiveConcert) extra = ((LiveConcert) e).getNumberOfBands() + " bands";
            else if (e instanceof DjSet) extra = ((DjSet) e).getNumberOfDjs() + " DJs";
            else if (e instanceof Theatre) extra = ((Theatre) e).getLanguage();
            else if (e instanceof StandUpComedy) extra = ((StandUpComedy) e).getLanguage();
            else if (e instanceof MagicShow) extra = ((MagicShow) e).getLanguage();
            rowData[i] = new String[]{
                String.valueOf(e.getEventID()),
                e.getEventCategory().toString(),
                e.getEventName(),
                e.getRestriction().toString(),
                String.valueOf(e.getQuantityInStock()),
                String.format("%.2f", e.getTicketPrice()),
                extra
            };
        }
        eventTable = new JTable(new DefaultTableModel(rowData, columns));
        JScrollPane eventScroll = new JScrollPane(eventTable);

        JButton addToBasketBtn = new JButton("Add to Basket");
        addToBasketBtn.addActionListener(e -> {
            int row = eventTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select an event to add.");
                return;
            }
            LiveEvent event = events.get(row);
            int availableQty = event.getQuantityInStock();
            String qtyStr = JOptionPane.showInputDialog(this, "Enter quantity (max " + availableQty + "):", "1");
            if (qtyStr == null) return;
            int qty;
            try {
                qty = Integer.parseInt(qtyStr);
                if (qty < 1 || qty > availableQty) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid quantity.");
                return;
            }
            customer.addToBasket(event, qty);
            updateBasketTable();
            JOptionPane.showMessageDialog(this, "Added to basket!");
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(addToBasketBtn);

        viewPanel.add(eventScroll, BorderLayout.CENTER);
        viewPanel.add(bottomPanel, BorderLayout.SOUTH);

        // --- Basket Tab ---
        JPanel basketPanel = new JPanel(new BorderLayout());
        String[] basketCols = { "Event Name", "Qty", "Price", "Subtotal" };
        basketModel = new DefaultTableModel(basketCols, 0);
        basketTable = new JTable(basketModel);
        JScrollPane basketScroll = new JScrollPane(basketTable);

        JButton payBtn = new JButton("Pay");
        JButton cancelBtn = new JButton("Cancel Basket");
        payBtn.addActionListener(e -> {
            if (customer.getBasket().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Basket is empty.");
                return;
            }
            double total = customer.getBasketTotal();
            String[] options = { "PayPal", "Credit Card" };
            int choice = JOptionPane.showOptionDialog(this, "Total: £" + String.format("%.2f", total) + "\nChoose payment method:",
                    "Payment", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                    PaymentMethod method = null;
                    Address address = customer.getAddress();
            if (choice == 0 || choice == 1) {
                customer.payForBasket(options[choice]);
                updateBasketTable();
                JOptionPane.showMessageDialog(this, "Payment successful via " + options[choice] + "!\nThank you for your purchase.");
            }
        });
        cancelBtn.addActionListener(e -> {
            customer.cancelBasket();
            updateBasketTable();
            JOptionPane.showMessageDialog(this, "Basket cancelled.");
        });
        

        JPanel basketBtnPanel = new JPanel();
        basketBtnPanel.add(payBtn);
        basketBtnPanel.add(cancelBtn);

        basketPanel.add(basketScroll, BorderLayout.CENTER);
        basketPanel.add(basketBtnPanel, BorderLayout.SOUTH);

        tabbedPane.addTab("View Events", viewPanel);
        tabbedPane.addTab("Basket", basketPanel);

        getContentPane().add(tabbedPane, BorderLayout.CENTER);
        setVisible(true);
    }

    private void updateBasketTable() {
        basketModel.setRowCount(0);
        for (Object obj : customer.getBasket()) {
            // If BasketItem is an inner class, cast accordingly
            // Replace 'BasketItem' with the correct class name if different
            BasketItem item = (BasketItem) obj;
            basketModel.addRow(new Object[]{
                item.event.getEventName(),
                item.qty,
                String.format("%.2f", item.event.getTicketPrice()),
                String.format("%.2f", item.qty * item.event.getTicketPrice())
            });
        }
    }
}
	
