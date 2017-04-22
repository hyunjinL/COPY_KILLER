#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<ctype.h>

typedef struct NODE{

	char data[10000];
	int count;
	struct NODE *link;

}NODE;

NODE *prev, *p, *next;
NODE *list = NULL;

void get_token(char *buf);
void CHECK_WORD(char *cp);

int all_num = 0;

int main()
{
	FILE *fp;
	char word[10000];
	int num = 0;
	char compare[10000];

	if ((fp = fopen("/tmp/scheme.GO.data", "r")) == NULL){
		fprintf(stderr, "file not found...\n");
		exit(1);
	}
	while (fgets(word, sizeof(word), fp) != NULL){
		get_token(word);
	}

	fclose(fp);

	printf("all num : %d ", all_num);
	printf("insert data : ");
	scanf("%s", compare);

	p = list;
	while(p != NULL){
		if(strcmp(p->data, compare) == 0){
			printf("num : %d\n", p->count);
			break;
		}else if(p->link == NULL){
			printf("Not Found Data\n");
		}
		p = p->link;
	}


	p = list;
	while(p != NULL){
		next = p->link;
		free(p);
		p = next;
	}

	return 0;

}

void get_token(char *buf)
{
	char *cp;

	for (cp = strtok(buf, " \t\n"); cp != NULL;){
		if(isdigit(*cp) && isdigit(*(cp+1)) && isdigit(*(cp+2))){
			if(list == NULL){
				p = (NODE *)malloc(sizeof(NODE));
				strcpy(p->data, cp);
				p->count = 1;
				list = p;
				p->link = NULL;
				prev = p;
				all_num++;
			}else{
				p = list;
				while(p!=NULL){
					if(strcmp(p->data, cp) == 0){
						p->count++;
						all_num++;
						break;
					}else if(p->link == NULL){
						p = (NODE *)malloc(sizeof(NODE));
						strcpy(p->data, cp);
						p->count = 1;
						prev->link = p;
						p->link = NULL;
						prev = p;
						all_num++;
						break;
					}
					p = p->link;
				}
			}
		}else{
			break;
		}
		cp = strtok(NULL, " \t\n");
	}
}
