/*
This is the test file to run the problem defined in EucQuadratic.h and EucQuadratic.cpp.

---- WH
*/

#ifndef TESTEUCQUADRATIC_H
#define TESTEUCQUADRATIC_H

#include "ForDebug.h"
#include <iostream>
#include "randgen.h"
#include "Manifold.h"
#include "Problem.h"
#include "SolversLS.h"
#include <ctime>

#include "EucVariable.h"
#include "EucVector.h"
#include "EucQuadratic.h"

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

#if !defined(MATLAB_MEX_FILE) && defined(TESTEUCQUADRATIC)
int main(void);
#endif

void testEucQuadratic(double *M, integer dim, double *X = nullptr, double *Xopt = nullptr);

#endif // end of TESTEUCQUADRATIC_H
