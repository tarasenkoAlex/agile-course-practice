package ru.unn.agile.Statistics.view;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.Font;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import ru.unn.agile.Statistics.viewmodel.Operation;
import ru.unn.agile.Statistics.viewmodel.ViewModel;





public class Calculator {
    private static final char
            RANDOM_VARIABLE_LABEL = 'x',
            POSSIBILITY_LABEL = 'P';
    private static final int
            COLUMN_WIDTH = 50;


    private JPanel mainPanel;
    private JTable table;
    private JComboBox<Operation> operationComboBox;
    private JButton computeButton;
    private JTextField momentOrderText;
    private JTextField resultText;
    private JTextField statusText;
    private JTextField deltaText;
    private JSpinner nSpinner;

    private final ViewModel viewModel;

    private final RowNumberRenderer rowNumberRenderer;


    private class TableBinding extends AbstractTableModel {
        @Override
        public int getRowCount() {
            return 2;
        }
        @Override
        public int getColumnCount() {
            return 1 + viewModel.getValues().length;
        }
        @Override
        public Object getValueAt(final int i, final int j) {
            if (j == 0) {
                return i == 0 ? RANDOM_VARIABLE_LABEL : POSSIBILITY_LABEL;
            }
            return (i == 0 ? viewModel.getValues() : viewModel.getPossibilities())[j - 1];
        }
        @Override
        public boolean isCellEditable(final int i, final int j) {
            return j != 0;
        }
        @Override
        public void setValueAt(final Object aValue, final int i, final int j) {
            double value;
            try {
                value = Double.valueOf((String) aValue);
            } catch (NumberFormatException e) {
                return;
            }
            (i == 0 ? viewModel.getValues() : viewModel.getPossibilities())[j - 1] = value;
        }
    }





    private void createUIComponents() {
        table = new JTable() {
            @Override
            public boolean getScrollableTracksViewportWidth() {
                return getPreferredSize().width < getParent().getWidth();
            }
            @Override
            public void editingStopped(final ChangeEvent e) {
                super.editingStopped(e);
                updateStatus();
            }
        };
    }


    private void updateTable() {
        table.getColumnModel().getColumn(0).setCellRenderer(rowNumberRenderer);
        table.getColumnModel().getColumn(0).setMaxWidth(COLUMN_WIDTH);

        for (int i = 0; i < table.getColumnCount(); ++i) {
            table.getColumnModel().getColumn(i).setMinWidth(COLUMN_WIDTH);
        }
        Dimension d = new Dimension(
                COLUMN_WIDTH * table.getColumnCount(),
                table.getPreferredSize().height
        );

        table.setPreferredSize(d);
    }

    private void updateArraysSize() {
        viewModel.setArraysSize((int) nSpinner.getValue()); // instead of bind()
        ((AbstractTableModel) table.getModel()).fireTableStructureChanged();
        updateTable();
        backBindAll();
    }

    public Calculator(final ViewModel viewModel) {
        this.viewModel = viewModel;


        rowNumberRenderer = new RowNumberRenderer(table);
        table.setTableHeader(null);

        table.setModel(new TableBinding());

        updateTable();



        backBindAll();

        loadListOfOperations();


        DocumentListener documentListener = new DocumentListener() {
            @Override
            public void insertUpdate(final DocumentEvent e) {
                updateStatus();
            }
            @Override
            public void removeUpdate(final DocumentEvent e) {
                updateStatus();
            }
            @Override
            public void changedUpdate(final DocumentEvent e) {
                updateStatus();
            }
        };

        KeyAdapter calculateWhenEnterReleasedListener = new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent e) {
                if (e.getKeyChar() == '\n') {
                    calculate();
                }
            }
        };


        nSpinner.addChangeListener(e -> updateArraysSize());
        nSpinner.addKeyListener(calculateWhenEnterReleasedListener);

        deltaText.addActionListener(e -> calculate());
        deltaText.getDocument().addDocumentListener(documentListener);

        table.addKeyListener(calculateWhenEnterReleasedListener);

        operationComboBox.addActionListener(e -> updateAll());
        operationComboBox.addKeyListener(calculateWhenEnterReleasedListener);

        momentOrderText.addActionListener(e -> calculate());
        momentOrderText.getDocument().addDocumentListener(documentListener);

        computeButton.addActionListener(e -> calculate());
        //deltaText.addKeyListener(calculateWhenEnterReleasedListener);
        //momentOrderText.addKeyListener(calculateWhenEnterReleasedListener);
    }

    public void applyTo(final JFrame frame) {
        frame.setContentPane(mainPanel);
    }

    private void loadListOfOperations() {
        Operation[] operations = Operation.values();
        operationComboBox.setModel(new JComboBox<>(operations).getModel());
    }


    private void bind() {
        viewModel.setDelta(deltaText.getText());
        if (viewModel.isMomentOrderEnabled()) {
            viewModel.setMomentOrder(momentOrderText.getText());
        }
        viewModel.setOperation((Operation) operationComboBox.getSelectedItem());
    }


    private void backBindAll() {
        deltaText.setText(viewModel.getDelta());
        backBindResultAndStatus();

        momentOrderText.setEnabled(viewModel.isMomentOrderEnabled());
        if (viewModel.isMomentOrderEnabled()) {
            momentOrderText.setText(viewModel.getMomentOrder());
        }

        nSpinner.setValue(viewModel.getValues().length);
    }


    private void backBindResultAndStatus() {
        resultText.setText(viewModel.getResult());
        statusText.setText(viewModel.getStatus());
        computeButton.setEnabled(viewModel.isCalculateButtonEnabled());
    }


    private void updateAll() {
        bind();
        backBindAll();
    }

    private void updateStatus() {
        bind();
        backBindResultAndStatus();
    }

    private void calculate() {
        bind();
        viewModel.calculate();
        backBindResultAndStatus();
    }

    private final class RowNumberRenderer extends DefaultTableCellRenderer {
        RowNumberRenderer(final JTable table) {
            JTableHeader header = table.getTableHeader();
            setForeground(header.getForeground());
            setBackground(header.getBackground());
            setFont(header.getFont());
            setHorizontalAlignment(JLabel.CENTER);
            setFont(getFont().deriveFont(Font.BOLD));
            setBorder(UIManager.getBorder("TableHeader.cellBorder"));
        }
        @Override
        public Component getTableCellRendererComponent(
                final JTable table, final Object value, final boolean isSelected,
                final boolean hasFocus, final int row, final int column
        ) {
            setText(value.toString());
            return this;
        }
    }
}
