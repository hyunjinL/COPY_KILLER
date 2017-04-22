#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>
typedef struct Data {
	char num[8];
	int count;
	struct Data *next;
} GO;
GO *Root_node, *tmp_ptr;

static int total_count = 0;
int flag;

void search_list(char *temp)
{
	GO *tmp = Root_node;

	while(tmp != NULL)
	{
		if(!strncmp(tmp->num, temp, 7))
		{
			tmp->count++;
			flag = 1;
			return;
		}
		else
			 tmp = tmp->next;
	}
}

void create(char *temp, GO *node, GO *last_node)
{
	last_node = (GO*)malloc(sizeof(GO));
	strncpy(last_node->num, temp, 7);
	last_node->count = 1;
	last_node->next = NULL;
	total_count++;

	node->next = last_node;
}

int main(int argc, char* argv[])
{
	FILE* stream;
	char *temp, search_num[8];
	char line_data[128];
	GO *last_node;

	int i;

// file open
	stream = fopen(argv[1], "r");
	if(stream == NULL)
	{
		printf("File open error...!!!");
		exit(1);
	}

	printf("Reading scheme.GO.data\n");

	while(!feof(stream))
	{
		fgets(line_data, sizeof(line_data), stream);

		temp = strtok(line_data, "\t");
		if(isdigit(temp[0]))
		{
			if(total_count == 0)
			{
				Root_node = (GO*)malloc(sizeof(GO));
				strncpy(Root_node->num, temp, 7);
				Root_node->count = 1;
				Root_node->next = NULL;
				total_count++;
			}
			else
			{
				flag = 0;
				search_list(temp);
				if(!flag)
				{
					tmp_ptr = Root_node;
					while(tmp_ptr->next != NULL)
						tmp_ptr = tmp_ptr->next;
					create(temp, tmp_ptr, last_node);
				}
			}
		}

		while((temp = strtok(NULL, "\t\n")))
		{
			if(isdigit(temp[0]))
			{
				flag = 0;
				search_list(temp);
				if(!flag)
				{
					tmp_ptr = Root_node;
					while(tmp_ptr->next != NULL)
						tmp_ptr = tmp_ptr->next;
					create(temp, tmp_ptr, last_node);
				}
			}
			else
				break;
		}
	}

	printf("Total number of GO's : %d\n", total_count);

	printf("Enter GO to find : ");
	scanf("%s", search_num);

	tmp_ptr = Root_node;
	while(tmp_ptr != NULL)
	{
		flag = 0;
		if(!strncmp(tmp_ptr->num, search_num, 7))
		{
			printf("%d GO:%s were found.\n", tmp_ptr->count, tmp_ptr->num);
			flag = 1;
			break;
		}
		else
			tmp_ptr = tmp_ptr->next;
	}
	if(!flag)
		printf("Not exist %s GO...\n", search_num);

	free(Root_node);

// file close
	if(fclose(stream))
		printf("File close error...!!!\n");

	return 0;
}
