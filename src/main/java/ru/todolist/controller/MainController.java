package ru.todolist.controller;
import org.springframework.stereotype.Service;
import javafx.scene.Node;

@Service
public class MainController implements Controller  {
    private Node view;

    public Node getView() {
        return view;
    }

    public void setView (Node view){
        this.view = view;
    }
}
