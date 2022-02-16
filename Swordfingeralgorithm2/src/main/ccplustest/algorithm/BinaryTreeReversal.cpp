#include <stdio.h>
#include <stack>

struct ListNode {
	int mValue;
	ListNode* pNext;
};

void ReversalPrint(ListNode* pHead) {
	std::stack<ListNode*> nodes;
	
	ListNode* pNode = pHead;
	while (pNode != NULL) {
		nodes.push(pNode);
		pNode = pNode->pNext;
	}
	
	while (!nodes.empty()) {
		pNode = nodes.top();
		printf("node: %d \n", pNode->mValue);
		nodes.pop();
	}
}

void test() {
	ListNode* pHead = new ListNode();
	ListNode* node1 = new ListNode();
	ListNode* node2 = new ListNode();
	ListNode* node3 = new ListNode();
	pHead->mValue = 1;
	pHead->pNext = node1;
	node1->mValue = 2;
	node1->pNext = node2;
	node2->mValue = 3;
	node2->pNext = node3;
	node3->mValue = 4;
	node3->pNext = NULL;
	
	ReversalPrint(pHead);
}


int main() {
	test();
	
	return 0;
}
