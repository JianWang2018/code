/*
This is the test file to run the problem defined in WeightedLowRank.h and WeightedLowRank.cpp.

---- WH
*/

#ifndef TESTWEIGHTEDLOWRANK_H
#define TESTWEIGHTEDLOWRANK_H

#include "ForDebug.h"
#include <iostream>
#include "randgen.h"
#include "Manifold.h"
#include "Problem.h"
#include "SolversLS.h"
#include <ctime>

#include "WeightedLowRank.h"
#include "LowRank.h"
#include "LowRankVariable.h"

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

#if !defined(MATLAB_MEX_FILE) && defined(TESTWEIGHTEDLOWRANK)
int main(void);
#endif

void testWeightedLowRank(void);

#endif
