import java.awt.GridLayout;
import javax.swing.JFrame;
public class SortApplication extends JFrame //class SortApplication extending JFrame
{
SortPanel panelA = new SortPanel();//panels A and B
SortPanel panelB = new SortPanel();
public static void main( String[] args )//main method
{
 SortApplication sortApp = new SortApplication();//sortapplication class object
 sortApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 sortApp.setSize(1500,1000);//sets the size of the app
 sortApp.setLayout(new GridLayout(1,3));//sets the layout
 sortApp.setTitle("Concurrency & Animation");//sets title
 sortApp.setVisible(true);
 sortApp.setResizable(true);
}
public SortApplication()
 {
 this.add(panelA);//adds both the panels to the sortapplication
 this.add(panelB);
 }
}
