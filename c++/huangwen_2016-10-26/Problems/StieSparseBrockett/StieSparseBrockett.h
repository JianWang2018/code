/*
This file defines the class for the problem
min_X tr(X^T B X D), where B is a sparse symmetric matrix represented
by three arrays, ir and jc is the same format as the Matlab, D is a diagonal matrix and X \in St(p, n).

Problem --> StieSparseBrockett

---- WH
*/

#ifndef STIESPARSEBROCKETT_H
#define STIESPARSEBROCKETT_H

#include "Stiefel.h"
#include "StieVariable.h"
#include "StieVector.h"
#include "Problem.h"
#include "SharedSpace.h"
#include "def.h"
#include "MyMatrix.h"

/*Define the namespace*/
namespace ROPTLIB{

	class StieSparseBrockett : public Problem{
	public:
		StieSparseBrockett(double *inB, unsigned long long *inir, unsigned long long *injc, integer innzmax, double *inD, integer inn, integer inp);
		virtual ~StieSparseBrockett();
		virtual double f(Variable *x) const;

		//virtual void RieGrad(Variable *x, Vector *gf) const;
		//virtual void RieHessianEta(Variable *x, Vector *etax, Vector *xix) const;

		virtual void EucGrad(Variable *x, Vector *egf) const;
		virtual void EucHessianEta(Variable *x, Vector *etax, Vector *exix) const;

		double *B;
		unsigned long long *ir;
		unsigned long long *jc;
		integer nzmax;
		double *D;
		integer n;
		integer p;
	};
}; /*end of ROPTLIB namespace*/
#endif // end of STIESPARSEBROCKETT_H
