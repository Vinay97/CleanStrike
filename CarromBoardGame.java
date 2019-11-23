import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CarromBoardGame {
	private Scanner in;
	public List<Integer> getInputs() throws FileNotFoundException{
		in = new Scanner(new File(System.getProperty("user.dir")+File.separator+"src"+File.separator+"input.txt"));
        List<Integer> arr = new ArrayList();
        while(in.hasNext()){
        	int value = Integer.parseInt(in.nextLine());
        	if(!(value<1 || value >6)){
        		arr.add(value);
        }
        }
        	return arr;
	}
	
	public void startGame() throws FileNotFoundException{
		
		int option = 0;
		CarromBoard carromBoard = new CarromBoard();
		Players players = new Players();
		int player  = 1;
		
		List<Integer> inputs = getInputs();
		while(true){
			for(Integer i: inputs){
				option = i;
			player = (player+1)%2;
			if((players.getPlayerScore()&&players.scoreDifference()>=3||
					carromBoard.coinCheck())){
				Map<String,Object> score = players.gameResult();
				System.out.println("Result: "+score.get("result")+" won");
				System.out.println("Final Score: "+score.get("player"+player+"score")+"-"+score.get("player2score"));
				System.exit(0);
			}
			
			System.out.println("Player:"+(player));
			System.out.println("1. Strike");
            System.out.println("2. Multi Strike");
            System.out.println("3. Red strike");
            System.out.println("4. Striker strike");
            System.out.println("5. Defunct coin");
            System.out.println("6. None");
            System.out.println(option);
                 
            if(option==1){
            	int result = carromBoard.strike();
            	players.setPlayerScore(player, result);
            	players.setPlayerMove(player, OptionConstants.STRIKE);
            	System.out.println(carromBoard);
            }
            else if(option==2){
            	int result = carromBoard.multiStrike();
            	players.setPlayerScore(player, result);
            	players.setPlayerMove(player, OptionConstants.MULTI_STRIKE);
            }
            else if(option==3){
            	int result = carromBoard.redStrike();
            	players.setPlayerScore(player, result);
            	players.setPlayerMove(player, OptionConstants.RED_STRIKE);
            }
            else if(option==4){
            	int result = carromBoard.strikerStrike();
            	players.setPlayerScore(player, result);
            	players.setPlayerMove(player, OptionConstants.STRIKER_STRIKE);
            	players.foulCount(player);
            }
            else if(option==5){
            	int result = carromBoard.defuntCoin();
            	players.setPlayerScore(player, result);
            	players.setPlayerMove(player, OptionConstants.DEFUNCT_COIN);
            	players.foulCount(player);
            }
            else if(option==6){
            	int result = carromBoard.emptyStrike();
            	players.setPlayerScore(player, result);
            	players.setPlayerMove(player, OptionConstants.EMPTY_STRIKE);
            	players.emptyMove(player);
            }
            System.out.println("Black Coin: "+carromBoard.getBlack());
            System.out.println("Red Coin: "+carromBoard.getRed());
		}
		}
	}
	}

