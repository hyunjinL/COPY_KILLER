#include <stdio.h>
#include <string.h>
#include <stdlib.h>

typedef struct unique  // 구조체 선언
{
	int number;
	int count;

}Unique;

int main(int argc, char* argv[])
{
	char* result;
	char *buf;
	FILE *fp;
	Unique *uq;
	int total = 0;
	int i, n;

	uq = (Unique *)malloc(sizeof(Unique));

	fp = fopen("/tmp/scheme.GO.data","r");

	if (fp == NULL)	{
		printf("파일이 없습니다\n");
		return 1;
	}

	while(fgets(buf, sizeof(buf), fp) != NULL) {

			result = strtok(buf, "\t");

			while(result != NULL) {
				result = strtok(NULL, "\t\n");
				uq = result;
				if(uq =! result) {
					total++;
					for(i=0; i<total; i++) {
						uq = (Unique *)realloc(sizeof(Unique)*total);
						(uq+i)->number;
						(uq+i)->count++; 
					}
				}
			}
	}
	printf("=>Reading scheme.GO.data\n");
	printf("=>Total number of GOs : %d\n", total);
	printf("=>Enter GO to find : ");
	scanf("%d", &n);
	printf("%d GO:%d were found.", Unique->number, n);
}
