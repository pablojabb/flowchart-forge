package main;

import static java.awt.Color.white;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class Side extends javax.swing.JPanel {

    private GamePanel gamePanel;
    private boolean isSpeedFast = true; // Initial state: fast

    public Side() {
        initComponents();
        this.setBackground(white);
        this.setDoubleBuffered(true);

        addTableHeader();

        setLabelImage(jLabel1, "/reso/final/start.png");
        addMouseListenerToLabel(jLabel1, "1,2,3,22,1,0", "/reso/final/start.png");

        setLabelImage(jLabel5, "/reso/final/end.png");
        addMouseListenerToLabel(jLabel5, "1,2,3,22,1,0,0", "/reso/final/end.png");

        setLabelImage(jLabel6, "/reso/final/input.png");
        addMouseListenerToLabel(jLabel6, "15,16,15,0", "/reso/final/input.png");

        setLabelImage(jLabel7, "/reso/final/output.png");
        addMouseListenerToLabel(jLabel7, "5,11,12,14,12,11,5,0", "/reso/final/output.png");

        setLabelImage(jLabel8, "/reso/final/box0.png");
        addMouseListenerToLabel(jLabel8, "26", "/reso/final/box0.png");

        setLabelImage(jLabel2, "/reso/final/refine.png");
        addMouseListenerToLabel(jLabel2, "5,6,7,8,9,10,0", "/reso/final/refine.png");

        setLabelImage(jLabel4, "/reso/final/recy.png");
        addMouseListenerToLabel(jLabel4, "1,4,1,0", "/reso/final/recy.png");

//        setLabelImage(jLabel3, "/reso/final/end.png");
//        addMouseListenerToLabel(jLabel3, "2", "/reso/final/end.png");
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

    }

    public void enableLabels(int lvl) {
        jLabel1.setEnabled(true);
        jLabel5.setEnabled(true);
        jLabel6.setEnabled(true);
        jLabel7.setEnabled(true);
        jLabel8.setEnabled(true);
        jLabel2.setEnabled(true);
        jLabel4.setEnabled(true);
        jButton2.setEnabled(true);
    }

    public void disableLabels(int lvl) {

        switch (lvl) {

            case 0 -> {

                jLabel1.setEnabled(false);
                jLabel5.setEnabled(false);
                jLabel6.setEnabled(false);
                jLabel7.setEnabled(false);
                jLabel8.setEnabled(false);
                jLabel2.setEnabled(false);
                jLabel4.setEnabled(false);
                jButton2.setEnabled(false);
                break;
            }

            case 1 -> {

                jLabel6.setEnabled(false);
                jLabel7.setEnabled(false);
                jLabel8.setEnabled(false);
                jLabel2.setEnabled(false);
                jLabel4.setEnabled(false);
                break;
            }
            case 2 -> {

                jLabel1.setEnabled(false);
                jLabel5.setEnabled(false);
                jLabel7.setEnabled(false);
                jLabel8.setEnabled(false);
                jLabel2.setEnabled(false);
                jLabel4.setEnabled(false);
                break;
            }

            case 3 -> {

                jLabel8.setEnabled(false);
                jLabel2.setEnabled(false);
                jLabel4.setEnabled(false);
                break;
            }
            case 4 -> {

                jLabel2.setEnabled(false);
                jLabel4.setEnabled(false);
                jButton2.setEnabled(false);
                break;
            }
            case 5 -> {

                jLabel7.setEnabled(false);
                jLabel8.setEnabled(false);
                jLabel2.setEnabled(false);
                jButton2.setEnabled(false);

                break;
            }
            case 6 -> {

                jLabel8.setEnabled(false);
                jLabel4.setEnabled(false);
                jButton2.setEnabled(false);
                break;
            }
            case 7 -> {
                jButton2.setEnabled(false);
                break;
            }
            case 8 -> {
                jButton2.setEnabled(false);
                break;
            }
        }

    }

    public void setLabelImage(JLabel label, String path) {
        // Load the image from the resource path
        ImageIcon imageIcon = new ImageIcon(getClass().getResource(path));

        if (imageIcon != null && imageIcon.getImageLoadStatus() == MediaTracker.COMPLETE) {
            // Scale the image to fit the label
            Image scaledImage = imageIcon.getImage().getScaledInstance(164, 90, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            label.setIcon(scaledIcon);

        } else {

        }
    }

    public void addTableHeader() {

        DefaultTableModel model;
        String ID = "";
        String selectedImagePath = "";

        model = (DefaultTableModel) jTable1.getModel();
        Object[] newIdentifiers = new Object[]{"ID", "Commands"};
        model.setColumnIdentifiers(newIdentifiers);

// Set the width of the ID column to zero to make it invisible
        jTable1.getColumn("ID").setMinWidth(0);
        jTable1.getColumn("ID").setMaxWidth(0);
        jTable1.getColumn("ID").setWidth(0);

// Set a cell renderer for the Image column
        jTable1.getColumn("Commands").setCellRenderer(new CellRenderer());

// Set up mouse handlers
        MouseHandler mouseHandler = new MouseHandler(jTable1, model);
        jTable1.addMouseListener(mouseHandler);
        jTable1.addMouseMotionListener(mouseHandler);

// Ensure the table fills the viewport height
        jTable1.setFillsViewportHeight(true);

    }

    class CellRenderer implements TableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table,
                Object value,
                boolean isSelected,
                boolean hasFocus,
                int row,
                int column) {

            TableColumn tb = jTable1.getColumn("Commands");
            tb.setMaxWidth(200);
            tb.setMinWidth(200);

            jTable1.setRowHeight(95);

            return (Component) value;
        }

    }

    public static boolean isNotOverLimt(JTable table, int column, int limit, String data) {
        int count = 0;

        for (int row = 0; row < table.getRowCount(); row++) {
            Object cellValue = table.getValueAt(row, column);
            if (cellValue != null && cellValue.equals(data)) {
                count++;
                if (count > limit) {
                    return false;
                }
            }
        }

        return true;
    }

    private void addMouseListenerToLabel(JLabel label, String id, String path) {
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    // Left click action

                    if (id.equalsIgnoreCase("26")) {
                        addLabelToTable("/reso/final/jumpto.png", "25");
                        addLabelToTable(path, id);

                    } else {
                        addLabelToTable(path, id);
                    }

                    gamePanel.requestFocusInWindow();
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    // Right click action

//                    int rem = jTable1.getSelectedRow();
//                    String remId = (String) jTable1.getValueAt(rem, 0);
//                     jTable1.remove(rem);
                    gamePanel.requestFocusInWindow();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // Change cursor to hand when mouse enters the label
                label.setCursor(new Cursor(Cursor.HAND_CURSOR));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Change cursor back to default when mouse exits the label
                label.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                gamePanel.requestFocusInWindow();
            }
        });
    }

    // Method to add label to the table
    public void addLabelToTable(String imagePath, String id) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        ImageIcon imageIcon = new ImageIcon(getClass().getResource(imagePath));
        JLabel label = new JLabel(imageIcon);

        boolean overdalimit = false;

        switch (id) {

            case "1,2,3,22,1,0":

                overdalimit = isNotOverLimt(jTable1, 0, 0, id);

                break;

            case "5,11,12,14,12,11,5,0":

                overdalimit = isNotOverLimt(jTable1, 0, 10, id);

                break;

            case "26":

                overdalimit = isNotOverLimt(jTable1, 0, 0, id);

                break;

            case "5,6,7,8,9,10,0":

                overdalimit = isNotOverLimt(jTable1, 0, 0, id);

                break;

            case "1,4,1,0":

                overdalimit = isNotOverLimt(jTable1, 0, 0, id);

                break;

            case "25":

                overdalimit = isNotOverLimt(jTable1, 0, 0, id);

                break;

            case "15,16,15,0":

                overdalimit = isNotOverLimt(jTable1, 0, 10, id);

                break;

            case "1,2,3,22,1,0,0":

                overdalimit = isNotOverLimt(jTable1, 0, 0, id);

                break;

        }

        if (overdalimit) {

            model.addRow(new Object[]{id, label});
        } else {

            JOptionPane.showMessageDialog(null, "Maximum symbol entry reached .", "Information", JOptionPane.INFORMATION_MESSAGE);
        }

        // Add label to the table as an object
    }

    public static String getColumnDataAsString(JTable table, int columnIndex) {
        // Initialize an empty StringBuilder to construct the result string
        StringBuilder columnData = new StringBuilder();

        // Get the number of rows in the table
        int rowCount = table.getRowCount();

        // Iterate through each row in the specified column
        for (int row = 0; row < rowCount; row++) {
            // Get the value at the specified column and current row
            Object cellValue = table.getValueAt(row, columnIndex);

            // Check if the cell value is not null
            if (cellValue != null) {
                // Append the cell value to the StringBuilder
                columnData.append(cellValue.toString());

                // If it's not the last row, append a comma
                if (row < rowCount - 1) {
                    columnData.append(", ");
                }
            }
        }

        // Convert the StringBuilder to a String and return it
        return columnData.toString();
    }

    public static ArrayList<Integer> getColumnDataAsIntList(String columnData) {
        // Create an ArrayList to hold the integers
        ArrayList<Integer> intList = new ArrayList<>();

        // Split the input string by commas to get individual number strings
        String[] numberStrings = columnData.split(",");

        // Loop through the split strings, parse them to integers, and add to the ArrayList
        for (String numberString : numberStrings) {
            // Trim any leading/trailing whitespace and convert the string to an integer
            int number = Integer.parseInt(numberString.trim());
            // Add the integer to the ArrayList
            intList.add(number);
        }

        // Return the populated ArrayList
        return intList;
    }

    public static boolean isAhead(ArrayList<Integer> list, int J, int K) {
        int indexJ = -1;
        int indexK = -1;

        // Iterate through the list to find the indexes of J and K
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == J && indexJ == -1) {
                indexJ = i;
            }
            if (list.get(i) == K && indexK == -1) {
                indexK = i;
            }
            // If both indices are found, we can stop searching
            if (indexJ != -1 && indexK != -1) {
                break;
            }
        }

        // If either J or K is not found, return false
        if (indexJ == -1 || indexK == -1) {
            return false;
        }

        // Return true if J is ahead of K, otherwise false
        return indexJ < indexK;
    }

    public void tableClear() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
    }

    public void toggleSpeed() {

        if (jTable1.getRowCount() <= 0) {
            // Show a message dialog if there's no data in jTable1
            JOptionPane.showMessageDialog(null, "No data", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Get the data from the first column as a string
            String ans = getColumnDataAsString(jTable1, 0);

            // Convert the column data string to an ArrayList<Integer>
            ArrayList<Integer> columnData = getColumnDataAsIntList(ans);

            boolean ahead = isAhead(columnData, 25, 26);

//            int index25 = columnData.indexOf(25);
//            int index26 = columnData.indexOf(26);
            if (columnData.contains(26) && columnData.contains(26)) {

                if (ahead) {

                    // Call the reRun method with the column data
                    gamePanel.player.reRun(columnData);
                    if (gamePanel.player.commands.size() > 0 && gamePanel.player.index < gamePanel.player.solution.size()) {

                        gamePanel.player.nextLvl = gamePanel.player.commands.equals(gamePanel.player.solution);

                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Wrong placement sequence for your decision symbol.", "Error", JOptionPane.ERROR_MESSAGE);
//                    index25 = columnData.indexOf(25);
//                    index26 = columnData.indexOf(26);
                }

            } else {
                gamePanel.player.reRun(columnData);
                if (gamePanel.player.commands.size() > 0 && gamePanel.player.index < gamePanel.player.solution.size()) {

                    gamePanel.player.nextLvl = gamePanel.player.commands.equals(gamePanel.player.solution);

                }

            }

//           
            // Request focus for the gamePanel
            gamePanel.requestFocusInWindow();
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton2.setText("Speed up");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Play");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Clear");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jLabel1.setDoubleBuffered(true);
        jLabel1.setOpaque(true);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 13, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel3);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "null"
            }
        ));
        jTable1.setColumnSelectionAllowed(true);
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane2.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Flowchart Symbols");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

        toggleSpeed();


    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        // Toggle the speed and update the button text
        if (isSpeedFast) {
            gamePanel.player.speed = 7;
            jButton2.setText("Slow down");
        } else {

            gamePanel.player.speed = 5;
            jButton2.setText("Speed up");
        }
        isSpeedFast = !isSpeedFast; // Toggle the state
        gamePanel.requestFocusInWindow();


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        tableClear();

    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
