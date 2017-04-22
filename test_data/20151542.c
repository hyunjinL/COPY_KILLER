#include <stdio.h>
#include<string.h>
#include<stdlib.h>
struct str
{
 	char *str;
	int count;	
};

int main(int argc, char *argv[])
{
	int i, leng, scheme_count = 0, t = 0, found = 0;
	char num[10];
	struct str *scheme_str;
	char buf[100];
	FILE *file;


	file = fopen("/tmp/scheme.GO.data", "r");

	while(fgets(buf, sizeof(buf), file))
	{
		if (scheme_str == NULL)
		{
			scheme_str = malloc(sizeof(struct str));
			leng = strlen(buf);
			scheme_str[0].str = malloc(sizeof(char) * leng);
			strcpy(scheme_str[0].str, buf);	
			scheme_str[0].count++;
			scheme_count++;
		}

		for(i = 0; i < scheme_count; i++)
		{
			if (strcmp(scheme_str[i].str, buf) == 0) 
			{
				scheme_str[i].count++;
				break;
			}
		}

		if (scheme_count == i)
		{
			leng = strlen(buf);
			scheme_str = realloc(scheme_str, sizeof(struct str) * (i + 1));
			scheme_str[i].str = malloc(sizeof(char) *leng);
			strcpy(scheme_str[i].str, buf);
			scheme_str[i].count++;
			scheme_count++;
		}
		t++;
	}

	printf("Total number of GOs : %d\n", t);
	printf("Enter GO to find : ");
	scanf("%s", num);

	for(i = 0; i < scheme_count; i++)
	{
		if (strstr(scheme_str[i].str, num) != NULL)
		{
			found += scheme_str[i].count;
		}
	}
	printf("%d GO:%s wer found\n", found, num);
	
	fclose(file);

	return 0;

}
