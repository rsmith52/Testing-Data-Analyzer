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
  String tokenizedDescription;
  String category;


  Case(){
    occurences.put("Office", 0);
    occurences.put("365", 0);
    occurences.put("O365", 0);
    occurences.put("Email", 0);
    occurences.put("Calendar", 0);
    occurences.put("Outlook", 0);
    occurences.put("Drive", 0);
    occurences.put("Shared", 0);
    occurences.put("Network", 0);
    occurences.put("SharedDrive", 0);
    occurences.put("Word", 0);
    occurences.put("PowerPoint", 0);
    occurences.put("Excel", 0);
    occurences.put("OneNote", 0);
    occurences.put("Outlook", 0);
    occurences.put("Power", 0);
    occurences.put("Monitor", 0);
    occurences.put("Firewall", 0);
    occurences.put("Setup", 0);
    occurences.put("New", 0);
    occurences.put("Computer", 0);
    occurences.put("Requested", 0);
    occurences.put("Requesting", 0);
    occurences.put("Request", 0);
    occurences.put("Purchase", 0);
    occurences.put("ALF", 0);
    occurences.put("Person", 0);
    occurences.put("Onsite", 0);
    occurences.put("Site", 0);
    occurences.put("Physical", 0);
    occurences.put("Location", 0);
    occurences.put("Come", 0);
    occurences.put("Wisclist", 0);
    occurences.put("List", 0);
    occurences.put("Lists", 0);
    occurences.put("Mass", 0);
    occurences.put("Realtime", 0);
    occurences.put("Real", 0);
    occurences.put("Time", 0);
    occurences.put("Audio", 0);
    occurences.put("HDMI", 0);
    occurences.put("Webchat", 0);
    occurences.put("Web Chat", 0);
    occurences.put("Voicemail", 0);
    occurences.put("Google", 0);
    occurences.put("Camera", 0);
    occurences.put("Projector", 0);
    occurences.put("Repair", 0);
    occurences.put("Broken", 0);
    occurences.put("Imaged", 0);
    occurences.put("Reimaged", 0);
    occurences.put("Issues", 0);
    occurences.put("Computer", 0);
    occurences.put("Wiped", 0);
    occurences.put("Formatted", 0);
    occurences.put("Format", 0);
    occurences.put("Re-Imaged", 0);
    occurences.put("Fix", 0);
    occurences.put("Fixed", 0);
    occurences.put("Role", 0);
    occurences.put("Based", 0);
    occurences.put("Employee", 0);
    occurences.put("Staff", 0);
    occurences.put("Member", 0);
    occurences.put("Setup", 0);
    occurences.put("Service", 0);
    occurences.put("Account", 0);
    occurences.put("Creation", 0);
    occurences.put("Admin", 0);
    occurences.put("Password", 0);
    occurences.put("Log", 0);
    occurences.put("In", 0);
    occurences.put("Login", 0);
    occurences.put("Username", 0);
    occurences.put("Incorrect", 0);
    occurences.put("Wrong", 0);
    occurences.put("Unable", 0);
    occurences.put("Click", 0);
    occurences.put("Link", 0);
    occurences.put("Clicked", 0);
    occurences.put("Phishing", 0);
    occurences.put("Infected", 0);
    occurences.put("Virus", 0);
    occurences.put("Malware", 0);
    occurences.put("Scam", 0);
    occurences.put("Printer", 0);
    occurences.put("Printers", 0);
    occurences.put("Printing", 0);
    occurences.put("Duplex", 0);
    occurences.put("Scan", 0);
    occurences.put("Fax", 0);
    occurences.put("Print", 0);
    occurences.put("Duo", 0);
    occurences.put("Multi", 0);
    occurences.put("Factor", 0);
    occurences.put("Authentication", 0);
    occurences.put("MFA", 0);
    occurences.put("Multi-Factor", 0);
    occurences.put("Token", 0);
    occurences.put("Fob", 0);
    occurences.put("OTP", 0);
    occurences.put("TIme", 0);
    occurences.put("Room", 0);
    occurences.put("Floorplan", 0);
    occurences.put("Request", 0);
    occurences.put("Requesting", 0);
    occurences.put("Access", 0);
    occurences.put("Classroom", 0);
    occurences.put("VPN", 0);
    occurences.put("Wifi", 0);
    occurences.put("Internet", 0);
    occurences.put("Ethernet", 0);
    occurences.put("LAN", 0);
    occurences.put("WLAN", 0);
    occurences.put("Outage", 0);
    occurences.put("Outages", 0);
    occurences.put("Server", 0);
    occurences.put("Adobe", 0);
    occurences.put("Photoshop", 0);
    occurences.put("Indesign", 0);
    occurences.put("Illustrator", 0);
    occurences.put("Premiere", 0);
    occurences.put("Premiere", 0);
    occurences.put("Acrobat", 0);
    occurences.put("PDF", 0);
    occurences.put("Creative", 0);
    occurences.put("Cloud", 0);
    occurences.put("Lightroom", 0);
    occurences.put("Bridge", 0);
    occurences.put("Audition", 0);
    occurences.put("Dreamweaver", 0);
    occurences.put("License", 0);
    occurences.put("Suite", 0);
    occurences.put("Verizon", 0);
    occurences.put("Mifi",0);
    occurences.put("Rental", 0);
    occurences.put("Loaner", 0);
    occurences.put("Rent", 0);
    occurences.put("Spare", 0);
    occurences.put("Borrow", 0);
    occurences.put("Borrowed", 0);
    occurences.put("Request", 0);
    occurences.put("Day", 0);
    occurences.put("Week", 0);
  }
  Case(int caseNumber, String caseOwner,String caseRequestor,
  int dateRequested, int dateCompleted, String description,
  String tokenizedDescription, String category){
    this.caseNumber = caseNumber;
    this.caseOwner = caseOwner;
    this.caseRequestor = caseRequestor;
    this.dateRequested = dateRequested;
    this.dateCompleted = dateCompleted;
    this.description = description;
    this.tokenizedDescription = tokenizedDescription;
    this.category = category;
    occurences.put("Office", 0);
    occurences.put("365", 0);
    occurences.put("O365", 0);
    occurences.put("Email", 0);
    occurences.put("Calendar", 0);
    occurences.put("Outlook", 0);
    occurences.put("Drive", 0);
    occurences.put("Shared", 0);
    occurences.put("Network", 0);
    occurences.put("SharedDrive", 0);
    occurences.put("Word", 0);
    occurences.put("PowerPoint", 0);
    occurences.put("Excel", 0);
    occurences.put("OneNote", 0);
    occurences.put("Outlook", 0);
    occurences.put("Power", 0);
    occurences.put("Monitor", 0);
    occurences.put("Computer Name:", 0);
    occurences.put("Firewall", 0);
    occurences.put("Setup", 0);
    occurences.put("New", 0);
    occurences.put("Computer", 0);
    occurences.put("Requested", 0);
    occurences.put("Requesting", 0);
    occurences.put("Request", 0);
    occurences.put("Purchase", 0);
    occurences.put("ALF", 0);
    occurences.put("Person", 0);
    occurences.put("Onsite", 0);
    occurences.put("Site", 0);
    occurences.put("Physical", 0);
    occurences.put("Location", 0);
    occurences.put("Come", 0);
    occurences.put("Wisclist", 0);
    occurences.put("List", 0);
    occurences.put("Lists", 0);
    occurences.put("Mass", 0);
    occurences.put("Realtime", 0);
    occurences.put("Real", 0);
    occurences.put("Time", 0);
    occurences.put("Audio", 0);
    occurences.put("HDMI", 0);
    occurences.put("Webchat", 0);
    occurences.put("Web Chat", 0);
    occurences.put("Voicemail", 0);
    occurences.put("Google Hangouts", 0);
    occurences.put("Camera", 0);
    occurences.put("Projector", 0);
    occurences.put("Repair", 0);
    occurences.put("Broken", 0);
    occurences.put("Imaged", 0);
    occurences.put("Reimaged", 0);
    occurences.put("Issues", 0);
    occurences.put("Computer", 0);
    occurences.put("Wiped", 0);
    occurences.put("Formatted", 0);
    occurences.put("Format", 0);
    occurences.put("Re-Imaged", 0);
    occurences.put("Fix", 0);
    occurences.put("Fixed", 0);
    occurences.put("Role", 0);
    occurences.put("Based", 0);
    occurences.put("Employee", 0);
    occurences.put("Staff", 0);
    occurences.put("Member", 0);
    occurences.put("Setup", 0);
    occurences.put("Service", 0);
    occurences.put("Account", 0);
    occurences.put("Creation", 0);
    occurences.put("Admin", 0);
    occurences.put("Password", 0);
    occurences.put("Log", 0);
    occurences.put("In", 0);
    occurences.put("Login", 0);
    occurences.put("Username", 0);
    occurences.put("Incorrect", 0);
    occurences.put("Wrong", 0);
    occurences.put("Unable", 0);
    occurences.put("Click", 0);
    occurences.put("Link", 0);
    occurences.put("Clicked", 0);
    occurences.put("Phishing", 0);
    occurences.put("Infected", 0);
    occurences.put("Virus", 0);
    occurences.put("Malware", 0);
    occurences.put("Scam", 0);
    occurences.put("Printer", 0);
    occurences.put("Printers", 0);
    occurences.put("Printing", 0);
    occurences.put("Duplex", 0);
    occurences.put("Scan", 0);
    occurences.put("Fax", 0);
    occurences.put("Print", 0);
    occurences.put("Duo", 0);
    occurences.put("Multi", 0);
    occurences.put("Factor", 0);
    occurences.put("Authentication", 0);
    occurences.put("MFA", 0);
    occurences.put("Multi-Factor", 0);
    occurences.put("Token", 0);
    occurences.put("Fob", 0);
    occurences.put("OTP", 0);
    occurences.put("TIme", 0);
    occurences.put("Room", 0);
    occurences.put("Floorplan", 0);
    occurences.put("Request", 0);
    occurences.put("Requesting", 0);
    occurences.put("Access", 0);
    occurences.put("Classroom", 0);
    occurences.put("VPN", 0);
    occurences.put("Wifi", 0);
    occurences.put("Internet", 0);
    occurences.put("Ethernet", 0);
    occurences.put("LAN", 0);
    occurences.put("WLAN", 0);
    occurences.put("Outage", 0);
    occurences.put("Outages", 0);
    occurences.put("Server", 0);
    occurences.put("Adobe", 0);
    occurences.put("Photoshop", 0);
    occurences.put("Indesign", 0);
    occurences.put("Illustrator", 0);
    occurences.put("Premiere Pro", 0);
    occurences.put("Premiere", 0);
    occurences.put("Acrobat", 0);
    occurences.put("PDF", 0);
    occurences.put("Creative", 0);
    occurences.put("Cloud", 0);
    occurences.put("Lightroom", 0);
    occurences.put("Bridge", 0);
    occurences.put("Audition", 0);
    occurences.put("Dreamweaver", 0);
    occurences.put("License", 0);
    occurences.put("Suite", 0);
    occurences.put("Verizon", 0);
    occurences.put("Mifi",0);
    occurences.put("Rental", 0);
    occurences.put("Loaner", 0);
    occurences.put("Rent", 0);
    occurences.put("Spare", 0);
    occurences.put("Borrow", 0);
    occurences.put("Borrowed", 0);
    occurences.put("Request", 0);
    occurences.put("Day", 0);
    occurences.put("Week", 0);
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
  public void setTokenizedDescription(String tokenizedDescription){
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
  public String getTokenizedDescription(){
    return this.tokenizedDescription;
  }
  public String getCategory(){
    return this.category;
  }

  public void findOccurences(String description){
    for(int i = 0; i < description.length(); i++){

    }
  }
  // Function to return all of the data from the case as input to neural network
  public HashMap<String, Integer> getAsInput() {
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
