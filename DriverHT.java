import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

import org.apache.commons.codec.digest.MurmurHash3;

/** This class, as its name implies, represents a K-mer object, or a substring
 *  of length k, belonging to the original DNA sequence, S, of length n. What differentiates
 *  a K-mer object from a regular String object, is the relevance of its number of 
 *  occurrences in the original DNA sequence (in order to compute for its K-mer 
 *  distribution). Hence, the variable denoting to which (nNumOfOccurrences), is stored as
 *  an attribute of this class. 
 */
class KMer{
	private String strSubstring;
	private int nNumOfOccurrences;
	
	/** This constructor creates a K-mer object by supplying the 
	  * needed values of its attributes (along with the argument passed).
	  * 
	  * @param strSubstring      the substring itself consisting only of the letters 
	  *                          from the alphabet, {a,c,g,t}
	  */
	public KMer(String strSubstring) {
		this.strSubstring = strSubstring;
		
		/* By default, the number of occurrences of each K-mer object(upon its creation) 
		   in the original DNA sequence is 1.                                           */
		this.nNumOfOccurrences = 1;
	}
	
	/** A method that returns the substring itself denoting the K-mer object, which belongs to the 
	  * original DNA sequence, S, of length n. 
	  * 
	  * @return      the substring denoting the K-mer object
	  */
	public String getSubstring() {
		return strSubstring;
	}
	
	/** A method that initializes the substring denoting the K-mer object,
	  * with the argument passed.
	  * 
	  * @param strSubstring    a String corresponding to a substring which
	  *                        represents the K-mer object
	  */
	public void setSubstring(String substring) {
		this.strSubstring = substring;
	}
	
	/** A method that returns the number of occurrences of the K-mer
	  * object represented in the original DNA sequence.  
	  * 
	  * @return       the number of occurrences of the given K-mer object
	  *               in the original DNA sequence, S, of length n
	  */
	public int getnNumOfOccurrences() {
		return nNumOfOccurrences;
	}
	
	/** A method that initializes the number of occurrences of the K-mer
	  * object represented in the original DNA sequence, with the argument passed.  
	  * 
	  * @param nNumOfOccurrences     a Integer corresponding to the number of occurrences 
	  *                              of the given K-mer object in the original DNA sequence, 
	  *                              S, of length n
	  */
	public void setnNumOfOccurrences(int nNumOfOccurrences) {
		this.nNumOfOccurrences = nNumOfOccurrences;
	}
	
	/** A convenient helper method that increments the number of occurrences of the
	  * K-mer object in the original DNA sequence, S, of length n.
	  */
	public void increaseNumOfOccurrences() {
		nNumOfOccurrences++;
	}
	
	/** "toString()" method of the class has been overridden such that it could provide the functionality
	  *  of displaying the attributes associated to each K-mer object upon its display in the algorithm of 
	  *  computing a K-mer distribution of a DNA sequence, S.
	  */
	@Override
	public String toString() {
		return strSubstring + " (" + nNumOfOccurrences + ")";
	}
}

/** This class is a template for a HT object or Hash Table, whose structure is important
 *  to integrate as part of the algorithm required to compute for the K-mer distribution of a DNA
 *  Sequence, S. It consists of an array of LinkedLists, referred to as "buckets", which can store given
 *  K-mer object/s in a location depending on the key generated from a hash function. It also consists of variables, 
 *  such as nMaxSize, which, as its name implies, will store the maximum size of the HT object (i.e. length n of the
 *  input DNA sequence), and a counter variable such as nNumOfCollisions, which will store the collision frequency
 *  upon storing the K-mer objects in this implemented HashTable object, specifically to each "bucket" or LinkedList.
 *  
 *  NOTE: The collision resolution technique utilized is Separate Chaining, as evident from the use of LinkedLists. 
 */
class HashTable{
	private LinkedList<KMer> [] buckets;
	private int nMaxSize;
	
