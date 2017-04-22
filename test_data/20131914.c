/* 실행파일을 돌리면 3분정도 소요... */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct NODE{
	int GO_num;
	int count;
}NODE;

int main(int argc, char *argv[])
{
	FILE *fp;
	char input[200], *tk;
	NODE *p;
	int GO_total = 0, tt_count, cp, i, j, k, a[13];	//

	fp = fopen(argv[1], "r");

	if(fp == NULL) {
		printf("Cannot open an input file\n");
		return 1;
	}
	printf("=>Reading %s\n", argv[1]);

	p = (NODE *)malloc(sizeof(NODE));

	while(fgets(input, sizeof(input), fp) != NULL) {
		tk = strtok(input, "\t");
		if(GO_total == 0) {
			p->GO_num = atoi(tk);
			p->count = 1;
			GO_total ++;
		}
		for(i = 0; tk != NULL; i++) {
			if(atoi(tk) == 0) {
				tk = strtok(NULL, "\t\n");
				i--;
				continue;
			}
			a[i] = 0;
			a[i] = atoi(tk);
			tk = strtok(NULL, "\t\n");
		}
		k = i;
		for(j = 0; j < k; j++) {
			for(i = 0, tt_count = 0; i < GO_total; i++) {
				if((p+i)->GO_num == a[j]) {
					(p+i)->count++;
					continue;
				}else {
					tt_count++;
				}
				if(tt_count == GO_total) {
					GO_total++;
					p = realloc(p, GO_total*sizeof(NODE));
					(p+GO_total-1)->GO_num = a[j];
					(p+GO_total-1)->count = 1;
				}
			}
		}
	}
	printf("=>Total number of GOs : %d\n", GO_total);
	printf("=>Enter GO to find : ");
	scanf("%d", &cp);

	for(i = 0; i < GO_total; i++) {
		if((p+i)->GO_num == cp) {		
			printf("  %d GO:%07d were found.\n", (p+i)->count-1, (p+i)->GO_num);
			break;
		}
	}
}
