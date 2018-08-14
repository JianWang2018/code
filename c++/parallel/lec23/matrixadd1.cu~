/* kernel routine starts with keyword __global__ */
#include <stdio.h>

#define N 4096

__global__ void matrixadd(float* A, float* B, float* C)
{
  int  i = blockIdx.x*blockDim.x+threadIdx.x;  
  int  j = blockIdx.y*blockDim.y+threadIdx.y;
  int index = i+j*N;
  int k;

//  if ((i<N) && (j<N)) {
    for (k=0; k<N; k++)
      C[index] += A[i+k*N] * B[k+j*N];

   // C[index] = sqrt(sin(cos(A[index]))) + sqrt(sin(cos(B[index])));
}

int main(int argc, char * argv[])
{
  float *host_A, *host_B, *host_C;
  float *dev_A, *dev_B, *dev_C;
  int i, j, n;

  if (argc == 1) n = 16;
  else n = atoi(argv[1]);
 
  if (n > 32) {
    printf("n must be less than 32\n");
    exit(0);
  }

  /* 1. allocate host memory */
  host_A = (float*)malloc( N*N*sizeof(float) );
  host_B = (float*)malloc( N*N*sizeof(float) );
  host_C = (float*)malloc( N*N*sizeof(float) );

  /* 2. allocate GPU memory */
  cudaMalloc( &dev_A, N*N*sizeof(float) );
  cudaMalloc( &dev_B, N*N*sizeof(float) ); 
  cudaMalloc( &dev_C, N*N*sizeof(float) ); 

  /* initialize array A and B */
  for( i = 0; i < N; ++i ) {
    for( j = 0; j < N; ++j ) {
      host_A[i+j*N] = (float) 1.0;
      host_B[i+j*N] = (float) 1.0;
    }
  }

  /* 3. Copydata (host_A and host_B) to GPU */
  cudaMemcpy( dev_A, host_A, N*N*sizeof(float), cudaMemcpyHostToDevice );
  cudaMemcpy( dev_B, host_B, N*N*sizeof(float), cudaMemcpyHostToDevice );

  /* 4. call kernel routine to execute on GPU */
  /* launch 1 thread per vector-element, 1024 threads per block */
  dim3 dimBlock(n, n);
  dim3 dimGrid(N/n, N/n);

  matrixadd<<<dimGrid, dimBlock>>>( dev_A, dev_B, dev_C );

  /* transfer results from GPU (dev_C) to CPU (host_C) */
  cudaMemcpy( host_C, dev_C, N*N*sizeof(float), cudaMemcpyDeviceToHost );
 
#ifdef CHECK
  {
    FILE *fd;
    fd = fopen("tmp333", "w");
    for (i=0;i<N*N; i++) {	
      fprintf(fd, "%f\n", host_C[i]);
    }
  }
#endif

  /* free host and GPU memory */
  free(host_A);  
  free(host_B);
  free(host_C);
  cudaFree(dev_A);
  cudaFree(dev_B);
  cudaFree(dev_C);
 
  return( 0 );
}
