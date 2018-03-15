/*
This is the test file for the Reyleigh Quotient problem defined in GrassRQ.h and GrassRQ.cpp.

---- WH
*/

#ifndef TESTGRASSRQ_H
#define TESTGRASSRQ_H

/*Help to debug the code*/
#include "ForDebug.h"

/*Output to console*/
#include <iostream>

/*Generate random number*/
#include "randgen.h"

/*Computational time*/
#include <ctime>

/*If this test file is called from Matlab, then functions in DriverMexProb.h are used.*/
#include "DriverMexProb.h"

/*Problem related classes*/
#include "Problem.h"
#include "GrassRQ.h"

/*Manifold related classes*/
#include "Manifold.h"
#include "GrassVector.h"
#include "GrassVariable.h"
#include "Grassmann.h"

/*Linesearch based solvers*/
#include "SolversLS.h"
#include "RSD.h"
#include "RNewton.h"
#include "RCG.h"
#include "RBroydenFamily.h"
#include "RWRBFGS.h"
#include "RBFGS.h"
#include "LRBFGS.h"

/*Trust-region based solvers*/
#include "SolversTR.h"
#include "RTRSD.h"
#include "RTRNewton.h"
#include "RTRSR1.h"
#include "LRTRSR1.h"

/*The global head file*/
#include "def.h"

using namespace ROPTLIB;

#if !defined(MATLAB_MEX_FILE) && defined(TESTGRASSRQ)
int main(void);
#endif

/*The main test function*/
void testGrassRQ(double *B, integer n, integer p, double *X = nullptr, double *Xopt = nullptr);

#endif // end of TESTGRASSRQ_H
