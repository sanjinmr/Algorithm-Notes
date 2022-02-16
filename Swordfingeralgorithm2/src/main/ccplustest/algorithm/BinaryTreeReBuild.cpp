#include <stdio.h>
#include <iostream> 
#include <exception>
#include <stdexcept>
#include <cstdio>

struct BinaryTreeNode {
	int mValue;
	BinaryTreeNode* pLeft;
	BinaryTreeNode* pRight;
};

void PrintTreeNode(const BinaryTreeNode* pNode)
{
    if(pNode != NULL)
    {
        printf("value of this node is: %d\n", pNode->mValue);

        if(pNode->pLeft != NULL)
            printf("value of its left child is: %d.\n", pNode->pLeft->mValue);
        else
            printf("left child is nullptr.\n");

        if(pNode->pRight != NULL)
            printf("value of its right child is: %d.\n", pNode->pRight->mValue);
        else
            printf("right child is nullptr.\n");
    }
    else
    {
        printf("this node is nullptr.\n");
    }

    printf("\n");
}

void PrintTree(const BinaryTreeNode* pRoot)
{
    PrintTreeNode(pRoot);

    if(pRoot != NULL)
    {
        if(pRoot->pLeft != NULL)
            PrintTree(pRoot->pLeft);

        if(pRoot->pRight != NULL)
            PrintTree(pRoot->pRight);
    }
}

void DestroyTree(BinaryTreeNode* pRoot)
{
    if(pRoot != NULL)
    {
        BinaryTreeNode* pLeft = pRoot->pLeft;
        BinaryTreeNode* pRight = pRoot->pRight;

        delete pRoot;
        pRoot = NULL;

        DestroyTree(pLeft);
        DestroyTree(pRight);
    }
}

BinaryTreeNode* ConstructCore(int* startPreorder, int* endPreorder, int* startInorder, int* endInorder) {
	printf("ConstructCore: %d %d %d %d \n", *startPreorder, *endPreorder, *startInorder, *endInorder);
	
	int rootValue = startPreorder[0];
	BinaryTreeNode* root = new BinaryTreeNode();
	root->mValue = rootValue;
	root->pLeft = root->pRight = NULL;
	
	if (startPreorder == endPreorder) {
		if (startInorder == endInorder && *startPreorder == *startInorder) {
			return root;
		} else {
			throw std::logic_error("Invalid input.");
		}
	}
	
	int* rootInorder = startInorder;
	while (rootInorder <= endInorder && *rootInorder != rootValue) {
		rootInorder ++;
	}
	if (rootInorder == endInorder && *rootInorder != rootValue) {
		throw std::logic_error("Invalid input.");
	}
	int leftLength = rootInorder - startInorder;
	
	int* leftPreorderEnd = startPreorder + leftLength;
	if (leftLength > 0) {
		root->pLeft = ConstructCore(startPreorder + 1, leftPreorderEnd, startInorder, rootInorder - 1);
	}
	
	if (leftLength < endPreorder - startPreorder) {
		root->pRight = ConstructCore(leftPreorderEnd + 1, endPreorder, rootInorder + 1, endInorder);
	}
	
	return root;
}

BinaryTreeNode* Construct(int* preorder, int* inorder, int length) {
	if (preorder == NULL || inorder == NULL || length <= 0) {
		return NULL;
	}
	printf("Construct: %d %d %d \n", *preorder, *inorder, length);
	return ConstructCore(preorder, preorder + length - 1, inorder, inorder + length - 1);
}

void test() {
	int preOrderValues[] = {1, 2, 4, 7, 3, 5, 6, 8};
	int midOrderValues[] = {4, 7, 2, 1, 5, 3, 8, 6};
	
	//printf("test: %d %d \n", *preOrderValues, *preOrderValues);
	
	BinaryTreeNode* node = Construct(preOrderValues, midOrderValues, 8);
	
	PrintTree(node);
}


int main() {
	
	try {
	    test();	
	} catch(std::exception& exception) {
	    printf(exception.what());
	}
	
	
	return 0;
} 
