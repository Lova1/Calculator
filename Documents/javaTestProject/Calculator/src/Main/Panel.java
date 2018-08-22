package Main;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Panel extends JPanel {

    private Font font = new Font("SanSerif", Font.TYPE1_FONT, 10);
    private Font font_for_screen = new Font("Chalkduster", Font.CENTER_BASELINE, 25);

    private JTextField screen = new JTextField();

    private JButton double_velue = new JButton(",");
    private JButton backspace = new JButton();
    private JButton eqv = new JButton("=");
    private JButton del = new JButton("Delete");
    private JButton cln = new JButton("Clean");
    // coздание операция "*" "%" "+" "-"
    private JButton dev = new JButton("%");
    private JButton mult = new JButton("*");
    private JButton min = new JButton("-");
    private JButton plus = new JButton("+");
    private JButton buttons[] = new JButton[10];
    private JButton operation_buttons[] = { double_velue, dev, mult, min, plus, del, cln, backspace };

    private JLabel label = new JLabel();

    private Boolean flag = false;
    private int length;
    private int number;
    private String store;
    private StringBuilder back;
    private int idel = 8;

    private int max_width = 241, max_height = 330;

    public Panel(){

        setLayout(null);  // Размещение елементов по нашему желанию
        setFocusable(true);
        //grabFocus();

        // настройка кнопки Delete
        del.setBounds(0 * 59, 3 * 58 + 76,64,64);
        del.setFont(font);
        add(del);

        // настройка кнопки Clean
        cln.setBounds(0 * 59 + 59 * 3, 4 * 58 + 76,64,64);
        cln.setFont(font);
        add(cln);

        // настройка кнопки ","
        double_velue.setBounds(2 * 59, 3 * 58 + 76,64,64);
        double_velue.setFont(font);
        add(double_velue);

        // настройка кнопки =
        eqv.setBounds(0 * 59, 4 * 58 + 76,64*3-10,64);
        eqv.setFont(font);
        add(eqv);
/*

        // настройка кнопок "*" "%" "+" "-"
        dev.setBounds(3 * 59, 0 * 72 + 76,64,78);
        dev.setFont(font);
        add(dev);
        mult.setBounds(3 * 59, 1 * 72 + 76,64,78);
        mult.setFont(font);
        add(mult);
        min.setBounds(3 * 59, 2 * 72 + 76,64,78);
        min.setFont(font);
        add(min);
        plus.setBounds(3 * 59, 3 * 72 + 76,64,80);
        plus.setFont(font);
        add(plus);

*/
        // настройка кнопок "*" "%" "+" "-"
        dev.setBounds(3 * 59, 0 * 58 + 76,64,64);
        dev.setFont(font);
        add(dev);
        mult.setBounds(3 * 59, 1 * 58 + 76,64,64);
        mult.setFont(font);
        add(mult);
        min.setBounds(3 * 59, 2 * 58 + 76,64,64);
        min.setFont(font);
        add(min);
        plus.setBounds(3 * 59, 3 * 58 + 76,64,64);
        plus.setFont(font);
        add(plus);

        // настройки кнопка 0
        buttons[0] = new JButton("0");
        buttons[0].setBounds(1 * 59, 3 * 58 + 76, 64, 64);
        add(buttons[0]);

        // настройки кнопок от 1-9
        for (int x=0; x<3; x++)
            for (int y=0; y<3; y++) {
                buttons[y * 3 + x + 1] = new JButton( (y * 3 + x + 1) + ""  );
                buttons[y * 3 + x + 1].setBounds(x * 59, y * 58 + 76, 64, 64);
                // buttons[y * 3 + x + 1].setFont(font);
                add(buttons[y * 3 + x + 1]);
            }

        // располодение екрана вывода
        screen.setBounds(0,0, max_width, 65);
        screen.setEditable(false); // изменять ввов на экране
        add(screen);

        label.setBounds(5,0, max_width,140);
        label.setText("Empty");
        add(label);

        // Реализация на уровене интерфейса
        ActionListener al = (ActionEvent e) -> {
            JButton b = (JButton)e.getSource();
            setFocusable(true);
            grabFocus();

            // Стерание одной кнопки Clean
            length = screen.getText().length();
            number = screen.getText().length()-1;

            if (b == cln) {
                screen.setText(null);  // screen.setText(""); делает тоже самое
                return;
            }
            if (b == del /*|| b == backspace*/)
                flag = true;
            if (length == 0 && flag){
                 screen.setText(null);  // screen.setText(""); делает тоже самое
                 flag = false;
                 return;
            }
            if (length > 0 && flag){
                back = new StringBuilder(screen.getText());
                back.deleteCharAt(number);
                screen.setFont(font_for_screen);
                store = back.toString();
                screen.setText(store);
                flag = false;
                return;
            }
            if (b == eqv){
                back = new StringBuilder(screen.getText());
                new Solve(back);
            }
            screen.setFont(font_for_screen);
            screen.setText(screen.getText() + b.getText());

        };


        for (JButton b : buttons){
            b.addActionListener(al);
        }

        for(JButton b : operation_buttons){
            b.addActionListener(al);
        }

        // Реализация нажатия и отработки кнопок
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char symbol = e.getKeyChar();
                Character csymbol = e.getKeyChar();

                // Реализация нажатия кнопок
                if ( Character.isDigit(symbol) || csymbol.equals('%') || csymbol.equals('*') || csymbol.equals('+') || csymbol.equals('-') ) {
                    screen.setFont(font_for_screen);
                    screen.setText(screen.getText() + symbol);
                }
                if ( csymbol.equals((char)idel) ){
                    // удаление по 1 знаку
                    length = screen.getText().length();
                    number = screen.getText().length()-1;
                    screen.setFont(font_for_screen);
                    if (length > 0 ){
                        back = new StringBuilder(screen.getText());
                        back.deleteCharAt(number);
                        screen.setFont(font_for_screen);
                        store = back.toString();
                        screen.setText(store);
                        flag = false;
                        return;
                    }
                }
            }
        });
    }
}
