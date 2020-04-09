import java.util.*;
import java.lang.*;

public class SpaceSearcher {

	public State simulatedAnnealing(State initialState)
	{
		State currentState = new State(initialState);
		currentState.heuristic();
		State bestState = new State(initialState);
		bestState.heuristic();
		State neighbor =null;
		ArrayList<State> children = new ArrayList<State>();
		double T= 10000;
		double coolingRate = 0.003;
		double currentEnergy=0;
		double neighborEnergy=0;
		double DE=0;
		while(T>0.01)
		{
			children.clear();
			children = currentState.getChildren();
			neighbor = children.get(new Random().nextInt(children.size()));
			currentEnergy = currentState.getScore();
			neighborEnergy = neighbor.getScore();
			DE = neighborEnergy-currentEnergy;
			if(DE>=0){
				currentState = new State(neighbor);
				currentState.heuristic();
			}
			else{
				if(Math.exp(DE/T)>Math.random()){
					currentState=new State(neighbor);
					currentState.heuristic();
				}

			}

			if(currentState.getScore()>bestState.getScore()){
				bestState=new State(currentState);
				bestState.heuristic();
			}

			T = T * (1-coolingRate);
			System.out.println("Temperature: " + T);
		}

		return bestState;
	}

}
