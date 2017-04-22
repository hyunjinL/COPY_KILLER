#include<stdio.h>
#include<string.h>
#include<stdlib.h>

typedef struct node{
	char num[100];
	int cnt;
}node;

int main()
{
	FILE *ifp;
	char s[100];
	char search[100];
	char* token;
	int i,key;
	int flag = 0;
	node *table = (node *)malloc(100000000);
	struct node sym;

	ifp=fopen("/tmp/scheme.GO.data","r");


	if(ifp == NULL){

		printf("파일을 열수없습니다\n");
		return 1;
	}

	while(fgets(s,100,ifp) != NULL){
		token=strtok(s,"\t ");
		while(token !=NULL)
		{
			if(*(s+0) == '0'){
				key = 0;
				for(i = 0;i<sizeof(table); i++){
					if(flag==0){
						break;
					}
					else if(strcmp(table[i].num,s)!=1&&strcmp(table[i].num,s)!=-1){
						strcpy(table[i].num,s);
						table[i].cnt++;
                                                key = 1;
                                                break;
					}
				}
				if(key == 0){
					strcpy(sym.num,s);
                        		sym.cnt = 1;
					strcpy(table[flag].num,sym.num);
					table[flag].cnt = sym.cnt;
					flag++;
				}
			}
			token=strtok(NULL,"\n");
		}
	}
	printf("=>Reading scheme.GO.data\n");
	printf("=>Total number of GOs :\n");
	printf("=>Enter GO to find :");
	scanf("%s",&search);
	key = 0;
	for(i = 0;i <sizeof(table);i++){            
		if(strcmp(table[i].num,s)!=1&&strcmp(table[i].num,s)!=-1){
			printf("%d GO:%s were found\n",table[i].cnt,search);
			key = 1;
			break;
		}
	}
	if(key == 0){
		printf("not found\n");
	}
	fclose(ifp);
	return 0;
}
