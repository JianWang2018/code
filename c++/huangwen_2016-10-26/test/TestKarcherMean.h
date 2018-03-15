//
//  TestKarcherMean.hpp
//  Updated
//
//  Created by Yaqing You on 5/7/16.
//  Copyright © 2016 Yaqing You. All rights reserved.
//

#ifndef TestKarcherMean_h
#define TestKarcherMean_h

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

#include "KarcherMean.h"
#include "ElasticShape.h"
#include "ShapeVariable.h"
#include "ShapeVector.h"

#include "RBFGSLPSub.h"


using namespace ROPTLIB;

#if !defined(MATLAB_MEX_FILE) && defined(TESTKARCHERMEAN)
int main(void);
#endif

void testKarcherMean();

#endif /* TestKarcherMean_h */
