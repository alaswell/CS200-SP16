// IdVal is a String KeyItem 
// Strings are Comparable
public class IdVal extends KeyItem<Long> {
	
	protected String val;
	
	public IdVal(Long id, String val){
		super(id);
		this.val = val;
	}
	
	public String getVal() {
		return this.val;
	}

	public String toString(){
		return "[" + getKey() + ": " + val+ "]"; 
	}
	
	public static void main(String[] args){
	}
}
