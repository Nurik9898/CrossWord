package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.IOException;

public class Window extends JFrame{
    private JTextField textField_dictionary;
    private JSpinner spinner_numberOfWords;
    private JPanel mainPanel;
    private JTable table_ofWords;
    private JButton buttonCalculate;
    private JButton clearButton;
    private JButton button_deleteWords;
    private int wordsNumber = 1;
    private DefaultTableModel dtm;
    String path = new File(".").getCanonicalPath() + "\\Dictionary.txt";
    //private String path =   "C:\\Users\\Ильнур\\IdeaProjects\\CrossWord\\src\\com\\company\\Dictionary.txt";
    //private int[] wordsNumbers;
    public Window(String title) throws IOException {
        super(title);
        System.out.println(path);
        this.setBounds(600,150,300,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
            e.fillInStackTrace();
        }

        spinner_numberOfWords.setModel(new SpinnerNumberModel(1,1,20,1));

        dtm = new DefaultTableModel(0,0);
        dtm.setColumnIdentifiers(getWordsNumbersByNum(wordsNumber));
        table_ofWords.setModel(dtm);
        dtm.addRow(getRow(wordsNumber));


        spinner_numberOfWords.addChangeListener(e -> {
            wordsNumber = (int)spinner_numberOfWords.getValue();
            updateTable();
        });

        buttonCalculate.addActionListener(e -> {
            Game game = new Game(path,textField_dictionary.getText(),getWordsLengths());
            game.print(true);
            addRowsInTable(game.getWords());
            button_deleteWords.setEnabled(true);
            buttonCalculate.setEnabled(false);
            spinner_numberOfWords.setEnabled(false);
        });
        clearButton.addActionListener(e -> {
            textField_dictionary.setText("");
            wordsNumber = 1;
            spinner_numberOfWords.setValue(1);
            updateTable();
            spinner_numberOfWords.setEnabled(true);
        });
        button_deleteWords.addActionListener(e -> {
            while (dtm.getRowCount()>1)
                dtm.removeRow(1);
            button_deleteWords.setEnabled(false);
            buttonCalculate.setEnabled(true);
            spinner_numberOfWords.setEnabled(true);
        });
    }



    private void addRowsInTable(String[][] arr) {
        for (String[] strings : arr) {
            dtm.addRow(strings);
        }
    }

    private int[] getWordsLengths(){
        int[] nums = new int[wordsNumber];
        for (int i = 0; i < wordsNumber; i++) {
            nums[i] = Integer.parseInt(table_ofWords.getModel().getValueAt(0,i).toString());
        }
        return nums;
    }

    private String[] getWordsNumbersByNum(int num) {
        String[] words = new String[num];
        for (int i = 0; i < num; i++) {
            words[i] = String.valueOf(i+1);
        }
        return words;
    }

    private Object[] getRow(int num) {
        Object[] obj = new Object[num];
        for (int i = 0; i < num; i++) {
            obj[i] = i + 1;
        }
        return obj;
    }

    /*private Object[] getRow(int[] row) {
        Object[] obj = new Object[row.length];
        for (int i = 0; i < row.length; i++) {
            obj[i] = row[i];
        }
        return obj;
    }*/

    private void updateTable() {
        dtm = new DefaultTableModel(0,0);
        dtm.setColumnIdentifiers(getWordsNumbersByNum(wordsNumber));
        table_ofWords.setModel(dtm);
        dtm.addRow(getRow(wordsNumber));
    }

}
