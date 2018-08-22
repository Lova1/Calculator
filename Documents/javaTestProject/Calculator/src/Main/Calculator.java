package Main;

import javax.swing.*;

public class Calculator {

    private JFrame window;

    public Calculator(){
        window = new JFrame("Calculator");
       // window.setSize(240, 330); // задаем размеры окна
        window.setSize(240, 393);
        window.add(new Panel());
        window.setLocationRelativeTo(null);  // выводит окно на центр екрана
        window.setResizable(false); // нельзя изменять размеры
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // завершает программу при нажатии на закрытие
        window.setVisible(true); // делает видимым окно
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater( () -> { new Calculator(); } );

    }

}
