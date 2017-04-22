#include <stdio.h>
#include<stdlib.h>
#include<string.h>

typedef struct node * nodePtr;
typedef  struct node  {
	int number;
	int cnt;
	nodePtr next;
}node;


nodePtr head, tail; 




void insert(int number) {
	nodePtr temp;
	

	if (head == NULL) { 
		temp = (nodePtr)malloc(sizeof(node)); 
		temp->number = number;
		temp->cnt = 1; 
		temp->next = NULL; 

		head = temp; 
		tail = head;
	}
	else if(plus_same_number(number)==0){		
		temp = (nodePtr)malloc(sizeof(node));
		temp->number = number; 
		temp->cnt = 1; 
		temp->next = NULL; 
		tail->next = temp; 
		tail = tail->next;
	}
}


int plus_find_by_number(int number) {
	nodePtr temp;
	

	temp = head;
	for (; temp != NULL; temp = temp->next) { 
		if (temp->number == number) {
			return temp->cnt;
		}
	}

	return -1;
}


int plus_same_number(int number) {
	nodePtr temp;
	int isFind = 0; 
	
	temp = head;	
	for (; temp != NULL;  temp = temp->next) { 
		if (temp->number == number) { 
			temp->cnt += 1;
			isFind = 1;
			break;
		}		
	}

	return isFind;	
}


int calc_total() {
	nodePtr temp;
	int i;
	int sum = 0;
	if (head == NULL) {
		printf("no list\n");
		return 0;
	}

	temp = head;
	printf("\n");
	for (i = 1; temp != NULL; ++i, temp = temp->next) { 
		sum += temp->cnt;
	}

	return sum;
}
void dir_free() {
	nodePtr temp,temp2;
	temp2 = head;
	temp = temp2->next;

	while (temp2 != NULL) {
		temp2 = temp->next;
		free(temp);
		temp = temp2;
	}
}
int main(int argc, char *argv[]) {	
	FILE *f;
	char line[256];
	char *ptr;
	int number, cnt;
	char fname[100];
	head = NULL;
	tail = NULL;
	
	strcpy(fname, argv[1]);
	f = fopen(fname, "r");
	printf("=>Reading %s", fname);

	while (1) {
		if (feof(f)) {			
			break;
		}
		fgets(line, 256, f);
		ptr = line;
		ptr = strtok(ptr, " ");
		
		number = atoi(ptr);
		insert(number);
		while (ptr = strtok(NULL, " ")) {			
			number = atoi(ptr);
			if(number !=0)
				insert(number);
		}		
	}
	


	fclose(f);


	printf("=>Total number of GOs : %d\n",calc_total());
	printf("=>Enter Go to find : ");
	scanf("%s", line);
	number = atoi(line);
	cnt = plus_find_by_number(number);

	printf("%d GO:%s were found.\n", cnt, line);
	dir_free();

}

