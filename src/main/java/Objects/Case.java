package Objects;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import File_IO.FileAccess;

import java.io.*;

public class Case implements Serializable{
  static final long serialVersionUID = 10;
  HashMap<String, Integer> occurrences = new HashMap<String, Integer>();
  boolean actualCategoriesKnown; // true if this is part of a training set we know the categorization of already, false otherwise
  int caseNumber;
  String caseOwner;
  String originalCategory;
  String caseRequestor;
  String dateRequested;
  String description;
  String[] tokenizedDescription;
  String category;
  static int numKeywords = 168; // MUST BE UPDATED IF INPUTS IS UPDATED

  /*
   * (Default constructor to create a case and initialize occurrences counts)
   */
  public Case(){
  	getInputs();
  }
  /*
   * (Constructor when all variables are known)
   */
  public Case(int caseNumber, String caseOwner,String caseRequestor,

    String dateRequested, String description,

    String[] tokenizedDescription, String category){
    this.caseNumber = caseNumber;
    this.caseOwner = caseOwner;
    this.caseRequestor = caseRequestor;
    this.dateRequested = dateRequested;
    this.description = description;
    this.tokenizedDescription = tokenizedDescription;
    this.category = category;
    this.originalCategory = category;
    getInputs();
  }
  
  private void getInputs() {
	  try {
		  File file = FileAccess.getFile("/inputs.txt");
		  Scanner sc = new Scanner(file);
		  while(sc.hasNextLine()) {
			  occurrences.put(sc.nextLine(), 0);
		  }
		  sc.close();
	  } catch (FileNotFoundException e) {
		  e.printStackTrace();
	  }
  }

  //setters for case variables
  public void setActualCategoriesKnown(boolean known){
    this.actualCategoriesKnown = known;
  }
  public void setCaseNumber(int caseNumber){
    this.caseNumber = caseNumber;
  }
  public void setCaseOwner(String caseOwner){
    this.caseOwner = caseOwner;
  }
  public void setCaseRequestor(String caseRequestor){
    this.caseRequestor = caseRequestor;
  }
  public void setDateRequested(String dateRequested){
    this.dateRequested = dateRequested;
  }

  public void setDescription(String description){
    this.description = description;
  }
  public void setTokenizedDescription(String[] tokenizedDescription){
    this.tokenizedDescription = tokenizedDescription;
  }
  public void setCategory(String category){
    this.category = category;
  }
  
  public void setOriginalCategory(String originalCategory){
	  this.originalCategory = originalCategory;
  }

  public boolean getActualCategoriesKnown(){
    return this.actualCategoriesKnown;
  }
  public int getCaseNumber(){
    return this.caseNumber;
  }
  public String getCaseOwner(){
    return this.caseOwner;
  }
  public String getCaseRequestor(){
    return this.caseRequestor;
  }
  public String getDateRequested(){
    return this.dateRequested;
  }
  
  public String getDescription(){
    return this.description;
  }
  public String[] getTokenizedDescription(){
    return this.tokenizedDescription;
  }
  public String getCategory(){
    return this.category;
  }

  public String getOriginalCategory(){
	    return this.originalCategory;
  }
  
  /*
   * (iterates through tokenized description to count occurences of keywords)
   * @param (tokenizedDescription) Contains a tokenized description that is easily iterable)
   */
  public void findOccurrences(String[] tokenizedDescription){
    for(int i = 0; i < tokenizedDescription.length; i++){
      if(occurrences.containsKey(tokenizedDescription[i])){
        occurrences.put(tokenizedDescription[i], occurrences.get(tokenizedDescription[i]) + 1);
      }
    }
  }
  // Function to return all of the data from the case as input to neural network
  public double[] getAsInput() {
	  double[] inputs = new double[numKeywords];
	  for (Map.Entry<String, Integer> entry : occurrences.entrySet()) {
          occurrences.put(entry.getKey(), 0);
      }
	  int i = 0;
    findOccurrences(tokenizedDescription);
    Iterator<Map.Entry<String, Integer>> it = occurrences.entrySet().iterator();
    while(it.hasNext()) {
    	@SuppressWarnings("unchecked")
		Map.Entry<String, Integer> pair = (Map.Entry<String, Integer>)it.next();
    	inputs[i] = (double) pair.getValue();
    	i++;
    }
    return inputs;
  }


