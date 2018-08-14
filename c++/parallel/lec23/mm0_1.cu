#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>

struct timeval t1, t2;

#define BLOCK_SIZE 16

// kernel MM routine
__global__ void mmkernel(float *a, float *b, float  *c, int N, int M, int K)
{
  int i = threadIdx.x;
  int j = threadIdx.y;
  float sum = 0.0f;
  for (int k = 0; k< M; k++) sum += a[i+N*k] * b[k+K*j];
  c [i+N*j] = sum;
}

// host multiplication function
// C = A * B
// A is a hA x wA matrix
// B is a wA x wB matrix
// C is a hA x wB matrix

void Mul (const float *A, const float *B, float *C, int N, int M, int K)
{
  int size;
  float *dev_A, *dev_B, *dev_C;

printf("%d %d %d\n", N, M, K);

  size = N*M*sizeof(float);
  cudaMalloc((void **)&dev_A, size);
  cudaMemcpy(dev_A, A, size, cudaMemcpyHostToDevice);

  size = M*K *sizeof(float);
  cudaMalloc((void **)&dev_B, size);
  cudaMemcpy(dev_B, B, size, cudaMemcpyHostToDevice);

  size = N*K * sizeof(float);
  cudaMalloc((void **)&dev_C, size);

  dim3 dimBlock(1);
  dim3 dimGrid(N, K);
 
  mmkernel<<<dimBlock, dimGrid>>> (dev_A, dev_B, dev_C, N, M, K);

  cudaMemcpy(C, dev_C, size, cudaMemcpyDeviceToHost);
  cudaFree(dev_A);
  cudaFree(dev_B);
  cudaFree(dev_C);
}

int main( int argc, char *argv[])
{
  float *A, *B, *C;
  int  N, M, K, iter, i;
  int method;

  if (argc < 6) {
    printf("Usage: a.out N M K iter method\n");
    exit(0);
  }

  N= atoi(argv[1]);
  M = atoi(argv[2]);
  K = atoi(argv[3]);
  iter = 1;
  if (argc >=5) 
    iter = atoi(argv[4]);

  method = 0;
  if (argc >= 6) 
    method = atoi(argv[5]);

  A = (float *)malloc(N*M*sizeof(float));
  B = (float *)malloc(M*K*sizeof(float));
  C = (float *)malloc(N*K*sizeof(float));

  srand48(100);

  for (i=0; i<N*M; ++i) {
    //      A[i] = drand48();
    A[i] = 1.0;
    C[i] = 0.0;
  }
  for (i=0; i<M*K; ++i) {
    //      B[i] = drand48();
    //      B[i] = myid*1.0;
    B[i] = 1.0;
  }

  gettimeofday(&t1, NULL);
  for (i=0; i<iter; i++) {
    if (method == 0) {
      //      printf("A[0][0] = %lf, B[0][0] = %lf\n", *A, *B);
      Mul(A, B, C, N, M, K);
   } else {
      printf("Method not supported.\n");
      exit(0);
    }
  }
  gettimeofday(&t2, NULL);
  printf("Time for the matrix multiplication(%d) is %d milliseconds\n",
         method,
         (t2.tv_sec - t1.tv_sec)*1000 + 
         (t2.tv_usec - t1.tv_usec) / 1000);

#ifdef CHECK
  {

     FILE *fd;
     if ((fd = fopen("tmp333", "w")) == NULL) {
       printf("Cannot open tmp333\n"); exit(0);
     }
     
     for (i=0; i<N*K; i++) 
       fprintf(fd, "%6.2lf\n", C[i]);
     fclose(fd);
  }
#endif
  return 0;
}
