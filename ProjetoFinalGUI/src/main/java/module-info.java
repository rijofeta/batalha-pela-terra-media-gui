module projeto.projetofinalgui {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.annotation;
    requires javafx.media;


    opens projeto.projetofinalgui to javafx.fxml;
    opens projeto.projetofinalgui.controllers to javafx.fxml;
    exports projeto.projetofinalgui;
    exports projeto.projetofinalgui.controllers;
    exports projeto.projetofinalgui.models.personagens.herois;
    exports projeto.projetofinalgui.models.personagens.bestas;
    exports projeto.projetofinalgui.models.dto;
    exports projeto.projetofinalgui.exercito;
}