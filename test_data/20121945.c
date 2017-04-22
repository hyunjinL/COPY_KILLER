#include <stdio.h>
#include <string.h>
#include <stdlib.h>

struct classification{
	char i[1000];
	int count;
};

int main(int argc, char *argv[])
{
	struct classification *A;

	int i;
	FILE *scheme;
	char buffer[1000];
	char *c;
	int count1=0;
	int co = 0;

	scheme = fopen(argv[1],"r");

	while(fgets(buffer,sizeof(buffer),scheme)!=NULL){

		c = strtok(buffer,"\t ");
		if(count1==0){
			A = (struct classification*)malloc(sizeof(struct classification));
			strcpy(A->i,c);
			A->count=1;
			count1++;
		}
		else{
			for(i=0;i<count1;i++){
				if(strcmp(A[i].i , c) == 0){
					A[i].count++;
					break;
				}
			}
			if(i==count1){
				A = (struct classification*)realloc(A,sizeof(struct classification) * (count1+1));
			 	strcpy((A+count1)->i,c);
				(A+count1)->count=1;
				count1++;
			}
		}
		while(c=strtok(NULL,"\t\n ")){
			if(c[0] < '0' || c[0] > '9'){
				break;
			}
			for(i=0;i<count1;i++){
				if(strcmp(A[i].i , c) == 0){
					A[i].count++;
					break;
				}
			}
			if(i==count1){
				A = (struct classification*)realloc(A,sizeof(struct classification) * (count1+1));
			 	strcpy((A+count1)->i,c); 
				(A+count1)->count=1;
				count1++;
			}
		}
	}
	printf("Reading scheme.Go.data\n");

	fclose(scheme);
	return 0;
}
