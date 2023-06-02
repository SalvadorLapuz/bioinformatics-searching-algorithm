import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

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

/** This class represents a Node object, that of which shall belong as element of the
 *  constructed Binary Search Tree in the implemented algorithm. A single Node, which
 *  contains its stored data (kMer), must also consist of two Node objects(leftNode and rightNode), 
 *  respectively containing a reference to each of its child node.
 */
class Node {
	private KMer kMer;
	private Node rightNode;
	private Node leftNode;
	
	/** This constructor creates a Node object by supplying the 
	  * needed value of its attribute, kMer, with the argument passed.
	  * 
	  * @param kMer     the KMer object to be stored as data of the Node object
	  */
	public Node(KMer kMer) {
		this.kMer = kMer;
		
		/* By default, upon creation of a Node object, the reference to its children nodes
		   must be null (denoting that these Nodes are referring to nothing, or haven't been
		   instantiated as Node objects containing data).                                   */
		this.rightNode = null;
		this.leftNode = null;
	}
	
	/** A method that returns the K-mer object stored as data of the Node object.
	  * 
	  * @return      the data stored by the Node object, also corresponding to a valid
	  *              K-mer object
	  */
	public KMer getkMer() {
		return kMer;
	}

	/** A method that initializes the K-mer object stored as data, with the argument passed.
	  * 
	  * @param kMer     a K-mer object which could be set as data of the Node object
	  */
	public void setkMer(KMer kMer) {
		this.kMer = kMer;
	}

	/** A method that returns a Node object, representing the right child of that
	  * certain Node object. It returns null if the Node object consists of no child node
	  * on its right subtree. 
	  * 
	  * @return      the Node object representing the right child of the certain Node object 
	  */
	public Node getRightNode() {
		return rightNode;
	}

	/** A method that initializes the child node on the Node object's right subtree,
	  * with the argument passed. 
	  * 
	  * @param rightNode    a Node object which may represent the right child of the certain Node object 
	  */
	public void setRightNode(Node rightNode) {
		this.rightNode = rightNode;
	}

	/** A method that returns a Node object, representing the left child of that
	  * certain Node object. It returns null if the Node object consists of no child node
	  * on its left subtree. 
	  * 
	  * @return   the Node object representing the left child of the certain Node object  
	  */
	public Node getLeftNode() {
		return leftNode;
	}

	/** A method that initializes the child node on the Node object's left subtree,
	  * with the argument passed. 
	  * 
	  * @param leftNode    a Node object which may represent the left child of the certain Node object 
	  */
	public void setLeftNode(Node leftNode) {
		this.leftNode = leftNode;
	}
}

/** This class is a template for a BST object or Binary Search Tree, whose structure is important
 *  to integrate as part of the algorithm required to compute for the K-mer distribution of a DNA
 *  Sequence, S. It consists of a Node object such as the rootNode to which all of the base operations
 *  of a Binary Search Tree usually originate from. Relevant operations, innately considered in the 
 *  implementation of its data structure, such as create(), insert() - recursive version, search()
 *  - recursive version and  inOrderWalk() - recursive version, have been included, such that they deem
 *  useful in the algorithm required to compute for a K-mer distribution of a DNA sequence, S.
 */
class BST {
	
	private Node rootNode;

	
	/** This methods creates an empty Binary Search Tree, such that its
	  * root node is represented by a null pointer, and as such, consists of no
	  * existing Node objects.
	  */
	public void create() {
		rootNode = null;
	}
	
	/** This method determines whether the search key of a given K-mer object, particularly
	  * the substring itself, exists in the implemented BST. As such, it returns a value
	  * corresponding to this description. 
	  * 
	  * @param rootNode   a Node object whose value will be inspected, such that it
	  *                   is the return value of the search operation (recursive version),
	  *                   of a given K-mer object in the BST
	  * 
	  * @return           a boolean value determining whether the search key of a K-mer
	  *                   exists in the BST
	  */
	public boolean search(Node rootNode) {
		
		/* If the returned value of the search operation (recursive version) is NOT null,
		   this means that the search key of a given K-mer object (i.e. its substring) exists 
		   in the implemented BST.                                                             */
		if(rootNode != null) 
		{
			return true;
		}
		
		/* Otherwise, it does NOT EXIST in the implemented BST or it could be that search 
		   operation was done on an empty BST at first.                                    */
		{
			return false;
		}	
	}
	
