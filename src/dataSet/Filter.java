package dataSet;
import java.util.LinkedList;
import java.util.List;

public class Filter {
	private String[] type;
	private boolean isTypeAll = false;
	private String[] goal;
	private boolean isGoalAll = false;
	private String[] keywords;
	private boolean isKeyNull = false;
	
	/**
	 * @param type
	 * @param goal
	 * @param keyword
	 */
	public Filter(String[] type, String[] goal, String keyword){
		/* check if type is 'all'*/
		if(type == null) isTypeAll = true;
		else{
				for(int i = 0; i < type.length; i ++)
					if(type[i].toLowerCase().equals("all"))
						isTypeAll = true;
				if(!isTypeAll)
					this.type = type;
		}
		
		/* check if goal is 'all' */
		if(goal == null) isGoalAll = true;
		else{
			for(int i = 0; i < goal.length; i ++)
				if(goal[i].toLowerCase().equals("all"))
					isGoalAll = true;
			if(!isGoalAll)
				this.goal = goal;
		}
		
		/* split the keyword*/
		if(keyword == null || keyword.length() == 0)
			isKeyNull = true;
		else
			keywords = keyword.split("[^a-z]+");
	}
	
	public boolean isMatch(DataSet data){
		/* check keywords*/
		boolean keyMatch = false;
		if(isKeyNull) keyMatch = true;
		else if(keywords.length > 0){
			for(int i = 0; i < keywords.length; i ++)
				if(data.hasKeyword(keywords[i])){
					keyMatch = true;
					break;
				}
		}
		else keyMatch = true;
		
		/* check data-type*/
		boolean typeMatch = false;
		if(!isTypeAll){
			for(int i = 0; i < type.length; i ++)
				if(data.isType(type[i])){
					typeMatch = true;
					break;
				}
		}
		else typeMatch = true;
		
		/* check goal*/
		boolean goalMatch = false;
		if(!isGoalAll){
			for(int i = 0; i < goal.length; i ++)
				if(data.isGoal(goal[i])){
					goalMatch = true;
					break;
				}
		}
		else goalMatch = true;
		
		/* summary */
		if(keyMatch && typeMatch && goalMatch) return true;
		else return false;
	}

	public LinkedList<DataSet> getMatchDataset(List<DataSet> list){
		LinkedList<DataSet> result = new LinkedList<DataSet>();
		for(DataSet data : list)
			if(isMatch(data))
				result.add(data);
		return result;
	}
	
//	public static void main(String[] args) throws IOException{
//		Filter f = new Filter(null, null, "test");
//		System.out.println("Filter constructed!");
//		DataSetList d = new DataSetList();
//		System.out.println("DataSetList constructed!");
//		LinkedList<DataSet> list = f.getMatchDataset(d.getList());
//		for(DataSet data : list)
//			System.out.println(data.getLink());
//	}

}
