/*
This is the test file to run the problem defined in StieSoftICA.h and StieSoftICA.cpp.

---- WH
*/

#ifndef TESTSTIESOFTICA_H
#define TESTSTIESOFTICA_H

#include "ForDebug.h"
#include <iostream>
#include "randgen.h"
#include "Manifold.h"
#include "Problem.h"
#include "SolversLS.h"
#include <ctime>
#include "DriverMexProb.h"

#include "StieSoftICA.h"
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

#if !defined(MATLAB_MEX_FILE) && defined(TESTSTIESOFTICA)
int main(void);
#endif

using namespace ROPTLIB;

#endif // end of TESTSTIESOFTICA_H
