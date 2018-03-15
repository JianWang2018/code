//
//  KarcherMean.hpp
//  Updated
//
//  Created by Yaqing You on 5/4/16.
//  Copyright © 2016 Yaqing You. All rights reserved.
//

#ifndef KarcherMean_h
#define KarcherMean_h

#include "ElasticShape.h"
#include "ShapeVariable.h"
#include "ShapeVector.h"
#include "Problem.h"
#include "SharedSpace.h"
#include "def.h"
#include "ShapePathStraighten.h"

#include <stdio.h>

#include <fstream>
#include "ForDebug.h"
#include <iostream>
#include "randgen.h"
#include "Manifold.h"
#include "SolversLS.h"
#include <ctime>

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

#include "DriverElasticCurvesRO.h"

/*Define the namespace*/
namespace ROPTLIB{
    class KarcherMean : public Problem{
    public:
        KarcherMean(double *Curves, integer innumP, integer indim, integer innumC, integer innumS);
        
        virtual ~KarcherMean();
        
        virtual double f(Variable *x) const;
        
        virtual void EucGrad(Variable *x, Vector *egf) const;
        
        void ComputeGeodesic(const double *q1, const double *q2, double &distance, double *w) const;
        
    private:
        integer numC;
        integer numP;
        integer dim;
        integer numS;
        double *Qs;
	};
    double LinesearchInput(Variable *x1, Vector *eta1, double initialstepsize, double initialslope, const Problem *Prob);
    
}; /*end of ROPTLIB namespace*/

#endif /* KarcherMean_h */
