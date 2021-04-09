package hu.latzkoo.cookbook.controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AboutController {

    private Stage stage;

    @FXML
    private Text about;

    public void init(Stage stage) {
        this.stage = stage;

        about.setText("Készítette: Timári László\n" +
                "Neptun azonosító: FA4ZPW\n\n" +
                "Az alkalmazás célja: Az alkalmazásfejlesztés I. (IBL414-2G) " +
                "tantárgy kötelező programjának megvalósítása\n" +
                "Oktatók: Gajdács Enikő, Dr. Siket István\n\n" +
                "Telefonszám: +36 70 466 7132\n" +
                "E-mail: Timari.Laszlo@stud.u-szeged.hu\n" +
                "LinkedIn: https://www.linkedin.com/in/latzkoo\n" +
                "GitHub: https://github.com/latzkoo/cookbook\n\n" +
                "Budapest, 2021. április 9.");
    }

    private void closeModal() {
        stage.close();
    }

}
