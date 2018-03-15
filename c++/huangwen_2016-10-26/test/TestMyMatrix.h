/*
This is the test file to test the wrapper functions, of lapack and blas, in MyMatrix.h and MyMatrix.cpp.

---- WH
*/

#ifndef TESTMYMATRIX_H
#define TESTMYMATRIX_H

/*Help to debug the code*/
#include "ForDebug.h"

/*Output to console*/
#include <iostream>

/*Generate random number*/
#include "randgen.h"

/*Computational time*/
#include <ctime>

/*The global head file*/
#include "def.h"

/*Test MyMatrix class*/
#include "MyMatrix.h"

using namespace ROPTLIB;

#if !defined(MATLAB_MEX_FILE) && defined(TESTMYMATRIX)
int main(void);
#endif

void testEigenSymmetricM(void);
void testExpSymmetricM(void);
void testLogSymmetricM(void);

#endif // end of TESTSTIEBROCKETT_H
