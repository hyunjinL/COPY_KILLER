#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<ctype.h>

struct J{
	char pt[30];
	int count;
};

int main(int argc, char *argv[])
{
	FILE* su;
	char *num = (char*)malloc(sizeof(char)*200);
	struct J *jsh;
	int count=0,i,j,sexy=1;
	char hw[1024];
	int sum = 0;

	su=fopen(argv[1],"r");
	while(fgets(hw, 1024, su)!=NULL){
		num = strtok(hw,"\t");

		if(count==0){
			jsh = (struct J *)malloc(sizeof(struct J)*1);
			strcpy(jsh->pt, num);
			count++;
		}
		else{
			for (i = 0; i < count; i++){
				if (strcmp((jsh + i)->pt, num) == 0){
					(jsh + i)->count++;
					break;
				}
			}
			if (i == count){
				jsh = (struct J *)realloc(jsh, sizeof(struct J) * (count + 1));
				strcpy((jsh + count)->pt, num);
				count++;
			}
		}
			sexy=1;
		while (num = strtok(NULL, "\t")){
	       		for(j=0; num[j]; j++){
				if (isalpha(num[j])){
					sexy=2;
					break;
				}
			}
			if(sexy)
				break;

			for (i = 0; i < count; i++){
				if (strcmp((jsh+i)->pt, num) == 0){
					(jsh+i)->count++;
					break;
				}
			}
			if (i == count){
				jsh = (struct J *)realloc(jsh, sizeof(struct J) * (count + 1));
				strcpy((jsh + count)->pt, num);
				count++;
			}
		}
	}
	for (i = 0; i < count; i++){
		sum += (jsh+i)->count;
	}
	printf("Reading scheme.GO.datal\n");
	printf("Total number of GOs:%d\n:",sum);
        printf("Enter GO to find :");
	scanf("%s",hw);
	for (i = 0; i < count; i++){
		if (strcmp((jsh+i)->pt, hw) == 0){
			printf("%d GO:%s were found\n", (jsh+i)->count, hw);
		}

	}


	fclose(su);
}
