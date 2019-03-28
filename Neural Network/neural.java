<<<<<<< HEAD
public class neural {

  // Holds weights (double)
  Cell[] firstLayer;
  Cell[] secondLayer;
  // Holds functions (math functions for RELU and Sigmoid)
  // Holds inputs (list of input variables)
  // Holds outputs/labels (list of output labels)


  // getters and setters for all the above


}
=======
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;

public class neural {

  // Holds weights (double) (Done in Cell.java)
  // Holds cells and connections (need new class/type) 
  
  //Hashmap to store our inputs. Each key will be an input word and each output will be the number of instances
  Map<String, Integer> inputs = new HashMap<String, Integer>();

  
  //TODO: write code to save/load serialized data for the neural network

  Cell[] firstLayer = new Cell[20];
  
  for(int i = 0; i < firstLayer.length(); i++){
    Cell[i].functionType = "ReLU";
  }
  
  Cell[] secondLayer = new Cell[20];
  
  for(int i = 0; i < firstLayer.length(); i++){
    Cell[i].functionType = "Sigmoid";
  }

  // Holds functions (math functions for RELU and Sigmoid) (done in Cell.java)
  

  // Holds inputs (list of input variables)

  // Holds outputs/labels (list of output labels)
  
  
  // getters and setters for all the above
  //
  public static void loadInputs{ 
    
  }
  
  public static void clearCounts{
    for(Map.Entry<String, HashMap> entry : selects.entrySet()) {
      entry.setValue(0);
    }
  }
}
>>>>>>> origin/dan
