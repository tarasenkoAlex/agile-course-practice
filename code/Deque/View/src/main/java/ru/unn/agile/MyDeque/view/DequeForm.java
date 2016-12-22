package ru.unn.agile.MyDeque.view;

import ru.unn.agile.MyDeque.infrastructure.TxtLogger;
import ru.unn.agile.MyDeque.viewmodel.ViewModel;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class DequeForm {

    private JPanel mainPanel;
    private JButton acceptButton;
    private final ViewModel viewmodel;

    private JTextField txtValue;
    private JComboBox<ViewModel.Operations> cbOperationChoice;
    private JLabel lbStatus;
    private JTextField txtResult;

    private final List<String> lstLog;

    private DequeForm(final ViewModel viewmodel) {
        this.viewmodel = viewmodel;
        viewmodel.setLogger(new TxtLogger("./TxtLogger-lab3.log"));
        lstLog = new ArrayList<>();
        backBind();

        getOperationsList();

        acceptButton.addActionListener(actionEvent -> {
            bind();
            viewmodel.accept();
            backBind();
        });

        cbOperationChoice.addActionListener(actionEvent -> {
            bind();
            backBind();
        });

        KeyAdapter keyListener = new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                bind();
                viewmodel.textFieldKey(e.getKeyCode());
                backBind();
            }
        };
        txtValue.addKeyListener(keyListener);
    }

    public static void main(final String[] args) {
        JFrame frame = new JFrame("DequeForm");
        frame.setContentPane(new DequeForm(new ViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void getLog() {
        List<String> log = viewmodel.getLog();
        String[] str = log.toArray(new String[log.size()]);
        lstLog.addAll(Arrays.asList(str));
    }

    private void bind() {
        viewmodel.setValue(txtValue.getText());
        viewmodel.setOperation((ViewModel.Operations) cbOperationChoice.getSelectedItem());
    }

    private void getOperationsList() {
        ViewModel.Operations[] operations = ViewModel.Operations.values();
        cbOperationChoice.setModel(new JComboBox<>(operations).getModel());
    }

    private void backBind() {
        acceptButton.setEnabled(viewmodel.isAcceptButtonEnabled());

        txtResult.setText(viewmodel.getResult());
        lbStatus.setText(viewmodel.getStatus());

        getLog();
    }

}
