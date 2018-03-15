/*
This is the test file for the Sparse coding problem on the manifold defined in EucPositive.h and EucPositive.cpp.
See details in Section IV.B in [CS15].
	[CS15] Anoop Cherian and Suvrit Sra. "Riemannian Dictionary Learning and Sparse Coding for Positive
	Definite Matrices"

---- WH
*/

#ifndef TESTEUCPOSSPCD_H
#define TESTEUCPOSSPCD_H

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
#include "EucPosSpCd.h"

/*Manifold related classes*/
#include "Manifold.h"
#include "EucPositive.h"
#include "EucPosVariable.h"
#include "EucPosVector.h"

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

#if !defined(MATLAB_MEX_FILE) && defined(TESTEUCPOSSPCD)
int main(void);
#endif

/*The main test function*/
void testEucPosSpCd(void);

#endif // end of TESTEUCPOSSPCD_H