	/** This method recursively traverses down the BST, based on comparisons of the
	  * K-mer object searched in the implemented BST and the data (K-mer object) of
	  * existing Node objects, such that each K-mer object is added in the BST while
	  * maintaining its property. Note that the substring itself of a K-mer object is compared
	  * with that of which, the existing Node object's. 
	  * 
	  * @param rootNode  the Node object whose K-mer object must be compared with
	  *                  the K-mer object searched in the implemented BST, 
	  *                  (comparison usually starts from the rootNode, and each Node object
	  *                  may be considered the rootNode of a smaller subtree)
	  *              
	  * @param kMer      the K-mer object searched in the implemented BST, such that it may 
	  *                  or may not be distinct amongst all                
	  */
	public Node searchRecursively(Node rootNode, KMer kMer) {
		
		Node nodeToReturn = null;
		
		/* BASE CASE: When the Node object compared to the K-mer object searched
		   no longer consists of any data, such that it is represented by a null pointer. 
		   This signifies that the K-mer object was not found in the BST. 
		   
		   Base Case also occurs when the K-mer object was eventually found in the BST.   */
		if(rootNode == null || rootNode.getkMer().getSubstring().equals(kMer.getSubstring())) 
		{
			/* In such cases, the associated variable is initialized with that Node object, eventually
			   to be returned to its calling method.                                                  */
			nodeToReturn = rootNode;
		}
		
		/* The following cases apply for as long as recursive traversal down the tree is implemented,
		   such that an empty Node object (represented by a null pointer) may be found (signifying that the
		   K-mer object searched does not exist in the BST), or the K-mer object is eventually found in the BST. */
		
		/* As maintenance of the BST property, the direction of recursive traversal is specified by the 
		   comparison of each Node object's K-mer object (substring) with that of which, the K-mer object
		   searched.                                                                                         */
		
		/* If the K-mer object (such as its substring) searched is lexicographically less with that of
		   the Node object being compared with, the child node on its left subtree is explored. As such,
		   a recursive call to the method is returned, passing in the appropriate argument.             */
		else if(kMer.getSubstring().compareTo(rootNode.getkMer().getSubstring()) < 0) 
		{
			return searchRecursively(rootNode.getLeftNode(), kMer);
		}
		
		/* If the K-mer object (such as its substring) searched is lexicographically greater with that of
		   the Node object being compared with, the child node on its right subtree is explored. As such,
		   a recursive call to method is returned, passing in the appropriate argument.                 */
		else if(kMer.getSubstring().compareTo(rootNode.getkMer().getSubstring()) > 0) 
		{
			return searchRecursively(rootNode.getRightNode(), kMer);
		}
		
		/* The Node object eventually returned in its final recursive call, may represent the Node object which
		   consists of the K-mer object searched (as data), or an empty Node object (signifying the non-existence of
		   the K-mer object in the BST).                                                                             */
		return nodeToReturn;
	}
	
	/** This method adds a new Node object with the argument passed (kMer object) 
	  * in its proper place in the BST. The procedure is implemented, by calling the 
	  * recursive version of the insert operation on the given K-mer object, passing
	  * along as an argument, the rootNode of the BST, which is also set anew as 
	  * recent insert operation enforces changes to the whole BST. 
	  * 
	  * 
	  * @param kMer  the K-mer object looking to be added in the implemented BST,
	  *              such that it is distinct among all other K-mer objects whose values
	  *              have served as data of the Node objects
	  */
	public void insert(KMer kMer) {
		setRootNode(insertRecursively(rootNode, kMer));
	}
	
