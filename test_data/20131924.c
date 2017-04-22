#include<stdio.h>
#include<stdlib.h>
#include<string.h>

#define MAX_SIZE 512
struct Data
{
        char str[7];
        int num;
};

int main()
{
        FILE *file = fopen("/tmp/scheme.GO.data", "r");
        int n = 1, i, search, go


        char t[MAX_SIZE];
        char *token;
        char d[7];
        struct Data *data = (struct Data*)malloc(sizeof(struct Data) * n);

        while( fgets(t, sizeof(t), file) )
        {
                go = 0;

                while(1)
                {

                        if(go == 0)
                        {
                                token = strtok(t, "\t");
                                go = 1;
                        }
                        else if(go == 1)
                        {
                                token = strtok(NULL, "\t");
                        }

                        search = 0;

                        if(token[0] != '0')
                                break;

                        for(i = 0 ; i < n-1 ; i++)
                        {
                                if( strcmp(data[i].str, token) == 0 )
                                {
                                        data[i].num++;
                                        search = 1;
                                        break;
                                }
                        }

                        if(search == 0)
                        {
                                strcpy(data[n-1].str, token);
                                data[n-1].num = 1;

                                n += 1;
                                data = (struct Data*)realloc(data, sizeof(struct Data)*n);
                        }
            }
        }

	printf("Reading scheme.GO.data\n");
	printf("Toteal number of GOs :%d\n ",n);
	printf("Enter GO to find : ");
        scanf("%s", d);

        for(i = 0 ; i < n-1 ; i++)
        {
                printf("%s\n", data[i].str);
                if(strcmp(d, data[i].str) == 0)
                {
                        printf("%d GO: %s were found", data[i].num, d);
                        break;
                }
        }

        free(data);
        fclose(file);
        return 0;
}
