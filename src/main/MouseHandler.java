/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.lang.ref.WeakReference;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MouseHandler implements MouseListener, MouseMotionListener {

    private Integer row = null;

    private final WeakReference<JTable> table;
    private final WeakReference<DefaultTableModel> tableModel;

    public MouseHandler(JTable table, DefaultTableModel model) {
        this.table = new WeakReference<>(table);
        this.tableModel = new WeakReference<>(model);
    }

    @Override
    public void mouseClicked(MouseEvent event) {
    }

    @Override
    public void mousePressed(MouseEvent event) {
        JTable table;
        if ((table = this.table.get()) == null) {
            return;
        }
        int viewRowIndex = table.rowAtPoint(event.getPoint());
        row = table.convertRowIndexToModel(viewRowIndex);
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        row = null;
    }

    @Override
    public void mouseEntered(MouseEvent event) {
    }

    @Override
    public void mouseExited(MouseEvent event) {
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        JTable table;
        DefaultTableModel tableModel;
        if ((table = this.table.get()) == null || (tableModel = this.tableModel.get()) == null) {
            return;
        }

        int viewRowIndex = table.rowAtPoint(event.getPoint());
        int currentRow = table.convertRowIndexToModel(viewRowIndex);

        if (row == null || currentRow == row || currentRow >= tableModel.getRowCount() || row >= tableModel.getRowCount()) {
            return;
        }

        int fromIndex = (currentRow > row) ? row : currentRow;
        int toIndex = (currentRow > row) ? currentRow : row;

        tableModel.moveRow(fromIndex, fromIndex, toIndex);
        row = currentRow;
        table.setRowSelectionInterval(viewRowIndex, viewRowIndex);
    }

    @Override
    public void mouseMoved(MouseEvent event) {
    }

}
