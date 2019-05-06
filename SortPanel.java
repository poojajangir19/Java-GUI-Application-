//importing classes awt,swing
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
//class SortPanel extends Jpanel
public class SortPanel extends JPanel
{
  int sortingSpeed = 500; //declaring the speed for sorting
  private JButton populate; //declaring all buttons                                           
  private JButton resume;
  private JButton stop;
  private JButton sort;
  private JButton pause;
  private JButton ascending;
  private JButton descending;
  private String[] speedArray = { "Slow", "Medium", "Fast"};//declaring speed array of string type
  //declaring sortingAlgorithms array of String type
  private String[] sortingAlgorithms = {"Select Sort","Shell Sort","Selection Sort","Insertion Sort","Bubble Sort"}; 
  //declaring a combobox and passing speedArray as argument
  private JComboBox sorting_speed = new JComboBox(speedArray);
  //declaring a combobox to display all the sorting algorithms
  private JComboBox<String> sortingCombo;
  //declaring Animation Panel
  private SortAnimationPanel animationPanel;
  //creating a button group object for handling all the buttons
  final ButtonGroup buttonGroup=new ButtonGroup();   
  //creating a gridbaglayout object 
  GridBagLayout layout1 = new GridBagLayout();   
  //creating a gridbag object
  GridBagConstraints gridBag = new GridBagConstraints(); 
  //variable to check condition
  public boolean subPanel = true;
  //string type variable declared to store the algorithm selected
  public String algorithmType;
  //constrcutor for sortPanel class
  SortPanel()
  {
   super();  //calls super class constructor                                           
   setLayout(new GridLayout(1,2)); //sets layout
   animationPanel = new SortAnimationPanel(); //animationPanel object created            
   populate = new JButton("populate Array");  //passing text to the populate button                
   sort = new JButton("Sort");          //passing text to the sort button                     
   resume = new JButton("Resume");      //passing text to the resume button                   
   pause = new JButton("Pause");        //passing text to the pause button                 
   stop = new JButton("Stop");           //passing text to the stop button               
   ascending = new JButton("Ascending");   //passing text to the ascending button          
   descending = new JButton("Descending"); //passing text to the descending button 
   //adding all the buttons to the buttongroup
   buttonGroup.add(populate);
   buttonGroup.add(sort);
   buttonGroup.add(resume);
   buttonGroup.add(pause);
   buttonGroup.add(stop);      
   buttonGroup.add(ascending);
   buttonGroup.add(descending);
    
   //Declaring and creating objects for all the classes to populate array, sortevent and controlevents 
    PopulateArray a = new PopulateArray();    
    SortEvents b = new SortEvents();
    ControlEvents c = new ControlEvents();
    //adding action listeners to all the buttons
    populate.addActionListener(a);                              
    sort.addActionListener(b);                              
    ascending.addActionListener(b);                          
    descending.addActionListener(b);                        
    pause.addActionListener(c);                            
    resume.addActionListener(c);                          
    stop.addActionListener(c); 
    //creating an object for the sorting ComboBox
    sortingCombo = new JComboBox(sortingAlgorithms); 
    //creating object for Item Listeners
    MyItemListener item = new MyItemListener();
    sortingCombo.addItemListener(item);
    //creating mainPanel and setting the grid bag layout and adding the elements to the panel
    this.setLayout(layout1);                               
    this.setVisible(true);
    
    gridBag.fill = GridBagConstraints.BOTH;                     
    gridBag.gridx = 0;
    gridBag.gridy = 0 ;
    gridBag.gridheight = 4;
    gridBag.gridwidth = 4;
    this.add(animationPanel, gridBag); //adding animationPanel
       
    gridBag.gridx = 0;
    gridBag.gridy = 4;  
    gridBag.gridwidth = 1;
    this.add(populate,gridBag); //adding populate 
     
    gridBag.gridx = 1;
    gridBag.gridy = 4;    
    gridBag.gridwidth = 1;
    this.add(sort, gridBag); //adding gridbag
        
     gridBag.gridx = 2;
     gridBag.gridy = 4;
     gridBag.gridheight = 1;
     gridBag.gridwidth = 1;
     this.add(sortingCombo, gridBag); //adding sorting combo
    
     gridBag.gridx = 3;
     gridBag.gridy = 4;  
     gridBag.gridwidth = 1;
     this.add(stop,gridBag); //adding stop
    
    gridBag.gridx = 0;
    gridBag.gridy = 8;  
    gridBag.gridwidth = 1;
    this.add(ascending,gridBag); //adding ascending
    
    gridBag.gridx = 1;
    gridBag.gridy = 8;  
    gridBag.gridwidth = 1;
    this.add(descending,gridBag); //adding descending
    
    gridBag.gridx = 2;
    gridBag.gridy = 8;  
    gridBag.gridwidth = 1;
    this.add(pause,gridBag);  //adding pause   
    
    gridBag.gridx = 3 ;
    gridBag.gridy = 8;  
    gridBag.gridwidth = 1;
    this.add(resume,gridBag);  //adding resume
    
    JPanel controlsPanel = new JPanel(); //creating a JPanel
    
    populate.setEnabled(true);//setting populate enabled
    sort.setEnabled(false); //setting sort false
    resume.setEnabled(false);//setting resume false
    stop.setEnabled(false);//setting stop false
    pause.setEnabled(false);
    ascending.setEnabled(false);
    descending.setEnabled(false);
    sortingCombo.setEnabled(false);
    controlsPanel.add(sorting_speed);
    this.add(controlsPanel);
  }
public class SortAnimationPanel extends JPanel implements Runnable //class SortAnimationPanel extends Runnable
{
  private int intArray[]; //declaring integer type array
  private String comboAlgorithm; //declaring string type for algorithm value
  private Thread thread; //declaring thread type variable
  private boolean subPanel = true;//declaring
  private volatile boolean interrupted = false;
  private volatile boolean onHold = false;
  private final Object lock = new Object();
  SortAnimationPanel()//constructor of SortAnimationPanel
  {
    super();        //super class constructor                                                
    super.setPreferredSize(new Dimension(500,500)); //setting the size
    super.setVisible(true);
  }
  public void populateArr() //populate array method to populate the array
  {  
    Random random = new Random();  //random class object                                 
    intArray = new int[500];  //integer array of size 500 declared                         
    random.setSeed(System.currentTimeMillis());//setting the object for random class
    for(int i = 0; i < 500;i++)                        
    {
      intArray[i] = random.nextInt(500 -1) + 1; //filling the array
    }
    repaint();                                               
  }
  @Override
    protected void paintComponent(Graphics g) //painting the components with a color and setting the height and width
     {
       int length = this.getWidth();
       int height = this.getHeight();
       super.paintComponent(g);                               
       g.setColor(Color.WHITE);
       repaint();   //invoked to do drawing
       if(intArray == null) //if the array is not null then it paints
       {
         return;   
       }
        for(int i = 0;i <length ;i++) // if the lines are painted blue            
        {
            g.setColor(Color.BLUE);
            g.drawLine(i,intArray[i],i,height);
        }
    }
  public void intializeThread(String algorithm)//Initializing the thread
  {
      thread = new Thread(this);   //creating thread object                               
      thread.start();          //staring the thread                                    
      comboAlgorithm = algorithm;
      setThread();                                       
  }
  public void setThread() //function to set the thread
  {
    synchronized(lock)//synschronises thread
    {
      try
      {
        while(onHold) //while the thread is suspended
        {
         lock.wait();     //calls wait method
        }
        if(interrupted)    //if the thread is paused
        {
         lock.wait();     //calls wait method
        }
      } 
       catch(InterruptedException e) 
       {
         System.out.println("Exception occured" + e);
       }
      }
        
    }  
    public void ifPauseOrNot() //method for the thread is paused or not
     {
       interrupted = true;  //pauses the thread
     }
     public void threadResume()//resumes thread
     {
       synchronized(lock) //synchronises thread
       {
         try
         {
         interrupted = false; //unpauses the thread
         lock.notifyAll();       //calls notifyAll method           
         }
         catch(Exception e)
         {
         System.out.println("Exception occured" + e);
         }
       }
     }
  public void run() //method to run the thread
  {     
    String speed_Selected = sorting_speed.getSelectedItem().toString(); //string type variable
    if(speed_Selected == "Slow")//checks if the speed selected is slow  
   {
     sortingSpeed = 5000; //assigns speed
   }
   if(speed_Selected == "Medium")//checks if speed is medium
   {
      sortingSpeed = 1000; //assigns speed
   }
   if(speed_Selected == "Fast")//checks if speed is fast
   {
      sortingSpeed = 100;//assigns speed
   }
   if(comboAlgorithm =="Shell Sort") //if value selected is shell sort
   {
     populate.setEnabled(false);//sets the buttons
     sort.setEnabled(false); 
     this.shellSort(intArray);//calls method
     populate.setEnabled(true);
     sort.setEnabled(true);
   }
    if(comboAlgorithm =="Selection Sort") //if value selected is selection sort
    {
      populate.setEnabled(false);
      sort.setEnabled(false);
      this.selectionSort(intArray);//calls method
      populate.setEnabled(true);
      sort.setEnabled(true);
    }
    if(comboAlgorithm == "Insertion Sort") //if value selected is insertion sort
    {
      populate.setEnabled(false);
      sort.setEnabled(false); 
      this.insertionSort(intArray);//calls method
      populate.setEnabled(true);
      sort.setEnabled(true);
    }
    if(comboAlgorithm == "Bubble Sort")//if value selected is bubble sort
    {
      populate.setEnabled(false);
      sort.setEnabled(false); 
      this.bubbleSort(intArray);//calls method
      populate.setEnabled(true);
      sort.setEnabled(true);
    }
  }
  //selection sort method with the integer array as return value
  public int[] selectionSort(int[] intArray)
     {
       try
       {
         int arrayLength = intArray.length - 1; //declaring variables
         int arrayLen = intArray.length;
         int i,j, temp;
         for (i = 0; i < arrayLength; i++) //looping to sort elements in the array                
          {
            int position = i;
            for (j = i + 1; j < arrayLen; j++)
            {
              if(!subPanel)                                         
            {
                if (intArray[j] < intArray[position])//checks if descending or ascending order
                {
                    position = j;
                }
            }
            else
            {
              if (intArray[j] > intArray[position])    
              {
                    position = j;
              }
            }
         }  
            //swapping elements as per the algorithm
            temp = intArray[position]; 
            intArray[position] = intArray[i];
            intArray[i] = temp;   
            //sets the speed as selected
            Thread.sleep(sortingSpeed);
            //draws the sort
            repaint();
            setThread();//executes the thread and status 
        }
         }
         catch(Exception e)
         {
           System.out.println("Exception occured " + e);
         }
        return intArray;
    }
  //insertion sort method to sort the elements using insertion algorithm
    public void insertionSort(int[] intArray) 
    {
      int i,j,k,length;//declaring variables
      length = intArray.length;
      try
      { 
        for (i = 1; i < length; i++) 
        {
          k = intArray[i];
          j = i;
          Thread.sleep(sortingSpeed); 
         if(!subPanel)   //checks for ascending or descding order                                 
         {
            while (j > 0 && (intArray[j - 1])> k) 
            {
              intArray[j] = intArray[j - 1];  //swapping the elements                                
              j = j-1;
              repaint();   //draws the sort                                   
              setThread();
            }
          }
         else                                             
         {
            while (j > 0 && (intArray[j - 1]) < k) 
            {
              intArray[j] = intArray[j - 1];
              j = j-1;
              repaint();                                    
              setThread();
            }
         }
          intArray[j] = k;
        }
      }
      catch(Exception e)
      {
        System.out.println("Exception occured" + e); 
      }
    }
    //bubblesort algorithm 
 public void bubbleSort(int [] intArray)
    {
      int length = intArray.length; //declaring variables
      int tempValue = 0;
      int i,j;
      try
         {
          for(i=0; i < length; i++)
           {
            Thread.sleep(sortingSpeed);                              
            setThread();
            for(j=1; j < (length-i); j++)
            { 
             if(!subPanel)                            
              {
               if(intArray[j-1] > intArray[j])//checks for ascending or descending order
                 {
                  tempValue = intArray[j-1];
                  intArray[j-1] = intArray[j];
                  intArray[j] = tempValue;
                  repaint(); //draws the GUI
                 }
              }
              else if(intArray[j-1] < intArray[j])         
                 {
                  tempValue = intArray[j-1];                                      
                  intArray[j-1] = intArray[j];
                  intArray[j] = tempValue;
                  repaint();//draws the GUI
                 }
               }
            }
          }
         catch(Exception e)
         {
           System.out.println("Exception occured" + e);
         }
    }
 //shell sort algorithm
public void shellSort(int[] intArray) 
   {
     int length, //declaring variables
     temp;
     int i,j;
     int value = intArray.length / 2;
     double newValue = 5.0/11;
     length = intArray.length;
     while (value > 0) 
     {
      for (i = value; i < length; i++)   //looping through the array         
       {
         j = i;
         temp = intArray[i];         //swapping elements as per the algorithm                           
         try
         {
         if(!subPanel)                                        
          {
             while ((value <= j) && intArray[j - value] > temp) //checks if ascending or descending order
             {
               intArray[j] = intArray[j - value];  
               j = j - value;
               Thread.sleep(sortingSpeed);
               setThread();
               repaint();
             }
         }
         else
         {
           while (value <= j && intArray[j - value] < temp)   
           {
             intArray[j] = intArray[j - value];
             j = j - value;
             Thread.sleep(sortingSpeed);
             setThread();
             repaint();
           }
        }
      }
         catch(Exception e)
         {
           System.out.println("Exception occured" + e);
         }
         intArray[j] = temp;
     }
       if (value == 2) //checks if the increment value has been incremented
       {
         value = 1;
       } 
       else 
       {
         value *= newValue;
       }
     }
 }
}
  public class MyItemListener implements ItemListener //event listener for MyItem lsiteneer class
  {
      public void itemStateChanged(ItemEvent event) //changing the item event 
      {
        sortingCombo.setEnabled(false);      
        ascending.setEnabled(true);
        descending.setEnabled(true);
      }
  }
  public class ControlEvents implements ActionListener//control event handlers
  {
    @Override
      public void actionPerformed(ActionEvent event)        
      {
         if(event.getSource() == pause)      //checks if the event is pause          
        {
          animationPanel.ifPauseOrNot();       //checks if the animation Panel is paused or not       
          pause.setEnabled(false);                 
          resume.setEnabled(true);
          animationPanel.interrupted = true;  
        }
        else if(event.getSource() == resume)    //checks if the event is resume     
        {
          resume.setEnabled(false);
          pause.setEnabled(true);
          animationPanel.threadResume();           
        }
        else if(event.getSource() == stop)       //checks if the event is stop  
        {
         animationPanel.intArray = null;   
         repaint();
         revalidate();
         resume.setEnabled(false);
         pause.setEnabled(true);
         sort.setEnabled(false);
         sortingCombo.setEnabled(false);
         pause.setEnabled(false);
         populate.setEnabled(true);
         stop.setEnabled(false);
        }
      }
  }
 public class PopulateArray implements ActionListener //action listeners
   {
     @Override
      public void actionPerformed(ActionEvent event) //populate array action event handler
      {
       populate.setEnabled(false);
       sortingCombo.setEnabled(true);
       animationPanel.populateArr();  
      }
   }
  public class SortEvents implements ActionListener //event handler for all the buttons
  {
    @Override
      public void actionPerformed(ActionEvent event)
      {
        if(event.getSource() == sort)               
        {
         descending.setEnabled(false);              
         ascending.setEnabled(false);
         pause.setEnabled(true);
         stop.setEnabled(true);         
         algorithmType = sortingCombo.getSelectedItem().toString();    
         animationPanel.intializeThread(algorithmType);                   
         populate.setEnabled(true);         
         revalidate();
         repaint();
        }
        else if(event.getSource() == ascending)         
        {
          descending.setEnabled(false);
          ascending.setEnabled(false);
          sort.setEnabled(true);
          subPanel = true;
          animationPanel.subPanel = true;           
        }
        else if(event.getSource() == descending)     
        {
          descending.setEnabled(false);              
          ascending.setEnabled(false);
          sort.setEnabled(true);
          animationPanel.subPanel = false;
        }
      }
   } 
}
  
