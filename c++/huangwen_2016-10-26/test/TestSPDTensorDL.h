/*
This is the test file for the Dictionary Learning problem on the manifold defined in SPDTensor.h and SPDTensor.cpp.
See details in Section IV.A in [CS15].
	[CS15] Anoop Cherian and Suvrit Sra. "Riemannian Dictionary Learning and Sparse Coding for Positive
	Definite Matrices"

---- WH
*/

#ifndef TESTSPDTENSORDL_H
#define TESTSPDTENSORDL_H

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
#include "SPDTensorDL.h"

/*Manifold related classes*/
#include "ProductManifold.h"
#include "SPDTVector.h"
#include "SPDTVariable.h"
#include "SPDTensor.h"

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

#if !defined(MATLAB_MEX_FILE) && defined(TESTSPDTENSORDL)
int main(void);
#endif

/*The main test function*/
void testSPDTensorDL(void);

#endif // end of TESTSPDTENSORDL_H