	/* By default, the number of collisions encountered in the HashTable, with 
	   the existence of empty LinkedLists, is 0.                              */
	private int nNumOfCollisions = 0;
	
	/** This constructor creates a HashTable object by supplying the 
	  * needed value of its attribute, nMaxSize, with the argument passed.
	  * 
	  * 
	  * @param nMaxSize      an Integer which represents the maximum size or number of LinkedLists that
	  *                      the array of LinkedLists, buckets, can hold (as specified by n or the length
	  *                      of the input DNA sequence, S)
	  */
	@SuppressWarnings("unchecked")
	public HashTable(int nMaxSize) {
		
		int i;
		
		/* The same argument passed to initialize nMaxSize is used to set the
		   number of LinkedLists that the array, buckets, can hold.           */
		buckets = new LinkedList[nMaxSize];
		
		this.nMaxSize = nMaxSize;
		
		/* This for loop enables the initialization of each LinkedList contained in the array, buckets. */
        for (i = 0; i < nMaxSize; i++) {
            buckets[i] = new LinkedList<KMer>();
        }
	}
	
	/** A method that checks whether the substring of a K-mer object belonging to a
	  * LinkedList (whose key is hashed by the MurmurHash3 function) in the array, is same as 
	  * the String argument passed, which represents a newly extracted substring from the input DNA 
	  * sequence, S. In this case, the K-mer object possessing such a String is no longer added to the 
	  * LinkedList, but its number of occurrences in the input DNA sequence, must be increased.
	  * 
	  * @param nKey            an Integer storing the index of a LinkedList in the array
	  *                        whose K-mer object's substring may possibly match with 
	  *                        the String argument passed, strSubstring
	  * @param strSubstring    a String corresponding to a substring
	  *                        representing the K-mer object which may/may not be
	  *                        added to the LinkedList indexed by the nKey argument 
	  *                        passed
	  * @return                an Integer representing the index of the K-mer object in the LinkedList, 
	  *                        which holds the value of the String argument passed, strSubstring
	  */
	public int search(int nKey, String strSubstring) {
		int i, nIndex = -1; 
		boolean isFound = false;
		
		/* This for loop "searches" for any duplicate of the String argument passed in the 
		   LinkedList (indexed by the nKey argument passed) in the array. Note that it specifically
		   compares each K-mer object's substring in the LinkedList, with the String argument passed, 
		   such that it may detect multiple occurrences of a specific String in the input DNA sequence, S. */
		for(i=0; i<buckets[nKey].size() && !isFound; i++) {
			
			/* Once the substring of a K-mer object belonging to the LinkedList matches with the String
			   argument passed, the boolean variable, isFound, is modified to true, its number of occurrences is increased,
			   the index of the K-mer object existing to hold the value of the String, is stored in the appropriate variable, nIndex, 
			   and for loop instantly terminates.                                                                                    */
			if(buckets[nKey].get(i).getSubstring().equals(strSubstring)) {
				isFound = true;
				buckets[nKey].get(i).increaseNumOfOccurrences();
				nIndex = i;
			}
		}
		
		/* The index of the K-mer object in the LinkedList which holds the value of the String argument passed is returned.
		   If K-mer object with the String argument passed does not exist in the LinkedList, a value of -1 is returned.    */
		return nIndex;
	}
	
	
	/** A method that allows the insertion of each K-mer object (such that it represents a distinct substring of the input DNA sequence, S) in the 
	  *  HashTable object constructed. Hash function, MurmurHash3, has been utilized to determine the key, or index of the LinkedList to which each 
	  *  K-Mer object may be added amongst the array of LinkedLists. Through calling this method, the K-mer distribution of the input DNA sequence, S, is also computed, along with
	  *  the total number of collisions encountered in storing each K-mer object in the HashTable constructed. Collision is resolved through Separate Chaining, 
	  *  as evident by the use of LinkedLists. 
	  * 
	  * @param kMer                 the K-mer object (such that it is distinct) to be added to a LinkedList of randomized key or index based on the  
	  *                             value computed from the Hash function, MurmurHash3
	  * @param nDNASequenceLength   an Integer representing the length, n, of the input DNA Sequence, S, which is useful
	  *                             in the computation of the key or index from the Hash function, MurmurHash3
	  * @return                     a boolean denoting the success of the insert() operation on the K-mer object passed as argument
	  */
	public boolean insert(KMer kMer, int nDNASequenceLength) {
		int nIndex, nKey;
		boolean isInserted = false;
		
		/* The value of the key or index of LinkedList to which the K-mer object can be inserted, is computed, through
		   the hash function, MurmurHash3. In its computation, the length, n, of the input DNA sequence, S, deems relevant, so
		   that the randomized key may appropriately index a number in the range of (0 to n-1) which also represents the indices
		   of the possible LinkedLists in the array. To integrate such a functionality, the modulo operator was utilized. Note that the
		   abs (absolute value) function from the Math library was also utilized, since a negative value may be generated from the function. */
	    nKey = Math.abs(MurmurHash3.hash32x86(kMer.getSubstring().getBytes()) % (nDNASequenceLength - 1));
		
	    /* The search() method is called in order to check an existing occurrence of the substring extracted,
	       in the LinkedList whose index was computed from the hash function. Note that similar String objects would
	       hash to the same key or index of LinkedList, hence, it would be useful to detect a similar occurrence of substring
	       through this method.                                                                                              */
	    nIndex = search(nKey, kMer.getSubstring());
		
	    /* In case the method returns the value of -1, this means that the substring extracted is distinct, hence it could be added
	       as a K-mer object to the LinkedList with a key or index computed from the hash function.                                 */
		 if (nIndex == - 1) {
			 
			 /* If the LinkedList to which the K-mer object must be inserted, already exists with some other K-mer objects as elements, 
			    collision occurs. Hence, collision frequency increases.                                                               */
			if(buckets[nKey].size() != 0)
				 nNumOfCollisions++; 
			
			/* Nevertheless, with or without collision, the K-mer object is inserted as an element of the LinkedList to which the index or key
			   computed, points to.                                                                                                           */
			buckets[nKey].add(kMer);	
			
			/* The respective boolean variable is also modified to true, such that it denotes the success of the insert() operation.  */
			isInserted = true;
		 }
		 
		 /* NOTE: An unsuccessful insert() operation would mean that the substring extracted is no longer distinct, as proven by the search() method 
		    returning its index in the LinkedList it is hashed to. Hence, the number of occurrences must be increased instead.                      */
		 
		 /* A boolean, denoting the success of the insert() operation, is returned.  */
		 return isInserted;
	}
	
