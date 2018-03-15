/*
This is the test file to run the problem defined in EucFrechetMean.h and EucFrechetMean.cpp.

---- WH
*/

#ifndef TESTEUCFRECHETMEAN_H
#define TESTEUCFRECHETMEAN_H

#include "ForDebug.h"
#include <iostream>
#include "randgen.h"
#include "Manifold.h"
#include "Problem.h"
#include "SolversLS.h"
#include <ctime>

#include "EucVariable.h"
#include "EucVector.h"
#include "EucFrechetMean.h"

#include "StieBrockett.h"
#include "StieVector.h"
#include "StieVariable.h"
#include "Stiefel.h"

#include "RSD.h"
#include "RNewton.h"
#include "RCG.h"
#include "RBroydenFamily.h"
#include "RWRBFGS.h"
#include "RBFGS.h"
#include "LRBFGS.h"

#include "SolversTR.h"
#include "RTRSD.h"
#include "RTRNewton.h"
#include "RTRSR1.h"
#include "LRTRSR1.h"

#include "def.h"

using namespace ROPTLIB;

#if !defined(MATLAB_MEX_FILE) && defined(TESTEUCFRECHETMEAN)
int main(void);
#endif

void testEucFrechetMean(double *Data, double *Weight, integer num, integer dim, double *X = nullptr, double *Xopt = nullptr);

#endif // end of TESTEUCFRECHETMEAN_H
