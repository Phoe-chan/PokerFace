import java.util.ArrayList;

public class Pot {
	private double myValue;
	private double myLastBid;
	//side pots cannot be won by certain players
	private ArrayList eligiblePlayers;
	
	public Pot(ArrayList players) {
		eligiblePlayers = players;
		myValue = 0;
		myLastBid = 0;
	}
	
	public void call(double value) {
		placeMoney(value);
	}

	public void raise(double value) {
		placeMoney(value);
	}
	
	private void placeMoney(double value) {
		myLastBid = value;
		myValue = myValue + value;
	}
	
	public double getLastBid() {
		return myLastBid;
	}
	
	public double getValue() {
		return myValue;
	}
}
