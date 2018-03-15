/*
This is the test file for the Brocokett problem defined in StieBrockett.h and StieBrockett.cpp.

---- WH
*/

#ifndef TESTSTIEBROCKETT_H
#define TESTSTIEBROCKETT_H

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
#include "StieBrockett.h"

/*Manifold related classes*/
#include "Manifold.h"
#include "StieVector.h"
#include "StieVariable.h"
#include "Stiefel.h"

/*Linesearch based solvers*/
#include "SolversLS.h"
#include "RSD.h"
#include "RNewton.h"
#include "RCG.h"
#include "RBroydenFamily.h"
#include "RWRBFGS.h"
#include "RBFGS.h"
#include "LRBFGS.h"
#include "RBFGSLPSub.h"

/*Trust-region based solvers*/
#include "SolversTR.h"
#include "RTRSD.h"
#include "RTRNewton.h"
#include "RTRSR1.h"
#include "LRTRSR1.h"

/*The global head file*/
#include "def.h"

using namespace ROPTLIB;

#if !defined(MATLAB_MEX_FILE) && defined(TESTSTIEBROCKETT)
int main(void);
#endif

/*The main test function*/
void testStieBrockett(double *B, double *D, integer n, integer p, double *X = nullptr, double *Xopt = nullptr);

#endif // end of TESTSTIEBROCKETT_H
