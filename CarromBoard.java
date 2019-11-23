
public class CarromBoard {
private int black;
private int red;

public int getBlack() {
	return black;
}

public int getRed() {
	return red;
}

public CarromBoard() {
	// TODO Auto-generated constructor stub
	this.black = 9;
	this.red = 1;
}

public int strike(){
	if(this.black==0){
		return 0;
	}
	this.black = this.black-1;
	return 1;
}

public int multiStrike(){
	if(this.black==0){
		return 0;
	}
	
	this.black = this.black-2;
	//this.red = 1;
	return 2;
}

public int redStrike(){
	if(this.red==0){
		return 0;
	}
	
	this.red = this.red-1;
	return 3;
}

public int strikerStrike(){
	return -1;
}

public int defuntCoin(){
	if(this.black==0){
		return 0;
	}
	this.black = this.black-1;
	return -2;
}

public int emptyStrike(){
	return 0;
}

public boolean coinCheck(){
	if(this.black==0&&this.red==0){
		return true;
	}
	return false;
}
}