  // Function to return correct labels of the data from the case
  public double[] getLabelsIfKnown() {
    double[] labelMatch = new double[19];
    for(int i = 0; i < labelMatch.length; i++){
      labelMatch[i] = 0;
    }
    if (actualCategoriesKnown) {
      switch(category){
        case "Office 365 Email and Calendar":
          labelMatch[0] = 1;
          break;
        case "Shared/Network Drive":
          labelMatch[1] = 1;
          break;
        case "Microsoft Office":
          labelMatch[2] = 1;
          break;
        case "Computer Support Settings":
          labelMatch[3] = 1;
          break;
        case "Purchase Request":
          labelMatch[4] = 1;
          break;
        case "Onsite Assistance":
          labelMatch[5] = 1;
          break;
        case "Wisclists":
          labelMatch[6] = 1;
          break;
        case "Audio/Visual Support":
          labelMatch[7] = 1;
          break;
        case "Device Repair":
          labelMatch[8] = 1;
          break;
        case "Service Account Creation (Email)":
          labelMatch[9] = 1;
          break;
        case "Admin Account/Password":
          labelMatch[10] = 1;
          break;
        case "Login Issues":
          labelMatch[11] = 1;
          break;
        case "Virus/Malware":
          labelMatch[12] = 1;
          break;
        case "Printer Support":
          labelMatch[13] = 1;
          break;
        case "Multi Factor Authentication":
          labelMatch[14] = 1;
          break;
        case "Room Access":
          labelMatch[15] = 1;
          break;
        case "Network Connectivity":
          labelMatch[16] = 1;
          break;
        case "Adobe Suite":
          labelMatch[17] = 1;
          break;
        case "Loaner Request (Computer/Mifi)":
          labelMatch[18] = 1;
          break;
        default:
          break;
      }
      return labelMatch;
      // return categories as double array with a 1 if it matches and a 0 if it doesn't
    }
    return null;
  }
}


