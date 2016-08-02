/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Exceptions.DuplicateMovieException;
import Exceptions.EmptyFieldException;
import Exceptions.InvalidReleaseDateException;
import Model.Model;
import java.awt.GridLayout;
import java.sql.SQLException;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import jdk.nashorn.internal.codegen.CompilerConstants;

/**
 *
 * @author Rozsenich Levente
 */
public class AddMovieDialog {

    public static void display(Model model) throws EmptyFieldException, InvalidReleaseDateException, DuplicateMovieException, NumberFormatException, SQLException {
        JTextField title = new JTextField();
        JTextField directors = new JTextField();
        JTextField actors = new JTextField();
        JTextField release = new JTextField();
        JRadioButton legalTrue = new JRadioButton("Eredeti");
        JRadioButton legalFalse = new JRadioButton("Nem eredeti");
        ButtonGroup radioGroup = new ButtonGroup();

        radioGroup.add(legalTrue);
        radioGroup.add(legalFalse);

        JPanel panel = new JPanel(new GridLayout(0, 1));

        panel.add(new JLabel("Cím:"));
        panel.add(title);
        panel.add(new JLabel("Rendezők:"));
        panel.add(directors);
        panel.add(new JLabel("Szereplők:"));
        panel.add(actors);
        panel.add(new JLabel("Megjelenés éve:"));
        panel.add(release);
        panel.add(new JLabel("Eredeti másolat:"));
        panel.add(legalFalse);
        panel.add(legalTrue);
        legalTrue.setSelected(true);

        int result = JOptionPane.showConfirmDialog(null, panel, "Film hozzáadás",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            boolean isLegal;

            if (legalFalse.isSelected()) {
                isLegal = false;
            } else {
                isLegal = true;
            }

            model.addMovie(title.getText(), directors.getText(), actors.getText(), Integer.parseInt(release.getText()), isLegal);

        }
    }
}
