package org.example;

import org.example.Data.DataBaseUtils;
import org.ini4j.Wini;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GUI {

    public DataBaseUtils dataBaseUtils;
    public GUI() throws IOException {

        dataBaseUtils = new DataBaseUtils();
        JPanel panel = new JPanel();


        JFrame frame = new JFrame();
        JLabel InformationInfoTextChanger = new JLabel("In dieser Sektion kannst du den Info Text der jeweiligen Ansichten ändern");
        JLabel InformationDatabaseChanger = new JLabel("In dieser Sektion kannst du die Datenbank zugänge ändern bzw. bearbeiten. Beachte bitte, dass du nur richtige angaben in die Folder einfügst. Solltest du dich vertippt haben überprüfe deine angaben noch einmal. ");
        JLabel InformationenMitarbeiter = new JLabel("Mitabeiter Ansicht");
        JLabel InformationenStudent = new JLabel("Studenten Ansicht");
        JLabel DatabaseUrl = new JLabel("Link zur Datenbank");
        JLabel Username = new JLabel("Nutzername");
        JLabel Password = new JLabel("Passwort");
        JLabel whitespace = new JLabel("Bestätige deine Aktion");
        JButton InfoTextMitabeiter = new JButton("Info Text Mitabeiter");
        JButton InfoTextStudenten = new JButton("Info Text Studenten");
        JButton BestätigenMitabeiter = new JButton("Bestätigen");
        JButton BestätigenStudenten = new JButton("Bestätigen");
        JTextField InfoTextAreaMitabeiter = new JTextField(dataBaseUtils.getInfoStaff());
        JTextField InfoTextAreaStudenten = new JTextField(dataBaseUtils.getInfoStudent());
        JTextField pDatabaseUrl = new JTextField();
        pDatabaseUrl.setToolTipText("Link zur Datenbank");
        JTextField pUser = new JTextField();
        pUser.setToolTipText("Nutzername");
        JTextField pPassword = new JTextField();
        pPassword.setToolTipText("Passwort");
        JButton ConfirmDatabaseChange = new JButton("Bestätigen");


        JComboBox<String> GenerellComboBox = new JComboBox<>();
        JComboBox<String> DatabaseComboBox = new JComboBox<>();

        DatabaseComboBox.setEditable(false);
        DatabaseComboBox.addItem("Momentane Datenbank");
        DatabaseComboBox.addItem("Datenbank hinzufügen");

        GenerellComboBox.setEditable(false);
        GenerellComboBox.addItem("Info Text");
        GenerellComboBox.addItem("Datenbank");

        GenerellComboBox.addActionListener(event -> {

            JComboBox GComboBox = (JComboBox) event.getSource();

            Object selected = GComboBox.getSelectedItem();
            assert selected != null;
            if (selected.toString().equals("Info Text")) {
                DatabaseUrl.setVisible(false);
                Username.setVisible(false);
                Password.setVisible(false);
                whitespace.setVisible(false);
                ConfirmDatabaseChange.setVisible(false);
                DatabaseComboBox.setVisible(false);
                InformationInfoTextChanger.setVisible(true);
                InformationDatabaseChanger.setVisible(false);
                InfoTextMitabeiter.setVisible(true);
                InfoTextStudenten.setVisible(true);
                pDatabaseUrl.setVisible(false);
                pUser.setVisible(false);
                pPassword.setVisible(false);
                ConfirmDatabaseChange.setVisible(false);

            }
            else if (selected.toString().equals("Datenbank")) {
                InformationenMitarbeiter.setVisible(false);
                InformationenStudent.setVisible(false);
                InformationInfoTextChanger.setVisible(false);
                InformationDatabaseChanger.setVisible(false);
                InfoTextMitabeiter.setVisible(false);
                InfoTextStudenten.setVisible(false);
                pDatabaseUrl.setVisible(false);
                pUser.setVisible(false);
                pPassword.setVisible(false);
                ConfirmDatabaseChange.setVisible(false);
                DatabaseComboBox.setVisible(true);
                DatabaseComboBox.setEditable(false);


                DatabaseComboBox.addActionListener(event1 -> {
                    Wini ini_read;
                    try {
                        ini_read = new Wini(new File("C:\\Users\\Admin\\Desktop\\SpringWebApplication\\src\\main\\resources\\application.properties"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    String pDatabaseUrl_Current = ini_read.get("pDatabaseUrl", "spring.datasource.url");
                    String pUser_Current = ini_read.get("pUser", "spring.datasource.username");
                    String pPassword_Current = ini_read.get("pPassword", "spring.datasource.password");


                    JComboBox DataComboBox = (JComboBox) event1.getSource();

                    Object selected1 = DataComboBox.getSelectedItem();
                    assert selected1 != null;
                    if (selected1.toString().equals("Momentane Datenbank")) {
                        whitespace.setVisible(false);
                        ConfirmDatabaseChange.setVisible(false);
                        InformationDatabaseChanger.setVisible(false);
                        InformationInfoTextChanger.setVisible(false);
                        InfoTextMitabeiter.setVisible(false);
                        InfoTextStudenten.setVisible(false);

                        //Current Database Connection
                        DatabaseUrl.setVisible(true);
                        pDatabaseUrl.setVisible(true);
                        pDatabaseUrl.setText(pDatabaseUrl_Current);
                        pDatabaseUrl.setEditable(false);
                        DatabaseUrl.setVisible(true);
                        pUser.setVisible(true);
                        pUser.setText(pUser_Current);
                        pUser.setEditable(false);
                        Username.setVisible(true);
                        pPassword.setVisible(true);
                        pPassword.setText(pPassword_Current);
                        pPassword.setEditable(false);
                        Password.setVisible(true);

                    } else if (selected1.toString().equals("Datenbank hinzufügen")) {
                        pDatabaseUrl.setVisible(true);
                        pDatabaseUrl.setEditable(true);
                        DatabaseUrl.setVisible(true);
                        pUser.setVisible(true);
                        pUser.setEditable(true);
                        Username.setVisible(true);
                        pPassword.setVisible(true);
                        pPassword.setEditable(true);
                        Password.setVisible(true);
                        InformationDatabaseChanger.setVisible(true);
                        InformationInfoTextChanger.setVisible(false);
                        InfoTextMitabeiter.setVisible(false);
                        InfoTextStudenten.setVisible(false);

                        //write new Database Connection
                        DatabaseUrl.setVisible(true);
                        pDatabaseUrl.setVisible(true);
                        DatabaseUrl.setVisible(true);
                        pUser.setVisible(true);
                        Username.setVisible(true);
                        pPassword.setVisible(true);
                        Password.setVisible(true);
                        whitespace.setVisible(true);
                        ConfirmDatabaseChange.setVisible(true);
                    }
                });

            }});

        ConfirmDatabaseChange.addActionListener(e -> {
            Wini ini_write;
            try {
                ini_write = new Wini(new File("C:\\Users\\Admin\\Desktop\\SpringWebApplication\\src\\main\\resources\\application.properties"));
            } catch (IOException ex) {
                System.out.println("Informationsabfrage von der Datei nicht möglich/Fehlgeschlagen");
                throw new RuntimeException(ex);
            }

            // [pDatabaseUrl]
            ini_write.put("pDatabaseUrl", "spring.datasource.url", pDatabaseUrl.getText());

            // [pUser]
            ini_write.put("pUser", "spring.datasource.username", pUser.getText());

            // [pPassword]
            ini_write.put("pPassword", "spring.datasource.password", pPassword.getText());

            // STORE INI FILE
            try {
                ini_write.store();
                DatabaseUrl.setVisible(false);
                pDatabaseUrl.setVisible(false);
                DatabaseUrl.setVisible(false);
                pUser.setVisible(false);
                Username.setVisible(false);
                pPassword.setVisible(false);
                Password.setVisible(false);
                whitespace.setVisible(false);
                ConfirmDatabaseChange.setVisible(false);
                System.out.println("Datenbank wurde Aktualiesiert");
            } catch (IOException ex) {
                System.out.println("Datenbank erkennt einen fehler beim Speichern der INI Datei");
                throw new RuntimeException(ex);
            }
        });

        InfoTextMitabeiter.addActionListener(e -> {
            GenerellComboBox.setVisible(false);
            InformationenMitarbeiter.setVisible(true);
            InformationenStudent.setVisible(false);
            InfoTextStudenten.setVisible(false);
            InfoTextMitabeiter.setVisible(false);
            InfoTextAreaMitabeiter.setVisible(true);
            BestätigenMitabeiter.setVisible(true);
            InfoTextAreaStudenten.setVisible(false);
            BestätigenStudenten.setVisible(false);
        });

        InfoTextStudenten.addActionListener(e -> {
            GenerellComboBox.setVisible(false);
            InformationenMitarbeiter.setVisible(false);
            InformationenStudent.setVisible(true);
            InfoTextStudenten.setVisible(false);
            InfoTextMitabeiter.setVisible(false);
            InfoTextAreaStudenten.setVisible(true);
            BestätigenStudenten.setVisible(true);
            InfoTextAreaMitabeiter.setVisible(false);
            BestätigenMitabeiter.setVisible(false);
        });

        BestätigenMitabeiter.addActionListener(e -> {
            InformationenMitarbeiter.setVisible(false);
            InformationenStudent.setVisible(false);
            GenerellComboBox.setVisible(true);
            dataBaseUtils.editInfoStaff(InfoTextAreaMitabeiter.getText());
            InfoTextStudenten.setVisible(true);
            InfoTextMitabeiter.setVisible(true);
            InfoTextAreaStudenten.setVisible(false);
            BestätigenStudenten.setVisible(false);
            InfoTextAreaMitabeiter.setVisible(false);
            BestätigenMitabeiter.setVisible(false);
        });

        BestätigenStudenten.addActionListener(e -> {
            InformationenMitarbeiter.setVisible(false);
            InformationenStudent.setVisible(false);
            GenerellComboBox.setVisible(true);
            dataBaseUtils.editInfoStudent(InfoTextAreaStudenten.getText());
            InfoTextStudenten.setVisible(true);
            InfoTextMitabeiter.setVisible(true);
            InfoTextAreaStudenten.setVisible(false);
            BestätigenStudenten.setVisible(false);
            InfoTextAreaStudenten.setVisible(false);
            BestätigenStudenten.setVisible(false);

        });


        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Admin Tool zum Bearbeiten von der WebApplication/Datenbank");
        frame.pack();
        frame.setVisible(true);
        panel.add(GenerellComboBox);
        panel.add(InformationInfoTextChanger);
        panel.add(InformationDatabaseChanger);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(InfoTextMitabeiter);
        panel.add(InfoTextStudenten);
        panel.add(InfoTextAreaMitabeiter);
        panel.add(InfoTextAreaStudenten);
        panel.add(BestätigenMitabeiter);
        panel.add(BestätigenStudenten);
        panel.add(DatabaseUrl);
        panel.add(pDatabaseUrl);
        panel.add(Username);
        panel.add(pUser);
        panel.add(Password);
        panel.add(pPassword);
        panel.add(whitespace);
        panel.add(ConfirmDatabaseChange);
        panel.add(InformationenMitarbeiter);
        panel.add(InformationenStudent);
        panel.add(DatabaseComboBox);
        DatabaseComboBox.setVisible(false);
        InformationenMitarbeiter.setVisible(false);
        InformationenStudent.setVisible(false);
        InformationInfoTextChanger.setVisible(false);
        InformationDatabaseChanger.setVisible(false);
        InfoTextMitabeiter.setVisible(false);
        InfoTextStudenten.setVisible(false);
        InfoTextAreaMitabeiter.setVisible(false);
        InfoTextAreaStudenten.setVisible(false);
        BestätigenMitabeiter.setVisible(false);
        BestätigenStudenten.setVisible(false);
        DatabaseUrl.setVisible(false);
        pDatabaseUrl.setVisible(false);
        Username.setVisible(false);
        pUser.setVisible(false);
        Password.setVisible(false);
        pPassword.setVisible(false);
        whitespace.setVisible(false);
        ConfirmDatabaseChange.setVisible(false);
    }
    public static void main(String[] args) throws IOException {
        new GUI();

    }
}
