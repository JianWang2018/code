/*
This is the test file to check the correctenss of the manifold defined in CpxNSTQOrth.h and CpxNSTQOrth.cpp.

---- WH
*/

#ifndef TESTCSO_H
#define TESTCSO_H

#include "ForDebug.h"
#include <iostream>
#include "randgen.h"
#include "Manifold.h"
#include "Problem.h"
#include "SolversLS.h"
#include <ctime>
#include "DriverMexProb.h"

#include "CSOVector.h"
#include "CSOVariable.h"
#include "CpxNStQOrth.h"

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

#if !defined(MATLAB_MEX_FILE) && defined(TESTCSO)
int main(void);
#endif

void testCSO();

#endif // end of TESTCSO_H
