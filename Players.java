import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class Players {
private int player1_score = 0;
private int player2_score = 0;
private List<Map<String,Object>> player1_History;
private List<Map<String,Object>> player2_History;

Players(){
	this.player1_score = 0;
	this.player2_score = 0;
	
	this.player1_History = new ArrayList<>();
	this.player2_History = new ArrayList<>();
	
}

public boolean getPlayerScore(){
	if(this.player1_score >= 5 || this.player2_score >= 5){
		return true;
	}
		return false;
}

public void setPlayerScore(int player, int score){
	if(player==1){
		this.player1_score = this.player1_score+score;
		if(this.player1_score<0){
			this.player1_score = 0;
		}
	}
	else if(player==2){
		this.player2_score = this.player2_score+score;
		if(this.player2_score<0)
			this.player2_score = 0;
	}
	
}


public void setPlayerMove(int player, String outcome){
	if(player==1){
		Map<String,Object> history = new HashMap<String,Object>();
		history.put("outcome", outcome);
		history.put("score", this.player1_score);
		this.player1_History.add(history);
	}
	else{
		Map<String,Object> history = new HashMap<String,Object>();
		history.put("outcome", outcome);
		history.put("score", this.player2_score);
		this.player2_History.add(history);
	}
}

public Map<String,Object> gameResult(){
	Map<String,Object> result = new HashMap<String,Object>();
	if(this.player1_score > this.player2_score && this.player1_score - this.player2_score >= 3){
		result.put("result", "Player1");
		result.put("player1score", this.player1_score);
		result.put("player2score", this.player2_score);
		return result;
	}
	else if(this.player1_score < this.player2_score && this.player2_score - this.player1_score >= 3){
		result.put("result", "Player2");
		result.put("player1score", this.player1_score);
		result.put("player2score", this.player2_score);
		return result;
	}

	result.put("result", "Draw");
	result.put("player1score", this.player1_score);
	result.put("player2score", this.player2_score);
	return result;
}

public void emptyMove(int player){
	List<Map<String,Object>> moves;
	if (player == 1)
        moves = this.player1_History;
    else
        moves = this.player2_History;
	
	
int count=0,j=0;
ListIterator<Map<String,Object>> li = moves.listIterator(moves.size());
while(li.hasPrevious()){
	if(j>3){
		break;
	}
	Map<String,Object> mh = li.previous();
	if(mh.get("outcome").toString().equalsIgnoreCase("Empty")){
		count = count+1;
	}
	j = j+1;
}
if(count>=3){
	this.setPlayerScore(player, -1);
}
}

public void foulCount(int player){
	List<Map<String,Object>> moves;
	if (player == 1)
        moves = this.player1_History;
    else
        moves = this.player2_History;
	
	int count=0,j=0;
	ListIterator<Map<String,Object>> li = moves.listIterator(moves.size());
	while(li.hasPrevious()){
		if(j>3){
			break;
		}
		Map<String,Object> mh = li.previous();
		if(mh.get("outcome").toString().equalsIgnoreCase("Defunct Coin") ||
				mh.get("outcome").toString().equalsIgnoreCase("Striker strike")){
			count = count+1;
		}
		j = j+1;
	}
	if(count>=3){
		this.setPlayerScore(player, -1);
	}
}

public int scoreDifference(){
	if(this.player1_score > this.player2_score)
        return this.player1_score - this.player2_score;
    else
        return this.player2_score - this.player1_score;
}
}
