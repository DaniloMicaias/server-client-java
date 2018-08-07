import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;
import javax.swing.JOptionPane;




public class EchoClient{
	public static void main(String[] args) {
		int porta = 33333;
		String servidor = "127.0.0.1";

		Scanner ent = new Scanner(System.in);

		String c = JOptionPane.showInputDialog(null, "Digite o numero: ");
		byte[] conteudo = c.getBytes();
		try{
			try(Socket socket = new Socket(servidor, porta)){
				OutputStream out = socket.getOutputStream();
				out.write(conteudo);
				System.out.println("Dados transmitidos para servidor:"+ servidor+porta);
				InputStream in = socket.getInputStream();
				in.read(conteudo);
				System.out.append(Arrays.toString(conteudo));
			}
		}catch(UnknownHostException el){
			System.out.println("Host Desconhecido");
		}catch(IOException ex){
			Logger.getLogger(EchoClient.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}