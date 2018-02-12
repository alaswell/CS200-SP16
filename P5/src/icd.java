import java.io.*;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

// Author:	Alex Laswell
// Created:	05/01/2016
// Contact: alaswell@cs.colostate.edu

public class icd {
	
	// Compute CRC-32 checksum
    public static long cksum(String s) {
        Checksum engine = new CRC32();
        byte[] bytes = s.getBytes();
        engine.update(bytes, 0, bytes.length);
        return engine.getValue();
    }
    
	public static void main(String [] args) {
		
        // The name of the file to open.
		String fileName = null;
		
		// The name of the hash table
		BST[] hashTable = null;

		// loop index for while loop
		int i = 2;
		
		// check for proper USAGE
		if(args.length < 3) {
			System.out.println("USAGE: java icd.java (String: filename) (Int: sizeOfHashTable) (String: codeQuerie)+");
			System.exit(1);
		}
		
		// Now we KNOW these definitely exit
		// and can blindly set them. 
		fileName = new String(args[0]);
		hashTable = new BST[Integer.parseInt(args[1])];
		
        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Read the file, one line at a time
            while((line = bufferedReader.readLine()) != null) {
            	// Parse the line into tokens
            	String orderNumber = line.substring(0, 5);
            	String code = line.substring(6,13);
            	code = code.trim();
            
            	String header = line.substring(14,15);
            	String shortDesc = line.substring(16,77);
            	String longDesc = line.substring(77);
            	
            	// Hash the query string to get the index into the hash table.
            	long checksum = cksum(code);
            	int index = (int) (checksum % hashTable.length);
            	
            	// Create a new BST at the checksum location
            	IdVal idVal = new IdVal(checksum, longDesc);
            	try {
            		if(hashTable[index] == null) {
            			hashTable[index] = new BST();
            			hashTable[index].insertItem(idVal); 
            		}
            		else
            			hashTable[index].insertItem(idVal);
				} catch (BSTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }   
            
            // Now we can search 
            // while there are still queries
            while(i < args.length){
            	// get the query string from command line
            	String query = args[i];
            	
            	// Hash the query string to get the index into the hash table.
            	long checksum = cksum(query);
            	int index = (int) (checksum % hashTable.length);

            	IdVal temp = hashTable[index].retrieveItem(checksum);
            	//temp = hashTable[2939].retrieveItem(3742892939l);
            	
            	if(temp != null) {
            		System.out.println(query + ": " + temp.getVal());
            	}
            	else
            		System.out.println(query  + ": code not found");
            	i++;
            }


            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");                  
        }
    }
}
