#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>

struct STR{
	char str[100];
	int count;
};

int main(int argc, char *argv[])
{
	FILE *data;
	char *num, *x;
	struct STR *st;
	int i, cnt=0, sum=0;

	x = (char*)malloc(sizeof(char)*400);
	data = fopen(argv[1], "r");

	while((fgets(x, sizeof(x), data) != NULL)){
		num = strtok(x, "\t");
		do{
			for(i=0; i<cnt; i++){
				if (strcmp((st+i)->str, num) == 0){
					(st+i)->count++;
					break;
				}
			}
			if(cnt == 0){
				cnt++;
				st = (struct STR *)malloc(sizeof(struct STR) * 1);
				strcpy(st->str, num);
				st->count = 1;
			}
			if(cnt == i){
				cnt++;
				st = (struct STR *)realloc(st, sizeof(struct STR) * cnt);
				strcpy((st+cnt)->str, num);
				(st+cnt)->count = 1;
			}
			num = strtok(NULL, "\t\n");
		}while(isalpha(num[0]));
	}
/*	printf("Reding scheme.GO.data\n");
	printf("Total number of GOs : %d\n", sum);
	printf("Enter Go to find : ");
	scanf("%d\n", );
	printf("%d GO: %d were found", );
*/
	for(i=0; i<cnt; i++)	
	printf("%s %d\n",st[i].str, st[i].count);

	fclose(data);
}
