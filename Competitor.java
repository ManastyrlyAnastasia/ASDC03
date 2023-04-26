import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Competitor {
    String lastName;
    String firstName;
    String country;
    int birthYear;
    int tableNumber;
    String category;

    public Competitor(String lastName, String firstName, String country, int birthYear, int tableNumber, String category) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.country = country;
        this.birthYear = birthYear;
        this.tableNumber = tableNumber;
        this.category = category;
    }

    public String toString() {
        return lastName + " " + firstName + ", " + country + ", " + birthYear + ", #" + tableNumber + ", " + category;
    }

    public static class Node {
        Competitor competitor;
        Node left;
        Node right;

        public Node(Competitor competitor) {
            this.competitor = competitor;
            this.left = null;
            this.right = null;
        }
    }

    Node root;

    public Competitor() {
        this.root = null;
    }

    public void insert(Competitor competitor) {
        root = insert(root, competitor);
    }

    private Node insert(Node node, Competitor competitor) {
        if (node == null) {
            node = new Node(competitor);
        } else if (competitor.tableNumber < node.competitor.tableNumber) {
            node.left = insert(node.left, competitor);
        } else if (competitor.tableNumber > node.competitor.tableNumber) {
            node.right = insert(node.right, competitor);
        }
        return node;
    }

    public void delete(int tableNumber) {
        root = delete(root, tableNumber);
    }

    private Node delete(Node node, int tableNumber) {
        if (node == null) {
            return node;
        } else if (tableNumber < node.competitor.tableNumber) {
            node.left = delete(node.left, tableNumber);
        } else if (tableNumber > node.competitor.tableNumber) {
            node.right = delete(node.right, tableNumber);
        } else {
            if (node.left == null && node.right == null) {
                node = null;
            } else if (node.left == null) {
                node = node.right;
            } else if (node.right == null) {
                node = node.left;
            } else {
                Node temp = findMin(node.right);
                node.competitor = temp.competitor;
                node.right = delete(node.right, temp.competitor.tableNumber);
            }
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public void inorder() {
        inorder(root);
    }

    private void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.println(node.competitor.toString());
            inorder(node.right);
        }
    }

    public void preorder() {
        preorder(root);
    }

    private void preorder(Node node) {
        if (node != null) {
            System.out.println(node.competitor.toString());
            preorder(node.left);
            preorder(node.right);
        }
    }

    public void postorder() {
        postorder(root);
    }

    private void postorder(Node node) {
        if (node != null) {
            postorder(node.left);
            postorder(node.right);
            System.out.println(node.competitor.toString());
        }
    }

    public boolean search(int tableNumber) {
        return search(root, tableNumber);
    }
    private boolean search(Node node, int tableNumber) {
        if (node == null) {
            return false;
        } else if (tableNumber == node.competitor.tableNumber) {
            return true;
        } else if (tableNumber < node.competitor.tableNumber) {
            return search(node.left, tableNumber);
        } else {
            return search(node.right, tableNumber);
        }
    }

    public static void main(String[] args) {
        Competitor bst = new Competitor();

        // Чтение файла "competitor.csv" и создание объектов Competitor
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\ACER\\Desktop\\настя\\2 курс\\ASDC\\LL3_ASDC\\src\\competitor.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String lastName = values[0].trim();
                String firstName = values[1].trim();
                String country = values[2].trim();
                int birthYear = Integer.parseInt(values[3].trim());
                int tableNumber = Integer.parseInt(values[4].trim());
                String category = values[5].trim();
                Competitor competitor = new Competitor(lastName, firstName, country, birthYear, tableNumber, category);
                bst.insert(competitor);
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        // Тестирование методов бинарного дерева поиска
        System.out.println("Inorder traversal:");
        bst.inorder();
        System.out.println("Preorder traversal:");
        bst.preorder();
        System.out.println("Postorder traversal:");
        bst.postorder();

        System.out.println("Search for table number 10: " + bst.search(10));
        System.out.println("Search for table number 20: " + bst.search(20));

        System.out.println("Delete competitor with table number 10:");
        bst.delete(10);
        bst.inorder();

        System.out.println("Delete competitor with table number 20:");
        bst.delete(20);
        bst.inorder();
    }}

