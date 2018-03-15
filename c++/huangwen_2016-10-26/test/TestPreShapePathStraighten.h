//
//  TestPreShapePathStraighten.hpp
//  headertry
//
//  Created by Yaqing You on 11/3/15.
//  Copyright © 2015 Yaqing You. All rights reserved.
//

#ifndef TestPreShapePathStraighten_h
#define TestPreShapePathStraighten_h

#include <fstream>
#include "ForDebug.h"
#include <iostream>
#include "randgen.h"
#include "Manifold.h"
#include "Problem.h"
#include "SolversLS.h"
#include <ctime>
#include "DriverMexProb.h"

#include "PSCVariable.h"
#include "PSCVector.h"


#include "PreShapeCurves.h"
#include "PreShapePathStraighten.h"

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

#include "DriverElasticCurvesRO.h"

using namespace ROPTLIB;

#if !defined(MATLAB_MEX_FILE) && defined(TESTPRESHAPEPATHSTRAIGHTEN)
int main(void);
#endif

void testPreShapePathStraighten();


#endif /* TestPreShapePathStraighten_h */
