import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import javax.swing.JOptionPane;


public class EchoServer{
	private Socket socket;
	private EchoServer(Socket socket){
		this.socket = socket;
	}
	public static void main(String[] args) {
		int porta = 33333;

		try{
			ServerSocket socket = new ServerSocket(porta);
			System.out.println("Socket Criado");
			while(true){
				System.out.println("aguardado cliente...");
				try(Socket cliente = socket.accept()){
					try(InputStream in = cliente.getInputStream(); OutputStream out = cliente.getOutputStream();){
						byte[] buffer = new byte[10000];
						int n;
						while((n = in.read(buffer)) > 0){
							for (int i = 0;i < n; i++) {
								JOptionPane.showMessageDialog(null, (char)buffer[i]);
							}
							System.out.println(socket.getInetAddress().getLocalHost().getHostAddress());
							Arrays.sort(buffer, 0, n);
							out.write(buffer);
						}
						}catch(IOException ex){
							System.out.println("Erro no acept: "+ex.getMessage());
					}
				}
			}
		}catch(IOException ex){
			System.out.println("Erro no acept: "+ex.getMessage());
		}
	}

}