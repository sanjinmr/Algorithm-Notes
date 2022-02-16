#include <stdio.h>

struct BinaryTreeNode {
	char mValue;
	BinaryTreeNode* pRight;
	BinaryTreeNode* pLeft;
	BinaryTreeNode* pParent;
}; 


BinaryTreeNode* GetNext(BinaryTreeNode* pNode) {
	if (pNode == NULL) {
		return NULL;
	}
	
	BinaryTreeNode* pNext = NULL;
	
	// 1. 如果有右子节点：next是右子树的最左子节点
	if (pNode->pRight != NULL) {
		BinaryTreeNode* pRight = pNode->pRight;
		while (pRight->pLeft != NULL) {
			pRight = pRight->pLeft;
		}

		pNext = pRight;
	}

	// 2. 如果没有右子节点：next需要进一步判断
	if (pNode->pRight == NULL) {
		BinaryTreeNode* pCurrent = pNode;
		BinaryTreeNode* pParent = pCurrent->pParent;

	    // 2.1 如果是父节点的左节点：next是父节点
		if (pCurrent != NULL && pParent != NULL && pCurrent == pParent->pLeft) {
			pNext = pParent;
		}

		// 2.2 如果是父节点的右节点：next是父节点为爷爷节点的左节点时的爷爷节点，否则没有next节点
		if (pCurrent != NULL && pParent != NULL && pCurrent == pParent->pRight) {
			while (pParent != NULL && pParent->pParent != NULL && pParent != pParent->pParent->pLeft) {
				pParent = pParent->pParent;
			}
			if (pParent->pParent != NULL) {
				pNext = pParent->pParent;
			} else {
				pNext = NULL;
			}
		}
	}

	return pNext;
}

void test() {
	// 创建中序遍历顺序为{d,b,h,e,i,a,f,c,g}的二叉树
	BinaryTreeNode* nodea = new BinaryTreeNode();
	nodea->mValue = 'a';
	BinaryTreeNode* nodeb = new BinaryTreeNode();
	nodeb->mValue = 'b';
	BinaryTreeNode* nodec = new BinaryTreeNode();
	nodec->mValue = 'c';
	BinaryTreeNode* noded = new BinaryTreeNode();
	noded->mValue = 'd';
	BinaryTreeNode* nodee = new BinaryTreeNode();
	nodee->mValue = 'e';
	BinaryTreeNode* nodef = new BinaryTreeNode();
	nodef->mValue = 'f';
	BinaryTreeNode* nodeg = new BinaryTreeNode();
	nodeg->mValue = 'g';
	BinaryTreeNode* nodeh = new BinaryTreeNode();
	nodeh->mValue = 'h';
	BinaryTreeNode* nodei = new BinaryTreeNode();
	nodei->mValue = 'i';

	nodea->pParent = NULL;
	nodea->pLeft = nodeb;
	nodea->pRight = nodec;

	nodeb->pParent = nodea;
	nodeb->pLeft = noded;
	nodeb->pRight = nodee;

	noded->pParent = nodeb;
	noded->pLeft = NULL;
	noded->pRight = NULL;

	nodee->pParent = nodeb;
	nodee->pLeft = nodeh;
	nodee->pRight = nodei;

	nodeh->pParent = nodee;
	nodeh->pLeft = NULL;
	nodeh->pRight = NULL;

	nodei->pParent = nodee;
	nodei->pLeft = NULL;
	nodei->pRight = NULL;

	nodec->pParent = nodea;
	nodec->pLeft = nodef;
	nodec->pRight = nodeg;

	nodef->pParent = nodec;
	nodef->pLeft = NULL;
	nodef->pRight = NULL;

	nodeg->pParent = nodec;
	nodeg->pLeft = NULL;
	nodeg->pRight = NULL;

	// 查询指定节点的下一个节点
	BinaryTreeNode* node = GetNext(nodeg);
	if (node == NULL) {
		printf("value: no next node! \n");
	} else {
		printf("value: %c \n", node->mValue);
	}
}

int main() {
	test();

	return 0;
}