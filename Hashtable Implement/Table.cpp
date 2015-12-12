// Table.cpp  Table class implementation
// CSCI 455 PA5
// Name:
// Loginid:

/*
 * Modified 11/22/11 by CMB
 *   changed name of constructor formal parameter to match .h file
 */

#include "Table.h"

#include <iostream>
#include <string>
#include <cassert>

//*************************************************************************
// Node class definition and member functions
//     You don't need to add or change anything in this section

// Note: we define the Node in the implementation file, because it's only
// used by the Table class; not by any Table client code.

struct Node {
  string key;
  int value;

  Node *next;

  Node(const string &theKey, int theValue);

  Node(const string &theKey, int theValue, Node *n);
};

Node::Node(const string &theKey, int theValue) {
  key = theKey;
  value = theValue;
  next = NULL;
}

Node::Node(const string &theKey, int theValue, Node *n) {
  key = theKey;
  value = theValue;
  next = n;
}

typedef Node * ListType;
ListType *bucket;
//*************************************************************************


Table::Table() {

	bucket = new ListType[Table::HASH_SIZE];
	hashSize = Table::HASH_SIZE;
	eleNum = 0;
	longestBucket = 0;
	nonEmptyBucket = 0;

}


Table::Table(unsigned int hSize) {
	bucket = new ListType[hSize];
	hashSize= hSize;
	eleNum = 0;
	longestBucket = 0;
	nonEmptyBucket = 0;

}


int * Table::lookup(const string &key) {

	unsigned int bucketNum = hashCode(key);

	if(*(bucket+bucketNum)){
		ListType iter = *(bucket+bucketNum);

		while(iter){

			if(iter->key == key){
				cout<<iter->key<<" : "<<iter->value<<endl;
				return &(iter->value);
			}
			iter = iter->next;
		}	
	}
	else{
		//bucket is empty
		return NULL;
	}

  	return NULL;   // dummy return value for stub
}

bool Table::remove(const string &key) {
	unsigned int bucketNum = hashCode(key);
	if(Table::lookup(key)){

		ListType iter = *(bucket+bucketNum);

		//update longest
		unsigned int longChainTmp=0;
		unsigned int tmp;
		//cout << "debug2" << ":"<<endl;

		for(int i=0; i < hashSize-1;i++){

			tmp = 0;

				if(*(bucket+i) && i!=bucketNum ){
					//cout << "debug4 case 1" << ":"<<i<<endl;
					ListType iter = *(bucket+i);
					//cout << i << ":"<<endl;

					while(iter){
						tmp++;
						iter = iter->next;
					}
					if(tmp > longChainTmp){
						longChainTmp = tmp;
					}

				}
				if(i==bucketNum && *(bucket+i)){
					// << "debug4 case 2" << ":"<<i<<endl;
					while(iter ){
						tmp++;
						iter = iter->next;
					}
					if(tmp - 1> longChainTmp){
						longChainTmp = tmp - 1;
					}
				}
				//cout << "debug5 case 2" << ":"<<i<<endl;
		}

		longestBucket = longChainTmp;
		eleNum--;
		iter = *(bucket+bucketNum);
		if(iter->key == key){
			//cout << "debug3" <<endl;
			if(!iter->next){
					nonEmptyBucket--;
			}
			//cout << "debug3" <<endl;
			*(bucket+bucketNum) = iter->next;
			//update longest & nonempty & number of entries
			return true;
		}
		else{
			ListType pre = iter;
			while( iter ){
				pre = iter;
				iter = iter->next;
				if(iter->key == key){

					pre->next = iter->next;
					iter->next = NULL;
					//free(iter);
					return true;
				}
			}
		}
	}
	else{
		cout<<"There is not such a student"<<endl;
		return false;
	}

	return false;  // dummy return value for stub

}

bool Table::insert(const string &key, int value) {

	//get hashcode from input and hashfunction

	unsigned int bucketNum = hashCode(key);

	if(*(bucket+bucketNum)){
		//if already exits

		if(Table::lookup(key)){
			cout <<"The key : "<< key << " is already existed"<<endl;
			return false;
		}
		else{
			unsigned int k=1;
			ListType tail = new Node(key,value);
			ListType iter = *(bucket+bucketNum);	
			while(iter->next){
				iter = iter->next;
				k++;
			}
			k++;
			iter->next = tail;
			eleNum ++;
			if(k > longestBucket){
				longestBucket = k;
			}
			//cout<<"get here and add: \n"<<key<<"+"<<value;
			return true;
		}	
	}
	else{

		//bucket is empty
		ListType head = new Node(key,value);
		//cout<<"get here and add: \n"<<key<<"+"<<value;
		*(bucket+bucketNum) = head;
		if(!longestBucket){
			longestBucket++;
		}
		nonEmptyBucket++;
		eleNum ++;
		return true;
	}
  	return false;  // dummy return value for stub
}

int Table::numEntries() const {
  return eleNum;      // dummy return value for stub
}



void Table::printAll() const {

	for(int i=0; i < hashSize;i++){

		if(*(bucket+i)){
			ListType iter = *(bucket+i);
			//cout << i << ":"<<endl;

			while(iter){
				cout<< iter->key <<" "<< iter->value<<endl;

				iter = iter->next;
			}
		}

	}

}

void Table::hashStats(ostream &out) const {
	//   number of buckets: 997
	   //   number of entries: 10
	   //   number of non-empty buckets: 9
	   //   longest chain: 2
	out << "number of buckets: "<<hashSize<<endl;
	out << "number of entries: "<<eleNum<<endl;
	out << "number of non-empty buckets: "<<nonEmptyBucket<<endl;
	out << "longest chain: "<<longestBucket<<endl;
}


// add definitions for your private methods here
