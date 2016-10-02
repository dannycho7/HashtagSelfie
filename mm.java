
/**
 * Write a description of class mm here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class mm
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class mm
     */
    public mm()
    {
        // initialise instance variables
        x = 0;
    }
    

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void randomEvens()
    {
        int random = (int)(Math.random()*100);
        if(random%2==0)//means it's even
        {
            System.out.println(random);
        }
        else{
            System.out.println(random+1);
        }
        
    }
    
    public static void main(String[]args){
        for(int row = 10 ; row > 0 ; row--){
            for(int col=0; col<row; col++){
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
