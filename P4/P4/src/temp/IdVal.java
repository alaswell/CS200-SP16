// IdVal is a String KeyItem 
// Strings are Comparable
public class IdVal extends KeyItem<String> {
	
	protected Boolean val;
	
	public IdVal(String id, Boolean val){
		super(id);
		this.val = val;
	}

	public String toString(){
		return "[" + getKey() + ": " + val+ "]"; 
	}
	
	public Boolean getVal(){
		return this.val;
	}
	
	public static void main(String[] args){
		IdVal iv1 = new IdVal("abc", true);
		IdVal iv2 = new IdVal("bcd", false);
		IdVal iv3 = new IdVal("cde", true);	
		if(iv1.getKey().compareTo(iv2.getKey())<0)
			System.out.println(iv1 + " < " + iv2);
	}
}
