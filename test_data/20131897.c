#include<stdio.h>
#include<string.h>
#include<stdlib.h>

int main( int argc, char* argv[])
{
        FILE *fp;
        fp = fopen("/tmp/scheme.GO.data", "r");

        char *ch;
	
	fgets(*ch,1000,fp);
	
	while(fgets(*ch,1000,fp) != NULL)
		printf("%s", ch);

	return 0;
}
