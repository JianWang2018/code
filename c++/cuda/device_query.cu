#include <stdio.h>
#include <cuda_runtime.h>
int main( ) {
    int dev = 0;
    cudaDeviceProp prop;
    cudaGetDeviceProperties(&prop, dev);
    printf("device id %d, name %s\n", dev, prop.name);
    printf("number of multi-processors = %d\n", 
        prop.multiProcessorCount);
    printf("Total constant memory: %4.2f kb\n", 
        prop.totalConstMem/1024.0);
    printf("Shared memory per block: %4.2f kb\n",
        prop.sharedMemPerBlock/1024.0);
    printf("Total registers per block: %d\n", 
        prop.regsPerBlock);
    printf("Maximum threads per block: %d\n", 
        prop.maxThreadsPerBlock);
    printf("Maximum threads per multi-processor: %d\n", 
        prop.maxThreadsPerMultiProcessor);
    printf("Maximum number of warps per multi-processor %d\n",
        prop.maxThreadsPerMultiProcessor/32);
    return 0;
}