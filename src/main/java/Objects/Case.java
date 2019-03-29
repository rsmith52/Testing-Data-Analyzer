package Objects;

import java.util.HashMap;
import java.util.Map;
import java.io.*;

public class Case implements Serializable{
  HashMap<String, Integer> occurences = new HashMap<String, Integer>();
  boolean actualCategoriesKnown; // true if this is part of a training set we know the categorization of already, false otherwise
  int caseNumber;
  String caseOwner;
  String caseRequestor;
  int dateRequested;
  int dateCompleted;
  String description;
  String[] tokenizedDescription;
  String category;


  public Case(){
    occurences.put("office", 0);
    occurences.put("365", 0);
    occurences.put("o365", 0);
    occurences.put("email", 0);
    occurences.put("calendar", 0);
    occurences.put("outlook", 0);
    occurences.put("drive", 0);
    occurences.put("shared", 0);
    occurences.put("network", 0);
    occurences.put("shareddrive", 0);
    occurences.put("word", 0);
    occurences.put("powerpoint", 0);
    occurences.put("excel", 0);
    occurences.put("onenote", 0);
    occurences.put("outlook", 0);
    occurences.put("power", 0);
    occurences.put("monitor", 0);
    occurences.put("firewall", 0);
    occurences.put("setup", 0);
    occurences.put("new", 0);
    occurences.put("computer", 0);
    occurences.put("requested", 0);
    occurences.put("requesting", 0);
    occurences.put("request", 0);
    occurences.put("purchase", 0);
    occurences.put("alf", 0);
    occurences.put("person", 0);
    occurences.put("onsite", 0);
    occurences.put("site", 0);
    occurences.put("physical", 0);
    occurences.put("location", 0);
    occurences.put("come", 0);
    occurences.put("wisclist", 0);
    occurences.put("list", 0);
    occurences.put("lists", 0);
    occurences.put("mass", 0);
    occurences.put("realtime", 0);
    occurences.put("real", 0);
    occurences.put("time", 0);
    occurences.put("audio", 0);
    occurences.put("hdmi", 0);
    occurences.put("webchat", 0);
    occurences.put("web chat", 0);
    occurences.put("voicemail", 0);
    occurences.put("google", 0);
    occurences.put("camera", 0);
    occurences.put("projector", 0);
    occurences.put("repair", 0);
    occurences.put("broken", 0);
    occurences.put("imaged", 0);
    occurences.put("reimaged", 0);
    occurences.put("issues", 0);
    occurences.put("computer", 0);
    occurences.put("wiped", 0);
    occurences.put("formatted", 0);
    occurences.put("format", 0);
    occurences.put("re-imaged", 0);
    occurences.put("fix", 0);
    occurences.put("fixed", 0);
    occurences.put("role", 0);
    occurences.put("based", 0);
    occurences.put("employee", 0);
    occurences.put("staff", 0);
    occurences.put("member", 0);
    occurences.put("setup", 0);
    occurences.put("service", 0);
    occurences.put("account", 0);
    occurences.put("creation", 0);
    occurences.put("admin", 0);
    occurences.put("password", 0);
    occurences.put("log", 0);
    occurences.put("in", 0);
    occurences.put("login", 0);
    occurences.put("username", 0);
    occurences.put("incorrect", 0);
    occurences.put("wrong", 0);
    occurences.put("unable", 0);
    occurences.put("click", 0);
    occurences.put("link", 0);
    occurences.put("clicked", 0);
    occurences.put("phishing", 0);
    occurences.put("infected", 0);
    occurences.put("virus", 0);
    occurences.put("malware", 0);
    occurences.put("scam", 0);
    occurences.put("printer", 0);
    occurences.put("printers", 0);
    occurences.put("printing", 0);
    occurences.put("duplex", 0);
    occurences.put("scan", 0);
    occurences.put("fax", 0);
    occurences.put("print", 0);
    occurences.put("duo", 0);
    occurences.put("multi", 0);
    occurences.put("factor", 0);
    occurences.put("authentication", 0);
    occurences.put("mfa", 0);
    occurences.put("multi-factor", 0);
    occurences.put("token", 0);
    occurences.put("fob", 0);
    occurences.put("otp", 0);
    occurences.put("time", 0);
    occurences.put("room", 0);
    occurences.put("floorplan", 0);
    occurences.put("request", 0);
    occurences.put("requesting", 0);
    occurences.put("access", 0);
    occurences.put("classroom", 0);
    occurences.put("vpn", 0);
    occurences.put("wifi", 0);
    occurences.put("internet", 0);
    occurences.put("ethernet", 0);
    occurences.put("lan", 0);
    occurences.put("wlan", 0);
    occurences.put("outage", 0);
    occurences.put("outages", 0);
    occurences.put("server", 0);
    occurences.put("adobe", 0);
    occurences.put("photoshop", 0);
    occurences.put("indesign", 0);
    occurences.put("illustrator", 0);
    occurences.put("premiere", 0);
    occurences.put("premiere", 0);
    occurences.put("acrobat", 0);
    occurences.put("pdf", 0);
    occurences.put("creative", 0);
    occurences.put("cloud", 0);
    occurences.put("lightroom", 0);
    occurences.put("bridge", 0);
    occurences.put("audition", 0);
    occurences.put("dreamweaver", 0);
    occurences.put("license", 0);
    occurences.put("suite", 0);
    occurences.put("verizon", 0);
    occurences.put("mifi",0);
    occurences.put("rental", 0);
    occurences.put("loaner", 0);
    occurences.put("rent", 0);
    occurences.put("spare", 0);
    occurences.put("borrow", 0);
    occurences.put("borrowed", 0);
    occurences.put("request", 0);
    occurences.put("day", 0);
    occurences.put("week", 0);
  }
  public Case(int caseNumber, String caseOwner,String caseRequestor,
  int dateRequested, int dateCompleted, String description,
  String[] tokenizedDescription, String category){
    this.caseNumber = caseNumber;
    this.caseOwner = caseOwner;
    this.caseRequestor = caseRequestor;
    this.dateRequested = dateRequested;
    this.dateCompleted = dateCompleted;
    this.description = description;
    this.tokenizedDescription = tokenizedDescription;
    this.category = category;
    occurences.put("office", 0);
    occurences.put("365", 0);
    occurences.put("o365", 0);
    occurences.put("email", 0);
    occurences.put("calendar", 0);
    occurences.put("outlook", 0);
    occurences.put("drive", 0);
    occurences.put("shared", 0);
    occurences.put("network", 0);
    occurences.put("shareddrive", 0);
    occurences.put("word", 0);
    occurences.put("powerpoint", 0);
    occurences.put("excel", 0);
    occurences.put("onenote", 0);
    occurences.put("outlook", 0);
    occurences.put("power", 0);
    occurences.put("monitor", 0);
    occurences.put("computer", 0);
    occurences.put("firewall", 0);
    occurences.put("setup", 0);
    occurences.put("new", 0);
    occurences.put("computer", 0);
    occurences.put("requested", 0);
    occurences.put("requesting", 0);
    occurences.put("request", 0);
    occurences.put("purchase", 0);
    occurences.put("alf", 0);
    occurences.put("person", 0);
    occurences.put("onsite", 0);
    occurences.put("site", 0);
    occurences.put("physical", 0);
    occurences.put("location", 0);
    occurences.put("come", 0);
    occurences.put("wisclist", 0);
    occurences.put("list", 0);
    occurences.put("lists", 0);
    occurences.put("mass", 0);
    occurences.put("realtime", 0);
    occurences.put("real", 0);
    occurences.put("time", 0);
    occurences.put("audio", 0);
    occurences.put("hdmi", 0);
    occurences.put("webchat", 0);
    occurences.put("web chat", 0);
    occurences.put("voicemail", 0);
    occurences.put("google", 0);
    occurences.put("camera", 0);
    occurences.put("projector", 0);
    occurences.put("repair", 0);
    occurences.put("broken", 0);
    occurences.put("imaged", 0);
    occurences.put("reimaged", 0);
    occurences.put("issues", 0);
    occurences.put("computer", 0);
    occurences.put("wiped", 0);
    occurences.put("formatted", 0);
    occurences.put("format", 0);
    occurences.put("re-imaged", 0);
    occurences.put("fix", 0);
    occurences.put("fixed", 0);
    occurences.put("role", 0);
    occurences.put("based", 0);
    occurences.put("employee", 0);
    occurences.put("staff", 0);
    occurences.put("member", 0);
    occurences.put("setup", 0);
    occurences.put("service", 0);
    occurences.put("account", 0);
    occurences.put("creation", 0);
    occurences.put("admin", 0);
    occurences.put("password", 0);
    occurences.put("log", 0);
    occurences.put("in", 0);
    occurences.put("login", 0);
    occurences.put("username", 0);
    occurences.put("incorrect", 0);
    occurences.put("wrong", 0);
    occurences.put("unable", 0);
    occurences.put("click", 0);
    occurences.put("link", 0);
    occurences.put("clicked", 0);
    occurences.put("phishing", 0);
    occurences.put("infected", 0);
    occurences.put("virus", 0);
    occurences.put("malware", 0);
    occurences.put("scam", 0);
    occurences.put("printer", 0);
    occurences.put("printers", 0);
    occurences.put("printing", 0);
    occurences.put("duplex", 0);
    occurences.put("scan", 0);
    occurences.put("fax", 0);
    occurences.put("print", 0);
    occurences.put("duo", 0);
    occurences.put("multi", 0);
    occurences.put("factor", 0);
    occurences.put("authentication", 0);
    occurences.put("mfa", 0);
    occurences.put("multi-factor", 0);
    occurences.put("token", 0);
    occurences.put("fob", 0);
    occurences.put("otp", 0);
    occurences.put("time", 0);
    occurences.put("room", 0);
    occurences.put("floorplan", 0);
    occurences.put("request", 0);
    occurences.put("requesting", 0);
    occurences.put("access", 0);
    occurences.put("classroom", 0);
    occurences.put("vpn", 0);
    occurences.put("wifi", 0);
    occurences.put("internet", 0);
    occurences.put("ethernet", 0);
    occurences.put("lan", 0);
    occurences.put("wlan", 0);
    occurences.put("outage", 0);
    occurences.put("outages", 0);
    occurences.put("server", 0);
    occurences.put("adobe", 0);
    occurences.put("photoshop", 0);
    occurences.put("indesign", 0);
    occurences.put("illustrator", 0);
    occurences.put("premiere", 0);
    occurences.put("premiere", 0);
    occurences.put("acrobat", 0);
    occurences.put("pdf", 0);
    occurences.put("creative", 0);
    occurences.put("cloud", 0);
    occurences.put("lightroom", 0);
    occurences.put("bridge", 0);
    occurences.put("audition", 0);
    occurences.put("dreamweaver", 0);
    occurences.put("license", 0);
    occurences.put("suite", 0);
    occurences.put("verizon", 0);
    occurences.put("mifi",0);
    occurences.put("rental", 0);
    occurences.put("loaner", 0);
    occurences.put("rent", 0);
    occurences.put("spare", 0);
    occurences.put("borrow", 0);
    occurences.put("borrowed", 0);
    occurences.put("request", 0);
    occurences.put("day", 0);
    occurences.put("week", 0);
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
  public void setDateRequested(int dateRequested){
    this.dateRequested = dateRequested;
  }
  public void setDateCompleted(int dateCompleted){
    this.dateCompleted = dateCompleted;
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
  public int getDateRequested(){
    return this.dateRequested;
  }
  public int getDateCompleted(){
    return this.dateCompleted;
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

  public void findOccurences(String[] tokenizedDescription){
    for(int i = 0; i < tokenizedDescription.length; i++){
      if(occurences.containsKey(tokenizedDescription[i])){
        occurences.put(tokenizedDescription[i], occurences.get(tokenizedDescription[i]) + 1);
      }
    }
  }
  // Function to return all of the data from the case as input to neural network
  public HashMap<String, Integer> getAsInput() {
    findOccurences(tokenizedDescription);
    return occurences;
  }

  // Function to return correct labels of the data from the case
  public double[] getLabelsIfKnown() {
    double[] labelMatch = new double[20];
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
        case "Virus/Maleware":
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
        case "General Question":
          labelMatch[19] = 1;
          break;
      }
      return labelMatch;
      // return categories as double array with a 1 if it matches and a 0 if it doesn't
    }
    return null;
  }
}
