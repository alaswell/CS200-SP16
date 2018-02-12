import java.util.Arrays;


public class Monkeys {

	private long count;
	private int n; 
	private int[] array;
	
	public Monkeys(int n){
		this.n =  n;
		this.count = 0;
		makeArray();
	}
	
	private void swap(int[] array, int i, int j){
		int t = array[i];
		array[i]=array[j];
		array[j]=t;
	}
	
	public void monkeys(int [] array, int i, int j, String indent){
		indent=indent+"\t";
		System.out.println("\n\n"+indent+"entering monkeys(array="+Arrays.toString(array)+", i="+i+", j="+j+")");
		count++;  // count method calls
		System.out.println(indent+"count ++ sets count to "+count);
		if(i<j) {
			System.out.println(indent+"i<j");
			// size 2 base case 
			if(j-i==1){
				System.out.println(indent+"Base case: j-i==1");
				if (array[i] > array[j]){ 
					System.out.println(indent+"swap("+Arrays.toString(array)+",i="+i+",j="+j+")");
					swap(array,i,j);}
			}
			else {
				System.out.println(indent+"j-i!=1");
				int third = (j-i+1)/3;
				System.out.println(indent+"third=(j-i+1)/3 = "+((j-i+1)/3));
				System.out.println(indent+"calling monkeys(array="+Arrays.toString(array)+", i="+i+", j-third="+(j-third)+")");
				monkeys(array, i, j-third,indent); // 2/3 n size problem
				System.out.println(indent+"calling monkeys(array="+Arrays.toString(array)+", i+third="+(i+third)+", j="+(j)+")");
				monkeys(array, i+third, j,indent); // 2/3 n size problem
				System.out.println(indent+"calling monkeys(array="+Arrays.toString(array)+", i="+i+", j-third="+(j-third)+")");
				monkeys(array, i, j-third,indent); // 2/3 n size problem
			}
		}
		System.out.println(indent+"end of call!\n");
	}

	public void monkeys() {
		System.out.println("\n\nCalling monkeys(array="+Arrays.toString(array)+", 0, array.length-1="+(array.length-1)+")");
		monkeys(array, 0, array.length-1,"");
	} 
	
	public void makeArray(){
		this.array = new int[n];
		for(int i=0; i<n; i++)
			array[i] = n-i;
	}
	
	public String toString(){
		String r = "n: " + n + ", count: " + count ;
		if(n<50)
			r += "\narray: "+Arrays.toString(array);
		return r;
	}
	
	public static void doTheMonkey(int n){
		System.out.println("creating new Monkey object with n="+n);
		Monkeys m = new Monkeys(n);
		System.out.println(m);		
		System.out.println("calling m.monkeys()");
		m.monkeys();
		System.out.println(m);		
	}
	
	public static void main(String[] args){
		System.out.println("calling doTheMonkey(1)");
		doTheMonkey(10);
		/*
		// Do you see what it does? How it does it?
		// How complex does it get?
        for(int i = 1; i<6; i+=1)
        	doTheMonkey(i);

		// How complex does it get?
        for(int i = 100; i<600; i+=100)
        	doTheMonkey(i);*/
	}
}
