/*
This is the test file to run the problem defined in StieBrockett.h and StieBrockett.cpp with p = 1, 
i.e., \min_{x \in S^{n - 1}) x^T B x.

---- WH
*/

#ifndef TESTSPHERERAYQUO_H
#define TESTSPHERERAYQUO_H

#include "ForDebug.h"
#include <iostream>
#include "randgen.h"
#include "Manifold.h"
#include "Problem.h"
#include "SolversLS.h"
#include <ctime>

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

#include "Problem.h"
#include "Sphere.h"
#include "SphereVariable.h"

using namespace ROPTLIB;

#if !defined(MATLAB_MEX_FILE) && defined(TESTSPHERERAYQUO)
int main(void);
#endif

void testSphereRayQuo(double *B, double *D, integer n, double *X = nullptr, double *Xopt = nullptr);

#endif // end of TESTSPHERERAYQUO_H