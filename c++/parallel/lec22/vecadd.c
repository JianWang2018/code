#include <stdlib.h>

void vcos(int i, float* A, float* B, float* C)
{
  C[i] = A[i] + B[i];
}

int main(int argc, char * argv[])
{
  float *host_A, *host_B, *host_C;
  float *dev_A, *dev_B, *dev_C;
  int n, i;

  if (argc == 1) n = 1024;
  else n = atoi(argv[1]);
 
  host_A = (float*)malloc( n*sizeof(float) );
  host_B = (float*)malloc( n*sizeof(float) );
  host_C = (float*)malloc( n*sizeof(float) );

  for(i = 0; i < n; ++i ) {
    host_A[i] = (float) i;
    host_B[i] = (float) i;
  }

  for (i=0; i< n; i++)
    vcos(i, host_A, host_B, host_C);

  free(host_A);  
  free(host_B);
  free(host_C);
 
  return( 0 );
}
