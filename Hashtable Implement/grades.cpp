// grades.cpp
// CSCI 455 PA5
// Name:
// Loginid:
// 
/*
 * A program to test the Table class.
 * How to run it:
 *      grades [hashSize]
 * 
 * the optional argument hashSize is the size of hash table to use.
 * if it's not given, the program uses default size (Table::HASH_SIZE)
 *
 */
#include <sstream>
#include "Table.h"

// cstdlib needed for call to atoi
#include <cstdlib>

int main(int argc, char * argv[]) {

  // gets the hash table size from the command line

  int hashSize = Table::HASH_SIZE;

  Table * grades;  // Table is dynamically allocated below, so we can call
                   // different constructors depending on input from the user.

  if (argc > 1) {
    hashSize = atoi(argv[1]);  // atoi converts c-string to int

    if (hashSize < 1) {
      cout << "Command line argument (hashSize) must be a positive number" 
	   << endl;
      return 1;
    }

    grades = new Table(hashSize);

  }
  else {   // no command line args given -- use default table size
    grades = new Table();
  }
  grades->hashStats(cout);
  /*
  grades->printAll();
  cout<<"--------------------------\n";
  grades->insert("a",120);

  grades->hashStats(cout);
  grades->printAll();
  cout<<"--------------------------\n";

  grades->insert("a",120);

  grades->hashStats(cout);
  grades->printAll();
  cout<<"--------------------------\n";
  grades->insert("b",140);

  grades->hashStats(cout);
  grades->printAll();
  cout<<"--------------------------\n";
  */
  // add more code here
  // Reminder: use -> when calling Table methods, since grades is type Table*


   char cstr[4096];
   bool keepgoing = true;


   //printCmdSummary();

   do {
     cout << "\nPlease enter a command [i, c, l, r, p, n, s, h, q]: ";
     cin.getline( cstr,4096);
     string c(cstr);
    // cout << c<<endl;

     if (cin.fail()) {
       cout << "ERROR: input stream failed." << endl;
       keepgoing = false;
     }


     else {

    	 stringstream ss(c);

    	 string cmd;
    	 ss >> cmd;

    	 if(cmd == "i"){

    		 string name,score;

    		 ss >> name;
    		ss >>score;

    		 int scoreNum = atoi(score.c_str());

    		 grades->insert(name,scoreNum);

    	 }
    	 if(cmd == "c"){
    		 string name,score;
    		 ss >> name;
    		    		ss >>score;

    		 int scoreNum = atoi(score.c_str());
    		 if(grades->remove(name)){
    			 grades->insert(name,scoreNum);
    		 }
    	 }
    	 if(cmd == "l"){
    		 string name;
    		 ss>>name;
    		 grades->lookup(name);
    	 }
    	 if(cmd == "r"){
    		 string name;
    		 ss>>name;
    		 grades->remove(name);
    	 }
    	 if(cmd == "p"){
    		 grades->printAll();
    	 }
    	 if(cmd == "n"){
    		 cout<<"there is totally : "<< grades->numEntries() <<" entries"<<endl;
    	 }
    	 if(cmd == "s"){
    		 grades->hashStats(cout);
    	 }
    	 if(cmd == "h"){
    		 cout<<  "i name score\n"<<
    				 "Insert this name and score in the grade table. If this name was already present, print a message to that effect, and don\'t do the insert.\n"<<
    				 "c name newscore\n"<<
    				 "Change the score for name. Print an appropriate message if this name isn\'t present.\n"<<
    				 "l name\n"<<
    				 "Lookup the name, and print out his or her score.\n"<<
    				 "r name\n"<<
    				 "Remove this student. If this student wasn\'t in the grade table, print a message to that effect.\n"<<
    				 "p\n"<<
    				 "Prints out all names and scores in the table.\n"<<
    				 "n\n"<<
    				 "Prints out the number of entries in the table.\n"<<
    				 "s\n"<<
    				 "Prints out statistics about the hash table at this point. (Calls hashStats() method)\n"<<
    				 "h\n"<<
    				 "(help) Prints out a brief command summary.\n"<<
    				 "q\n"<<
    				 "Exits the program.\n";
    	 }
    	 if(cmd == "q"){
    		 keepgoing = false;
    	 }
     }
   } while (keepgoing);

   return 0;

  return 0;
}
