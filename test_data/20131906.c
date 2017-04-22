#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

int main(int argc, char *argv[])
{
	FILE *fp;
	int i=0, j=0;
	char line[100];
	char *moonja = (char *)malloc(20);
	int sum = 0;
	int count = 0;
	char *select = (char *)malloc(20);

	fp = fopen(argv[1], "r");

	scanf("%s", select);

	for (i=0; i<3; i++) {
		fgets(line, sizeof(line), fp);
		moonja = strtok(moonja, "\t");
		for (j=0; j<20; j++) {
			moonja = strtok(NULL, "\t\n");
			sum ++;
			if ( isdigit (*moonja) ) {	
				if (strcmp((moonja+j), select) == 0)
					count++;
			}
			else
				break;
		}
	}
	printf("=> Total number of GOs : %d", sum);
	printf("=> Reading scheme.GO.data\n");
	printf("=> Enter GO to find : %s\n %d GO:%s were found.\n", select, count, select);
}
