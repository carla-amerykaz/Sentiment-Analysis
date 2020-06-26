//package assign2;

import java.util.*;
import java.io.*;
import java.util.List;
import java.util.Map.*;

public class ReviewHandler extends AbstractReviewHandler  {
	
	private Random randID;
		

    private String readFile(File f) throws FileNotFoundException //reads single file
    {
        StringBuilder sb = new StringBuilder();
        Scanner s = new Scanner(f);
        while (s.hasNextLine())
        {
            sb.append(s.nextLine());
        }
        s.close();
        return sb.toString();
    }
    
    //function to generate random ID
    private int generateID()
    {
    	int rand = randID.nextInt(10000);
    	Integer x = new Integer(rand);
    	if (!database.containsKey(x))
    	{
    		return rand;
    	}
    	return generateID();
    		
    }
    
    public void writeTo(String out) throws IOException
    {
    	FileWriter w= new FileWriter(out);
    	w.write(database.toString());
    	w.close();
    	
    }
    
    //print database
    public String toString()
    {
    	return database.toString();
    }
    //constructor 
    public ReviewHandler()
    {
        super();
        randID = new Random();
    }
    
    
    @Override
    //call readReview()
    public int loadReviews(String filePath, int realClass) throws IOException
    {
    	int countCorrect =0;
    	File f = new File(filePath);
    	MovieReview m = null;
    	if (f.isDirectory()) //if its directory 
    	{
    		File[] fArr = f.listFiles(); 
    		for (File x : fArr)
    		{
    			
    			m = readReview(x.getPath(), realClass);
    			Integer i = new Integer(m.getId());
    			database.put(i, m);
    			if (realClass == m.getPredictedPolarity()) 
    			{
    				countCorrect +=1;
    			}
    		}
    	}
    	else  //single file txt
    	{
    		
    		 m = readReview(filePath, realClass);
    		 Integer i = new Integer(m.getId());
    		 database.put(i,  m);
    		 if (realClass == m.getPredictedPolarity()) 
 			{
 				countCorrect +=1;
 			}
    	}
    	return countCorrect;
    		
    	

    }

    @Override
    //call readFile() 
    public MovieReview readReview(String reviewFilePath, int realClass) throws IOException 
    {
    	File f = new File(reviewFilePath);
    	String fileWords = readFile(f);
    	int id = generateID();
    	//if (realClass == 2)
    	MovieReview temp = new MovieReview(id, fileWords, realClass);
    	
    	int predictedPol = classifyReview(temp);
    	temp.setPredictedPolarity(predictedPol);
    	
    	return temp;
    }

    @Override
    public void deleteReview(int id)
    {
        Integer x = new Integer(id); // key to search by
        if (database.containsKey(x))
        {
            database.remove(x);
        }
        else
        {
            System.out.println("ID does not exist!");
        }

    }

   @Override
    public void loadSerialDB() throws IOException
 {
    	try {
    		File f = new File(DATA_FILE_NAME);
    		 ObjectInputStream obj = new ObjectInputStream(new FileInputStream(f));
    	     
    	     database = (HashMap<Integer, MovieReview>) obj.readObject();
    	     obj.close();
    	     
    	     
    	}catch (ClassNotFoundException e) {
    		System.out.println(e.getMessage());
    	}
    	 catch(FileNotFoundException fe){

    	     System.out.println("File not found ");

    	    } catch (EOFException e) {

    	       
    	       } 
    	
   }

   
   @Override
    public MovieReview searchById(int id) {
    	Integer x = new Integer(id);
    	
    	if (database.containsKey(x))
    	{
    		return database.get(x);
    	}
    	
        return null;
    }

    @Override
    public List<MovieReview> searchBySubstring(String substring) {
    	List<MovieReview> movieList = new ArrayList<MovieReview>();
    	for (Entry<Integer, MovieReview> entry: database.entrySet())
    	{
    		String text = entry.getValue().getText();
    		if (text.contains(substring))
    		{
    			movieList.add(entry.getValue());
    		}
    	}
    	
        return movieList;
    }

}
