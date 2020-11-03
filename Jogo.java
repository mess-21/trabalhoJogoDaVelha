package jogo_da_velha;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Jogo 
{
	//Vars
	public static int j_atual = 1;
	public static int matrizPrincipal[][] = new int[3][3];
	
	public static void main(String[] args) 
	{
		// Criando o Frame
		JFrame frame = new JFrame();
		frame.setBounds(300,300,322,364);
		frame.getContentPane().setBackground(Color.white);
		frame.setLayout(null);
		frame.setVisible(true);
		
		//Criando o Label
		JLabel labelJogador = new JLabel("Turno do Jogador 1");
		labelJogador.setBounds(96, 298, 128, 32);
		frame.add(labelJogador);
		
		// Criando os botões
		JButton btnCasa[][] = new JButton[3][3];
		for(int i = 0; i < btnCasa.length; i++)
		{
			for(int z = 0; z < btnCasa[i].length; z++)
			{	
				int x, y;
				x = i;
				y = z;
				
				btnCasa[x][y] = new JButton("");
				btnCasa[x][y].setBounds (10+(96*z), 10+(96*i), 96, 96);
				btnCasa[x][y].setBackground(Color.white);
				btnCasa[x][y].setFont(new Font("Arial Narrow", Font.BOLD, 50));
				frame.add(btnCasa[x][y]);
				
				//Action Listener
				btnCasa[x][y].addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e)
					{	
						// Marcando o Tabuleiro
						String marca = "";
						if( j_atual == 1 ) marca = "O";
						else marca = "X";
						btnCasa[x][y].setText( marca );
						
						// Alterando a Matriz
						matrizPrincipal[x][y] = j_atual;
						
						// Trocando de Jogador e desabilitando o botão
						j_atual *= -1; 						
						if (j_atual == 1 ) labelJogador.setText("Turno do Jogador 1");
						else if (j_atual == -1 ) labelJogador.setText("Turno do Jogador 2");
						btnCasa[x][y].setEnabled(false);
						
						// Checando o Tabuleiro
						int check = 0;
						
						//Horizontal
						for(int i = 0; i < matrizPrincipal.length; i++)
						{
							for(int z = 0; z < matrizPrincipal[i].length; z++)
							{
								check += matrizPrincipal[i][z];
							}
							
							if(check == 3 || check == -3) break;
							else check = 0;
						}
						
						//Vertical
						if(check == 0)
						{
							for(int i = 0; i < matrizPrincipal[0].length; i++)
							{
								for(int z = 0; z < matrizPrincipal.length; z++)
								{
									check += matrizPrincipal[z][i];
								}
								
								if(check == 3 || check == -3) break;
								else check = 0;
							}		
						}
						
						//Diagonal
						if(check == 0)
						{
							if( (matrizPrincipal[0][0] + matrizPrincipal[1][1] + matrizPrincipal[2][2] == 3) || (matrizPrincipal[0][2] + matrizPrincipal[1][1] + matrizPrincipal[2][0] == 3) )
							check = 3;
							
							if( (matrizPrincipal[0][0] + matrizPrincipal[1][1] + matrizPrincipal[2][2] == -3) || (matrizPrincipal[0][2] + matrizPrincipal[1][1] + matrizPrincipal[2][0] == -3) )
							check = -3;
						}

						//Empate
						if(check == 0)
						{
							for(int ii = 0; ii < matrizPrincipal.length; ii++)
							{
								for(int zz = 0; zz < matrizPrincipal[ii].length; zz++)
								{
									if ( matrizPrincipal[ii][zz] != 0 ) check ++; 
								}
							}
							if(check != 9) check = 0;
						}
						
						
						
						//Game Over
						if(check == 3 || check == -3 || check == 9) 
						{
							if(check == 3) JOptionPane.showMessageDialog(null, "Jogador 1 Ganhou");
							else if (check == -3) JOptionPane.showMessageDialog(null, "Jogador 2 Ganhou");
							else if (check == 9) JOptionPane.showMessageDialog(null, "Empate");
							
							for(int ii = 0; ii < btnCasa.length; ii++)
							{
								for(int zz = 0; zz < btnCasa[ii].length; zz++)
								{
									btnCasa[ii][zz].setText("");
									btnCasa[ii][zz].setEnabled(true);
									matrizPrincipal[ii][zz] = 0;
								}
							}
							
						}
						
					}
				});
			}
		}
	}

}
