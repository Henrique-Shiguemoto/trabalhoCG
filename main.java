import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.*;
import javax.swing.*;

public class main{
	public static void main(String[] args){
		//Leitura do arquivo input.txt
		String[] linhas = new String[7];
		try{
			BufferedReader leitor = new BufferedReader(new FileReader("input.txt"));	
			for (int i = 0; i < 7; i++) {
				linhas[i] = leitor.readLine();
			}
			leitor.close();
		}catch(IOException excecao){
			excecao.printStackTrace();
		}

		//Coletando a posição da camera
		String[] ponto_de_vista_em_string = linhas[0].split("/")[1].split(",");
		float[] ponto_de_vista = new float[3];
		ponto_de_vista[0] = Float.parseFloat(ponto_de_vista_em_string[0]);
		ponto_de_vista[1] = Float.parseFloat(ponto_de_vista_em_string[1]);
		ponto_de_vista[2] = Float.parseFloat(ponto_de_vista_em_string[2]);

		//Coletando os pontos do plano
		String[] pontos_no_plano_em_string = linhas[1].split("/");
		float[][] pontos_do_plano = new float[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				pontos_do_plano[i][j] = Float.parseFloat(pontos_no_plano_em_string[i + 1].split(",")[j]);
			}
		}

		//Coletando número de vértices
		String numero_de_vertices_em_string = linhas[2].split("/")[1];
		int numero_de_vertices = Integer.parseInt(numero_de_vertices_em_string);