	/** This method recursively traverses down the BST, based on comparisons of the
	  * K-mer object looking to be inserted in the implemented BST and the data (K-mer object) of
	  * existing Node objects, such that the K-mer object may be added in the BST while
	  * maintaining its property. Note that the substring itself of a K-mer object is compared
	  * with that of which, the existing Node object's. 
	  * 
	  * @param rootNode  the Node object whose K-mer object must be compared with
	  *                  the K-mer object looking to be added in the implemented BST, 
	  *                  (comparison usually starts from the rootNode, and each Node object
	  *                  may be considered the rootNode of a smaller subtree)
	  *              
	  * @param kMer      the K-mer object looking to be added in the implemented BST,
	  *                  such that it is distinct among all other K-mer objects whose values
	  *                  have served as data of the Node objects
	  */
	public Node insertRecursively(Node rootNode, KMer kMer) {
		
		/* BASE CASE: When the Node object compared to the K-mer object to be inserted,
		   no longer consists of any data, such that it is represented by a null pointer */
		if(rootNode == null) 
		{
			/* Comparison could no longer occur between substrings, and a placement position 
			   for the Node object consisting of the K-mer object as data, has been found, by
			   traversal down the tree.                                                       */
			
			/* A new Node object has been created, with its data consisting of the K-mer object 
			   to be inserted. Its object is returned, finally terminating all the recursive calls
			   in memory.                                                                           */
			rootNode = new Node(kMer);
			return rootNode;
		}
		
		/* The following cases apply for as long as recursive traversal down the tree is implemented,
		   such that an empty Node object (represented by a null pointer) may be found for the insertion of
		   the K-mer object as its data.                                                                   */
		
		/* In order to maintain the BST property, the direction of recursive traversal is specified by the 
		   comparison of each Node object's K-mer object (substring) with that of which, the K-mer object
		   looking to be inserted.                                                                        */
		
		/* If the K-mer object (such as its substring) to be inserted is lexicographically less with that of
		   the Node object being compared with, the child node on its left subtree is explored.              */
		else if(kMer.getSubstring().compareTo(rootNode.getkMer().getSubstring()) < 0) 
		{
			/* After success of each k-Mer object having been inserted as the data value of a
			   Node object, the rootNode through it was called, is modified to contain that newly
			   created Node object, as a child of its left subtree.                               */
			rootNode.setLeftNode(insertRecursively(rootNode.getLeftNode(), kMer));
		}
		
		/* If the K-mer object (such as its substring) to be inserted is lexicographically greater with that of
		   the Node object being compared with, the child node on its right subtree is explored.              */
		else if(kMer.getSubstring().compareTo(rootNode.getkMer().getSubstring()) > 0) 
		{
			/* After success of each k-Mer object having been inserted as the data value of a
			   Node object, the rootNode through it was called, is modified to contain that newly
			   created Node object, as a child of its right subtree.                             */
			rootNode.setRightNode(insertRecursively(rootNode.getRightNode(), kMer));
		}
		
		/* The rootNode object modified to contain the newly created Node object is returned for each of the past
		   recursive calls' modification as well.                                                                  */
		return rootNode;
	}
	
	/** This method implements an inorder traversal of the BST, such that data of each Node object
	  * (K-mer object) in the K-mer distribution could be displayed in a lexicographically, ascending order.
	  * 
	  * This method was created for the purpose of checking the correctness of the implemented BST data structure
	  * operations (most particularly related to insertion) and the overall algorithm for the computation 
	  * of a K-mer distribution of a DNA sequence, S. 
	  * 
	  * @param node   the Node object through which the inorder traversal (recursive version)
	  *               of the implemented BST must start (usually the RootNode)
	  */
	public void inOrderWalk(Node node) {
		/* The recursive version of this method is implemented, for as long 
		   base case or a null Node object whose substring is non-existing
		   is reached.                                                     */
		 if (node != null) {
			 
			 /* Inorder Traversal, for each recursive call to the method,
			    works by visiting all Node objects in the left subtree, visiting 
			    the original Node object(rootNode), and all Node objects in the right subtree. */
	         
			 inOrderWalk(node.getLeftNode());
	         
	         /* The contents of a Node object, such as the data it holds (K-mer object with
	            substring and number of occurrences) are printed.                             */
	         System.out.println(node.getkMer().getSubstring() + " (" + node.getkMer().getnNumOfOccurrences() + ")");
	         inOrderWalk(node.getRightNode());
	     }
	}
	
	/** A method that returns a Node object, representing the Root Node of the BST.
	  * 
	  * @return      the Node object representing the Root Node of the BST 
	  */
	public Node getRootNode() {
		return rootNode;
	}

	/** A method that initializes Root Node of the BST, with the argument passed.
	  * 
	  * @param rootNode      a Node object which may represent the Root Node of the BST
	  */
	public void setRootNode(Node rootNode) {
		this.rootNode = rootNode;
	}
}

