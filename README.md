# ADS_ASSIGNMENT
Impementation of K-Dimentional tree to store student data.
Student data consist of five elements:
1: Roll number
2: Semester
3: Marks
4: Pointer to the right child.
5: Pointer to the left child.

Tree is structered on the basis of user choice either by roll or sem.
Program provide three basic operations
Insert - By Roll/Sem
Delete- Basis of Marks
INorder Traversal.

Everytime we delete a node it is collected by garbage collector to avoid memory wastage.
For deletion we perform inorder traversal and find out all those nodes that have marks less or equal to the limit(stated by the user) .
We store thos nodes in an ArrayList and then we delete all nodes in the arraylist linearly.


While insertion if two nodes have same semester then it is sorted on the basis of roll or vice versa.

*DOWNLOAD AND COPY THE JAVA FILE IN YOUR JAVA PROJECT AND RUN*

ADNAN HUSAIN.
CSE
