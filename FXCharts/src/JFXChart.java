// --module-path C:\javafx-sdk-23\lib --add-modules javafx.controls,javafx.fxml,javafx.swing



import javafx.application.Platform;
import javafx.embed.swing.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
 
public class JFXChart extends javax.swing.JFrame {
    private static JFXPanel chartFxPanel;
    private static SimpleTModel tableModel;

    public JFXChart() {
        initComponents();
        
        tableModel   = new SimpleTModel();
        chartFxPanel = new JFXPanel();
        chartFxPanel.setPreferredSize(new Dimension(600, 400));

        JTable table = new JTable(tableModel);
        table.setGridColor(Color.DARK_GRAY);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i);  
        }
        JScrollPane tablePanel = new JScrollPane(table);
        
        tablePanel.setPreferredSize(new Dimension(600, 100));
        JPanel chartTablePanel = new JPanel();
        chartTablePanel.setLayout(new BorderLayout());
        
        //Split pane that holds both chart and table
        JSplitPane jsplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        jsplitPane.setTopComponent(chartTablePanel);
        jsplitPane.setBottomComponent(tablePanel);
        jsplitPane.setDividerLocation(380);
        chartTablePanel.add(chartFxPanel, BorderLayout.CENTER);   
        
        add(jsplitPane, BorderLayout.CENTER);
        
        // create JavaFX scene
        Platform.runLater(new Runnable() {
            public void run() {
                chartFxPanel.setScene(new Scene(createBarChart()));
            }
        });
    }

    private BarChart createBarChart() {
    CategoryAxis xAxis = new CategoryAxis();
    xAxis.setLabel("Bus");
    
    NumberAxis yAxis = new NumberAxis();
    yAxis.setLabel("People");
    
    final BarChart chart = new BarChart(xAxis, yAxis, 
    		tableModel.getBarChartData());
    
    tableModel.addTableModelListener(new TableModelListener() {
        public void tableChanged(TableModelEvent e) {
            if (e.getType() == TableModelEvent.UPDATE) {
                final int row = e.getFirstRow();
                final int column = e.getColumn();
                final Object value = ((SimpleTModel)e.getSource()).getValueAt(row, column);

                Platform.runLater(new Runnable() {
                    public void run() {
                        XYChart.Series<String, Number> s =
                        (XYChart.Series<String, Number>) chart.getData().get(row);
                        BarChart.Data data = s.getData().get(column);
                        data.setYValue(value);
                    }
                });
            }
        }});
    return chart;
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        setSize(new java.awt.Dimension(603, 542));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFXChart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFXChart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFXChart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFXChart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        //javax.swing.SwingUtilities.invokeLater(doRun);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFXChart().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
