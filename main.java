import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

		//Coletando coordenadas dos vértices
		String[] coordenadas_vertices_em_string = linhas[3].split("/");
		float[][] coordenadas_vertices = new float[numero_de_vertices][3];
		for (int i = 0; i < numero_de_vertices; i++) {
			for (int j = 0; j < 3; j++) {
				coordenadas_vertices[i][j] = Float.parseFloat(coordenadas_vertices_em_string[i + 1].split(",")[j]);
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
	}
}