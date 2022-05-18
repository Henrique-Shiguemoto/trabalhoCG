import java.awt.*;
import javax.swing.*;

public class CGPanel extends JPanel{
	
	float[][] pontos = new float [2][8];
	int[][] vertices_por_superficie = new int[6][4];
	CGPanel(float[][] pontos1, int[][] ordem_vertices_por_superficie){
		for (int i = 0; i < 8; i++) {
			this.pontos[0][i] = 450*pontos1[0][i] + 300;
			this.pontos[1][i] = (-1)*300*pontos1[1][i] + 325;
			System.out.println("X - Ponto " + i + ": " + this.pontos[0][i]);
			System.out.println("Y - Ponto " + i + ": " + this.pontos[1][i]);
		}
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
			 	this.vertices_por_superficie[i][j] = ordem_vertices_por_superficie[i][j];
			} 
		}
		this.setPreferredSize(new Dimension(900, 600));
	}

	public void paint(Graphics g){
		Graphics2D g2D = (Graphics2D) g;
		
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
				g2D.setStroke(new BasicStroke(5));
				if(j == 3){
					g2D.drawLine((int)pontos[0][vertices_por_superficie[i][j]], (int)pontos[1][vertices_por_superficie[i][j]],
							 (int)pontos[0][vertices_por_superficie[i][0]], (int)pontos[1][vertices_por_superficie[i][0]]);
				}else{
					g2D.drawLine((int)pontos[0][vertices_por_superficie[i][j]], (int)pontos[1][vertices_por_superficie[i][j]],
							 (int)pontos[0][vertices_por_superficie[i][j + 1]], (int)pontos[1][vertices_por_superficie[i][j + 1]]);
				}
			}
		}
	}
}