// Created by: Alex Laswell
// Date 3/31/2016

public class Words {
    public static void main(String[] args) {
    	
    	// 1: Draw the class hierarchy
    	// which instance variables are defined where?
    	// 	name in super / defs in child
    	// which methods are overriden?
    	// 	toString()
    	
        Dictionary webster = new Dictionary("Webster",100000);
        Book limbo = new Book("Alice in Wonderland");
        Book dosto = new Book("The brothers Karamazov");

        
        // 2: the 
        //    return this + ", a good book";
        // in Book's motto method refers to which toString method?
        // 	books class
        System.out.println(limbo.motto()); 
        
        
        // 3: the 
        //    return this + ", a good book";
        // in Book's motto method refers to which toString method?
        // 	dictionary's class
        System.out.println(webster.motto()); 
        
        // 4: Is this valid? If not, can it be fixed and how?
        // 	yes, of course b/c Dictionary isa book 
        limbo = webster;
        // What will be printed?  Which toString is called?
        // 	Dictionary: Webster, 100000 definitions 	
        System.out.println(limbo);
        
                
                
        
         // 5: Is this valid? If not, can it be fixed and how?
         // 	no, but a cast would work.
         Dictionary d1 = (Dictionary) limbo;
         
         // When fixed, what will be printed?
         // 	Dictionary: Webster, 100000 definitions, a good book!
         System.out.println(d1.motto());
        
        
         
         // 6: Is this valid? If not, can it be fixed and how?
         // 	no
         Dictionary d2 = (Dictionary)dosto;
         System.out.println(d2);
      }
}
