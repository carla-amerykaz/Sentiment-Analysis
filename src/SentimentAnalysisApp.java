import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.*;
/**
 *
 @author metsis
 @author tesic
 @author wen
 */
public class SentimentAnalysisApp {
	static Scanner s = new Scanner(System.in);
	
    
    
    
	public static void main(String [] args) throws IOException{
		
        ReviewHandler rh = new ReviewHandler();
        
        rh.loadSerialDB();
        
        
        int again = 1;
    	int count = 0;
    	
        while(again ==1)
    	   {
    		   printMenu();
    		   System.out.print("Enter choice: ");
    		   int choice = s.nextInt();
    		   while (choice > 4 ||choice < 0)
    		   {
    			   System.out.print("Not an option, choose 0-4: " );
    			   choice = s.nextInt();
    		   }
    		   s.nextLine();
    		   
    			   switch(choice)
                   {
                   case 0:
                	   System.out.println("Correct predicted reviews: " + count);
                	   rh.saveSerialDB();
                	   System.exit(0);
                	   
                	   
                	   break;
                   case 1:
                	  try {
                		  System.out.print("Enter folder or filepath: ");
                    	  
                    	  String t = s.nextLine();
                    	   
                    	   System.out.print("Enter real class of review: ");
                    	   int real = Integer.parseInt(s.nextLine());
                    	   while (real >2 || real < 0)
                    	   {
                    		   System.out.print("Enter 0, 1, 0r 2: ");
                    		   real = Integer.parseInt(s.nextLine());
                    	   }
                    	   count = rh.loadReviews(t, real);

                    	   rh.saveSerialDB();
                    	   
                    	   
                	  } catch (FileNotFoundException e)
                	  {
                		  System.out.println(e);
                	  }
                	  
                	   break;
                   case 2:
                	   	System.out.print("Enter id to delete review: ");
                	   	rh.deleteReview(s.nextInt());
                	   	rh.saveSerialDB();

                	   	break;
                   case 3:
                	   System.out.print("Enter the id or substring: ");
                	   
                	   if (s.hasNextInt())
                	   {
                		   int i = s.nextInt();
                		   MovieReview temp = rh.searchById(i);
                		   
                		   if (temp != null)
                    	   {
                        	   System.out.println(rh.searchById(i));

                    	   }

                    	   else
                    		   System.out.println("No such review exists");
                	   }
                	   else
                	   {
                   	    List<MovieReview> r = rh.searchBySubstring(s.nextLine());
                   	    for (MovieReview review : r)
                   	    {
                   	    	System.out.println(review);
                   	    }
                	   }
                	   
                	   	break;
                   
                   case 4:
                	    rh.saveSerialDB();
                	   	System.out.println(rh);
                	   	break;
                   
                	  
                   }
    		   
     
    	   }
        
}//end of main



       
       
    public static void printMenu()
    {
    	System.out.println("Menu: ");
        System.out.println("0. Exit Program");
        System.out.println("1. Load new movie review collection");
        System.out.println("2. Delete movie review from database with given ID");
        System.out.println("3 Search movie reviews in database by id or matching substring");

    }
}