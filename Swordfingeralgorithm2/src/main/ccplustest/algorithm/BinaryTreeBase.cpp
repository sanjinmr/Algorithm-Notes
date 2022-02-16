#include <stdio.h>

struct ListNode {
	int mValue;
	ListNode* pNext; 
}; 

void AddToTail(ListNode** pHead, int value) {
	ListNode* pNew = new ListNode();
	pNew->mValue = value;
	pNew->pNext = NULL;
	
	if (*pHead == NULL) {
		*pHead = pNew;
		return;
	}
	
	ListNode* pNode = *pHead;
	while (pNode->pNext != NULL) {
		pNode = pNode->pNext;
	}	
	pNode->pNext = pNew;
}


void test() {
	//ListNode* nodes = new ListNode();
	ListNode* nodes = NULL;

	AddToTail(&nodes, 12); 
	AddToTail(&nodes, 13); 
	AddToTail(&nodes, 14); 
	AddToTail(&nodes, 15); 
	
	while (nodes != NULL) {
		printf("check nodes: %d \n", nodes->mValue);	
		nodes = nodes->pNext;
	}
}


int main() {
	
	test(); 
	
	return 0;
} 