/** This class, as its name implies, represents a K-mer distribution of a DNA sequence, S, of length n.
 *  Other than the DNA sequence, it consists of an Integer representing the number of K-mer objects in 
 *  its distribution, and an ArrayList containing all of its K-mer objects (substrings of length k belonging
 *  to the original DNA sequence, S).
 *  
 *  This class has been created more specifically to allow for the implementation of the algorithm for computing a 
 *  K-mer distribution of a DNA sequence (as its contained methods might imply). 
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
	  * distribution of an original DNA sequence, s, of length n. It integrates a BST data structure in the
	  * implementation of its algorithm. 
	  * 
	  * @param k                an Integer representing the length of each K-mer object that must be generated
	  */
	public void generateKMers(int k) {
		
		int i;
		KMer kMer;
		Node node;
		Boolean isFound;
		
		/* A new BST object is created for the implementation of the algorithm. Note that each
		   node of the BST consists of a K-mer object as its data.                            */
		BST bst = new BST();
		
		/* This method is called in order to create an empty BST (represented by a null pointer). */
		bst.create();
		
		/* This for loop iterates for as long as valid substrings of length k could be extracted from the
		   original DNA sequence, S, of length n. Each of these substrings could represent a potential
		   K-mer object in the distribution.                                                            */
		for(i=0; i<(strDNASequence.length()-k)+1; i++) {
			
			/* A new K-mer object is created, passing along as argument, the substring itself of length k, 
			   extracted from the original DNA sequence, S, of length n.                                 */
			kMer = new KMer(strDNASequence.substring(i, i+k));
			
			/* The K-mer object created is searched in the BST data structure integrated, passing along as 
			   argument the rootNode of the BST, as recursive traversal down the tree starts from there. The
			   return value is stored in the appropriate variable of type Node.                             */
			node = bst.searchRecursively(bst.getRootNode(), kMer);
			 
			/* In order to determine whether the K-mer object was found in the BST data structure, the appropriate
			   method is called, passing along the return value of the previous method called. The boolean value
			   it eventually returns is stored in the appropriate descriptive Boolean variable.                 */
		    isFound = bst.search(node);
			 
		     /* If the K-mer object was found in the BST such that the return value of the called method before,
		        represents a non-null Node object, instead of inserting that Node object in the implemented BST, its
		        number of occurrences in the original DNA sequence, S, is increased.                                  */
			 if(isFound) 
			 {
				 node.getkMer().increaseNumOfOccurrences();
			 }
			 
			 /* Otherwise, the return value of the called method before represents a null Node object (signifying that a
			    node object consisting of that K-mer object as data does not exist in the implemented BST), hence, as distinct,
			    a node object with that K-mer object, is inserted into the implemented BST, by calling the appropriate method 
			    of the object. Moreover, that K-mer object is included in the list of the K-mer objects in the distribution, 
			    incrementing the number of K-mer objects of the DNA sequence, S.                                              */
			 else {
				 bst.insert(kMer);
				 kMers.add(kMer);
				 nNumOfKMers++;
			 }
		}	
		
		System.out.println("\nINORDER WALK: ");
		
		/* For the purpose of showcasing the correctness of the implemented BST data structure in constructing a valid BST, 
		   the inorder traversal of the resulting BST for the computation of a K-mer distribution is displayed.          */ 
		bst.inOrderWalk(bst.getRootNode());
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
 *  to which the BST-based algorithm can be run, applied, and tested. As such, it also consists of a method
 *  that allows for the generation of a random DNA sequence, depending on the input length, n.
 */
public class DriverBST {
	
	public static void main(String[]args) {
		
		boolean isValidInput = false;
		int k = 0, nStringLength = 0;
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
		   distribution, passing along the appropriate argument, such as the desired length of each K-mer.                                  */
		kMerDistribution.generateKMers(k);
		
		System.out.println("\nPLACEMENT BY INSERTION: ");
		
		/* The list of K-mer objects in the distribution is displayed, along with each of their number
		   of occurrences in the original DNA sequence, S. The method "displayKMers()" of the object is called
		   for this purpose.                                                                                  */ 
	    /* NOTE: The order of display is dependent on each K-mer object's placement in the insertion procedure. */
		kMerDistribution.displayKMers();
		
		/* The endTime variable stores the current value of the most precise available system timer, 
		   in nanoseconds, as it deems essential to calculate the execution time of the algorithm soon. */                                                                   
		/* NOTE: The time to be measured here marks the ending time of the algorithm. The algorithm ends
		   after the output, K-mer distribution, is displayed.                                           */
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
