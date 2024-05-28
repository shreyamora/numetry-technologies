package com.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.*;

public class Invoice {
    private static String htmlContent;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame inputFrame = new JFrame("Invoice Input");
        inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inputFrame.setSize(500, 500);
        inputFrame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel customerNameLabel = new JLabel("Customer Name:");
        JTextField customerNameField = new JTextField();
        JLabel contactLabel = new JLabel("Contact:");
        JTextField contactField = new JTextField();
        JLabel addressLabel = new JLabel("Address:");
        JTextArea addressField = new JTextArea(3, 20);
        JLabel numberOfItemsLabel = new JLabel("Number of Items:");
        JTextField numberOfItemsField = new JTextField();
        JButton generateButton = new JButton("Generate Invoice");

        panel.add(customerNameLabel);
        panel.add(customerNameField);
        panel.add(contactLabel);
        panel.add(contactField);
        panel.add(addressLabel);
        panel.add(new JScrollPane(addressField));
        panel.add(numberOfItemsLabel);
        panel.add(numberOfItemsField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(generateButton);

        inputFrame.add(panel, BorderLayout.CENTER);
        inputFrame.add(buttonPanel, BorderLayout.SOUTH);

        generateButton.addActionListener(e -> {
            String customerName = customerNameField.getText();
            String contact = contactField.getText();
            String address = addressField.getText();
            int numberOfItems = Integer.parseInt(numberOfItemsField.getText());

            String[] itemNames = new String[numberOfItems];
            int[] quantities = new int[numberOfItems];
            double[] prices = new double[numberOfItems];

            for (int i = 0; i < numberOfItems; i++) {
                itemNames[i] = JOptionPane.showInputDialog(inputFrame, "Enter name of item " + (i + 1) + ":");
                quantities[i] = Integer.parseInt(JOptionPane.showInputDialog(inputFrame, "Enter quantity of " + itemNames[i] + ":"));
                prices[i] = Double.parseDouble(JOptionPane.showInputDialog(inputFrame, "Enter price of " + itemNames[i] + ":"));
            }

            double gstRate = 0.18; // 18% GST
            htmlContent = generateHtmlInvoice(customerName, contact, address, itemNames, quantities, prices, gstRate);
            displayHtmlInvoice(htmlContent);
        });

        inputFrame.setVisible(true);
    }

    private static String generateHtmlInvoice(String customerName, String contact, String address, String[] itemNames, int[] quantities, double[] prices, double gstRate) {
        StringBuilder html = new StringBuilder();
        html.append("<html><head><style>")
                .append("body { font-family: Arial, sans-serif; margin: 20px; }")
                .append(".invoice-box { max-width: 800px; margin: auto; padding: 30px; border: 1px solid #eee; box-shadow: 0 0 10px rgba(0, 0, 0, 0.15); }")
                .append(".invoice-box h1 { color: #333; }")
                .append(".invoice-box table { width: 100%; line-height: inherit; text-align: left; border-collapse: collapse; }")
                .append(".invoice-box table th { background: #f4f4f4; padding: 10px; border: 1px solid #ddd; }")
                .append(".invoice-box table td { padding: 10px; border: 1px solid #ddd; }")
                .append(".invoice-box .header { display: flex; justify-content: space-between; }")
                .append(".invoice-box .total { font-size: 20px; font-weight: bold; }")
                .append("</style></head><body>")
                .append("<div class='invoice-box'>")
                .append("<div class='header'><div><h1>Invoice</h1><p>Customer: ").append(customerName).append("</p>")
                .append("<p>Contact: ").append(contact).append("</p>")
                .append("<p>Address: ").append(address).append("</p></div>")
                .append("<div><p>Date: ").append(java.time.LocalDate.now()).append("</p></div></div>")
                .append("<table><tr>")
                .append("<th>Item</th><th>Quantity</th><th>Price</th><th>Total</th></tr>");

        double subTotal = 0;
        for (int i = 0; i < itemNames.length; i++) {
            double itemTotal = quantities[i] * prices[i];
            html.append("<tr><td>").append(itemNames[i])
                    .append("</td><td>").append(quantities[i])
                    .append("</td><td>").append(String.format("&#8377;%.2f", prices[i]))
                    .append("</td><td>").append(String.format("&#8377;%.2f", itemTotal))
                    .append("</td></tr>");
            subTotal += itemTotal;
        }

        double gstAmount = subTotal * gstRate;
        double total = subTotal + gstAmount;

        html.append("</table>")
                .append("<p class='total'>Subtotal: &#8377;").append(String.format("%.2f", subTotal)).append("</p>")
                .append("<p class='total'>GST (18%): &#8377;").append(String.format("%.2f", gstAmount)).append("</p>")
                .append("<p class='total'>Total: &#8377;").append(String.format("%.2f", total)).append("</p>")
                .append("</div></body></html>");

        return html.toString();
    }

    private static void displayHtmlInvoice(String htmlContent) {
        JFrame invoiceFrame = new JFrame("Generated Invoice");
        invoiceFrame.setLayout(new BorderLayout());

        JEditorPane editorPane = new JEditorPane("text/html", htmlContent);
        editorPane.setEditable(false);
        invoiceFrame.add(new JScrollPane(editorPane), BorderLayout.CENTER);

        JButton printButton = new JButton("Print Invoice");
        printButton.addActionListener(e -> printInvoice(htmlContent));
        invoiceFrame.add(printButton, BorderLayout.SOUTH);

        invoiceFrame.setSize(800, 600);
        invoiceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        invoiceFrame.setVisible(true);
    }

    private static void printInvoice(String htmlContent) {
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setPrintable(new Printable() {
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                if (pageIndex > 0) {
                    return NO_SUCH_PAGE;
                }
                Graphics2D g2d = (Graphics2D) graphics;
                g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
                JEditorPane pane = new JEditorPane("text/html", htmlContent);
                pane.setSize((int) pageFormat.getImageableWidth(), (int) pageFormat.getImageableHeight());
                pane.print(g2d);
                return PAGE_EXISTS;
            }
        });
        boolean doPrint = printerJob.printDialog();
        if (doPrint) {
            try {
                printerJob.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        }
    }
}
