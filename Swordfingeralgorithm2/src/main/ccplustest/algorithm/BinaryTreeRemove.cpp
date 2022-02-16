#include <stdio.h>

struct ListNode {
	int mValue;
	ListNode* mNext;
};

void RemoveNode(ListNode** pHead, int value) {
	if (pHead == NULL || *pHead == NULL) {
		return;
	} 
	
	ListNode* pToBeDeleted = NULL;
	if ((*pHead)->mValue == value) {
		pToBeDeleted = *pHead;
		*pHead = (*pHead)->mNext;
	} else {
		ListNode* pNode = *pHead;
		while (pNode->mNext != NULL && pNode->mNext->mValue != value) {
			pNode = pNode->mNext;
		}
		
		if (pNode->mNext != NULL && pNode->mNext->mValue == value) {
			pToBeDeleted = pNode->mNext;
			pNode->mNext = pNode->mNext->mNext;
		}
	}
	
	if (pToBeDeleted != NULL) {
		delete pToBeDeleted;
		pToBeDeleted = NULL;
	}
}

void test() {
	ListNode* node = new ListNode();
	ListNode* node1 = new ListNode();
	ListNode* node2 = new ListNode();
	ListNode* node3 = new ListNode();
	node->mValue = 1;
	node->mNext = node1;
	node1->mValue = 2;
	node1->mNext = node2;
	node2->mValue = 3;
	node2->mNext = node3;
	node3->mValue = 4;
	node3->mNext = NULL;
	
	RemoveNode(&node, 4);
	
	while (node != NULL) {
	    printf("check nodes: %d \n", node->mValue);	
	    node = node->mNext;
	}
}

int main() {
	
	test();
	
	return 0;
}