	/** A method that returns the array of LinkedLists with each List storing distinct K-mer objects, as part of the computation of 
	  * the K-mer distribution of an input DNA Sequence, S, with a HashTable object and Separate Chaining as collision resolution technique.
	  * 
	  * @return      the array of LinkedLists storing distinct K-mer objects
	  */
	public LinkedList<KMer>[] getBuckets() {
		return buckets;
	}

	/** A method that initializes the array of LinkedLists with each List storing distinct K-mer objects, with the argument passed.
	  * 
	  * @param buckets      an array of LinkedLists storing distinct K-mer objects
	  */
	public void setBuckets(LinkedList<KMer>[] buckets) {
		this.buckets = buckets;
	}

	/** A method that returns the maximum size or number of LinkedLists that the array of LinkedLists, buckets, can hold, 
	  * as specified by n or the length of the input DNA sequence, S.
	  * 
	  * @return      the maximum size or number of LinkedLists that the designated array can hold
	  */
	public int getnMaxSize() {
		return nMaxSize;
	}

	/** A method that initializes the number of LinkedLists that the array of LinkedLists, buckets, can hold, 
	  * with the argument passed.
	  * 
	  * @param nMaxSize     an Integer representing the maximum size or number of LinkedLists that the designated array can hold
	  */
	public void setnMaxSize(int nMaxSize) {
		this.nMaxSize = nMaxSize;
	}

