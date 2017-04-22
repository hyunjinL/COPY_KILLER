#include <stdio.h>
#include <string.h>
#include <stdlib.h>

typedef struct epik {
	int cnt;
	int num;
}epik;

int main(int argc, char* argv[])
{
	int i, ch, *ptr;
	char *pt;
	FILE *f1;
	epik *dp;

	dp = (epik *)malloc(sizeof(epik));
	f1 = fopen("/tmp/scheme.GO.data", "r");
	if (f1==NULL)
	{
		puts("");
		return -1;
	}

	while(!fEOF(f1))
	{ i = 0;
		fgets(ch, sizeof(ch), f1);
		ptr = strtok(s, "\t");
		num =(int *)realloc(sizeof(int));

		while(f1 == NULL) {


		fclose(f1);
}
