#include<stdio.h>
#include<string.h>
#include<stdlib.h>

typedef struct str{
	char a[100];
	int count;
}Table;
int Total = 0;

int main(int argc, char *argv[])
{
	FILE *fp;
	Table *st;
	int i;
	char *ptr, *token, *f;

	fp = fopen(argv[1], "r");
	ptr= (char *)malloc(sizeof(char) * 10000);

	while(fgets(ptr,sizeof(ptr),fp) != NULL)
	{
		token = strtok(ptr, "\t");
		if(0x30 > token[0] || token[0] > 0x39)
			continue;
		if(Total == 0)
		{
			st = (Table*)malloc(sizeof(Table) * (Total + 1));
			strcpy(st->a, token);
			st->count = 1;
			Total++;
		}
		else
		{
			for(i = 0; i < Total; i++)
			{
				if(strcmp((st+i)->a, token) == 0)
				{
					(st+i)->count++;
					break;
				}
			}			
			if(Total == i)
			{
				st = (Table*) realloc(st, sizeof(Table) * (Total+1));
				strcpy((st+Total)->a, token);
				(st+Total)->count = 1;
				Total++;
			}
		}
		while(token = strtok(NULL, "\t\n")) 
		{
			if(token[0] < 0x30 || token[0] > 0x39)
				break;
			else
			{
				for(i = 0; i < Total; i++)
				{
					if(strcmp((st+i)->a, token) == 0)
                       	         	{
						((st+i)->count)++;
						break;
					}
				}
				if(Total == i)
				{
					st = (Table*) realloc(st, sizeof(Table) * (Total+1));
					strcpy((st+Total)->a, token);
					(st+Total)->count = 1;
					Total++;
				
				}
			}
		}
	}
	
	printf("Reading scheme.GO.data\n");
        printf("Total number of GOs : %d\n", Total);
        printf("Enter GO to find : ");
        scanf("%s", f);
 	for (i = 0; i < Total; i++)
        {
                if (strcmp(f, (st+i)->a) == 0)
                {
                        printf("%d GO:%s were fourd.", (st+i)->count, f);
                        break;
                }
	}
	if(i == Total)
		printf("Not found.\n");
	fclose(fp);
}