	/** A method that returns the number of collisions encountered while storing each distinct K-mer object
	  * by the use of the hash function, MurmurHash3.
	  * 
	  * @return      the collision frequency or number of collisions encountered while using the hash function, MurmurHash3,
	  *              in storing each distinct K-mer object in the HashTable object constructed
	  */
	public int getnNumOfCollisions() {
		return nNumOfCollisions;
	}

	/** A method that initializes the number of collisions or collision frequency in storing each distinct K-mer object
	  * by the use of the hash function, MurmurHash3, with the argument passed.
	  * 
	  * @param nNumOfCollisions      an Integer representing the number of collisions encountered while using the hash function, MurmurHash3,
	  *                              in storing each distinct K-mer object in the HashTable object constructed
	  */
	public void setnNumOfCollisions(int nNumOfCollisions) {
		this.nNumOfCollisions = nNumOfCollisions;
	}
}

/** This class, as its name implies, represents a K-mer distribution of a DNA sequence, S, of length n.
 *  Other than the DNA sequence, it consists of an Integer representing the number of K-mer objects in 
 *  its distribution, and an ArrayList containing all of its K-mer objects (substrings of length k belonging
 *  to the original DNA sequence, S).
 *  
 *  This class has been created more specifically to allow for the implementation of the algorithm for 
 *  computing a K-mer distribution of a DNA sequence (as its contained methods might imply). 
 */
class KMerDistribution {
	private String strDNASequence;
	/* By default, the number of K-mer objects in a newly created object of the K-mer distribution is 0, as
	   algorithm has not been implemented yet.                                                             */
	private int nNumOfKMers = 0;
	private ArrayList <KMer> kMers = new ArrayList<>();
	
	/** This constructor creates a KMerDistribution object by supplying the 
	  * needed value of its attribute (along with the argument passed).
	  * 
	  * @param strDNASequence    the original DNA sequence, S, of length n, whose k-mer 
	  *                          distribution is to be computed
	  */
	public KMerDistribution(String strDNASequence) {
		this.strDNASequence = strDNASequence;
	}
	
	/** This method, as the name implies, is responsible for the generation of the K-mer objects in the K-mer
	  * distribution of an original DNA sequence, s, of length n. It integrates a HT data structure in the
	  * implementation of its algorithm. 
	  * 
	  * @param k                an Integer representing the length of each K-mer object that must be generated
	  * @return                 the collision frequency or number of collisions encountered while storing each distinct
	  *                         K-mer object in the integrated HT data structure
	  */
	public int generateKMers(int k) {
		
		int i, j;
		boolean isInserted, isFound;
		KMer kMer;
		
		/* A new HashTable object is created for the implementation of the algorithm. Note that each
		   element of the HashTable, referencing to an array of LinkedLists with each individual List, consists 
		   of distinct K-mer object/s as its data.                                                              */
		HashTable HT = new HashTable(strDNASequence.length());
		
		/* This for loop iterates for as long as valid substrings of length k could be extracted from the
		   original DNA sequence, S, of length n. Each of these substrings could represent a potential
		   K-mer object in the distribution.                                                            */
		for(i=0; i<(strDNASequence.length()-k)+1; i++) {
			
			/* A new K-mer object is created, passing along as argument, the substring itself of length k, 
			   extracted from the original DNA sequence, S, of length n.                                 */
			kMer = new KMer(strDNASequence.substring(i, i+k));
			
			/* The K-mer object created is to be inserted in the HT data structure integrated. However, the
			   success of such an insert operation depends on whether the substring extracted, representing this
			   K-mer object, is distinct, or is not contained already in the LinkedList of index/key computed by 
			   the hash function, MurmurHash3.                                                                   */
			isInserted = HT.insert(kMer, strDNASequence.length());
		
		     /* If the insert() operation yields successful (i.e. the K-mer object with such a substring is distinct), 
		        it should be added to the appropriate ArrayList, and the number of distinct K-mer objects in the distribution, 
		        must be incremented.                                                                                           */
			 if(isInserted == true) {
				 kMers.add(kMer);
				 nNumOfKMers++; 
			 }
			 
			 /* Otherwise, the number of occurrences of such a K-mer object is already incremented in the search() operation. */
		}
		
		/* The number of collisions encountered while storing each distinct K-mer object in the integrated HT data structure, 
		   is returned, such that it also reveals information on the efficiency of the hash function utilized in storage, which is
		   MurmurHash3.                                                                                                             */
		return HT.getnNumOfCollisions();
	}
		
