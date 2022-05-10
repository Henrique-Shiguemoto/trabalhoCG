import java.awt.*;
import javax.swing.*;

public class CGFrame extends JFrame{
	CGFrame(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1200, 700);
		this.setLocationRelativeTo(null);
		this.setTitle("Trabalho de Computacao Grafica. Feito por Gabriel de Melo Osorio e Henrique Shiguemoto Felizardo");
		this.setResizable(false);
		this.setVisible(true);
	}
}