package MicroProject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Placeholder for Product class
class Product {
    private int id;
    private String name;
    private double price;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return id + ": " + name + " - ₹" + price;
    }
} // Fix: Close the Product class

// Placeholder for ProductCatalog class
class ProductCatalog {
    private List<Product> products;

    public ProductCatalog() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product getProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
} // Fix: Close the ProductCatalog class

// Placeholder for ShoppingCart class
class ShoppingCart {
    private List<Product> cart;

    public ShoppingCart() {
        cart = new ArrayList<>();
    }

    public void addProduct(Product product) {
        cart.add(product);
    }

    public List<Product> getCart() {
        return cart;
    }

    public boolean removeProduct(int productId) {
        for (Product product : cart) {
            if (product.getId() == productId) {
                cart.remove(product);
                return true;
            }
        }
        return false;
    }

    public void clearCart() {
        cart.clear();
    }
}

public class ECommerceGUI extends JFrame {
    private ProductCatalog catalog;
    private ShoppingCart cart;
    private JTextArea displayArea;

    public ECommerceGUI() {
        catalog = new ProductCatalog();
        cart = new ShoppingCart();

        // Add sample products
        catalog.addProduct(new Product(1, "Laptop", 75000));
        catalog.addProduct(new Product(2, "Smartphone", 25000));
        catalog.addProduct(new Product(3, "Headphones", 2000));

        // Setup GUI
        setTitle("Simple E-commerce System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Display area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4));

        JButton viewProductsButton = new JButton("View Products");
        JButton removeFromCartButton = new JButton("Remove from Cart");
        removeFromCartButton.addActionListener(e -> removeFromCart());
        JButton addToCartButton = new JButton("Add to Cart");
        JButton viewCartButton = new JButton("View Cart");
        JButton checkoutButton = new JButton("Checkout");

        buttonPanel.add(viewProductsButton);
        buttonPanel.add(addToCartButton);
        buttonPanel.add(removeFromCartButton);
        buttonPanel.add(viewCartButton);
        buttonPanel.add(checkoutButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Button actions
        viewProductsButton.addActionListener(e -> displayProducts());
        addToCartButton.addActionListener(e -> addToCart());
        viewCartButton.addActionListener(e -> displayCart());
        checkoutButton.addActionListener(e -> checkout());
    }

    private void displayProducts() {
        displayArea.setText("Available Products:\n");
        for (Product product : catalog.getProducts()) {
            displayArea.append(product + "\n");
        }
    }

    private void addToCart() {
        String input = JOptionPane.showInputDialog(this, "Enter Product ID to add:");
        if (input != null) {
            try {
                int productId = Integer.parseInt(input);
                Product product = catalog.getProductById(productId);
                if (product != null) {
                    cart.addProduct(product);
                    JOptionPane.showMessageDialog(this, "Product added to cart.");
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Product ID.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number.");
            }
        }
    }

    private void displayCart() {
        displayArea.setText("Your Cart:\n");
        for (Product product : cart.getCart()) {
            displayArea.append(product + "\n");
        }
        if (cart.getCart().isEmpty()) {
            displayArea.append("Your cart is empty.\n");
        }
    }

    private void removeFromCart() {
        String input = JOptionPane.showInputDialog(this, "Enter Product ID to remove:");
        if (input != null) {
            try {
                int productId = Integer.parseInt(input);
                if (cart.removeProduct(productId)) {
                    JOptionPane.showMessageDialog(this, "Product removed from cart.");
                } else {
                    JOptionPane.showMessageDialog(this, "Product not found in cart.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number.");
            }
        }
        displayArea.setText("Your Cart:\n");
        for (Product product : cart.getCart()) {
            displayArea.append(product + "\n");
        }
        if (cart.getCart().isEmpty()) {
            displayArea.append("Your cart is empty.\n");
        }
    }

    private void checkout() {
        if (cart.getCart().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Your cart is empty. Add items to checkout.");
        } else {
            double total = 0;
            StringBuilder summary = new StringBuilder("Checkout Summary:\n");
            for (Product product : cart.getCart()) {
                summary.append(product).append("\n");
                total += product.getPrice();
            }
            summary.append("Total Amount: ").append(total).append("\n");
            JOptionPane.showMessageDialog(this, summary.toString());
            cart.clearCart();
            displayArea.setText("Thank you for your purchase!\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ECommerceGUI gui = new ECommerceGUI();
            gui.setVisible(true);
        });
    }
}