		/** A method that displays the K-mer objects in the K-mer distribution of the DNA sequence, S.
		  */
		public void displayKMers() {
			
			int i;
			
			/* The display of each K-mer object in the K-mer distribution allow for the display of all
			   its associated attributes, consisting of the substring, and its number of occurrences 
			   in the DNA sequence.                                                                   */
			for(i=0; i<nNumOfKMers; i++) { 
				System.out.println(kMers.get(i));
			}
			
		}
		
		/** A method that returns the number of K-mer objects in the K-mer distribution, 
		  * of the DNA sequence, S.
		  * 
		  * @return      the number of K-mer objects in the K-mer distribution of the DNA
		  *              sequence, S
		  */
		public int getnNumOfKMers() {
			return nNumOfKMers;
		}

		/** A method that initializes the number of K-mer objects in the K-mer distribution, 
		  * of the DNA sequence, S.
		  * 
		  * @param nNumOfKMers     an Integer representing the number of K-mer objects in the 
		  *                        K-mer distribution of a DNA sequence, S
		  */
		public void setnNumOfKMers(int nNumOfKMers) {
			this.nNumOfKMers = nNumOfKMers;
		}

		/** A method that returns the list of K-mer objects in the K-mer distribution of the DNA
		  * sequence, S.
		  * 
		  * @return      the list of K-mer objects in the K-mer distribution of the DNA
		  *              sequence, S
		  */
		public ArrayList<KMer> getkMers() {
			return kMers;
		}

		/** A method that initializes the list of K-mer objects in the K-mer distribution of the DNA
		  * sequence, S.
		  * 
		  * @param kMers    an ArrayList representing the list of K-mer objects in the K-mer distribution 
		  *                 of a DNA sequence, S
		  */
		public void setkMers(ArrayList<KMer> kMers) {
			this.kMers = kMers;
		}
}

/** This class, as its name implies, represents the Driver class, consisting of the main method, 
 *  to which the HT-based algorithm can be run, applied, and tested. As such, it also consists of a method
 *  that allows for the generation of a random DNA sequence, depending on the input length, n.
 */
public class DriverHT {
		