		//Coletando coordenadas homogêneas dos vértices (WCS)
		String[] coordenadas_homogeneas_vertices_em_string = linhas[3].split("/");
		float[][] coordenadas_homogeneas_vertices = new float[4][numero_de_vertices];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < numero_de_vertices; j++) {
				if (i == 3) {
					coordenadas_homogeneas_vertices[i][j] = 1;	
				}else{
					coordenadas_homogeneas_vertices[i][j] = Float.parseFloat(coordenadas_homogeneas_vertices_em_string[j + 1].split(",")[i]);	
				}
			}
		}

		//Coletando o número de superfícies
		String numero_de_superficies_em_string = linhas[4].split("/")[1];
		int numero_de_superficies = Integer.parseInt(numero_de_superficies_em_string);
		
		//Coletando o número de vértices por superfície
		String[] numero_de_vertices_por_superficie_em_string = linhas[5].split("/");
		int[] numero_de_vertices_por_superficie = new int[numero_de_superficies];
		for (int i = 0; i < numero_de_superficies; i++) {
			numero_de_vertices_por_superficie[i] = Integer.parseInt(numero_de_vertices_por_superficie_em_string[i + 1]);
		}

		//Coletando os vértices que estão em cada superfície, 
		//a ordem dos vértices já está de acordo com a regra da mão direita
		String[] vertices_em_cada_superficie_em_string = linhas[6].split("/");
		int[][] vertices_em_cada_superficie = new int[numero_de_superficies][4];
		for (int i = 0; i < numero_de_superficies; i++) {
			for (int j = 0; j < 4; j++) {
				vertices_em_cada_superficie[i][j] = Integer.parseInt(vertices_em_cada_superficie_em_string[i + 1].split(",")[j]);
			}
		}

		//Calculando vetores determinantes do plano
		float[] vetor_U_plano = new float[3];
		float[] vetor_V_plano = new float[3];
		vetor_U_plano[0] = pontos_do_plano[0][0] - pontos_do_plano[1][0];
		vetor_U_plano[1] = pontos_do_plano[0][1] - pontos_do_plano[1][1];
		vetor_U_plano[2] = pontos_do_plano[0][2] - pontos_do_plano[1][2];

		vetor_V_plano[0] = pontos_do_plano[2][0] - pontos_do_plano[1][0];
		vetor_V_plano[1] = pontos_do_plano[2][1] - pontos_do_plano[1][1];
		vetor_V_plano[2] = pontos_do_plano[2][2] - pontos_do_plano[1][2];		

		//Cálculo do vetor normal ao plano
		float[] vetor_normal = new float[3];
		vetor_normal[0] = vetor_U_plano[1]*vetor_V_plano[2] - vetor_U_plano[2]*vetor_V_plano[1];
		vetor_normal[1] = vetor_U_plano[2]*vetor_V_plano[0] - vetor_U_plano[0]*vetor_V_plano[2];
		vetor_normal[2] = vetor_U_plano[0]*vetor_V_plano[1] - vetor_V_plano[1]*vetor_V_plano[0];

		//Cálculo do d0, d1 e d
		float[] ponto_no_plano = new float[3];
		ponto_no_plano[0] = pontos_do_plano[1][0];
		ponto_no_plano[1] = pontos_do_plano[1][1];
		ponto_no_plano[2] = pontos_do_plano[1][2];
		float d0 = ponto_no_plano[0]*vetor_normal[0] + ponto_no_plano[1]*vetor_normal[1] + ponto_no_plano[2]*vetor_normal[2];
		float d1 = ponto_de_vista[0]*vetor_normal[0] + ponto_de_vista[1]*vetor_normal[1] + ponto_de_vista[2]*vetor_normal[2];
		float d = d0 - d1;

		//Cálculo da matriz perspectiva
		float[][] matriz_perspectiva = new float[4][4];
		matriz_perspectiva[0][0] = d + ponto_de_vista[0]*vetor_normal[0];
		matriz_perspectiva[0][1] = ponto_de_vista[0]*vetor_normal[1];
		matriz_perspectiva[0][2] = ponto_de_vista[0]*vetor_normal[2];
		matriz_perspectiva[0][3] = - ponto_de_vista[0]*d0;
		matriz_perspectiva[1][0] = ponto_de_vista[1]*vetor_normal[0];
		matriz_perspectiva[1][1] = d + ponto_de_vista[1]*vetor_normal[1];
		matriz_perspectiva[1][2] = ponto_de_vista[1]*vetor_normal[2];
		matriz_perspectiva[1][3] = - ponto_de_vista[1]*d0;
		matriz_perspectiva[2][0] = ponto_de_vista[2]*vetor_normal[0];
		matriz_perspectiva[2][1] = ponto_de_vista[2]*vetor_normal[1];
		matriz_perspectiva[2][2] = d + ponto_de_vista[2]*vetor_normal[2];
		matriz_perspectiva[2][3] = - ponto_de_vista[2]*d0;
		matriz_perspectiva[3][0] = vetor_normal[0];
		matriz_perspectiva[3][1] = vetor_normal[1];
		matriz_perspectiva[3][2] = vetor_normal[2];
		matriz_perspectiva[3][3] = -d1;

		//Cálculo das coordenadas no plano de projeção (matriz_perspectiva*coordenadas_homogeneas_vertices)
		float[][] coordenadas_vertices_projetadas = new float[4][numero_de_vertices];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < numero_de_vertices; j++) {
				float value = 0;
				for (int k = 0; k < 4; k++) {
					value += matriz_perspectiva[i][k]*coordenadas_homogeneas_vertices[k][j];
				}
				coordenadas_vertices_projetadas[i][j] = value;
			}
		}

		//Dividir as coordenadas de cada vértice pelo valor de W
		for (int i = 0; i < numero_de_vertices; i++) {
			float divisor = coordenadas_vertices_projetadas[3][i]; // Coordenada W do vértice
			for (int j = 0; j < 4; j++) {
				coordenadas_vertices_projetadas[j][i] = coordenadas_vertices_projetadas[j][i] / divisor;
			}
		}

		JFrame frame = new JFrame();
		CGPanel panel = new CGPanel(coordenadas_vertices_projetadas, vertices_em_cada_superficie);
		panel.setPreferredSize(new Dimension(900, 600));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Trabalho de Computacao Grafica. Feito por Gabriel de Melo Osorio e Henrique Shiguemoto Felizardo");
		frame.add(panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}