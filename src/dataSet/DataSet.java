package dataSet;

public class DataSet {

	
	private int id;
	private String name;
	private String goal;
	private String type;
	private String link;
	private float size;
	
	/**
	 * @param id
	 * @param name
	 * @param goal
	 * @param type
	 * @param link
	 * @param size
	 */
	public DataSet(int id, String name, String goal, String type, String link, float size){
		this.id = id;
		this.name = name.toLowerCase();
		this.goal = goal.toLowerCase();
		this.type = type.toLowerCase();
		this.link = link;
		this.size = size;
	}
	
	
	public int getId() {return id;}

	public String getName() {return name;}

	public String getGoal() {return goal;}

	public String getType() {return type;}

	public String getLink() {return link;}

	public float getSize() {return size;}
	
	public boolean isType(String target){
		String LCTarget = target.toLowerCase();
		if(LCTarget.equals("all")) return true;
		return type.equals(LCTarget);
	}
	
	public boolean hasKeyword(String keyword){
		String LCKeyword = keyword.toLowerCase();
		String[] split = name.split("[^a-zA-Z]+");
		for(int i = 0; i < split.length; i ++){
			String LCWord = split[i].toLowerCase();
			if(LCWord.equals(LCKeyword)) return true;
		}
		return false;
	}
	
	public boolean isGoal(String target){
		String LCGoal = target.toLowerCase();
		if(LCGoal.equals("all")) return true;
		return goal.equals(LCGoal);
	}
}