		public static void main(String[]args) {
			
			boolean isValidInput = false;
			int k = 0, nStringLength = 0, nNumOfCollisions;
			String strDNASequence, strInputForLength, strInputForK;
			
			/* The variables of long datatype below are declared for the purpose of 
		       estimating the running time of the algorithm.                       */
			long midTime, endTime, timeElapsed;
			
			/* A new Scanner object is declared and initialized in order to prompt 
			   user of the desired length, n, by which a random DNA sequence, S, shall
			   be generated, consisting only of letters from the alphabet, {a,c,g,t}. */
			Scanner scanner = new Scanner(System.in);
			System.out.println("INPUT LENGTH: ");
			
			
			/* This do-while loop iterates for as long as user enters invalid input for the
			   length, such as input which do not correspond to an Integer value (i.e. String, 
			   double, etc.).                                                                 */
			do{
				/* The try block executes code that scans the user's input for the desired length
				   while also checking if their input as String can be parsed into an integer for 
				   validity checking. This is done to avoid any errors beforehand, upon implementing
				   the algorithm.                                                                   */
				try {
					strInputForLength = scanner.next();
					nStringLength = Integer.parseInt(strInputForLength);
					
					/* The boolean attribute below is modified to true once their input is valid
					   (i.e. representing anIinteger).                                             */
					isValidInput = true;
					
					/* However, the same attribute is modified to false again once their input is not
					   representing a positive integer (i.e. either zero or negative). An informative 
					   error message is also displayed, and the procedure of scanning input repeats. */
					if(nStringLength <= 0) {
					    System.out.println("Invalid Input! Not a positive integer.");
					    isValidInput = false;
					}			
				    /* If the String cannot be parsed into an integer, NumberFormatException is caught.
				       An informative error message will be displayed, allowing the user to enter new 
				       input, as the do-while loop iterates.                                          */
				} catch(NumberFormatException e) {
					System.out.println("Invalid Input! Not an integer.");
				}

			} while(!isValidInput);
			
			/* Once user's input of desired length is finally valid, the static method of generating a 
			   random DNA sequence from the class shall be called, to which its return value corresponding to 
			   the input DNA sequence for the algorithm is stored in the appropriate variable (strDNASequence). */
			strDNASequence = generateRandomDNASequence(nStringLength);
			
			/* The random DNA Sequence generated is displayed for the purpose of assuring its validity as
			   consisting only of letters from the alphabet, {a,c,g,t}, and of a fixed length, n.         */
			System.out.println("Random DNA Sequence of Length " + nStringLength + ": " + strDNASequence + "\n");
		
			System.out.println("INPUT K: ");
			
			isValidInput = false;
			
			/* This do-while loop iterates for as long as user enters invalid input for the
			   length of each k-mer (k), such as input which do not correspond to an integer value 
			   (i.e. String, double, etc.).                                                        */
			do{
				/* The try block executes code that scans the user's input for the desired length
				   of each K-mer object while also checking if their input as String can be parsed 
				   into an integer for validity checking. This is done to avoid any errors beforehand,
				   upon implementing the algorithm.                                                   */
				try {
					strInputForK = scanner.next();
					k = Integer.parseInt(strInputForK);
					
					/* The boolean attribute below is modified to true once their input is valid
					   (i.e. representing an integer).                                             */
					isValidInput = true;
					
					/* However, the same attribute is modified to false again once their input is not
					   representing any valid K-mer length for the algorithm, {5, 6, 7}. An informative 
					   error message is also displayed, and the procedure of scanning input repeats. */
					if(k != 5 && k!=6 && k!= 7) {
					    System.out.println("Invalid Input! Integer must only be equivalent to 5, 6, or 7.");
					    isValidInput = false;
					}			
				    /* If the String cannot be parsed into an integer, NumberFormatException is caught.
				       An informative error message will be displayed, allowing the user to enter new 
				       input, as the do-while loop iterates.                                          */
				} catch(NumberFormatException e) {
					System.out.println("Invalid Input! Not an integer.");
				}

			} while(!isValidInput);	
			
			/* The midTime variable stores the current value of the most precise available system timer, 
			   in nanoseconds, as it deems essential to calculate the execution time of the algorithm soon. */                                                                            
			midTime = System.nanoTime();
			
			/* In order to conduct the algorithm required for the computation of a K-mer distribution of a DNA
			   sequence, S, a new KMerDistribution object is created, passing as argument, the DNA sequence 
			   which was randomly generated.                                                                  */
			KMerDistribution kMerDistribution = new KMerDistribution(strDNASequence);
			
			/* The method of the object is called in order to finally conduct the algorithm for the computation of a K-mer
			   distribution, passing along the appropriate argument, such as the desired length of each K-mer. Its
			   return value is stored in the appropriate variable, nNumOfCollisions.                                      */
			nNumOfCollisions = kMerDistribution.generateKMers(k);
			
			System.out.println("\nPLACEMENT BY INSERTION: ");
			
			/* The list of K-mer objects in the distribution is displayed, along with each of their number
			   of occurrences in the original DNA sequence, S. The method "displayKMers()" of the object is called
			   for this purpose.                                                                                  */ 
		    /* NOTE: The order of display is dependent on each K-mer object's placement in the insertion procedure. */
			kMerDistribution.displayKMers();

			/* The number of collisions encountered upon storage of each distinct K-mer object in the HT data structure is also displayed. */
			System.out.println("\nNumber of Collisions: " + nNumOfCollisions);
			
			/* The endTime variable stores the current value of the most precise available system timer, 
			   in nanoseconds, as it deems essential to calculate the execution time of the algorithm soon. */                                                                   
			/* NOTE: The time to be measured here marks the ending time of the algorithm. The algorithm ends
			   after the output, K-mer distribution is displayed, along with the number of collisions.     */
			endTime = System.nanoTime();
			
			 /* The elapsed time or duration of the algorithm on the input length is calculated by the subtraction 
                of the ending time of the algorithm by the time the algorithm started its process (upon creation 
                of the new kMerDistribution object)                                                               */
		    timeElapsed = endTime - midTime;
		 
		    /* The execution time in nanoseconds and seconds are displayed for the purpose of estimating the 
		       running time of the algorithm in the report, and comparison with other implementations.       */
		    System.out.println("\n\nExecution time in nanoseconds: " + timeElapsed);
		    System.out.println("Execution time in seconds: " + timeElapsed/(double)1000000000);
			
			 /* The method "close" on the Scanner object is called, upon termination of the program. */
			scanner.close();
		}
		
