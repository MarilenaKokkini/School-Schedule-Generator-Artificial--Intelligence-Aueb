import java.util.*;
import java.lang.*;

public class SpaceSearcher {
	private ArrayList<State> states;
	private HashSet<State> closedSet;


	/*public State BestFSClosedSet(State initialState)
	{
		this.states = new ArrayList<State>();
		this.closedSet = new HashSet<State>();
		this.states.add(initialState);
		while(this.states.size() > 0)
		{
			State currentState = this.states.remove(0);
			if(currentState.isTerminal())
			{
				return currentState;
			}
			if(!closedSet.contains(currentState))
			{
				this.closedSet.add(currentState);
				for(State child : currentState.getChildren())
				{
					if(!closedSet.contains(child)) this.states.add(child);
				}
				//this.states.addAll(currentState.getChildren());
				Collections.sort(this.states,Collections.reverseOrder());
			}
		}
		return null;
	} */

	/*public State BestFS(State initialState)
	{
		this.states = new ArrayList<State>();
		this.states.add(initialState);
		while(this.states.size() > 0)
		{
			State currentState = this.states.remove(0);
			if(currentState.isTerminal())
			{
				System.out.println("YEEESSSSSSSSS");
				return currentState;
			}
			//We generate the children and calculate the heuristic values
			//this.states.addAll(currentState.getChildren());
			//We sort all the children according to their heuristic scores
			Collections.sort(this.states,Collections.reverseOrder());
		}
		return null;
	} */

	/*public State BestFSClosedSet(State initialState)
	{
		this.states = new ArrayList<State>();
		this.closedSet = new HashSet<State>();
		this.states.add(initialState);
		while(this.states.size() > 0)
		{
			State currentState = this.states.remove(0);
			//System.out.println(currentState.getScore());
			if(currentState.isTerminal())
			{
				System.out.println("YEEESSSSSSSSS");

				return currentState;
			}
			if(!closedSet.contains(currentState))
			{
				this.closedSet.add(currentState);
				this.states.addAll(currentState.getChildren());
				Collections.sort(this.states,Collections.reverseOrder());
			}
		}
		return null;
	} */

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
