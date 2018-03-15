/*
This is the test file for the bounding box problem defined in OrthBoundingBox.h and OrthBoundingBox.cpp.

---- WH
*/

#ifndef TESTORTHBOUNDINGBOX_H
#define TESTORTHBOUNDINGBOX_H

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
#include "OrthBoundingBox.h"

/*Manifold related classes*/
#include "Manifold.h"
#include "OrthGroupVector.h"
#include "OrthGroupVariable.h"
#include "OrthGroup.h"

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
#include "LRBFGSLPSub.h"
#include "RGS.h"

/*Trust-region based solvers*/
#include "SolversTR.h"
#include "RTRSD.h"
#include "RTRNewton.h"
#include "RTRSR1.h"
#include "LRTRSR1.h"

/*The global head file*/
#include "def.h"

using namespace ROPTLIB;

#if !defined(MATLAB_MEX_FILE) && defined(TESTORTHBOUNDINGBOX)
int main(void);
#endif

/*The main test function*/
void testOrthBoundingBox(double *E, integer d, integer n, double *X = nullptr, double *Xopt = nullptr);

#endif // end of TESTORTHBOUNDINGBOX_H