   /** As the name implies, a method that generates a random DNA sequence, given user's desired 
	 * length of it and inclusion of letters from the alphabet, {a,c,g,t}.  It takes advantage 
	 * of creating objects of pre-defined classes in Java, such as StringBuilder class and
	 * Random Class, in order to complete its procedure. 
	 * 
	 * @param nSequenceLength    the desired length, n, of the DNA sequence, S
	 */
	 public static String generateRandomDNASequence(int nSequenceLength) {
		
		int nCounter;
		String randomDNASequence;
		
		/* This String object holds the value of the sole possible characters that could
		   be included in the random String to be generated.                            */
		String strChars = "acgt";
		
		/* A new StringBuilder object is created (given desired length) which represents a
		   mutable sequence of characters. As mutable, we are allowed to manually append 
		   characters to it, as shown in the process below.                               */
		
		StringBuilder sb = new StringBuilder(nSequenceLength);
		
		/* A new Random object is created, which helps in generating random numbers. 
		   This would be useful in generating a random index applicable to strChars String. */  
		Random randomGenerator = new Random();
		
		/* For loop is constructed to manually append characters to the StringBuilder object
		   created, or allow for the generation of a random String.                          */
		for(nCounter = 0; nCounter<nSequenceLength; nCounter++) 
		{
			/* To simulate the generation of a random DNA sequence, the Random object is used 
			   to return a random index applicable to strChars String (0 inclusive and
			   strChars.length() is exclusive in the numbers that can be randomly returned), 
			   and locate the given character of the returned index in strChars by the 
			   "charAt(int index)" method. The located character can be any character from 
			   the alphabet, {a,c,g,t}. After which, the located character is appended to 
			   the sequence so far created.                                                  */
			sb.append(strChars.charAt(randomGenerator.nextInt(strChars.length())));
		}
		
		/* Random DNA sequence generated is appropriately converted to a String object and stored
		   in a separate variable that shall be returned as shown below.                         */
		randomDNASequence = sb.toString();
		
		/* The random DNA sequence generated is returned to the caller of the method (main method). */
		return randomDNASequence;
	   }
}