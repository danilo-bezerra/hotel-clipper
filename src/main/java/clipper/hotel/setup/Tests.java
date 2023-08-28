package clipper.hotel.setup;

import javax.swing.*;

public class Tests {

    private static JTextField jTextId = new JTextField();
    public static void main(String[] args) {
        jTextId.setText("1");

        Integer id = null;
        String idFieldvalue = jTextId.getText().trim();


        try {
            id = Integer.valueOf(idFieldvalue);
        } catch (NumberFormatException e){
            System.out.println("mensagem de erro: " + e.getMessage());
        }


        System.out.println("valor id: " + id);
    }
}
