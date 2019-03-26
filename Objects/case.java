public class case{

    boolean actualCategoriesKnown; // true if this is part of a training set we know the categorization of already, false otherwise
  
    // Function to return all of the data from the case as input to neural network
    public int[] getAsInput() {
      return null;
    }
  
    // Function to return correct labels of the data from the case
    public double[] getLabelsIfKnown() {
      if (actualCategoriesKnown) {
        // return categories as double array with a 1 if it matches and a 0 if it doesn't
      }
      return null;
    }
  }