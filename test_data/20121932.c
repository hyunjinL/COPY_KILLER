#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct  DATA
{
	char all[100];
	int count;
};

int main(int argc,char *argv[])
{
	typedef struct DATA Da;
	Da *p;
        FILE *fp = fopen("/tmp/scheme.GO.data","r");
	int a=0,b,i=0;
        char buf[256];
        char *tok = NULL;
	int flag=0;
        if(fp==NULL){
                printf("Error!");
                return 1;
        }

	while(fgets(buf,sizeof(buf),fp) != NULL)
        {


		while(1){
			tok = strtok(buf,"\t");
			if(tok == NULL)break;
			if(*(tok+0) == '0' && i==0)
               		{
				p = (Da*)malloc(sizeof(Da));
				strcpy(p->all,tok);
				p->count++;
			}
			if(*(tok+0) == '0' && i!=0)
			{
				for(b=0; b<i; b++){
					if(strcmp(p[b].all, tok) == 0){
						p[b].count++;
						flag = 1;
						break;
					}
					flag =0;
				}
				if(flag != 1){
					p = (Da*)realloc(p, sizeof(Da)*(i+1));
					strcpy(p[i].all,tok);
					p[i].count++;
				}
			}
			i += 1;	
		}
        }
	printf("Reading Scheme.GO.Data\n");
        printf("count : %d \n" ,p->count);
	for(a=0;a<30;a++){
		printf("%s\n",p->all);
	}
	fclose(fp);

        return 0;
}
