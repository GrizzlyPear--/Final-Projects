import java.io.Serializable;


public class Envolope implements Serializable {

	private Object data;
	private Object command;
	private Object player1;
	private Object player2;
	private int turn;
	
	



	public Envolope(){}
	
	public Envolope(Object data){
		
		setData(data);
		
	}
	
	public Envolope(Object data, Object command){
		
		setData(data);
		setCommand(command);
	}
	
	public Envolope(Object data, Object command, Object player1, Object player2){
		
		setData(data);
		setCommand(command);
		setPlayer1(player1);
		setPlayer2(player2);
		turn=0;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
		public Object getCommand() {
		return command;
	}

	public void setCommand(Object command) {
		this.command = command;
	}
	
	public Object getPlayer1() {
		return player1;
	}

	public void setPlayer1(Object player1) {
		this.player1 = player1;
	}

	public Object getPlayer2() {
		return player2;
	}

	public void setPlayer2(Object player2) {
		this.player2 = player2;
	}
	
	public int getTurn() {
		return turn;
	}

	public void addTurn() {
		turn++;
	}
}