//package Objects;
//
//import Objects.*;
//import File_IO.*;
//import java.util.Scanner;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.Map.Entry;
//import java.io.*;
//
//public class Case implements Serializable{
//  static final long serialVersionUID = 10;
//  HashMap<String, Integer> occurrences = new HashMap<String, Integer>();
//  boolean actualCategoriesKnown; // true if this is part of a training set we know the categorization of already, false otherwise
//  int caseNumber;
//  String caseOwner;
//  String caseRequestor;
//  String dateRequested;
//  String description;
//  String[] tokenizedDescription;
//  String category;
//  static int numKeywords = 144;
//
//  /*
//   * (Default constructor to create a case and initialize occurrences counts)
//   */
//  public Case(){
//  	occurrences.put("365", 0);
//    occurrences.put("access", 0);
//    occurrences.put("account", 0);
//    occurrences.put("acrobat", 0);
//    occurrences.put("admin", 0);
//    occurrences.put("administrative", 0);
//    occurrences.put("administrator", 0);
//    occurrences.put("adobe", 0);
//    occurrences.put("alf", 0);
//    occurrences.put("audio", 0);
//    occurrences.put("audition", 0);
//    occurrences.put("authentication", 0);
//    occurrences.put("based", 0);
//    occurrences.put("borrow", 0);
//    occurrences.put("borrowed", 0);
//    occurrences.put("bridge", 0);
//    occurrences.put("broken", 0);
//    occurrences.put("calendar", 0);
//    occurrences.put("camera", 0);
//    occurrences.put("classroom", 0);
//    occurrences.put("click", 0);
//    occurrences.put("clicked", 0);
//    occurrences.put("cloud", 0);
//    occurrences.put("come", 0);
//    occurrences.put("computer", 0);
//    occurrences.put("creation", 0);
//    occurrences.put("creative", 0);
//    occurrences.put("credentials", 0);
//    occurrences.put("day", 0);
//    occurrences.put("dreamweaver", 0);
//    occurrences.put("drive", 0);
//    occurrences.put("duo", 0);
//    occurrences.put("duplex", 0);
//    occurrences.put("email", 0);
//    occurrences.put("employee", 0);
//    occurrences.put("ethernet", 0);
//    occurrences.put("excel", 0);
//    occurrences.put("factor", 0);
//    occurrences.put("fax", 0);
//    occurrences.put("firewall", 0);
//    occurrences.put("fix", 0);
//    occurrences.put("fixed", 0);
//    occurrences.put("floor", 0);
//    occurrences.put("floorplan", 0);
//    occurrences.put("fob", 0);
//    occurrences.put("format", 0);
//    occurrences.put("formatted", 0);
//    occurrences.put("google", 0);
//    occurrences.put("hdmi", 0);
//    occurrences.put("illustrator", 0);
//    occurrences.put("imaged", 0);
//    occurrences.put("in", 0);
//    occurrences.put("incorrect", 0);
//    occurrences.put("indesign", 0);
//    occurrences.put("infected", 0);
//    occurrences.put("internet", 0);
//    occurrences.put("issues", 0);
//    occurrences.put("lan", 0);
//    occurrences.put("license", 0);
//    occurrences.put("lightroom", 0);
//    occurrences.put("link", 0);
//    occurrences.put("list", 0);
//    occurrences.put("lists", 0);
//    occurrences.put("loaner", 0);
//    occurrences.put("location", 0);
//    occurrences.put("log", 0);
//    occurrences.put("login", 0);
//    occurrences.put("malware", 0);
//    occurrences.put("mass", 0);
//    occurrences.put("member", 0);
//    occurrences.put("mfa", 0);
//    occurrences.put("mifi", 0);
//    occurrences.put("monitor", 0);
//    occurrences.put("mount", 0);
//    occurrences.put("multi", 0);
//    occurrences.put("multi-factor", 0);
//    occurrences.put("network", 0);
//    occurrences.put("new", 0);
//    occurrences.put("o365", 0);
//    occurrences.put("office", 0);
//    occurrences.put("onenote", 0);
//    occurrences.put("onsite", 0);
//    occurrences.put("otp", 0);
//    occurrences.put("outage", 0);
//    occurrences.put("outages", 0);
//    occurrences.put("outlook", 0);
//    occurrences.put("password", 0);
//    occurrences.put("pdf", 0);
//    occurrences.put("permission", 0);
//    occurrences.put("permissions", 0);
//    occurrences.put("person", 0);
//    occurrences.put("phishing", 0);
//    occurrences.put("photoshop", 0);
//    occurrences.put("physical", 0);
//    occurrences.put("plan", 0);
//    occurrences.put("powerpoint", 0);
//    occurrences.put("premiere", 0);
//    occurrences.put("print", 0);
//    occurrences.put("printer", 0);
//    occurrences.put("printers", 0);
//    occurrences.put("printing", 0);
//    occurrences.put("projector", 0);
//    occurrences.put("purchase", 0);
//    occurrences.put("real", 0);
//    occurrences.put("realtime", 0);
//    occurrences.put("reimaged", 0);
//    occurrences.put("re-imaged", 0);
//    occurrences.put("rent", 0);
//    occurrences.put("rental", 0);
//    occurrences.put("repair", 0);
//    occurrences.put("request", 0);
//    occurrences.put("requested", 0);
//    occurrences.put("requesting", 0);
//    occurrences.put("requesting", 0);
//    occurrences.put("role", 0);
//    occurrences.put("room", 0);
//    occurrences.put("scam", 0);
//    occurrences.put("scan", 0);
//    occurrences.put("server", 0);
//    occurrences.put("service", 0);
//    occurrences.put("setup", 0);
//    occurrences.put("shared", 0);
//    occurrences.put("site", 0);
//    occurrences.put("spare", 0);
//    occurrences.put("staff", 0);
//    occurrences.put("suite", 0);
//    occurrences.put("time", 0);
//    occurrences.put("time", 0);
//    occurrences.put("token", 0);
//    occurrences.put("unable", 0);
//    occurrences.put("username", 0);
//    occurrences.put("verizon", 0);
//    occurrences.put("virus", 0);
//    occurrences.put("voicemail", 0);
//    occurrences.put("vpn", 0);
//    occurrences.put("web chat", 0);
//    occurrences.put("webchat", 0);
//    occurrences.put("week", 0);
//    occurrences.put("wifi", 0);
//    occurrences.put("wiped", 0);
//    occurrences.put("wisclist", 0);
//    occurrences.put("wlan", 0);
//    occurrences.put("word", 0);
//	occurrences.put("wrong", 0);
//  }
//  /*
//   * (Constructor when all variables are known)
//   */
//  public Case(int caseNumber, String caseOwner,String caseRequestor,
//
//  String dateRequested, String description,
//
//  String[] tokenizedDescription, String category){
//    this.caseNumber = caseNumber;
//    this.caseOwner = caseOwner;
//    this.caseRequestor = caseRequestor;
//    this.dateRequested = dateRequested;
//    this.description = description;
//    this.tokenizedDescription = tokenizedDescription;
//    this.category = category;
//    occurrences.put("365", 0);
//    occurrences.put("access", 0);
//    occurrences.put("account", 0);
//    occurrences.put("acrobat", 0);
//    occurrences.put("admin", 0);
//    occurrences.put("administrative", 0);
//    occurrences.put("administrator", 0);
//    occurrences.put("adobe", 0);
//    occurrences.put("alf", 0);
//    occurrences.put("audio", 0);
//    occurrences.put("audition", 0);
//    occurrences.put("authentication", 0);
//    occurrences.put("based", 0);
//    occurrences.put("borrow", 0);
//    occurrences.put("borrowed", 0);
//    occurrences.put("bridge", 0);
//    occurrences.put("broken", 0);
//    occurrences.put("calendar", 0);
//    occurrences.put("camera", 0);
//    occurrences.put("classroom", 0);
//    occurrences.put("click", 0);
//    occurrences.put("clicked", 0);
//    occurrences.put("cloud", 0);
//    occurrences.put("come", 0);
//    occurrences.put("computer", 0);
//    occurrences.put("creation", 0);
//    occurrences.put("creative", 0);
//    occurrences.put("credentials", 0);
//    occurrences.put("day", 0);
//    occurrences.put("dreamweaver", 0);
//    occurrences.put("drive", 0);
//    occurrences.put("duo", 0);
//    occurrences.put("duplex", 0);
//    occurrences.put("email", 0);
//    occurrences.put("employee", 0);
//    occurrences.put("ethernet", 0);
//    occurrences.put("excel", 0);
//    occurrences.put("factor", 0);
//    occurrences.put("fax", 0);
//    occurrences.put("firewall", 0);
//    occurrences.put("fix", 0);
//    occurrences.put("fixed", 0);
//    occurrences.put("floor", 0);
//    occurrences.put("floorplan", 0);
//    occurrences.put("fob", 0);
//    occurrences.put("format", 0);
//    occurrences.put("formatted", 0);
//    occurrences.put("google", 0);
//    occurrences.put("hdmi", 0);
//    occurrences.put("illustrator", 0);
//    occurrences.put("imaged", 0);
//    occurrences.put("in", 0);
//    occurrences.put("incorrect", 0);
//    occurrences.put("indesign", 0);
//    occurrences.put("infected", 0);
//    occurrences.put("internet", 0);
//    occurrences.put("issues", 0);
//    occurrences.put("lan", 0);
//    occurrences.put("license", 0);
//    occurrences.put("lightroom", 0);
//    occurrences.put("link", 0);
//    occurrences.put("list", 0);
//    occurrences.put("lists", 0);
//    occurrences.put("loaner", 0);
//    occurrences.put("location", 0);
//    occurrences.put("log", 0);
//    occurrences.put("login", 0);
//    occurrences.put("malware", 0);
//    occurrences.put("mass", 0);
//    occurrences.put("member", 0);
//    occurrences.put("mfa", 0);
//    occurrences.put("mifi", 0);
//    occurrences.put("monitor", 0);
//    occurrences.put("mount", 0);
//    occurrences.put("multi", 0);
//    occurrences.put("multi-factor", 0);
//    occurrences.put("network", 0);
//    occurrences.put("new", 0);
//    occurrences.put("o365", 0);
//    occurrences.put("office", 0);
//    occurrences.put("onenote", 0);
//    occurrences.put("onsite", 0);
//    occurrences.put("otp", 0);
//    occurrences.put("outage", 0);
//    occurrences.put("outages", 0);
//    occurrences.put("outlook", 0);
//    occurrences.put("password", 0);
//    occurrences.put("pdf", 0);
//    occurrences.put("permission", 0);
//    occurrences.put("permissions", 0);
//    occurrences.put("person", 0);
//    occurrences.put("phishing", 0);
//    occurrences.put("photoshop", 0);
//    occurrences.put("physical", 0);
//    occurrences.put("plan", 0);
//    occurrences.put("powerpoint", 0);
//    occurrences.put("premiere", 0);
//    occurrences.put("print", 0);
//    occurrences.put("printer", 0);
//    occurrences.put("printers", 0);
//    occurrences.put("printing", 0);
//    occurrences.put("projector", 0);
//    occurrences.put("purchase", 0);
//    occurrences.put("real", 0);
//    occurrences.put("realtime", 0);
//    occurrences.put("reimaged", 0);
//    occurrences.put("re-imaged", 0);
//    occurrences.put("rent", 0);
//    occurrences.put("rental", 0);
//    occurrences.put("repair", 0);
//    occurrences.put("request", 0);
//    occurrences.put("requested", 0);
//    occurrences.put("requesting", 0);
//    occurrences.put("requesting", 0);
//    occurrences.put("role", 0);
//    occurrences.put("room", 0);
//    occurrences.put("scam", 0);
//    occurrences.put("scan", 0);
//    occurrences.put("server", 0);
//    occurrences.put("service", 0);
//    occurrences.put("setup", 0);
//    occurrences.put("shared", 0);
//    occurrences.put("site", 0);
//    occurrences.put("spare", 0);
//    occurrences.put("staff", 0);
//    occurrences.put("suite", 0);
//    occurrences.put("time", 0);
//    occurrences.put("time", 0);
//    occurrences.put("token", 0);
//    occurrences.put("unable", 0);
//    occurrences.put("username", 0);
//    occurrences.put("verizon", 0);
//    occurrences.put("virus", 0);
//    occurrences.put("voicemail", 0);
//    occurrences.put("vpn", 0);
//    occurrences.put("web chat", 0);
//    occurrences.put("webchat", 0);
//    occurrences.put("week", 0);
//    occurrences.put("wifi", 0);
//    occurrences.put("wiped", 0);
//    occurrences.put("wisclist", 0);
//    occurrences.put("wlan", 0);
//    occurrences.put("word", 0);
//    occurrences.put("wrong", 0);
//
//  }
//
//  //setters for case variables
//  public void setActualCategoriesKnown(boolean known){
//    this.actualCategoriesKnown = known;
//  }
//  public void setCaseNumber(int caseNumber){
//    this.caseNumber = caseNumber;
//  }
//  public void setCaseOwner(String caseOwner){
//    this.caseOwner = caseOwner;
//  }
//  public void setCaseRequestor(String caseRequestor){
//    this.caseRequestor = caseRequestor;
//  }
//  public void setDateRequested(String dateRequested){
//    this.dateRequested = dateRequested;
//  }
//
//  public void setDescription(String description){
//    this.description = description;
//  }
//  public void setTokenizedDescription(String[] tokenizedDescription){
//    this.tokenizedDescription = tokenizedDescription;
//  }
//  public void setCategory(String category){
//    this.category = category;
//  }
//
//  public boolean getActualCategoriesKnown(){
//    return this.actualCategoriesKnown;
//  }
//  public int getCaseNumber(){
//    return this.caseNumber;
//  }
//  public String getCaseOwner(){
//    return this.caseOwner;
//  }
//  public String getCaseRequestor(){
//    return this.caseRequestor;
//  }
//  public String getDateRequested(){
//    return this.dateRequested;
//  }
//  public String getDescription(){
//    return this.description;
//  }
//  public String[] getTokenizedDescription(){
//    return this.tokenizedDescription;
//  }
//  public String getCategory(){
//    return this.category;
//  }
//  
//  public static void loadText(String fileName, Map<String, Integer> hm) {
//	  hm = new HashMap<String, Integer>();
//	  
//	  File file = FileAccess.getFile(fileName);
//	  
//	  try {
//		  Scanner scanner = new Scanner(file);
//		  
//		  while(scanner.hasNextLine()) {
//			  hm.put(scanner.nextLine(), 0);
//		  }
//		  
//		  scanner.close();
//	  } catch (FileNotFoundException e) {
//	  }
//	  
//  }
//
//  /*
//   * (iterates through tokenized description to count occurences of keywords)
//   * @param (tokenizedDescription) Contains a tokenized description that is easily iterable)
//   */
//  public void findOccurrences(String[] tokenizedDescription){
//    for(int i = 0; i < tokenizedDescription.length; i++){
//      if(occurrences.containsKey(tokenizedDescription[i])){
//        occurrences.put(tokenizedDescription[i], occurrences.get(tokenizedDescription[i]) + 1);
//      }
//    }
//  }
//  // Function to return all of the data from the case as input to neural network
//  public double[] getAsInput() {
//	  double[] inputs = new double[numKeywords];
//	  for (Map.Entry<String, Integer> entry : occurrences.entrySet()) {
//          occurrences.put(entry.getKey(), 0);
//      }
//	  int i = 0;
//    findOccurrences(tokenizedDescription);
//    Iterator<Entry<String, Integer>> it = occurrences.entrySet().iterator();
//    while(it.hasNext()) {
//    	@SuppressWarnings("unchecked")
//		Map.Entry<String, Integer> pair = (Map.Entry<String, Integer>)it.next();
//    	inputs[i] = (double) pair.getValue();
//    	i++;
//    }
//    return inputs;
//  }
//
//
//  // Function to return correct labels of the data from the case
//  public double[] getLabelsIfKnown() {
//    double[] labelMatch = new double[19];
//    for(int i = 0; i < labelMatch.length; i++){
//      labelMatch[i] = 0;
//    }
//    if (actualCategoriesKnown) {
//      switch(category){
//        case "Office 365 Email and Calendar":
//          labelMatch[0] = 1;
//          break;
//        case "Shared/Network Drive":
//          labelMatch[1] = 1;
//          break;
//        case "Microsoft Office":
//          labelMatch[2] = 1;
//          break;
//        case "Computer Support Settings":
//          labelMatch[3] = 1;
//          break;
//        case "Purchase Request":
//          labelMatch[4] = 1;
//          break;
//        case "Onsite Assistance":
//          labelMatch[5] = 1;
//          break;
//        case "Wisclists":
//          labelMatch[6] = 1;
//          break;
//        case "Audio/Visual Support":
//          labelMatch[7] = 1;
//          break;
//        case "Device Repair":
//          labelMatch[8] = 1;
//          break;
//        case "Service Account Creation (Email)":
//          labelMatch[9] = 1;
//          break;
//        case "Admin Account/Password":
//          labelMatch[10] = 1;
//          break;
//        case "Login Issues":
//          labelMatch[11] = 1;
//          break;
//        case "Virus/Malware":
//          labelMatch[12] = 1;
//          break;
//        case "Printer Support":
//          labelMatch[13] = 1;
//          break;
//        case "Multi Factor Authentication":
//          labelMatch[14] = 1;
//          break;
//        case "Room Access":
//          labelMatch[15] = 1;
//          break;
//        case "Network Connectivity":
//          labelMatch[16] = 1;
//          break;
//        case "Adobe Suite":
//          labelMatch[17] = 1;
//          break;
//        case "Loaner Request (Computer/Mifi)":
//          labelMatch[18] = 1;
//          break;
//        default:
//          break;
//      }
//      return labelMatch;
//      // return categories as double array with a 1 if it matches and a 0 if it doesn't
//    }
//    return null;
//  }
//}

